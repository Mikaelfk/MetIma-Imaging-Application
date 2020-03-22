package gruppe2.imagingapplication;

import gruppe2.imagingapplication.gui.MetImaApplication;
import java.util.HashMap;

public class Search {

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
