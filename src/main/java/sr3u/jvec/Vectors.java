package sr3u.jvec;

public class Vectors {

    private final JMath math;

    public Vectors(JMath math) {
        this.math = math;
    }

    public Vector zeros(int size) {
        return math.vec(size, 0.0);
    }

    public Vector ones(int size) {
        return math.vec(size, 1.0);
    }

}
