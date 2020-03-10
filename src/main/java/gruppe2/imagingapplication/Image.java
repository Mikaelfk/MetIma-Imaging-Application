package gruppe2.imagingapplication;

import java.util.HashMap;


public class Image {
    private String filename;
    private String tags;

    /**
     * This is a HashMap that contains the metadata of the images
     * There is a unique HashMap for each image and the different keys
     * are all the different metadata of the image
     */
    private HashMap<String, String> path = new HashMap<>();
}
