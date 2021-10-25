package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.service.interfaces.JockeyService;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jockey")
public class JockeyController {

    @Autowired
    JockeyService jockeyService;


    @GetMapping("/list")
    public List<Jockey> listJockies(){
        return jockeyService.listJockies();
    }


    @PostMapping("/add")
    public Jockey saveJockey(@RequestBody Jockey jockey){
        return jockeyService.saveJockey(jockey);
    }
}
