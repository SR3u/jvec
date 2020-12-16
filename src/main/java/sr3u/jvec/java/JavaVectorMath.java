package sr3u.jvec.java;

import sr3u.jvec.Vector;
import sr3u.jvec.VectorMath;
import sr3u.jvec.java.vectors.ArrayVector;
import sr3u.jvec.java.vectors.SingleVector;

public class JavaVectorMath implements VectorMath {

    private static final ThreadLocal<JavaVectorMath> INSTANCE = ThreadLocal.withInitial(JavaVectorMath::new);

    public static JavaVectorMath get() {
        return INSTANCE.get();
    }

    @Override
    public JavaVector vector(double... array) {
        return new ArrayVector(array);
    }

    @Override
    public Vector vector(int size, double fill) {
        return new SingleVector(size, fill);
    }


    @Override
    public JavaVector convert(Vector v) {
        if (v instanceof JavaVector) {
            return (JavaVector) v;
        }
        return vector(v.calculate().data());
    }

    @Override
    public Vector add(Vector a, Vector b) {
        JavaVector A = convert(a);
        JavaVector B = convert(b);
        return new ArrayVector(resultVectorSize(A, B), (i) -> A.get(i) + B.get(i));
    }

    @Override
    public Vector sub(Vector a, Vector b) {
        JavaVector A = convert(a);
        JavaVector B = convert(b);
        return new ArrayVector(resultVectorSize(A, B), (i) -> A.get(i) - B.get(i));
    }

    @Override
    public Vector add(Vector a, double b) {
        JavaVector A = convert(a);
        return new ArrayVector(resultVectorSize(A), (i) -> A.get(i) + b);
    }

    @Override
    public Vector sub(Vector a, double b) {
        JavaVector A = convert(a);
        return new ArrayVector(resultVectorSize(A), (i) -> A.get(i) - b);
    }

    @Override
    public Vector sub(double a, Vector b) {
        JavaVector B = convert(b);
        return new ArrayVector(resultVectorSize(B), (i) -> a - B.get(i));
    }

    @Override
    public Vector mul(Vector a, Vector b) {
        JavaVector A = convert(a);
        JavaVector B = convert(b);
        return new ArrayVector(resultVectorSize(A, B), (i) -> A.get(i) * B.get(i));
    }

    @Override
    public Vector mul(Vector a, double b) {
        JavaVector A = convert(a);
        return new ArrayVector(resultVectorSize(A), (i) -> A.get(i) * b);
    }

    @Override
    public Vector div(Vector a, Vector b) {
        JavaVector A = convert(a);
        JavaVector B = convert(b);
        return new ArrayVector(resultVectorSize(A, B), (i) -> A.get(i) / B.get(i));
    }

    @Override
    public Vector div(double a, Vector b) {
        JavaVector B = convert(b);
        return new ArrayVector(resultVectorSize(B), (i) -> a / B.get(i));
    }

    @Override
    public Vector div(Vector a, double b) {
        JavaVector A = convert(a);
        return new ArrayVector(resultVectorSize(A), (i) -> A.get(i) / b);
    }

    @Override
    public JavaVector exp(Vector a) {
        JavaVector A = convert(a);
        return new ArrayVector(resultVectorSize(A), (i) -> Math.exp(A.get(i)));
    }

    @Override
    public double sum(Vector a) {
        return convert(a).sum();
    }

    private int resultVectorSize(JavaVector a) {
        return a.size();
    }

    private int resultVectorSize(JavaVector a, JavaVector b) {
        assertEqualSize(a, b);
        return resultVectorSize(a);
    }

    private void assertEqualSize(JavaVector a, JavaVector b) {
        if (a.size() != b.size()) {
            try {
                throw new IllegalArgumentException("Expected equal sized vectors!");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
