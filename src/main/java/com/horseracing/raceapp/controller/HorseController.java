package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.service.interfaces.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Horse createHorse(Horse horse){
        return horseService.createHorse(horse);
    }


}
