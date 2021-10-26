package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Horse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'name': ?0}")
    public User findUserByEmailAddress (String emailAddress);

}
