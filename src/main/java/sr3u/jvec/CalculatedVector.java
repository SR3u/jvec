package sr3u.jvec;

public interface CalculatedVector extends Vector {

    double[] data();

    @Override
    CalculatedVector copy();

    @Override
    default CalculatedVector calculate() {
        return this;
    }

}
