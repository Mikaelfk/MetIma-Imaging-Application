package gruppe2.imagingapplication.serializableclasses;

import java.io.Serializable;
import java.util.List;

public class TagsListSerializable implements Serializable {
  private List<String> tags;

  public TagsListSerializable(List<String> tags) {
    this.tags = tags;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
