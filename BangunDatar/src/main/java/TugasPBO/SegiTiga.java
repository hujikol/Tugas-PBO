package TugasPBO;

public class SegiTiga extends BangunDatar {

    private double tinggi, alas;

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    public double getAlas() {
        return alas;
    }

    public void setAlas(double alas) {
        this.alas = alas;
    }

    @Override
    public double keliling() {
        return Math.sqrt(Math.pow(this.alas / 2, 2) + Math.pow(this.tinggi, 2));
    }

    @Override
    public double luas() {
        return this.tinggi * (this.alas / 2);
    }

}
