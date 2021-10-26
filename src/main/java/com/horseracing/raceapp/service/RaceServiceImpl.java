package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.*;
import com.horseracing.raceapp.model.forms.AddHorseToStableForm;
import com.horseracing.raceapp.model.forms.RaceForm;
import com.horseracing.raceapp.repository.HorseRepository;
import com.horseracing.raceapp.repository.JockeyRepository;
import com.horseracing.raceapp.repository.StableRepository;
import com.horseracing.raceapp.repository.TrackRepository;
import com.horseracing.raceapp.security.MyUserDetails;
import com.horseracing.raceapp.service.interfaces.HorseService;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {



    @Autowired
    HorseRepository horseRepository;

    @Autowired
    StableRepository stableRepository;

    @Autowired
    JockeyRepository jockeyRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    RaceLedgerService raceLedgerService;


    @Override
    public void startRace(RaceForm form) {
        System.out.println("Calling CategoryService createCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        // TODO logic of taking horse and Jockey input, as well as Track input
        Random random = new Random();
        Hashtable<Horse, Jockey> racer = new Hashtable<>();
        try {
            Horse selectedHorse = horseRepository.findHorseByName(form.getHorseName());
            Jockey selectedJockey = jockeyRepository.findJockeyByName(form.getJockeyName());
            Track selectedTrack = trackRepository.findTrackByName(form.getTrackName());
                List<Horse> horses = horseRepository.findAll();
                horses.removeIf(p -> p.getName().equals(selectedHorse.getName()));
                List<Jockey> jockeys = jockeyRepository.findAll();
                jockeys.removeIf(p -> p.getName().equals(selectedJockey.getName()));
                racer.put(selectedHorse,selectedJockey);

                for(int i = 0; i < 8; i++){
                    int randomHorseIndex = random.nextInt(horses.size());
                    int randomJockeyIndex = random.nextInt(jockeys.size());
                    racer.put(horses.get(randomHorseIndex), jockeys.get(randomJockeyIndex));
                    horses.remove(randomHorseIndex);
                    jockeys.remove(randomJockeyIndex);
                }
            Set<Map.Entry<Horse, Jockey>> entrySet
                    = racer.entrySet();

            for (Map.Entry<Horse, Jockey> entry : entrySet) {
                System.out.println("Key: " + entry.getKey().getName() + " and Value: " + entry.getValue().getName());

                //TODO logic to update
                // if selected.track == horse.favorite +1
                // if seleted.track == worst -1
                // logic for stamina difference from horse stamina and track distance - 1 for every 200 difference
                // jockey skill level +1 for everyone over 5 and -1 for everything under 5
                // random number to adjust 5 percent
                if(entry.getKey().getFavCondition().equalsIgnoreCase(selectedTrack.getCondition())){
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed())+5));
                } else if (entry.getKey().getWorstCondition().equalsIgnoreCase(selectedTrack.getCondition())){
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed())-5));
                }

                int diff = (Math.abs(Integer.parseInt(entry.getKey().getStamina())-Integer.parseInt(selectedTrack.getLength())));
                if(diff >= 200 && diff < 400){
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed())-1));
                } else if(diff >= 400 && diff < 600) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 2));
                } else if(diff >= 600 && diff < 800){
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed())-4));
                } else if(diff >= 800) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 8));
                }

                switch(Integer.parseInt(entry.getValue().getSkillLevel())){
                    case 10:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 4));
                        break;
                    case 7: case 8: case 9:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 2));
                        break;
                    case 6:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 1));
                        break;
                    case 4:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 1));
                        break;
                    case 2: case 3:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 2));
                        break;
                    case 1:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 4));
                        break;
                    default:
                        break;
                }

                double min = -0.05;
                double max = 0.05;
                double randomMultiplayer = random.nextDouble()*(max - min +0.01)+min;
                entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - (Integer.parseInt(entry.getKey().getSpeed()) * randomMultiplayer)));

                List<Map.Entry<Horse, Jockey>> results = racer.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(Horse::getSpeed).reversed())).collect(Collectors.toList());/*.forEach(System.out::println);*/

                for (Map.Entry<Horse, Jockey> result : results) {
                    System.out.println("Horse: " + result.getKey().getName() + " final Speed: " + result.getKey().getSpeed() + " and Jockey: " + result.getValue().getName());
                }
                RaceLedger newRace = new RaceLedger(selectedTrack.getName(),results);
                raceLedgerService.addEntry(newRace);
            }

        }catch(Exception e){
            throw new InformationNotFoundException("Something went wrong, please try again");
        }
    }
}
