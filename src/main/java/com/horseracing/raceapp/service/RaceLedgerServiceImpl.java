package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.Track;
import com.horseracing.raceapp.model.forms.RaceForm;
import com.horseracing.raceapp.repository.*;
import com.horseracing.raceapp.security.MyUserDetails;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RaceLedgerServiceImpl implements RaceLedgerService {



    @Autowired
    HorseRepository horseRepository;

    @Autowired
    StableRepository stableRepository;

    @Autowired
    JockeyRepository jockeyRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    RaceLedgerRepository raceLedgerRepository;


    @Override
    public List<RaceLedger> getRaces() {
        return raceLedgerRepository.findAll();
    }

    @Override
    public RaceLedger getRace(String id) {
        return null;
    }

    @Override
    public RaceLedger addEntry(RaceLedger raceLedger) {
        System.out.println("Calling CategoryService createCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return raceLedgerRepository.save(raceLedger);
    }

    @Override
    public RaceLedger updateEntry(String id) {
        return null;
    }

    @Override
    public RaceLedger deleteEntry(String id) {
        return null;
    }
}
