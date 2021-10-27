package com.horseracing.raceapp.service;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.Result;
import com.horseracing.raceapp.repository.*;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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


    @Override
    public List<RaceLedger> getRaces() {
        return raceLedgerRepository.findAll();
    }

    @Override
    public RaceLedger getRace(String id) {
        return null;
    }

    @Override
    public RaceLedger addEntry(RaceLedger raceLedger) {
        RaceLedger addedLedger = new RaceLedger();
        addedLedger.setDate(raceLedger.getDate());
        addedLedger.setTrackName(raceLedger.getTrackName());
        addedLedger.setResults(raceLedger.getResults());
        return raceLedgerRepository.save(addedLedger);
    }

    @Override
    public RaceLedger updateEntry(String id) {
        return null;
    }

    @Override
    public RaceLedger deleteEntry(String id) {
        return null;
    }

    @Override
    public RaceLedger createEntry(String trackName, List<Map.Entry<Horse, Jockey>> results) {
        RaceLedger createdLedger = new RaceLedger();
        createdLedger.setDate(LocalDateTime.now());
        createdLedger.setTrackName(trackName);
        Result result = new Result();
        for(int i = 1; i <= results.size(); i++){
            result.setPlace(String.valueOf(i));
            result.setHorse(results.get(i).getKey().getName());
            result.setFinalSpeed(results.get(i).getKey().getSpeed());
            result.setJockey(results.get(i).getValue().getName());
            raceLedgerRepository.save(createdLedger);
//            mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(createdLedger.getId()), new Update().push("results", new Result[]{result})));
            raceLedgerRepository.pushResultToLedger(createdLedger.getId(), result.getPlace(), result.getHorse(),result.getJockey(),result.getFinalSpeed());
            raceLedgerRepository.save(createdLedger);
        }
        return raceLedgerRepository.save(createdLedger);
    }
}
