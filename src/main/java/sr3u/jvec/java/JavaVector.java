package sr3u.jvec.java;

import sr3u.jvec.Vector;
import sr3u.jvec.java.vectors.Op;

public abstract class JavaVector implements Vector {

    @Override
    public JavaMath math() {
        return JavaMath.get();
    }

    public abstract double get(int i);

    @Override
    public abstract double sum();

    @Override
    public JavaVector add(Vector b) {
        JavaVector B = math().convert(b);
        return newInstance(resultVectorSize(this, B), (i) -> this.get(i) + B.get(i));
    }

    @Override
    public JavaVector sub(Vector b) {
        JavaVector B = math().convert(b);
        return newInstance(resultVectorSize(this, B), (i) -> this.get(i) - B.get(i));
    }

    @Override
    public JavaVector sub(double b) {
        return newInstance(resultVectorSize(this), (i) -> this.get(i) - b);
    }


    @Override
    public JavaVector mul(Vector b) {
        JavaVector B = math().convert(b);
        return newInstance(resultVectorSize(this, B), (i) -> this.get(i) * B.get(i));
    }

    @Override
    public JavaVector mul(double b) {
        return newInstance(resultVectorSize(this), (i) -> this.get(i) * b);
    }

    @Override
    public JavaVector div(Vector b) {
        JavaVector B = math().convert(b);
        return newInstance(resultVectorSize(this, B), (i) -> this.get(i) / B.get(i));
    }

    @Override
    public JavaVector div(double b) {
        return newInstance(resultVectorSize(this), (i) -> this.get(i) / b);
    }

    @Override
    public JavaVector exp() {
        return newInstance(resultVectorSize(this), (i) -> Math.exp(this.get(i)));
    }

    protected abstract JavaVector newInstance(int size, Op op);

    static int resultVectorSize(JavaVector a) {
        return a.size();
    }

    static int resultVectorSize(JavaVector a, JavaVector b) {
        assertEqualSize(a, b);
        return resultVectorSize(a);
    }

    static void assertEqualSize(JavaVector a, JavaVector b) {
        if (a.size() != b.size()) {
            try {
                throw new IllegalArgumentException("Expected equal sized vectors!");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
