package sr3u.jvec.java;

import sr3u.jvec.Vector;
import sr3u.jvec.VectorMath;

public interface JavaVector extends Vector {

    @Override
    default VectorMath math() {
        return JavaVectorMath.get();
    }

    double get(int i);

    @Override
    double sum();
}
