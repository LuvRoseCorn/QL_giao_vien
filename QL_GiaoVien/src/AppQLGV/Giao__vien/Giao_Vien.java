package AppQLGV.Giao__vien;

public class Giao_Vien {
    protected String ten, SDT, donViChuyenMon ;
    protected int namVeTruong;
    //phuong thuc khoi tao co tham so cua lop Giao_Vien
    public Giao_Vien(String ten, String SDT, String donViChuyenMon, int namVeTruong)
    {
        this.ten = ten;
        this.SDT = SDT;
        this.donViChuyenMon = donViChuyenMon;
        this.namVeTruong = namVeTruong;
    }
    //
    public Giao_Vien(String tenGv, String Sdt, String donVi)
    {
        ten = tenGv;
        SDT = Sdt;
        donViChuyenMon = donVi;
    }
    //
    public Giao_Vien(){
    }
    //getter & Setter cua ten
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    //getter & Setter cua SDT
    public String getSDT() {
        return SDT;
    }
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    //getter & Setter cua donViChuyenMon
    public String getDonViChuyenMon() {
        return donViChuyenMon;
    }
    public void setDonViChuyenMon(String donViChuyenMon) {
        this.donViChuyenMon = donViChuyenMon;
    }
    //getter & Setter cua namVeTruong
    public int getNamVeTruong() {
        return namVeTruong;
    }
    public void setNamVeTruong(int namVeTruong) {
        this.namVeTruong = namVeTruong;
    }
}
