package org.gucardev.futures2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.gucardev.futures2.FutureUtil.createFutureOption;

public class Main {

  public static String sayHello(String name) {
    return "hello " + name;
  }

  public static String sayHello2(String name) {
    throw new RuntimeException("error");
  }

  public static void main(String[] args) {
    String url1 = "https://jsonplaceholder.typicode.com/posts/1";
    String url2 = "https://jsonplaceholder.typicode.com/posts";

    long time = System.currentTimeMillis();

    //   getRequest("https://jsonplaceholder.typicode.com/posts/1", 5000);
    //  getRequest("https://jsonplaceholder.typicode.com/posts", 5000);

    Map<String, FutureOption<Supplier<Object>, Function<Throwable, Object>>> features =
        new HashMap<>();

    // features.put("sayHello1", new FutureOption<>(() -> sayHello("Gurkan"), e -> null));
    features.put("sayHello1", createFutureOption(() -> sayHello("Gurkan"), e -> null));

    features.put("sayHello1", new FutureOption<>(() -> sayHello("Gurkan"), e -> null));
    features.put(
        "sayHello2",
        new FutureOption<>(
            () -> sayHello2("Ahmet"),
            e -> {
              System.out.println(e.getMessage());
              return "EMPTY";
            }));
    features.put("sayHello3", new FutureOption<>(() -> sayHello("Mehmet"), e -> null));
    features.put("getRequest1", new FutureOption<>(() -> getRequest(url1, 5000), e -> null));
    features.put("getRequest2", new FutureOption<>(() -> getRequest(url2, 5000), e -> null));

    var result = FutureUtil.allOf(features).join();

    System.out.println("time: " + (System.currentTimeMillis() - time));

    System.out.println(result.keySet());
    System.out.println(result.get("sayHello1"));
    System.out.println(result.get("sayHello2"));
    // System.out.println(result.get("sayHello3"));

  }

  public static String getRequest(String targetUrl, int delay) {
    try {
      sleep(delay);
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
}
