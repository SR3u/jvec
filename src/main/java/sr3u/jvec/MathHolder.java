package sr3u.jvec;

import sr3u.jvec.java.JavaMath;
import sr3u.jvec.nd4j.Nd4jMath;

public class MathHolder {

    private static final ThreadLocal<JMath> INSTANCE = ThreadLocal.withInitial(MathHolder::create);

    public static JMath get() {
        return INSTANCE.get();
    }

    private static JMath create() {
        try {
            Nd4jMath nd4jMath = Nd4jMath.get();
            if (nd4jMath != null) {
                return nd4jMath;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return JavaMath.get();
    }

}
