package gruppe2.imagingapplication;

import com.drew.imaging.ImageProcessingException;
import gruppe2.imagingapplication.gui.MetImaApplication;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ContentManager {
  private EntityManagerFactory entityManagerFactory;
  Logger logger = LoggerFactory.getLogger(ContentManager.class);
  /**
   * A makeshift HashMap for storing the images. This is just for storing the images for the MVP.
   */
  private HashMap<String, ImageData> images;
  private HashMap<String, ImageData> searchResults;

  /**
   * Constructs a new content manager object.
   */
  public ContentManager() {
    images = new HashMap<>();
    searchResults = new HashMap<>();
    entityManagerFactory = Persistence.createEntityManagerFactory("AsukaBestGrill");
    readFromDB();
  }

  /**
   * This method reads the image data from the database, and puts them
   * in the images HashMap.
   */
  public void readFromDB() {
    List<ImageData> imageDataList;
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    String jdbcQuery = "SELECT image FROM ImageData image";
    Query databaseQuery = entityManager.createQuery(jdbcQuery);
    imageDataList = (List<ImageData>) databaseQuery.getResultList();
    for (ImageData imageData : imageDataList) {
      if ((images.get(imageData.getPath()) == null) || !images.get(imageData.getPath()).getTags().equals(imageData.getTags())) {
        imageData.setImage(new Image("file:" + imageData.getPath()));
        images.put(imageData.getPath(), imageData);
      }
    }
  }

  /**
   * Returns the images field.
   *
   * @return Returns it as HashMap<>
   */
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
  public void addImageToDB(String absolutePath, List<String> tags) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      ImageData image = new ImageData(absolutePath, tags);
      entityManager.getTransaction().begin();
      entityManager.merge(image);
      entityManager.flush();
      entityManager.getTransaction().commit();
    } catch (ImageProcessingException e) {
      logger.error("Not and image file", e);
    } catch (IOException e) {
      logger.error("Could not find file", e);
    } finally {
      entityManager.close();
    }
    readFromDB();
  }

  /**
   * Takes a search term and performs a search.
   *
   * @param searchTerm Takes a search term as String
   */
  public void performSearch(String searchTerm) {
    this.searchResults = fullSearch(searchTerm);
  }

  /**
   * Returns the search result.
   *
   * @return Returns the result as HashMap<>
   */
  public HashMap<String, ImageData> getSearchResults() {
    return searchResults;
  }

  /**
   * Removes an image from images by using the image path.
   *
   * @param path The path of the image as a String
   */
  public void removeImage(String path) {
    logger.info("Removed image: {}", path);
    images.remove(path);
  }

  /**
   * Method that combines tagSearch and imageNameSearch and searches for both.
   *
   * @param searchTerm String to search for
   * @return Matching results in a HashMap
   */
  public HashMap<String, ImageData> fullSearch(String searchTerm) {
    HashMap<String, ImageData> results = new HashMap<>();

    tagSearch(searchTerm).forEach(results::put);
    imageNameSearch(searchTerm).forEach(results::put);

    return results;
  }

  /**
   * Method that searches by tags and returns a hashmap with all matches.
   *
   * @param searchTerm String to search for
   * @return Matching results in a HashMap
   */
  private HashMap<String, ImageData> tagSearch(String searchTerm) {
    HashMap<String, ImageData> gallery = MetImaApplication.getContentManager().getImages();
    HashMap<String, ImageData> results = new HashMap<>();

    gallery.forEach((String key, ImageData image) ->
            image.getTags().forEach(tag -> {
              if (tag.toLowerCase().contains(searchTerm)) {
                results.put(key, image);
              }
            }));

    return results;
  }

  /**
   * Method that searches by image names and returns a hashmap with all matches.
   *
   * @param searchTerm String to search for
   * @return Matching results in a HashMap
   */
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
