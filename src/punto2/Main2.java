/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import javafx.stage.Stage;
import punto2.ventanas.ControladorInicio;
import punto2.ventanas.Modelo;
import punto2.ventanas.Singleton;


/**
 *
 * @author Alejandro
 */
public class Main2 extends javafx.application.Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Singleton singleton = Singleton.getSingleton();
        singleton.setStage(primaryStage);
        
        Modelo modelo = new Modelo();
        Gestor gestor = new Gestor();
        modelo.setGestor(gestor);
        
        modelo.getGestor().cargarInformacion();
        
        ControladorInicio controladorInicio = new ControladorInicio(modelo);
        controladorInicio.mostrarVista();
    }
    
    public static void main (String[] args){
        launch(args);
    }
}
