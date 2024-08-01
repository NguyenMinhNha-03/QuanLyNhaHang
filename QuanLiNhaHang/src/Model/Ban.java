package Model;

public class Ban {
	int maban,soban;
	String kiemTraBanString;
	public Ban(int maban, int soban, String kiemTraBanString) {
		super();
		this.maban = maban;
		this.soban = soban;
		this.kiemTraBanString = kiemTraBanString;
	}
	public int getMaban() {
		return maban;
	}
	public void setMaban(int maban) {
		this.maban = maban;
	}
	public int getSoban() {
		return soban;
	}
	public void setSoban(int soban) {
		this.soban = soban;
	}
	public String getKiemTraBanString() {
		return kiemTraBanString;
	}
	public void setKiemTraBanString(String kiemTraBanString) {
		this.kiemTraBanString = kiemTraBanString;
	}
}
