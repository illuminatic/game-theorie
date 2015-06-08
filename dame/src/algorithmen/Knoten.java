package algorithmen;

import spiel.Status;

public abstract class Knoten<T> {

    public Knoten(Status state) {
        this.setState(state);
    }

    public Knoten(Status state, T val) {
        this.setState(state);
        this.setData(val);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    abstract Knoten<T> nextNode();

    abstract T getValue();

    abstract boolean solve();

    private Status state;
    private T data;
}
