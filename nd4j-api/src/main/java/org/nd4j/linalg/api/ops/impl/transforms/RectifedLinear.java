package org.nd4j.linalg.api.ops.impl.transforms;

import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseTransformOp;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.factory.Nd4j;

/**
 *
 * Rectified linear units
 *
 * @author Adam Gibson
 */
public class RectifedLinear extends BaseTransformOp {
    private double cutoff = 0.0;

    public RectifedLinear(INDArray x, INDArray z, double cutoff) {
        super(x, z);
        this.cutoff = cutoff;
    }

    public RectifedLinear(INDArray x, INDArray z, int n, double cutoff) {
        super(x, z, n);
        this.cutoff = cutoff;
    }

    public RectifedLinear(INDArray x, INDArray y, INDArray z, int n, double cutoff) {
        super(x, y, z, n);
        this.cutoff = cutoff;
    }

    public RectifedLinear(INDArray x, double cutoff) {
        super(x);
        this.cutoff = cutoff;
    }

    public RectifedLinear(INDArray x, INDArray z) {
        super(x, z);
    }

    public RectifedLinear(INDArray x, INDArray z, int n) {
        super(x, z, n);
    }

    public RectifedLinear(INDArray x, INDArray y, INDArray z, int n) {
        super(x, y, z, n);
    }

    public RectifedLinear(INDArray x) {
        super(x);
    }

    @Override
    public String name() {
        return "relu";
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, double other) {
        return origin.realComponent().doubleValue() < cutoff ? Nd4j.createComplexNumber(cutoff,0) : origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, float other) {
        return origin.realComponent().doubleValue() < cutoff ? Nd4j.createComplexNumber(cutoff,0) : origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, IComplexNumber other) {
        return origin.realComponent().doubleValue() < cutoff ? Nd4j.createComplexNumber(cutoff,0) : origin;
    }

    @Override
    public float op(float origin, float other) {
        return origin < cutoff ? (float) cutoff : origin;
    }

    @Override
    public double op(double origin, double other) {
        return origin < cutoff ?  cutoff : origin;
    }

    @Override
    public double op(double origin) {
        return origin < cutoff ? cutoff : origin;

    }

    @Override
    public float op(float origin) {
        return origin < cutoff ? (float) cutoff : origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin) {
        return origin.realComponent().doubleValue() < cutoff ? Nd4j.createComplexNumber(cutoff,0) : origin;

    }

    @Override
    public Op opForDimension(int index, int dimension) {
        INDArray xAlongDimension = x.vectorAlongDimension(index, dimension);

        if (y() != null)
            return new Pow(xAlongDimension, y.vectorAlongDimension(index, dimension), z.vectorAlongDimension(index, dimension), xAlongDimension.length(), cutoff);
        else
            return new Pow(xAlongDimension, z.vectorAlongDimension(index, dimension), xAlongDimension.length(), cutoff);
    }
}