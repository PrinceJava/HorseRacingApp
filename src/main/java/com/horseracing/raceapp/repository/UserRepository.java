package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


    // created when we copied Create User
    boolean existsByEmailAddress(String emailAddress);

    User findUserByEmailAddress(String userEmailAddress);
    User findUserByUserName(String username);

}
