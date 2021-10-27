package com.horseracing.raceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document @Data @NoArgsConstructor @AllArgsConstructor
public class RaceLedger {

    @Id @Indexed
    private String id;

    private String trackName;

    private LocalDateTime date;

    private List<Result> results;

}
