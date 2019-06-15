/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.graphes;

import ananum.calculnumerique.Function2D;
import ananum.calculnumerique.functions.ConstantFunction2D;
import ananum.calculnumerique.functions.PolynomialFunction2D;
import ananum.calculnumerique.solvers2d.EDRSolver2D;
import ananum.calculnumerique.solvers2d.EDRSolverDiff2D;
import ananum.matrice.Matrice;
import ananum.matrice.MatriceCRS;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import eu.hansolo.tilesfx.chart.PixelMatrix;
import java.net.URL;
import javafx.concurrent.Service;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nivekiba
 */
public class GrapheController implements Initializable {

    private Stage st;
    
    @FXML
    JFXComboBox<String> f, contour;
    @FXML
    JFXTextField n, m;
    @FXML
    Label msg;
    @FXML
    AnchorPane container;
    
    Tooltip toolTip = new Tooltip("");
    
    ArrayList<Function2D> functions;
    Function2D f_, contour_;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init_fonctions();
    }    
    static Matrice u, sol;
    @FXML
    void onDraw(ActionEvent ae) {
        msg.setText("Résolution en cours\nVeuilllez patienter...");
        int nn = Integer.parseInt(n.getText());
        int mm = Integer.parseInt(m.getText());
        
        f_ = functions.get(f.getSelectionModel().getSelectedIndex());
        contour_ = functions.get(contour.getSelectionModel().getSelectedIndex());
        
        EDRSolver2D s = new EDRSolverDiff2D();
        Function2D res = s.solve(f_, nn, mm, contour_, null);
        sol = res.f(nn, mm);
        u = contour_.f(nn+1, mm+1);
        
        double max =-15555555555., min=15000000000.;
        for(int i=1; i<nn; i++)
            for(int j=1; j<mm; j++){
                u.set(i, j, sol.get(i-1, j-1));
            }
        
        for(int i=0; i<nn+1; i++)
            for(int j=0; j<mm+1; j++){
                max = Math.max(u.get(i, j), max);
                min = Math.min(u.get(i, j), min);
            }
        double a = 1./(max-min), b=-min/(max-min);
        PixelMatrix pixel = new PixelMatrix(nn,mm);
        pixel.setMinSize(530, 530);
        pixel.setSquarePixels(true);
        pixel.setSpacerSizeFactor(0);

        for(int i=0; i<nn; i++)
            for(int j=0; j<mm; j++){
                double v = 0.0;
                v += (u.get(i, j)+u.get(i+1, j)+u.get(i, j+1)+u.get(i+1, j+1))/4;
                int vy = (int)(200*(a*v+b));
                pixel.setPixel(i, mm-j-1, Color.rgb(vy+55, 200-vy, vy));
            }
        
        pixel.setOnMouseMoved(e -> {
            javafx.geometry.Point2D point = pixel.sceneToLocal(e.getSceneX(), e.getSceneY());
            double x = point.getX()/pixel.getWidth();
            double y = 1- point.getY()/pixel.getHeight();
            //double u = interpolation(x,y);
            //mousePositionToolTip.setText(String.format("(%.5f; %.5f ; %.5f)",x,y,u));
            int x_cord = (int)(x*nn);
            int y_cord = (int)(y*mm);
            double u_res = calcul_interpol(x, y, x_cord, y_cord, u);
            toolTip.setText(String.format("(%.4f; %.4f ; %.4f)",x,y,u_res));
            Node node = (Node) e.getSource();
            toolTip.show(node, e.getScreenX()+20, e.getScreenY());
        });
        pixel.setOnMouseExited(e->{
            toolTip.hide();
        });
        msg.setText("");
        container.getChildren().clear();
        container
                .getChildren()
                .add(pixel);
        pixel.setUseSpacer(false);
    }
    
    private double calcul_interpol(double x, double y, int x_c, int y_c, Matrice u){
        double xa = x_c/(u.rows()-1.);
        double ya = y_c/(u.cols()-1.);
        
        double xb = (x_c+1)/(u.rows()-1.);
        double yb = (y_c+1)/(u.cols()-1.);
        
        double dx = x-xa, dy=y-ya, delta_x = xb-xa, delta_y=yb-ya;
        double delta_fx = u.get(x_c+1, y_c) - u.get(x_c, y_c);
        double delta_fy = u.get(x_c, y_c+1) - u.get(x_c, y_c);
        double delta_fxy = u.get(x_c, y_c) + u.get(x_c+1, y_c+1) - u.get(x_c+1, y_c) - u.get(x_c, y_c+1);
        return delta_fx*dx/delta_x + delta_fy*dy/delta_y + delta_fxy*dx*dy/(delta_x*delta_y)+u.get(x_c, y_c);
    }
    
    public void init_fonctions(){
        functions = new ArrayList<>();
        /* f(x,y) = 0, 1, -2 */
        functions.add(new ConstantFunction2D(0)); f.getItems().add("f(x,y) = 0");
        functions.add(new ConstantFunction2D(1)); f.getItems().add("f(x,y) = 1");
        functions.add(new ConstantFunction2D(-4)); f.getItems().add("f(x,y) = -4");
        /* f(x,y) = x */
        Matrice mat1 = new MatriceCRS(1, 2);
        mat1.set(0, 1, 1);
        functions.add(new PolynomialFunction2D(mat1, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = x");
        /* f(x,y) = y */
        Matrice mat2 = new MatriceCRS(2, 1);
        mat2.set(1, 0, 1);
        functions.add(new PolynomialFunction2D(mat2, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = y");
        /* f(x,y) = x + y */
        Matrice mat = new MatriceCRS(2, 2);
        mat.set(0, 1, 1);
        mat.set(1, 0, 1);
        functions.add(new PolynomialFunction2D(mat, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = x + y");
        /* f(x,y) = xy */
        Matrice mat3 = new MatriceCRS(2, 2);
        mat3.set(1, 1, 1);
        functions.add(new PolynomialFunction2D(mat3, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = xy");
        /* f(x,y) = x^2+y^2 */
        Matrice matt = new MatriceCRS(3, 3);
        matt.set(0, 2, 1);
        matt.set(2, 0, 1);
        functions.add(new PolynomialFunction2D(matt, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = x^2+y^2");
        /* f(x,y) = x^2-y^2 */
        Matrice maty = new MatriceCRS(3, 3);
        maty.set(0, 2, 1);
        maty.set(2, 0, -1);
        functions.add(new PolynomialFunction2D(maty, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = x^2-y^2");
        /* f(x,y) = x^2-2xy+y^2 */
        Matrice matu = new MatriceCRS(3, 3);
        matu.set(0, 2, 1);
        matu.set(1, 1, -1);
        matu.set(2, 0, 1);
        functions.add(new PolynomialFunction2D(matu, 0, 1, 0, 1));
        f.getItems().add("f(x,y) = x^2-2xy+y^2");
        
        contour.setItems(FXCollections.observableArrayList(f.getItems()));
    }
    
    public void setStage(Stage st){
        this.st = st;
    }
}
