package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HorseRepository extends MongoRepository<Horse, String> {

    @Query("{'name': ?0}")
    public Horse findHorseByName(String name);

    @Query("{'stable.name': ?0}")
    public List<Horse> findHorseByStableName(String stableName);
}
