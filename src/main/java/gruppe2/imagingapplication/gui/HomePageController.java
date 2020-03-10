package gruppe2.imagingapplication.gui;

import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void viewGallery(ActionEvent event) {
        logger.info("View Gallery button pressed");
    }

}
