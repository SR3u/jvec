package sr3u.jvec.java;

import sr3u.jvec.JMath;
import sr3u.jvec.VectorMath;

public class JavaMath implements JMath {
    private static final ThreadLocal<JavaMath> INSTANCE = ThreadLocal.withInitial(JavaMath::new);
    private final JavaVectorMath vector;

    public static JavaMath get() {
        return INSTANCE.get();
    }

    private JavaMath() {
        this.vector = new JavaVectorMath();
    }

    @Override
    public VectorMath vector() {
        return vector;
    }

}
