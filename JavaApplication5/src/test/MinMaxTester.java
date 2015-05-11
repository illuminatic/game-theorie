/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author bfh
 */

import java.io.IOException;

import algorithmen.*;
import spiel.*;


public class MinMaxTester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("MinMax-Verfahren:");
		SpielStatus game = new DameStatus();
		MinMaxKnoten node = new MinMaxKnoten(game);
		
		long elapsed = 0;
		while (node!=null)
		{
			elapsed = System.currentTimeMillis();
			node.getState().print();
			node.getState().setDepth(0);
			node = (MinMaxKnoten)node.nextNode();
			
			if ((System.currentTimeMillis()-elapsed) < 1000) {
				try {
					Thread.sleep(System.currentTimeMillis()-elapsed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		System.out.println("Fertig!");
	}

}