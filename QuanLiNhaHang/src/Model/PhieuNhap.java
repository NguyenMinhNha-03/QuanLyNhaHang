package Model;

import java.sql.Date;

public class PhieuNhap {
	private String maPN;
	private Date ngayNhap;
	private int tongTienNhap;
	private String maNCC;
	public String getMaPN() {
		return maPN;
	}
	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public int getTongTienNhap() {
		return tongTienNhap;
	}
	public void setTongTienNhap(int tongTienNhap) {
		this.tongTienNhap = tongTienNhap;
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public PhieuNhap(String maPN, Date ngayNhap, int tongTienNhap, String maNCC) {
		super();
		this.maPN = maPN;
		this.ngayNhap = ngayNhap;
		this.tongTienNhap = tongTienNhap;
		this.maNCC = maNCC;
	}
	public PhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
