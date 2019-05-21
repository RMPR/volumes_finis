/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.matrice;

/**
 * @author nivekiba
 */
public class MatricePleine extends Matrice {

    double[][] tab = null;

    public MatricePleine(int n, int m) {
        tab = new double[n][];
        for (int i = 0; i < n; i++) {
            tab[i] = new double[m];
        }
    }

    public MatricePleine(int n) {
        this(n, n);
    }

    @Override
    public void set(int i, int j, double x) {
        tab[i][j] = x;
    }

    @Override
    public double get(int i, int j) {
        return tab[i][j];
    }

    @Override
    public int rows() {
        return tab.length;
    }

    @Override
    public int cols() {
        return tab[0].length;
    }

}
