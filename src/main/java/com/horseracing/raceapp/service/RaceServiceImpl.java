package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.*;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.model.forms.RaceForm;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.JockeyRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.repository.TrackRepository;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RaceServiceImpl implements RaceService {



    @Autowired
    HorseRepository horseRepository;

    @Autowired
    StableRepository stableRepository;

    @Autowired
    JockeyRepository jockeyRepository;

    @Autowired
    TrackRepository trackRepository;


    @Override
    public Race startRace(RaceForm form) {

        // TODO logic of taking horse and Jockey input, as well as Track input
        Hashtable<Horse, Jockey> racer = new Hashtable<>();
        try {
            Horse selectedHorse = horseRepository.findHorseByName(form.getHorseName());
            Jockey selectedJockey = jockeyRepository.findJockeyByName(form.getJockeyName());
            Track selectedTrack = trackRepository.findTrackByName(form.getTrackName());
            racer.put(selectedHorse,selectedJockey);
            return null;
        }catch(Exception e){
            throw new InformationNotFoundException("Something went wrong, please try again");
        }
    }
}
