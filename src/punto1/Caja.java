/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alejandro
 */
public class Caja {
    
    private ArrayList <Producto> productos;
    private int numero;

    public Caja(int numero) {
        this.numero = numero;
        this.productos = new ArrayList<>();
    }
    
    public boolean addProductos (ArrayList<Producto> nProd){
        if (nProd.size() > 7-productos.size()){
            System.out.println("La caja no tiene suficiente espacio.");
            return false;
        }
        for (Producto p : nProd){
            if (!productos.add(p)){
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<Producto> getProductos (String nombreP, int cantidad){
        ArrayList<Producto> copia = new ArrayList<>(productos);
        ArrayList<Producto> pedido = new ArrayList<>();
        int c = 0;
        for (Producto p : productos){
            if (p.getNombre().equals(nombreP) && c < cantidad){
                pedido.add(p);
                c++;
                copia.remove(p);
            }
        }
        productos = copia;
        return pedido;
    }
    
    public HashMap<String,Integer> getInfoProductos (){
        HashMap<String,Integer> info = new HashMap<>();
        for (Producto p : productos){
            if (info.containsKey(p.getNombre())){
                info.replace(p.getNombre(), info.get(p.getNombre())+1);
            } else {
                info.put(p.getNombre(), 1);
            }
        }
        return info;
    }
}
