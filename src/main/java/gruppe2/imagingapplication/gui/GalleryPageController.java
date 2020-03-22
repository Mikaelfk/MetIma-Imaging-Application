package gruppe2.imagingapplication.gui;

import gruppe2.imagingapplication.Export;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import gruppe2.imagingapplication.ImageData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GalleryPageController implements Initializable {
  public TextField searchField;
  Export export = new Export();
  Logger logger = LoggerFactory.getLogger(GalleryPageController.class);

  @FXML
  private TilePane galleryImages;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    generateGallery(MetImaApplication.getContentManager().getImages());
  }

  /**
   * This method handles what happens when the home button is pressed.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonHome(ActionEvent event) {
    try {
      if (MetImaApplication.getStage().isFullScreen()) {
        MetImaApplication.getStage().setMaximized(true);
        MetImaApplication.getStage().setScene(
            new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
      } else {
        MetImaApplication.getStage().setScene(
            new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
      }
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
  private void buttonExport(ActionEvent event) throws IOException {
    HashMap<String, ImageData> images;
    if(!MetImaApplication.getContentManager().getSearchResults().isEmpty()) {
      images = MetImaApplication.getContentManager().getSearchResults();
    } else {
      images = MetImaApplication.getContentManager().getImages();
    }
    export.exportImagesToPdf(images, "filename");
  }

  @FXML
  public void buttonSearch(ActionEvent actionEvent) {
    if (!searchField.getText().isEmpty()) {
      galleryImages.getChildren().clear();

      MetImaApplication.getContentManager().performSearch(searchField.getText());

      generateGallery(MetImaApplication.getContentManager().getSearchResults());
    } else {
      galleryImages.getChildren().clear();
      generateGallery(MetImaApplication.getContentManager().getImages());
    }
  }

  public void generateGallery(HashMap<String, ImageData> imageHashMap) {
    imageHashMap.keySet().forEach(path -> {
      ImageView imagePreview = new ImageView();
      imagePreview.setImage(MetImaApplication.getContentManager().getImages().get(path).getImage());
      imagePreview.setFitHeight(100);
      imagePreview.setSmooth(true);
      imagePreview.setPreserveRatio(true);

      imagePreview.setOnMouseClicked(e -> {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("MetIma_ViewImagePage.fxml"));
          ViewImagePageController controller = new ViewImagePageController();
          loader.setController(controller);
          controller.setImage(path);
          MetImaApplication.getStage().setScene(new Scene(loader.load()));
        } catch (IOException exception) {
          logger.error("File not found", exception);
        }
      });
      galleryImages.getChildren().add(imagePreview);
    });
  }
}
