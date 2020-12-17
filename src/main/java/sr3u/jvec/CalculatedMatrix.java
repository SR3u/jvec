package sr3u.jvec;

public interface CalculatedMatrix extends Matrix {

    double[][] data2d();

    double[] data();

    @Override
    CalculatedMatrix copy();

    @Override
    default CalculatedMatrix calculate() {
        return this;
    }

}
