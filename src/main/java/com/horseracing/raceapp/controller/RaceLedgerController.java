package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Race;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.model.forms.RaceForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/")
public class RaceLedgerController {

    @Autowired
    HorseService horseService;

    @Autowired
    RaceLedgerService raceLedgerService;

    @Autowired
    RaceService raceService;


    //TODO CRUD for RACE LEDGER

    @GetMapping("/allraces")
    public List<RaceLedger> getRaces() {
        return raceLedgerService.getRaces();
    }

    @GetMapping("/race")
    public ResponseEntity<?> startRace(@RequestBody RaceForm form) {
        System.out.println("Starting Race");
        raceService.startRace(form.getHorseName(), form.getJockeyName(), form.getTrackName());
        URI uri = URI.create("/com.horseracing.raceapp.controller.raceledgercontroller");
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{raceId}")
    public List<RaceLedger> getRaces(@PathVariable(value = "raceId") String raceId) {
        return raceLedgerService.getRaces();
    }

    @DeleteMapping("/delete/{raceId}")
    public void deleteRaceId(@PathVariable(value = "raceId") String raceId){
        raceLedgerService.deleteEntry(raceId);
    }

    // Should updating the race be an option?
/*    @PutMapping("/updaterace/{raceId}")
    public List<RaceLedger> getRaces(@RequestBody Race race,
                                     @PathVariable(value = "raceId") String raceId){
        return raceLedgerService.updateEntry(raceId).setResults(race);
    }

    @PostMapping("/add")
    public List<RaceLedger> getRaces(@RequestBody Race race){
        return raceLedgerService.addEntry(RaceLedger);
    }*/


    @GetMapping("{horseName}/record")
    public List<String> getRecord(@PathVariable(value = "horseName") String horseName){
        return raceLedgerService.getRecord(horseName);
    }
}