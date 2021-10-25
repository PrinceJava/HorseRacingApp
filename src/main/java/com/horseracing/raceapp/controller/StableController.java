package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stable")
public class StableController {

    @Autowired
    StableService stableService;


    @GetMapping("/list")
    public List<Stable> listStables(){
        return stableService.listStables();
    }

    @GetMapping("/{stableName}/horses")
    public List<Horse> listHorses(@PathVariable(value = "stableName") String stableName){
        return stableService.listHorses(stableName);
    };

    @PostMapping("/add")
    public Stable saveStable(@RequestBody Stable stable){
        return stableService.saveStable(stable);
    }
}
