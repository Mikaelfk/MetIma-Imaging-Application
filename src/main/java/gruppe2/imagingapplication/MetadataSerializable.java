package gruppe2.imagingapplication;

import com.drew.metadata.Metadata;

import java.io.Serializable;

public class MetadataSerializable implements Serializable {
  private Metadata metadata;

  public MetadataSerializable(Metadata metadata) {
    this.metadata = metadata;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }
}
