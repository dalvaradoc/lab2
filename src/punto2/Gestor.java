/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alejandro
 */
public class Gestor {
    private ArrayList<Solicitud> solicitudes;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Promotor> promotores;
    private ArrayList<Aportante> aportantes;
    
    private HashMap<Solicitud, Prestamo> subastasRealizadas;

    public Gestor() {
        this.solicitudes = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.promotores = new ArrayList<>();
        this.aportantes = new ArrayList<>();
        this.subastasRealizadas = new HashMap<>();
    }
    
    public boolean addSolicitud (String nP, String desc, double minMonto, Promotor prom){
        Solicitud newSol = new Solicitud(nP, desc, minMonto, prom);
        prom.addSolicitud(newSol);
        return solicitudes.add(newSol);
    }
    
    public boolean aceptar (Solicitud s, Prestamo p){
        subastasRealizadas.put(s, p);
        s.setEstado("aceptado");
        return true;
    }
    
    public boolean addAportante (Aportante a){
        return aportantes.add(a);
    }
    
    public boolean addPromotor (Promotor p){
        return promotores.add(p);
    }
    
//+ guardarInformacion () : void
//+ cargarInformacion () : void

    
}
