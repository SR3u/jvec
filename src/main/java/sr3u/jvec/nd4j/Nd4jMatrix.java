package sr3u.jvec.nd4j;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import sr3u.jvec.CalculatedMatrix;
import sr3u.jvec.Matrix;
import sr3u.jvec.java.JavaMath;
import sr3u.jvec.java.matrices.Op;

import java.util.stream.IntStream;

public class Nd4jMatrix implements Matrix, CalculatedMatrix {

    protected final INDArray matrix;

    public Nd4jMatrix(INDArray matrix) {
        this.matrix = matrix;
    }

    public Nd4jMatrix(Size size, double[] data) {
        this(createIndArray(size, data));
    }

    private static INDArray createIndArray(Size size, double[] data) {
        return Nd4j.create(data, size.rows(), size.columns());
    }

    @Override
    public Nd4jMath math() {
        return Nd4jMath.get();
    }

    @Override
    public double[][] data2d() {
        double[][] result = new double[size().rows()][size().columns()];
        loop((r, c) -> result[r][c] = matrix.getDouble(r, c));
        return result;
    }

    @Override
    public double[] data() {
        return matrix.data().asDouble();
    }

    @Override
    public Nd4jMatrix copy() {
        return new Nd4jMatrix(matrix);
    }

    @Override
    public Size size() {
        return new Size(matrix.rows(), matrix.columns());
    }

    @Override
    public CalculatedMatrix calculate() {
        return this;
    }

    @Override
    public Matrix add(Matrix b) {
        Nd4jMatrix B = math().convert(b);
        return new Nd4jMatrix(matrix.add(B.matrix));
    }

    @Override
    public Matrix sub(Matrix b) {
        Nd4jMatrix B = math().convert(b);
        return new Nd4jMatrix(matrix.sub(B.matrix));
    }

    @Override
    public Matrix mul(Matrix b) {
        Nd4jMatrix B = math().convert(b);
        return new Nd4jMatrix(matrix.mmul(B.matrix));
    }

    @Override
    public Matrix mulScalar(Matrix b) {
        Nd4jMatrix B = math().convert(b);
        return new Nd4jMatrix(matrix.mul(B.matrix));
    }

    @Override
    public Matrix divScalar(Matrix b) {
        Nd4jMatrix B = math().convert(b);
        return new Nd4jMatrix(matrix.div(B.matrix));
    }

    @Override
    public Matrix transposed() {
        return new Nd4jMatrix(matrix.transpose());
    }

    @Override
    public Matrix invertRows() {
        return JavaMath.get().mat(size(), data()).invertRows(); // TODO Implement using ND4J
    }

    @Override
    public Matrix invertColumns() {
        return JavaMath.get().mat(size(), data()).invertColumns(); // TODO Implement using ND4J
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Matrix) {
            return math().matrixEquals(this, (Matrix) o);
        }
        return false;
    }

    @Override
    public boolean equals(Matrix other, double epsilon) {
        Nd4jMatrix B = math().convert(other);
        return matrix.equalsWithEps(B.matrix, epsilon);
    }

    protected void loop(Op op) {
        IntStream.range(0, size().rows())
                .parallel()
                .forEach(r -> IntStream.range(0, size().columns())
                        .parallel()
                        .forEach(c -> op.accept(r, c)));
    }
}
