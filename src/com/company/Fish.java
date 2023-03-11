package com.company;

import static com.company.FishService.*;
import static com.company.FishService.createGeneration;

class Fish implements Runnable {

    private String name;
    private int currentLocation;
    private boolean isMale;
    private int lifeTime;
    private float realLifeTime;

    public Fish() {
    }

    public Fish(String name, int currentLocation, boolean isMale, int lifeTime) {
        this.name = name;
        this.currentLocation = currentLocation;
        this.isMale = isMale;
        this.lifeTime = lifeTime;
    }

    @Override
    public void run() {

        realLifeTime = lifeTime;
        while (this.lifeTime > 0 && !(fishList.size() >= aquariumSize)) {
            try {
                Thread.sleep(3000);
                this.lifeTime--;

                for (int i = 0; i < fishList.size(); i++) {
                    Fish thisFish = fishList.get(i);

                    if (thisFish != null && thisFish.getLifeTime() > 0) {

                        int prevLoc = thisFish.currentLocation;
                        int randomLoc = RandomValueUtils.generateRandomNumber(1);

                        if (prevLoc != randomLoc) {
                            thisFish.setCurrentLocation(randomLoc);
                        } else {
                            thisFish.setCurrentLocation(RandomValueUtils.generateRandomNumber(1));
                        }
                    }
                }

                System.out.println("Fish's Location Updated! - -> name: " + (this.name)
                        + ", current_location: " + this.currentLocation
                        + ", life time: " + this.lifeTime
                        + ", gender: " + this.isMale);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            createGeneration();
        }
        checkAquarium();

        Fish deadFish = Fish.this;
        fishList.remove(deadFish);
        String count = countAliveFish();

        if (deadFish.isMale) {
            System.err.println("\nFISH DEAD - -> name: " + Fish.this.name + ", gender: " + true + ";  - -> REMOVED" + count + "\n");
        } else {
            System.err.println("\nFISH DEAD - -> name: " + Fish.this.name + ", gender: " + false + ";  - -> REMOVED" + count + "\n");
        }
        Thread.currentThread().stop();
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
                '}';
    }
}
