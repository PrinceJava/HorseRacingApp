package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Race;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.model.forms.RaceForm;

import java.util.List;

public interface RaceService {


    public void startRace(RaceForm form);

}
