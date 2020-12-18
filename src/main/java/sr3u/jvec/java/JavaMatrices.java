package sr3u.jvec.java;

import sr3u.jvec.Matrices;
import sr3u.jvec.Matrix;
import sr3u.jvec.SquareMatrix;

public class JavaMatrices extends Matrices {


    public JavaMatrices(JavaMath javaMath) {
        super(javaMath);
    }

    @Override
    public SquareMatrix square(int size, double[] data) {
        return new JavaSquareMatrix(math().matrix(new Matrix.Size(size, size), data));
    }

    private JavaMath math() {
        return (JavaMath) math;
    }

}
