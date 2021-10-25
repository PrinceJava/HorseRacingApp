package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/addstable")
    public Horse addHorseToStable(@RequestBody AddHorseToStableForm form){
        return horseService.addHorseToStable(form);
    }

    @GetMapping("/{horseName}")
    public Horse getHorse (@PathVariable(value = "horseName")String horseName){
        return horseService.getHorse(horseName);
    }

    @PutMapping("/updatehorse/{horseName}")
    public Horse updateHorse (@PathVariable (value = "horseName") String horseName,
                              @RequestBody Horse horse){
        return horseService.updateHorse(horse, horseName);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteHorse(@PathVariable(value = "name") String name){
        horseService.deleteHorse(name);
    }


}
