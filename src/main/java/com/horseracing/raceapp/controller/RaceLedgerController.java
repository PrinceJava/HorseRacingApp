package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.forms.RaceForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
 * HORSE RACING APP
 * ---- RACE LEDGER CONTROLLER ------
 *   Race Ledger - Record NoSQL Document that houses all previous records
 *      Takes in RaceForm and send variables to the RaceServiceImpl Service startRace
 *
 * */

@RestController
@RequestMapping("/api/")
public class RaceLedgerController {

    @Autowired
    HorseService horseService;

    @Autowired
    RaceLedgerService raceLedgerService;

    @Autowired
    RaceService raceService;


    @GetMapping("/allraces")
    public List<RaceLedger> getRaces() {
        return raceLedgerService.getRaces();
    }

    /**
     * This endpoint will start the race
     * @param form - intake body for race, including - horseName, jockeyName, trackName
     * @return response Entity 201 for a race was created
     */
    @GetMapping("/race")
    public ResponseEntity<?> startRace(@RequestBody RaceForm form) {
        System.out.println("Starting Race");
        raceService.startRace(form.getHorseName(), form.getJockeyName(), form.getTrackName());
        URI uri = URI.create("/com.horseracing.raceapp.controller.raceledgercontroller");
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{raceId}")
    public RaceLedger getRace(@PathVariable(value = "raceId") String raceId) {
        return raceLedgerService.getRace(raceId);
    }

    @DeleteMapping("/delete/{raceId}")
    public void deleteRaceId(@PathVariable(value = "raceId") String raceId){
        raceLedgerService.deleteEntry(raceId);
    }

/*    // Should updating the race be an option?*/
    @PutMapping("/updaterace/{raceId}")
    public RaceLedger updateEntry(@RequestBody RaceLedger raceLedgerObject,
                                     @PathVariable(value = "raceId") String raceId){
        return raceLedgerService.updateEntry(raceId, raceLedgerObject);
    }

    @PostMapping("/add")
    public RaceLedger addEntry(@RequestBody RaceLedger raceLedgerObject){
        return raceLedgerService.addEntry(raceLedgerObject);
    }


    @GetMapping("{horseName}/record")
    public List<String> getRecord(@PathVariable(value = "horseName") String horseName){
        return raceLedgerService.getRecord(horseName);
    }
}