package AppQLGV.Giao__vien;

public class Gv_BienChe extends Giao_Vien implements Luong {
    private float hsLuongNgachBac;
    //phuong thuc khoi tao co tham so cua lop Gv_BienChe
    public Gv_BienChe(String tenGv, String Sdt, String donVi, int namVe, float hsLuongNgachBac)
    {
        super(tenGv,Sdt,donVi,namVe);
        this.hsLuongNgachBac = hsLuongNgachBac;
    }
    //
    public Gv_BienChe(String tenGv, String Sdt, String donVi)
    {
        super(tenGv,Sdt,donVi);
    }
    //phuong thuc khoi tao khong tham so cua lop Gv_BienChe
    public Gv_BienChe() {
    }
    //getter&setter cua Gv_BienChe
    public float getHsLuongNgachBac() {
        return hsLuongNgachBac;
    }
    public void setHsLuongNgachBac(float hsLuongNgachBac) {
        this.hsLuongNgachBac = hsLuongNgachBac;
    }
    //thuc thi giao dien Luong
    @Override
    public float tinh_Luong() {
        return hsLuongNgachBac * 3000000;
    }
}
