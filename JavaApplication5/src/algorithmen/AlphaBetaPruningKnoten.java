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
import java.util.Random;

import spiel.SpielStatus;
import spiel.NimStatus;

public class AlphaBetaPruningKnoten extends Knoten<Integer> {

    private int alpha;
    private int beta;

    public AlphaBetaPruningKnoten(SpielStatus state) {
        super(state);

        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }

    public Knoten<Integer> nextNode() {

        if (!this.getState().isTerminal()) {

            int val = Integer.MIN_VALUE;
            boolean allowCut = true;
            AlphaBetaPruningKnoten negMaxKnoten = null;

            for (AlphaBetaPruningKnoten node : this.getChildren()) {
                int newVal = node.getValue();

                if (negMaxKnoten == null || val < newVal) {

                    allowCut = (negMaxKnoten != null);

                    negMaxKnoten = node;
                    val = newVal;

                    if (this.getState().isMaxPlayer()) {
                        alpha = val;
                    } else {
                        beta = val;
                    }

                    if (alpha >= beta && allowCut) {
                        return negMaxKnoten;
                    }
                }
            }

            return negMaxKnoten;
        }
        return null;
    }

    @Override
    public Integer getValue() {
        int val;
        if (this.getState().isTerminal()) {
            val = this.getState().getValue();
            if (this.getState().isMaxPlayer()) {
                val = -val;
            }
        } else {
            val = -this.nextNode().getValue();
        }

        return val;
    }

    public List<AlphaBetaPruningKnoten> getChildren() {
        List<AlphaBetaPruningKnoten> list = new ArrayList<AlphaBetaPruningKnoten>();

        List<SpielStatus> cache = this.getState().nextStates();
        for (SpielStatus game : cache) {
            AlphaBetaPruningKnoten n = new AlphaBetaPruningKnoten(game.clone());
            n.alpha = this.alpha;
            n.beta = this.beta;
            list.add(n);
        }

        return list;
    }

    @Override
    public boolean solve() {
        return -this.getValue() > 0;
    }

}
