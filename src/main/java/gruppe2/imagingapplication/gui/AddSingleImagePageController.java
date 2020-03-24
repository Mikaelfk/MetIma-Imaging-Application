package gruppe2.imagingapplication.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddSingleImagePageController implements Initializable {

  @FXML
  private Text selectedFileText;
  @FXML
  private ImageView imagePreview;
  @FXML
  private TextField txtFileName;
  @FXML
  private TextField txtTags;
  @FXML
  private Button btnAddImage;
  private boolean imageSelected = false;
  private File selectedFile;
  private List<File> multipleFiles;
  Logger logger = LoggerFactory.getLogger(AddSingleImagePageController.class);


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    btnAddImage.disableProperty().bind(Bindings.createBooleanBinding(
        () -> txtFileName.getText().trim().isEmpty() || !imageSelected,
        txtFileName.textProperty(), btnAddImage.defaultButtonProperty()
    ));
  }

  /**
   * This method prompts the user tp chooses a file from your library and
   * opens the picture in the image preview.
   *
   * @param event Selecting an image in your library
   */
  @FXML
  private void chooseFile(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    multipleFiles = fileChooser.showOpenMultipleDialog(new Stage());
    for (File selectedImage : multipleFiles) {
      if (selectedImage != null) {
        selectedFileText.setText(selectedImage.getName());
        imagePreview.setImage(new Image("file:" + selectedImage.getAbsolutePath()));
        if (!imagePreview.getImage().isError()) {
          imageSelected = true;
        }
      }
    }
  }

  /**
   * This method the the chosen file and ads it to the gallery.
   * Then it takes you to the gallery page.
   */
  @FXML
  private void addImage(ActionEvent event) throws IOException {
    List<String> tags = null;
    if (!txtTags.getText().isEmpty()) {
      tags = Arrays.asList(txtTags.getText().split("\\s*,\\s*"));
    }
    for (File selectedImage : multipleFiles) {
      MetImaApplication.getContentManager().addImageToDB(selectedImage.getAbsolutePath(), tags);
      MetImaApplication.getContentManager().getImages().get(selectedImage.getAbsolutePath())
          .setImageName(txtFileName.getText());
    }
    MetImaApplication.getScene().setRoot(
            FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml")));
  }

  /**
   * This method handles what happens when the home button is pressed.
   * The button sets a new scene by using the MetIma_HomePage.fxml file.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnHome(ActionEvent event) {
    try {
      MetImaApplication.getScene().setRoot(
              FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml")));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }

  /**
   * This method handles what happens when the gallery button is pressed.
   * The button sets a new scene by using the MetIma_GalleryPage.fxml file.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnGallery(ActionEvent event) {
    try {
      MetImaApplication.getScene().setRoot(
              FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml")));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }
}