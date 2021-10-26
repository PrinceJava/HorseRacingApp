package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.Track;
import com.horseracing.raceapp.model.forms.DeleteStableForm;
import com.horseracing.raceapp.model.forms.DeleteTrackForm;
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

    @GetMapping("/{trackName}")
    public Track getTrack(@PathVariable(value = "trackName") String trackName){
        return trackService.getTrack(trackName);
    }

    @PostMapping("/add")
    public Track saveTrack(@RequestBody Track track){
        return trackService.saveTrack(track);
    }

    @PutMapping("/update/{trackName}")
    public Track updateTrack(@RequestBody Track track,
                               @PathVariable(value = "trackName")String trackName){
        return trackService.updateTrack(track, trackName);
    }

    @DeleteMapping("/delete")
    public void deleteTrack(@RequestBody DeleteTrackForm form){
        trackService.deleteTrack(form.getTrackName());
    }
}
