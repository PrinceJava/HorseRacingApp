package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HorseServiceImpl implements HorseService {



    @Autowired
    HorseRepository horseRepository;

    @Autowired
    StableRepository stableRepository;

    @Override
    public List<Horse> listHorses() {
        return horseRepository.findAll();
    }

    @Override
    public Horse createHorse(Horse horse) {
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
    public Horse updateHorse(Horse horse, String horseName) {
        try{
            Horse horse1 = horseRepository.findHorseByName(horseName);
            if(horse.getName() != null) {
                horse1.setName(horse.getName());
            }
            if(horse.getAge() != null) {
                horse1.setAge(horse.getAge());
            }
            if(horse.getColor() != null) {
                horse1.setColor(horse.getColor());
            }
            if(horse.getHeight() != null) {
                horse1.setHeight(horse.getHeight());
            }
            if(horse.getSex() != null) {
                horse1.setSex(horse.getSex());
            }
            if(horse.getFavCondition() != null) {
                horse1.setFavCondition(horse.getFavCondition());
            }
            if(horse.getSpeed() != null) {
                horse1.setSpeed(horse.getSpeed());
            }
            if(horse.getStable() != null) {
                horse1.setStable(horse.getStable());
            }
            if(horse.getStamina() != null) {
                horse1.setStamina(horse.getStamina());
            }
            if(horse.getWorstCondition() != null) {
                horse1.setWorstCondition(horse.getWorstCondition());
            }
            if(horse.getWeight() != null) {
                horse1.setWeight(horse.getWeight());
            }
            return horseRepository.save(horse1);
        }catch(Exception e){
            throw new InformationNotFoundException("Horse name does not exist");
        }

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
