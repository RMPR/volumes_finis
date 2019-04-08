/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.volumes_finis;

import ananum.matrice.EquationSolver;
import ananum.matrice.Matrice;
import ananum.matrice.MatricePleine;

/**
 *
 * @author Rufus
 */
public class Main {
    public static void main(String[] args) {
        MatricePleine mp = new MatricePleine(3);
        mp.set(0, 0, 3);
        mp.set(0, 1, 1);
        mp.set(0, 2, 1);
        
        mp.set(1, 1, 1);
        mp.set(1, 2, 1);
        
        mp.set(2, 0, 1);
        mp.set(2, 2, 2);
        Double[] b = new Double[]{5., 3., 3.};
        Double[] x = EquationSolver.solve(mp, b);
        for(int i=0; i<3; i++){
            System.out.print(x[i]+",");
        }
    }
}
