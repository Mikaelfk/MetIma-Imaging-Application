package gruppe2.imagingapplication.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MetImaApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml"));

    Scene scene = new Scene(root, 1200, 800);

    stage.setMinWidth(1000);
    stage.setMinHeight(600);
    stage.setTitle("no");
    stage.setScene(scene);
    stage.show();
  }
}
