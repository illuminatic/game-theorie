/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package status;


import java.util.ArrayList;
import java.util.List;

public class NimStatus implements SpielStatus {

    private int depth = 0;
    public int numberOfSticks;
    private boolean maxSpieler = true;
    private int sticksToremove;

    public NimStatus(int sticksNumberToPlay, int sticksNumberToRemove) {
        this.numberOfSticks = sticksNumberToPlay ;
        this.sticksToremove = sticksNumberToRemove;
    }

    @Override
    public List<SpielStatus> nextStates() {
        List<SpielStatus> states = new ArrayList<>();

        for (int i = 1; i <= Math.min(3, numberOfSticks); i++) {
            NimStatus newState = (NimStatus) this.clone();
            newState.numberOfSticks -= i;
            newState.maxSpieler = !maxSpieler;
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
        if (isMaxSpieler()) {
            return -100;
        } else {
            return 100;
        }

        // @TODO exception
    }

    @Override
    public int heuristicValue() {
        if (numberOfSticks % sticksToremove == 0 && isMaxSpieler()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean isMaxSpieler() {
        return maxSpieler;
    }

    @Override
    public SpielStatus clone() {
        NimStatus state = new NimStatus(numberOfSticks,sticksToremove);
        state.numberOfSticks = this.numberOfSticks;
        state.maxSpieler = this.maxSpieler;
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
            System.out.println((!isMaxSpieler() ? "Spieler 1" : "Spieler 2") + " hat gewonnen!");
        } else {
            System.out.println("Nächster Zug von " + (isMaxSpieler() ? "Spieler 1" : "Spieler 2"));
        }

    }

}
