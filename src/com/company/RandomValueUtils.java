package com.company;

import java.util.Random;

public class RandomValueUtils {

    //baliqlarga random nom generate qiladi
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

    //baliqning location nini o'zgartirib turish uchun
    public static int generateRandomNumber(int numOfDigits) {
        if (numOfDigits == 1)
            return (int) ((Math.random() * 9) + 1); // 1 xonali generate qiladi
        else if (numOfDigits == 2)
            return (int) ((Math.random() * 90) + 10); // 2 xonali generate qiladi
        else
            return (int) ((Math.random() * 900) + 100); // 3 xonali generate qiladi
     }

}
