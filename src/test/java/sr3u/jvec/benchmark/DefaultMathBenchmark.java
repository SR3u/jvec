package sr3u.jvec.benchmark;

import org.junit.Ignore;
import org.junit.Test;
import sr3u.jvec.JMath;
import sr3u.jvec.Matrix;

import java.util.Date;

@Ignore
public class DefaultMathBenchmark {
    private static final Matrix.Size SIZE = new Matrix.Size(500);
    private static final int TIMES = 100;
    private static final JMath math = JMath.get();

    @Test
    public void transpose() {
        Matrix m = math.mat(SIZE, 1.0);
        printBenchmark(m::t);
    }

    @Test
    public void multiply() {
        Matrix m = math.mat(SIZE, 1.0);
        Matrix n = math.mat(SIZE, 2.0);
        printBenchmark(() -> m.mul(n));
    }

    private void printBenchmark(Runnable t) {
        printResult(benchmark(t));
    }

    private void printResult(double timeMs) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.out.println(stackTraceElement.getMethodName() + " " + timeMs + " ms");
    }

    private double benchmark(Runnable r) {
        final long start = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            r.run();
        }
        final long end = new Date().getTime();
        return (end - start) * 1.0 / TIMES;
    }

}
