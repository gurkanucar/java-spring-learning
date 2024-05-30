package org.gucardev.funtionalInterfaces;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

  public static Consumer<String> printConsumer =
      value -> System.out.println(value); // Takes args, don't return anything

  public static Predicate<String> isFirstLetterUpperCase =
      value -> Character.isUpperCase(value.charAt(0)); // Takes args, returns just True|False

  public static Function<String, String> convertToUpperCase =
      value -> value.toUpperCase(); // Takes args, returns values

  public static Supplier<List<String>> getData
      = () -> List.of("gurkan", "Metin", "ali", "Sezai"); // Don't take args, returns values


  public static void main(String[] args) {
    getData.get().stream()
        .filter(isFirstLetterUpperCase)
        .map(convertToUpperCase)
        .forEach(printConsumer);
  }

}
