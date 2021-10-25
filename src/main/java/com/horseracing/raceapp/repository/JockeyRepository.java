package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JockeyRepository extends MongoRepository<Jockey, String> {

    @Query("{'name': ?0}")
    public Jockey findJockeyByName(String name);

}
