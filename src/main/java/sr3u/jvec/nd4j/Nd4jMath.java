package sr3u.jvec.nd4j;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import sr3u.jvec.JMath;
import sr3u.jvec.Matrix;
import sr3u.jvec.Vector;

public class Nd4jMath extends JMath {

    private static final ThreadLocal<Nd4jMath> INSTANCE = ThreadLocal.withInitial(Nd4jMath::create);

    private static Nd4jMath create() {
        try{
            INDArray tmp = Nd4j.create(1, 1);
            return new Nd4jMath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Nd4jMath get() {
        return INSTANCE.get();
    }

    private Nd4jMath() {

    }

    @Override
    public Nd4jVector vector(double... array) {
        return new Nd4jVector(array);
    }

    @Override
    public Nd4jMatrix matrix(Matrix.Size size, double[] data) {
        return new Nd4jMatrix(size, data);
    }

    public Nd4jMatrix convert(Matrix v) {
        if (v instanceof Nd4jMatrix) {
            return (Nd4jMatrix) v;
        }
        return matrix(v.size(), v.calculate().data());
    }

    public Nd4jVector convert(Vector v) {
        if (v instanceof Nd4jVector) {
            return (Nd4jVector) v;
        }
        return vector(v.calculate().data());
    }
}
