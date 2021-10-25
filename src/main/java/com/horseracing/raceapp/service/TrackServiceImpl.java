package com.horseracing.raceapp.service;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Stable;
import com.horseracing.raceapp.model.Track;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.repository.TrackRepository;
import com.horseracing.raceapp.service.interfaces.StableService;
import com.horseracing.raceapp.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    TrackRepository trackRepository;

    @Override
    public List<Track> listTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track saveTrack(Track track) {
        Track newTrack = new Track();
        newTrack.setName(track.getName());
        newTrack.setCondition(track.getCondition());
        newTrack.setLength(track.getLength());
        return trackRepository.save(newTrack);
    }

    @Override
    public Track updateTrack(Track track) {
        return null;
    }

    @Override
    public Track deleteTrack(Track track) {
        return null;
    }
}



