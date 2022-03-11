package ControllerQLGV;

import javafx.stage.Stage;

import java.io.IOException;

//interface định nghĩa việc load scene khi chuyển scene
public interface SwitchScene {
    Stage stageLoading = new Stage();
    void loading(String urlFxml) throws IOException ;
}
