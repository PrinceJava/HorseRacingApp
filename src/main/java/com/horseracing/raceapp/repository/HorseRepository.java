package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HorseRepository extends MongoRepository<Horse, String> {

    public Horse findHorseByName(String name);
}
