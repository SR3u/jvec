package sr3u.jvec.java.matrices.accessors;

import sr3u.jvec.Matrix;
import sr3u.jvec.java.JavaMatrix;

public class InvertedColumns implements JavaMatrix.Accessor {
    private final Matrix.Size matrixSize;

    public InvertedColumns(Matrix.Size size) {
        this.matrixSize = size;
    }

    @Override
    public JavaMatrix.Index index(int row, int column) {
        return new JavaMatrix.Index(matrixSize.rows() - row - 1, column);
    }

    @Override
    public Matrix.Size size(int row, int column) {
        return new Matrix.Size(row, column);
    }
}
