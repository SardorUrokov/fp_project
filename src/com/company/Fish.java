package com.company;

import static com.company.FishService.*;
import static com.company.FishService.createGeneration;

class Fish implements Runnable {

    private String name;
    private int currentLocation;
    private boolean isMale;
    private int lifeTime;
    private int realLifeTime; //nasl qoldirishda realLifeTime dan lifeTime ni ayirib nechchi birlik yashaganini aniqlash uchun

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

                    //listdagi object null emasligini va baliq tirik ekanligini tekshiradi
                    if (thisFish != null && thisFish.getLifeTime() > 0) {

                        int prevLoc = thisFish.currentLocation;
                        int randomLoc = RandomValueUtils.generateRandomNumber(1);

                        //yangi location set qilishdan oldin avvalgi va yangi locationlarini bir xil emasligini tekshiradi
                        if (prevLoc != randomLoc) {
                            thisFish.setCurrentLocation(randomLoc);
                        } else {
                            thisFish.setCurrentLocation(RandomValueUtils.generateRandomNumber(1));
                        }
                    }
                }

                //baliqning location ni o'zgargandagi message
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
        fishList.remove(deadFish); //o'lgan baliqni umumiy list(ya'ni aquarium)dan remove qilish
        String count = countAliveFish();

        if (deadFish.isMale) {
            System.err.println("\nFISH DEAD - -> name: " + Fish.this.name + ", gender: " + true + ";  - -> REMOVED" + count + "\n");
            menList.remove(deadFish); //jinsi bp'yicha listdan remove qilish
        } else {
            System.err.println("\nFISH DEAD - -> name: " + Fish.this.name + ", gender: " + false + ";  - -> REMOVED" + count + "\n");
            womenList.remove(deadFish);
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

    public void setRealLifeTime(int realLifeTime) {
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
