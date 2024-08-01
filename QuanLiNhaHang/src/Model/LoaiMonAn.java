package Model;

public class LoaiMonAn {
	private String maLoai;
    private String tenLoai;

    public LoaiMonAn(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    @Override
    public String toString() {
        return tenLoai;
    }
}
