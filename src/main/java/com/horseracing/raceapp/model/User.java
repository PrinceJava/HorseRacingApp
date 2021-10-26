package com.horseracing.raceapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document @NoArgsConstructor
@Data
public class User {

    @Id
    private String id;

    @Indexed (unique = true, sparse = true)
    private String userName;

    private String emailAddress;

    private String password;

}
