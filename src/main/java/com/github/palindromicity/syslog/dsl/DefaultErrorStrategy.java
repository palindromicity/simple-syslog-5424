package com.github.palindromicity.syslog.dsl;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;

public class DefaultErrorStrategy extends org.antlr.v4.runtime.DefaultErrorStrategy {

  @Override
  public void reportError(Parser parser, RecognitionException recognitionException) {
    throw new ParseException("Parse Error " + recognitionException.getMessage(), recognitionException);
  }
}
