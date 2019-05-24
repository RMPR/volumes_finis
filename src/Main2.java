
import ananum.calculnumerique.Function2D;
import ananum.calculnumerique.functions.ConstantFunction2D;
import ananum.calculnumerique.functions.PolynomialFunction2D;
import ananum.calculnumerique.functions.Utilities;
import ananum.calculnumerique.solvers2d.EDRSolver2D;
import ananum.calculnumerique.solvers2d.EDRSolverVol2D;
import ananum.matrice.Matrice;
import ananum.matrice.MatriceCRS;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nivekiba
 */
public class Main2 {
    public static void main(String[] args) {
        EDRSolver2D t = new EDRSolverVol2D();
        Matrice m = new MatriceCRS(3, 3);
        m.set(0, 2, 1);
        m.set(2, 0, 1);
        m.set(1, 1, 2);
        Function2D f = new PolynomialFunction2D(m, 0, 1, 0, 1);
        int nn = 4, mm = 6;
        Function2D res = t.solve(new ConstantFunction2D(-4), nn, mm, f, null);
        System.out.println(res.fv(0, 0));
        ArrayList<Double> xs = Utilities.generate_volume_points(0, 1, nn);
        ArrayList<Double> ys = Utilities.generate_volume_points(0, 1, mm);
        for (int i = 0; i < xs.size(); i++) {
            for (int j = 0; j < ys.size(); j++) {
                System.out.print(" "+f.value(xs.get(i), ys.get(j)));
            }
            System.out.println("");
        }
    }
}
