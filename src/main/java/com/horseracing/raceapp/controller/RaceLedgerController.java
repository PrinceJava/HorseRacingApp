package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.RaceLedger;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RaceLedgerController {

    @Autowired
    HorseService horseService;

    @Autowired
    RaceLedgerService raceLedgerService;


    //TODO CRUD for RACE LEDGER

    @GetMapping("/allraces")
    public List<RaceLedger> getRaces(){
        return raceLedgerService.getRaces();
    }

}
