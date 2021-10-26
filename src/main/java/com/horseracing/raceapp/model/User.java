package com.horseracing.raceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Data @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String userName;

    @Indexed(unique = true, sparse = true)
    private String emailAddress;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Horse> horse;

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Jockey> jockey;

}
