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
public class Aportante extends Persona{
    
    private double cuenta;
    private ArrayList<Prestamo> prestamos;
    
    public Aportante(String nombre, String apellido, String correo, double fondos) {
        super(nombre, apellido, correo);
        this.cuenta = fondos;
        this.prestamos = new ArrayList<>();
    }
    
    public boolean addFondos (double fondos){
        if (fondos < 0){
            return false;
        }
        cuenta += fondos;
        return true;
    }
    
    public boolean addPrestamo (Prestamo prestamo){
        return prestamos.add(prestamo);
    }

    public double getCuenta() {
        return cuenta;
    }
    
}
