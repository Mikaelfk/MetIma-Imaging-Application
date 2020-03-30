package gruppe2.imagingapplication;

import java.util.List;

public class TagsList {
  private List<String> tags;

  public TagsList(List<String> tags) {
    this.tags = tags;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
