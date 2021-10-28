package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StableServiceImpl implements StableService {

    @Autowired
    StableRepository stableRepository;

    @Autowired
    HorseRepository horseRepository;

    @Override
    public List<Stable> listStables() {
        System.out.println("STABLE SERVICE CALLING listStables ==>");
        return stableRepository.findAll();
    }

    @Override
    public Stable getStable(String stableName) {
        System.out.println("STABLE SERVICE CALLING getStable ==>");
        try{
            Stable stable = stableRepository.findByStableName(stableName);
            return stable;
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Stable not found");
        }
    }

    @Override
    public Stable saveStable(Stable stable) {
        System.out.println("STABLE SERVICE CALLING saveStable ==>");
        Stable newStable = new Stable();
        newStable.setName(stable.getName());
        return stableRepository.save(newStable);
    }

    @Override
    public Stable updateStable(String stableName, Stable stable) {
        System.out.println("STABLE SERVICE CALLING updateStable ==>");
        try{
            Stable updatedStable = stableRepository.findByStableName(stableName);
            if(stable.getName() != null){
                updatedStable.setName(stable.getName());
            }
            return stableRepository.save(updatedStable);
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Stable not found");
        }
    }

    @Override
    public void deleteStable(String stableName) {
        System.out.println("STABLE SERVICE CALLING deleteStable ==>");
        try{
            Stable stable = stableRepository.findByStableName(stableName);
            stableRepository.delete(stable);
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Stable not found");
        }
    }

    @Override
    public List<Horse> listHorses(String stableName) {
        System.out.println("STABLE SERVICE CALLING listHorses ==>");
        return horseRepository.findHorseByStableName(stableName);
    }
}



