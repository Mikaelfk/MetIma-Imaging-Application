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
  public ImageView imageView;
  Logger logger = LoggerFactory.getLogger(ViewImagePageController.class);
  private ImageData image;

  @FXML
  private TextFlow textFlow;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    imageView.setImage(new javafx.scene.image.Image("file:" + this.image.getPath()));
    for (Directory directory : image.getMetadata().getDirectories()) {
      for (Tag tag : directory.getTags()) {
        Text text = new Text();
        text.setText(tag.toString() + "\n");
        textFlow.getChildren().add(text);
      }
    }
  }

  public void setImage(String path) {
    this.image = MetImaApplication.getContentManager().getImages().get(path);
  }

  public void setMetadataText(String path) {
    this.textFlow.setAccessibleText(
            MetImaApplication.getContentManager().getImages().get(path).getMetadata().toString());
  }

  @FXML
  private void btnHome() {
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }

  @FXML
  private void btnGallery() {
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }

  @FXML
  private void btnAddImage() {
    try {
      MetImaApplication.getStage().setScene(
          new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));


    } catch (IOException exception) {
      logger.error("File not found", exception);
    }

  }

  public ImageView getImageView() {
    return imageView;
  }

  public void setImageView(ImageView imageView) {
    this.imageView = imageView;
  }
}
