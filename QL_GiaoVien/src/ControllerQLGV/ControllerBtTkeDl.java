package ControllerQLGV;

import AppQLGV.Giao__vien.Gv_BienChe;
import AppQLGV.Giao__vien.Gv_MoiGiang;
import Lket_Dl.DAO_gvBc;
import Lket_Dl.DAO_gvMg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerBtTkeDl implements SwitchScene, Initializable {
    @FXML
    private Button btBackTke;
    @FXML
    private Button btChoose;
    @FXML
    private TextField luongGv;
    @FXML
    private TableView<Gv_BienChe> tblGvBc;
    @FXML
    private TableColumn<Gv_BienChe, String> colTen1;
    @FXML
    private TableColumn<Gv_BienChe, String> colSdt1;
    @FXML
    private TableColumn<Gv_BienChe, String> colDonvi1;
    @FXML
    private TableColumn<Gv_BienChe, Integer> colNam1;
    @FXML
    private TableColumn<Gv_BienChe, Float> colLuong1;
    @FXML
    private TableView<Gv_MoiGiang> tblGvMg;
    @FXML
    private TableColumn<Gv_BienChe, String> colTen2;
    @FXML
    private TableColumn<Gv_BienChe, String> colSdt2;
    @FXML
    private TableColumn<Gv_BienChe, String> colDonvi2;
    @FXML
    private TableColumn<Gv_BienChe, Integer> colNam2;
    @FXML
    private TableColumn<Gv_BienChe, Float> colLuong2;

    //giúp quay trở lại giao diện menu chính
    public void pressBackTke(){
        try{
            loading("../FXML_QLGV/QLGV.fxml");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //giúp chọn 1 trong các giáo viên muốn hiển thị lương
    public void pressChoose(ActionEvent event){
        tblGvBc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //xử lý sự kiện khi click chuột vào 1 tableview
            //khi click vào 1 item của 1 table sẽ lấy ra thông tin của item đó
            @Override
            public void handle(MouseEvent mouseEvent) {
                Gv_BienChe gvBc = tblGvBc.getItems().get(tblGvBc.getSelectionModel().getSelectedIndex());
                luongGv.setText(String.valueOf(gvBc.tinh_Luong()));
            }
        });
        tblGvMg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //xử lý sự kiện khi click chuột vào 1 tableview
            //khi click vào 1 item của 1 table sẽ lấy ra thông tin của item đó
            @Override
            public void handle(MouseEvent mouseEvent) {
                Gv_MoiGiang gvMg = tblGvMg.getItems().get(tblGvMg.getSelectionModel().getSelectedIndex());
                luongGv.setText(String.valueOf(gvMg.tinh_Luong()));
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //phương thức thực hiện việc load dữ liệu lấy ra từ database ngay khi hiển thị scene
        loadBc();
        loadMg();
    }
    //thiết lập giá trị tương ứng cho từng cột của tableview
    private void loadBc(){
        colTen1.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colSdt1.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDonvi1.setCellValueFactory(new PropertyValueFactory<>("donViChuyenMon"));
        colNam1.setCellValueFactory(new PropertyValueFactory<>("namVeTruong"));
        colLuong1.setCellValueFactory(new PropertyValueFactory<>("hsLuongNgachBac"));
        tblGvBc.setItems(new DAO_gvBc().getListGvBc());//thêm danh sách các giá trị quan sát được được lấy ra từ database sau đó lấy làm giá trị cho tableview
    }
    //thiết lập giá trị tương ứng cho từng cột của tableview
    private void loadMg(){
        colTen2.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colSdt2.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDonvi2.setCellValueFactory(new PropertyValueFactory<>("donViChuyenMon"));
        colNam2.setCellValueFactory(new PropertyValueFactory<>("namVeTruong"));
        colLuong2.setCellValueFactory(new PropertyValueFactory<>("soGioGiangThuc"));
        tblGvMg.setItems(new DAO_gvMg().getListGvMg());//thêm danh sách các giá trị quan sát được được lấy ra từ database sau đó lấy làm giá trị cho tableview
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
