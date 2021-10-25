package com.horseracing.raceapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Data @NoArgsConstructor
public class Jockey {
    private String id;
    @Indexed(unique = true, sparse = true)
    private String name;
    private String height;
    private String weight;
    private String skillLevel;
}
