package gruppe2.imagingapplication;

import gruppe2.imagingapplication.gui.MetImaApplication;

import java.util.HashMap;

public class Search {

  public HashMap<String, gruppe2.imagingapplication.Image> fullSearch(String searchTerm) {
    HashMap<String, gruppe2.imagingapplication.Image> results = new HashMap<>();

    tagSearch(searchTerm).forEach(results::put);
    imageNameSearch(searchTerm).forEach(results::put);

    return results;
  }

  private HashMap<String, gruppe2.imagingapplication.Image> tagSearch(String searchTerm) {
    HashMap<String, gruppe2.imagingapplication.Image> gallery = MetImaApplication.getContentManager().getImages();
    HashMap<String, gruppe2.imagingapplication.Image> results = new HashMap<>();

    gallery.forEach((String key, Image image) -> {
      image.getTags().forEach(tag -> {
        if (tag.toLowerCase().contains(searchTerm)) {
          results.put(key, image);
        }
      });
    });

    return results;
  }

  private HashMap<String, gruppe2.imagingapplication.Image> imageNameSearch(String searchTerm) {
    HashMap<String, gruppe2.imagingapplication.Image> gallery = MetImaApplication.getContentManager().getImages();
    HashMap<String, gruppe2.imagingapplication.Image> results = new HashMap<>();

    gallery.values().forEach((Image image) -> {
      if (image.getImageName().toLowerCase().contains(searchTerm)) {
        results.put(image.getPath(), image);
      }
    });

    return results;
  }
}
