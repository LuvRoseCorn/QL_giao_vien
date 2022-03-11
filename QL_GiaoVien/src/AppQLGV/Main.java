package AppQLGV;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    //chương trình bắt đầu chạy từ đây
    public void start(Stage primaryStage) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML_QLGV/QLGV.fxml"));
            primaryStage.setTitle("Quản lí giáo viên");
            Image icon = new Image("icon1.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
