package org.gucardev.abstractMethodPattern;

import java.io.InputStream;
import java.util.Objects;

public abstract class BaseProcessor<T> {
  protected String path;

  public BaseProcessor(String path) {
    this.path = path;
  }

  public abstract T read(InputStream is) throws Exception;
  public abstract T process(T payload) throws Exception;
  public abstract void write(T payload) throws Exception;

  public void startToProcess() {
    System.out.println("Processing started");
    T data = null;
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      try (InputStream is = classLoader.getResourceAsStream(path)) {
        data = read(is);
      }
    } catch (Exception e) {
      System.out.println("Error during reading: " + e.getMessage());
      return;
    }

    T processResult = null;
    try {
      processResult = process(data);
    } catch (Exception e) {
      System.out.println("Error during processing: " + e.getMessage());
      return;
    }

    try {
      write(processResult);
    } catch (Exception e) {
      System.out.println("Error during writing: " + e.getMessage());
    }
  }
}

