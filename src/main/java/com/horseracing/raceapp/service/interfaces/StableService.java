package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;

import java.util.List;

public interface StableService {


    public List<Stable> listStables();
    public Stable saveStable(Stable stable);
    public Stable updateStable(Stable stable);
    public Stable deleteStable(Stable stable);

    public List<Horse> listHorses(String stableName);


}
