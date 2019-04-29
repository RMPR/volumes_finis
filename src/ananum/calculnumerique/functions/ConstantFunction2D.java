/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import ananum.matrice.Matrice;
import ananum.matrice.MatriceCRS;
import java.util.ArrayList;
import ananum.calculnumerique.Function2D;

/**
 *
 * @author nivekiba
 */
public class ConstantFunction2D implements Function2D {

    public double value = 0.0;
    
    public ConstantFunction2D(double v){
        value = v;
    }
    
    @Override
    public Matrice f(int n, int m) {
        MatriceCRS mat = new MatriceCRS(n, m);
        return mat;
    }

    @Override
    public Matrice fv(int n, int m) {
        return f(n, m);
    }

    @Override
    public double apply(double x, double y) {
        return value;
    }
    
}
