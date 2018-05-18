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

package com.github.palindromicity.syslog.dsl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.palindromicity.syslog.KeyProvider;
import com.github.palindromicity.syslog.dsl.generated.Rfc5424BaseListener;
import com.github.palindromicity.syslog.dsl.generated.Rfc5424Listener;
import com.github.palindromicity.syslog.dsl.generated.Rfc5424Parser;

/**
 * Simple implementation of {@link Rfc5424Listener}.
 * <p>
 * {@code Syslog5424Listener} populates a {@code Map} with the values parsed from a valid RFC 5424 syslog line.
 * Nil -> '-' values are not inserted in the {@code Map} currently.
 * </p>
 * <p>
 * The {@code Syslog5424Listener} uses the provided {@link KeyProvider} when inserting items into the map.
 * </p>
 */
public class Syslog5424Listener extends Rfc5424BaseListener {

  /**
   * {@link KeyProvider} that provides our key names.
   */
  private KeyProvider keyProvider;

  /**
   * The {@code Map} used to store our syslog values.
   */
  private final Map<String, Object> msgMap = new HashMap<>();

  /**
   * Create a new {@code Syslog5424Listener}.
   *
   * @param keyProvider {@link KeyProvider} used for map insertion.
   */
  public Syslog5424Listener(KeyProvider keyProvider) {
    this.keyProvider = keyProvider;
  }

  /**
   * Returns the {@code Map} of syslog values with the keys as provided by the {@link KeyProvider}.
   * The map returned is unmodifiable.
   *
   * @return unmodifiable {@code Map}
   */
  public Map<String, Object> getMsgMap() {
    return Collections.unmodifiableMap(msgMap);
  }


  @Override
  public void exitHeaderPriorityValue(Rfc5424Parser.HeaderPriorityValueContext ctx) {
    msgMap.put(keyProvider.getHeaderPriority(), ctx.getText());
  }

  @Override
  public void exitHeaderVersion(Rfc5424Parser.HeaderVersionContext ctx) {
    msgMap.put(keyProvider.getHeaderVersion(), ctx.getText());
  }

  @Override
  public void exitHeaderHostName(Rfc5424Parser.HeaderHostNameContext ctx) {
    msgMap.put(keyProvider.getHeaderHostName(), ctx.getText());

  }

  @Override
  public void exitHeaderAppName(Rfc5424Parser.HeaderAppNameContext ctx) {
    msgMap.put(keyProvider.getHeaderAppName(), ctx.getText());

  }

  @Override
  public void exitHeaderProcId(Rfc5424Parser.HeaderProcIdContext ctx) {
    msgMap.put(keyProvider.getHeaderProcessId(), ctx.getText());

  }

  @Override
  public void exitHeaderMsgId(Rfc5424Parser.HeaderMsgIdContext ctx) {
    msgMap.put(keyProvider.getHeaderMessageId(), ctx.getText());
  }

  @Override
  public void exitHeaderTimeStamp(Rfc5424Parser.HeaderTimeStampContext ctx) {
    msgMap.put(keyProvider.getHeaderTimeStamp(), ctx.full_date().getText()
        + "T" + ctx.full_time().getText());
  }

  @Override
  public void exitSdElement(Rfc5424Parser.SdElementContext ctx) {
    String id = ctx.sd_id().getText();
    for (Rfc5424Parser.Sd_paramContext paramContext : ctx.sd_param()) {
      msgMap.put(String.format(keyProvider.getStructuredElementIdParamNameFormat(), (id),
          ((Rfc5424Parser.SdParamContext) paramContext).param_name()
              .getText()),
          ((Rfc5424Parser.SdParamContext) paramContext).param_value().getText());
    }

  }

  @Override
  public void exitMsg_any(Rfc5424Parser.Msg_anyContext ctx) {
    msgMap.put(keyProvider.getMessage(), ctx.getText().trim());
  }

  @Override
  public void exitMsg_utf8(Rfc5424Parser.Msg_utf8Context ctx) {
    msgMap.put(keyProvider.getMessage(), ctx.getText().trim());
  }
}
