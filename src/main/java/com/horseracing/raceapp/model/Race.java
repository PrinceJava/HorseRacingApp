package com.horseracing.raceapp.model;


import lombok.Data;

import java.util.List;

@Data
public class Race {
    //RACE WILL HAVE AN ID in constructor
    private String id;

    private String track_id;

    private List<Horse> horses;



}
