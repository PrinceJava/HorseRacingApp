package com.horseracing.raceapp.service;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.JockeyRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.service.interfaces.JockeyService;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JockeyServiceImpl implements JockeyService {

    @Autowired
    StableRepository stableRepository;

    @Autowired
    HorseRepository horseRepository;

    @Autowired
    JockeyRepository jockeyRepository;


    @Override
    public List<Jockey> listJockeys() {
        return jockeyRepository.findAll();
    }

    @Override
    public Jockey saveJockey(Jockey jockey) {
        // potential error on value bounds.  come back to this later
        // thinking of weight, height, and skill limits parseInt values
        Jockey newJockey = new Jockey();
        newJockey.setName(jockey.getName());
        newJockey.setHeight(jockey.getHeight());
        newJockey.setWeight(jockey.getWeight());
        newJockey.setSkillLevel(jockey.getSkillLevel());
        return jockeyRepository.save(newJockey);
    }

    @Override
    public Jockey updateJockey(Jockey jockey) {
        return null;
    }

    @Override
    public Jockey deleteJockey(Jockey jockey) {
        return null;
    }
}



