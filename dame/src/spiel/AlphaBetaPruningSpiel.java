package spiel;

import java.io.IOException;

import algorithmen.*;
import java.util.Scanner;

public class AlphaBetaPruningSpiel {

    public static Status status;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("NegMax-Verfahren mit AlphaBetaPruning :");

        Scanner sc = new Scanner(System.in);
        System.out.println("Spielbeginn...");
        status = new Status();

        AlphaBetaPruningKnoten node = new AlphaBetaPruningKnoten(status);

        long elapsed = 0;
        while (node != null) {
            elapsed = System.currentTimeMillis();
            node.getState().print();
            node.getState().setDepth(0);
            node = (AlphaBetaPruningKnoten) node.nextNode();
            if ((System.currentTimeMillis() - elapsed) < 1000) {
                try {
                    Thread.sleep(System.currentTimeMillis() - elapsed);
                } catch (InterruptedException e) {
                }
            }
        }

        System.out.println("Spielende!");
    }
}
