package com.github.palindromicity.syslog;

public class SyslogParserBuilder {
  private SyslogSpecification specification = SyslogSpecification.RFC_5424;
  private NameGenerator nameGenerator = new DefaultNameGenerator();

  public SyslogParserBuilder forSpecification(final SyslogSpecification specification) {
    this.specification = specification;
    return this;
  }

  public SyslogParserBuilder withNameGenerator(final NameGenerator nameGenerator) {
    this.nameGenerator = nameGenerator;
    return this;
  }

  public SyslogParser build() {
    if (specification == SyslogSpecification.RFC_5424) {
      return new Rfc5424SyslogParser(nameGenerator);
    }
    return null;
  }
}
