package spiel;

import java.io.IOException;

import algorithmen.*;
import java.util.Scanner;

public class AlphaBetaPruningSpiel {

    public static Status status;

    public static void main(String[] args) throws IOException {

        System.out.println("NegMax-Verfahren mit AlphaBetaPruning :");

        Scanner sc = new Scanner(System.in);
        System.out.println("Spielbeginn...");
        status = new Status();

        AlphaBetaPruningKnoten knoten = new AlphaBetaPruningKnoten(status);

        while (knoten != null) {
            knoten.getState().print();
            knoten.getState().setDepth(0);
            knoten = (AlphaBetaPruningKnoten) knoten.nextNode();
        }

        System.out.println("Spielende!");
    }
}
