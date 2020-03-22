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
  public void exportImagesToPdf(HashMap<String, ImageData> images,
                                String filename) {
    PDDocument document = new PDDocument();

    // Might have to put this in the for each loop to generate one page per image.

    images.forEach((String key, ImageData image) -> {
      String path = image.getPath();
      PDPage page = new PDPage();
      document.addPage(page);
      try {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDImageXObject imageXObject = PDImageXObject.createFromFile(path, document);
        double height = 0;
        double width = 0;
        if(imageXObject.getWidth() <= imageXObject.getHeight()) {
          width = 900*((double)imageXObject.getWidth()/imageXObject.getHeight());
          height = 900;
          contentStream.drawImage(imageXObject, 200, 0, (float)width, (float)height);
        } else {
          height = 600*((double)imageXObject.getHeight()/imageXObject.getWidth());
          width = 600;
          contentStream.drawImage(imageXObject, 6, 300, (float)width, (float)height);
        }
        System.out.println(width);
        System.out.println(height);

        contentStream.close();
        document.save(filename + ".pdf");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
