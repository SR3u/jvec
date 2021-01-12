package sr3u.jvec.benchmark;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import sr3u.jvec.JMath;
import sr3u.jvec.Matrix;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/*
 *
 * Results on my machine (Java implementation, AMD Ryzen 7 3800X, 8 cores 16 threads, 3.9 GHz, boost to 4.2 GHz):
 *
 *    @BeforeClass machineInfo
 *      CPU: amd64 cores (threads): 16
 *      OS: Windows 10 version 10.0
 *      Java: 15.0.1 Oracle Corporation
 *    @Test add 1.39 ms
 *    @Test sub 1.74 ms
 *    @Test hadamardProduct 1.317 ms
 *    @Test multiply 39.955 ms
 *    @Test transpose 0.001 ms
 *
 */

@Ignore
public class DefaultMathMatrixBenchmark {
    private static final Matrix.Size SIZE = new Matrix.Size(500);
    private static final int TIMES = 1000;
    private static final JMath math = JMath.get();

    private final Random random = new Random();

    @BeforeClass
    public static void machineInfo() throws ClassNotFoundException {
        System.out.println(getMethodName(BeforeClass.class));
        System.out.println("    CPU: " + System.getProperty("os.arch") + " cores (threads): " + Runtime.getRuntime().availableProcessors());
        System.out.println("    OS: " + System.getProperty("os.name") + " version " + System.getProperty("os.version"));
        System.out.println("    Java: " + System.getProperty("java.version") + " " + System.getProperty("java.vendor"));
    }

    @Test
    public void transpose() {
        Matrix m = randomMatrix();
        printBenchmark(m::t);
    }

    @Test
    public void multiply() {
        Matrix m = randomMatrix();
        Matrix n = randomMatrix();
        printBenchmark(() -> m.mul(n));
    }

    @Test
    public void hadamardProduct() {
        Matrix m = randomMatrix();
        Matrix n = randomMatrix();
        printBenchmark(() -> m.hadamardProduct(n));
    }

    @Test
    public void add() {
        Matrix m = randomMatrix();
        Matrix n = randomMatrix();
        printBenchmark(() -> m.add(n));
    }

    @Test
    public void sub() {
        Matrix m = randomMatrix();
        Matrix n = randomMatrix();
        printBenchmark(() -> m.sub(n));
    }

    private void printBenchmark(Runnable t) {
        printResult(benchmark(t));
    }

    private void printResult(double timeMs) {
        String methodName;
        try {
            methodName = getMethodName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            methodName = e.getMessage();
        }
        System.out.println(methodName + " " + timeMs + " ms");
    }

    private static String getMethodName() throws ClassNotFoundException {
        return getMethodName(Test.class);
    }

    private static String getMethodName(final Class<? extends Annotation> annotationClass) throws ClassNotFoundException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : stackTrace) {
            Class<?> aClass = Class.forName(e.getClassName());
            if (Arrays.stream(aClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals(e.getMethodName()))
                    .anyMatch(m -> m.getAnnotation(annotationClass) != null)) {
                return "@" + annotationClass.getSimpleName() + " " + e.getMethodName();
            }
        }
        return "<unknown>";
    }

    private double benchmark(Runnable r) {
        final long start = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            r.run();
        }
        final long end = new Date().getTime();
        return (end - start) * 1.0 / TIMES;
    }

    private Matrix randomMatrix() {
        return randomMatrix(SIZE);
    }

    private Matrix randomMatrix(Matrix.Size size) {
        return math.mat(size, randomArray(size.size()));
    }

    private double[] randomArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextDouble() * 1000;
        }
        return arr;
    }

}
