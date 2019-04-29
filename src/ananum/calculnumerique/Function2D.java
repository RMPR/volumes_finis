/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

import ananum.matrice.Matrice;

/**
 *
 * @author nivekiba
 */
public interface Function2D {
    public Matrice f(int n, int m);
    public Matrice fv(int n, int m);
    public double apply(double x, double y);
}
