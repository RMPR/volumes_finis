/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.solvers2d;

import ananum.calculnumerique.Function2D;
import ananum.matrice.Matrice;

/**
 *
 * @author Rufus
 */
public interface Solver2D {
    public Function2D solve(Function2D f, int n, int m, Matrice contour, Matrice K);
}

