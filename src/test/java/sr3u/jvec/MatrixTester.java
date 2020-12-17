package sr3u.jvec;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

@Ignore
public class MatrixTester {


    public static final Matrix.Size SIZE = new Matrix.Size(5, 5);
    private static final double EPSILON = 1e-10;
    private final JMath math;
    private final Matrix A;
    private final Matrix B;

    private final Matrix Ad;
    private final Matrix Bd;

    private final double a = 5;
    private final double b = 3;

    public MatrixTester(JMath math) {
        this.math = math;
        A = math.mat(SIZE, a);
        B = math.mat(SIZE, b);

        Ad = math.mat().diag(SIZE.rows, a);
        Bd = math.mat().diag(SIZE.rows, b).invertColumns();
    }


    //@Test
    public void print() {
        System.out.println(Bd);
    }

    @Test
    public void matrAddMatr() {
        Matrix r = Ad.add(Bd);
        System.out.println(r);
        assertAllEquals(r, a + b);
    }

    //@Test
    public void matrSubMatr() {
        assertAllEquals(A.sub(b), a - b);
    }

    private void assertAllEquals(Matrix m, double v) {
        Assert.assertTrue(Arrays.stream(m.calculate().data())
                .allMatch(d -> doubleEquals(d, v)));
    }

    private void assertAllNonZeroEquals(Matrix m, double v) {
        Assert.assertTrue(Arrays.stream(m.calculate().data())
                .filter(d -> Math.abs(d) > 0.0)
                .allMatch(d -> doubleEquals(d, v)));
    }

    private boolean doubleEquals(double d, double v) {
        return Math.abs(d - v) <= EPSILON;
    }
}
