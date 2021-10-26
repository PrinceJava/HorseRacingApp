package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.User;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpForm signUpForm){
        return userService.createUser(User);
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
