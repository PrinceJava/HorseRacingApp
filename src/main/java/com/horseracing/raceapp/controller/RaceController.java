package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race")
public class RaceController {

    @Autowired
    RaceService raceService;


}
