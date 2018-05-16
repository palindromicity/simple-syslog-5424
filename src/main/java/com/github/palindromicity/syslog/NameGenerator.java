package com.github.palindromicity.syslog;

import java.util.regex.Pattern;

public interface NameGenerator {
  String getMessage();

  String getHeaderAppName();

  String getHeaderHostName();

  String getHeaderPriority();

  String getHeaderProcessId();

  String getHeaderTimeStamp();

  String getHeaderMessageId();

  String getHeaderVersion();

  String getStructuredBase();

  String getStructuredElementIdFormat();

  String getStructuredElementIdParamNameFormat();

  Pattern getStructuredElementIdParamNamePattern();
}
