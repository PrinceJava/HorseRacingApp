package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.repository.UserRepository;
import com.horseracing.raceapp.security.jwt.JWTUtils;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JWTUtils jwtUtils;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {this.userRepository = userRepository;}

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {this.passwordEncoder = passwordEncoder;}

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        @Autowired
        public void setUserDetailsService(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Autowired
        public void setJwtUtils(JWTUtils jwtUtils) {this.jwtUtils = jwtUtils;}

    @Override
    public User createUser(User user) {
        Horse newHorse = new Horse();
        newHorse.setName(horse.getName());
        newHorse.setAge(horse.getAge());
        newHorse.setColor(horse.getColor());
        newHorse.setHeight(horse.getHeight());
        newHorse.setSex(horse.getSex());
        newHorse.setFavCondition(horse.getFavCondition());
        newHorse.setSpeed(horse.getSpeed());
        newHorse.setStamina(horse.getStamina());
        newHorse.setWorstCondition(horse.getWorstCondition());
        newHorse.setWeight(horse.getWeight());
        horseRepository.save(newHorse);
        return newHorse;
    }



    @Override
    public void deleteHorse(String name) {
        System.out.println("Horse Service Delete Horse ====>");
        try{
            Horse horse = horseRepository.findHorseByName(name);
            horseRepository.delete(horse);
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Horse not found");
        }
    }

    @Override
    public Horse getHorse(String horseName) {
       try {
           return horseRepository.findHorseByName(horseName);
       }catch(NoSuchElementException e) {
           throw new InformationNotFoundException("Horse does not exist");
       }
    }

    @Override
    public Horse addHorseToStable(AddHorseToStableForm form) {
        try {
            Stable stable = stableRepository.findByStableName(form.getStableName());
            Horse horse = horseRepository.findHorseByName(form.getHorseName());
            horse.setStable(stable);
            return horseRepository.save(horse);
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Horse and or Stable not found");
        }
    }
}
