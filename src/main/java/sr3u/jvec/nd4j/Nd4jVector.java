package sr3u.jvec.nd4j;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import sr3u.jvec.CalculatedVector;
import sr3u.jvec.JMath;
import sr3u.jvec.Vector;
import sr3u.jvec.java.JavaMath;

public class Nd4jVector implements Vector, CalculatedVector {

    protected final INDArray vec;

    public Nd4jVector(INDArray vec) {
        this.vec = vec;
    }

    public Nd4jVector(double[] data) {
        this(Nd4j.create(data));
    }

    @Override
    public CalculatedVector calculate() {
        return this;
    }

    @Override
    public double[] data() {
        return vec.toDoubleVector();
    }

    @Override
    public Nd4jVector copy() {
        return new Nd4jVector(vec);
    }

    @Override
    public int size() {
        return (int) vec.length();
    }

    @Override
    public Nd4jMath math() {
        return Nd4jMath.get();
    }

    @Override
    public double sum() {
        return vec.sum(0).sumNumber().doubleValue();
    }

    @Override
    public Vector add(Vector b) {
        Nd4jVector B = math().convert(b);
        return new Nd4jVector(vec.add(B.vec));
    }

    @Override
    public Vector sub(Vector b) {
        Nd4jVector B = math().convert(b);
        return new Nd4jVector(vec.sub(B.vec));
    }

    @Override
    public Vector mul(Vector b) {
        Nd4jVector B = math().convert(b);
        return new Nd4jVector(vec.mul(B.vec));
    }

    @Override
    public Vector div(Vector b) {
        Nd4jVector B = math().convert(b);
        return new Nd4jVector(vec.div(B.vec));
    }

    @Override
    public double magnitudeSq() {
        return JavaMath.get().convert(this).magnitudeSq(); // TODO Implement using ND4J
    }

    @Override
    public Vector exp() {
        return new Nd4jVector(Transforms.exp(vec));
    }

    @Override
    public boolean equals(Vector other, double epsilon) {
        Nd4jVector B = math().convert(other);
        return vec.equalsWithEps(B.vec, epsilon);
    }
}
