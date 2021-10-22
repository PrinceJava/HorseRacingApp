package com.horseracing.raceapp.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document @Data
public class Stable {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String name;

    @DBRef
    private List<Horse> horses;

}
