package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/*
Завдання 4 (комплексне): скласти комп’ютерну програму (мова програмування довільна)
із зазначеними вхідними даними за своїм варіантом та результатами для такої задачі:
Маємо колоду з 52 карт. Визначити кількість комбінацій для заданих операцій.
9 варіант: Скількома способами можна розкласти 15 обраних (без врахування порядку) карт?
*/
    
public class Main {

    public static int scanIfInteger(String x) {
        int y;
        if (x.matches("[-,0-9]+")) {
            y = Integer.parseInt(x);
            return y;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String scanIfText(String x) {
        if (x.matches("[a-zA-Z]+")) {
            return x;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        String[] deckCardsValue = new String[]{"D", "H", "S", "C"};
        String[] deckCardsSuit = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int deckLength = deckCardsValue.length * deckCardsSuit.length;
        String[] deck = new String[deckLength];
        Scanner scan = new Scanner(System.in);
        String userInput;
        String userText;
        int userNumber = 0;
        boolean noError;
        int n = 0;


        for (String suit : deckCardsSuit) {
            for (String value : deckCardsValue) {
                deck[n] = suit + value;
                n++;
            }
        }

        System.out.println(Colors.yellowText.getColor() + "Created deck: \n" + Colors.colorReset.getColor()
                + Arrays.toString(deck));
        do {
            noError = true;
            try {
                System.out.print(Colors.yellowText.getColor() + "Shuffle the deck? (yes/no)"
                        + Colors.colorReset.getColor());
                userInput = scan.nextLine();
                userInput = userInput.toLowerCase(Locale.ROOT);
                userText = scanIfText(userInput);
                if (userText.matches("[yes]+")) {
                    Collections.shuffle(Arrays.asList(deck));
                    System.out.println("Deck has been shuffled!");
                } else if (userText.matches("[no]+")) {
                    break;
                } else {
                    System.out.println("That's not an answer!");
                    noError = false;
                }
            } catch (IllegalArgumentException e) {
                noError = false;
            }
        } while (!noError);
        System.out.println(Colors.yellowText.getColor() + "Current deck: \n" + Colors.colorReset.getColor()
                + Arrays.toString(deck));

        do {
            noError = true;
            try {
                System.out.print(Colors.yellowText.getColor() + "Input amount of cards you need: "
                        + Colors.colorReset.getColor());
                userInput = scan.nextLine();
                userNumber = scanIfInteger(userInput);
                if (userNumber > 52 || userNumber < 1) {
                    System.out.println("Wrong number!");
                    noError = false;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong number!");
                noError = false;
            }
        } while (!noError);

        String[] chosenCards = new String[userNumber];

        n = 0;
        for (int i = 0; i < chosenCards.length; i++) {
            chosenCards[i] = deck[n];
            n++;
        }

        BigInteger amountOfWays = BigInteger.valueOf(1);
        for (int i = 1; i <= userNumber; i++) {
            amountOfWays = amountOfWays.multiply(BigInteger.valueOf(i));
        }

        System.out.println(Colors.yellowText.getColor() + "Chosen cards: " + Colors.greenText.getColor() +
                Arrays.toString(chosenCards) + Colors.colorReset.getColor());
        System.out.println(Colors.yellowText.getColor() + "Amount of ways to get " + userNumber + " cards: " +
                Colors.greenText.getColor() + amountOfWays + Colors.colorReset.getColor());
    }
}
