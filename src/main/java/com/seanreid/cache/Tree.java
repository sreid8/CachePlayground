package com.seanreid.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {

  private static Map<String, List<String>> adjacencyList = new HashMap<>();

  static {
    List<String> aList = new ArrayList<>();
    aList.add("B");
    aList.add("C");
    aList.add("E");
    List<String> bList = new ArrayList<>();
    bList.add("D");

    adjacencyList.put("A", aList);
    adjacencyList.put("B", bList);
  }

  public static List<String> get(String key) {
    return adjacencyList.get(key);
  }
}
