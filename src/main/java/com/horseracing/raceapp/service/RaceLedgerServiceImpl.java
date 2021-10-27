package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.*;
import com.horseracing.raceapp.repository.*;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
        return raceLedgerRepository.findAll();
    }

    @Override
    public RaceLedger getRace(String raceId) {
        try {
            return raceLedgerRepository.findRaceLedgerById(raceId);
        } catch (NoSuchElementException e){
            throw new InformationNotFoundException("Race not found");
        }
    }

    @Override
    public RaceLedger addEntry(RaceLedger raceLedger){
        return raceLedgerRepository.save(raceLedger);
    }

    @Override
    public RaceLedger updateEntry(String raceId, RaceLedger raceLedgerObject) {
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
    try {
        RaceLedger raceLedger = raceLedgerRepository.findRaceLedgerById(id);
        raceLedgerRepository.delete(raceLedger);
    }catch (NoSuchElementException e) {
        throw new InformationNotFoundException("No such race found");
    }
    }

    @Override
    public void createEntry(String trackName, List<Map.Entry<Horse, Jockey>> results) {

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

    @Override
    public List<String> getRecord(String horseName) {
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
