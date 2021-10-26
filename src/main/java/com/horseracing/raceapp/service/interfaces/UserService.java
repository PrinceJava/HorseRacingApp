package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.User;
import com.horseracing.raceapp.model.forms.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public User addUser(User user);
    public List<User> getUsers();
    public User getUser(String userName);
    public User updateUser(User user, String userName);
    public void deleteUser(String userName);
    public User createUser(String userName, String emailAddress, String password);
    public ResponseEntity<?> loginUser (LoginRequest loginRequest);
    public User findUserByEmailAddress(String email);
}
