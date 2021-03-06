package sr3u.jvec.java.matrices;

import sr3u.jvec.CalculatedMatrix;
import sr3u.jvec.java.JavaMatrix;

public class ArrayMatrix extends JavaMatrix {

    double[] data;

    public ArrayMatrix(Size size) {
        this(size, new double[size.size()]);
    }

    public ArrayMatrix(Size size, double[] data) {
        super(ACCESSOR_REGULAR, size);
        this.data = data;
    }

    protected ArrayMatrix(Accessor accessor, JavaMatrix.JavaSize size, double[] data) {
        super(accessor, size);
        this.data = data;
    }

    @Override
    protected ArrayMatrix copyWithAccessor(Accessor accessor) {
        return new ArrayMatrix(accessor, size, data);
    }

    public double[][] data2d() {
        double[][] result = new double[size().rows()][size().columns()];
        loop((r, c) -> result[r][c] = get(r, c));
        return result;
    }

    @Override
    protected double getRaw(int r, int c) {
        return data[r * size.raw.columns() + c];
    }

    @Override
    public void setRaw(int r, int c, double value) {
        data[r * size.raw.columns() + c] = value;
    }

    @Override
    protected JavaMatrix matrixWithSameSize() {
        return new ArrayMatrix(size());
    }

    @Override
    public ArrayMatrix copy() {
        CalculatedMatrix calculate = calculate();
        return new ArrayMatrix(calculate.size(), calculate.data());
    }

    @Override
    public CalculatedMatrix calculate() {
        return new CalculatedArrayMatrix(this);
    }


}
