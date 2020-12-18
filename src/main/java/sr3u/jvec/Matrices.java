package sr3u.jvec;

public abstract class Matrices {
    protected final JMath math;

    public Matrices(JMath math) {
        this.math = math;
    }

    public SquareMatrix E(int size) {
        return diagonal(size, 1);
    }

    public SquareMatrix diag(int size, double fill) {
        return diagonal(size, fill);
    }

    public SquareMatrix diag(double... data) {
        return diagonal(data);
    }

    public abstract SquareMatrix square(int size, double[] data);

    public SquareMatrix diagonal(int size, double fill) {
        double[] data = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i * size + j] = 0.0;
            }
            data[i * size + i] = fill;
        }
        return square(size, data);
    }


    public SquareMatrix diagonal(double... data) {
        double[] mat = new double[data.length * data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                mat[i * data.length + j] = 0.0;
            }
            mat[i * data.length + i] = data[i];
        }
        return square(data.length, mat);
    }

}
