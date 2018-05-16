package com.github.palindromicity.syslog;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface SyslogParser {
  Map<String,Object> parseLine(String line);

  void parseLine(String line, Consumer<Map<String,Object>> consumer);

  List<Map<String,Object>> parseLines(Reader reader);

  void parseLines(Reader reader, Consumer<Map<String,Object>> consumer);
}
