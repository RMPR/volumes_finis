/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

//import ananum.EquationSolver.EquationSolver3D;
import ananum.solver.EquationSolver3D;
import ananum.matrice.Matrice3DTL;
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
    public Function solve(Function f, int n, double c, double a, double b) {

        if (n < 1) {
            return null;
        }
        Matrice3DTL mat = new Matrice3DTL(n);
        //partie droite
        Double[] val_f = new Double[n];

        if (n == 1) {
            //initialisation de la matrice
            //premiere ligne
            mat.setValeurDiag(0, 2 * n + c / (2 * n));

            //initialisation de la partie de droite du systeme
            ArrayList<Double> tab = f.f(n + 1);
            val_f[0] = (1 / 2. ) * (tab.get(1) + tab.get(0)) + (1 - c / 2. ) * a + b;

        } else {
            //initialisation de la matrice
            mat.setValeurDiag(0, 2 * n + c / (2 * n));
            mat.setValeurDiag(1, -n);
            mat.setValeurDiag(-1, -n + c / (2 * n));

            //initialisation de la partie de droite du systeme
            ArrayList<Double> tab = f.f(n + 1);
            for (int i = 0; i < n; i++) {
                val_f[i] = (1.0 / (2 * n) ) * (tab.get(i + 1) + tab.get(i));
            }
            val_f[0] += (n - c / (2 * n)) * a;
            val_f[n - 1] += n * b;

        }
        
        final Double[] res = EquationSolver3D.solve(mat, val_f);
        return new Function() {
            public double apply(double x){ return 0.0; }
            @Override
            public ArrayList<Double> f(int n1) {
                ArrayList<Double> resu = new ArrayList<>();
                resu.add(a);
                resu.addAll(Arrays.asList(res));
                resu.add(b);
                return resu;
            }
        };
    }

}
