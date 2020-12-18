package sr3u.jvec.java;

import sr3u.jvec.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AccessorChain implements JavaMatrix.Accessor {

    List<JavaMatrix.Accessor> accessors;

    private AccessorChain(List<JavaMatrix.Accessor> accessors) {
        this.accessors = new ArrayList<>(accessors);
    }

    public static AccessorChain of(JavaMatrix.Accessor... accessors) {
        return of(Arrays.stream(accessors).collect(Collectors.toList()));
    }

    public static AccessorChain of(Collection<JavaMatrix.Accessor> accessors) {
        return new AccessorChain(new ArrayList<>(accessors)).unwrap();
    }

    private AccessorChain unwrap() {
        accessors = accessors.stream()
                .flatMap(accessor -> {
                    if (accessor instanceof AccessorChain) {
                        return ((AccessorChain) accessor).unwrap().accessors.stream();
                    }
                    return Stream.of(accessor);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public JavaMatrix.Index index(int row, int column) {
        JavaMatrix.Index idx = new JavaMatrix.Index(row, column);
        for (JavaMatrix.Accessor a : accessors) {
            idx = a.index(idx.row, idx.column);
        }
        return idx;
    }

    @Override
    public Matrix.Size size(int rows, int columns) {
        Matrix.Size idx = new Matrix.Size(rows, columns);
        for (JavaMatrix.Accessor a : accessors) {
            idx = a.size(idx.rows(), idx.columns());
        }
        return idx;
    }
}
