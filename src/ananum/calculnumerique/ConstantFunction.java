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
public class ConstantFunction implements Function {

    double constant = 0.0;
    public ConstantFunction(double value){
        constant = value;
    }
    
    @Override
    public ArrayList<Double> f(int n) {
        ArrayList<Double> res = new ArrayList<>();
        for(int i=0; i<n; i++) res.add(constant);
        return res;
    }
    
}
