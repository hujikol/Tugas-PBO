package com.nicholas.latres;

import java.sql.*;

public class DAO extends Mahasiswa {
    private Connection con;
    private final String insertMhs = "INSERT INTO mahasiswa" + "(NIM, Nama, Alamat, ID_Fakultas, ID_Prodi) VALUES" + "(?,?,?,?,?)";

    private final String updateMhs = "UPDATE mahasiswa SET Nama=?,Alamat=?,ID_Fakultas=?,ID_Prodi=? WHERE NIM=?";

    private final String deleteMhs = "DELETE FROM mahasiswa WHERE NIM=?";

    private final String selectNama = "SELECT * FROM mahasiswa WHERE Nama=? OR Alamat=?";

    private final String selectNim = "SELECT * FROM mahasiswa WHERE NIM=?";

    private final String selectFakultas = "SELECT * FROM fakultas";

    private final String selectProdi = "SELECT * FROM prodi";


    public DAO(int nim, String nama, String alamat, int idFakultas, int idProdi) {
        super(nim, nama, alamat, idFakultas, idProdi);
    }

    public DAO(int nim) {
        super(nim);
    }

    public DAO(String nama, String alamat) {
        super(nama, alamat);
    }

    public void insertMahasiswa() throws SQLException {

        PreparedStatement statement;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktikum", "root", "");
            con.setAutoCommit(false);
            statement = con.prepareStatement(insertMhs);
            statement.setInt(1, super.getNim());
            statement.setString(2, super.getNama());
            statement.setString(3, super.getAlamat());
            statement.setInt(4, super.getIdFakultas());
            statement.setInt(5, super.getIdProdi());
            int i = statement.executeUpdate();
            con.commit();
            if (i > 0) throw new IllegalStateException("Data Berhasil Di Masukkan!");
            else throw new SQLException("Data Gagal di Update!");
        } catch (SQLIntegrityConstraintViolationException exception) {
            System.err.println("Data Dengan NIM Tersebut Sudah Ada!");
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
    }

    public void updateMahasiswa() throws SQLException {

        PreparedStatement statement;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktikum", "root", "");
            con.setAutoCommit(false);
            statement = con.prepareStatement(updateMhs);
            statement.setString(1, super.getNama());
            statement.setString(2, super.getAlamat());
            statement.setInt(3, super.getIdFakultas());
            statement.setInt(4, super.getIdProdi());
            statement.setInt(5, super.getNim());
            int i = statement.executeUpdate();
            con.commit();
            if (i > 0) throw new IllegalStateException("Data Berhasil Di Update!");
            else throw new SQLException("Data Gagal di Update!");
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
    }

    public void selectNim() throws SQLException {
        PreparedStatement statement;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktikum", "root", "");
            con.setAutoCommit(false);
            statement = con.prepareStatement(selectNim);
            statement.setInt(1, super.getNim());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("\tNIM       : " + rs.getInt(1));
                System.out.println("\tNama      : " + rs.getString(2));
                System.out.println("\tAlamat    : " + rs.getString(3));
                int fakultas = rs.getInt(4);
                int prodi = rs.getInt(5);
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(selectFakultas);
                while (rs.next()) {
                    if (rs.getInt(1) == fakultas) {
                        System.out.println("\tFakultas  : " + rs.getString(2));
                        break;
                    }
                }
                rs = stmt.executeQuery(selectProdi);
                while (rs.next()) {
                    if (rs.getInt(1) == prodi) {
                        System.out.println("\tProdi     : " + rs.getString(3));
                        break;
                    }
                }
            } else {
                throw new IllegalArgumentException("Mahasiswa dengan Nim tersebut Tidak ada!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            con.close();
        }
    }

    public void selectNama() throws SQLException {
        PreparedStatement statement;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktikum", "root", "");
            con.setAutoCommit(false);
            statement = con.prepareStatement(selectNama);
            statement.setString(1, super.getNama());
            statement.setString(2, super.getAlamat());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("\tNIM       : " + rs.getInt(1));
                System.out.println("\tNama      : " + rs.getString(2));
                System.out.println("\tAlamat    : " + rs.getString(3));
                int fakultas = rs.getInt(4);
                int prodi = rs.getInt(5);
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(selectFakultas);
                while (rs.next()) {
                    if (rs.getInt(1) == fakultas) {
                        System.out.println("\tFakultas  : " + rs.getString(2));
                        break;
                    }
                }
                rs = stmt.executeQuery(selectProdi);
                while (rs.next()) {
                    if (rs.getInt(1) == prodi) {
                        System.out.println("\tProdi     : " + rs.getString(3));
                        break;
                    }
                }
            } else {
                throw new IllegalArgumentException("Mahasiswa dengan Nama/ Alamat tersebut Tidak ada!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            con.close();
        }
    }

    public void deleteMahasiswa() throws SQLException {
        PreparedStatement statement;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktikum", "root", "");
            con.setAutoCommit(false);
            statement = con.prepareStatement(deleteMhs);
            statement.setInt(1, super.getNim());
            int i = statement.executeUpdate();
            con.commit();
            if (i > 0) throw new IllegalStateException("Data Berhasil Di Hapus!");
            else throw new SQLException("Data Gagal di Hapus!");
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
    }
}
