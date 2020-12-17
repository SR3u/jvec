package sr3u.jvec.java.matrices;

import sr3u.jvec.CalculatedMatrix;

public class CalculatedArrayMatrix extends ArrayMatrix implements CalculatedMatrix {
    public CalculatedArrayMatrix(ArrayMatrix arrayMatrix) {
        super(arrayMatrix.size());
        double[][] data2d = arrayMatrix.data2d();
        for (int i = 0; i < data2d.length; i++) {
            for (int j = 0; j < data2d[i].length; j++) {
                set(i, j, data2d[i][j]);
            }
        }
    }

    public CalculatedArrayMatrix(Size size, double[] data) {
        super(size, data);
    }

    @Override
    public CalculatedArrayMatrix calculate() {
        return this;
    }

    @Override
    public CalculatedArrayMatrix copy() {
        return new CalculatedArrayMatrix(size(), data);
    }

    @Override
    public double[] data() {
        return data;
    }

}
