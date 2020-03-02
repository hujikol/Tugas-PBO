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
public class PersegiPanjang extends BangunDatar {

    private double panjang, lebar;

    public double getPanjang() {
        return panjang;
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public double getLebar() {
        return lebar;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    @Override
    public double keliling() {
        return (2 * this.lebar) + (2 * this.panjang);
    }

    @Override
    public double luas() {
        return this.lebar * this.panjang;
    }

}
