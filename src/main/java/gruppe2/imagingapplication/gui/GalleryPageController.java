package gruppe2.imagingapplication.gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GalleryPageController implements Initializable {
  Logger logger = LoggerFactory.getLogger(GalleryPageController.class);

  @FXML
  private TilePane galleryImages;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
      MetImaApplication.getContentManager().getImages().keySet().forEach(path -> {
                ImageView imagePreview = new ImageView();
                imagePreview.setImage(new Image("file:" + path));
                imagePreview.setFitHeight(100);
                imagePreview.setSmooth(true);

               // Jakob og Mikael, dette er en ide. Derfor har den en dark mode toggle.
                imagePreview.setOnMouseClicked(e -> {
                  try {
                    MetImaApplication.getStage().setScene(
                            new Scene(FXMLLoader.load(getClass().getResource("MetIma_ViewImagePage.fxml"))));

                  } catch (IOException exception) {
                    logger.error("File not found", exception);
                  }
                });
                galleryImages.getChildren().add(imagePreview);
              });
    ImageView imagePreview2 = new ImageView();
    imagePreview2.setImage(new Image("file:C:\\Users\\Ukhur\\Pictures\\Profile Pictures\\Ichika-01.jpg"));
    imagePreview2.setFitWidth(200);
    imagePreview2.setPreserveRatio(true);

    imagePreview2.setViewport(new Rectangle2D(100,100,100,100));
    galleryImages.getChildren().add(imagePreview2);
  }

  /**
   * This method handles what happens when the home button is pressed.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonHome(ActionEvent event) {
    try {
      MetImaApplication.getStage().setScene(
              new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
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
      MetImaApplication.getStage().setScene(
              new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));
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
