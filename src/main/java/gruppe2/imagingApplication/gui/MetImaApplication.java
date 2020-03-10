package gruppe2.imagingApplication.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MetImaApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml"));

    Scene scene = new Scene(root, 300, 275);

    stage.setTitle("no");
    stage.setScene(scene);
    stage.show();
  }
}
