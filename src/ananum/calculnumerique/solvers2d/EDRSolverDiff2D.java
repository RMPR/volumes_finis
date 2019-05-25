/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique.solvers2d;

import ananum.calculnumerique.Function2D;
import ananum.calculnumerique.functions.Utilities;
import ananum.matrice.Matrice;
import ananum.matrice.MatriceCRS;
import ananum.solver.EquationSolver;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author nivekiba
 */
public class EDRSolverDiff2D extends EDRSolver2D {
    
    /**
     * Carole to do
     * @param f
     * @param n
     * @param a
     * @param b
     * @param K
     * @return 
     */
    @Override
    public Function2D solve(Function2D f, int n, int m, Function2D contour, Matrice K) {
        int o = (n-1)*(m-1);
        if(n < 2 || m < 2){
            return null;
        }
        Matrice mat = new MatriceCRS(o, o);
        Matrice resultat_fin = new MatriceCRS(o+2, o+2);
        ArrayList<Double> arr_val_f = new ArrayList<>();
        
        /***** Generation des elements de la matrice et du second membre *****/
        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(1, 0));
        pairs.add(new Pair<>(-1, 0));
        pairs.add(new Pair<>(0, 1));
        pairs.add(new Pair<>(0, -1));
        
        ArrayList<Double> Xs = Utilities.generate_diff_points(0, 1, n+1);
        ArrayList<Double> Ys = Utilities.generate_diff_points(0, 1, m+1);
        
        for(int j=1; j<m; j++){
            for (int i=1; i<n; i++) {
                double x = Xs.get(i), y = Ys.get(j);
                //String res = "";
                //String f = "f_"+i+"_"+j;
                double ff = f.value(x, y);
                int m_ind = getind(i, j, n);
                int t = m_ind;
                //res += "(2/h+2/k) X_"+m_ind+" ";
                //mat_str[m_ind][m_ind] = "2/h+2/k";
                mat.set(m_ind, m_ind, 2*n*n+2*m*m);
                for(Pair<Integer, Integer> p: pairs){
                    int ii = i+p.getKey();
                    int jj = j+p.getValue();
                    double xi = Xs.get(ii), yj = Ys.get(jj);
                    if(ii>0 && ii<n && jj>0 && jj<m ) {
                        m_ind = getind(ii, jj, n);
                        if(p.getKey() == 0){
                            //res += "-1/k";
                            //mat_str[t][m_ind] = "-1/k";
                            mat.set(t, m_ind, -m*m);
                        }
                        if(p.getValue() == 0){
                            //res += "-1/h";
                            //mat_str[t][m_ind] = "-1/h";
                            mat.set(t, m_ind, -n*n);
                        }
                        //res += " X_"+m_ind+" ";
                    } else {
                        /*
                            if(p.getKey() == 0)
                                f += "+1/k";
                            if(p.getValue() == 0)
                                f += "+1/h";
                            f += " U"+ii+","+jj+" ";
                        */
                        if(p.getKey() == 0) {
                            ff += m*m*contour.value(xi, yj);
                        }
                        if(p.getValue() == 0) {
                            ff += n*n*contour.value(xi, yj);
                        }
                    }
                }
                //System.out.println(res+" = "+f);
                arr_val_f.add(ff);
            }
        }
        /*******  Fin generation ********/
        Double[] val_f = arr_val_f.toArray(new Double[arr_val_f.size()]);
        System.out.println(mat);
        System.out.println(arr_val_f);
        Double[] res = EquationSolver.solve(mat, val_f);
        Matrice mat_r = transformeEnMatrice(res, n-1, m-1);
        return new Function2D() {
            @Override
            public Matrice f(int n, int m) {
                return mat_r;
            }

            @Override
            public Matrice fv(int n, int m) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public double value(double x, double y) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    /**
     * X_m(i,j) = U_i_j
     * @param i
     * @param j
     * @param n
     * @return 
     */
    public static int getind(int i, int j, int n){
        return (i-1)+(n-1)*(j-1);
    }
    
    private Matrice transformeEnMatrice(Double[] res, int n, int m) {
        Matrice mat = new MatriceCRS(n, m);
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                mat.set(i % n, i / n, res[i]);
            }
        }
        return mat;
    }
}
