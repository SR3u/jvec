package sr3u.jvec.java.vectors;

import sr3u.jvec.CalculatedVector;
import sr3u.jvec.Vector;
import sr3u.jvec.java.JavaVector;

import java.util.stream.IntStream;

public class SingleVector implements JavaVector {
    private final int size;
    private final double fill;

    public SingleVector(int size, double fill) {
        this.fill = fill;
        this.size = size;
    }

    @Override
    public double get(int i) {
        return fill;
    }

    @Override
    public double sum() {
        return size * fill;
    }

    @Override
    public CalculatedVector calculate() {
        return new ArrayVector(IntStream.range(0, size)
                .mapToDouble(i -> fill)
                .toArray());
    }

    @Override
    public Vector copy() {
        return new SingleVector(size, fill);
    }

    @Override
    public int size() {
        return size;
    }
}
