package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;

import java.util.List;

public interface StableService {


    public List<Stable> listStables();
    public Stable getStable(String stableName);
    public Stable saveStable(Stable stable);
    public Stable updateStable(String stableName, Stable stable);
    public void deleteStable(String stableName);

    public List<Horse> listHorses(String stableName);


}
