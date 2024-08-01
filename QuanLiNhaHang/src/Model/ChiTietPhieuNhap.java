package Model;

import java.sql.Date;

public class ChiTietPhieuNhap {
	private String maCTPN,maNL,maPN,maNV;
	private Date hanSuDung;
	private int soLuongNhap,donGiaNhap,thanhTienNhap;
	public String getMaCTPN() {
		return maCTPN;
	}
	public void setMaCTPN(String maCTPN) {
		this.maCTPN = maCTPN;
	}
	public String getMaNL() {
		return maNL;
	}
	public void setMaNL(String maNL) {
		this.maNL = maNL;
	}
	public String getMaPN() {
		return maPN;
	}
	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	public int getDonGiaNhap() {
		return donGiaNhap;
	}
	public void setDonGiaNhap(int donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}
	public int getThanhTienNhap() {
		return thanhTienNhap;
	}
	public void setThanhTienNhap(int thanhTienNhap) {
		this.thanhTienNhap = thanhTienNhap;
	}
	public ChiTietPhieuNhap(String maCTPN, String maNL, String maPN, String maNV, Date hanSuDung, int soLuongNhap,
			int donGiaNhap, int thanhTienNhap) {
		super();
		this.maCTPN = maCTPN;
		this.maNL = maNL;
		this.maPN = maPN;
		this.maNV = maNV;
		this.hanSuDung = hanSuDung;
		this.soLuongNhap = soLuongNhap;
		this.donGiaNhap = donGiaNhap;
		this.thanhTienNhap = thanhTienNhap;
	}
	public ChiTietPhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
