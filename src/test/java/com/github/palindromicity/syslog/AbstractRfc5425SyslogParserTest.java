package com.github.palindromicity.syslog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractRfc5425SyslogParserTest {

  protected static List<Map<String, Object>> handleFile(String fileName, SyslogParser parser) throws Exception {
    Reader reader = new BufferedReader(new FileReader(new File(fileName)));
    return parser.parseLines(reader);
  }

  protected static void handleFile(String fileName, SyslogParser parser, Consumer<Map<String, Object>> consumer)
      throws Exception {
    Reader reader = new BufferedReader(new FileReader(new File(fileName)));
    parser.parseLines(reader, consumer);
  }

  protected static Map<String, Object> handleLine(String line, SyslogParser parser) throws Exception {
    return parser.parseLine(line);
  }

  protected static void handleLine(String line, SyslogParser parser, Consumer<Map<String, Object>> consumer)
      throws Exception {
    parser.parseLine(line, consumer);
  }
}
