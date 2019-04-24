package ananum.matrice;

/*
 *Matrice de Toeplitz
 *Matrice3DTL: permet de stocker les matrices tridiagonal qui possede les memes elements sur toute la diagonal
 */
public class Matrice3DTL extends Matrice {

    private int n;
    /*permet de stocker les elements importants
    indice 0: pour la diagonal inferieur
    indice 1: pour la grande diagonal
    indice 2: pour la diagonal superieur
     */
    private Double tab[];

    public Matrice3DTL(int n) {
        if (n <= 0) {
            n = -1;
            tab = null;
        } else {
            this.n = n;
            this.tab = new Double[(n < 2) ? 1 : 3];
        }

    }

    private boolean mauvaixIndex(int i, int j) {
        return (i < 0 || i >= rows() || j < 0 || j >= cols());
    }

    public void setValeurDiag(int diag, double val) {
        if (this.n > 1) {
            switch (diag) {
                case 0:
                    tab[1] = val;
                    break;
                case 1:
                    tab[2] = val;
                    break;
                case -1:
                    tab[0] = val;
                    break;
                default:

            }
        } else if (n == 1) {
            if (diag == 0) tab[0] = val;
        }
    }

    @Override
    public void set(int i, int j, double x) {
        if (mauvaixIndex(i, j) || this.n <= 0) return;
        if (n > 1) {
            int ind = j - i + 1;
            if (ind < 0 || ind > 2) return;
            tab[ind] = x;
        } else {
            int ind = j - i;
            if (ind != 0) return;
            tab[ind] = x;
        }
    }

    @Override
    public double get(int i, int j) {
        if (mauvaixIndex(i, j) || this.n <= 0) return 0.0;
        if (n > 1) {
            int ind = j - i + 1;
            if (ind < 0 || ind > 2) return 0;
            return tab[ind];
        } else {
            int ind = j - i;
            if (ind != 0) return 0;
            return tab[ind];
        }
    }

    @Override
    public int rows() {
        return n;
    }

    @Override
    public int cols() {
        return n;
    }
}
