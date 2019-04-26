/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

import java.util.ArrayList;

/**
 * @author Rufus
 */
public interface Function {
    public ArrayList<Double> f(int n);
    public ArrayList<Double> fv(int n);
    public double apply(double x);
}
