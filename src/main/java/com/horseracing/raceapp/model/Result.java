package com.horseracing.raceapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {

    private String place;
    private String horse;
    private String finalSpeed;
    private String jockey;
}
