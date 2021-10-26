package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationExistsException;
import com.horseracing.raceapp.model.User;
import com.horseracing.raceapp.model.forms.LoginRequest;
import com.horseracing.raceapp.model.forms.LoginResponse;
import com.horseracing.raceapp.repository.UserRepository;
import com.horseracing.raceapp.security.jwt.JWTUtils;
import com.horseracing.raceapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String userName) {
        return null;
    }

    @Override
    public User updateUser(User user, String userName) {
        return null;
    }

    @Override
    public void deleteUser(String userName) {

    }

    @Override
    public User createUser(String userName, String emailAddress, String password) {
        System.out.println("service is calling createUser==>");
        // if user not exists by the email
        // then create the user in the db

        if (!userRepository.existsByEmailAddress(emailAddress)) {

            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setEmailAddress(emailAddress);
            newUser.setPassword(passwordEncoder.encode(password));
            return userRepository.save(newUser);
//            return newUser;
        } else {
            throw new InformationExistsException("user with the email address " +
                    emailAddress + " already exists");
        }
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        System.out.println("service calling loginUser ==>");
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String JWT = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(JWT));
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }
}
