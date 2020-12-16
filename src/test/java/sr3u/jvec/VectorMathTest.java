package sr3u.jvec;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class VectorMathTest {

    protected static final int SIZE = 4;
    public static final double EPSILON = 1e-10;
    private static final VectorMath math = VectorMath.get();
    protected static final double a = 7;
    protected static final double b = 3;
    protected static final Vector A = getA();
    protected static final Vector B = getB();

    private static Vector getB() {
        return getVector(b);
    }

    private static Vector getA() {
        return getVector(a);
    }

    private static Vector getVector(double fill) {
        return math.vector(SIZE, fill);
    }

    private void assertAllEquals(double expected, Vector actual) {
        Assert.assertTrue(Arrays.stream(actual.calculate().data())
                .allMatch(d -> doubleEquals(expected, d)));
    }

    private boolean doubleEquals(double a, double b) {
        return Math.abs(b - a) <= EPSILON;
    }

    private void assertAdd(Vector add) {
        assertAllEquals(a + b, add);
    }

    private void assertSub(Vector sub) {
        assertAllEquals(a - b, sub);
    }

    private void assertMul(Vector mul) {
        assertAllEquals(a * b, mul);
    }

    private void assertDiv(Vector div) {
        assertAllEquals(a / b, div);
    }

    @Test
    public void vecAddVec() {
        assertAdd(math.add(A, B));
    }

    @Test
    public void vecSubVec() {
        assertSub(math.sub(A, B));
    }

    @Test
    public void vecMulVec() {
        assertMul(math.mul(A, B));
    }

    @Test
    public void vecDivVec() {
        assertDiv(math.div(A, B));
    }

    @Test
    public void vecAddNum() {
        assertAdd(math.add(A, b));
    }

    @Test
    public void vecSubNum() {
        assertSub(math.sub(A, b));
    }

    @Test
    public void vecMulNum() {
        assertMul(math.mul(A, b));
    }

    @Test
    public void vecDivNum() {
        assertDiv(math.div(A, b));
    }

    @Test
    public void numAddVec() {
        assertAdd(math.add(a, B));
    }

    @Test
    public void numSubVec() {
        assertSub(math.sub(a, B));
    }

    @Test
    public void numMulVec() {
        assertMul(math.mul(a, B));
    }

    @Test
    public void numDivVec() {
        assertDiv(math.div(a, B));
    }

}