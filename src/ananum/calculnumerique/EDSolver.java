/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.calculnumerique;

/**
 *
 * @author Rufus
 */
public abstract class EDSolver implements Solver{
	@Override
	public Function solve(Function f, int n,double c, double a, double b){
		Double[] values = f.f(n);

		double pas = values[1] - values[0];
		return null;
	}	
}
