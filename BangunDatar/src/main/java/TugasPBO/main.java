package TugasPBO;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Kubus persegi = new Kubus();
        persegi.setSisi(5);//set sisi 5
        System.out.print("===== Persegi -> Kubus =====\n");
        System.out.print("Keliling   : " + persegi.keliling() + "\n");
        System.out.print("Luas       : " + persegi.luas() + "\n");
        System.out.print("Volume     : " + persegi.volume() + "\n\n");

        Silinder lingkaran = new Silinder();
        lingkaran.setJariJari(9);//set r 9
        lingkaran.setTinggi(18);//set tinggi 18
        System.out.print("===== Lingkaran -> Silinder =====\n");
        System.out.print("Keliling   : " + lingkaran.keliling() + "\n");
        System.out.print("Luas       : " + lingkaran.luas() + "\n");
        System.out.print("Volume     : " + lingkaran.volume() + "\n\n");

        Balok sPanjang = new Balok();
        sPanjang.setLebar(9);//set lebar 9
        sPanjang.setPanjang(13);//set panjang 13
        sPanjang.setTinggi(17);//set tinggi 17
        System.out.print("===== Persegi Panjang -> Balok =====\n");
        System.out.print("Keliling   : " + sPanjang.keliling() + "\n");
        System.out.print("Luas       : " + sPanjang.luas() + "\n");
        System.out.print("Volume     : " + sPanjang.volume() + "\n\n");

        SegiTiga s3 = new SegiTiga();
        s3.setAlas(34);
        s3.setTinggi(56);
        System.out.print("===== SegiTiga =====\n");
        System.out.print("Keliling  : " + s3.keliling() + "\n");
        System.out.print("Luas      : " + s3.luas());

    }
}
