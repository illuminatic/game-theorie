package status;

import java.util.ArrayList;
import java.util.List;

class DameColor {

    public static final int S = 0;
    public static final int W = 1;
}

public class DameStatus implements SpielStatus {

    private int depth = 0;
    private boolean playerMax = true;
    private int[][] field = new int[5][5];
    private int maxStones;
    private int minStones;
    private List<SpielStatus> states;
    private boolean initialized = false;

    public DameStatus() {

        states = new ArrayList<SpielStatus>();

        // init positions
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i * 5 + j < 12) {
                    field[i][j] = DameColor.S;
                    maxStones++;
                } else if (i * 5 + j >= 13) {
                    field[i][j] = DameColor.W;
                    minStones++;
                } else {
                    field[i][j] = Integer.MIN_VALUE;
                }
            }
        }
    }

    private void generateNextStates() {
        int value = (playerMax ? DameColor.S : DameColor.W);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                // it is one of the stones of the player
                if (value == field[i][j]) {

                    // check surrounding fields
                    for (int k = i - 1; k <= i + 1; k++) {

                        for (int l = j - 1; l <= j + 1; l++) {

                            if (!validFields(k, l)) {
                                continue;
                            }

                            // field is empty
                            if (field[k][l] == Integer.MIN_VALUE) {
                                int[][] newField = cloneField(field);
                                newField[k][l] = value;
                                newField[i][j] = Integer.MIN_VALUE;

                                DameStatus newState = (DameStatus) this.clone(newField);
                                newState.playerMax = !playerMax;

                                states.add(newState);
                            } // field has opponent stone
                            else if (field[k][l] != Integer.MIN_VALUE && field[k][l] != value) {
                                // check if field behind opponent is free
                                int m = k + (k - i);
                                int n = l + (l - j);

                                if (!validFields(m, n)) {
                                    continue;
                                }

                                if (field[m][n] == Integer.MIN_VALUE) {

                                    int[][] newField = cloneField(field);
                                    newField[k][l] = Integer.MIN_VALUE;
                                    newField[i][j] = Integer.MIN_VALUE;
                                    newField[m][n] = value;
                                    DameStatus newState = (DameStatus) this.clone(newField);

                                    if (isMaxSpieler()) {
                                        newState.minStones--;
                                    } else {
                                        newState.maxStones--;
                                    }

                                    newState.playerMax = !playerMax;

                                    states.add(newState);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<SpielStatus> nextStates() {
        if (!initialized) {
            if (depth <= 5) {
                generateNextStates();
            }
            initialized = true;
        }
        return states;
    }

    private boolean validFields(int x, int y) {
        if (x < 0 || x > field.length - 1) {
            return false;
        }

        if (y < 0 || y > field[x].length - 1) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isTerminal() {

        if (maxStones == 0 || minStones == 0) {
            return true;
        }
        if (this.nextStates().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public int utilityValue() {
        if (isMaxSpieler()) {
            return -1000;
        } else {
            return 1000;
        }

    }

    @Override
    public int heuristicValue() {
        return (maxStones - minStones);
    }

    @Override
    public boolean isMaxSpieler() {
        return playerMax;
    }

    public int[][] cloneField(int[][] copyField) {
        int[][] newField = new int[5][5];
        for (int i = 0; i < copyField.length; i++) {
            for (int j = 0; j < copyField[i].length; j++) {
                newField[i][j] = copyField[i][j];
            }
        }

        return newField;
    }

    @Override
    public SpielStatus clone() {
        DameStatus state = new DameStatus();
        state.playerMax = this.playerMax;
        state.field = cloneField(this.field);
        state.maxStones = this.maxStones;
        state.minStones = this.minStones;
        state.depth = this.depth + 1;
        state.initialized = false;
        return state;
    }

    public SpielStatus clone(int[][] field) {
        DameStatus state = new DameStatus();
        state.playerMax = this.playerMax;
        state.field = cloneField(field);
        state.maxStones = this.maxStones;
        state.minStones = this.minStones;
        state.depth = this.depth + 1;
        state.initialized = false;
        return state;
    }

    @Override
    public int getValue() {
        if (isTerminal()) {
            if (maxStones == 0 || minStones == 0) {
                return utilityValue();
            } else {
                if (this.nextStates().isEmpty()) {
                    return heuristicValue();
                } else {
                    int val = (isMaxSpieler() ? Integer.MIN_VALUE : Integer.MAX_VALUE);
                    for (SpielStatus s : states) {
                        if (isMaxSpieler() && (s.getValue() > val)) {
                            val = s.getValue();
                        } else if (!isMaxSpieler() && (s.getValue() < val)) {
                            val = s.getValue();
                        }
                    }
                    return val;
                }
            }
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
        generateNextStates();
    }

    @Override
    public void print() {
        for (int i = 0; i < field.length; i++) {
            for (int k = 0; k < 21; k++) {
                System.out.print("\u2015");
            }
            System.out.print("\n");
            System.out.print("|");
            for (int j = 0; j < field[i].length; j++) {

                String c = "   |";

                if (field[i][j] == 0) {
                    c = " S |";
                } else if (field[i][j] == 1) {
                    c = " W |";
                }

                System.out.print(c);
            }
            System.out.println("");
        }
        for (int k = 0; k < 21; k++) {
            System.out.print("\u2015");
        }
        System.out.print("\n");
        if (maxStones == 0 || minStones == 0) {
            System.out.println((!isMaxSpieler() ? "Schwarz" : "Weiss") + " hat gewonnen!");
        } else {
            System.out.println("Nächster Zug von " + (isMaxSpieler() ? "Schwarz:" : "Weiss:"));
        }
    }

}
