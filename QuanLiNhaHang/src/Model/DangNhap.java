package Model;

public class DangNhap {
	private String userName;
	private String password;
	private int quyenTruyCap;
	private String maNhanVien;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getQuyenTruyCap() {
		return quyenTruyCap;
	}
	public void setQuyenTruyCap(int quyenTruyCap) {
		this.quyenTruyCap = quyenTruyCap;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public DangNhap(String userName, String password, int quyenTruyCap, String maNhanVien) {
		super();
		this.userName = userName;
		this.password = password;
		this.quyenTruyCap = quyenTruyCap;
		this.maNhanVien = maNhanVien;
	}
	public DangNhap()
	{
		
	}
}
