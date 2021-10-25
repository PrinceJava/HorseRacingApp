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
        newHorse.setStable(horse.getStable());
        newHorse.setStamina(horse.getStamina());
        newHorse.setWorstCondition(horse.getWorstCondition());
        newHorse.setWeight(horse.getWeight());
        horseRepository.save(newHorse);
        return newHorse;
    }

    @Override
    public Horse updateHorse(Horse horse) {
        return null;
    }

    @Override
    public Horse deleteHorse(Horse horse) {
        return null;
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
