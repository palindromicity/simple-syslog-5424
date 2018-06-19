package com.github.palindromicity.syslog.dsl;

public class ParseException extends RuntimeException {

  public ParseException(String reason) {
    super(reason);
  }

  public ParseException(String reason, Throwable throwable) {
    super(reason, throwable);
  }
}
