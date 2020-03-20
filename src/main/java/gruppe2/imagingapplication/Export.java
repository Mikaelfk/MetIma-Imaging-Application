package gruppe2.imagingapplication;

import java.io.IOException;
import java.util.HashMap;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class Export {
  /**
   * This method generates a pdf document and adds images to it based on the HashMap you give it
   *
   * @param images   Takes a HashMap<String, gruppe2.imagingapplication.Image>
   *                 that holds the images you wish to export to a pdf document
   * @param filename Takes a string that it sets as filename
   */
  public void exportImagesToPdf(HashMap<String, gruppe2.imagingapplication.Image> images,
                                String filename) {
    PDDocument document = new PDDocument();

    // Might have to put this in the for each loop to generate one page per image.
    PDPage page = new PDPage();
    document.addPage(page);

    images.forEach((String key, Image image) -> {
      String path = image.getPath();
      try {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDImageXObject imageXObject = PDImageXObject.createFromFile(path, document);
        contentStream.drawImage(imageXObject, 0, 0);
        contentStream.close();
        document.save(filename + ".pdf");
        document.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
