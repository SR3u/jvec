package sr3u.jvec;

import java.util.stream.IntStream;

public abstract class JMath {

    Vectors vectors = new Vectors(this);
    Matrices matrices = new Matrices(this);

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

    public Matrices mat() {
        return matrix();
    }

    public Matrices matrix() {
        return matrices();
    }

    public Matrices matrices() {
        return matrices;
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

    public abstract Matrix matrix(Matrix.Size size, double[] data);

    public Matrix matrix(Matrix.Size size, double fill) {
        return mat(size, IntStream.range(0, size.size()).mapToDouble(i -> fill).toArray());
    }

    public Matrix mat(Matrix.Size size, double[] data) {
        return matrix(size, data);
    }

    public Matrix mat(Matrix.Size size, double fill) {
        return matrix(size, fill);
    }

    public Matrix matr(Matrix.Size size, double[] data) {
        return matrix(size, data);
    }

    public Matrix matr(Matrix.Size size, double fill) {
        return matrix(size, fill);
    }

    public Vector convert(Vector v) {
        return vector(v.calculate().data());
    }

    public Matrix convert(Matrix v) {
        return matrix(v.size(), v.calculate().data());
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

    public Matrix add(double a, Matrix b) {
        return b.add(a);
    }

    public Matrix sub(double a, Matrix b) {
        return matrix(b.size(), a).sub(b);
    }

    public Matrix mul(double a, Matrix b) {
        return b.mul(a);
    }

    public Matrix div(double a, Matrix b) {
        return matrix(b.size(), a).div(b);
    }

}
