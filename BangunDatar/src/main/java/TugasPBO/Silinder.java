/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasPBO;

/**
 *
 * @author nicolas
 */
public class Silinder extends Lingkaran {

    private double tinggi;

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    double volume() {
        return this.luas() * this.tinggi;
    }
}
