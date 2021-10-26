package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.RaceLedger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RaceLedgerRepository extends MongoRepository<RaceLedger, String> {

    @Query("{'id': ?0}")
    public RaceLedger findRaceLedgerById(String id);

}
