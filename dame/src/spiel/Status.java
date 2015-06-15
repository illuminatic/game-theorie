package spiel;


import java.util.ArrayList;
import java.util.List;

public class Status {

    private int tiefe = 0;
    private boolean MaxSpieler = true;
    private int[][] feld = new int[5][5];
    private int maxSteine, minSteine;
    //private int feldWert;
    private List<Status> states;
    private boolean initialized = false;
    private static final int MAX_TIEFE = 5;

    public Status() {

        states = new ArrayList<>();

        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                if (i * 5 + j < 12) {
                    feld[i][j] = 0;
                    maxSteine++;
                } else if (i * 5 + j >= 13) {
                    feld[i][j] = 1;
                    minSteine++;
                } else {
                    feld[i][j] = Integer.MIN_VALUE;
                }
            }
        }
    }

    private void generateNextStates() {
        int value = (MaxSpieler ? 0 : 1);

        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {

                if (value == feld[i][j]) {

                    for (int k = i - 1; k <= i + 1; k++) {

                        for (int l = j - 1; l <= j + 1; l++) {

                            if (!validFields(k, l)) {
                                continue;
                            }

                            if (feld[k][l] == Integer.MIN_VALUE) {
                                int[][] newField = cloneField(feld);
                                newField[k][l] = value;
                                newField[i][j] = Integer.MIN_VALUE;

                                Status newState = (Status) this.clone(newField);
                                newState.MaxSpieler = !MaxSpieler;

                                states.add(newState);
                            } 
                            else if (feld[k][l] != Integer.MIN_VALUE && feld[k][l] != value) {
                                
                                int m = k + (k - i);
                                int n = l + (l - j);

                                if (!validFields(m, n)) {
                                    continue;
                                }

                                if (feld[m][n] == Integer.MIN_VALUE) {

                                    int[][] newField = cloneField(feld);
                                    newField[k][l] = Integer.MIN_VALUE;
                                    newField[i][j] = Integer.MIN_VALUE;
                                    newField[m][n] = value;
                                    Status newState = (Status) this.clone(newField);

                                    if (isMaxSpieler()) {
                                        newState.minSteine--;
                                    } else {
                                        newState.maxSteine--;
                                    }

                                    newState.MaxSpieler = !MaxSpieler;

                                    states.add(newState);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public List<Status> nextStates() {
        if (!initialized) {
            if (tiefe <= MAX_TIEFE) {
                generateNextStates();
            }
            initialized = true;
        }
        return states;
    }

    private boolean validFields(int x, int y) {
        if (x < 0 || x > feld.length - 1) {
            return false;
        }

        if (y < 0 || y > feld[x].length - 1) {
            return false;
        }

        return true;
    }

    public boolean isTerminal() {

        if (maxSteine == 0 || minSteine == 0) {
            return true;
        }
        if (this.nextStates().isEmpty()) {
            return true;
        }
        return false;
    }

    public int utilityValue() {
        if (isMaxSpieler()) {
            return -1000;
        } else {
            return 1000;
        }
    }

    public int heuristicValue() {
        
        
        int heuristicVal = 0;
        int symbol = 0;
        int steine = minSteine;
        if (isMaxSpieler()) {
            symbol = 1;
            steine = maxSteine;
        }
        
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 4; y++) {
                if (symbol == feld[x][y] && (x == 0 || x == 4 || y == 0 || y == 4))
                    heuristicVal+=3;
                else if (symbol == feld[x][y] && (x == 1 || x == 3 || y == 1 || y == 3))
                    heuristicVal+=2;
                else if (symbol == feld[x][y])
                    heuristicVal+=1;
                else
                    heuristicVal+=0;
            }
        }
        heuristicVal = heuristicVal / steine;
        return heuristicVal + (maxSteine - minSteine)*2;
    }

    public boolean isMaxSpieler() {
        return MaxSpieler;
    }

    public int[][] cloneField(int[][] copyField) {
        int[][] newField = new int[5][5];
        for (int i = 0; i < copyField.length; i++) {
            System.arraycopy(copyField[i], 0, newField[i], 0, copyField[i].length);
        }
        return newField;
    }

    public Status clone() {
        Status state = new Status();
        state.MaxSpieler = this.MaxSpieler;
        state.feld = cloneField(this.feld);
        state.maxSteine = this.maxSteine;
        state.minSteine = this.minSteine;
        state.tiefe = this.tiefe + 1;
        state.initialized = false;
        return state;
    }

    public Status clone(int[][] field) {
        Status state = new Status();
        state.MaxSpieler = this.MaxSpieler;
        state.feld = cloneField(field);
        state.maxSteine = this.maxSteine;
        state.minSteine = this.minSteine;
        state.tiefe = this.tiefe + 1;
        state.initialized = false;
        return state;
    }

    public int getValue() {
        if (isTerminal()) {
            if (maxSteine == 0 || minSteine == 0) {
                return utilityValue();
            } else {
                if (this.nextStates().isEmpty()) {
                    return heuristicValue();
                } else {
                    int val = (isMaxSpieler() ? Integer.MIN_VALUE : Integer.MAX_VALUE);
                    for (Status s : states) {
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

    public int getDepth() {
        return this.tiefe;
    }

    public void setDepth(int depth) {
        this.tiefe = depth;
        generateNextStates();
    }

    public void print() {
        for (int i = 0; i < feld.length; i++) {
            for (int k = 0; k < 21; k++) {
                System.out.print("\u2015");
            }
            System.out.print("\n");
            System.out.print("|");
            for (int j = 0; j < feld[i].length; j++) {

                String c = "   |";

                if (feld[i][j] == 0) {
                    c = " S |";
                } else if (feld[i][j] == 1) {
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
        if (maxSteine == 0 || minSteine == 0) {
            System.out.println((!isMaxSpieler() ? "Schwarz" : "Weiss") + " hat gewonnen!");
        } else {
            System.out.println("Nächster Zug von " + (isMaxSpieler() ? "Schwarz:" : "Weiss:"));
        }
    }

}
