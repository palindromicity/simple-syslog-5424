package com.github.palindromicity.syslog;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SyslogParserBuilderTest {

  @Test
  public void testWithSpecification() {
    SyslogParser parser = new SyslogParserBuilder().forSpecification(SyslogSpecification.RFC_5424).build();
    assertTrue(parser.getClass() == Rfc5424SyslogParser.class);
  }

  @Test
  public void testNoSpecification() {
    SyslogParser parser = new SyslogParserBuilder().build();
    assertTrue(parser.getClass() == Rfc5424SyslogParser.class);
  }
}