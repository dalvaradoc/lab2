/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    
    public boolean addSolicitud (String nP, String desc,double inMonto, double minMonto, Promotor prom){
        Solicitud newSol = new Solicitud(nP, desc, inMonto, minMonto, prom);
        prom.addSolicitud(newSol);
//        this.guardarInformacion();
        return solicitudes.add(newSol);
    }
    
    public boolean aceptar (Solicitud s, Prestamo p) {
        subastasRealizadas.put(s, p);
        s.setEstado("aceptado");
//        this.guardarInformacion();
        return true;
    }
    
    public boolean addAportante (Aportante a) {
        boolean r = aportantes.add(a);
//        this.guardarInformacion();
        return r;
    }
    
    public boolean addPromotor (Promotor p) {
        boolean r = promotores.add(p);
//        this.guardarInformacion();
        return r;
    }
    
    public Persona getUsuario (String correo){
        for (Promotor p : promotores){
            if (p.getCorreo().equals(correo)){
                return p;
            }
        }
        for (Aportante a : aportantes){
            if (a.getCorreo().equals(correo)){
                return a;
            }
        }
        return null;
    }
    
    public void guardarInformacion () throws IOException {
        File saveFile = new File("savefile.txt");
        saveFile.createNewFile();
        PrintStream out = new PrintStream(saveFile);
        for (Promotor p : promotores){
            out.print("promotor," + p.getNombre() + "," + p.getApellido() + "," + p.getCorreo() + 
                    ",");
            out.print(p.getSolicitudes().size() + ",");
            for (Solicitud s : p.getSolicitudes()){
                out.print(s.getNombreProyecto() + "," + s.getDescripcion() + "," + s.getMontoActual() +
                        "," + s.getMontoMinimo() + "," + s.getEstado() + ",");
            }
            out.print("\r\n");
        }
        for (Aportante a : aportantes){
            out.print("aportante," + a.getNombre() + "," + a.getApellido() + "," + a.getCorreo() + 
                    "," + a.getCuenta() + ",");
            out.print(a.getPrestamos().size() + ",");
            for (Prestamo p : a.getPrestamos()){
                out.print(p.getMonto() + "," + p.getInteres() + "," + p.getTiempoMeses() + ",");
            }
            out.print("\r\n");
        }
        out.flush();
        out.close();
    }
    
    public void cargarInformacion () throws FileNotFoundException, IOException {
        File saveFile = new File("savefile.txt");
        if (!saveFile.exists()){
            return;
        }
        Scanner sc = new Scanner(saveFile);
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split(",");
            switch (line[0]){
                case "aportante":
                    Aportante a = new Aportante(line[1], line[2], line[3], Double.parseDouble(line[4]));
                    for (int i = 0; i < Integer.parseInt(line[5]); i++){
                        a.addPrestamo(new Prestamo(Double.parseDouble(line[6]), Double.parseDouble(line[7]), Integer.parseInt(line[8]), a));
                    }
                    this.addAportante(a);
                    break;
                case "promotor":
                    Promotor p = new Promotor(line[1], line[2], line[3]);
                    this.addPromotor(p);
                    for (int i = 0; i < Integer.parseInt(line[4]); i++){
                        this.addSolicitud(line[5], line[6], Double.parseDouble(line[7]), Double.parseDouble(line[8]), p);
                    }
                    break;
            }
        }
    }
    
//+ guardarInformacion () : void
//+ cargarInformacion () : void

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    
}
