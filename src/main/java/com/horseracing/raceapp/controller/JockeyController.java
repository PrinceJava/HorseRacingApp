package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.forms.DeleteJockeyForm;
import com.horseracing.raceapp.service.interfaces.JockeyService;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/jockey")
public class JockeyController {

    @Autowired
    JockeyService jockeyService;


    @GetMapping("/list")
    public ResponseEntity<List<Jockey>> listJockeys(){
        return ResponseEntity.ok().body(jockeyService.listJockeys());
    }

    @GetMapping("/{jockeyName}")
    public ResponseEntity<Jockey> getJockey(@PathVariable(value = "jockeyName") String jockeyName){
        return ResponseEntity.ok().body(jockeyService.getJockey(jockeyName));
    }

    @PostMapping("/add")
    public ResponseEntity<Jockey> saveJockey(@RequestBody Jockey jockey){
        URI uri = URI.create("/com.horseracingapp.raceapp/jockeycontroller/saveJockey/");
        return ResponseEntity.created(uri).body(jockeyService.saveJockey(jockey));
    }

    @PutMapping("/update/{jockeyName}")
    public ResponseEntity<Jockey> updateJockey(@RequestBody Jockey jockey,
                               @PathVariable(value = "jockeyName") String jockeyName){
        URI uri = URI.create("/com.horseracingapp.raceapp/jockeycontroller/updateJockey/");
        return ResponseEntity.created(uri).body(jockeyService.updateJockey(jockey, jockeyName));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteJockey(@RequestBody DeleteJockeyForm form){
        jockeyService.deleteJockey(form.getJockeyName());
        return ResponseEntity.ok().build();
    }
}
