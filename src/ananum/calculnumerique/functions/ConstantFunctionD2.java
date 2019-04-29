/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import ananum.calculnumerique.FunctionD2;
import java.util.ArrayList;

/**
 *
 * @author nivekiba
 */
public class ConstantFunctionD2 implements FunctionD2 {

    public double value = 0.0;
    
    public ConstantFunctionD2(double v){
        value = v;
    }
    
    @Override
    public ArrayList<Double> f(int n, int m) {
       ArrayList<Double> res = new ArrayList<>();
       for(int i=0; i<n*m; i++) res.add(value);
       return res;
    }

    @Override
    public ArrayList<Double> fv(int n, int m) {
        return f(n, m);
    }

    @Override
    public double apply(double x, double y) {
        return value;
    }
    
}
