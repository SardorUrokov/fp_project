package com.company;

import java.util.*;

public class FishService {

    public static List<Fish> fishList = new ArrayList<>();
    public static List<Fish> menList = new ArrayList<>();
    public static List<Fish> womenList = new ArrayList<>();
    public static Random random = new Random();
    public static final int aquariumSize = 20; //akvarium maximum 20ta baliqni sig'dira oladi

    public static void start() {
        int men = random.nextInt(8);
        int women = random.nextInt(8);

        if (women == 0) women = 1;
        else if (men == 0) men = 1;

        for (int i = 0; i < men; i++) {
            fishList.add(new Fish(RandomValueUtils.generateRandomName(), RandomValueUtils.generateRandomNumber(1), true, random.nextInt(50)));
        }
        for (int i = 0; i < women; i++) {
            fishList.add(new Fish(RandomValueUtils.generateRandomName(), RandomValueUtils.generateRandomNumber(1), false, random.nextInt(50)));
        }

        setFishList();
        for (int i = 0; i < fishList.size(); ++i) {
            new Thread(fishList.get(i)).start();

            System.out.println((i + 1) + ". " + fishList.get(i));
        }

        System.out.println("\n");
    }

    public static void createGeneration() {

        Fish newFish;
        int tempNum = Math.min(menList.size(), womenList.size());
            for (int i = 0; i < tempNum; i++) {

                Fish womanFish = womenList.get(i);
                Fish manFish = menList.get(i);
                boolean isPossible = isDurable(manFish, womanFish);

                if (isPossible) {

                    newFish = new Fish(RandomValueUtils.generateRandomName(), RandomValueUtils.generateRandomNumber(1), random.nextBoolean(), random.nextInt(50));
                    fishList.add(newFish);

                    if (newFish.isMale()) menList.add(newFish);
                    else womenList.add(newFish);

                    new Thread(newFish).start();

                    System.out.println("\nNEW FISH - -> name: " + newFish.getName()
                            + ", gender: " + newFish.isMale()
                            + ", life_time: " + newFish.getLifeTime()
                            + ", current_location: " + newFish.getCurrentLocation() + "\n"
                            + "Parents - -> ManFish name: " + manFish.getName()
                            + ", WomanFish name: " + womanFish.getName());
                }
            }
//        checkAquarium();
    }

    public static void setFishList() {

        for (Fish thisFish : fishList) {
            if (thisFish.isMale())
                menList.add(thisFish);
            else
                womenList.add(thisFish);
        }

        System.out.println("True Fish: " + menList.size());
        System.out.println("False Fish: " + womenList.size());
    }

    public static boolean isDurable(Fish manFish, Fish womanFish) {

        boolean isAbleBody = womanFish.getRealLifeTime() - womanFish.getLifeTime() >= 4;

        boolean isDurable = manFish.getRealLifeTime() - manFish.getLifeTime() >= 4;

        boolean isSameLocation = (manFish.getCurrentLocation()) == (womanFish.getCurrentLocation());

        return isAbleBody && isDurable && isSameLocation;

    }

    public static String countAliveFish() {
        int men = 0;
        int women = 0;

        for (int i = 0; i < fishList.size(); i++) {
            Fish fish = fishList.get(i);
            if (fish.getLifeTime() > 0) {
                if (fish.isMale())
                    men++;
                else
                    women++;
            }
        }
        int count = men + women;
        return "\nCount of Fish: " + count + ": \nTrue fish: " + men + ", False fish: " + women + "\n";
    }

    public static void checkAquarium() {
        if (fishList.size() > aquariumSize) {
            System.err.println("\nTHE NUMBER OF FISH IN THE AQUARIUM EXCEEDED " + aquariumSize);
            System.exit(0);
        }
    }
}