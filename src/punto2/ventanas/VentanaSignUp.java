/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro
 */
public class VentanaSignUp {
    private Scene scene;
    private StackPane pane;
    private TextField firstName;
    private TextField lastName;
    private TextField correo;
    private ChoiceBox tipoUsuario;
    private Button btnSignUp;
    private Button btnVolver;

    public VentanaSignUp() {
        
        pane = new StackPane();
        VBox vbox = new VBox(10);
        
        firstName = new TextField();
        lastName = new TextField();
        correo = new TextField();
        ObservableList<String> tipo = FXCollections.observableArrayList( 
         "Promotor", "Aportante"); 
        tipoUsuario = new ChoiceBox(tipo);
        btnSignUp = new Button("Sign Up");
        btnVolver = new Button("Volver");
        
        GridPane grid = new GridPane();
        grid.add(new Text("Primer Nombre: "), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Text("Primer Apellido: "), 0, 1);
        grid.add(lastName, 1, 1);
        grid.add(new Text("Correo: "), 0, 2);
        grid.add(correo, 1, 2);
        grid.add(new Text("Tipo de Usuario: "), 0, 3);
        grid.add(tipoUsuario, 1, 3);
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        vbox.getChildren().addAll(grid,btnSignUp, btnVolver);
        
        vbox.setAlignment(Pos.CENTER);
        
        pane.getChildren().add(vbox);
        
        pane.setAlignment(Pos.CENTER);
        
        scene = new Scene(pane, 500, 250);
    }
    
    public void mostrar (Stage stage){
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public StackPane getPane() {
        return pane;
    }

    public TextField getFirstName() {
        return firstName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public TextField getCorreo() {
        return correo;
    }

    public ChoiceBox getTipoUsuario() {
        return tipoUsuario;
    }

    public Button getBtnSignUp() {
        return btnSignUp;
    }

    public Button getBtnVolver() {
        return btnVolver;
    }
    
    
}
