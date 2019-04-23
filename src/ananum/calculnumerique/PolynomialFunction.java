/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

import java.util.ArrayList;

/**
 * @author nivekiba
 */
public class PolynomialFunction implements Function {

    public double[] coef;
    public double a, b;

    public PolynomialFunction(double[] coef, double a, double b) {
        this.coef = coef;
        this.a = a;
        this.b = b;
    }

    public PolynomialFunction(double[] coef) {
        this(coef, 0, 1);
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

}
