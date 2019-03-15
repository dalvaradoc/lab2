/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Alejandro
 */
public class Solicitud {
    private String nombreProyecto;
    private String descripcion;
    private double montoInicial;
    private double montoMinimo;
    private double montoActual;
    private String estado;
    private Promotor promotor;
    
    private GridPane info;

    public Solicitud(String nombreProyecto, String descripcion, double montoInicial, double montoMinimo, Promotor promotor) {
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.montoInicial = montoInicial;
        this.montoActual = montoInicial;
        this.montoMinimo = montoMinimo;
        this.estado = "activa";
        this.promotor = promotor;
        
        info = new GridPane();
        info.add(new Text("Descripcion: "), 0, 0);
        info.add(new Text(this.nombreProyecto), 1, 0);
        info.add(new Text("Valor: "), 0, 1);
        info.add(new Text(Double.toString(this.montoActual)), 1, 1);
        info.add(new Text("Estado: "), 0, 2);
        info.add(new Text(this.estado), 1, 2);
        info.add(new Text("Promotor: "), 0, 3);
        info.add(new Text(promotor.getCorreo()), 1, 3);
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMontoInicial() {
        return montoInicial;
    }
    
    public GridPane getInfo() {
        return info;
    }

    public double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(double montoActual) {
        this.montoActual = montoActual;
    }
    
    
    
}
