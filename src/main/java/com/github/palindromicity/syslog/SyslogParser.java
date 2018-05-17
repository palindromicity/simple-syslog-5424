package com.github.palindromicity.syslog;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * {@code SyslogParser} defines an interface for classes that parse Syslog into {@code Map}.
 */
public interface SyslogParser {

  /**
   * Parse a {@code String} to a {@code Map}.
   *
   * @param line the line of Syslog to parse
   * @return a {@code Map}
   */
  Map<String, Object> parseLine(String line);

  /**
   * Parse a {@code String} to a {@code Map} and provides that {@code Map} to the provided {@code Consumer}.
   *
   * @param line the line of Syslog to parser
   * @param consumer the {@code Consumer}
   */
  void parseLine(String line, Consumer<Map<String, Object>> consumer);

  /**
   * Reads each line from the {@code Reader} and parses it to a {@code List} of {@code Map}.
   *
   * @param reader {@code Reader} used.  It is not closed in this method.
   * @return {@code List} of {@code Map}
   */
  List<Map<String, Object>> parseLines(Reader reader);

  /**
   * Reads each line from the {@code Reader} and parses it to {@code Map}, which is passed to the
   * provided {@code Consumer}.
   *
   * @param reader {@code Reader} used.  It is not closed in this method.
   * @param consumer the {@code Consumer}
   */
  void parseLines(Reader reader, Consumer<Map<String, Object>> consumer);
}
