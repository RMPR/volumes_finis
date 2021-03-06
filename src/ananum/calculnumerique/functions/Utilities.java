/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import java.util.ArrayList;

/**
 *
 * @author nivekiba
 */
public class Utilities {

    public static ArrayList<Double> generate_volume_points(double a, double b, int n) {
        ArrayList<Double> res = new ArrayList<>();
        if (n < 2) {
            res.add((a + b) / 2);
            return res;
        }
        //res.add(a);
        for (int i = 1; i <= n; i++) {
            double tmp = (2. * i - 1) / (2. * n);
            res.add(tmp);
        }
        //res.add(b);
        return res;
    }

    public static ArrayList<Double> generate_diff_points(double a, double b, int n) {
        ArrayList<Double> res = new ArrayList<>();
        if (n < 2) {
            res.add((a + b) / 2);
            return res;
        }
        double pas = (b - a) / (n - 1);
        for (int i = 0; i < n; i++) {
            double tmp = a + i * pas;
            res.add(tmp);
        }
        return res;
    }
}
