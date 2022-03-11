package ControllerQLGV;

import AppQLGV.Giao__vien.Gv_BienChe;
import AppQLGV.Giao__vien.Gv_MoiGiang;
import Lket_Dl.DAO_gvBc;
import Lket_Dl.DAO_gvMg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ControllerBtTkiemDl implements SwitchScene {
    @FXML
    private Button btBackTkiem;
    @FXML
    private Button btTkiemTen;
    @FXML
    private Button btTkiemLuong;
    @FXML
    private TextField hvtTk;
    @FXML
    private TextField sdtTk;
    @FXML
    private TextField dvcmTk;
    @FXML
    private TextField luongTk;
    @FXML
    private TableView<Gv_BienChe> tblGvBctk;
    @FXML
    private TableColumn<Gv_BienChe, String> colTentk1;
    @FXML
    private TableColumn<Gv_BienChe, String> colSdttk1;
    @FXML
    private TableColumn<Gv_BienChe, String> colDonvitk1;
    @FXML
    private TableColumn<Gv_BienChe, Integer> colNamtk1;
    @FXML
    private TableColumn<Gv_BienChe, Float> colLuongtk1;
    @FXML
    private TableView<Gv_MoiGiang> tblGvMgtk;
    @FXML
    private TableColumn<Gv_MoiGiang, String> colTentk2;
    @FXML
    private TableColumn<Gv_MoiGiang, String> colSdttk2;
    @FXML
    private TableColumn<Gv_MoiGiang, String> colDonvitk2;
    @FXML
    private TableColumn<Gv_MoiGiang, Integer> colNamtk2;
    @FXML
    private TableColumn<Gv_MoiGiang, Float> colLuongtk2;
    //quay lại giao diện menu chính
    public void pressBackTkiem(ActionEvent event){
        try {
            loading("../FXML_QLGV/QLGV.fxml");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //phương thức thực hiện việc tìm kiếm theo Tên, sdt, đơn vị chuyên môn
    public void pressTkiemTen(ActionEvent event){
        loadMg();
        loadBc();
        tblGvBctk.setItems(new DAO_gvBc().searchTen(new Gv_BienChe(hvtTk.getText(),sdtTk.getText(),dvcmTk.getText())));//nạp các giá trị cho các cột và lấy giá trị quan sát được trả về từ searchTen của DAO_gvBc với tham số
                                                                                                                        //truyền vào là 1 đối tượng kiểu Gv_BienChe với các giá trị khởi tạo lấy từ các TextField do người dùng nhâp
        tblGvMgtk.setItems(new DAO_gvMg().searchTen(new Gv_MoiGiang(hvtTk.getText(),sdtTk.getText(),dvcmTk.getText())));
    }
    //phương thức tìm kiếm theo lương lớn hơn 1 số cho trước
    public void pressTkiemLuong(ActionEvent event){
        loadMg();
        loadBc();
        tblGvBctk.setItems(new DAO_gvBc().searchLuong(Float.parseFloat(luongTk.getText())));//nạp các giá trị cho các cột và lấy giá trị quan sát được trả về từ searchLuong của DAO_gvBc với tham số
                                                                                            //truyền vào là 1 đối tượng kiểu Gv_BienChe với các giá trị khởi tạo lấy từ TextField luong do người dùng nhâp
        tblGvMgtk.setItems(new DAO_gvMg().searchLuong(Float.parseFloat(luongTk.getText())));
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
    //thiết lập giá trị tương ứng cho từng cột của tableview
    private void loadBc(){
        colTentk1.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colSdttk1.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDonvitk1.setCellValueFactory(new PropertyValueFactory<>("donViChuyenMon"));
        colNamtk1.setCellValueFactory(new PropertyValueFactory<>("namVeTruong"));
        colLuongtk1.setCellValueFactory(new PropertyValueFactory<>("hsLuongNgachBac"));
        tblGvBctk.setItems(new DAO_gvBc().getListGvBc());//thêm danh sách các giá trị quan sát được được lấy ra từ database sau đó lấy làm giá trị cho tableview
    }
    //thiết lập giá trị tương ứng cho từng cột của tableview
    private void loadMg(){
        colTentk2.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colSdttk2.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDonvitk2.setCellValueFactory(new PropertyValueFactory<>("donViChuyenMon"));
        colNamtk2.setCellValueFactory(new PropertyValueFactory<>("namVeTruong"));
        colLuongtk2.setCellValueFactory(new PropertyValueFactory<>("soGioGiangThuc"));
        tblGvMgtk.setItems(new DAO_gvMg().getListGvMg());//thêm danh sách các giá trị quan sát được được lấy ra từ database sau đó lấy làm giá trị cho tableview
    }

}
