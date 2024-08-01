package Model;

import java.sql.Date;

public class NhanVien {
	private String maNhanVien,tenNhanVien,chucVu,gioiTinh;
	private int soDienThoai;
	private Date ngaySinh;
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public int getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(int soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public NhanVien(String maNhanVien, String tenNhanVien, Date ngaySinh, String chucVu, int soDienThoai,
			String gioiTinh) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
