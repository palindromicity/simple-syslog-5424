package com.github.palindromicity.syslog;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.github.palindromicity.syslog.dsl.Syslog5424Listener;
import com.github.palindromicity.syslog.dsl.generated.Rfc5424Lexer;
import com.github.palindromicity.syslog.dsl.generated.Rfc5424Parser;
import com.github.palindromicity.syslog.util.Validate;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * {@link SyslogParser} for valid RFC 5424 syslog.
 */
class Rfc5424SyslogParser implements SyslogParser {

  /**
   * {@link com.github.palindromicity.syslog.KeyProvider} to provide keys for the
   * {@link Syslog5424Listener}.
   */
  private KeyProvider keyProvider;

  /**
   * {@link NilPolicy} for the {@link Syslog5424Listener}.
   */
  private NilPolicy nilPolicy = NilPolicy.OMIT;

  /**
   * Create a new {@code Rfc5424SyslogParser}.
   *
   * @param keyProvider {@link com.github.palindromicity.syslog.KeyProvider} to provide keys for the
   * {@link Syslog5424Listener}.
   */
  Rfc5424SyslogParser(KeyProvider keyProvider ) {
    this(keyProvider, null);
  }

  Rfc5424SyslogParser(KeyProvider keyProvider, NilPolicy nilPolicy) {
    Validate.notNull(keyProvider, "keyProvider");
    this.keyProvider = keyProvider;
    if (nilPolicy != null) {
      this.nilPolicy = nilPolicy;
    }
  }

  @Override
  public Map<String, Object> parseLine(String syslogLine) {
    Validate.notBlank(syslogLine, "syslogLine");
    Rfc5424Lexer lexer = new Rfc5424Lexer(new ANTLRInputStream(syslogLine));
    Rfc5424Parser parser = new Rfc5424Parser(new CommonTokenStream(lexer));
    Syslog5424Listener listener = new Syslog5424Listener(keyProvider, nilPolicy);
    parser.addParseListener(listener);
    Rfc5424Parser.Syslog_msgContext ctx = parser.syslog_msg();
    return listener.getMsgMap();
  }

  @Override
  public void parseLine(String line, Consumer<Map<String, Object>> consumer) {
    Validate.notNull(consumer, "consumer");
    consumer.accept(parseLine(line));
  }

  @Override
  public List<Map<String, Object>> parseLines(Reader reader) {
    Validate.notNull(reader, "reader");
    return new BufferedReader(reader).lines()
        .map(this::parseLine)
        .collect(Collectors.toList());
  }

  @Override
  public void parseLines(Reader reader, Consumer<Map<String, Object>> consumer) {
    Validate.notNull(reader, "reader");
    new BufferedReader(reader).lines()
        .map(this::parseLine)
        .forEach(consumer);
  }
}
