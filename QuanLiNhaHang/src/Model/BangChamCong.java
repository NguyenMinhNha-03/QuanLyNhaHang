package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class BangChamCong {
    private int maChamCong;
    private String maNhanVien;
    private String caLam;
    private Timestamp gioBatDauLam;
    private Timestamp gioKetThucLam;
    private float soGioLam;

    // Constructor
    public BangChamCong(int maChamCong, String maNhanVien,  String caLam, Timestamp gioBatDauLam, Timestamp gioKetThucLam) {
        this.maChamCong = maChamCong;
        this.maNhanVien = maNhanVien;
        this.caLam = caLam;
        this.gioBatDauLam = gioBatDauLam;
        this.gioKetThucLam = gioKetThucLam;
    }

    // Getters and setters
    public int getMaChamCong() {
        return maChamCong;
    }

    public BangChamCong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setMaChamCong(int maChamCong) {
        this.maChamCong = maChamCong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }


    public String getCaLam() {
        return caLam;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }

    public Timestamp getGioBatDauLam() {
        return gioBatDauLam;
    }

    public void setGioBatDauLam(Timestamp gioBatDauLam) {
        this.gioBatDauLam = gioBatDauLam;
    }

    public Timestamp getGioKetThucLam() {
        return gioKetThucLam;
    }

    public void setGioKetThucLam(Timestamp gioKetThucLam) {
        this.gioKetThucLam = gioKetThucLam;
    }

    public float getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(float soGioLam) {
        this.soGioLam = soGioLam;
    }
}