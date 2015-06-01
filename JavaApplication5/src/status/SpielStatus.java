/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package status;


import java.util.List;

import algorithmen.Knoten;

public interface SpielStatus {

    SpielStatus clone();

    List<SpielStatus> nextStates();

    boolean isTerminal();

    int getValue();		// utility oder heuristic	

    int utilityValue(); 	// utility

    int heuristicValue(); 	// heuristic

    boolean isMaxSpieler();

    int getDepth();

    void setDepth(int depth);
    
    void print();

}
