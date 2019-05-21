/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.functions;

import ananum.calculnumerique.Function;
import java.util.ArrayList;

/**
 *
 * @author nivekiba
 */
public class ConstantFunction implements Function {

    double constant = 0.0;
    
    /**
     * 
     * @param value : la valeur de la constante a renvoyer
     */
    
    public ConstantFunction(double value){
        constant = value;
    }
    
    public double value(double x){ return constant; }
    
    @Override
    public ArrayList<Double> f(int n) {
        ArrayList<Double> res = new ArrayList<Double>();
        for(int i=0; i<n; i++) res.add(constant);
        return res;
    }
    
    public ArrayList<Double> fv(int n){
        return f(n);
    }
    
}
