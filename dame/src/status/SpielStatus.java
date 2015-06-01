package status;

import java.util.List;

public interface SpielStatus {

    SpielStatus clone();

    List<SpielStatus> nextStates();

    boolean isTerminal();

    int getValue();

    int utilityValue();

    int heuristicValue();

    boolean isMaxSpieler();

    int getDepth();

    void setDepth(int depth);

    void print();
}
