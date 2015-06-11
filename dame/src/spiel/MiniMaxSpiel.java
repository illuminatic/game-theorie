package spiel;

import java.io.IOException;
import algorithmen.*;
import java.util.Scanner;

public class MiniMaxSpiel {

    private static Status status;
    private static MiniMaxKnoten knoten;

    public static void main(String[] args) throws IOException {

        System.out.println("MinMax-Verfahren:");

        Scanner sc = new Scanner(System.in);
        System.out.println("Spielbeginn...");
        status = new Status();

        knoten = new MiniMaxKnoten(status);

        while (knoten != null) {
            knoten.getState().print();
            knoten.getState().setDepth(0);
            knoten = (MiniMaxKnoten) knoten.nextNode();
        }
        System.out.println("Spielende!");
    }

}
