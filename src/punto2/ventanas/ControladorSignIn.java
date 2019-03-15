/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import punto2.Persona;

/**
 *
 * @author Alejandro
 */
public class ControladorSignIn {
    private VentanaSignIn ventana;
    private Modelo modelo;

    public ControladorSignIn(Modelo modelo) {
        this.modelo = modelo;
        this.ventana = new VentanaSignIn();
        
        //Eventos de los botones
        ventana.getBtnSignIn().setOnAction(new SignIn());
        ventana.getBtnVolver().setOnAction(new EventoVolverInicio());
    }
    
    public void mostrarVentana () {
        Singleton singleton = Singleton.getSingleton();
        ventana.mostrar(singleton.getStage());
    }
    
    private class SignIn implements EventHandler<ActionEvent>{
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Persona usuario = modelo.getGestor().getUsuario(ventana.getCorreo().getText());
            if (usuario == null){
                ventana.getCorreo().setText("No hay ningun usuario registrado con ese correo");
            } else {
                modelo.setUsuario(usuario);
                ControladorSubastas sub = new ControladorSubastas(modelo);
                sub.mostrarVentana();
            }
        }
    }
    
    private class EventoVolverInicio implements EventHandler<ActionEvent>{
        @Override
        public void handle(javafx.event.ActionEvent event) {
            ControladorInicio inicio = new ControladorInicio(modelo);
            inicio.mostrarVista();
        }
        
    }
    
}
