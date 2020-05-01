package com.nicholas.latres;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String lagi;
        int pil;
        boolean exit = false;
        Scanner scn = new Scanner(System.in);
        do {
            System.out.println("========== Menu ==========");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Lihat Data Mahasiswa");
            System.out.println("3. Edit Data Mahasiswa");
            System.out.println("4. Hapus Data Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilihan : ");
            pil = scn.nextInt();
            if (pil > 5) throw new IllegalArgumentException("Pilihan Tidak Ada di MENU!");

            String url = "jdbc:mysql://localhost:3306/praktikum";
            String username = "root";
            String password = "";
            try (Connection con = DriverManager.getConnection(url, username, password)) {
                Statement stmt = con.createStatement();
                switch (pil) {
                    case 1:
                        System.out.println("===== Menu Tambah Data =====");
                        System.out.print("\tNIM      : ");
                        int nim = scn.nextInt();
                        scn.nextLine();
                        System.out.print("\tNama     : ");
                        String nama = scn.nextLine();
                        System.out.print("\tAlamat   : ");
                        String alamat = scn.nextLine();

                        System.out.println("\n================= Daftar Fakultas ==================");
                        System.out.println("|\tKode\t|" + "   Nama Fakultas");
                        System.out.println("----------------------------------------------------");
                        String bacaFakultas = "SELECT * FROM fakultas";
                        ResultSet rs = stmt.executeQuery(bacaFakultas);
                        int[] listFakultas = new int[5];
                        int x = 0;
                        while (rs.next()) {
                            listFakultas[x] = rs.getInt(1);
                            System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(2));
                            x++;
                        }
                        System.out.println("----------------------------------------------------");
                        System.out.print("Kode Fakultas : ");
                        int idFakutas = scn.nextInt();
                        boolean ketemu = false;
                        for (int i : listFakultas) {
                            if (idFakutas == i) {
                                ketemu = true;
                                break;
                            }
                        }
                        if (!ketemu) throw new IllegalStateException("Kode Fakultas Tidak Ada dalam Daftar!");
                        System.out.println("\n=================== Daftar Prodi ===================");
                        System.out.println("|\tKode\t|" + "   Nama Prodi");
                        System.out.println("----------------------------------------------------");
                        String bacaProdi = "SELECT * FROM prodi WHERE ID_Fakultas =" + idFakutas;
                        rs = stmt.executeQuery(bacaProdi);
                        int[] listProdi = new int[21];
                        x = 0;
                        while (rs.next()) {
                            listProdi[x] = rs.getInt(1);
                            System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(3));
                            x++;
                        }
                        System.out.println("----------------------------------------------------");
                        System.out.print("Kode Prodi    : ");
                        int idProdi = scn.nextInt();
                        ketemu = false;
                        for (int i : listProdi) {
                            if (idProdi == i) {
                                ketemu = true;
                                break;
                            }
                        }
                        if (!ketemu) throw new IllegalStateException("Kode Prodi Tidak Ada dalam Daftar!");
                        scn.nextLine();
                        DAO masuk = new DAO(nim, nama, alamat, idFakutas, idProdi);
                        masuk.insertMahasiswa();
                        break;
                    case 2:
                        System.out.println("\t========== Menu Tapilkan ==========");
                        System.out.println("\t1. Tampilkan Semua Data");
                        System.out.println("\t2. Cari Data Berdasarkan NIM");
                        System.out.println("\t3. Cari Data Berdasarkan Nama/ Alamat");
                        System.out.println("\t4. Cari Data Berdasarkan Kode Fakultas");
                        System.out.println("\t5. Cari Data Berdasarkan Kode Prodi");
                        System.out.println("\t6. Data Kode Fakultas/ Kode Prodi");
                        System.out.print("\tPilihan : ");
                        pil = scn.nextInt();
                        scn.nextLine();
                        System.out.println("========== Data Mahasiswa ==========");
                        if (pil == 1) {
                            System.out.println("\n=================================================== Daftar Mahasiswa ====================================================");
                            System.out.printf("%-15.15s  %-20.20s %-20.20s %-20.20s %-15.15s%n", "|\tNIM\t\t\t|", "\tNama\t\t\t|", "\tAlamat\t\t\t|", "\tFakultas\t|", "\tProdi\t|");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                            String bacaMahasiswa = "SELECT NIM,mahasiswa.Nama,Alamat,fakultas.Nama,prodi.Nama FROM mahasiswa LEFT JOIN fakultas ON mahasiswa.ID_Fakultas = fakultas.ID_Fakultas LEFT JOIN prodi ON mahasiswa.ID_Prodi = prodi.ID_Prodi";
                            rs = stmt.executeQuery(bacaMahasiswa);
                            while (rs.next()) {
                                System.out.printf("%-16.16s  %-20.20s %-20.20s %-20.20s %-20.20s%n", "|\t" + rs.getInt(1) + "   |", "     " + rs.getString(2) + "\t\t\t|", "\t" + rs.getString(3), "\t|\t" + rs.getString(4), "\t|\t" + rs.getString(5) + "\t|");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                        } else if (pil == 2) {
                            System.out.print("Masukkan NIM : ");
                            int angka = scn.nextInt();
                            DAO cari1 = new DAO(angka);
                            cari1.selectNim();
                        } else if (pil == 3) {
                            System.out.print("Masukkan Nama/ Alamat : ");
                            String tulisan = scn.nextLine();
                            DAO cari2 = new DAO(tulisan, tulisan);
                            cari2.selectNama();
                        } else if (pil == 4) {
                            System.out.println("\n================= Daftar Fakultas ==================");
                            System.out.println("|\tKode\t|" + "   Nama Fakultas");
                            System.out.println("----------------------------------------------------");
                            bacaFakultas = "SELECT * FROM fakultas";
                            rs = stmt.executeQuery(bacaFakultas);
                            listFakultas = new int[5];
                            x = 0;
                            while (rs.next()) {
                                listFakultas[x] = rs.getInt(1);
                                System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(2));
                                x++;
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.print("\tMasukkan Kode Faultas");
                            idFakutas = scn.nextInt();
                            ketemu = false;
                            for (int i : listFakultas) {
                                if (idFakutas == i) {
                                    ketemu = true;
                                    break;
                                }
                            }
                            if (!ketemu) throw new IllegalStateException("Kode Fakultas Tidak Ada dalam Daftar!");
                            else {
                                System.out.println("\n=================================================== Daftar Mahasiswa ====================================================");
                                System.out.printf("%-15.15s  %-20.20s %-20.20s %-20.20s %-15.15s%n", "|\tNIM\t\t\t|", "\tNama\t\t\t|", "\tAlamat\t\t\t|", "\tFakultas\t|", "\tProdi\t|");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                String bacaMahasiswa = "SELECT NIM,mahasiswa.Nama,Alamat,fakultas.Nama,prodi.Nama FROM mahasiswa LEFT JOIN fakultas ON mahasiswa.ID_Fakultas = fakultas.ID_Fakultas LEFT JOIN prodi ON mahasiswa.ID_Prodi = prodi.ID_Prodi WHERE ID_Fakultas=" + idFakutas;
                                rs = stmt.executeQuery(bacaMahasiswa);
                                while (rs.next()) {
                                    System.out.printf("%-16.16s  %-20.20s %-20.20s %-20.20s %-20.20s%n", "|\t" + rs.getInt(1) + "   |", "     " + rs.getString(2) + "\t\t\t|", "\t" + rs.getString(3), "\t|\t" + rs.getString(4), "\t|\t" + rs.getString(5) + "\t|");
                                }
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                            }
                        } else if (pil == 5) {
                            System.out.println("\n================= Daftar Fakultas ==================");
                            System.out.println("|\tKode\t|" + "   Nama Fakultas");
                            System.out.println("----------------------------------------------------");
                            bacaFakultas = "SELECT * FROM fakultas";
                            rs = stmt.executeQuery(bacaFakultas);
                            listFakultas = new int[5];
                            x = 0;
                            while (rs.next()) {
                                listFakultas[x] = rs.getInt(1);
                                System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(2));
                                x++;
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.print("Kode Fakultas : ");
                            idFakutas = scn.nextInt();
                            ketemu = false;
                            for (int i : listFakultas) {
                                if (idFakutas == i) {
                                    ketemu = true;
                                    break;
                                }
                            }
                            if (!ketemu) throw new IllegalStateException("Kode Fakultas Tidak Ada dalam Daftar!");
                            System.out.println("\n=================== Daftar Prodi ===================");
                            System.out.println("|\tKode\t|" + "   Nama Prodi");
                            System.out.println("----------------------------------------------------");
                            bacaProdi = "SELECT * FROM prodi WHERE ID_Fakultas =" + idFakutas;
                            rs = stmt.executeQuery(bacaProdi);
                            listProdi = new int[21];
                            x = 0;
                            while (rs.next()) {
                                listProdi[x] = rs.getInt(1);
                                System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(3));
                                x++;
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.print("Kode Prodi    : ");
                            idProdi = scn.nextInt();
                            ketemu = false;
                            for (int i : listProdi) {
                                if (idProdi == i) {
                                    ketemu = true;
                                    break;
                                }
                            }
                            if (!ketemu) throw new IllegalStateException("Kode Prodi Tidak Ada dalam Daftar!");
                            else {
                                System.out.println("\n=================================================== Daftar Mahasiswa ====================================================");
                                System.out.printf("%-15.15s  %-20.20s %-20.20s %-20.20s %-15.15s%n", "|\tNIM\t\t\t|", "\tNama\t\t\t|", "\tAlamat\t\t\t|", "\tFakultas\t|", "\tProdi\t|");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                String bacaMahasiswa = "SELECT NIM,mahasiswa.Nama,Alamat,fakultas.Nama,prodi.Nama FROM mahasiswa LEFT JOIN fakultas ON mahasiswa.ID_Fakultas = fakultas.ID_Fakultas LEFT JOIN prodi ON mahasiswa.ID_Prodi = prodi.ID_Prodi WHERE ID_Fakultas=" + idProdi;
                                rs = stmt.executeQuery(bacaMahasiswa);
                                while (rs.next()) {
                                    System.out.printf("%-16.16s  %-20.20s %-20.20s %-20.20s %-20.20s%n", "|\t" + rs.getInt(1) + "   |", "     " + rs.getString(2) + "\t\t\t|", "\t" + rs.getString(3), "\t|\t" + rs.getString(4), "\t|\t" + rs.getString(5) + "\t|");
                                }
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                            }
                        } else if (pil == 6) {
                            System.out.println("\n================= Daftar Fakultas ==================");
                            System.out.println("|\tKode\t|" + "   Nama Fakultas");
                            System.out.println("----------------------------------------------------");
                            bacaFakultas = "SELECT * FROM fakultas";
                            rs = stmt.executeQuery(bacaFakultas);
                            listFakultas = new int[5];
                            x = 0;
                            while (rs.next()) {
                                listFakultas[x] = rs.getInt(1);
                                System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(2));
                                x++;
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.print("Lihat Daftar Prodi?");
                            String pilihan = scn.nextLine().toLowerCase();
                            if (pilihan.equals("y")) {
                                System.out.println("\n");
                                System.out.print("Kode Fakultas : ");
                                idFakutas = scn.nextInt();
                                ketemu = false;
                                for (int i : listFakultas) {
                                    if (idFakutas == i) {
                                        ketemu = true;
                                        break;
                                    }
                                }
                                if (!ketemu) throw new IllegalStateException("Kode Fakultas Tidak Ada dalam Daftar!");
                                System.out.println("\n=================== Daftar Prodi ===================");
                                System.out.println("|\tKode\t|" + "   Nama Prodi");
                                System.out.println("----------------------------------------------------");
                                bacaProdi = "SELECT * FROM prodi WHERE ID_Fakultas =" + idFakutas;
                                rs = stmt.executeQuery(bacaProdi);
                                while (rs.next()) {
                                    System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(3));
                                }
                                System.out.println("----------------------------------------------------");
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                        break;
                    case 3:
                        System.out.println("========== Menu Edit Data ==========");
                        System.out.print("Masukkan NIM :");
                        nim = scn.nextInt();
                        System.out.println("======== Data Lama ========");
                        DAO cari1 = new DAO(nim);
                        cari1.selectNim();
                        System.out.println("\n======== Masukkan Data Baru ========");
                        System.out.print("NIM     : ");
                        nim = scn.nextInt();
                        scn.nextLine();
                        System.out.print("\tNama     : ");
                        nama = scn.nextLine();
                        System.out.print("\tAlamat   : ");
                        alamat = scn.nextLine();
                        System.out.println("\n================= Daftar Fakultas ==================");
                        System.out.println("|\tKode\t|" + "   Nama Fakultas");
                        System.out.println("----------------------------------------------------");
                        bacaFakultas = "SELECT * FROM fakultas";
                        rs = stmt.executeQuery(bacaFakultas);
                        listFakultas = new int[5];
                        x = 0;
                        while (rs.next()) {
                            listFakultas[x] = rs.getInt(1);
                            System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(2));
                            x++;
                        }
                        System.out.println("----------------------------------------------------");
                        System.out.print("Kode Fakultas : ");
                        idFakutas = scn.nextInt();
                        ketemu = false;
                        for (int i : listFakultas) {
                            if (idFakutas == i) {
                                ketemu = true;
                                break;
                            }
                        }
                        if (!ketemu) throw new IllegalStateException("Kode Fakultas Tidak Ada dalam Daftar!");
                        System.out.println("\n=================== Daftar Prodi ===================");
                        System.out.println("|\tKode\t|" + "   Nama Prodi");
                        System.out.println("----------------------------------------------------");
                        bacaProdi = "SELECT * FROM prodi WHERE ID_Fakultas =" + idFakutas;
                        rs = stmt.executeQuery(bacaProdi);
                        listProdi = new int[21];
                        x = 0;
                        while (rs.next()) {
                            listProdi[x] = rs.getInt(1);
                            System.out.println("|\t" + rs.getInt(1) + "\t\t|\t" + rs.getString(3));
                            x++;
                        }
                        System.out.println("----------------------------------------------------");
                        System.out.print("Kode Prodi    : ");
                        idProdi = scn.nextInt();
                        ketemu = false;
                        for (int i : listProdi) {
                            if (idProdi == i) {
                                ketemu = true;
                                break;
                            }
                        }
                        if (!ketemu) throw new IllegalStateException("Kode Prodi Tidak Ada dalam Daftar!");
                        scn.nextLine();
                        DAO dao = new DAO(nim, nama, alamat, idFakutas, idProdi);
                        dao.updateMahasiswa();
                        break;
                    case 4:
                        System.out.println("========== Menu Hapus Data ==========");
                        System.out.print("Masukkan NIM : ");
                        nim = scn.nextInt();
                        DAO delete = new DAO(nim);
                        delete.deleteMahasiswa();
                        break;
                    case 5:
                        System.exit(1);
                        break;
                    default:
                        exit = false;
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Tidak dapat terkoneksi ke DataBase!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                scn.nextLine();
                System.out.print("\nKembali ke Menu? ");
                lagi = scn.nextLine().toLowerCase();
                System.out.println("\n");
                if (lagi.equals("n")) {
                    System.out.println("System Closed");
                    exit = true;
                }
            }
        } while (!exit);
    }
}
