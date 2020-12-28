package sr3u.jvec.java;

import sr3u.jvec.Matrix;
import sr3u.jvec.java.matrices.Cop;
import sr3u.jvec.java.matrices.Op;
import sr3u.jvec.java.matrices.accessors.InvertedColumns;
import sr3u.jvec.java.matrices.accessors.InvertedRows;
import sr3u.jvec.java.matrices.accessors.Regular;
import sr3u.jvec.java.matrices.accessors.Transposed;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public abstract class JavaMatrix implements Matrix {

    protected static final Accessor ACCESSOR_REGULAR = new Regular();
    protected static final Accessor ACCESSOR_TRANSPOSED = new Transposed();

    protected static final Set<Accessor> DEFAULT_ACCESSORS = new HashSet<>(
            Arrays.asList(ACCESSOR_REGULAR, ACCESSOR_TRANSPOSED)
    );


    private final Accessor accessor;
    private final JavaSize size;

    protected JavaMatrix(Accessor accessor, Size size) {
        this.accessor = accessor;
        this.size = new JavaSize(accessor, size);
    }

    public Index index(int row, int column) {
        return accessor.index(row, column);
    }

    @Override
    public JavaMatrix transposed() {
        if (ACCESSOR_REGULAR.equals(accessor)) {
            return copyWithAccessor(ACCESSOR_TRANSPOSED);
        } else if (ACCESSOR_TRANSPOSED.equals(accessor)) {
            return copyWithAccessor(ACCESSOR_REGULAR);
        } else {
            return copyWithAccessor(AccessorChain.of(accessor, ACCESSOR_TRANSPOSED));
        }
    }

    @Override
    public JavaMatrix invertColumns() {
        return copyWithAccessor(AccessorChain.of(accessor, new InvertedColumns(size())));
    }

    @Override
    public JavaMatrix invertRows() {
        return copyWithAccessor(AccessorChain.of(accessor, new InvertedRows(size())));
    }

    @Override
    public final Size size() {
        return size;
    }

    protected abstract JavaMatrix copyWithAccessor(Accessor accessor);

    protected double get(Index index) {
        return get(index.row, index.column);
    }

    protected void set(Index index, double value) {
        set(index.row, index.column, value);
    }

    protected double get(int r, int c) {
        Index index = accessor.index(r, c);
        return getRaw(index.row, index.column);
    }

    protected void set(int r, int c, double value) {
        Index index = accessor.index(r, c);
        setRaw(index.row, index.column, value);
    }

    protected abstract double getRaw(int r, int c);

    protected abstract void setRaw(int r, int c, double value);

    public Accessor getAccessor() {
        return accessor;
    }

    public static final class Index {
        public final int row;
        public final int column;

        public Index(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public static Index regular(int row, int column) {
            return new Index(row, column);
        }

        public static Index transposed(int row, int column) {
            return new Index(column, row);
        }

        public static Index invertRows(int row, int column, Supplier<Size> size) {
            return new Index(size.get().rows() - row, column);
        }

        public static Index invertColumns(int row, int column, Supplier<Size> size) {
            return new Index(row, size.get().columns() - column);
        }
    }


    public interface Accessor {
        Index index(int row, int column);

        Size size(int row, int column);
    }

    private static class JavaSize extends Size {
        public JavaSize(Accessor accessor, Size size) {
            super(getSize(accessor, size));
        }

        private static Size getSize(Accessor accessor, Size size) {
            return accessor.size(size.rows(), size.columns());
        }
    }

    @Override
    public JavaMath math() {
        return JavaMath.get();
    }

    @Override
    public Matrix add(Matrix b) {
        assertSizesEqual(b);
        JavaMatrix B = math().convert(b);
        return resultMatrix(this, B)
                .loop((r, c) -> get(r, c) + B.get(r, c));
    }

    @Override
    public Matrix sub(Matrix b) {
        assertSizesEqual(b);
        JavaMatrix B = math().convert(b);
        return resultMatrix(this, B)
                .loop((r, c) -> get(r, c) - B.get(r, c));
    }

    @Override
    public Matrix mul(Matrix b) {
        assertSizesForMul(b);
        JavaMatrix B = math().convert(b);
        int columns = size().columns();
        return resultMatrixMul(B)
                .loop((Cop) (r, c) -> IntStream.range(0, columns)
                        .mapToDouble(i -> get(r, i) * B.get(i, c))
                        .sum());
    }

    @Override
    public Matrix mulScalar(Matrix b) {
        assertSizesEqual(b);
        JavaMatrix B = math().convert(b);
        return resultMatrix(this, B)
                .loop((r, c) -> get(r, c) * B.get(r, c));
    }

    @Override
    public Matrix div(Matrix b) {
        assertSizesEqual(b);
        JavaMatrix B = math().convert(b);
        return resultMatrix(this, B)
                .loop((r, c) -> get(r, c) / B.get(r, c));
    }

    private static JavaMatrix resultMatrix(JavaMatrix a, JavaMatrix b) {
        return a.matrixWithSameSize();
    }

    protected JavaMatrix resultMatrixMul(JavaMatrix other) {
        Size size = new Size(this.size().rows(), other.size().columns());
        return math().matrix(size, new double[size.size()]);
    }

    protected abstract JavaMatrix matrixWithSameSize();

    protected void loop(Op op) {
        IntStream.range(0, size().rows())
                .forEach(r -> IntStream.range(0, size().columns())
                        .forEach(c -> op.accept(r, c)));
    }

    private Matrix loop(Cop op) {
        IntStream.range(0, size().rows())
                .forEach(r -> IntStream.range(0, size().columns())
                        .forEach(c -> set(r, c, op.apply(r, c))));
        return this;
    }

    @Override
    public String toString() {
        if (size().columns() > 10 || size().rows() > 10) {
            return super.toString();
        }
        StringBuilder b = new StringBuilder("Matrix " + size() + ": \n");
        for (int i = 0; i < size().rows(); i++) {
            for (int j = 0; j < size().columns(); j++) {
                b.append(get(i, j)).append("\t");
            }
            b.append("\n");
        }
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Matrix) {
            return math().matrixEquals(this, (Matrix) o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessor, size);
    }

    @Override
    public boolean equals(Matrix other, double epsilon) {
        return size().equals(other.size()) &&
                Arrays.equals(other.calculate().data(), calculate().data());
    }
}
