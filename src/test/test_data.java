/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;
import ananum.calculnumerique.EDSolver;
import ananum.calculnumerique.Function;
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
    double tol=10e-8;
    private double a;
    private double b;
    private double c;
    private EDSolver solver;
    
    public test_data(EDSolver s, Function f, Function RA, String scenario,  int n, double a, double b, double c){
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
        double mes=0.0;
        double abs=0.0;
        ArrayList<Double> v1 = V1.f(n);
        ArrayList<Double> v2 = V2.f(n);
        for (int i=0; i<v1.size();i++){
            abs+= v1.get(i)*v2.get(i);
            double tmp=v1.get(i)-v2.get(i);
            mes+=tmp*tmp;   
        }
        if (abs==0.0)
            return sqrt(mes);
        else
            return sqrt(mes/abs);
    }
    public boolean oracle(){
        if (n<=0){
            if (solver.solve(this.fonctionATester, this.n, this.c, this.a, this.b)==null)
                return true;
            else
                return false;
        }else{
            if(mesure(solver.solve(this.fonctionATester, this.n, this.c, this.b, this.a), RA)<tol)
                return true;
            else
                return false;
        }
    }
    
}
