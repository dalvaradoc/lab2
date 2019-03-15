/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro
 */
public class VentanaInicio {
    private StackPane pane;
    private Scene scene;
    private Button btnSignIn;
    private Button btnSignUp;
    

    public VentanaInicio() {
        pane = new StackPane();
        VBox vbox = new VBox(10);
        Text text = new Text("Bienvenido\n\nSeleccione Sign in si ya tiene una cuenta"
                + " registrada,\npor el contrario seleccione sign up y cree una cuenta.");
        text.setTextAlignment(TextAlignment.CENTER);
        
        btnSignIn = new Button("Sign In");
        btnSignUp = new Button("Sign Up");
        
        vbox.getChildren().addAll(text, btnSignIn, btnSignUp);
        
        vbox.setAlignment(Pos.CENTER);
        
        pane.getChildren().add(vbox);
        scene = new Scene(pane,300, 250);
    }

    public StackPane getPane() {
        return pane;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getBtnSignIn() {
        return btnSignIn;
    }

    public Button getBtnSignUp() {
        return btnSignUp;
    }
    
    public void mostrar (Stage stage){
        stage.setTitle("Bienvenido");
        stage.setScene(scene);
        stage.show();
    }
    
    
}
