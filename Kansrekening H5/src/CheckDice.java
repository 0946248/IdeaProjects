package KansRekenenOpdracht5;

import java.util.ArrayList;

public class CheckDice {
    private int pokerCount = 0, fourofaKindCount = 0, fullHouseCount = 0, threeofaKindCount = 0, twoofaKindCount = 0, twoPairCount = 0, restCount = 0;
    String poker = "";
    String fourofaKind = "";
    String fullHouse = "";
    String threeofaKind = "";
    String twoofaKind = "";
    String twoPair = "";
    String rest = "";

    public void checkOutcome(int[] Dice) {
        ArrayList<Integer> ones = new ArrayList<Integer>();
        ArrayList<Integer> twos = new ArrayList<Integer>();
        ArrayList<Integer> threes = new ArrayList<Integer>();
        ArrayList<Integer> fours = new ArrayList<Integer>();
        ArrayList<Integer> fives = new ArrayList<Integer>();
        ArrayList<Integer> sixes = new ArrayList<Integer>();

        for (int i = 1; i < 6; i++) {
            if (Dice[i] == 1) {
                ones.add(1);
            } else if (Dice[i] == 2) {
                twos.add(1);
            } else if (Dice[i] == 3) {
                threes.add(1);
            } else if (Dice[i] == 4) {
                fours.add(1);
            } else if (Dice[i] == 5) {
                fives.add(1);
            } else if (Dice[i] == 6) {
                sixes.add(1);
            }
        }
        // Poker
        if (ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size() == 5) {
            pokerCount++;
            for (int j = 1; j < 6; j++) {
                poker += Dice[j];
            }
            poker += " ";
        }
        // Four of a Kind
        else if (ones.size() == 4 || twos.size() == 4 || threes.size() == 4 || fours.size() == 4 || fives.size() == 4 || sixes.size() == 4) {
            fourofaKindCount++;
            for (int j = 1; j < 6; j++) {
                fourofaKind += Dice[j];
            }
            fourofaKind += " ";
        }
        // Full House OR Three of a Kind
        else if (ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3 || sixes.size() == 3) {
            if (ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2) {
                //Full House
                fullHouseCount++;
                for (int j = 1; j < 6; j++) {
                    fullHouse += Dice[j];
                }
                fullHouse += " ";
            } else {
                //Three of a Kind
                threeofaKindCount++;
                for (int j = 1; j < 6; j++) {
                    threeofaKind += Dice[j];
                }
                threeofaKind += " ";
            }
        }
        // Two of a Kind
        else if (ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2) {
            if (
                    (ones.size() == 2 && (twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2)) ||
                            (twos.size() == 2 && (ones.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2)) ||
                            (threes.size() == 2 && (twos.size() == 2 || ones.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2)) ||
                            (fours.size() == 2 && (twos.size() == 2 || threes.size() == 2 || ones.size() == 2 || fives.size() == 2 || sixes.size() == 2)) ||
                            (fives.size() == 2 && (twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || ones.size() == 2 || sixes.size() == 2)) ||
                            (sixes.size() == 2 && (twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || ones.size() == 2))
                    ) {
                twoPairCount++;
                for (int j = 1; j < 6; j++) {
                    twoPair += Dice[j];
                }
                twoPair += " ";

            } else {
                twoofaKindCount++;
                for (int j = 1; j < 6; j++) {
                    twoofaKind += Dice[j];
                }
                twoofaKind += " ";
            }
        }

        // Rest
        else {
            restCount++;
            for (int j = 1; j < 6; j++) {
                rest += Dice[j];
            }
            rest += " ";
        }
    }


    public void printOutcome() {
        System.out.println("Poker: " + poker);
        System.out.println("Aantal: " + pokerCount);
        System.out.println("Four of a Kind: " + fourofaKind);
        System.out.println("Aantal: " + fourofaKindCount);
        System.out.println("Full House: " + fullHouse);
        System.out.println("Aantal: " + fullHouseCount);
        System.out.println("Three of a Kind: "+threeofaKind);
        System.out.println("Aantal: " + threeofaKindCount);
        System.out.println("Two of a Kind: "+twoofaKind);
        System.out.println("Aantal: " + twoofaKindCount);
        System.out.println("Two Pair: "+twoPair);
        System.out.println("Aantal: " + twoPairCount);
        System.out.println("Rest: "+rest);
        System.out.println("Aantal: " + restCount);
    }

    public void printPoker() {
        System.out.println("Poker: " + poker);
        System.out.println("Aantal: " + pokerCount);
    }

    public void printFourofaKind() {
        System.out.println("Four of a Kind: " + fourofaKind);
        System.out.println("Aantal: " + fourofaKindCount);
    }

    public void printFullHouse() {
        System.out.println("Full House: " + fullHouse);
        System.out.println("Aantal: " + fullHouseCount);
    }

    public void printThreeofaKind() {
        System.out.println("Three of a Kind: " + threeofaKind);
        System.out.println("Aantal: " + threeofaKindCount);
    }

    public void printTwoofaKind() {
        System.out.println("Two of a Kind: " + twoofaKind);
        System.out.println("Aantal: " + twoofaKindCount);
    }

    public void printTwoPair() {
        System.out.println("Two Pair: " + twoPair);
        System.out.println("Aantal: " + twoPairCount);
    }

    public void printRest() {
        System.out.println("Rest: " + rest);
        System.out.println("Aantal: " + restCount);
    }


}