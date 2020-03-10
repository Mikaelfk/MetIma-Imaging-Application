package gruppe2.imagingapplication.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddImagePageController implements Initializable {

    Logger logger = LoggerFactory.getLogger(AddImagePageController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void btnHome(ActionEvent event){
        logger.info("Home button pressed.");
    }

    @FXML
    private void btnAdd(ActionEvent event){
        logger.info("Add button pressed.");
    }

    @FXML
    private void btnCancel(ActionEvent event){
        logger.info("Cancel button pressed.");
    }

    @FXML
    private void btnGallery(ActionEvent event){
        logger.info("Gallery button pressed.");
    }
}
