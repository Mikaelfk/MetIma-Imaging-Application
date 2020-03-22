package gruppe2.imagingapplication;

import com.drew.imaging.ImageProcessingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import gruppe2.imagingapplication.gui.MetImaApplication;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContentManager {
  Logger logger = LoggerFactory.getLogger(ContentManager.class);
  /**
   * A makeshift HashMap for storing the images. This is just for storing the images for the MVP.
   */
  private HashMap<String, ImageData> images;
  private HashMap<String, ImageData> searchResults;

  public ContentManager() throws ImageProcessingException, IOException {
    images = new HashMap<>();
    searchResults = new HashMap<>();
  }

  public HashMap<String, ImageData> getImages() {
    return images;
  }

  /**
   * Method for adding images to the DB with it's path.
   *
   * @param absolutePath The absolute path of the image to add
   * @param tags         User-defined tags to describe image, set null for no tags
   * @return True/False for image was added/image was not added to to error respectively
   */
  public boolean addImageToDB(String absolutePath, List<String> tags) {
    try {
      images.put(absolutePath, new ImageData(absolutePath, tags, new Image("file:"+absolutePath)));
      return true;
    } catch (ImageProcessingException e) {
      logger.error("Not and image file", e);
      return false;
    } catch (IOException e) {
      logger.error("Could not find file", e);
      return false;
    }
  }

  public void performSearch(String searchTerm) {
    this.searchResults = fullSearch(searchTerm);
  }

  public HashMap<String, ImageData> getSearchResults() {
    return searchResults;
  }

  public void removeImage(String path) {
    logger.info("Removed image: {}", path);
    images.remove(path);
  }

  public HashMap<String, ImageData> fullSearch(String searchTerm) {
    HashMap<String, ImageData> results = new HashMap<>();

    tagSearch(searchTerm).forEach(results::put);
    imageNameSearch(searchTerm).forEach(results::put);

    return results;
  }

  private HashMap<String, ImageData> tagSearch(String searchTerm) {
    HashMap<String, ImageData> gallery = MetImaApplication.getContentManager().getImages();
    HashMap<String, ImageData> results = new HashMap<>();

    gallery.forEach((String key, ImageData image) -> {
      image.getTags().forEach(tag -> {
        if (tag.toLowerCase().contains(searchTerm)) {
          results.put(key, image);
        }
      });
    });

    return results;
  }

  private HashMap<String, ImageData> imageNameSearch(String searchTerm) {
    HashMap<String, ImageData> gallery = MetImaApplication.getContentManager().getImages();
    HashMap<String, ImageData> results = new HashMap<>();

    gallery.values().forEach((ImageData image) -> {
      if (image.getImageName().toLowerCase().contains(searchTerm)) {
        results.put(image.getPath(), image);
      }
    });

    return results;
  }
}
