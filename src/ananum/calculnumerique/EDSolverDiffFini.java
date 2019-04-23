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
     * @param n the number of meshes
     * @param c the value of the function c (0 by default)
     * @param a u(x_0) = a
     * @param b u(x_n-1+1) = b
     */
    public Function solve(Function f, int n,double c, double a, double b){
        if(n==0){
            return null;
        }
        if(n==1){
            ArrayList<Double> U = new ArrayList<Double>(2);
            U.add(a);
            U.add(b);
            return new Function() {
                @Override
                public ArrayList<Double> f(int n) {
                    return U;
                }
            };
        }
        int N = n+1;
        double h = 1/n;
        double w;
        ArrayList<Double> B = new ArrayList<Double>(n-1);
        ArrayList<Double> A = new ArrayList<Double>(n-1);
        ArrayList<Double> C = new ArrayList<Double>(n-1);
        ArrayList<Double> D = new ArrayList<Double>(n-1);
        ArrayList<Double> X = new ArrayList<Double>(n-1);
        ArrayList<Double> U = new ArrayList<Double>(N);
        U.add(a);
        for (int i = 0; i < n-1; i++) {
            X.add(0.0);
            D = f.f(n-1);
        }
        for (int i=0; i<n-1; i++){
            B.add(-1.0);
            A.add(2+c*Math.pow(h,2));
            C.add(-1.0);
        }
        for(int i=1; i<n-1; i++){
            w = A.get(i) / B.get(i-1);
            B.set(i, B.get(i) - w*C.get(i-1)) ;
            D.set(i, D.get(i) - w*D.get(i-1));
        }
        for(int i=n-3; i>=0; i--){
            X.set(i, D.get(i) - C.get(i) * X.get(i+1)/B.get(i));
        }
        U.addAll(X);
        U.add(b);
        return new Function() {
            @Override
            public ArrayList<Double> f(int n) {
                return U;
            }
        };
    }
}
