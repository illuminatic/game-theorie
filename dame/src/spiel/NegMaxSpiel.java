package spiel;

import java.io.IOException;
import algorithmen.*;
import java.util.Scanner;

public class NegMaxSpiel {

    private static Status status;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("NegMax-Verfahren:");

        Scanner sc = new Scanner(System.in);
        System.out.println("Spielbeginn...");
        status = new Status();
        NegMaxKnoten node = new NegMaxKnoten(status);

        long elapsed = 0;
        while (node != null) {
            elapsed = System.currentTimeMillis();
            node.getState().print();
            node.getState().setDepth(0);
            node = (NegMaxKnoten) node.nextNode();

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
