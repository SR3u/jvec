package sr3u.jvec;

import java.io.Serializable;

// A class to represent vector for vectorised math
public interface Vector extends Serializable {

    CalculatedVector calculate();

    Vector copy();

    int size();

    JMath math();

    // default methods
    default double sum() {
        return math().vec().sum(this);
    }

    default Vector add(Vector b) {
        return math().vec().add(this, b);
    }

    default Vector sub(Vector b) {
        return math().vec().sub(this, b);
    }

    default Vector add(double b) {
        return math().vec().add(this, b);
    }

    default Vector sub(double b) {
        return math().vec().add(this, b);
    }

    default Vector mul(Vector b) {
        return math().vec().mul(this, b);
    }

    default Vector mul(double b) {
        return math().vec().mul(this, b);
    }

    default Vector div(Vector b) {
        return math().vec().div(this, b);
    }

    default Vector div(double b) {
        return math().vec().div(this, b);
    }

    default Vector exp() {
        return math().vec().exp(this);
    }

    default Vector sigmoid() {
        return math().vec().sigmoid(this);
    }

    default double magnitude() {
        return math().vec().magnitude(this);
    }

    default double magnitudeSq() {
        return math().vec().magnitudeSq(this);
    }

    default Vector invert() {
        return math().vec().invert(this);
    }

}
