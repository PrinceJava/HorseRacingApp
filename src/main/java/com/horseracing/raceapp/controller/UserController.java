package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.User;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.model.forms.LoginRequest;
import com.horseracing.raceapp.model.forms.RaceForm;
import com.horseracing.raceapp.model.forms.RegisterForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import com.horseracing.raceapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RaceService raceService;

    @GetMapping("/race")
    public void startRace(RaceForm form){
        System.out.println("Starting Race");
        raceService.startRace(form);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("controller is calling loginUser ===>");
        return userService.loginUser(loginRequest);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody RegisterForm registerForm) {
        System.out.println("controller is calling create user ===>");
        return userService.createUser(registerForm.getUserName(),
                registerForm.getEmailAddress(), registerForm.getPassword());
    }

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

