package org.gucardev.abstractMethodPattern;

import lombok.Data;
import lombok.ToString;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextProcessor extends BaseProcessor {
  public TextProcessor(String path) {
    super(path);
  }

  @Override
  public <T> T read(InputStream is) throws Exception {
    System.out.println("TextProcessor reading...");
    Scanner in = new Scanner(new InputStreamReader(is, StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    while (in.hasNext()) {
      sb.append(in.next());
    }
    in.close();
    return (T) sb;
  }

  @Override
  public <T> T process(T payload) {
    System.out.println("TextProcessor processing...");
    TextResult result = new TextResult();
    result.setPath(path);
    result.setLength(((StringBuilder) payload).toString().length());
    return (T) result;
  }

  @Override
  public <T> T write(T payload) throws Exception {
    System.out.println("TextProcessor writing...");
    FileWriter myWriter = new FileWriter("./processed_" + path);
    myWriter.write(payload.toString());
    myWriter.close();
    return null;
  }
}

@Data
@ToString
class TextResult {
  private String path;
  private long length;
}
