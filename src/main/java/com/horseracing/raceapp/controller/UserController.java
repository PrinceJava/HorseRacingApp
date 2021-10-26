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

//    @GetMapping("/list")
//    public List<User> listUsers() {
////        return userService.listUsers();
//    }
//
//    @PostMapping("/signup")
//    public User signUp(@RequestBody SignUpForm signUpForm) {
//        return userService.createUser(User);
//    }

//    @GetMapping ("/user/{email}")
//    public User getUserByEmail(@PathVariable String email){
//        return userService.findUserByEmail(email);
//    }


}

