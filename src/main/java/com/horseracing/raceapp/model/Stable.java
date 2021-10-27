package com.horseracing.raceapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document @NoArgsConstructor
@Data
public class Stable {

    @Id
    private String id;

    @Indexed
    private String name;

    @DBRef
    @JsonIgnore
    private List<Horse> horses;

}
