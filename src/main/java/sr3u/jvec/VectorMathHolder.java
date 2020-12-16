package sr3u.jvec;

import sr3u.jvec.java.JavaVectorMath;

public class VectorMathHolder {

    private static final ThreadLocal<VectorMath> INSTANCE = ThreadLocal.withInitial(VectorMathHolder::create);

    public static VectorMath get() {
        return INSTANCE.get();
    }

    private static VectorMath create() {
        return JavaVectorMath.get();
    }

}
