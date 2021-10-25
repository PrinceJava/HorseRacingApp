package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.Stable;

import java.util.List;

public interface JockeyService {


    public List<Jockey> listJockeys();
    public Jockey getJockey(String jockeyName);
    public Jockey saveJockey(Jockey jockey);
    public Jockey updateJockey(Jockey jockey, String jockeyName);
    public void deleteJockey(String jockeyName);

}
