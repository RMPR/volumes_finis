/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

//import ananum.EquationSolver.EquationSolver3D;

import ananum.equation.EquationSolver;
import ananum.matrice.Matrice3DTL;
import ananum.solver.EquationSolver3D;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rufus
 */
public class EDSolverVolFini extends EDSolver {

    /**
     * Resolution de l'equation -u'' + cu = f par la methode des volumes finis
     *
     * @param f second membre de l'equation
     * @param n nombre de volume de controle
     * @param c poid de la fonction inconnue dans l'equation
     * @param a valeur de la fonction inconnue en 0
     * @param b valeur de la fonction inconnue en 1
     * @return un tableau de n point representant la valeur de la focntion en n
     * point(2i+1/2n) i variant de 0 a n-1, si n < 1 la focntion retourne un pointeur null
     */
    @Override
    public Function solve(Function f, int n, double c, final double a, final double b) {
        if (n < 1) {
            return null;
        }
        Matrice3DTL mat = new Matrice3DTL(n);
        final double a1 = 0.774596669;
        final double a3 = -0.774596669;

        final double m1 = -n;
        final double m_d = -2*n;
        final double m0 = 2 * n + c/n;
        final double m00= n + m0;
        //partie droite
        Double[] val_f = new Double[n];

        if (n == 1) {
            //initialisation de la matrice
            //premiere ligne
            mat.setValeurDiag(0, m00);

            //initialisation de la partie de droite du systeme
            val_f[0] = 0.5 * ((5 / 9) * f.apply(0.5 * a1 + 0.5) + (8 / 9) * f.apply( 0.5) + (5 / 9) * f.apply(0.5 * a3 + 0.5))
                    - m_d * (a + b);

        } else {
            //initialisation de la matrice
            mat.setValeurDiag(0, m0);
            mat.setValeurDiag(1, m1);
            mat.setValeurDiag(-1, m1);

            mat.set(0,0,m00);
            mat.set(n-1,n-1,m00);

            //initialisation de la partie de droite du systeme
            ArrayList<Double> tab = f.f(n + 1);
            for (int i = 0; i < n; i++) {
                val_f[i] = (0.5 / n) * ((5. / 9) * f.apply((0.5 / n) * a1 + (0.5 * (2 * i + 1)) / n)
                        + (8. / 9) * f.apply( (0.5 * (2 * i + 1)) / n)
                        + (5. / 9) * f.apply((0.5 / n) * a3 + (0.5 * (2 * i + 1)) / n));
            }
            val_f[0] += -m_d * a;
            val_f[n - 1] += -m_d * b;
        }
        /*
        System.out.println(mat);
        for (int i = 0; i < n; i++) {
            System.out.println(val_f[i]);
        }
        System.out.println();
        */

        final Double[] res = EquationSolver.solve(mat, val_f);
        return new Function() {
            public double apply(double x) {
                return 0.0;
            }
            public ArrayList<Double> fv(int n){ return null; }
            @Override
            public ArrayList<Double> f(int n1) {
                ArrayList<Double> resu = new ArrayList<Double>();
                resu.add(a);
                resu.addAll(Arrays.asList(res));
                resu.add(b);
                return resu;
            }
        };
    }

}
