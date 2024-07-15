package org.gucardev.abstractMethodPattern;

import java.io.InputStream;
import java.util.Objects;

public abstract class BaseProcessor {
  public String path;
  protected InputStream is;

  private BaseProcessor() {}

  public BaseProcessor(String path) {
    this.path = path;
  }

  public abstract <T> T read(InputStream is) throws Exception;

  public abstract <T> T process(T payload) throws Exception;

  public abstract <T> T write(T payload) throws Exception;

  public void startToProcess() {
    System.out.println("process started");
    Object data = null;
    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream is = classloader.getResourceAsStream(path);
      data = read(is);
    } catch (Exception e) {
      System.out.println("something went wrong while reading!");
      e.printStackTrace();
    }
    Object processResult = null;
    try {
      if (Objects.isNull(data)) {
        throw new RuntimeException("read result is null");
      }
      processResult = process(data);
    } catch (Exception e) {
      System.out.println("something went wrong while processing!");
      e.printStackTrace();
    }

    try {
      if (Objects.isNull(processResult)) {
        throw new RuntimeException("process result is null");
      }
      write(processResult);
    } catch (Exception e) {
      System.out.println("something went wrong while writing!");
      e.printStackTrace();
    }
  }
}
