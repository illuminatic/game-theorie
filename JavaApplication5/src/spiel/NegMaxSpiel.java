/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

/**
 *
 * @author Bolaños & Düggelin
 */
import status.SpielStatus;
import status.NimStatus;
import status.DameStatus;
import java.io.IOException;

import algorithmen.*;
import java.util.Scanner;

public class NegMaxSpiel {
    private static SpielStatus game;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("NegMax-Verfahren:");
        //System.out.println("Wahle dein Spiel \n[0] für Dame \n[1] für Nim");
        
        Scanner sc = new Scanner(System.in);
        //int zahl = sc.nextInt();
        int zahl = 0;
        if(zahl == 0){
            System.out.println("Beginne Dame...");
            game = new DameStatus();
             
        }else{
            System.out.println("Beginne Nim...");
            System.out.println("Wähle die Anzahl Hölzchen zum Spielen");
           // Scanner sc2 = new Scanner(System.in);
            int num1 = sc.nextInt();
            System.out.println("Wähle Anzahl Hölzchen zum Ziehen ");
             int num2 = sc.nextInt();
            
            game = new NimStatus(num1,num2); 
        }
        NegMaxKnoten node = new NegMaxKnoten(game);

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
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Fertig!");
    }

}
