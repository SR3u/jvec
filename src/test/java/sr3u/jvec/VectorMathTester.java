package sr3u.jvec;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore
public class VectorMathTester {

    private final VectorMath math;

    protected final int SIZE = 4;
    protected final double EPSILON = 1e-10;
    protected final double a = 7;
    protected final double b = 3;
    protected final Vector A;
    protected final Vector B;

    public VectorMathTester(VectorMath math) {
        this.math = math;
        A = math.vector(SIZE, a);
        B = math.vector(SIZE, b);
    }

    public VectorMathTester() {
        this(VectorMath.get());
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

    @Test
    public void sum() {
        Assert.assertEquals(a * SIZE, A.sum(), EPSILON);
    }

}