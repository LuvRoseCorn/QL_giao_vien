package Lket_Dl;

import AppQLGV.Giao__vien.Gv_MoiGiang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO_gvMg{
    private Connection connection;
    ObservableList<Gv_MoiGiang> list = FXCollections.observableArrayList();

    public DAO_gvMg(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QL_GV;username=sa;password=chuyen149");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean addGvMg(Gv_MoiGiang gvMg){
        String sql = "INSERT INTO TBL_QL_GV_MG(ten2, sdt2, donVi2, namVe2, soGioGiang) "
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gvMg.getTen());
            ps.setString(2, gvMg.getSDT());
            ps.setString(3, gvMg.getDonViChuyenMon());
            ps.setInt(4, gvMg.getNamVeTruong());
            ps.setFloat(5, gvMg.getSoGioGiangThuc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Gv_MoiGiang> getListGvMg(){
        String sql = "SELECT * FROM TBL_QL_GV_MG";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Gv_MoiGiang gvMg = new Gv_MoiGiang();
                gvMg.setTen(rs.getString("ten2"));
                gvMg.setSDT(rs.getString("sdt2"));
                gvMg.setDonViChuyenMon(rs.getString("donVi2"));
                gvMg.setNamVeTruong(rs.getInt("namVe2"));
                gvMg.setSoGioGiangThuc(rs.getFloat("soGioGiang"));
                list.add(gvMg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean Save(Gv_MoiGiang gvMg){
        String sql = "UPDATE TBL_QL_GV_MG SET ten2=?, sdt2=?, donVi2=?, namVe2=?, soGioGiang=? WHERE sdt2="+gvMg.getSDT();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gvMg.getTen());
            ps.setString(2, gvMg.getSDT());
            ps.setString(3, gvMg.getDonViChuyenMon());
            ps.setInt(4, gvMg.getNamVeTruong());
            ps.setFloat(5, gvMg.getSoGioGiangThuc());;
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean Delete(Gv_MoiGiang gvMg){
        String sql = "DELETE FROM TBL_QL_GV_MG WHERE sdt2="+gvMg.getSDT();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            list.remove(gvMg);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Gv_MoiGiang> searchTen(Gv_MoiGiang gv){
        String sql = "SELECT * FROM TBL_QL_GV_MG WHERE (ten2 = '"+gv.getTen()+"') AND (sdt2 = '"+gv.getSDT()+"') AND (donVi2 = '"+gv.getDonViChuyenMon()+"')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Gv_MoiGiang gvMg = new Gv_MoiGiang();
                gvMg.setTen(rs.getString("ten2"));
                gvMg.setSDT(rs.getString("sdt2"));
                gvMg.setDonViChuyenMon(rs.getString("donVi2"));
                gvMg.setNamVeTruong(rs.getInt("namVe2"));
                gvMg.setSoGioGiangThuc(rs.getFloat("soGioGiang"));
                list.add(gvMg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<Gv_MoiGiang> searchLuong(float luong){
        String sql = "SELECT * FROM TBL_QL_GV_MG";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Gv_MoiGiang gvMg = new Gv_MoiGiang();
                gvMg.setTen(rs.getString("ten2"));
                gvMg.setSDT(rs.getString("sdt2"));
                gvMg.setDonViChuyenMon(rs.getString("donVi2"));
                gvMg.setNamVeTruong(rs.getInt("namVe2"));
                gvMg.setSoGioGiangThuc(rs.getFloat("soGioGiang"));
                if(gvMg.tinh_Luong()>luong)
                    list.add(gvMg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

