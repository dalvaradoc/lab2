/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import becker.robots.City;
import becker.robots.Direction;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alejandro
 */
public class Estante extends becker.robots.Robot {
    
    private int numero;
    private int streetParq;
    private int avenueParq;
    private boolean ocupado;
    private ArrayList<Caja> cajas;

    public Estante(int numero, City city, int i, int i1, Direction drctn) {
        super(city, i, i1, drctn);
        this.streetParq = i;
        this.avenueParq = i1;
        this.numero = numero;
        this.ocupado = false;
        this.cajas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getStreetParq() {
        return streetParq;
    }

    public int getAvenueParq() {
        return avenueParq;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
    public boolean addCaja () {
        if (cajas.size() > 3){
            return false;
        }
        
        return cajas.add(new Caja(cajas.size()+1));
    }
    
    public boolean removeCaja (int numero) {
        if (numero > 3 || numero < 0 || cajas.size() < numero-1){
            return false;
        }
        if (cajas.remove(numero) == null){
            return false;
        }
        return true;
    }
    
    public ArrayList<Producto> getProductos (String nombreP, int cantidad){
        ArrayList<Producto> productos = new ArrayList<>();
        for (Caja c : cajas){
            ArrayList<Producto> prodsInCaja = c.getProductos(nombreP, cantidad);
            cantidad -= prodsInCaja.size();
            productos.addAll(prodsInCaja);
        }
        return productos;
    }
    
    public HashMap<String, Integer> getInfoProducts () {
        HashMap<String,Integer> info = new HashMap<>();
        for (Caja c : cajas){
            HashMap<String, Integer> infoCaja = c.getInfoProductos();
            for (String i : infoCaja.keySet()){
                if(info.containsKey(i)){
                    info.replace(i, infoCaja.get(i)+info.get(i));
                } else {
                    info.put(i, infoCaja.get(i));
                }
            }
        }
        return info;
    }
    
    public Caja getCaja (int numero){
        if (numero <= 0 || numero > cajas.size()){
            System.out.println("No existe la caja " + numero + ".");
            return null;
        }
        return cajas.get(numero-1);
    }
}
