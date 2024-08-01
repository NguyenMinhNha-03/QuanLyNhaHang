package Model;

public class NguyenLieu {
	private String maNguyenLieu, tenNguyenLieu, donViTinh;

	public String getMaNguyenLieu() {
		return maNguyenLieu;
	}

	public void setMaNguyenLieu(String maNguyenLieu) {
		this.maNguyenLieu = maNguyenLieu;
	}

	public String getTenNguyenLieu() {
		return tenNguyenLieu;
	}

	public void setTenNguyenLieu(String tenNguyenLieu) {
		this.tenNguyenLieu = tenNguyenLieu;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public NguyenLieu(String maNguyenLieu, String tenNguyenLieu, String donViTinh) {
		super();
		this.maNguyenLieu = maNguyenLieu;
		this.tenNguyenLieu = tenNguyenLieu;
		this.donViTinh = donViTinh;
	}

	public NguyenLieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
