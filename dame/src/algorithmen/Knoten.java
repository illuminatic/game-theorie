package algorithmen;

import status.SpielStatus;

public abstract class Knoten<T> {

    public Knoten(SpielStatus state) {
        this.setState(state);
    }

    public Knoten(SpielStatus state, T val) {
        this.setState(state);
        this.setData(val);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SpielStatus getState() {
        return state;
    }

    public void setState(SpielStatus state) {
        this.state = state;
    }

    abstract Knoten<T> nextNode();

    abstract T getValue();

    abstract boolean solve();

    private SpielStatus state;
    private T data;
}
