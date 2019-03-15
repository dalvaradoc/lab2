/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2.ventanas;

import punto2.Gestor;
import punto2.Persona;

/**
 *
 * @author Alejandro
 */
public class Modelo {
    private Gestor gestor;
    private Persona usuario;

    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public Persona getUsuario() {
        return usuario;
    }

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }
    
    
    
}
