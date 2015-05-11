/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

/**
 *
 * @author bfh
 */
import java.util.ArrayList;
import java.util.List;

import algorithmen.Knoten;
import spiel.SpielStatus;

public class NimStatus implements SpielStatus {

    private int depth = 0;
    public int numberOfSticks;
    private boolean maxPlayer = true;

    public NimStatus() {
        this.numberOfSticks = 21;
    }

    @Override
    public List<SpielStatus> nextStates() {
        List<SpielStatus> states = new ArrayList<SpielStatus>();

        for (int i = 1; i <= Math.min(3, numberOfSticks); i++) {
            NimStatus newState = (NimStatus) this.clone();
            newState.numberOfSticks -= i;
            newState.maxPlayer = !maxPlayer;
            newState.depth = this.depth + 1;
            states.add(newState);
        }

        return states;
    }

    @Override
    public boolean isTerminal() {
        return (numberOfSticks == 0);
    }

    @Override
    public int utilityValue() {
        if (isMaxPlayer()) {
            return -100;
        } else {
            return 100;
        }

        // @TODO exception
    }

    @Override
    public int heuristicValue() {
        if (numberOfSticks % 4 == 0 && isMaxPlayer()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean isMaxPlayer() {
        return maxPlayer;
    }

    @Override
    public SpielStatus clone() {
        NimStatus state = new NimStatus();
        state.numberOfSticks = this.numberOfSticks;
        state.maxPlayer = this.maxPlayer;
        return state;
    }

    @Override
    public int getValue() {
        if (isTerminal()) {
            return utilityValue();
        } else {
            return heuristicValue();
        }
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public void print() {
        System.out.println("Verbleibende Zundhölzchen: " + numberOfSticks);
        if (isTerminal()) {
            System.out.println((!isMaxPlayer() ? "MAX" : "MIN") + " hat gewonnen!");
        } else {
            System.out.println("Nächster Zug von " + (isMaxPlayer() ? "MAX" : "MIN"));
        }

    }

}
