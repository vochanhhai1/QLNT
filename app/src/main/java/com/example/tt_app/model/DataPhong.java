package com.example.tt_app.model;

import java.io.Serializable;

public class DataPhong implements Serializable {
    int maphong;
    String phong;
    Double chiphithue;
    int dientich;
    int songuoithue;
    Double tiencoc;
    String doituong;
    String anhphong;
    int giadien;
    int gianuoc;
    String mota;
    String lydo;

    public DataPhong(int maphong, String phong, Double chiphithue, int dientich, int songuoithue, Double tiencoc, String doituong, String anhphong, int giadien, int gianuoc, String mota, String lydo) {
        this.maphong = maphong;
        this.phong = phong;
        this.chiphithue = chiphithue;
        this.dientich = dientich;
        this.songuoithue = songuoithue;
        this.tiencoc = tiencoc;
        this.doituong = doituong;
        this.anhphong = anhphong;
        this.giadien = giadien;
        this.gianuoc = gianuoc;
        this.mota = mota;
        this.lydo = lydo;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public Double getChiphithue() {
        return chiphithue;
    }

    public void setChiphithue(Double chiphithue) {
        this.chiphithue = chiphithue;
    }

    public int getDientich() {
        return dientich;
    }

    public void setDientich(int dientich) {
        this.dientich = dientich;
    }

    public int getSonguoithue() {
        return songuoithue;
    }

    public void setSonguoithue(int songuoithue) {
        this.songuoithue = songuoithue;
    }

    public Double getTiencoc() {
        return tiencoc;
    }

    public void setTiencoc(Double tiencoc) {
        this.tiencoc = tiencoc;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public String getAnhphong() {
        return anhphong;
    }

    public void setAnhphong(String anhphong) {
        this.anhphong = anhphong;
    }

    public int getGiadien() {
        return giadien;
    }

    public void setGiadien(int giadien) {
        this.giadien = giadien;
    }

    public int getGianuoc() {
        return gianuoc;
    }

    public void setGianuoc(int gianuoc) {
        this.gianuoc = gianuoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }
}
