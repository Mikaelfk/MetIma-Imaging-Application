package gruppe2.imagingapplication.serializableclasses;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import java.io.*;

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

  @Override
  public String toString()
  {
    return this.metadata.toString();
  }

  /**
   * Serialize this instance.
   *
   * @param out Target to which this instance is written.
   * @throws IOException Thrown if exception occurs during serialization.
   */
  private void writeObject(final ObjectOutputStream out, String path) throws IOException
  {
    try {
      out.writeUTF(ImageMetadataReader.readMetadata(new File(path)).toString());
    } catch (ImageProcessingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deserialize this instance from input stream.
   *
   * @param in Input Stream from which this instance is to be deserialized.
   * @throws IOException Thrown if error occurs in deserialization.
   * @throws ClassNotFoundException Thrown if expected class is not found.
   */
  private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
  {
    this.metadata = (Metadata) in.readObject();
  }

  private void readObjectNoData() throws ObjectStreamException
  {
    throw new InvalidObjectException("Stream data required");
  }
}
