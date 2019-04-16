/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

import java.util.ArrayList;

/**
 *
 * @author Rufus
 */
public class EDSolverDiffFini extends EDSolver{
    @Override
    /**
     * Method solving -u''+ cu = f
     * @param f the function returned
     * @param n the number of points in the mesh
     * @param c the value of the function c (0 by default)
     * @param a u(x_0) = a
     * @param b u(x_N+1) = b
     */
    public Function solve(Function f, int n,double c, double a, double b){
        if(n==0){
            return null;
        }
        double h = 1/n;
        double w;
        ArrayList<Double> B = new ArrayList<Double>(n);
        ArrayList<Double> A = new ArrayList<Double>(n);
        ArrayList<Double> C = new ArrayList<Double>(n);
        ArrayList<Double> D = new ArrayList<Double>();
        ArrayList<Double> X = new ArrayList<Double>(n);
        for (int i = 0; i < n; i++) {
            D.add(0.);
            X.add(0.0);
        }
        for (int i=0; i<n; i++){
            B.add(-1.0);
            A.add(2+c*Math.pow(h,2));
            C.add(-1.0);
        }
        for(int i=1; i<n; i++){
            w = A.get(i) / B.get(i-1);
            B.set(i, B.get(i) - w*C.get(i-1)) ;
            D.set(i, D.get(i) - w*D.get(i-1));
        }
        X.set(n-1, D.get(n-1));
        for(int i=n-2; i>=0; i--){
            X.set(i, C.get(i) * X.get(i+1)/B.get(i));
        }
        System.out.println(X);
        return new Function() {
            @Override
            public ArrayList<Double> f(int n) {
                return X;
            }
        };
    }
}
