package org.nd4j.linalg.factory;

import org.nd4j.linalg.api.blas.*;
import org.nd4j.linalg.api.ndarray.ISparseMatrix;

/**
 * @author Audrey Loeffel
 */
interface ISparseMatrixFactory {


    Blas blas();

    Lapack lapack();

    Level1 level1();

    Level2 level2();

    Level3 level3();

    void createBlas();

    void createLevel1();

    void createLevel2();

    void createLevel3();

    void createLapack();

    ISparseMatrix createSparse(double[] data, int[] columns, int[] pointerB, int[] pointerE, int[] shape);

}