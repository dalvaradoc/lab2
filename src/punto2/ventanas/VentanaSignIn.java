/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro
 */
public class VentanaSignIn {
    private StackPane pane;
    private Scene scene;
    private TextField correo;
    private Button btnSignIn;
    private Button btnVolver;

    public VentanaSignIn() {
        pane = new StackPane();
        VBox vbox = new VBox(15);
        
        Text text = new Text("Ingrese el correo: ");
        correo = new TextField();
        correo.setPrefWidth(300);

        
        HBox hbox1 = new HBox(text, correo);
        
        btnSignIn = new Button("Sign In");
        btnVolver = new Button("Volver");
        
        vbox.getChildren().addAll(hbox1,btnSignIn,btnVolver);
        
        hbox1.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        pane.setAlignment(Pos.CENTER);
        
        pane.getChildren().add(vbox);
        
        scene = new Scene(pane,500,250);
        
    }
    
    public void mostrar (Stage stage){
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public StackPane getPane() {
        return pane;
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getCorreo() {
        return correo;
    }

    public Button getBtnSignIn() {
        return btnSignIn;
    }

    public Button getBtnVolver() {
        return btnVolver;
    }
    
    
}
