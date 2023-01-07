package com.demo.cowsandbulls;

    import java.util.Scanner;
    import java.util.Arrays;

public class Main {

    //The main framework, puts the "game" into a while loop until win conditions are met
    public static void main(String[] args) {
        Boolean gameOn = true;
        int len = getLen(gameOn);
        int[] code = createCode(len);
        while (gameOn) {
            int[] guess = takeGuess(len);
            gameOn = getResult(gameOn, len, code, guess);
        }
    }


    //Asks user for length of code, prints error if length is over 10 digits
    public static int getLen(Boolean gameOn) {
        System.out.println("Enter length");
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        if (len > 10) {
            System.out.println("Error");
            gameOn = false;
        }
        return len;
    }

    //method to create the secret code using length.  The code is stored as an array for easy comparsions
    public static int[] createCode(int len) {
        Boolean done = false;
        int code = 0;
        int[] acode = new int[len];
        while (!done) {
            code = 0;
            done = true;
            while (code == 0) {
                code = (int) (Math.random() * 10);
                acode[0] = code;
            }
            for (int i = 1; i < len; i++) {
                int newnum = (int) (Math.random() * 10);
                code = (code * 10) + newnum;
                acode[i] = newnum;
            }
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (acode[i] == acode[j] && i != j) {
                        done = false;
                    }
                }
            }
        }
        //System.out.println(code);
        return acode;
    }


    //Method to take guess from user and turns it into an array
    public static int[] takeGuess(int len) {
        System.out.println("Enter your guess");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int[] guess = new int[len];
        for (int i = len - 1; num > 0; i--) {
            guess[i] = num % 10;
            num = num / 10;
        }
        //System.out.println(Arrays.toString(guess));
        return guess;
    }

    //Method to take number of Bulls and Cows into a result.  Correct code will trigger win conditions
    public static Boolean getResult(Boolean gameOn, int len, int[] code, int[] guess) {
        int bulls = getBulls(len, code, guess);
        int cows = getCows(len, code, guess);
        System.out.format("You have %d bulls and %d cows", bulls, cows);
        System.out.println();
        if (bulls == len) {
            gameOn = false;
            System.out.println("Congratulations you won!");
        } else {
            gameOn = true;
        }
        return gameOn;
    }


    //Method to compare guess and code for bulls (correct value in correct place)
    public static int getBulls(int len, int[] code, int[] guess) {
        int bulls = 0;
        for (int i = 0; i < len; i++) {
            if (guess[i] == code[i]) {
                bulls = bulls + 1;
            }
        }
        return bulls;
    }

    //Method to get number of cows (correct value, wrong place)
    public static int getCows(int len, int[] code, int[] guess) {
        int cows = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (code[i] == guess[j] && i != j) {
                    cows = cows + 1;
                }
            }
        }
        return cows;
    }
}
