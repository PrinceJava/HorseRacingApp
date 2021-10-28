package com.horseracing.raceapp.service;

import com.horseracing.raceapp.exception.InformationNotFoundException;
import com.horseracing.raceapp.model.*;
import com.horseracing.raceapp.repository.*;
import com.horseracing.raceapp.service.interfaces.RaceLedgerService;
import com.horseracing.raceapp.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/*
 * HORSE RACING APP
 * ---- RACE SERVICE IMPL ------
 *   Race - Initialize starting a race, stating with Horse, Jockey, and Track
 *   Race Ledger - Record NoSQL Document that houses all previous records
 *
 *   Start Race will take input, generate a full list of racers
 *      Adjust each speed based off Horse, Track, and Jockey Variables
 *      Create a Set of sorted Entries descending based off speed
 *      Send that to Race Ledger Create Entry to record results
 * */

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


    /**
     * Horse Race Logic included here.  Steps included
     * 1. Take Selected input and assign each element to selected Variable
     *      a. selectedHorse - Horse based off Horse Name
     *      b. selectedJockey - Jockey based off Jockey Name
     *      c. selectedTrack - Track based off Track Name
     * 2. Create Random object random for Random Number Generator to be used in:
     *      a. Generating Random Jockey and Horse pairs
     *      b. Generating element of Chance variable for speed
     * 3. Create Hashtable Racer that will consist of all Horse - Jockey pairs in the race
     * 4. Create a list of all horses and jockeys, and remove the horse and jockey if they
     *      are the same as selected Jockey and horse, to remove duplicates
     * 5. Do a for loop through the size of the race (STATIC IN THIS APP TO 8) + selected = 9 total
     * 6. Add the horse - jockey combo to the racer HashSet
     * 7. Remove each horse/jockey from respective lists to prevent the same horse being selected twice.
     * @param horseName - horseName to be used to find the Horse Object
     * @param jockeyName - jockeyName to be used to find the Jockey Object
     * @param trackName - Track Name to be used to find the Track Object
     */
    @Override
    public void startRace(String horseName, String jockeyName, String trackName) {
        System.out.println("Calling CategoryService createCategory ==>");
        // TODO logic of taking horse and Jockey input, as well as Track input
        Random random = new Random();
        Hashtable<Horse, Jockey> racer = new Hashtable<>();
        try {
            Horse selectedHorse = horseRepository.findHorseByName(horseName);
            Jockey selectedJockey = jockeyRepository.findJockeyByName(jockeyName);
            Track selectedTrack = trackRepository.findTrackByName(trackName);
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
    /*
    * 8. Create a Set - entrySet from HashSet Racer.entrySet() to loop through each pair
    * 9. Start with Conditions, if Either Favorite or Worst Condition match track.getCondition, + or - 5
    * 10. Create the difference of horse stamina and track distance - Math.abs to ensure they are positive numbers
    *   a. use If - Else-If to determine is diff is in selected range, if it is, subtract from total speed.
    * 11. Create a Switch statement based off Jockey Skill Level, and subtract from Horse speed based off value
    * 12.
    * */
            Set<Map.Entry<Horse, Jockey>> entrySet
                    = racer.entrySet();

            for (Map.Entry<Horse, Jockey> entry : entrySet) {

                //TODO logic to update
                // if selected.track == horse.favorite +1
                // if seleted.track == worst -1
                // logic for stamina difference from horse stamina and track distance - 1 for every 200 difference
                // jockey skill level +1 for everyone over 5 and -1 for everything under 5
                // random number to adjust 5 percent
                if (entry.getKey().getFavCondition().equalsIgnoreCase(selectedTrack.getCondition())) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 5));
                } else if (entry.getKey().getWorstCondition().equalsIgnoreCase(selectedTrack.getCondition())) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 5));
                }

                int diff = (Math.abs(Integer.parseInt(entry.getKey().getStamina()) - Integer.parseInt(selectedTrack.getLength())));
                if (diff >= 200 && diff < 400) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 1));
                } else if (diff >= 400 && diff < 600) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 2));
                } else if (diff >= 600 && diff < 800) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 4));
                } else if (diff >= 800) {
                    entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 8));
                }

                switch (Integer.parseInt(entry.getValue().getSkillLevel())) {
                    case 10:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 4));
                        break;
                    case 7:
                    case 8:
                    case 9:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 2));
                        break;
                    case 6:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) + 1));
                        break;
                    case 4:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 1));
                        break;
                    case 2:
                    case 3:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 2));
                        break;
                    case 1:
                        entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - 4));
                        break;
                    default:
                        break;
                }
    /*
    * 13. for Each Horse - Jockey entry in entrySet, generate a random number between -5% and +5% and change the value
    *       of speed by that amount (horse speed - horse speed * randomMultiplier) to generate final speed
    * 14. Each change in Set will alter the HashSet racer, so take racer and convert it to List<Map.Entry<Horse,Jockey>
            racer.entrySet() -> stream() -> sorted(Map.Entry Compare Horse::getSpeed()) -> reversed() -> Collect to List
    * 15. Print each entry of sorted List to console showing horse, speed, and jockey to verify expected results
    * 16. Send Track Name and Results List to raceLedgerService.createEntry() to add race to ledger.
    * */
                double min = -0.05;
                double max = 0.05;
                double randomMultiplayer = random.nextDouble() * (max - min + 0.01) + min;
                entry.getKey().setSpeed(String.valueOf(Integer.parseInt(entry.getKey().getSpeed()) - (Integer.parseInt(entry.getKey().getSpeed()) * randomMultiplayer)));
            }
            List<Map.Entry<Horse, Jockey>> results = racer.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(Horse::getSpeed).reversed())).collect(Collectors.toList());/*.forEach(System.out::println);*/

            for (Map.Entry<Horse, Jockey> result : results) {
                System.out.println("Horse: " + result.getKey().getName() + " final Speed: " + result.getKey().getSpeed() + " and Jockey: " + result.getValue().getName());
            }
            raceLedgerService.createEntry(selectedTrack.getName(), results);

        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("Something went wrong, please try again");
        }
    }
}
