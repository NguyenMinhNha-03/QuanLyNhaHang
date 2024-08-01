package Model;

public class Menu {
	private String maMonAn;
	private String hinhAnhMonAn;
	private String tenMonAn;
	private int giaBan;
	private String loaiMA;
	
	public String getLoaiMA() {
		return loaiMA;
	}
	public void setLoaiMA(String loaiMA) {
		this.loaiMA = loaiMA;
	}
	public String getMaMonAn() {
		return maMonAn;
	}
	public void setMaMonAn(String maMonAn) {
		this.maMonAn = maMonAn;
	}
	public String getHinhAnhMonAn() {
		return hinhAnhMonAn;
	}
	public void setHinhAnhMonAn(String hinhAnhMonAn) {
		this.hinhAnhMonAn = hinhAnhMonAn;
	}
	public String getTenMonAn() {
		return tenMonAn;
	}
	public void setTenMonAn(String tenMonAn) {
		this.tenMonAn = tenMonAn;
	}
	public int getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(String maMonAn, String hinhAnhMonAn, String tenMonAn, int giaBan) {
		super();
		this.maMonAn = maMonAn;
		this.hinhAnhMonAn = hinhAnhMonAn;
		this.tenMonAn = tenMonAn;
		this.giaBan = giaBan;
	}
}
