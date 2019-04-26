package ananum.calculnumerique;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 *
 * @author Rufus
 */
public class EDSolverDiffFini extends EDSolver{

    /**
     * Method solving -u''+ cu = f
     * @param f the function returned
     * @param n the number of meshes
     * @param c the value of the function c (0 by default)
     * @param a u(x_0) = a
     * @param b u(x_n-1+1) = b
     */
    @Override
    public Function solve(Function f, int n,double c, double a, double b){
        if(n==0){
            return null;
        }
        if(n==1){
            final ArrayList<Double> U = new ArrayList<Double>(2);
            U.add(a);
            U.add(b);
            return new Function() {
                public ArrayList<Double> fv(int n){ return null; }
                public double apply(double x){ return 0.; }
                @Override
                public ArrayList<Double> f(int n) {
                    return U;
                }
            };
        }
        int N = n+1;
        double h = 1./(double)n;
        double w;
        ArrayList<Double> B = new ArrayList<Double>(n-1);
        ArrayList<Double> A = new ArrayList<Double>(n-1);
        ArrayList<Double> C = new ArrayList<Double>(n-1);
        ArrayList<Double> D = new ArrayList<Double>(n-1);
        ArrayList<Double> X = new ArrayList<Double>(n-1);
        final ArrayList<Double> U = new ArrayList<Double>(N);
        U.add(a);
        /* Construction of D */
        boolean flag = true;
        for(double element : f.f(n)){
           if(flag){
               flag = false;
               continue;
            }
           D.add(element);
        }
        for (int i=0; i<n-1; i++) {
            X.add(0.0);
            D.set(i, pow(h, 2)*D.get(i));
        }
        D.set(0, D.get(0)+a);
        D.set(n-2, D.get(n-2)+b);
        for (int i=0; i<n-1; i++){
            A.add(-1.0);
            B.add(2+c* pow(h,2));
            C.add(-1.0);
        }
        A.set(0, 0.0);
        C.set(n-2, 0.0);
        for(int i=1; i<n-1; i++){
            w = A.get(i) / B.get(i-1);
            B.set(i, B.get(i) - (w*C.get(i-1))) ;
            D.set(i, D.get(i) - (w*D.get(i-1)));
        }
        X.set(n-2, D.get(n-2)/B.get(n-2));
        for(int i=n-3; i>=0; i--){
            X.set(i, (D.get(i) - (C.get(i) * X.get(i+1)))/B.get(i));
        }
        U.addAll(X);
        U.add(b);
        return new Function() {
            public ArrayList<Double> fv(int n){ return null; }
            @Override
            public ArrayList<Double> f(int n) {
                return U;
            }
            public double apply(double x) { return 0.; }
        };
    }
}
