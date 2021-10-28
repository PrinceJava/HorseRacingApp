package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/horse")
public class HorseController {

    @Autowired
    HorseService horseService;


    @GetMapping("/list")
    public ResponseEntity<List<Horse>> listHorses(){
        return ResponseEntity.ok().body(horseService.listHorses());
    }

    @PostMapping("/add")
    public ResponseEntity<Horse> createHorse(@RequestBody Horse horse){
        URI uri = URI.create("/com.horseracingapp.raceapp/horsecontroller/createHorse/");
        return ResponseEntity.created(uri).body(horseService.createHorse(horse));
    }

    @PostMapping("/addstable")
    public ResponseEntity<Horse> addHorseToStable(@RequestBody AddHorseToStableForm form){
        URI uri = URI.create("/com.horseracingapp.raceapp/horsecontroller/createHorse/");
        return ResponseEntity.created(uri).body(horseService.addHorseToStable(form));
    }

    @GetMapping("/{horseName}")
    public ResponseEntity<Horse> getHorse (@PathVariable(value = "horseName")String horseName){
        return ResponseEntity.ok().body(horseService.getHorse(horseName));
    }

    @PutMapping("/updatehorse/{horseName}")
    public ResponseEntity<Horse> updateHorse (@PathVariable (value = "horseName") String horseName,
                              @RequestBody Horse horse){
        return ResponseEntity.accepted().body(horseService.updateHorse(horse, horseName));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteHorse(@PathVariable(value = "name") String name){
        horseService.deleteHorse(name);
        return ResponseEntity.ok().build();
    }


}
