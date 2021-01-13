package sr3u.jvec;

import java.util.Objects;

public interface Matrix {

    JMath math();

    Matrix copy();

    Size size();

    CalculatedMatrix calculate();

    Matrix add(Matrix b);

    Matrix sub(Matrix b);

    Matrix mul(Matrix b);

    Matrix mulScalar(Matrix b);

    Matrix divScalar(Matrix b);

    default Matrix hadamardProduct(Matrix b) {
        return mulScalar(b);
    }

    default Matrix hadamardDiv(Matrix b) {
        return divScalar(b);
    }


    default Matrix add(double b) {
        return add(math().mat(size(), b));
    }

    default Matrix sub(double b) {
        return sub(math().mat(size(), b));
    }

    default Matrix mul(double b) {
        return mulScalar(math().mat(size(), b));
    }

    default Matrix div(double b) {
        return divScalar(math().mat(size(), b));
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

    Matrix exp();

    default void assertSizesForMul(Matrix other) {
        if (this.size().columns() != other.size().rows()) {
            throw new IllegalArgumentException("Invalid matrix sizes for multiplication " + this.size() + " * " + other.size());
        }
    }

    default void assertSizesEqual(Matrix other) {
        if (this.size().columns() != other.size().columns() &&
                this.size().rows() != other.size().rows()) {
            throw new IllegalArgumentException("Invalid matrix sizes " + this.size() + " and " + other.size());
        }
    }

    boolean equals(Matrix other, double epsilon);

    class Size {
        protected final int rows;
        protected final int columns;

        public Size(Size size) {
            this(size.rows, size.columns);
        }

        public Size(double... rowAndColumn) {
            this((int) rowAndColumn[0], (int) rowAndColumn[1]);
        }

        public Size(double rowAndColumn) {
            this((int) rowAndColumn, (int) rowAndColumn);
        }

        public Size(int... rowAndColumn) {
            this(rowAndColumn[0], rowAndColumn[1]);
        }

        public Size(int rowAndColumn) {
            this(rowAndColumn, rowAndColumn);
        }

        public Size(Vector rowAndColumn) {
            this(rowAndColumn.calculate().data());
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof Size) {
                Size size = (Size) o;
                return rows() == size.rows() &&
                        columns() == size.columns();
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(rows, columns);
        }
    }

}
