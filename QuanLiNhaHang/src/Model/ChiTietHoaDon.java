package Model;

public class ChiTietHoaDon {
	private String maCTHD;
	private int maHD,maBan;
	private String maMA;
	private String maNVString;
	private int soLuong;
	private int thanhTien;
	public ChiTietHoaDon(String maCTHD, int maHD, int maBan, String maMA, String maNVString, int soLuong,
			int thanhTien) {
		super();
		this.maCTHD = maCTHD;
		this.maHD = maHD;
		this.maBan = maBan;
		this.maMA = maMA;
		this.maNVString = maNVString;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}
	public String getMaNVString() {
		return maNVString;
	}
	public void setMaNVString(String maNVString) {
		this.maNVString = maNVString;
	}
	public String getMaCTHD() {
		return maCTHD;
	}
	public void setMaCTHD(String maCTHD) {
		this.maCTHD = maCTHD;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public int getMaBan() {
		return maBan;
	}
	public void setMaBan(int maBan) {
		this.maBan = maBan;
	}
	public String getMaMA() {
		return maMA;
	}
	public void setMaMA(String maMA) {
		this.maMA = maMA;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon(String maCTHD, int maHD, int maBan, String maMA, int soLuong, int thanhTien) {
		super();
		this.maCTHD = maCTHD;
		this.maHD = maHD;
		this.maBan = maBan;
		this.maMA = maMA;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
