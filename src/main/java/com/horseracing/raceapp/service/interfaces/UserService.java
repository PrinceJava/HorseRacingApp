package com.horseracing.raceapp.service.interfaces;

import com.horseracing.raceapp.model.Horse;
import com.horseracing.raceapp.model.User;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;

import java.util.List;

public interface UserService {

public List<User> listUsers();

public User findUserByEmail(String email);

    User findUserByEmailAddress(String email);

}
