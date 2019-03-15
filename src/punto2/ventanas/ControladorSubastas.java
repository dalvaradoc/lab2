/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import punto2.Aportante;
import punto2.Promotor;
import punto2.Solicitud;

/**
 *
 * @author Alejandro
 */
public class ControladorSubastas {
    
    private VentanaSubastas ventana;
    private Modelo modelo;

    public ControladorSubastas(Modelo modelo) {
        this.modelo = modelo;
        this.ventana = new VentanaSubastas();
        //EventosBotones
        
        ventana.getBtnVolver().setOnAction(new EventoVolverInicio());
        
    }
    
    public void mostrarVentana (){
        for (Solicitud s : modelo.getGestor().getSolicitudes()){
            if (s.getEstado().equals("cancelado")){
                continue;
            }
            ventana.getSubastas().getItems().add(new TitledPane(s.getNombreProyecto(), s.getInfo()));
        }
        Singleton.getSingleton().getStage().setTitle("Sign in as " + modelo.getUsuario().getNombre());
        if (modelo.getUsuario().getClass().getSimpleName().equals("Promotor")){
            ventana.getVbox().getChildren().add(ventana.getBtnAddSubasta());
        }
        ventana.mostrar(Singleton.getSingleton().getStage());
    }
    
    private class EventoVolverInicio implements EventHandler<ActionEvent>{
        @Override
        public void handle(javafx.event.ActionEvent event) {
            ControladorInicio contInicio = new ControladorInicio(modelo);
            contInicio.mostrarVista();
        }   
    }
}
