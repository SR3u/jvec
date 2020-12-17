package sr3u.jvec.java;

import sr3u.jvec.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class AccessorChain implements JavaMatrix.Accessor {

    List<JavaMatrix.Accessor> accessors;

    private AccessorChain(List<JavaMatrix.Accessor> accessors) {
        this.accessors = new ArrayList<>(accessors);
    }

    public static AccessorChain of(JavaMatrix.Accessor... accessors) {
        return new AccessorChain(Arrays.stream(accessors).collect(Collectors.toList())).unwrap();
    }

    public static AccessorChain of(Collection<JavaMatrix.Accessor> accessors) {
        return new AccessorChain(new ArrayList<>(accessors)).unwrap();
    }

    private AccessorChain unwrap() {
        /*List<JavaMatrix.Accessor> accessorList = accessors;
        boolean repaced = true;
        while (repaced) {
            List<JavaMatrix.Accessor> newAccessorList = new ArrayList<>();
            repaced = false;
            if (accessors.size() > 1) {
                for (int i = 0; i < accessors.size() - 1; i++) {
                    JavaMatrix.Accessor current = accessors.get(i);
                    JavaMatrix.Accessor next = accessors.get(i + 1);
                    if (JavaMatrix.ACCESSOR_TRANSPOSED.equals(current) && JavaMatrix.ACCESSOR_TRANSPOSED.equals(next)) {
                        i += 1;
                    } else {
                        accessorList.add(current);
                    }
                }
            }
            accessorList = newAccessorList;
        }
        accessors = accessorList;*/
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
