package sr3u.jvec.java.matrices.accessors;

import sr3u.jvec.Matrix;
import sr3u.jvec.java.JavaMatrix;

public class InvertedRows implements JavaMatrix.Accessor {
    private final Matrix.Size matrixSize;

    public InvertedRows(Matrix.Size size) {
        this.matrixSize = size;
    }

    @Override
    public JavaMatrix.Index index(int row, int column) {
        return new JavaMatrix.Index(row, matrixSize.columns() - column - 1);
    }

    @Override
    public Matrix.Size size(int row, int column) {
        return new Matrix.Size(row, column);
    }
}