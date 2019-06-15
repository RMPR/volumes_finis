/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.solver;

import ananum.matrice.Matrice;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        /*sThread[] th = new Thread[50];
        for(int m=0; m < 50; m++){
            th[m] = new Thread(){
                @Override
                public void run() {
                    for(int k=0; k<100; k++)
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
                }
            };
            th[m].start();
            try {
                th[m].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(EquationSolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        } */
        double err = 0.0;
        do {
            err = 0.0;
            for (int i = 0; i < A.rows(); i++) {
                if (A.get(i, i) != 0) {
                    double tmp = b[i];
                    for (int j = i + 1; j < A.cols(); j++)
                        tmp = tmp - x[j] * A.get(i, j);

                    for (int j = 0; j < i; j++)
                        tmp = tmp - x[j] * A.get(i, j);
                    
                    err += Math.pow(x[i]-(tmp/ A.get(i, i)), 2);
                    x[i] = tmp / A.get(i, i);
                }
            }
        //} while (Matrice.dist(A.produit(x), b) > eps);
        } while(err > eps);
        return x;
    }
}
