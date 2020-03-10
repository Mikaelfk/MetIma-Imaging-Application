package gruppe2.imagingapplication.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GalleryPageController implements Initializable {
  Logger logger = LoggerFactory.getLogger(GalleryPageController.class);

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  /**
   * This method handles what happens when the home button is pressed.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonHome(ActionEvent event) {
    try {
      MetImaApplication.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }

  /**
   * This method handles what happens when the add image button is pressed.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonAddImage(ActionEvent event) {
    try {
      MetImaApplication.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }

  /**
   * This method handles what happens when the export button is pressed.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonExport(ActionEvent event) {
    logger.info("Export button pressed");
  }
}
