package com.nicholas.latres;

public class Mahasiswa {
    private int nim;
    private String nama;
    private String alamat;
    private int idFakultas;
    private int idProdi;

    public Mahasiswa(int nim, String nama, String alamat, int idFakultas, int idProdi) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.idFakultas = idFakultas;
        this.idProdi = idProdi;
    }

    public Mahasiswa(int nim) {
        this.nim = nim;
    }

    public Mahasiswa(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public int getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getIdFakultas() {
        return idFakultas;
    }

    public int getIdProdi() {
        return idProdi;
    }

}
