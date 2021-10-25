package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.Track;
import com.horseracing.raceapp.service.interfaces.StableService;
import com.horseracing.raceapp.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/track")
public class TrackController {

    @Autowired
    TrackService trackService;


    @GetMapping("/list")
    public List<Track> listTracks(){
        return trackService.listTracks();
    }


    @PostMapping("/add")
    public Track saveTrack(@RequestBody Track track){
        return trackService.saveTrack(track);
    }
}
