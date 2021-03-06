package org.nd4j.autodiff.functions.impl.binary.transform;

import org.nd4j.autodiff.ArrayField;
import org.nd4j.autodiff.functions.AbstractBinaryFunction;
import org.nd4j.autodiff.functions.Constant;
import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.functions.Variable;
import org.nd4j.autodiff.samediff.SameDiff;

import java.util.List;

public class Set extends AbstractBinaryFunction<ArrayField> {
    public Set(SameDiff sameDiff, DifferentialFunction<ArrayField> i_v1, DifferentialFunction<ArrayField> i_v2) {
        super(sameDiff, i_v1, i_v2);
    }

    @Override
    public ArrayField doGetValue() {
        return sameDiff.getArrayFactory().set(larg().getValue(true), rarg().getValue(true));
    }

    @Override
    public double getReal() {
        return Math.pow(larg().getReal(), rarg().getReal());
    }

    @Override
    public DifferentialFunction<ArrayField> diff(DifferentialFunction<ArrayField> i_v) {
        Constant<ArrayField> ym1 = sameDiff.getFunctionFactory()
                .val(rarg().getValue(true).sub(sameDiff.getArrayFactory().one(getResultShape())));
        return rarg().mul(sameDiff.getFunctionFactory().pow(larg(), ym1))
                .mul(larg().diff(i_v));
    }

    @Override
    public String toString() {
        return "set(" + larg().toString() + ", " + rarg().toString() + ")";
    }

    @Override
    public String doGetFormula(List<Variable<ArrayField> > variables) {
        return "set(" + larg().doGetFormula(variables) + ","
                + rarg().doGetFormula(variables) + ")";
    }

    @Override
    public String functionName() {
        return new org.nd4j.linalg.api.ops.impl.transforms.Set().name();
    }


}
