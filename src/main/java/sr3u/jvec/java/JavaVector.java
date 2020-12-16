package sr3u.jvec.java;

import sr3u.jvec.CalculatedVector;
import sr3u.jvec.Vector;

import java.util.Arrays;

public class JavaVector implements CalculatedVector {
    private double[] array;

    public JavaVector(double[] array) {
        this.array = array;
    }

    public JavaVector(int size) {
        this(size, 0.0);
    }

    public JavaVector(int size, double fill) {
        this.array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = fill;
        }
    }

    @Override
    public Vector copy() {
        return new JavaVector(array);
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

    public double[] get() {
        return array;
    }


    public double get(int i) {
        return array[i];
    }

    public void set(int i, double value) {
        array[i] = value;
    }

    public void set(double[] array) {
        this.array = array;
    }

}
