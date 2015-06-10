package spiel;

import java.io.IOException;
import java.util.Scanner;

public class Spielen {

    private static String antwort;

    public static void main(String[] args) throws IOException {
        do {
            int num = 0;
            do {
                try {
                    System.out.println("Bitte wählen Sie das Verfahren:");
                    callOptions();
                    Scanner in = new Scanner(System.in);
                    String input = in.next();

                    num = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Falscher Input! Versuchen Sie es nochmal...");
                }
            } while (!(num > 0 && num < 4));

            chooseGame(num, args);

            System.out.println("Möchten Sie noch einmal spielen? [j][n]");
            Scanner in = new Scanner(System.in);
            antwort = in.nextLine().toLowerCase();

        } while (antwort.equals("j"));
    }

    private static void chooseGame(int num, String[] args) throws IOException {
        if (num == 1) {
            MiniMaxSpiel.main(args);
        } else if (num == 2) {
            NegMaxSpiel.main(args);
        } else {
            AlphaBetaPruningSpiel.main(args);
        }
    }

    private static void callOptions() {
        System.out.println("MiniMax [1] ");
        System.out.println("NegMax [2]");
        System.out.println("NegMax mit AlphaBetaPruning [3]");
    }

}
