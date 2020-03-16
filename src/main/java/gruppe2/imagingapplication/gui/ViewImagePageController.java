package gruppe2.imagingapplication.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gruppe2.imagingapplication.Image;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ViewImagePageController implements Initializable {
  public ImageView imageView;
  Logger logger = LoggerFactory.getLogger(ViewImagePageController.class);
  private Image image;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    imageView.setImage(new javafx.scene.image.Image("file:" + this.image.getPath()));
  }

  public void setImage(String path) {
    this.image = MetImaApplication.getContentManager().getImages().get(path);
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
  private void btnAddImage() {
    try {
      MetImaApplication.getStage().setScene(
              new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }

  public ImageView getImageView(){
    return imageView;
  }

  public void setImageView(ImageView imageView){
    this.imageView = imageView;
  }
}
