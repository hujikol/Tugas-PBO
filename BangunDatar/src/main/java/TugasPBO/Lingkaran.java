package TugasPBO;

public class Lingkaran extends BangunDatar {

    private double r;

    public double getJariJari() {
        return r;
    }

    public void setJariJari(double jariJari) {
        this.r = jariJari;
    }

    @Override
    public double keliling() {
        return 2 * Math.PI * this.r;
    }

    @Override
    public double luas() {
        return Math.pow(this.r, 2.0) * Math.PI;
    }

}
