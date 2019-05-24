/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import ananum.matrice.Matrice;
import java.util.ArrayList;
import ananum.calculnumerique.Function2D;
import ananum.matrice.MatriceCRS;

/**
 *
 * @author nivekiba
 */
public class PolynomialFunction2D implements Function2D {

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

    public PolynomialFunction2D(Matrice coef, double a1, double b1, double a2, double b2) {
        this.coef = coef;
        this.a1 = a1;
        this.b1 = b1;
        this.a2 = a2;
        this.b2 = b2;
    }
    
    
    @Override
    public Matrice f(int n, int m) {
        Matrice mat = new MatriceCRS(n, m);
        if(n < 2){
            double x = (a1+b1)/2;
            if(m < 2){
                double r = 0., y = (a2+b2)/2;
                for (int k = 0; k < coef.rows(); k++) {
                    for (int l = 0; l < coef.cols(); l++) {
                        r += coef.get(k, l) * Math.pow(x, k) * Math.pow(y, l);
                    }
                }
                mat.set(0, 0, r);
            } else {
                double pas_y = (b2-a2)/(m-1);
                for (int j = 0; j < m; j++) {
                    double tmp_y = a2 + j * pas_y;
                    double r = 0.;
                    for (int k = 0; k < coef.rows(); k++) {
                        for (int l = 0; l < coef.cols(); l++) {
                            r += coef.get(k, l) * Math.pow(x, k) * Math.pow(tmp_y, l);
                        }
                    }
                    mat.set(0, j, r);
                }
            }
            return mat;
        } else {
            if (m < 2){
                double y = (a2+b2)/2;
                double pas_x = (b1-a1)/(n-1);
                for (int i = 0; i < m; i++) {
                    double tmp_x = a1 + i * pas_x;
                    double r = 0.;
                    for (int k = 0; k < coef.rows(); k++) {
                        for (int l = 0; l < coef.cols(); l++) {
                            r += coef.get(k, l) * Math.pow(tmp_x, k) * Math.pow(y, l);
                        }
                    }
                    mat.set(i, 0, r);
                }
                return mat;
            }
        }
        double pas_x = (b1-a1)/(n-1);
        double pas_y = (b2-a2)/(m-1);
        for(int i=0; i<n; i++){
            double tmp_x = a1 + i * pas_x;
            for (int j = 0; j < m; j++) {
                double tmp_y = a2 + j * pas_y;
                double r = 0.;
                for (int k = 0; k < coef.rows(); k++) {
                    for (int l = 0; l < coef.cols(); l++) {
                        r += coef.get(k, l) * Math.pow(tmp_x, k) * Math.pow(tmp_y, l);
                    }
                }
                mat.set(i, j, r);
            }
        }
        return mat;
    }

    @Override
    public Matrice fv(int n, int m) {
        ArrayList<Double> Xs = Utilities.generate_volume_points(a1, b1, n);
        ArrayList<Double> Ys = Utilities.generate_volume_points(a2, b2, m);
        Matrice mat = new MatriceCRS(n, m);
        for (int i = 0; i < Xs.size(); i++) {
            for (int j = 0; j < Ys.size(); j++) {
                mat.set(i, j, value(Xs.get(i), Ys.get(j)));
            }
        }
        return mat;
    }

    @Override
    public double value(double x, double y) {
        double r = 0.;
        for (int i = 0; i < coef.rows(); i++) {
            for (int j = 0; j < coef.cols(); j++) {
                r += coef.get(i, j) * Math.pow(x, i) * Math.pow(y, j);
            }
        }
        return r;
    }
    
}
