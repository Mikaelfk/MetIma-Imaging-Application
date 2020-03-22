package gruppe2.imagingapplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Export {
  Logger logger = LoggerFactory.getLogger(Export.class);

  /**
   * This method generates a pdf document and adds images to it based on the HashMap you give it.
   *
   * @param images Takes a HashMap<String, ImageData>
   *               that holds the images you wish to export to a pdf document
   */
  public void exportImagesToPdf(HashMap<String, ImageData> images) {
    if (images.isEmpty()) {
      logger.info("No pictures.");
    } else {
      try (PDDocument document = new PDDocument()) {
        images.forEach((String key, ImageData image) -> {
          String path = image.getPath();
          PDPage page = new PDPage();
          document.addPage(page);
          try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDImageXObject imageXObject = PDImageXObject.createFromFile(path, document);
            double height;
            double width;
            if (imageXObject.getWidth() <= imageXObject.getHeight()) {
              width = 600 * ((double) imageXObject.getWidth() / imageXObject.getHeight());
              height = 600;
              contentStream.drawImage(imageXObject, 0, 0, (float) width, (float) height);
            } else {
              height = 600 * ((double) imageXObject.getHeight() / imageXObject.getWidth());
              width = 600;
              contentStream.drawImage(imageXObject, 6, 300, (float) width, (float) height);
            }
            contentStream.close();

          } catch (IOException exception) {
            logger.error("No images found", exception);
          }
        });
        saveDocument(document);
      } catch (Exception e) {
        logger.error("Could not export to pdf");
      }
    }
  }

  /**
   * Takes a PDDocument and saves it with a file chooser.
   *
   * @param document Takes a PDDocument
   * @throws IOException Throws an exception if the document does not exist
   */
  public void saveDocument(PDDocument document) throws IOException {
    FileChooser fileChooser = new FileChooser();
    File saveLocation = fileChooser.showSaveDialog(new Stage());
    if (!saveLocation.getAbsolutePath().endsWith(".pdf")) {
      document.save(saveLocation.getAbsolutePath() + ".pdf");
    } else {
      document.save(saveLocation.getAbsolutePath());
    }
  }
}