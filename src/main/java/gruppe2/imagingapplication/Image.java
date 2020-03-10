package gruppe2.imagingapplication;

import java.util.HashMap;


public class Image {
    private String filename;
    private String tags;
    private String path;

    /**
     * This is a HashMap that contains the metadata of the images
     * There is a unique HashMap for each image and the different keys
     * are all the different metadata of the image
     */
    private HashMap<String, String> metadata = new HashMap<>();

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
