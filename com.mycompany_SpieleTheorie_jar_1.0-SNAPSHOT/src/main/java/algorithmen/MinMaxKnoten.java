/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmen;

/**
 *
 * @author bfh
 */
import java.util.ArrayList;
import java.util.List;

import spiel.SpielStatus;
import spiel.NimStatus;

public class MinMaxKnoten extends Knoten<Integer> {

    public MinMaxKnoten(SpielStatus state) {
        super(state);
    }

    @Override
    public Knoten<Integer> nextNode() {

        if (!this.getState().isTerminal()) {

            if (this.getState().isMaxPlayer()) {

                int val = Integer.MIN_VALUE;
                MinMaxKnoten maxNode = null;

                for (MinMaxKnoten node : this.getChildren()) {
                    if (maxNode == null || val < node.getValue()) {
                        maxNode = node;
                        val = node.getValue();
                    }
                }

                return maxNode;

            } else {

                int val = Integer.MAX_VALUE;
                MinMaxKnoten minNode = null;

                for (MinMaxKnoten node : this.getChildren()) {
                    if (minNode == null || val > node.getValue()) {
                        minNode = node;
                        val = node.getValue();
                    }
                }

                return minNode;

            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        if (this.getState().isTerminal()) {
            return this.getState().getValue();
        } else {
            return this.nextNode().getValue();

        }
    }

    public List<MinMaxKnoten> getChildren() {
        List<MinMaxKnoten> list = new ArrayList<MinMaxKnoten>();

        List<SpielStatus> cache = this.getState().nextStates();
        for (SpielStatus game : cache) {
            MinMaxKnoten n = new MinMaxKnoten(game.clone());
            list.add(n);
        }

        return list;
    }

    @Override
    boolean solve() {
        return this.getValue() > 0;
    }

}
