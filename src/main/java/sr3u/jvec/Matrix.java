package sr3u.jvec;

public interface Matrix {

    JMath math();

    Matrix copy();

    Size size();

    CalculatedMatrix calculate();

    Matrix add(Matrix b);

    Matrix sub(Matrix b);

    Matrix mul(Matrix b);

    Matrix div(Matrix b);


    default Matrix add(double b) {
        return add(math().mat(size(), b));
    }

    default Matrix sub(double b) {
        return sub(math().mat(size(), b));
    }

    default Matrix mul(double b) {
        return mul(math().mat(size(), b));
    }

    default Matrix div(double b) {
        return div(math().mat(size(), b));
    }

    default Matrix t() {
        return transpose();
    }

    default Matrix transpose() {
        return transposed();
    }

    Matrix transposed();

    Matrix invertRows();

    Matrix invertColumns();


    class Size {
        protected final int rows;
        protected final int columns;

        public Size(Size size) {
            this(size.rows, size.columns);
        }

        public int rows() {
            return rows;
        }

        public int columns() {
            return columns;
        }

        public Size(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }

        public int size() {
            return rows * columns;
        }

        @Override
        public String toString() {
            return rows + "x" + columns;
        }
    }

}