package gruppe2.imagingapplication.gui;

import gruppe2.imagingapplication.Export;
import gruppe2.imagingapplication.ImageData;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GalleryPageController implements Initializable {
  @FXML
  private TextField searchField;
  private static final String FILE_NOT_FOUND = "File not found";
  Export export = new Export();
  Logger logger = LoggerFactory.getLogger(GalleryPageController.class);

  @FXML
  private TilePane galleryImages;

  /**
   * The generateGallery method is run when the gallery page is initialized.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    generateGallery(MetImaApplication.getContentManager().getImages());
  }

  /**
   * This method handles what happens when the home button is pressed.
   * The button sets a new scene by using the MetIma_HomePage.fxml file
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
      logger.error(FILE_NOT_FOUND, exception);
    }
  }

  /**
   * This method handles what happens when the add image button is pressed.
   * The button sets a new scene by using the MetIma_AddImagePage.fxml file.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonAddImage(ActionEvent event) {
    try {
      MetImaApplication.getStage().setScene(
              new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));
    } catch (IOException exception) {
      logger.error(FILE_NOT_FOUND, exception);
    }
  }

  /**
   * This method handles what happens when the export button is pressed.
   * It exports everything that is shown in the gallery to a pdf document.
   *
   * @param event The event is the event that occurs when the button is pressed
   */
  @FXML
  private void buttonExport(ActionEvent event) {
    HashMap<String, ImageData> images;
    if (!MetImaApplication.getContentManager().getSearchResults().isEmpty()) {
      images = MetImaApplication.getContentManager().getSearchResults();
    } else {
      images = MetImaApplication.getContentManager().getImages();
    }
    export.exportImagesToPdf(images);
  }

  /**
   * This button handles what happens when the search button is pressed.
   * It uses the performSearch method in ContentManager with the searchField text as its input.
   *
   * @param actionEvent The event is the event that occurs when the button is pressed
   */
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

  /**
   * This method generates a gallery by accessing the paths of the images.
   * If an image in clicked by a mouse it will be taken to the view image page.
   *
   * @param imageHashMap Takes a HashMap with a string as key and ImageData as value as a parameter
   */
  public void generateGallery(HashMap<String, ImageData> imageHashMap) {
    imageHashMap.keySet().forEach(path -> {
      ImageView imagePreview = new ImageView();
      Image image = MetImaApplication.getContentManager().getImages().get(path).getImage();

      if (image.getHeight() / image.getWidth() == 1) {
        imagePreview.setImage(image);
      } else {
        PixelReader reader = image.getPixelReader();
        WritableImage newImage = new WritableImage(reader, (int) (image.getWidth() / 6), (int) (image.getHeight() / 6), (int) (image.getWidth() / 1.5), (int) (image.getHeight() / 1.5));
        imagePreview.setImage(newImage);
      }
      imagePreview.setFitWidth(100);
      imagePreview.setFitHeight(100);

      imagePreview.setOnMouseClicked(e -> {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("MetIma_ViewImagePage.fxml"));
          ViewImagePageController controller = new ViewImagePageController();
          loader.setController(controller);
          controller.setImage(path);
          MetImaApplication.getStage().setScene(new Scene(loader.load()));
        } catch (IOException exception) {
          logger.error(FILE_NOT_FOUND, exception);
        }
      });
      VBox gridBox = new VBox(imagePreview);
      VBox.setMargin(imagePreview, new Insets(10, 10, 10, 10));
      gridBox.setStyle("-fx-border-color: purple;");
      galleryImages.getChildren().add(gridBox);
    });
    galleryImages.setHgap(10);
    galleryImages.setVgap(10);
  }
}
