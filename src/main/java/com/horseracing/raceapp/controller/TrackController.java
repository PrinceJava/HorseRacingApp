package com.horseracing.raceapp.controller;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.Track;
import com.horseracing.raceapp.model.forms.DeleteStableForm;
import com.horseracing.raceapp.model.forms.DeleteTrackForm;
import com.horseracing.raceapp.service.interfaces.StableService;
import com.horseracing.raceapp.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/track")
public class TrackController {

    @Autowired
    TrackService trackService;


    @GetMapping("/list")
    public ResponseEntity<List<Track>> listTracks(){
        return ResponseEntity.ok().body(trackService.listTracks());
    }

    @GetMapping("/{trackName}")
    public ResponseEntity<Track> getTrack(@PathVariable(value = "trackName") String trackName){
        return ResponseEntity.ok().body(trackService.getTrack(trackName));
    }

    @PostMapping("/add")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track){
        URI uri = URI.create("/com.horseracingapp.raceapp/trackcontroller/saveTrack/");
        return ResponseEntity.created(uri).body(trackService.saveTrack(track));
    }

    @PutMapping("/update/{trackName}")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track,
                               @PathVariable(value = "trackName")String trackName){
        return ResponseEntity.accepted().body(trackService.updateTrack(track, trackName));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrack(@RequestBody DeleteTrackForm form){
        trackService.deleteTrack(form.getTrackName());
        return ResponseEntity.ok().build();
    }
}
