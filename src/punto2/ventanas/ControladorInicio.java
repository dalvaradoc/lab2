/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Alejandro
 */
public class ControladorInicio {
    private VentanaInicio ventana;
    private Modelo modelo;

    public ControladorInicio(Modelo modelo) {
        this.modelo = modelo;
        this.ventana = new VentanaInicio();
        //Eventos botones
        this.ventana.getBtnSignIn().setOnAction(new SignIn());
        this.ventana.getBtnSignUp().setOnAction(new SignUp());
    }
    
    public void mostrarVista () {
        Singleton singleton = Singleton.getSingleton();
        this.ventana.mostrar(singleton.getStage());
    }
    
    private class SignIn implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            ControladorSignIn contSignIn = new ControladorSignIn(modelo);
            contSignIn.mostrarVentana();
        }   
    }
    
    private class SignUp implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            ControladorSignUp contSignUp = new ControladorSignUp(modelo);
            contSignUp.mostrarVentana();
        }   
    }
}
