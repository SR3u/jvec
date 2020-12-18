package sr3u.jvec.java;

import sr3u.jvec.CalculatedMatrix;

public class JavaCalculatedSquareMatrix extends JavaSquareMatrix implements CalculatedMatrix {
    private final CalculatedMatrix calculatedMatrix;

    public JavaCalculatedSquareMatrix(JavaMatrix matrix) {
        this(matrix, matrix.calculate());
    }

    public JavaCalculatedSquareMatrix(JavaMatrix matrix, CalculatedMatrix calculatedMatrix) {
        super(matrix);
        this.calculatedMatrix = calculatedMatrix;
    }

    @Override
    public double[][] data2d() {
        return calculatedMatrix.data2d();
    }

    @Override
    public double[] data() {
        return calculatedMatrix.data();
    }

    @Override
    public JavaCalculatedSquareMatrix copy() {
        return new JavaCalculatedSquareMatrix(matrix, calculatedMatrix);
    }

}
