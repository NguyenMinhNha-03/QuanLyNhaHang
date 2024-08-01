package Model;

import java.sql.Date;

public class HoaDon {
	int maHD;
	Date ngayLapHD;
	int tongTien;
	

	public HoaDon(int maHD, Date ngayLapHD, int tongTien) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
	}

	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public Date getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	
}
