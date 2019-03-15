/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

/**
 *
 * @author Alejandro
 */
public class Solicitud {
    private String nombreProyecto;
    private String descripcion;
    private double montoMinimo;
    private String estado;
    private Promotor promotor;

    public Solicitud(String nombreProyecto, String descripcion, double montoMinimo, Promotor promotor) {
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.montoMinimo = montoMinimo;
        this.estado = "activa";
        this.promotor = promotor;
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
    
}
