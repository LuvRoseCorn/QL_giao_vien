package Lket_Dl;

import AppQLGV.Giao__vien.Gv_BienChe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//lớp để thao tác dữ liệu với database
public class DAO_gvBc {
    private Connection connection;//đối tượng của lớp Connection giúp liên kết với database
    ObservableList<Gv_BienChe> list = FXCollections.observableArrayList();//list danh sách các giáo viên quan sát được được lấy từ database

    public DAO_gvBc(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QL_GV;username=sa;password=chuyen149");
            //liên kết với database
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //phương thức thêm dữ liệu giáo viên
    public boolean addGvBc(Gv_BienChe gvBc){
        String sql = "INSERT INTO TBL_QL_GV_BC(ten1, sdt1, donVi1, namVe1, heSoLuong) "
                + "VALUES(?,?,?,?,?)";
        try {
            //nhập dữ liệu cho các thuộc tính cho database lấy từ đối tương giáo viên được truyền vào
            PreparedStatement ps = connection.prepareStatement(sql);//thực hiện câu truy vấn
            ps.setString(1, gvBc.getTen());
            ps.setString(2, gvBc.getSDT());
            ps.setString(3, gvBc.getDonViChuyenMon());
            ps.setInt(4, gvBc.getNamVeTruong());
            ps.setFloat(5, gvBc.getHsLuongNgachBac());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //phương thức lấy ra danh sách các giáo viên quan sát được từ database
    //phương thức trả 1 về danh sách các giá trị quan sát được
    public ObservableList<Gv_BienChe> getListGvBc(){
        String sql = "SELECT * FROM TBL_QL_GV_BC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                    //lấy các dữ liệu ứng với các cột để cài đặt dữ liệu cho các thuộc tính tương ứng
                    Gv_BienChe gvBc = new Gv_BienChe();
                    gvBc.setTen(rs.getString("ten1"));
                    gvBc.setSDT(rs.getString("sdt1"));
                    gvBc.setDonViChuyenMon(rs.getString("donVi1"));
                    gvBc.setNamVeTruong(rs.getInt("namVe1"));
                    gvBc.setHsLuongNgachBac(rs.getFloat("heSoLuong"));
                    gvBc.tinh_Luong();
                    list.add(gvBc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //phương thức để lưu lại giá trị dữ liệu giáo viên sau khi chỉnh sửa vào lại database
    public boolean Save(Gv_BienChe gvBc){
        String sql = "UPDATE TBL_QL_GV_BC SET ten1=?, sdt1=?, donVi1=?, namVe1=?, heSoLuong=? WHERE sdt1="+gvBc.getSDT();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gvBc.getTen());//
            ps.setString(2, gvBc.getSDT());//
            ps.setString(3, gvBc.getDonViChuyenMon());//cài đặt dữ liệu cho các cột của database tương ứng với các thuộc tính của đối tương Gv_BienChe truyền vào
            ps.setInt(4, gvBc.getNamVeTruong());//
            ps.setFloat(5, gvBc.getHsLuongNgachBac());//
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //xóa dữ liệu ra khỏi database
    public boolean Delete(Gv_BienChe gvBc){
        String sql = "DELETE FROM TBL_QL_GV_BC WHERE sdt1="+gvBc.getSDT();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);//thực hiện câu lệnh truy vấn
            list.remove(gvBc);//xóa đối tương muốn xóa ra khỏi danh sách quan sát được từ database
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //tìm kiếm dữ liệu giáo viên biên chế theo tên, sdt, đơn vị chuyên môn
    public ObservableList<Gv_BienChe> searchTen(Gv_BienChe gv){
        String sql = "SELECT * FROM TBL_QL_GV_BC WHERE (ten1 = '"+gv.getTen()+"') AND (sdt1 = '"+gv.getSDT()+"') AND (donVi1 = '"+gv.getDonViChuyenMon()+"')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Gv_BienChe gvBc = new Gv_BienChe();
                gvBc.setTen(rs.getString("ten1"));
                gvBc.setSDT(rs.getString("sdt1"));
                gvBc.setDonViChuyenMon(rs.getString("donVi1"));
                gvBc.setNamVeTruong(rs.getInt("namVe1"));
                gvBc.setHsLuongNgachBac(rs.getFloat("heSoLuong"));
                list.add(gvBc);//thêm các đối tương thỏa mãn vào danh sách quan sát được để thêm vào tableview
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //tìm kiếm giáo viên theo lương lớn hơn 1 số cho trước tham số truyền vào sẽ là 1 số do người dùng nhập lấy từ TextField
    public ObservableList<Gv_BienChe> searchLuong(float luong){
        String sql = "SELECT * FROM TBL_QL_GV_BC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Gv_BienChe gvBc = new Gv_BienChe();
                gvBc.setTen(rs.getString("ten1"));
                gvBc.setSDT(rs.getString("sdt1"));
                gvBc.setDonViChuyenMon(rs.getString("donVi1"));
                gvBc.setNamVeTruong(rs.getInt("namVe1"));
                gvBc.setHsLuongNgachBac(rs.getFloat("heSoLuong"));
                if(gvBc.tinh_Luong()>luong)//nếu đối tương đó có lương lớn hơn số người dùng nhập mới thêm vào danh sách giá trị quan sát được
                    list.add(gvBc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
