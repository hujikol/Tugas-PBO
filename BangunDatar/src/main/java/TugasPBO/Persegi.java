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
public class Persegi extends BangunDatar {

    private double sisi;

    public double getSisi() {
        return sisi;
    }

    public void setSisi(double sisi) {
        this.sisi = sisi;
    }

    @Override
    public double keliling() {
        return 4*this.sisi;
    }

    @Override
    public double luas() {
        return this.sisi*this.sisi;
    }

}
