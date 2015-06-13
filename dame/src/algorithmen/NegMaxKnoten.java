package algorithmen;

import java.util.ArrayList;
import java.util.List;
import spiel.Status;

public class NegMaxKnoten extends Knoten<Integer> {

    public NegMaxKnoten(Status state) {
        super(state);
    }

    @Override
    public Knoten<Integer> nextNode() {

        if (!this.getState().isTerminal()) {

            int val = Integer.MIN_VALUE;
            NegMaxKnoten negMaxKnoten = null;

            for (NegMaxKnoten node : this.getChildren()) {
                int newVal = node.getValue();

                if (negMaxKnoten == null || val < newVal) {
                    negMaxKnoten = node;
                    val = newVal;
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
            if (this.getState().isMaxSpieler()) {
                val = -val;
            }
        } else {
            val = -this.nextNode().getValue();
        }
        return val;
    }

    public List<NegMaxKnoten> getChildren() {
        List<NegMaxKnoten> list = new ArrayList<>();

        List<Status> cache = this.getState().nextStates();
        for (Status status : cache) {
            NegMaxKnoten n = new NegMaxKnoten(status.clone());
            list.add(n);
        }
        return list;
    }
}
