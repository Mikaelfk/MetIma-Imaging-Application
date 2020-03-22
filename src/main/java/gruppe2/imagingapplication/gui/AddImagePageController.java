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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddImagePageController implements Initializable {

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
  Logger logger = LoggerFactory.getLogger(AddImagePageController.class);


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    btnAddImage.disableProperty().bind(Bindings.createBooleanBinding(
        () -> txtFileName.getText().trim().isEmpty() || !imageSelected,
        txtFileName.textProperty(), btnAddImage.defaultButtonProperty()
    ));
  }

  @FXML
  private void chooseFile(ActionEvent event)  {
    FileChooser fileChooser = new FileChooser();
    selectedFile = fileChooser.showOpenDialog(new Stage());
    if (selectedFile != null) {
      selectedFileText.setText(selectedFile.getName());
      imagePreview.setImage(new Image("file:" + selectedFile.getAbsolutePath()));
      if (!imagePreview.getImage().isError()) {
        imageSelected = true;
      }
    }
  }

  @FXML
  private void addImage(ActionEvent event) throws IOException {
    List<String> tags = null;
    if (!txtTags.getText().isEmpty()) {
      tags = Arrays.asList(txtTags.getText().split("\\s*,\\s*"));
    }
    MetImaApplication.getContentManager().addImageToDB(selectedFile.getAbsolutePath(), tags);
    MetImaApplication.getContentManager().getImages().get(selectedFile.getAbsolutePath())
        .setImageName(txtFileName.getText());
    MetImaApplication.getStage().setScene(
        new Scene(FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml")))
    );
  }

  /**
   * This method handles what happens when the home button is pressed.
   *
   * @param event Represents the action when the button is pressed
   */
  @FXML
  private void btnHome(ActionEvent event) {
    try {
      MetImaApplication.getStage().setScene(
              new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
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
    try {
      MetImaApplication.getStage().setScene(
              new Scene(FXMLLoader.load(getClass().getResource("MetIma_GalleryPage.fxml"))));
    } catch (IOException exception) {
      logger.error("File not found", exception);
    }
  }
}