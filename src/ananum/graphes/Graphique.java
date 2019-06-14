/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ananum.graphes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author nivekiba
 */
public class Graphique extends Application {
    
    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage dialogStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("graphe.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.show();
        
        GrapheController sc = loader.getController();
        sc.setStage(dialogStage);
    }
}
