/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import punto2.Aportante;
import punto2.Promotor;

/**
 *
 * @author Alejandro
 */
public class ControladorSignUp {
    private VentanaSignUp ventana;
    private Modelo modelo;

    public ControladorSignUp(Modelo modelo) {
        this.modelo = modelo;
        this.ventana = new VentanaSignUp();
        //EventosBotones
        ventana.getBtnSignUp().setOnAction(new SignUp());
        ventana.getBtnVolver().setOnAction(new EventoVolverInicio());
        
    }
    
    public void mostrarVentana (){
        Singleton singleton = Singleton.getSingleton();
        ventana.mostrar(singleton.getStage());
    }
    
    private class SignUp implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            if (ventana.getFirstName().getText().equals("")){
                ventana.getFirstName().setText("No puede dejar este espacio en blanco");
                return;
            }
            if (ventana.getLastName().getText().equals("")){
                ventana.getLastName().setText("No puede dejar este espacio en blanco");
                return;
            }
            if (ventana.getCorreo().getText().equals("")){
                ventana.getCorreo().setText("No puede dejar este espacio en blanco");
                return;
            }
            if (ventana.getTipoUsuario().getSelectionModel().getSelectedItem() == null){
                return;
            }
            if (modelo.getGestor().getUsuario(ventana.getCorreo().getText()) != null){
                ventana.getCorreo().setText("El correo ya esta registrado");
            } else {
                switch (ventana.getTipoUsuario().getSelectionModel().getSelectedItem().toString()){
                    case "Aportante":
                        Aportante a = new Aportante(ventana.getFirstName().getText(), ventana.getLastName().getText(), ventana.getCorreo().getText(),0);
                        modelo.getGestor().addAportante(a);
                        modelo.setUsuario(a);
                        break;
                    case "Promotor":
                        Promotor p = new Promotor(ventana.getFirstName().getText(), ventana.getLastName().getText(), ventana.getCorreo().getText());
                        modelo.getGestor().addPromotor(p);
                        modelo.setUsuario(p);
                        break;
                }
                try {
                    modelo.getGestor().guardarInformacion();
                } catch (IOException ex) {
                    Logger.getLogger(ControladorSignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
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
