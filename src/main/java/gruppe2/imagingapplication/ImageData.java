package gruppe2.imagingapplication;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.drew.metadata.Tag;
import javafx.scene.image.Image;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * A class representing an image with metadata and tags.
 */
@Entity
public class ImageData implements Serializable {
  @Id
  private String path;
  private String imageName;
  private String tags;
  private String metadata;
  @Transient
  private Image image;

  /**
   * This is a constructor for the Image class.
   *
   * @param absolutePath The absolute path to the image for the class to represent
   * @param tags         User-defined tags to describe image, set null for no tags
   * @throws ImageProcessingException If filetype is unknown
   * @throws IOException              If file could not be found
   */
  public ImageData(String absolutePath, List<String> tags, Image image) throws
      ImageProcessingException, IOException {
    this.path = absolutePath;
    this.metadata = convertMetadataToString();
    this.image = image;
    if (tags != null) {
      this.tags = convertTagsToString(tags);
    } else {
      this.tags = "";
    }
  }

  public ImageData() {

  }

  /**
   * This method converts the metadata into a listed String.
   *
   * @return Returns the metadata as a listed String
   * @throws ImageProcessingException If filetype is unknown
   * @throws IOException              If file could not be found
   */
  public String convertMetadataToString() throws ImageProcessingException, IOException {
    StringBuilder metaData = new StringBuilder();
    for (Directory directory : ImageMetadataReader.readMetadata(new File(path)).getDirectories()) {
      for (Tag tag : directory.getTags()) {
        metaData.append(tag.toString()).append("\n");
      }
    }
    return metaData.toString();
  }

  /**
   * This method collects all the tags to a single string.
   * @param tags A list of tags
   * @return returns the String of tags
   */
  public String convertTagsToString(List<String> tags) {
    StringBuilder allTheTags = new StringBuilder();
    for(String tag: tags) {
      allTheTags.append(tag).append("\n");
    }
    return allTheTags.toString();
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getTags() {
    return tags;
  }

  /*public void addTag(String tag) {
    this.tags.add(tag);
  }*/

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMetadata() {
    return metadata;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
