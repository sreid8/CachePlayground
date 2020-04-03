package com.seanreid.cache;

import java.util.Random;

public class ValueGenerator {

  private static Random random = new Random();

  public static Integer getValue() {
    return random.nextInt();
  }
}
