/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

import status.SpielStatus;
import status.DameStatus;
import java.io.IOException;

import algorithmen.*;
import java.util.Scanner;

public class AlphaBetaPruningSpiel {

    public static SpielStatus game;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("NegMax-Verfahren mit AlphaBetaPruning :");

        Scanner sc = new Scanner(System.in);
        System.out.println("Beginne Spiel...");
        game = new DameStatus();

        AlphaBetaPruningKnoten node = new AlphaBetaPruningKnoten(game);

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
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Fertig!");
    }
}
