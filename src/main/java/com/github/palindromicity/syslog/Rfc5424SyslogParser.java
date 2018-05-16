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
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

class Rfc5424SyslogParser implements SyslogParser {
  private NameGenerator nameGenerator;

  Rfc5424SyslogParser(NameGenerator nameGenerator) {
    this.nameGenerator = nameGenerator;
  }

  @Override
  public Map<String, Object> parseLine(String line) {
    Rfc5424Lexer lexer = new Rfc5424Lexer(new ANTLRInputStream(line));
    Rfc5424Parser parser = new Rfc5424Parser(new CommonTokenStream(lexer));
    Syslog5424Listener listener = new Syslog5424Listener(nameGenerator);
    parser.addParseListener(listener);
    Rfc5424Parser.Syslog_msgContext ctx = parser.syslog_msg();
    return listener.getMsgMap();
  }

  @Override
  public void parseLine(String line, Consumer<Map<String, Object>> consumer) {
    consumer.accept(parseLine(line));
  }

  @Override
  public List<Map<String, Object>> parseLines(Reader reader) {
    return new BufferedReader(reader).lines()
        .map(this::parseLine)
        .collect(Collectors.toList());
  }

  @Override
  public void parseLines(Reader reader, Consumer<Map<String, Object>> consumer) {
    new BufferedReader(reader).lines()
        .map(this::parseLine)
        .forEach(consumer);
  }
}
