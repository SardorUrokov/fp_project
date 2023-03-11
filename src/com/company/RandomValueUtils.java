package com.company;

import java.util.Random;

public class RandomValueUtils {

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

}
