package gruppe2.imagingapplication.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MetImaApplication extends Application {

  private static Stage stage;

  @Override
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml")), 1200, 800);
    stage.setMinWidth(1000);
    stage.setMinHeight(600);
    stage.setTitle("no");
    stage.setScene(scene);
    setStage(stage);
    stage.show();
  }

  private void setStage(Stage stage) {
    MetImaApplication.stage = stage;
  }

  public static Stage getStage() {
    return stage;
  }
}
