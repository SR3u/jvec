package sr3u.jvec.java.vectors;

import sr3u.jvec.CalculatedVector;
import sr3u.jvec.java.JavaVector;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayVector extends JavaVector implements CalculatedVector {

    private final double[] array;

    public ArrayVector(double[] array) {
        this.array = array;
    }

    public ArrayVector(int size) {
        this(size, 0.0);
    }

    public ArrayVector(int size, double fill) {
        this.array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = fill;
        }
    }

    public ArrayVector(int size, Op op) {
        this(createArray(size, op));
    }

    @Override
    public CalculatedVector copy() {
        return new ArrayVector(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public double[] data() {
        return array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public double get(int i) {
        return array[i];
    }

    @Override
    public double sum() {
        return Arrays.stream(array).sum();
    }

    @Override
    public ArrayVector newInstance(int size, Op op) {
        return new ArrayVector(size, op);
    }

    private static double[] createArray(int size, Op op) {
        double[] array = new double[size];
        IntStream range = IntStream.range(0, size);
        if (size > 20000) {
            range = range.parallel();
        }
        range.forEach(i -> array[i] = op.apply(i));
        return array;
    }

    @Override
    public double magnitudeSq() {
        return new ArrayVector(createArray(size(), i -> array[i] * array[i])).sum();
    }

}
