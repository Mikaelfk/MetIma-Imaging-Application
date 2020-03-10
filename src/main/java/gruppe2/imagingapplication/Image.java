package gruppe2.imagingapplication;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing an image with metadata and tags.
 */
public class Image {
    private String imageName;
    private ArrayList<String> tags;
    private String path;
    private Metadata metadata;

    /**
     * @param absolutePath The absolute path to the image for the class to represent
     * @param tags User-defined tags to describe image, set null for no tags
     * @throws ImageProcessingException If filetype is unknown
     * @throws IOException If file could not be found
     */
    public Image(String absolutePath, ArrayList<String> tags) throws ImageProcessingException, IOException {
        this.path = absolutePath;
        this.metadata = ImageMetadataReader.readMetadata(new File(path));
        if (tags != null) {
            this.tags = tags;
        } else {
            this.tags = new ArrayList<>();
        }
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName= imageName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
