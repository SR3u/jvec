package sr3u.jvec;

public interface SquareMatrix extends Matrix {

    SquareMatrix inverse();

    double determinant();

    default double det() {
        return determinant();
    }

}