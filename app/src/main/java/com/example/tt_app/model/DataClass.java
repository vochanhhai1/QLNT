package com.example.tt_app.model;


import java.io.Serializable;

public class DataClass implements Serializable {
    private Integer id_dichvu;
    private String uploadTopic;
    private String uploadDonvido;
    private Integer uploadDichvu;
    private String uploadNote;

    private boolean isSelected;
    private byte[] uploadhinhanh;

    public DataClass(String uploadTopic, Integer uploadDichvu, boolean isSelected, byte[] uploadhinhanh) {
        this.uploadTopic = uploadTopic;
        this.uploadDichvu = uploadDichvu;
        this.isSelected = isSelected;
        this.uploadhinhanh = uploadhinhanh;
    }

    public DataClass(Integer id_dichvu, String uploadTopic, String uploadDonvido, Integer uploadDichvu, String uploadNote, byte[] uploadhinhanh) {
        this.id_dichvu = id_dichvu;
        this.uploadTopic = uploadTopic;
        this.uploadDonvido = uploadDonvido;
        this.uploadDichvu = uploadDichvu;
        this.uploadNote = uploadNote;
        this.uploadhinhanh = uploadhinhanh;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Integer getId_dichvu() {
        return id_dichvu;
    }

    public void setId_dichvu(Integer id_dichvu) {
        this.id_dichvu = id_dichvu;
    }

    public String getUploadTopic() {
        return uploadTopic;
    }

    public void setUploadTopic(String uploadTopic) {
        this.uploadTopic = uploadTopic;
    }

    public String getUploadDonvido() {
        return uploadDonvido;
    }

    public void setUploadDonvido(String uploadDonvido) {
        this.uploadDonvido = uploadDonvido;
    }

    public Integer getUploadDichvu() {
        return uploadDichvu;
    }

    public void setUploadDichvu(Integer uploadDichvu) {
        this.uploadDichvu = uploadDichvu;
    }

    public String getUploadNote() {
        return uploadNote;
    }

    public void setUploadNote(String uploadNote) {
        this.uploadNote = uploadNote;
    }

    public byte[] getUploadhinhanh() {
        return uploadhinhanh;
    }

    public void setUploadhinhanh(byte[] uploadhinhanh) {
        this.uploadhinhanh = uploadhinhanh;
    }
}