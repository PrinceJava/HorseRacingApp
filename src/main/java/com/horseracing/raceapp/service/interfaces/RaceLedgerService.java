package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.forms.RaceForm;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RaceLedgerService {


    public List<RaceLedger> getRaces();
    public RaceLedger getRace(String raceId);
    public RaceLedger addEntry(RaceLedger raceLedger);
    public RaceLedger updateEntry(String raceId, RaceLedger raceLedgerObject);
    public void deleteEntry(String id);
    public void createEntry(String trackName, List<Map.Entry<Horse, Jockey>> results);
    public List<String> getRecord(String horseName);

}
