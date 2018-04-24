package KansRekenenOpdracht5;

public class Main {

    public static void main(String[] args) {
        int[] Dice = new int[6];
        KansRekenenOpdracht5.CheckDice check = new KansRekenenOpdracht5.CheckDice();
        int TotalCount = 0;
        for (Dice[1] = 1; Dice[1] <= 6; Dice[1]++) {
            for (Dice[2] = 1; Dice[2] <= 6; Dice[2]++) {
                for (Dice[3] = 1; Dice[3] <= 6; Dice[3]++) {
                    for (Dice[4] = 1; Dice[4] <= 6; Dice[4]++) {
                        for (Dice[5] = 1; Dice[5] <= 6; Dice[5]++) {
                            TotalCount++;
                            check.checkOutcome(Dice);

                        }
                    }
                }
            }
        }

        check.printOutcome();
        System.out.println("Totaal Aantal Mogelijkheden: "+TotalCount);
    }


}
