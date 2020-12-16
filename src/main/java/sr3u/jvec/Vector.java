package sr3u.jvec;

import java.io.Serializable;

// A class to represent vector for vectorised math
public interface Vector extends Serializable {

    CalculatedVector calculate();

    Vector copy();

    int size();

    JMath math();

    double sum();

    Vector add(Vector b);

    Vector sub(Vector b);

    Vector mul(Vector b);

    Vector div(Vector b);

    double magnitudeSq();

    Vector exp();

    // default methods

    default Vector add(double b) {
        return add(math().vector(size(), b));
    }

    default Vector sub(double b) {
        return sub(math().vector(size(), b));
    }

    default Vector mul(double b) {
        return mul(math().vector(size(), b));
    }

    default Vector div(double b) {
        return div(math().vector(size(), b));
    }

    default double magnitude() {
        return Math.sqrt(magnitudeSq());
    }

}
