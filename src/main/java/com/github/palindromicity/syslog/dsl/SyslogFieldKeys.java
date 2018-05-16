package com.github.palindromicity.syslog.dsl;

public enum SyslogFieldKeys {
  MESSAGE("syslog.message"),
  HEADER_APPNAME("syslog.header.appName"),
  HEADER_HOSTNAME("syslog.header.hostName"),
  HEADER_PRI("syslog.header.pri"),
  HEADER_PROCID("syslog.header.procId"),
  HEADER_TIMESTAMP("syslog.header.timestamp"),
  HEADER_MSGID("syslog.header.msgId"),
  HEADER_VERSION("syslog.header.version"),
  STRUCTURED_BASE("syslog.structuredData."),
  STRUCTURED_ELEMENT_ID_FMT("syslog.structuredData.%s"),
  STRUCTURED_ELEMENT_ID_PNAME_FMT("syslog.structuredData.%s.%s"),
  STRUCTURED_ELEMENT_ID_PNAME_PATTERN("syslog.structuredData\\.(.*)\\.(.*)$");

  private String field;
  private SyslogFieldKeys(String field) {
    this.field = field;
  }

  public String getField() {
    return field;
  }
}
