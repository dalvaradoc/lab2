/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Thing;
import becker.robots.Wall;
import becker.robots.icons.Icon;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class Sistema {
    private City ciudad;
    private Estante[] estantes;
    private int c;
    private Thing empleado;

    public Sistema() {
        ciudad = new City("save.txt");
        estantes = new Estante[20];
        c = 0;

        empleado = new Thing(ciudad, 1, 10);
        empleado.getIcon().setLabel("Empleado:0");
        Thing zonaEnvio = new Thing(ciudad, 8, 9);
        zonaEnvio.getIcon().setLabel("Zona Envio");
        
        //Se crean los 20 estantes-robots
        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                estantes[index] = new Estante(index, ciudad, j+1, i*2+1, Direction.EAST);
                estantes[index].addCaja();
                estantes[index].addCaja();
                estantes[index].addCaja();
                estantes[index].getIcon().setLabel(Integer.toString(index+1));
                index++;
            }
        }
        
        //Añado panes aleatorios en cada estante
        for (int i = 0; i < 20; i++){
            ArrayList<Producto> randomProductos = new ArrayList<>();
            int randomc = (int)Math.floor(Math.random()*5);
            for (int m = 0; m < randomc;m++){
                randomProductos.add(new Producto("Pan", 200.0));
            }
            estantes[i].getCaja((int)Math.floor(Math.random()*2)+1).addProductos(randomProductos);
        }
    }
    
    public void mover (Estante estante) {
        Thread t = new Thread () {            
            public void run () {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int x = 0;
                int y = 0;
                
                Direction actualDirct = estante.getDirection();
                
                switch (actualDirct){                    
                    case WEST:
                        x = -1;
                        break;
                    case EAST:
                        x = 1;
                        break;
                    case SOUTH:
                        y = 1;
                        break;
                    case NORTH:
                        y = -1;
                        break;
                }
                boolean moverse = true;
                for (int i = 0; i < 20; i++){
                    if (estantes[i].getAvenue() == estante.getAvenue() + x && estantes[i].getStreet() == estante.getStreet() + y){
                        moverse = false;
                    }
                }
                if (moverse){
                    estante.move();
                } else {
                    try {
                        Thread.sleep(500);
                        mover(estante);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        t.run();
    }
    
    
    
    public boolean traerEstante (int numero) {
        if (numero > 20 || numero <= 0){
            System.out.println("El numero que ingreso no es valido");
            return false;
        }
        Thread t = new Thread () {
            public void run () {
                Estante estante = estantes[numero-1];
                if (estante.isOcupado()){
                    System.out.println("Esta ocupado el estante que selecciono.");
                    return;
                }
                estante.setOcupado(true);
                while (estante.getDirection() != Direction.EAST){
                    estante.turnLeft();
                }
                mover(estante);
                estante.turnLeft();
                while (estante.frontIsClear()){
                    mover(estante);
                }
                estante.turnLeft();
                estante.turnLeft();
                estante.turnLeft();
                while (estante.frontIsClear()){
                    mover(estante);
                }
                estante.turnLeft();
                estante.turnLeft();
                estante.turnLeft();
                while (estante.getStreet() != 1){
                    mover(estante);
                }
            }
        };
        t.start();
        return true;
    }
    
    public boolean devolverEstante (Estante estante){
        Thread t = new Thread () {
            public void run () {
                if (estante == null){
                    System.out.println("No hay ningun estante en la zona de empleados");
                    System.out.println("-------------");
                    return;
                }
                while (estante.getDirection() != Direction.SOUTH){
                    estante.turnLeft();
                }
                
                while (estante.getStreet() != 7){
                    mover(estante);
                }
                
                estante.turnLeft();
                estante.turnLeft();
                estante.turnLeft();
                
                while (estante.getAvenue() != estante.getAvenueParq()+1){
                    mover(estante);
                }
                
                estante.turnLeft();
                estante.turnLeft();
                estante.turnLeft();
                
                while (estante.getStreet() != estante.getStreetParq()){
                    mover(estante);
                }
                
                estante.turnLeft();
                mover(estante);
                
                estante.turnLeft();
                estante.turnLeft();
                
                estante.setOcupado(false);
            }
        };
        t.start();
        return true;
    }
    
    public boolean devolverEstante () {
        Thread t = new Thread () {
            public void run () {
                Estante estante = null;
                for (int i = 0; i < 20; i++){
                    if (estantes[i].getAvenue() == 9 && estantes[i].getStreet() == 1){
                        estante = estantes[i];
                    }
                }
                if (estante == null){
                    System.out.println("No hay ningun estante en la zona de empleados");
                    System.out.println("-------------");
                    return;
                }
                while (estante.getDirection() != Direction.SOUTH){
                    estante.turnLeft();
                }
                
                while (estante.getStreet() != 7){
                    mover(estante);
                }
                
                estante.turnLeft();
                estante.turnLeft();
                estante.turnLeft();
                
                while (estante.getAvenue() != estante.getAvenueParq()+1){
                    mover(estante);
                }
                
                estante.turnLeft();
                estante.turnLeft();
                estante.turnLeft();
                
                while (estante.getStreet() != estante.getStreetParq()){
                    mover(estante);
                }
                
                estante.turnLeft();
                mover(estante);
                
                estante.turnLeft();
                estante.turnLeft();
                
                estante.setOcupado(false);
            }
        };
        t.start();
        return true;
    }
    
    public boolean llenarEstante (int caja, ArrayList<Producto> productos) {
        Thread t = new Thread () {
            public void run () {
                Estante estante = null;
                for (int i = 0; i < 20; i++){
                    if (estantes[i].getAvenue() == 9 && estantes[i].getStreet() == 1){
                        estante = estantes[i];
                    }
                }
                if (estante == null){
                    System.out.println("No hay ningun estante en la zona de empleados");
                    System.out.println("-------------");
                    return;
                }
                
                if (estante.getCaja(caja) != null){
                    estante.getCaja(caja).addProductos(productos);
                }
            }
        };
        t.start();
        return true;
    }
    
    public boolean isEstanteEnZona () {
        for (int i = 0; i < 20; i++){
            if (estantes[i].getAvenue() == 9 && estantes[i].getStreet() == 1){
                return true;
            }
        }
        return false;
    }
    
    public HashMap<String, Integer> getInfoAllProductos (){
        HashMap<String, Integer> info = new HashMap<>();
        for (Estante est : estantes){
            HashMap<String,Integer> infoEst = est.getInfoProducts();
            for (String n : infoEst.keySet()){
                if (info.containsKey(n)){
                    info.replace(n, info.get(n)+infoEst.get(n));
                } else {
                    info.put(n, infoEst.get(n));
                }
            }
        }
        return info;
    }
    
    public boolean traerPedido (HashMap<String,Integer> lista) {
        boolean completa = true;
        HashMap<String,Integer> copia = new HashMap<>(lista);
        HashMap<String,Integer> info = this.getInfoAllProductos();
        for (String n : copia.keySet()){
            if (info.containsKey(n)){
                copia.replace(n, Math.max(0, copia.get(n) - info.get(n)));
            }
        }
        for (String n : copia.keySet()){
            if (copia.get(n) != 0){
                System.out.println("Faltan " + copia.get(n) + " de " + n);
                completa = false;
            }
        }
        if (!completa){
            System.out.println("No se pudo hacer el pedido.");
            return false;
        }
        copia = new HashMap<>(lista);
        Estante[] copiaEst = estantes.clone();
        for (int i = 0; i < 20; i++){
            if (copiaEst[i] == null){
                continue;
            }
            Estante est = copiaEst[i];
            HashMap<String,Integer> infoEst = est.getInfoProducts();
            for (String n : lista.keySet()){
                if (copia.get(n) < 0){
                    continue;
                }
                if (infoEst.containsKey(n)){
                    copia.replace(n, copia.get(n)-infoEst.get(n));
                    this.traerEstante(i+1);
                    copiaEst[i] = null;
                }
            }
        }
        return true;
    }    
    
    
    public boolean llevarPedido (ArrayList<Producto> pedido){
        Estante estn = null;
        for (int i = 0; i < 20; i++){
            if (estantes[i].getAvenue() == 9 && estantes[i].getStreet() == 1){
                estn = estantes[i];
            }
        }
        if (estn == null){
            return false;
        }
        final Estante est = estn;
        Thread t = new Thread () {
            public void run (){
                while (est.getStreet() != 7){
                    mover(est);;
                }
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.run();
        this.generarFactura(pedido);
        this.devolverEstante(est);
        return true;
    }
    
    public void generarFactura (ArrayList<Producto> pedido){
        System.out.println("----------");
        System.out.println("Factura: ");
        double total = 0;
        ArrayList<String> nombresP = new ArrayList<>();
        for (Producto p : pedido){
            if (nombresP.contains(p.getNombre())){
                continue;
            }
            int c = 0;
            for (Producto p2 : pedido){
                if (p2.getNombre().equals(p.getNombre())){
                    c++;
                }
            }
            nombresP.add(p.getNombre());
            System.out.println(p.getNombre() + " x " + c + ": " + c*p.getPrecio());
            total += c*p.getPrecio();
        }
        System.out.println("Total: " + total);
        System.out.println("----------");
    }
    
    public ArrayList<Producto> getProductos (HashMap<String,Integer> lista){
        
        ArrayList<Producto> pedido = new ArrayList<>();
        Estante est = null;
        for (int i = 0; i < 20; i++){
            if (estantes[i].getAvenue() == 9 && estantes[i].getStreet() == 1){
                est = estantes[i];
            }
        }
        if (est == null){
            return null;
        }
        
        for (String n : lista.keySet()){
            ArrayList<Producto> prodsInEst = est.getProductos(n, lista.get(n));
            pedido.addAll(prodsInEst);
            //Esto puede no funcionar.
            lista.replace(n, lista.get(n)-prodsInEst.size());
        }
        return pedido;
    }
    
//    public ArrayList<Producto> pedido (HashMap<String,Integer> lista) {
//        boolean completa = true;
//        HashMap<String,Integer> copia = new HashMap<>(lista);
//        HashMap<String,Integer> info = this.getInfoAllProductos();
//        for (String n : copia.keySet()){
//            if (info.containsKey(n)){
//                copia.replace(n, Math.max(0, copia.get(n) - info.get(n)));
//            }
//        }
//        for (String n : copia.keySet()){
//            if (copia.get(n) != 0){
//                System.out.println("Faltan " + copia.get(n) + " de " + n);
//                completa = false;
//            }
//        }
//        if (!completa){
//            System.out.println("No se pudo hacer el pedido.");
//            return null;
//        }
//        
//        int cantidad = 0;
//        for (String n : lista.keySet()){
//            cantidad += lista.get(n);
//        }
//        
//        ArrayList<Producto> pedido = new ArrayList<>();
//        for (Estante est : estantes){
//            HashMap<String,Integer> infoEst = est.getInfoProducts();
//            for (String n : lista.keySet()){
//                if (infoEst.containsKey(n) && lista.get(n) != 0){
//                    this.traerEstante(est.getNumero()+1);
//                    while (est.getAvenue() != 9 && est.getStreet() != 1){
//                        Thread t3 = new Thread () {
//                            public void run () {
//                                try {
//                                    sleep(200);
//                                } catch (InterruptedException ex) {
//                                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        };
//                    }
//                    ArrayList<Producto> prodsInEst = est.getProductos(n, lista.get(n));
//                    pedido.addAll(prodsInEst);
//                    lista.replace(n, lista.get(n)-prodsInEst.size());
//                    cantidad -= prodsInEst.size();
//                    empleado.getIcon().setLabel("Empleado:" + Integer.parseInt(empleado.getIcon().getLabel().split(":")[1]) + prodsInEst.size());
//                    if (cantidad != 0){
//                        this.devolverEstante();
//                    } else {
//                        empleado.getIcon().setLabel("Empleado:0");
//                        Thread t = new Thread(){
//                            public void run (){
//                                while (est.getStreet() != 7){
//                                    mover(est);
//                                }
//                                try {
//                                    sleep(5000);
//                                } catch (InterruptedException ex) {
//                                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        };
//                        t.start();
//                        this.devolverEstante(est);
//                        return pedido;
//                    }
//                }
//            }
//        }
//        return pedido;
//    }
}
