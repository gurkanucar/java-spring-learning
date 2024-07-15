package org.gucardev.log_test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
    sayHello();
  }

  public static void sayHello() {
    log.info("selam");
  }
}
