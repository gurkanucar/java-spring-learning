package org.gucardev.abstractMethodPattern;

public class Main {

  public static void main(String[] args) {

    var textProcessor = new TextProcessor("read.txt");
    textProcessor.startToProcess();
  }
}
