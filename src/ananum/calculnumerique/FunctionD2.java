/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

import java.util.ArrayList;

/**
 *
 * @author nivekiba
 */
public interface FunctionD2 {
    public ArrayList<Double> f(int n, int m);
    public ArrayList<Double> fv(int n, int m);
    public double apply(double x, double y);
}
