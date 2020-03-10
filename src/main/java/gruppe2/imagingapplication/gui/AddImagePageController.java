package gruppe2.imagingapplication.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddImagePageController implements Initializable {

  Logger logger = LoggerFactory.getLogger(AddImagePageController.class);

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  /**
   * This method handles what happens when the home button is pressed.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnHome(ActionEvent event) {
    logger.info("Home button pressed.");
  }

  /**
   * This method handles what happens when the add button is pressed.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnAdd(ActionEvent event) {
    logger.info("Add button pressed.");
  }

  /**
   * This method handles what happens when the cancel button is pressed.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnCancel(ActionEvent event) {
    logger.info("Cancel button pressed.");
  }

  /**
   * This method handles what happens when the gallery button is pressed.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnGallery(ActionEvent event) {
    logger.info("Gallery button pressed.");
  }
}
