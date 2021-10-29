package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.*;
import com.horseracing.raceapp.repository.*;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/*
 * HORSE RACING APP
 * ---- RACE LEDGER SERVICE IMPL ------
 *   Race Ledger - Record NoSQL Document that houses all previous records
 *      CRUD FUNCTIONS including GET, GET ALL, UPDATE, DELETE
 *
 *      CREATE ENTRY - takes input from raceServiceImpl - startRace()
 *                      generates entry in ledger
 *
 * Get Record - gets record of input horseName including count of first, second, third, and total races
 * */

@Service
public class RaceLedgerServiceImpl implements RaceLedgerService {

    @Autowired
    HorseRepository horseRepository;

    @Autowired
    StableRepository stableRepository;

    @Autowired
    JockeyRepository jockeyRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    RaceLedgerRepository raceLedgerRepository;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    MongoClient mongoClient;

    @Override
    public List<RaceLedger> getRaces() {
        System.out.println("RACE LEDGER SERVICE CALLING getRaces ==>");
        return raceLedgerRepository.findAll();
    }

    @Override
    public RaceLedger getRace(String raceId) {
        System.out.println("RACE LEDGER SERVICE CALLING getRace ==>");
        try {
            return raceLedgerRepository.findRaceLedgerById(raceId);
        } catch (NoSuchElementException e){
            throw new InformationNotFoundException("Race not found");
        }
    }

    @Override
    public RaceLedger addEntry(RaceLedger raceLedger){
        System.out.println("RACE LEDGER SERVICE CALLING addEntry ==>");
        return raceLedgerRepository.save(raceLedger);
    }

    @Override
    public RaceLedger updateEntry(String raceId, RaceLedger raceLedgerObject) {
        System.out.println("RACE LEDGER SERVICE CALLING updateEntry ==>");
        try{
            RaceLedger updateEntry = raceLedgerRepository.findRaceLedgerById(raceId);
            if(raceLedgerObject.getId() != null){
                updateEntry.setId(raceLedgerObject.getId());
            }
            if(raceLedgerObject.getDate() != null){
                updateEntry.setDate(raceLedgerObject.getDate());
            }
            if(raceLedgerObject.getTrackName() !=null){
                updateEntry.setTrackName(raceLedgerObject.getTrackName());
            }
            if(raceLedgerObject.getResults() != null){
                updateEntry.setResults(raceLedgerObject.getResults());
            }

            return raceLedgerRepository.save(updateEntry);
        }catch (NoSuchElementException e){
            throw new InformationNotFoundException("Entry not found");
        }
    }

    @Override
    public void deleteEntry(String id) {
        System.out.println("RACE LEDGER SERVICE CALLING deleteEntry ==>");
    try {
        RaceLedger raceLedger = raceLedgerRepository.findRaceLedgerById(id);
        raceLedgerRepository.delete(raceLedger);
    }catch (NoSuchElementException e) {
        throw new InformationNotFoundException("No such race found");
    }
    }

    /**
     * Create Entry method takes in List of sorted Results and Track Name and creates a ledger Entry
     * 1. Create a Race Ledger object createdLedger to store variable Track, Date&Time.
     * 2. Save that object to the ledger with no results
     * 3. Create Results Object results that will loop through all rows or Results List:
     *      a. Overwrite any values with current entries Horse Name, Jockey Name, Horse Speed
     * 4. Utilize Query and Update objects to find the RaceLedger Object and push results with each field of Result obj.
     * 5. Use mongoTemplate.upsert to add all Updated Changes to Query Object of createdLedger
     * @param trackName - selectedTrack from startRace
     * @param results - List of Horse Jockeys, sorted by horse final speed, which is a double
     */
    @Override
    public void createEntry(String trackName, List<Map.Entry<Horse, Jockey>> results) {
        System.out.println("RACE LEDGER SERVICE CALLING createEntry ==>");

        RaceLedger createdLedger = new RaceLedger();
        createdLedger.setDate(LocalDateTime.now());
        createdLedger.setTrackName(trackName);
        raceLedgerRepository.save(createdLedger);
        Result result = new Result();
        for(int i = 0; i < results.size(); i++){
            result.setPlace(String.valueOf(i+1));
            result.setHorse(results.get(i).getKey().getName());
            result.setFinalSpeed(results.get(i).getKey().getSpeed());
            result.setJockey(results.get(i).getValue().getName());



            Query query = Query.query(Criteria.where("_id").is(createdLedger.getId()));
            Update update = new Update().push("results").each(result);
            mongoTemplate.upsert(query, update, "raceLedger");
        }
    }

    /**
     * Get Record utilizes RaceLedgerRepository Custom Queries to generate variables
     * Ints are created by searching all RaceLedger Entries and counting times name and place match
     * Create an ArrayList() to store all the string values from the ints and return that Array List to the console
     * @param horseName - passed from JSON body
     * @return - List of Strings
     */
    @Override
    public List<String> getRecord(String horseName) {
        System.out.println("RACE LEDGER SERVICE CALLING getRecord ==>");
        try{
            List<String> horseRecord = new ArrayList<>();
            Integer first = raceLedgerRepository.getWinsCount(horseName);
            Integer second = raceLedgerRepository.getSecondCount(horseName);
            Integer third = raceLedgerRepository.getThirdCount(horseName);
            Integer total = raceLedgerRepository.getTotalCount(horseName);

            horseRecord.add("Total 1st place finishes: " + first);
            horseRecord.add("Total 2nd place finishes: " + second);
            horseRecord.add("Total 3rd place finishes: " + third);
            horseRecord.add("Total Races: " + total);

            return horseRecord;
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Information not found");
        }
    }
}
