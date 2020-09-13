package com.sedia.my_course.utils;

import java.util.function.Supplier;

public class ExceptionUtil {

  public static Supplier<NullPointerException> nullPointerException() {
    return NullPointerException::new;
  }

  public static Supplier<NullPointerException> nullPointerException(String message) {
    return () -> new NullPointerException(message);
  }

}
