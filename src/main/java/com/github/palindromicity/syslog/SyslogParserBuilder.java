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

/**
 * Builder for SyslogParser instances.
 */
public class SyslogParserBuilder {

  /**
   * The {@link com.github.palindromicity.syslog.SyslogSpecification}.
   * Defaults to {@link com.github.palindromicity.syslog.SyslogSpecification#RFC_5424}
   */
  private SyslogSpecification specification = SyslogSpecification.RFC_5424;

  /**
   * The {@link KeyProvider}.
   * Defaults to {@link DefaultKeyProvider}
   */
  private KeyProvider keyProvider = new DefaultKeyProvider();

  /**
   * The {@link NilPolicy}.
   * Defaults to {@link NilPolicy#OMIT}
   */
  private NilPolicy nilPolicy = NilPolicy.OMIT;

  /**
   * Add a {@link SyslogSpecification} to the builder.
   * @param specification the specification
   * @return {@code SyslogParserBuilder}
   */
  public SyslogParserBuilder forSpecification(final SyslogSpecification specification) {
    this.specification = specification;
    return this;
  }

  /**
   * Add a {@link KeyProvider} to the builder.
   * @param keyProvider the {@link KeyProvider}
   * @return {@code SyslogParserBuilder}
   */
  public SyslogParserBuilder withKeyProvider(final KeyProvider keyProvider) {
    this.keyProvider = keyProvider;
    return this;
  }

  /**
   * Set the {@link NilPolicy} to the builder.
   * @param nilPolicy the {@link NilPolicy}
   * @return {@code SyslogParserBuilder}
   */
  public SyslogParserBuilder withNilPolicy(NilPolicy nilPolicy) {
    this.nilPolicy = nilPolicy;
    return this;
  }

  /**
   * Builds a new {@link SyslogParser} instance using options if provided.
   * @return {@link SyslogParser}
   * @throws IllegalStateException if specification is unknown
   */
  public SyslogParser build() {
    if (specification == SyslogSpecification.RFC_5424) {
      return new Rfc5424SyslogParser(keyProvider, nilPolicy);
    }
    throw new IllegalStateException("unknown SyslogSpecification " + specification.name());
  }
}
