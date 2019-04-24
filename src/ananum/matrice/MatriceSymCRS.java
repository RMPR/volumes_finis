/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.matrice;

/**
 * Represente la matrice just de facon triangulaire superieur
 *
 * @author jodel
 */
public class MatriceSymCRS extends MatriceCRS {

    public MatriceSymCRS(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public double get(int i, int j) {
        int min = Math.min(i, j);
        return super.get(min, j + i - min); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(int i, int j, double x) {
        int min = Math.min(i, j);
        super.set(min, i + j - min, x); //To change body of generated methods, choose Tools | Templates.
    }


}
