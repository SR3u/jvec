package sr3u.jvec;

import java.io.Serializable;

// A class to represent vector for vectorised math
public interface Vector extends Serializable {

    CalculatedVector calculate();

    Vector copy();

    int size();

    VectorMath math();

    // default methods
    default double sum() {
        return math().sum(this);
    }

    default Vector add(Vector b) {
        return math().add(this, b);
    }

    default Vector sub(Vector b) {
        return math().sub(this, b);
    }

    default Vector add(double b) {
        return math().add(this, b);
    }

    default Vector sub(double b) {
        return math().add(this, b);
    }

    default Vector mul(Vector b) {
        return math().mul(this, b);
    }

    default Vector mul(double b) {
        return math().mul(this, b);
    }

    default Vector div(Vector b) {
        return math().div(this, b);
    }

    default Vector div(double b) {
        return math().div(this, b);
    }

    default Vector exp() {
        return math().exp(this);
    }

    default Vector sigmoid() {
        return math().sigmoid(this);
    }

}
