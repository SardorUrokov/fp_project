package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FishService implements Runnable {

    public static List<Fish> fishList = new ArrayList<>();
    public static List<Fish> menList = new ArrayList<>();
    public static List<Fish> womenList = new ArrayList<>();
    public static Random random = new Random();

    public static void start() {
        int men = random.nextInt(6);
        int women = random.nextInt(6);

        for (int i = 0; i < men; i++) {
            fishList.add(new Fish(generateRandomName(), generateRandomNumber(1), true, random.nextInt(50)));
        }
        for (int i = 0; i < women; i++) {
            fishList.add(new Fish(generateRandomName(), generateRandomNumber(1), false, random.nextInt(50)));
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

                newFish = new Fish(generateRandomName(), generateRandomNumber(1), random.nextBoolean(), random.nextInt(50));
                fishList.add(newFish);

                if (newFish.isMale())
                    menList.add(newFish);
                else
                    womenList.add(newFish);

                new Thread(newFish).start();

                System.out.println("\nNEW FISH - -> name: " + newFish.getName()
                        + ", gender: " + newFish.isMale()
                        + ", life_time: " + newFish.getLifeTime()
                        + ", current_location: " + newFish.getCurrentLocation());

                System.out.println("Parents - -> ManFish name: " + manFish.getName()
                        + ", WomanFish name: " + womanFish.getName() + "\n");
            }
        }
    }

    public static String generateRandomName() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return (sb.toString()).toUpperCase();
    }

    public static int generateRandomNumber(int numOfDigits) {
        if (numOfDigits == 1)
            return (int) ((Math.random() * 9) + 1); //a 1 digit random value is generated
        else if (numOfDigits == 2)
            return (int) ((Math.random() * 90) + 10); //a 2 digit random value is generated
        else if (numOfDigits == 3)
            return (int) ((Math.random() * 900) + 100); //a 3 digit random value is generated
        else
            return (int) ((Math.random() * 9000) + 1000); //a 4 digit random value is generated
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

        boolean isAbleBody = womanFish.getRealLifeTime() - womanFish.getLifeTime() >= 5;

        boolean isDurable = manFish.getRealLifeTime() - manFish.getLifeTime() >= 5;

        boolean isSameLocation = (manFish.getCurrentLocation()) == (womanFish.getCurrentLocation());

        return isAbleBody && isDurable && isSameLocation;

    }

    public static String countAliveFish() {
        long men = 0;
        long women = 0;

        for (Fish fish : fishList) {
            if (fish.getLifeTime() > 0) {
                if (fish.isMale())
                    men++;
                else
                    women++;
            }
        }
        return men + women + ": \nTrue fish: " + men + ", False fish: " + women + "\n";
    }

    @Override
    public void run() {
        createGeneration();
    }
}