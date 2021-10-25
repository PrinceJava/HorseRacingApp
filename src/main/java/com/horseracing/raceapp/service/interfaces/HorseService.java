package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HorseService {


    public List<Horse> listHorses();
    public Horse createHorse(Horse horse);
    public Horse updateHorse(Horse horse);
    public Horse deleteHorse(Horse horse);
    public Horse addHorseToStable(AddHorseToStableForm form);

}
