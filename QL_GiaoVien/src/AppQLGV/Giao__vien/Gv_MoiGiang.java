package AppQLGV.Giao__vien;

public class Gv_MoiGiang extends Giao_Vien implements Luong {
    private float soGioGiangThuc;
    //phuong thuc khoi tao co tham so cua lop Gv_MoiGiang
    public Gv_MoiGiang(String tenGv, String Sdt, String donVi, int namVe, float soGioGiangThuc)
    {
        super(tenGv,Sdt,donVi,namVe);
        this.soGioGiangThuc = soGioGiangThuc;
    }
    //
    public Gv_MoiGiang(String tenGv, String Sdt, String donVi)
    {
        super(tenGv,Sdt,donVi);
    }
    //phuong thuc khoi tao khong tham so cua lop Gv_MoiGiang
    public Gv_MoiGiang() {
    }
    //getter&setter cua Gv_MoiGiang
    public float getSoGioGiangThuc() {
        return soGioGiangThuc;
    }
    public void setSoGioGiangThuc(float soGioGiangThuc) {
        this.soGioGiangThuc = soGioGiangThuc;
    }
    //thuc thi giao dien Luong
    @Override
    public float tinh_Luong() {
        return soGioGiangThuc * 500000 + 1000000;
    }
}

