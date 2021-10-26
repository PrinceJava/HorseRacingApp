package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.Track;
import com.horseracing.raceapp.model.Track;

import java.util.List;

public interface TrackService {


    public List<Track> listTracks();
    public Track getTrack(String trackName);
    public Track saveTrack(Track track);
    public Track updateTrack(Track track, String trackName);
    public void deleteTrack(String trackName);

}
