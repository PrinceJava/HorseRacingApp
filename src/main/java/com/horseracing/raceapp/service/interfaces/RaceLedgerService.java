package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.forms.RaceForm;

import java.util.List;
import java.util.Map;

public interface RaceLedgerService {


    public List<RaceLedger> getRaces();
    public RaceLedger getRace(String id);
    public RaceLedger addEntry(RaceLedger raceLedger);
    public RaceLedger updateEntry(String id);
    public RaceLedger deleteEntry(String id);
    public RaceLedger createEntry(String trackName,List<Map.Entry<Horse, Jockey>> results);

}
