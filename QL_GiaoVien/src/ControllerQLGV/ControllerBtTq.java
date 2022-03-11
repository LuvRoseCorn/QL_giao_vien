package ControllerQLGV;

import com.sun.security.jgss.GSSUtil;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ControllerBtTq implements SwitchScene,Initializable {
    @FXML
    private Button btBackTq;
    @FXML
    private BarChart< ?,? > bieuDo;
    @FXML
    private CategoryAxis boMon;
    @FXML
    private NumberAxis soLuong;
    @FXML
    private Label sumGv;
    @FXML
    private Label sumGvBc;
    @FXML
    private Label sumGvMg;
    @FXML
    private Label luongAvg;
    private Connection connection;
    public void pressBackTq(ActionEvent event){
        try {
            loading("../FXML_QLGV/QLGV.fxml");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //load dữ liệu từ database vào các số và biểu đồ ngay khi người dùng nhấn vào nút
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql1 = "SELECT COUNT (*) FROM TBL_QL_GV_BC WHERE donVi1 = ";
        String sql2 = "SELECT COUNT (*) FROM TBL_QL_GV_MG WHERE donVi2 = ";
        String sql3 = "SELECT COUNT (*) FROM TBL_QL_GV_BC";
        String sql4 = "SELECT COUNT (*) FROM TBL_QL_GV_MG";
        String sql5 = "SELECT SUM (heSoLuong) FROM TBL_QL_GV_BC";
        String sql6 = "SELECT SUM (soGioGiang) FROM TBL_QL_GV_MG";

        XYChart.Series se = new XYChart.Series<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QL_GV;username=sa;password=chuyen149");
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            //các giá trị nhận kết quả từ database trả về khi thực hiện câu lệnh truy vấn tương ứng
            ResultSet rs1 = connection.createStatement().executeQuery(sql1+"N'Toán'");
            ResultSet rs2 = connection.createStatement().executeQuery(sql2+"N'Toán'");
            ResultSet rs3 = connection.createStatement().executeQuery(sql1+"N'Lí'");
            ResultSet rs4 = connection.createStatement().executeQuery(sql2+"N'Lí'");
            ResultSet rs5 = connection.createStatement().executeQuery(sql1+"N'Hóa'");
            ResultSet rs6 = connection.createStatement().executeQuery(sql2+"N'Hóa'");
            ResultSet rs7 = connection.createStatement().executeQuery(sql1+"N'Văn'");
            ResultSet rs8 = connection.createStatement().executeQuery(sql2+"N'Văn'");
            ResultSet rs9 = connection.createStatement().executeQuery(sql1+"N'Anh'");
            ResultSet rs10 = connection.createStatement().executeQuery(sql2+"N'Anh'");
            ResultSet rs11 = connection.createStatement().executeQuery(sql1+"N'Sinh'");
            ResultSet rs12 = connection.createStatement().executeQuery(sql2+"N'Sinh'");
            ResultSet rs13 = connection.createStatement().executeQuery(sql1+"N'Sử'");
            ResultSet rs14 = connection.createStatement().executeQuery(sql2+"N'Sử'");
            ResultSet rs15 = connection.createStatement().executeQuery(sql1+"N'Địa'");
            ResultSet rs16 = connection.createStatement().executeQuery(sql2+"N'Địa'");
            ResultSet rs17 = connection.createStatement().executeQuery(sql1+"N'Tin'");
            ResultSet rs18 = connection.createStatement().executeQuery(sql2+"N'Tin'");
            while(rs1.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next() && rs6.next() && rs7.next() && rs8.next() && rs9.next() && rs10.next()
                    && rs11.next() && rs12.next() && rs13.next() && rs14.next() && rs15.next() && rs16.next() && rs17.next() && rs18.next())
                {
                    //lấy giá trị nhận được làm giá trị cho từng cột với tên cột và số lượng từng cột
                    se.getData().add(new XYChart.Data("Toán",rs1.getInt(1)+rs2.getInt(1)));
                    se.getData().add(new XYChart.Data("Lí",rs3.getInt(1)+rs4.getInt(1)));
                    se.getData().add(new XYChart.Data("Hóa",rs5.getInt(1)+rs6.getInt(1)));
                    se.getData().add(new XYChart.Data("Văn",rs7.getInt(1)+rs8.getInt(1)));
                    se.getData().add(new XYChart.Data("Anh",rs9.getInt(1)+rs10.getInt(1)));
                    se.getData().add(new XYChart.Data("Sinh",rs11.getInt(1)+rs12.getInt(1)));
                    se.getData().add(new XYChart.Data("Sử",rs13.getInt(1)+rs14.getInt(1)));
                    se.getData().add(new XYChart.Data("Địa",rs15.getInt(1)+rs16.getInt(1)));
                    se.getData().add(new XYChart.Data("Tin",rs17.getInt(1)+rs18.getInt(1)));
                }
            bieuDo.getData().add(se);
            //lấy tổng số giáo viên sẽ bằng tổng số giáo viên biên chế và giáo viên mời giảng có trong database
            ResultSet rsSumBc = connection.createStatement().executeQuery(sql3);
            ResultSet rsSumMg = connection.createStatement().executeQuery(sql4);
            while(rsSumBc.next() && rsSumMg.next()){
                //lấy giá trị là tổng số giáo viên đó làm giá trị cho các label
                sumGv.setText(String.valueOf(rsSumBc.getInt(1)+rsSumMg.getInt(1)));
                sumGvBc.setText(String.valueOf(rsSumBc.getInt(1)));
                sumGvMg.setText(String.valueOf(rsSumMg.getInt(1)));
            }
            //tính lương trung bình của tất cả các giáo viên làm giá trị hiển trị của label luongAvg
            ResultSet rsLuongBc = connection.createStatement().executeQuery(sql5);
            ResultSet rsLuongMg = connection.createStatement().executeQuery(sql6);
            while(rsLuongMg.next() && rsLuongBc.next()){
                luongAvg.setText(String.valueOf((rsLuongBc.getFloat(1)*3000000+rsLuongMg.getFloat(1)*500000+1000000)/Integer.parseInt(sumGv.getText())));
            }
        } catch (Exception e){
        }
    }

    @Override
    public void loading(String urlFxml) throws IOException {
        try{
            stageLoading.setScene(new Scene(FXMLLoader.load(getClass().getResource(urlFxml))));
            stageLoading.show();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
