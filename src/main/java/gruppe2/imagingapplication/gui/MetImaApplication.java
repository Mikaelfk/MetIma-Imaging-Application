package gruppe2.imagingapplication.gui;

import com.drew.imaging.ImageProcessingException;
import gruppe2.imagingapplication.ContentManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MetImaApplication extends Application {

  private static Stage stage;
  private static ContentManager contentManager;

  @Override
  public void start(Stage stage) throws Exception {
    createContentManager();
    stage.setMinWidth(1000);
    stage.setMinHeight(600);
    stage.setTitle("MetIma");
    Scene scene = new Scene(
            FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml")), 1200, 800);
    stage.setScene(scene);
    setStage(stage);
    stage.show();
  }

  private static void createContentManager() throws ImageProcessingException, IOException {
    contentManager = new ContentManager();
  }

  private static void setStage(Stage stage) {
    MetImaApplication.stage = stage;
  }

  public static Stage getStage() {
    return stage;
  }

  public static ContentManager getContentManager() {
    return contentManager;
  }

}
