/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Promotor extends Persona{
    
    ArrayList<Solicitud> solicitudes;
    
    public Promotor(String nombre, String apellido, String correo) {
        super(nombre, apellido, correo);
        this.solicitudes = new ArrayList<>();
    }
    
    public boolean addSolicitud (Solicitud s){
        return solicitudes.add(s);
    }
    
    public boolean cancelarSolicitud (Solicitud s){
        s.setEstado("cancelado");
        return true;
    }
}
