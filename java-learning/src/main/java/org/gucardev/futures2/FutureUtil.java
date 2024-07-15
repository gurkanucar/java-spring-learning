package org.gucardev.futures2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class FutureUtil {

  public static <T> FutureOption<Supplier<T>, Function<Throwable, T>> createFutureOption(
      Supplier<T> method, Function<Throwable, T> handler) {
    return new FutureOption<>(method, handler);
  }


  public static CompletableFuture<Map<String, Object>> allOf(
      Map<String, FutureOption<Supplier<Object>, Function<Throwable, Object>>> tasks) {

    List<CompletableFuture<Void>> allFutures = new ArrayList<>();

    Map<String, Object> resultMap = new ConcurrentHashMap<>();

    for (Map.Entry<String, FutureOption<Supplier<Object>, Function<Throwable, Object>>> task : tasks.entrySet()) {
      CompletableFuture<Void> cf = CompletableFuture.supplyAsync(task.getValue().getMethod())
          .exceptionally(task.getValue().getException())
          .thenAccept(result -> resultMap.put(task.getKey(), result));

      allFutures.add(cf);
    }

    return CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0]))
        .thenApply(v -> resultMap);
  }


}
