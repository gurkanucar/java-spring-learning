package org.gucardev.completablefuture;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Main {

  public static void main(String[] args) {

    CompletableFuture.supplyAsync(() -> getRequest("https://jsonplaceholder.typicode.com/posts/1"))
        .thenAccept(res -> System.out.printf("Result: %s%n", res)).join();

    System.out.println("end of the code");


  }

  public static String getRequest(String targetUrl) {
    try {
      sleep(2500);
      URL url = new URL(targetUrl);
      OkHttpClient client = new OkHttpClient().newBuilder().build();
      Request request = new Request.Builder().url(url).get().build();
      Response response = client.newCall(request).execute();
      return Objects.requireNonNull(response.body()).string();
    } catch (Exception e) {
      throw new RuntimeException("Hata! %s".formatted(e.getMessage()));
    }
  }


  static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> generateNickname(int limit, String groupId) {
    String upper = "ABCDEFGHJIJKLMNOPRSTUVWXYZ";
    String lower = upper.toLowerCase();
    String numbers = "0123456789";
    String symbols = upper + lower + numbers;

    int rightLimit = symbols.length();
    int generatedStringCount = 10;
    HashSet<String> generatedNick = new HashSet<>();
    while (generatedNick.size() != limit) {
      boolean up = false;
      boolean low = false;
      boolean number = false;
      StringBuilder tempString = new StringBuilder();
      for (int i = 0; i < generatedStringCount; i++) {

        int random = new Random().nextInt(rightLimit);
        char tmp = symbols.charAt(random);
        if (!up && upper.contains(String.valueOf(tmp))) {
          up = true;
        } else if (!low && lower.contains(String.valueOf(tmp))) {
          low = true;
        } else if (!number && numbers.contains(String.valueOf(tmp))) {
          number = true;
        }
        tempString.append(tmp);
      }
      String generated = tempString.toString();
      boolean exists;
//      if (groupId == null) {
//        exists = userRepository.existsByNickName(generated);
//      } else {
//        exists = userRepository.existsByNickNameAndGroup(generated, groupId);
//      }
//      if (up && low && number && !exists) {
//        generatedNick.add(generated);
//      }
    }
    return new ArrayList<>(generatedNick);
  }

}
