package sr3u.jvec;

public class Matrices {
    private final JMath math;

    public Matrices(JMath math) {
        this.math = math;
    }

    public Matrix diag(int size, double fill) {
        return diagonal(size, fill);
    }

    public Matrix diagonal(int size, double fill) {
        double[] data = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i * size + j] = 0.0;
            }
            data[i * size + i] = fill;
        }
        return math.mat(new Matrix.Size(size, size), data);
    }

}
