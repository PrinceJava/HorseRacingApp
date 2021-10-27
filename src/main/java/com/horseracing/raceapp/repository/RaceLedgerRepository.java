package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Race;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RaceLedgerRepository extends MongoRepository<RaceLedger, String> {


    @Query("{'id': ?0}")
    public RaceLedger findRaceLedgerById(String id);

    @Query("{'_id': ?0},{$push: {results: ['place': ?1, 'horse': ?2, 'jockey': ?3, 'speed': ?4")
    public void pushResultToLedger(String id, String place, String horse, String jockey, String speed);

    @Query(value = "{'results': {$elemMatch: {$and: [{horse: ?0},{'place': '1'}]}}}",count = true)
    public Integer getWinsCount(String horseName);

    @Query(value = "{'results': {$elemMatch: {$and: [{horse: ?0},{'place': '2'}]}}}",count = true)
    public Integer getSecondCount(String horseName);

    @Query(value = "{'results': {$elemMatch: {$and: [{horse: ?0},{'place': '3'}]}}}",count = true)
    public Integer getThirdCount(String horseName);

    @Query(value = "{'results': {$elemMatch: {horse: ?0}}}",count = true)
    public Integer getTotalCount(String horseName);

}
