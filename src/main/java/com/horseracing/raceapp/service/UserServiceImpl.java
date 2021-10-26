package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.User;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.repository.UserRepository;
import com.horseracing.raceapp.security.jwt.JWTUtils;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JWTUtils jwtUtils;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByEmailAddress(String email) {
        return null;
    }
}

