/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.test2D;
import ananum.calculnumerique.Function2D;
import ananum.calculnumerique.solvers2d.EDRSolver2D;
import ananum.matrice.Matrice;

/**
 *
 * @author User
 */
public class test_data_2d {

    private Function2D RA;
    private int n;
    private int m;
    public String scenario;
    Function2D fonctionATester;
    Function2D contour;
    double tol=10e-7;
    private Matrice K;
    private EDRSolver2D solver;
    
    public test_data_2d(EDRSolver2D s,String scenario,Function2D f, int n, int m, Function2D contour, Matrice K, Function2D RA) {
        this.fonctionATester=f;
        this.RA=RA;
        this.scenario = scenario;
        this.n=n;
        this.m=m;
        this.K=K;
        this.contour = contour;
        this.solver = s;
    }
    public double mesure(Function2D V1, Function2D V2){
        // Cette methode calcule la mesure algébrique de l'érreur absolue ou relative
        double mes=0.0;
        double abs=0.0;
        Matrice v1;
        try{
            v1 = V1.f(n, m);
        }catch(Exception e){
            v1 = V1.fv(n+2, m+2);
        }
        Matrice v2;
        if(v1.rows() == n){
            v2 = V2.fv(n, m);
            for(int i=0; i< n; i++){
                for(int j=0; j<m; j++){
                    mes += Math.pow(v1.get(i, j) - v2.get(i, j), 2);
                    abs += Math.pow(v2.get(i, j), 2);
                }
            }
        }else{
            v2 = V2.f(n+1, m+1);
            for(int i=1; i< n; i++){
                for(int j=1; j<m; j++){
                    mes += Math.pow(v1.get(i-1, j-1) - v2.get(i, j), 2);
                    abs += Math.pow(v2.get(i, j), 2);
                }
            }
        }
            
        // mes = Math.pow(mes, 0.5);
        // abs = Math.pow(abs, 0.5);
        if(abs <= tol)
            return mes;
        return mes/abs;
    }
    public boolean oracle(){
        if (n<=2 || m <=2){
            return solver.solve(this.fonctionATester, this.n, this.m, this.contour, this.K)==null;
        }else{
            double err = mesure(solver.solve(this.fonctionATester, this.n, this.m, this.contour, this.K), RA);
            System.out.println("Err: "+err);
            return err < tol;
        }
    }
    
}
