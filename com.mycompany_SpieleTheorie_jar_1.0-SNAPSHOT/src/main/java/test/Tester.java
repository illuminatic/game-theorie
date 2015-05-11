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
import java.util.Scanner;

public class Tester {
	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Bitte wählen Sie das Verfahren:");
		System.out.println("[1] MinMax");
		System.out.println("[2] NegMax");
		System.out.println("[3] NegMax mit AlphaBetaPruning");
		
		int num = 0;
		
		
		while (num < 1 || num > 3) {
			Scanner in = new Scanner(System.in);
			num = in.nextInt();
		}
		
		switch (num) {
		case 1:
			MinMaxTester.main(args);
			break;
		case 2:
			NegMaxTester.main(args);
			break;
		case 3:
			AlphaBetaPruningTester.main(args);
			break;
		}
	}
	
	
}

