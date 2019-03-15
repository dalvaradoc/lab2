/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro
 */
public class VentanaSubastas {
    private Scene scene;
    private StackPane pane;
    private ListView<TitledPane> subastas;

    public VentanaSubastas() {
        pane = new StackPane();
        
        subastas = new ListView<>();
        
        VBox vbox = new VBox(subastas);
        
        pane.getChildren().add(vbox);
        
        scene = new Scene(pane,500,500);
    }
    
    public void mostrar (Stage stage){
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public StackPane getPane() {
        return pane;
    }

    public ListView<TitledPane> getSubastas() {
        return subastas;
    }
    
    
    
}
