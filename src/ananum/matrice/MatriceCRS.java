/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.matrice;

import java.util.ArrayList;

/**
 * @author jodel
 * les indices de la matrice commence a 0
 */
public class MatriceCRS extends Matrice {

    protected int rows;
    protected int cols;

    protected final ArrayList<Double> val = new ArrayList<>();
    protected final ArrayList<Integer> col_index = new ArrayList<>();
    protected final Integer row_ptr[];

    public MatriceCRS(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        row_ptr = new Integer[rows + 1];
        for (int i = 0; i < rows + 1; i++) {
            row_ptr[i] = 0;
        }
    }

    @Override
    public void set(int i, int j, double x) {
        //verifis les indices
        //les indices admissibles sont de 0...row-1 et 0...col-1
        if (i >= rows() || i < 0 || j < 0 || j >= cols()) {
            System.out.println("Mauvais indices");
            return;
        }
        //retrouve l'indice correspondant dans la liste val
        int ind_val = index_col(j, row_ptr[i], row_ptr[i + 1]);

        if (ind_val < row_ptr[i + 1] && col_index.get(ind_val) == j) {
            if (x == 0.0) {
                remove(ind_val, i);
            } else {
                val.set(ind_val, x);
            }
        } else {
            insert(ind_val, i, j, x);
        }
    }

    @Override
    public double get(int i, int j) {
        //verifie les indices
        if (i >= rows() || i < 0 || j < 0 || j >= cols()) {
            return 0.0;
        }

        for (int k = row_ptr[i]; k < row_ptr[i + 1]; k++) {
            if (col_index.get(k) == j) {
                return val.get(k);
            }
        }
        return 0;
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }


    protected int index_col(int j, int left, int right) {
        //s'il ya aucun element sur la ligne left-right = 0
        if (right - left == 0 || j > col_index.get(right - 1)) {
            return right;
        }

        while (left < right) {
            int p = (left + right) / 2;
            if (col_index.get(p) > j) {
                right = p;
            } else if (col_index.get(p) < j) {
                left = p + 1;
            } else {
                return p;
            }
        }

        return left;
    }

    public void etat() {
        System.out.println("val " + val);
        System.out.println("col_index " + col_index);
        System.out.print("row_ptr [");
        for (Integer elt : row_ptr) {
            System.out.print(elt + " ");
        }
        System.out.println("]");
    }

    protected void remove(int k, int i) {
        val.remove(k);
        col_index.remove(k);
        for (int ii = i + 1; ii < rows + 1; ii++) {
            row_ptr[ii]--;
        }
    }

    protected void insert(int k, int i, int j, double value) {
        if (value == 0.0) {
            return;
        }

        if (k >= val.size()) {
            val.add(value);
            col_index.add(j);
        } else {
            val.add(k, value);
            col_index.add(k, j);
        }

        for (int ii = i + 1; ii < rows + 1; ii++) {
            row_ptr[ii]++;
        }
    }
}
