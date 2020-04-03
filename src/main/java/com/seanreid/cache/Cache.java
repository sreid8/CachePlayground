package com.seanreid.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Cache {

  private static final Cache INSTANCE = new Cache();

  private final LoadingCache<String, Integer> cache;

  public static Cache getInstance() {
    return INSTANCE;
  }

  Cache() {
    cache =
        Caffeine.newBuilder()
            .maximumSize(3)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Integer>() {
              @Nullable
              @Override
              public Integer load(@NonNull String s) throws Exception {
                System.out.println("Loading for key: " + s);
                List<String> deps = Tree.get(s);
                if (deps == null || deps.isEmpty()) {
                  Integer i = ValueGenerator.getValue();
                  System.out.println(
                      "Returning value " +
                          i +
                          " for key " +
                          s +
                          " because it has no deps.");
                  return i;
                }
                Integer i = 0;
                for (final String key : deps) {
                  i = i + cache.get(key);
                }
                i = i + ValueGenerator.getValue();
                System.out.println(
                    "Returning " +
                        i.toString() +
                        " as value for key " +
                        s +
                        " because it is a sum of " +
                        Arrays.toString(deps.toArray()));
                return i;
              }
            });
  }

  public Integer getValue(String key) {
    return this.cache.get(key);
  }

  public Map<String, Integer> asMap() {
    return cache.asMap();
  }
}
