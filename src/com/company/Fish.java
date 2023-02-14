package com.company;

import java.util.Objects;

import static com.company.FishService.*;
import static com.company.FishService.createGeneration;

class Fish implements Runnable {

    private String name;
    private int currentLocation;
    private boolean isMale;
    private int lifeTime;
    private float realLifeTime = lifeTime;

    public Fish() {
    }

    public Fish(String name, int currentLocation, boolean isMale, int lifeTime) {
        this.name = name;
        this.currentLocation = currentLocation;
        this.isMale = isMale;
        this.lifeTime = lifeTime;
    }

    public Fish(String name, int currentLocation, boolean isMale, float realLifeTime) {
        this.name = name;
        this.currentLocation = currentLocation;
        this.isMale = isMale;
        this.realLifeTime = realLifeTime;
    }

    @Override
    public void run() {
        realLifeTime = lifeTime;

        while (this.lifeTime > 0) {
            try {
                Thread.sleep(5000);
                this.lifeTime--;

                for (Fish thisFish : fishList) {
                    if (thisFish.getLifeTime() > 0) {

                        int prevLoc = thisFish.currentLocation;
                        int randomLoc = generateRandomNumber(1);

                        if (prevLoc != randomLoc)
                            thisFish.setCurrentLocation(randomLoc);
                    } else {
                        continue;
                    }
                }

                System.out.println("Current Location Updated! - -> name: " + this.name
                        + ", current_location: " + this.currentLocation
                        + ", life time: " + this.lifeTime
                        + ", gender: " + this.isMale);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            createGeneration();

        }

        if (isMale) {
            System.err.println("\nFISH DEAD - -> name: " + Fish.this.name + ", gender: " + true + "\n");
        } else {
            System.err.println("\nFISH DEAD - -> name: " + Fish.this.name + ", gender: " + false + "\n");
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public float getRealLifeTime() {
        return realLifeTime;
    }

    public void setRealLifeTime(float realLifeTime) {
        this.realLifeTime = realLifeTime;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", currentLocation=" + currentLocation +
                ", isMale=" + isMale +
                ", lifeTime=" + lifeTime +
                ", realLifeTime=" + realLifeTime +
                '}';
    }
}
