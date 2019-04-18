/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.matrice;

/**
 *
 * @author nivekiba
 */
public interface IMatrice {
    
    public void set(int i, int j, double x);
    public double get(int i, int j);
    
    public int rows();
    public int cols();
    
    public Double[] produit(Double[] v);
}
