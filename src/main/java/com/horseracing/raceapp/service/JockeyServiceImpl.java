package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
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
import java.util.NoSuchElementException;

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
        System.out.println("JOCKEY SERVICE CALLING listJockeys ==>");
        return jockeyRepository.findAll();
    }

    @Override
    public Jockey getJockey(String jockeyName) {
        System.out.println("JOCKEY SERVICE CALLING getJockey ==>");
        try{
         Jockey jockey = jockeyRepository.findJockeyByName(jockeyName);
         return jockey;
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Jockey not found");
        }
    }

    @Override
    public Jockey saveJockey(Jockey jockey) {
        System.out.println("JOCKEY SERVICE CALLING saveJockey ==>");
        Jockey newJockey = new Jockey();
        newJockey.setName(jockey.getName());
        newJockey.setHeight(jockey.getHeight());
        newJockey.setWeight(jockey.getWeight());
        newJockey.setSkillLevel(jockey.getSkillLevel());
        return jockeyRepository.save(newJockey);
    }

    @Override
    public Jockey updateJockey(Jockey jockey, String jockeyName) {
        System.out.println("JOCKEY SERVICE CALLING updateJockey ==>");
        try{
            Jockey updatedJockey = jockeyRepository.findJockeyByName(jockeyName);
            if(jockey.getName() != null){
                updatedJockey.setName(jockey.getName());
            }
            if(jockey.getHeight() != null){
                updatedJockey.setHeight(jockey.getHeight());
            }
            if(jockey.getWeight() != null){
                updatedJockey.setWeight(jockey.getWeight());
            }
            if(jockey.getSkillLevel() != null){
                updatedJockey.setSkillLevel(jockey.getSkillLevel());
            }
            return jockeyRepository.save(updatedJockey);
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Jockey not found");
        }
    }

    @Override
    public void deleteJockey(String jockeyName) {
        System.out.println("JOCKEY SERVICE CALLING deleteJockeys ==>");
        try {
            Jockey jockey = jockeyRepository.findJockeyByName(jockeyName);
            jockeyRepository.delete(jockey);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Jockey not found");
        }
    }
}



