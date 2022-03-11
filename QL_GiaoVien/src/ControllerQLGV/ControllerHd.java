package ControllerQLGV;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerHd implements SwitchScene{
    @FXML
    private Button btBackHd;
    public void pressBackHd(){
        try{
            loading("../FXML_QLGV/QLGV.fxml");
        }catch (IOException ioe) {
            ioe.printStackTrace();
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
