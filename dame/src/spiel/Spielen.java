package spiel;

import java.io.IOException;
import java.util.Scanner;

public class Spielen {

    private static String ant;

    public static void main(String[] args) throws IOException {
        do {

            System.out.println("Bitte wählen Sie das Verfahren:");
            callOptions();

            int num = 0;

            while (num < 1 || num > 3) {
                Scanner in = new Scanner(System.in);
                num = in.nextInt();
                if (num < 1 || num > 3) {
                    System.out.println("Bitte wählen Sie ein gültiges Verfahren:");
                    callOptions();
                }
            }

            chooseGame(num, args);

            System.out.println("Möchten Sie noch einmal spielen? [j][n]");
            Scanner in = new Scanner(System.in);
            ant = in.nextLine().toLowerCase();

        } while (ant.equals("j"));
    }

    private static void chooseGame(int num, String[] args) throws IOException {
        if (num == 1) {
                MiniMaxSpiel.main(args); }
        else if (num == 2) {
                NegMaxSpiel.main(args); }
        else {
                AlphaBetaPruningSpiel.main(args);
        }
    }

    private static void callOptions() {
        System.out.println("MiniMax [1] ");
        System.out.println("NegMax [2]");
        System.out.println("NegMax mit AlphaBetaPruning [3]");
    }

}
