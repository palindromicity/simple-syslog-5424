/*
 * Copyright 2018 simple-syslog-5424 authors
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.palindromicity.syslog;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.github.palindromicity.syslog.dsl.DefaultErrorListener;
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
  private StructuredDataPolicy structuredDataPolicy = StructuredDataPolicy.FLATTEN;

  /**
   * Create a new {@code Rfc5424SyslogParser}.
   *
   * @param keyProvider {@link com.github.palindromicity.syslog.KeyProvider} to provide keys for the
   * {@link Syslog5424Listener}.
   */
  Rfc5424SyslogParser(KeyProvider keyProvider ) {
    this(keyProvider, null, null);
  }

  Rfc5424SyslogParser(KeyProvider keyProvider, NilPolicy nilPolicy, StructuredDataPolicy structuredDataPolicy) {
    Validate.notNull(keyProvider, "keyProvider");
    this.keyProvider = keyProvider;
    if (nilPolicy != null) {
      this.nilPolicy = nilPolicy;
    }
    if (structuredDataPolicy != null) {
      this.structuredDataPolicy = structuredDataPolicy;
    }
  }

  @Override
  public Map<String, Object> parseLine(String syslogLine) {
    Validate.notBlank(syslogLine, "syslogLine");
    Rfc5424Lexer lexer = new Rfc5424Lexer(new ANTLRInputStream(syslogLine));
    lexer.removeErrorListeners();
    lexer.addErrorListener(new DefaultErrorListener());
    Rfc5424Parser parser = new Rfc5424Parser(new CommonTokenStream(lexer));
    Syslog5424Listener listener = new Syslog5424Listener(keyProvider, nilPolicy, structuredDataPolicy);
    parser.addParseListener(listener);
    parser.removeErrorListeners();
    parser.addErrorListener(new DefaultErrorListener());
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
