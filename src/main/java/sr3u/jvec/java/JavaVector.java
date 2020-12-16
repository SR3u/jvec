package sr3u.jvec.java;

import sr3u.jvec.JMath;
import sr3u.jvec.Vector;

public interface JavaVector extends Vector {

    @Override
    default JMath math() {
        return JavaMath.get();
    }

    double get(int i);

    @Override
    double sum();

    @Override
    double magnitudeSq();

}
