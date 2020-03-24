package gruppe2.imagingapplication.gui;

import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import gruppe2.imagingapplication.ImageData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ViewImagePageController implements Initializable {
  @FXML
  private ImageView imageView;
  @FXML
  private Text imageName;
  @FXML
  private Text tags;
  Logger logger = LoggerFactory.getLogger(ViewImagePageController.class);
  private ImageData image;
  private static final String FILE_NOT_FOUND = "File not found";

  @FXML
  private TextFlow textFlow;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    imageView.setImage(new javafx.scene.image.Image("file:" + this.image.getPath()));
    imageName.setText("FileName:" + this.image.getImageName());
    tags.setText("Tags:" + String.valueOf(this.image.getTags()));

    for (Directory directory : image.getMetadata().getDirectories()) {
      for (Tag tag : directory.getTags()) {
        Text text = new Text();
        text.setText(tag.toString() + "\n");
        textFlow.getChildren().add(text);
      }
    }
  }

  /**
   * This sets the image you are viewing, it uses a getter to get the correct image.
   * @param path This is the image path
   */
  public void setImage(String path) {
    this.image = MetImaApplication.getContentManager().getImages().get(path);
  }

  /**
   * This method displays the metadata text from an image, it uses a getter to get the metadata.
   * @param path This is the image path
   */
  public void setMetadataText(String path) {
    this.textFlow.setAccessibleText(
            MetImaApplication.getContentManager().getImages().get(path).getMetadata().toString());
  }

  /**
   *This method handles the home button it takes the user to the homepage.
   */
  @FXML
  private void btnHome() {
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
    } catch (IOException exception) {
      logger.error(FILE_NOT_FOUND, exception);
    }
  }

  /**
   * This method handles the gallery button, it takes the user to the gallery page.
   */
  @FXML
  private void btnGallery() {
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml"))));
    } catch (IOException exception) {
      logger.error(FILE_NOT_FOUND, exception);
    }
  }

  /**
   * This method handles the add image button, it takes you to the add Image page.
   */
  @FXML
  private void btnAddImage() {
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));


    } catch (IOException exception) {
      logger.error(FILE_NOT_FOUND, exception);
    }

  }

  /**
   * This method handles the delete button, it deletes the viewed image.
   */
  @FXML
  private void btnDelete() {
    MetImaApplication.getContentManager().removeImage(this.image.getPath());
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml"))));
    } catch (IOException exception) {
      logger.error(FILE_NOT_FOUND, exception);
    }
  }

  /**
   * Getter for ImageView.
   * @return ImageView, this shows an image within JavaFX
   */
  public ImageView getImageView() {
    return imageView;
  }

  /**
   * Setter for ImageView.
   * @param imageView This shows an image within JavaFX
   */
  public void setImageView(ImageView imageView) {
    this.imageView = imageView;
  }
}
