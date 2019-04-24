/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;
import ananum.calculnumerique.EDSolver;
import ananum.calculnumerique.Function;
import ananum.matrice.Matrice;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class test_data {

    private Function RA;
    private int n;
    public String scenario;
    Function fonctionATester;
    double tol=10e-7;
    private double a;
    private double b;
    private double c;
    private EDSolver solver;
    
    public test_data(EDSolver s, Function f, Function RA, String scenario,  int n, double c, double a, double b){
        this.fonctionATester=f;
        this.RA=RA;
        this.scenario = scenario;
        this.n=n;
        this.a=a;
        this.b=b;
        this.c=c;
        this.solver = s;
    }
    public double mesure(Function V1, Function V2){
        // Cette methode calcule la mesure algébrique de l'érreur absolue ou relative
        //double err = Matrice.dist(sol, g.f(201));
        //System.out.println("===> "+ (err/Matrice.norme(g.f(201))) );
        double mes=0.0;
        double abs=0.0;
        ArrayList<Double> v1 = V1.f(n);
        ArrayList<Double> v2 = V2.f(v1.size());
        
        mes = Matrice.dist(v1, v2);
        abs = Matrice.norme(v2);
        if (abs <= tol)
            return sqrt(mes);
        else
            return sqrt(mes/abs);
    }
    public boolean oracle(){
        if (n<=0){
            return solver.solve(this.fonctionATester, this.n, this.c, this.a, this.b)==null;
        }else{
            double err = mesure(solver.solve(this.fonctionATester, this.n, this.c, this.a, this.b), RA);
            System.out.println("==> "+err);
            return err < tol;
        }
    }
    
}
