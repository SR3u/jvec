package sr3u.jvec.java;

import sr3u.jvec.JMath;
import sr3u.jvec.Vector;
import sr3u.jvec.java.vectors.ArrayVector;
import sr3u.jvec.java.vectors.SingleVector;

public class JavaMath extends JMath {
    private static final ThreadLocal<JavaMath> INSTANCE = ThreadLocal.withInitial(JavaMath::new);

    public static JavaMath get() {
        return INSTANCE.get();
    }

    private JavaMath() {
    }

    @Override
    public JavaVector vector(double... array) {
        return new ArrayVector(array);
    }

    @Override
    public JavaVector vector(int size, double fill) {
        return new SingleVector(size, fill);
    }


    @Override
    public JavaVector convert(Vector v) {
        if (v instanceof JavaVector) {
            return (JavaVector) v;
        }
        return vector(v.calculate().data());
    }

  /*  @Override
    public JavaVector div(double a, Vector b) {
        JavaVector B = convert(b);
        return new ArrayVector(JavaVector.resultVectorSize(B), (i) -> a / B.get(i));
    }

    @Override
    public JavaVector sub(double a, Vector b) {
        JavaVector B = convert(b);
        return new ArrayVector(JavaVector.resultVectorSize(B), (i) -> a - B.get(i));
    }*/

}
