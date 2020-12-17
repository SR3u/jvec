package sr3u.jvec.java.matrices.accessors;

import sr3u.jvec.Matrix;
import sr3u.jvec.java.JavaMatrix;

public class Transposed implements JavaMatrix.Accessor {

    public Transposed() {
    }

    @Override
    public JavaMatrix.Index index(int row, int column) {
        return new JavaMatrix.Index(column, row);
    }

    @Override
    public Matrix.Size size(int row, int column) {
        return new Matrix.Size(column, row);
    }
}