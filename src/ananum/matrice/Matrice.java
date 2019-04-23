/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.matrice;

import java.util.ArrayList;

/**
 * @author nivekiba
 */
public abstract class Matrice implements IMatrice {

    @Override
    public Double[] produit(Double[] v) {
        Double[] res = new Double[v.length];

        for (int i = 0; i < v.length; i++) {
            res[i] = 0.;
            for (int j = 0; j < v.length; j++) {
                res[i] += v[j] * this.get(i, j);
            }
        }

        return res;
    }

    public static double norme(Double[] v) {
        double r = 0.;
        for (int i = 0; i < v.length; i++) {
            r += v[i] * v[i];
        }
        return Math.sqrt(r);
    }
    
    public static double norme(ArrayList<Double> v) {
        double r = 0.;
        for (int i = 0; i < v.size(); i++) {
            r += v.get(i) * v.get(i);
        }
        return Math.sqrt(r);
    }

    public static double dist(Double[] a, Double[] b) {
        double r = 0.;
        for (int i = 0; i < a.length; i++) {
            r += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(r);
    }
    
    public static double dist(ArrayList<Double> a, ArrayList<Double> b) {
        double r = 0.;
        for (int i = 0; i < a.size(); i++) {
            r += (a.get(i) - b.get(i)) * (a.get(i) - b.get(i));
        }
        return Math.sqrt(r);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                str += "  " + get(i, j);
            }
            str += "\n";
        }
        return str;
    }
}
