package sr3u.jvec.java;

import sr3u.jvec.CalculatedMatrix;
import sr3u.jvec.SquareMatrix;
import sr3u.jvec.java.matrices.Cop;

public class JavaSquareMatrix extends JavaMatrix implements SquareMatrix {

    protected final JavaMatrix matrix;

    public JavaSquareMatrix(JavaMatrix matrix) {
        this(matrix, matrix.accessor());
    }

    public JavaSquareMatrix(JavaMatrix matrix, Accessor accessor) {
        super(accessor, matrix.size());
        this.matrix = matrix;
    }

    @Override
    protected JavaSquareMatrix copyWithAccessor(Accessor accessor) {
        return new JavaSquareMatrix(matrix, accessor);
    }

    @Override
    protected JavaSquareMatrix matrixWithSameSize() {
        return new JavaSquareMatrix(matrix.matrixWithSameSize());
    }

    @Override
    protected double getRaw(int r, int c) {
        return matrix.getRaw(r, c);
    }

    @Override
    protected void setRaw(int r, int c, double value) {
        matrix.setRaw(r, c, value);
    }

    @Override
    public JavaSquareMatrix copy() {
        return new JavaSquareMatrix(matrix.math().convert(matrix.copy()));
    }

    @Override
    public CalculatedMatrix calculate() {
        return new JavaCalculatedSquareMatrix(matrix);
    }

    @Override
    public SquareMatrix inverse() {
        final double det = det();
        return matrixWithSameSize().loop((r, c) -> alcComp(c, r) / det);
    }

    private double alcComp(int c, int r) { // TODO
        return get(c, r);
    }

    @Override
    protected JavaSquareMatrix loop(Cop op) {
        super.loop(op);
        return this;
    }
}
