package ControllerQLGV;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Optional;

public class Controller implements SwitchScene {
    @FXML
    private Button btTq;
    @FXML
    private Button btQlgv;
    @FXML
    private Button btTkiemgv;
    @FXML
    private Button btTkegv;
    @FXML
    private Button btExit;
    @FXML
    private Button btHd;
    //xu li su kien bam cac nut Tong_quat, Quan_li_du_lieu, Tim_kiem_du_lieu, Thong_ke_du_lieu
    public void click(ActionEvent event) throws IOException{
        try {
            if (event.getSource() == btTq)
                loading("../FXML_QLGV/SceneBtTq.fxml");
            else if (event.getSource() == btQlgv)
                loading("../FXML_QLGV/SceneBtQldl.fxml");
            else if (event.getSource() == btTkiemgv)
                loading("../FXML_QLGV/SceneBtTkiemDl.fxml");
            else if (event.getSource() == btTkegv)
                loading("../FXML_QLGV/SceneBtTkeDl.fxml");
            else if (event.getSource() == btHd)
                loading("../FXML_QLGV/SceneBtHd.fxml");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //xu li su kien bam nut Exit:(
    public void clickExit(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit!!!");
        alert.setContentText("Bạn có chắc chắn muốn thoát không?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            System.exit(0);
    }
    //phương thức giúp xử lý việc chuyển giữa các Scene bằng cách set các
    //địa chỉ của các file .fxml vào Scene
    @Override
    public void loading(String urlFxml) throws IOException {
        try{
            stageLoading.setTitle("Quản lí giáo viên");
            Image icon = new Image("icon1.png");
            stageLoading.getIcons().add(icon);
            stageLoading.setScene(new Scene(FXMLLoader.load(getClass().getResource(urlFxml))));
            stageLoading.show();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
