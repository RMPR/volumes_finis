/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.solvers2d;

import ananum.calculnumerique.Function2D;
import ananum.equation.EquationSolver;
import ananum.matrice.Matrice;
import ananum.matrice.MatriceCRS;

/**
 *
 * @author nivekiba
 */
public class EDRSolverVol2D extends EDRSolver2D {

    private int n = -1;
    private int m = -1;

    /**
     * Jodel to do
     *
     * @param f
     * @param n
     * @param m
     * @param contour
     * @param a
     * @param b
     * @param K
     * @return
     */
    @Override
    public Function2D solve(Function2D f, int n, int m, Function2D contour, Matrice K) {
        if (n <= 0 || m <= 0) {
            return null;
        }
        this.m = m;
        this.n = n;
        Double vect_f[] = calculeVecteurF(f, n, m);
        Matrice A = calculeMatrice(K, contour, n, m, vect_f);
        //System.out.println(A);
        Double res[] = EquationSolver.solve(A, vect_f);
        Matrice B = transformeEnMatrice(res);
        return new Function2D() {
            @Override
            public Matrice f(int n, int m) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Matrice fv(int n, int m) {
                return B;
            }

            @Override
            public double value(double x, double y) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    private Matrice calculeMatrice(Matrice K, Function2D contour, int n, int m, Double vect_f[]) {
        Matrice mat = new MatriceCRS(n * m, n * m);
        int ind = -1;
        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                editeLigne(i, j, mat, vect_f, contour);
            }
        }

        return mat;
    }

    private Double[] calculeVecteurF(Function2D f, int n, int m) {
        Double vect_f[] = new Double[n * m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                vect_f[i + n * j] = f.fv(n, m).get(i, j) / (n * m);
            }
        }
        return vect_f;
    }

    private Matrice transformeEnMatrice(Double[] res) {
        Matrice mat = new MatriceCRS(n, m);
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                mat.set(i % n, i / n, res[i]);
            }
        }
        return mat;
    }

    private int indice(int i, int j) {
        return i - 1 + n * (j - 1);
    }

    private double k22(int i, int j) {
        return 1.;
    }

    private double k11(int i, int j) {
        return 1.;
    }

    //coeficient de u(i,j-1) 
    private double cijmd(int i, int j) {
        if (j == 1) {
            return 2. * m * k22(i, j);
        } else {
            double k1 = k22(i, j);
            double k2 = k22(i, j - 1);
            return (2. * m * k1 * k2) / (k1 + k2);
        }
    }

    //ceoficient de u(i,j+1)
    private double cijpd(int i, int j) {
        if (j == 0) {
            return 2. * m * k22(i, j + 1);
        } else if (j == m) {
            return 2. * m * k22(i, j);
        } else {
            double k1 = k22(i, j);
            double k2 = k22(i, j + 1);
            return (2. * m * k1 * k2) / (k1 + k2);
        }
    }

    //ceoficient de u(i-1,j)
    private double cimdj(int i, int j) {
        if (i == 1) {
            return 2. * n * k11(i, j);
        } else {
            double k1 = k11(i, j);
            double k2 = k11(i - 1, j);
            return (2. * n * k1 * k2) / (k1 + k2);
        }
    }

    //coeficient de u(i+1,j)
    private double cipdj(int i, int j) {
        if (i == 0) {
            return 2. * n * k11(i + 1, j);
        } else if (i == n) {
            return 2. * n * k11(i, j);
        } else {
            double k1 = k11(i, j);
            double k2 = k11(i + 1, j);
            return (2. * n * k1 * k2) / (k1 + k2);
        }
    }

    //Edite la ligne correspondant aux indices (i,j)
    private void editeLigne(int i, int j, Matrice mat, Double vect_f[], Function2D contour) {

        int ind = indice(i, j);

        //les autres coeficients
        double val0 = cijmd(i, j) / n;
        double val1 = cimdj(i, j) / m;
        double val3 = cipdj(i, j) / m;
        double val4 = cijpd(i, j) / n;

        //modifie la valeur de la diagonale
        mat.set(ind, ind, val0 + val1 + val3 + val4);

        if (ind - n >= 0) {
            mat.set(ind, ind - n, -val0);
            mat.set(ind, ind - 1, -val1);
        } else {
            vect_f[ind] += val0 * contour.fv(n, m).get(i, j - 1);
            if (ind == 0) {
                vect_f[ind] += val1 * contour.fv(n, m).get(i - 1, j);
            } else {
                mat.set(ind, ind - 1, -val1);
            }
        }
        if (ind + n < n * m) {
            mat.set(ind, ind + n, -val4);
            mat.set(ind, ind + 1, -val3);
        } else {
            vect_f[ind] += val4 * contour.fv(n, m).get(i, j + 1);
            if (ind == n * m - 1) {
                vect_f[ind] += val3 * contour.fv(n, m).get(i + 1, j);
            } else {
                mat.set(ind, ind + 1, -val3);
            }
        }
    }
}
