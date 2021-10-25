package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StableRepository extends MongoRepository<Stable, String> {

    @Query("{'stable.name': ?0}")
    public Stable findStableByName(String stableName);

}
