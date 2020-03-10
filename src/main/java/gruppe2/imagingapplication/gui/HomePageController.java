package gruppe2.imagingapplication.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePageController implements Initializable {
  Logger logger = LoggerFactory.getLogger(HomePageController.class);

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  /**
   * This method handles what happens when the view gallery button is pressed.
   */
  @FXML
  private void viewGallery(ActionEvent event) {
    logger.info("View Gallery button was pressed");
  }


  /**
   * This method handles what happens when the view gallery button is pressed.
   */
  @FXML
  private void addImage(ActionEvent event) {
    logger.info("Add Image button was pressed");
  }

}
