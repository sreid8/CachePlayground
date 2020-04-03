package com.seanreid.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

  private static String[] VALUES = {"A", "B", "C", "D", "E"};

  public static void main(String[] args) {
    System.out.println("Run 1");
    Map<String, Integer> values = buildMap();
    System.out.println(mapToString(values));

    System.out.println("Run 2");
    values = buildMap();
    System.out.println(mapToString(values));

  }

  private static Map<String, Integer> buildMap() {
    Map<String, Integer> values = new HashMap<>();
    for (final String value : VALUES) {
      values.put(value, App.getValue(value));
    }
    return values;
  }

  public static Integer getValue(String key) {
    return Cache.getInstance().getValue(key);
  }

  private static String mapToString(Map<String, Integer> map) {
    ArrayList<String> keyList = new ArrayList<>(map.keySet());
    Collections.sort(keyList);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < keyList.size(); i++) {
      final String key = keyList.get(i);
      sb.append("{ " + key + " : " + map.get(key) + " }");
      if (i != keyList.size() - 1) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }
}
