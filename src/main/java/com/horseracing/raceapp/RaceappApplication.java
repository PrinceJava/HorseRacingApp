package com.horseracing.raceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* HORSE RACING APP
* ---- MAIN ------
* Horse Racing app will have several CRUD endpoints for the following models
*   Horses - create, update, delete, and get all or single horses
*   Stables - create, update, delete, and get all horses in a Stable
*   Jockeys - create, update, delete, and get all or single Jockey
*   Race - Initialize starting a race, stating with Horse, Jockey, and Track
*   Race Ledger - Record NoSQL Document that houses all previous records
*
*   Each Race will be determined by a horses final speed.
*   Speed will be calculated by several variables and notated in RaceServiceImpl
* */

@SpringBootApplication
public class RaceappApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaceappApplication.class, args);
    }

}
