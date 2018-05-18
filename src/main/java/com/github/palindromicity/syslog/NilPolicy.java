package com.github.palindromicity.syslog;

/**
 * Policies available for handling NIL '-' values.
 */
public enum NilPolicy {
  /**
   * a nil value will result msg part being omitted from the map.
   */
  OMIT,
  /**
   * a nil value will result in a null value in the map.
   */
  NULL,
  /**
   * a nil value will result in a '-' symbol in the map.
   */
  DASH
}
