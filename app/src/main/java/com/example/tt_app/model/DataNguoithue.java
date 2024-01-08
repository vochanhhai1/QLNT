package com.example.tt_app.model;

import java.io.Serializable;

public class DataNguoithue implements Serializable {
    private Integer id_dichvu;
    private String hovaten;
    private Integer sodienthoai;
    private String chonphong;
    private String email;
    private String ngaysinh;
    private Integer cmnd;
    private String ngaycap;
    private String noicap;
    private String diachi;
   private String anhcm;
   private Integer maphong;

    public DataNguoithue(Integer id_dichvu, String hovaten, Integer sodienthoai, String chonphong, String email, String ngaysinh, Integer cmnd, String ngaycap, String noicap, String diachi, String anhcm, Integer maphong) {
        this.id_dichvu = id_dichvu;
        this.hovaten = hovaten;
        this.sodienthoai = sodienthoai;
        this.chonphong = chonphong;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.cmnd = cmnd;
        this.ngaycap = ngaycap;
        this.noicap = noicap;
        this.diachi = diachi;
        this.anhcm = anhcm;
        this.maphong = maphong;
    }

    public Integer getId_dichvu() {
        return id_dichvu;
    }

    public void setId_dichvu(Integer id_dichvu) {
        this.id_dichvu = id_dichvu;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public Integer getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(Integer sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getChonphong() {
        return chonphong;
    }

    public void setChonphong(String chonphong) {
        this.chonphong = chonphong;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public Integer getCmnd() {
        return cmnd;
    }

    public void setCmnd(Integer cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getAnhcm() {
        return anhcm;
    }

    public void setAnhcm(String anhcm) {
        this.anhcm = anhcm;
    }

    public Integer getMaphong() {
        return maphong;
    }

    public void setMaphong(Integer maphong) {
        this.maphong = maphong;
    }
}
