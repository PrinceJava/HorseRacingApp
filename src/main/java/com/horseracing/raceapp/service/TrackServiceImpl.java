package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
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
import java.util.NoSuchElementException;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    TrackRepository trackRepository;

    @Override
    public List<Track> listTracks() {
        System.out.println("TRACK SERVICE CALLING listTracks ==>");
        return trackRepository.findAll();
    }

    @Override
    public Track getTrack(String trackName) {
        System.out.println("TRACK SERVICE CALLING getTrack ==>");
        return trackRepository.findTrackByName(trackName);
    }

    @Override
    public Track saveTrack(Track track) {
        System.out.println("TRACK SERVICE CALLING saveTrack ==>");
        Track newTrack = new Track();
        newTrack.setName(track.getName());
        newTrack.setCondition(track.getCondition());
        newTrack.setLength(track.getLength());
        return trackRepository.save(newTrack);
    }

    @Override
    public Track updateTrack(Track track, String trackName) {
        System.out.println("TRACK SERVICE CALLING updateTrack ==>");
        try {
            Track updateTrack = trackRepository.findTrackByName(trackName);
            if (track.getName() != null) {
                updateTrack.setName(track.getName());
            }
            if (track.getCondition() != null) {
                updateTrack.setCondition(track.getCondition());
            }
            if (track.getLength() != null) {
                updateTrack.setLength(track.getLength());
            }
            return trackRepository.save(updateTrack);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Track not found");
        }
    }

    @Override
    public void deleteTrack(String trackName) {
        System.out.println("TRACK SERVICE CALLING deleteTrack ==>");
        try {
            Track track = trackRepository.findTrackByName(trackName);
            trackRepository.delete(track);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Track not found");
        }
    }
}



