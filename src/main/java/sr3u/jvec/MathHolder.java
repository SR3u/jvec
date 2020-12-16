package sr3u.jvec;

import sr3u.jvec.java.JavaMath;

public class MathHolder {

    private static final ThreadLocal<JMath> INSTANCE = ThreadLocal.withInitial(MathHolder::create);

    public static JMath get() {
        return INSTANCE.get();
    }

    private static JMath create() {
        return JavaMath.get();
    }

}
