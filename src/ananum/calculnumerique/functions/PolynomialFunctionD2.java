/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import ananum.calculnumerique.FunctionD2;
import ananum.matrice.Matrice;
import java.util.ArrayList;

/**
 *
 * @author nivekiba
 */
public class PolynomialFunctionD2 implements FunctionD2 {

    public Matrice coef;
    public double a1, b1, a2, b2;
    
    /**
     * 
     * @param coef : matrice des coefficients de la fonction
     * @param a1
     * @param b1
     * @param a2
     * @param b2 
     * [a1, b1] : domaine de definition de la 1ere variable
     * [a2, b2] : domaine de definition de la 2eme variable
     */

    public PolynomialFunctionD2(Matrice coef, double a1, double b1, double a2, double b2) {
        this.coef = coef;
        this.a1 = a1;
        this.b1 = b1;
        this.a2 = a2;
        this.b2 = b2;
    }
    
    
    @Override
    public ArrayList<Double> f(int n, int m) {
        
    }

    @Override
    public ArrayList<Double> fv(int n, int m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double apply(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
