package com.horseracing.raceapp.model.forms;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
