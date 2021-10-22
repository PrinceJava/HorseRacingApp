package com.horseracing.raceapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Data
public class Horse {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String name;

    private String sex;

    private String weight;

    private String height;

    private String age;

    private String speed;

    private String color;

    private String stamina;

    private String favCondition;

    private String worstCondition;

    private Stable stable;
}
