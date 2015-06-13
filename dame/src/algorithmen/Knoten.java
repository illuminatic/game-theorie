package algorithmen;

import spiel.Status;

public abstract class Knoten<T> {

    public Knoten(Status status) {
        this.setState(status);
    }

    public Knoten(Status status, T val) {
        this.setState(status);
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

    private Status state;
    private T data;
}
