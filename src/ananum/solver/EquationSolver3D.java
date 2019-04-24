package ananum.solver;

import ananum.matrice.Matrice;

/*
 * EquationSolver3D: 3D pour tri-diagonale, permet de resourdre les systemes d'equation a matrice tri-diagonale
 *
 * */
public class EquationSolver3D {
    public static Double[] solve(Matrice A, Double[] b) {
        int n = A.rows();
        Double u[] = new Double[n];

        Double alpha[] = new Double[n];
        Double beta[] = new Double[n];

        alpha[0] = A.get(0, 0);
        beta[0] = b[0] / alpha[0];

        for (int i = 1; i < n; i++) {
            alpha[i] = A.get(i, i) - A.get(i, i - 1) * A.get(i - 1, i) / alpha[i - 1];
            beta[i] = (b[i] - A.get(i, i + 1) * beta[i - 1]) / alpha[i];
        }
        u[n - 1] = beta[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            u[i] = beta[i] - (A.get(i, i + 1) * u[i + 1]) / alpha[i];
        }

        return u;
    }
}
