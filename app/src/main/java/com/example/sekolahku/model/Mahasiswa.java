package com.example.sekolahku.model;

import java.util.Date;

public class Mahasiswa {

    private int id;
    private String nama;
    private String nim;
    private String tanggal_lahir;
    private String kelamin;
    private String alamat;

    public Mahasiswa() {
    }

    public Mahasiswa(int id, String nama, String nim, String tanggal_lahir, String kelamin, String alamat) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.tanggal_lahir = tanggal_lahir;
        this.kelamin = kelamin;
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "Nama :" + nama + "\nNIM :" + nim ;
    }

    /*
        @Override
    public String toString() {
        return "Mahasiswa{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", nim='" + nim + '\'' +
                ", tanggal_lahir='" + tanggal_lahir + '\'' +
                ", kelamin='" + kelamin + '\'' +
                ", alamat='" + alamat + '\'' +
                '}';
    }

     */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getTanggalLahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


}
