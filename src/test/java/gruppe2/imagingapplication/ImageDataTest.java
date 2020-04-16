package gruppe2.imagingapplication;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageDataTest {
  private Logger logger = LoggerFactory.getLogger(ImageDataTest.class);

  @BeforeAll
  void setUp() {

  }

  @Test
  public void metadataTests() {
    ImageData testImage = null;
    try {
      // need test imge to work with, this path might be different depending on current working directory
      // works if working directory is project root
      testImage = new ImageData(System.getProperty("user.dir")
          + "/src/test/java/gruppe2/imagingapplication/test_image.png", null, "test_image");
    } catch (Exception e) {
      logger.error("Image file not found", e);
    }

    logger.info("Test 1: Hashmap is populated with data");
    if (testImage.getMetadata().isEmpty()) {
      // hashmap is empty, which means no metadata was found
      assert false;
    } else {
      // hashmap is empty, which means metadata was found
      assert true;
    }
  }
}
