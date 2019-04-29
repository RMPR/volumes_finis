/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import ananum.calculnumerique.Function;
import java.util.ArrayList;

/**
 * @author nivekiba
 */
public class PolynomialFunction implements Function {

    public double[] coef;
    public double a, b;
    
    /**
     * 
     * @param coef : tableau de coeficients { a0, a1, a2, ....., an }
     * @param a 
     * @param b
     * [a, b] : domaine de définition de la fonction polynome 
     */

    public PolynomialFunction(double[] coef, double a, double b) {
        this.coef = coef;
        this.a = a;
        this.b = b;
    }

    public PolynomialFunction(double[] coef) {
        this(coef, 0, 1);
    }
    
    public double value(double x){
        double r=0.0;
        for (int j = 0; j < coef.length; j++) {
            r += coef[j] * Math.pow(x, j);
        }
        return r;
    }

    @Override
    public ArrayList<Double> f(int n) {
        ArrayList<Double> res = new ArrayList<Double>();
        if (n < 2) {
            double r = 0.;
            double x = (a + b) / 2;
            for (int j = 0; j < coef.length; j++) {
                r += coef[j] * Math.pow(x, j);
            }
            res.add(r);
            return res;
        }
        double pas = (b - a) / (n - 1);
        for (int i = 0; i < n; i++) {
            double tmp = a + i * pas;
            double r = 0.;
            for (int j = 0; j < coef.length; j++) {
                r += coef[j] * Math.pow(tmp, j);
            }
            res.add(r);
        }
        return res;
    }
    
    public ArrayList<Double> fv(int n){
        ArrayList<Double> res = new ArrayList<Double>();
        if (n < 2) {
            double r = 0.;
            double x = (a + b) / 2;
            for (int j = 0; j < coef.length; j++) {
                r += coef[j] * Math.pow(x, j);
            }
            res.add(r);
            return res;
        }
        res.add(value(a));
        for (int i = 0; i < n-2; i++) {
            double tmp = (2.*i+1)/(2. * (n-2) );
            double r = 0.;
            for (int j = 0; j < coef.length; j++) {
                r += coef[j] * Math.pow(tmp, j);
            }
            res.add(r);
        }
        res.add(value(b));
        return res;
    }

}
