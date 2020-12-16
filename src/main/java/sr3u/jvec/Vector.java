package sr3u.jvec;

import java.io.Serializable;

// A class to represent vector for vectorised math
public interface Vector extends Serializable {

    CalculatedVector calculate();

    Vector copy();

    int size();

}
