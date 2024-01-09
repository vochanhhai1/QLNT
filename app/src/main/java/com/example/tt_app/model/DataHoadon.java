package com.example.tt_app.model;

import java.io.Serializable;

public class DataHoadon implements Serializable {
    private Integer idhoadon;
    private Integer id_nguoithue;
    private Integer maphong;
    private Integer id_dichvu;
    private  String hoadonthang;
    private  String ngaythanhtoan;
    private  String hanthanhtoan;
    private  Integer giamgia;
    private Double total;
    private String ghichu;
    private String ngayxuathoadon;

    public Integer getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(Integer idhoadon) {
        this.idhoadon = idhoadon;
    }

    public Integer getId_nguoithue() {
        return id_nguoithue;
    }

    public void setId_nguoithue(Integer id_nguoithue) {
        this.id_nguoithue = id_nguoithue;
    }

    public Integer getMaphong() {
        return maphong;
    }

    public void setMaphong(Integer maphong) {
        this.maphong = maphong;
    }

    public Integer getId_dichvu() {
        return id_dichvu;
    }

    public void setId_dichvu(Integer id_dichvu) {
        this.id_dichvu = id_dichvu;
    }

    public String getHoadonthang() {
        return hoadonthang;
    }

    public void setHoadonthang(String hoadonthang) {
        this.hoadonthang = hoadonthang;
    }

    public String getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(String ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public String getHanthanhtoan() {
        return hanthanhtoan;
    }

    public void setHanthanhtoan(String hanthanhtoan) {
        this.hanthanhtoan = hanthanhtoan;
    }

    public Integer getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(Integer giamgia) {
        this.giamgia = giamgia;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getNgayxuathoadon() {
        return ngayxuathoadon;
    }

    public void setNgayxuathoadon(String ngayxuathoadon) {
        this.ngayxuathoadon = ngayxuathoadon;
    }
}
