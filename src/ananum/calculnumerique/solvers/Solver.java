/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.solvers;

import ananum.calculnumerique.Function;

/**
 *
 * @author Rufus
 */
public interface Solver {
public Function solve(Function f, int n,double c, double a, double b);
        }

