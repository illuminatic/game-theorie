package algorithmen;

import java.util.ArrayList;
import java.util.List;

import spiel.Status;

public class MiniMaxKnoten extends Knoten<Integer> {

    public MiniMaxKnoten(Status state) {
        super(state);
    }

    @Override
    public Knoten<Integer> nextNode() {

        if (!this.getState().isTerminal()) {

            if (this.getState().isMaxSpieler()) {

                int val = Integer.MIN_VALUE;
                MiniMaxKnoten maxKnoten = null;

                for (MiniMaxKnoten node : this.getChildren()) {
                    if (maxKnoten == null || val < node.getValue()) {
                        maxKnoten = node;
                        val = node.getValue();
                    }
                }
                return maxKnoten;

            } else {

                int val = Integer.MAX_VALUE;
                MiniMaxKnoten minKnoten = null;

                for (MiniMaxKnoten node : this.getChildren()) {
                    if (minKnoten == null || val > node.getValue()) {
                        minKnoten = node;
                        val = node.getValue();
                    }
                }
                return minKnoten;
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

    public List<MiniMaxKnoten> getChildren() {
        List<MiniMaxKnoten> list = new ArrayList<>();

        List<Status> cache = this.getState().nextStates();
        for (Status status : cache) {
            MiniMaxKnoten n = new MiniMaxKnoten(status.clone());
            list.add(n);
        }
        return list;
    }
}
