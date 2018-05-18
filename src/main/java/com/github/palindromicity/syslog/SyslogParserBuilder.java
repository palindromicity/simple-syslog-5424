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
   * Builds a new {@link SyslogParser} instance using options if provided.
   * @return {@link SyslogParser}
   * @throws IllegalStateException if specification is unknown
   */
  public SyslogParser build() {
    if (specification == SyslogSpecification.RFC_5424) {
      return new Rfc5424SyslogParser(keyProvider);
    }
    throw new IllegalStateException("unknown SyslogSpecification " + specification.name());
  }
}
