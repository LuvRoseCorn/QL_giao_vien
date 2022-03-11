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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerQlDl implements SwitchScene,Initializable {
    @FXML
    private Button btDelete;
    @FXML
    private Button btAdd;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btBack;
    @FXML
    private Button btSave;
    @FXML
    private TextField hvt;
    @FXML
    private TextField sdt;
    @FXML
    private TextField dvcm;
    @FXML
    private TextField nam;
    @FXML
    private TextField luong;
    @FXML
    private RadioButton Bc;
    @FXML
    private RadioButton Mg;
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
    //khi nhấn vào Button xóa sẽ thực hiện phương thức xóa dữ liệu này
    public void pressDelete(ActionEvent event){
        if(Bc.isSelected()){
            //tạo ra 1 đối tương DAO_gvBc đẻ sử dụng các phương thưc của lớp này
            new DAO_gvBc().Delete(new Gv_BienChe(hvt.getText(), sdt.getText(), dvcm.getText(), Integer.parseInt(nam.getText()), Float.parseFloat(luong.getText())));
            loadBc();
        }
        else if (Mg.isSelected()) {
            //tạo ra 1 đối tương DAO_gvMg đẻ sử dụng các phương thưc của lớp nà
            new DAO_gvMg().Delete(new Gv_MoiGiang(hvt.getText(), sdt.getText(), dvcm.getText(), Integer.parseInt(nam.getText()), Float.parseFloat(luong.getText())));
            loadMg();
        }
    }
    //phương thức để thêm dữ liệu khi nhấn vào nút Thêm
    public void pressAdd(ActionEvent event){
        if(Bc.isSelected()){
            //dữ liệu sẽ lấy từ các TextField do người dùng nhập vào làm giá trị khởi tạo cho đối tượng
            new DAO_gvBc().addGvBc(new Gv_BienChe(hvt.getText(), sdt.getText(), dvcm.getText(), Integer.parseInt(nam.getText()), Float.parseFloat(luong.getText())));
            loadBc();
        }
        else if (Mg.isSelected()) {
            new DAO_gvMg().addGvMg(new Gv_MoiGiang(hvt.getText(), sdt.getText(), dvcm.getText(), Integer.parseInt(nam.getText()), Float.parseFloat(luong.getText())));
            loadMg();
        }
    }
    //phương thức để lưu các thông tin của giáo viên khi mình đã chỉnh sửa xong
    public void pressSave(ActionEvent event){
        if(Bc.isSelected()){
            new DAO_gvBc().Save(new Gv_BienChe(hvt.getText(), sdt.getText(), dvcm.getText(), Integer.parseInt(nam.getText()), Float.parseFloat(luong.getText())));
            loadBc();
        }
        else if (Mg.isSelected()) {
            new DAO_gvMg().Save(new Gv_MoiGiang(hvt.getText(), sdt.getText(), dvcm.getText(), Integer.parseInt(nam.getText()), Float.parseFloat(luong.getText())));
            loadMg();
        }
    }
    //phương thức để chọn ra giáo viên muốn chỉnh sửa hoặc xóa
    public void pressUpdate(ActionEvent event){
        //xử lý sự kiện khi nhấn click vào 1 tableview
        tblGvBc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Gv_BienChe gvBc = tblGvBc.getItems().get(tblGvBc.getSelectionModel().getSelectedIndex());
                hvt.setText(gvBc.getTen());
                sdt.setText(gvBc.getSDT());
                dvcm.setText(gvBc.getDonViChuyenMon());
                nam.setText(String.valueOf(gvBc.getNamVeTruong()));
                Bc.setSelected(true);
                luong.setText(String.valueOf(gvBc.getHsLuongNgachBac()));
            }
        });
        tblGvMg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Gv_MoiGiang gvMg = tblGvMg.getItems().get(tblGvMg.getSelectionModel().getSelectedIndex());
                hvt.setText(gvMg.getTen());
                sdt.setText(gvMg.getSDT());
                dvcm.setText(gvMg.getDonViChuyenMon());
                nam.setText(String.valueOf(gvMg.getNamVeTruong()));
                Mg.setSelected(true);
                luong.setText(String.valueOf(gvMg.getSoGioGiangThuc()));
            }
        });
    }

    public void pressBack(ActionEvent event) throws IOException{
        try{
            loading("../FXML_QLGV/QLGV.fxml");
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBc();
        loadMg();
    }

    private void loadBc(){
        colTen1.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colSdt1.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDonvi1.setCellValueFactory(new PropertyValueFactory<>("donViChuyenMon"));
        colNam1.setCellValueFactory(new PropertyValueFactory<>("namVeTruong"));
        colLuong1.setCellValueFactory(new PropertyValueFactory<>("hsLuongNgachBac"));
        tblGvBc.setItems(new DAO_gvBc().getListGvBc());
    }

    private void loadMg(){
        colTen2.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colSdt2.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDonvi2.setCellValueFactory(new PropertyValueFactory<>("donViChuyenMon"));
        colNam2.setCellValueFactory(new PropertyValueFactory<>("namVeTruong"));
        colLuong2.setCellValueFactory(new PropertyValueFactory<>("soGioGiangThuc"));
        tblGvMg.setItems(new DAO_gvMg().getListGvMg());
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
