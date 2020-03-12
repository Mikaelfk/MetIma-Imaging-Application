package gruppe2.imagingapplication.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewImagePageController implements Initializable {
    Logger logger = LoggerFactory.getLogger(ViewImagePageController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void btnHome(){
        try {
            MetImaApplication.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("MetIma_HomePage.fxml"))));
        } catch (IOException exception) {
            logger.error("File not found", exception);
        }
    }

    @FXML
    private void btnAddImage(){
        try {
            MetImaApplication.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("MetIma_AddImagePage.fxml"))));
        } catch (IOException exception) {
            logger.error("File not found", exception);
        }
    }
}