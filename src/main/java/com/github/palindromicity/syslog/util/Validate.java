/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.palindromicity.syslog.util;

/**
 * Utility class to validate arguments and the like.
 */
public class Validate {

  /**
   * Validate that an {@code Object} is not {@code Null}.
   *
   * @param object The object to validate
   * @param name the name to use in the thrown exception
   * @throws IllegalArgumentException if object is null
   */
  public static void notNull(Object object, String name) {
    if (object == null) {
      throw new IllegalArgumentException(String.format("%s cannot be null", name));
    }
  }

  /**
   * Validate that an {@code String} is not {@code Null} or empty.
   *
   * @param string The String to validate
   * @param name the name to use in the thrown exception
   * @throws IllegalArgumentException if string is null or empty
   */
  public static void notBlank(String string, String name) {
    if (string == null || string.isEmpty() || string.trim().isEmpty()) {
      throw new IllegalArgumentException(String.format("%s cannot be null", name));
    }
  }

}
