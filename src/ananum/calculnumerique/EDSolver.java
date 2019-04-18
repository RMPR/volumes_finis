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
public abstract class EDSolver implements Solver{
	@Override
	public Function solve(Function f, int n,double c, double a, double b){
		ArrayList<Double> values = f.f(n);

		double pas = values.get(1) - values.get(0);
		return null;
	}	
}
