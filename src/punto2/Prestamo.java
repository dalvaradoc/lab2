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
public class Prestamo {
    private double monto;
    private double interes;
    private int tiempoMeses;
    private Aportante aportante;

    public Prestamo(double monto, double interes, int tiempoMeses, Aportante aportante) {
        this.monto = monto;
        this.interes = interes;
        this.tiempoMeses = tiempoMeses;
        this.aportante = aportante;
    }

    public double getMonto() {
        return monto;
    }

    public double getInteres() {
        return interes;
    }

    public int getTiempoMeses() {
        return tiempoMeses;
    }

    public Aportante getAportante() {
        return aportante;
    }
    
}
