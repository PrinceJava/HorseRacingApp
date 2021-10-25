package com.horseracing.raceapp.service;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StableServiceImpl implements StableService {

    @Autowired
    StableRepository stableRepository;

    @Autowired
    HorseRepository horseRepository;

    @Override
    public List<Stable> listStables() {
        return stableRepository.findAll();
    }

    @Override
    public Stable saveStable(Stable stable) {
        Stable newStable = new Stable();
        newStable.setName(stable.getName());
        return stableRepository.save(newStable);
    }

    @Override
    public Stable updateStable(Stable stable) {
        return null;
    }

    @Override
    public Stable deleteStable(Stable stable) {
        return null;
    }

    @Override
    public List<Horse> listHorses(String stableName) {
        return horseRepository.findHorseByStableName(stableName);
    }
}



