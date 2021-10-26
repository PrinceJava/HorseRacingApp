package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;

import java.util.List;

public interface UserService {


    public List<Horse> listHorses();
    public Horse getHorse(String horseName);
    public Horse createHorse(Horse horse);
    public Horse updateHorse(Horse horse, String horseName);
    public void deleteHorse(String horseName);
    public Horse addHorseToStable(AddHorseToStableForm form);

}
