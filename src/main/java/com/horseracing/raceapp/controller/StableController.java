package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.forms.DeleteStableForm;
import com.horseracing.raceapp.service.interfaces.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/stable")
public class StableController {

    @Autowired
    StableService stableService;


    @GetMapping("/list")
    public ResponseEntity<List<Stable>> listStables(){
        return ResponseEntity.ok().body(stableService.listStables());
    }

    @GetMapping("/{stableName}")
    public ResponseEntity<Stable> getStable(@PathVariable(value = "stableName") String stableName){
        return ResponseEntity.ok().body(stableService.getStable(stableName));
    }
    @GetMapping("/{stableName}/horses")
    public ResponseEntity<List<Horse>> listHorses(@PathVariable(value = "stableName") String stableName){
        return ResponseEntity.ok().body(stableService.listHorses(stableName));
    };

    @PostMapping("/add")
    public ResponseEntity<Stable> saveStable(@RequestBody Stable stable){
        URI uri = URI.create("/com.horseracingapp.raceapp/stablecontroller/saveStable/");
        return ResponseEntity.created(uri).body(stableService.saveStable(stable));
    }

    @PutMapping("/update/{stableName}")
    public ResponseEntity<Stable> updateStable(@RequestBody Stable stable,
                               @PathVariable(value = "stableName")String stableName){
        return ResponseEntity.accepted().body(stableService.updateStable(stableName, stable));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStable(@RequestBody DeleteStableForm form){
        stableService.deleteStable(form.getStableName());
        return ResponseEntity.ok().build();
    }
}
