package com.horseracing.raceapp.repository;

import com.horseracing.raceapp.model.Jockey;
import com.horseracing.raceapp.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TrackRepository extends MongoRepository<Track, String> {

    @Query("{'name': ?0}")
    public Track findTrackByName(String name);

}
