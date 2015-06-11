package spiel;

import java.io.IOException;
import algorithmen.*;
import java.util.Scanner;

public class NegMaxSpiel {

    private static Status status;

    public static void main(String[] args) throws IOException {

        System.out.println("NegMax-Verfahren:");

        Scanner sc = new Scanner(System.in);
        System.out.println("Spielbeginn...");
        status = new Status();
        NegMaxKnoten knoten = new NegMaxKnoten(status);

        while (knoten != null) {
            knoten.getState().print();
            knoten.getState().setDepth(0);
            knoten = (NegMaxKnoten) knoten.nextNode();
        }

        System.out.println("Spielende!");
    }

}
