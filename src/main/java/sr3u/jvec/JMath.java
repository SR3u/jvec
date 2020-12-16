package sr3u.jvec;

import java.util.stream.IntStream;

public abstract class JMath {

    Vectors vectors = new Vectors(this);

    static JMath get() {
        return MathHolder.get();
    }

    public Vectors vectors() {
        return this.vectors;
    }

    public Vectors vector() {
        return vectors();
    }

    public Vectors vec() {
        return vectors();
    }

    public abstract Vector vector(double... array);

    public Vector vector(int size, double fill) {
        return vector(IntStream.range(0, size).mapToDouble(i -> fill).toArray());
    }

    public Vector vec(double... array) {
        return vector(array);
    }

    public Vector vec(int size, double fill) {
        return vector(size, fill);
    }

    public Vector convert(Vector v) {
        return vector(v.calculate().data());
    }

    public Vector add(double a, Vector b) {
        return b.add(a);
    }

    public Vector sub(double a, Vector b) {
        return vector(b.size(), a).sub(b);
    }

    public Vector mul(double a, Vector b) {
        return b.mul(a);
    }

    public Vector div(double a, Vector b) {
        return vector(b.size(), a).div(b);
    }

}
