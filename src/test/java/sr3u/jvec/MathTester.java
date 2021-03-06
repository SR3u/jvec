package sr3u.jvec;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore
public class MathTester {

    private final JMath math;

    protected final int SIZE = 4;
    protected final double EPSILON = 1e-10;
    protected final double a = 7;
    protected final double b = 3;
    protected final Vector A;
    protected final Vector B;

    public MathTester(JMath math) {
        this.math = math;
        A = math.vector(SIZE, a);
        B = math.vector(SIZE, b);
    }

    public MathTester() {
        this(JMath.get());
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
        assertAdd(A.add(B));
    }

    @Test
    public void vecSubVec() {
        assertSub(A.sub(B));
    }

    @Test
    public void vecMulVec() {
        assertMul(A.mul(B));
    }

    @Test
    public void vecDivVec() {
        assertDiv(A.div(B));
    }

    @Test
    public void vecAddNum() {
        assertAdd(A.add(b));
    }

    @Test
    public void vecSubNum() {
        assertSub(A.sub(b));
    }

    @Test
    public void vecMulNum() {
        assertMul(A.mul(b));
    }

    @Test
    public void vecDivNum() {
        assertDiv(A.div(b));
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

    @Test
    public void magnitude() {
        Assert.assertEquals(Math.sqrt(a * a * A.size()), A.magnitude(), EPSILON);
    }

    @Test
    public void magnitudeSq() {
        Assert.assertEquals(a * a * A.size(), A.magnitudeSq(), EPSILON);
    }

    @Test
    public void magnitude2() {
        Assert.assertEquals(Math.sqrt((a + b) * (a + b) * A.size()), A.add(B).magnitude(), EPSILON);
    }

    @Test
    public void magnitudeSq2() {
        Assert.assertEquals((a + b) * (a + b) * A.size(), A.add(B).magnitudeSq(), EPSILON);
    }

}