package gruppe2.imagingapplication;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.drew.metadata.Metadata;
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
  @Transient
  private gruppe2.imagingapplication.TagsList tags;
  private gruppe2.imagingapplication.MetadataSerializable metadata;
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
    this.metadata = new MetadataSerializable(ImageMetadataReader.readMetadata(new File(path)));
    this.image = image;
    if (tags != null) {
      this.tags = new TagsList(tags);
    } else {
      this.tags = new TagsList(new ArrayList<>());
    }
  }

  public ImageData() {

  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public List<String> getTags() {
    return tags.getTags();
  }

  public void addTag(String tag) {
    this.tags.getTags().add(tag);
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Metadata getMetadata() {
    return metadata.getMetadata();
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
