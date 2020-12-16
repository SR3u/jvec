package sr3u.jvec;

public interface JMath {

    static JMath get() {
        return MathHolder.get();
    }

    VectorMath vector();

    default VectorMath vec() {
        return vector();
    }

}
