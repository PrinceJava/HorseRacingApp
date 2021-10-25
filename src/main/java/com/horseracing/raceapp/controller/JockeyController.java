package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.forms.DeleteJockeyForm;
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
    public List<Jockey> listJockeys(){
        return jockeyService.listJockeys();
    }

    @GetMapping("/{jockeyName}")
    public Jockey getJockey(@PathVariable(value = "jockeyName") String jockeyName){
        return jockeyService.getJockey(jockeyName);
    }

    @PostMapping("/add")
    public Jockey saveJockey(@RequestBody Jockey jockey){
        return jockeyService.saveJockey(jockey);
    }

    @PutMapping("/update/{jockeyName}")
    public Jockey updateJockey(@RequestBody Jockey jockey,
                               @PathVariable(value = "jockeyName") String jockeyName){
        return jockeyService.updateJockey(jockey, jockeyName);
    }

    @DeleteMapping("/delete")
    public void deleteJockey(@RequestBody DeleteJockeyForm form){
        jockeyService.deleteJockey(form.getJockeyName());
    }
}
