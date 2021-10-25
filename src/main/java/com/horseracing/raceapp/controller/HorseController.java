package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horse")
public class HorseController {

    @Autowired
    HorseService horseService;


    @GetMapping("/list")
    public List<Horse> listHorses(){
        return horseService.listHorses();
    }

    @PostMapping("/add")
    public Horse createHorse(@RequestBody Horse horse){
        return horseService.createHorse(horse);
    }

    @PostMapping("/addStable")
    public Horse addHorseToStable(@RequestBody AddHorseToStableForm form){
        return horseService.addHorseToStable(form);
    }

}
