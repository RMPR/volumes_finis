/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.equation;

import ananum.matrice.Matrice;

/**
 * @author nivekiba
 */
public class EquationSolver {

    /**
     * Uniquement si la matrice est a diagonale strictement dominante
     *
     * @param A
     * @param b
     * @return
     */

    public static Double[] solve(Matrice A, Double[] b) {
        double eps = 1e-10;
        Double[] x = new Double[b.length];

        for (int i = 0; i < b.length; i++)
            x[i] = 0.;
        x[0] = b[0];

        do {
            for (int i = 0; i < A.rows(); i++) {
                if (A.get(i, i) != 0) {
                    double tmp = b[i];
                    for (int j = i + 1; j < A.cols(); j++)
                        tmp = tmp - A.get(i, j) * x[j];

                    for (int j = 0; j < i; j++)
                        tmp = tmp - A.get(i, j) * x[j];

                    x[i] = tmp / A.get(i, i);
                }
            }
        } while (Matrice.dist(A.produit(x), b) > eps);

        return x;
    }
}
