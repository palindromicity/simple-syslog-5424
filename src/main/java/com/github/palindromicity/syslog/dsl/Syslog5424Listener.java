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
import com.github.palindromicity.syslog.dsl.generated.Rfc5424Listener;
import com.github.palindromicity.syslog.dsl.generated.Rfc5424Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Simple implemenation of {@link Rfc5424Listener}.
 * <p>
 * {@code Syslog5424Listener} populates a {@code Map} with the values parsed from a valid RFC 5424 syslog line.
 * Nil -> '-' values are not inserted in the {@code Map} currently.
 * </p>
 * <p>
 * The {@code Syslog5424Listener} uses the provided {@link KeyProvider} when inserting items into the map.
 * </p>
 */
public class Syslog5424Listener implements Rfc5424Listener {

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
  public void enterSyslogMsg(Rfc5424Parser.SyslogMsgContext ctx) {

  }

  @Override
  public void exitSyslogMsg(Rfc5424Parser.SyslogMsgContext ctx) {

  }

  @Override
  public void enterSyslogHeader(Rfc5424Parser.SyslogHeaderContext ctx) {

  }

  @Override
  public void exitSyslogHeader(Rfc5424Parser.SyslogHeaderContext ctx) {

  }

  @Override
  public void enterHeaderPriority(Rfc5424Parser.HeaderPriorityContext ctx) {

  }

  @Override
  public void exitHeaderPriority(Rfc5424Parser.HeaderPriorityContext ctx) {
  }

  @Override
  public void enterHeaderPriorityValue(Rfc5424Parser.HeaderPriorityValueContext ctx) {

  }

  @Override
  public void exitHeaderPriorityValue(Rfc5424Parser.HeaderPriorityValueContext ctx) {
    msgMap.put(keyProvider.getHeaderPriority(), ctx.getText());
  }

  @Override
  public void enterHeaderVersion(Rfc5424Parser.HeaderVersionContext ctx) {

  }

  @Override
  public void exitHeaderVersion(Rfc5424Parser.HeaderVersionContext ctx) {
    msgMap.put(keyProvider.getHeaderVersion(), ctx.getText());
  }

  @Override
  public void enterHeaderNilHostName(Rfc5424Parser.HeaderNilHostNameContext ctx) {

  }

  @Override
  public void exitHeaderNilHostName(Rfc5424Parser.HeaderNilHostNameContext ctx) {

  }

  @Override
  public void enterHeaderHostName(Rfc5424Parser.HeaderHostNameContext ctx) {

  }

  @Override
  public void exitHeaderHostName(Rfc5424Parser.HeaderHostNameContext ctx) {
    msgMap.put(keyProvider.getHeaderHostName(), ctx.getText());

  }

  @Override
  public void enterHeaderNilAppName(Rfc5424Parser.HeaderNilAppNameContext ctx) {

  }

  @Override
  public void exitHeaderNilAppName(Rfc5424Parser.HeaderNilAppNameContext ctx) {

  }

  @Override
  public void enterHeaderAppName(Rfc5424Parser.HeaderAppNameContext ctx) {

  }

  @Override
  public void exitHeaderAppName(Rfc5424Parser.HeaderAppNameContext ctx) {
    msgMap.put(keyProvider.getHeaderAppName(), ctx.getText());

  }

  @Override
  public void enterHeaderNilProcId(Rfc5424Parser.HeaderNilProcIdContext ctx) {

  }

  @Override
  public void exitHeaderNilProcId(Rfc5424Parser.HeaderNilProcIdContext ctx) {

  }

  @Override
  public void enterHeaderProcId(Rfc5424Parser.HeaderProcIdContext ctx) {

  }

  @Override
  public void exitHeaderProcId(Rfc5424Parser.HeaderProcIdContext ctx) {
    msgMap.put(keyProvider.getHeaderProcessId(), ctx.getText());

  }

  @Override
  public void enterHeaderNilMsgId(Rfc5424Parser.HeaderNilMsgIdContext ctx) {

  }

  @Override
  public void exitHeaderNilMsgId(Rfc5424Parser.HeaderNilMsgIdContext ctx) {

  }

  @Override
  public void enterHeaderMsgId(Rfc5424Parser.HeaderMsgIdContext ctx) {
  }

  @Override
  public void exitHeaderMsgId(Rfc5424Parser.HeaderMsgIdContext ctx) {
    msgMap.put(keyProvider.getHeaderMessageId(), ctx.getText());
  }

  @Override
  public void enterHeaderNilTimestamp(Rfc5424Parser.HeaderNilTimestampContext ctx) {

  }

  @Override
  public void exitHeaderNilTimestamp(Rfc5424Parser.HeaderNilTimestampContext ctx) {

  }

  @Override
  public void enterHeaderTimeStamp(Rfc5424Parser.HeaderTimeStampContext ctx) {

  }

  @Override
  public void exitHeaderTimeStamp(Rfc5424Parser.HeaderTimeStampContext ctx) {
    msgMap.put(keyProvider.getHeaderTimeStamp(), ctx.full_date().getText()
        + "T" + ctx.full_time().getText());
  }

  @Override
  public void enterFull_date(Rfc5424Parser.Full_dateContext ctx) {

  }

  @Override
  public void exitFull_date(Rfc5424Parser.Full_dateContext ctx) {

  }

  @Override
  public void enterDate_fullyear(Rfc5424Parser.Date_fullyearContext ctx) {

  }

  @Override
  public void exitDate_fullyear(Rfc5424Parser.Date_fullyearContext ctx) {

  }

  @Override
  public void enterDate_month(Rfc5424Parser.Date_monthContext ctx) {

  }

  @Override
  public void exitDate_month(Rfc5424Parser.Date_monthContext ctx) {

  }

  @Override
  public void enterDate_mday(Rfc5424Parser.Date_mdayContext ctx) {

  }

  @Override
  public void exitDate_mday(Rfc5424Parser.Date_mdayContext ctx) {

  }

  @Override
  public void enterFull_time(Rfc5424Parser.Full_timeContext ctx) {

  }

  @Override
  public void exitFull_time(Rfc5424Parser.Full_timeContext ctx) {

  }

  @Override
  public void enterPartial_time(Rfc5424Parser.Partial_timeContext ctx) {

  }

  @Override
  public void exitPartial_time(Rfc5424Parser.Partial_timeContext ctx) {

  }

  @Override
  public void enterTime_hour(Rfc5424Parser.Time_hourContext ctx) {

  }

  @Override
  public void exitTime_hour(Rfc5424Parser.Time_hourContext ctx) {

  }

  @Override
  public void enterTime_minute(Rfc5424Parser.Time_minuteContext ctx) {

  }

  @Override
  public void exitTime_minute(Rfc5424Parser.Time_minuteContext ctx) {

  }

  @Override
  public void enterTime_second(Rfc5424Parser.Time_secondContext ctx) {

  }

  @Override
  public void exitTime_second(Rfc5424Parser.Time_secondContext ctx) {

  }

  @Override
  public void enterTime_secfrac(Rfc5424Parser.Time_secfracContext ctx) {

  }

  @Override
  public void exitTime_secfrac(Rfc5424Parser.Time_secfracContext ctx) {

  }

  @Override
  public void enterTime_offset(Rfc5424Parser.Time_offsetContext ctx) {

  }

  @Override
  public void exitTime_offset(Rfc5424Parser.Time_offsetContext ctx) {

  }

  @Override
  public void enterTime_numoffset(Rfc5424Parser.Time_numoffsetContext ctx) {

  }

  @Override
  public void exitTime_numoffset(Rfc5424Parser.Time_numoffsetContext ctx) {

  }

  @Override
  public void enterStructured_data(Rfc5424Parser.Structured_dataContext ctx) {

  }

  @Override
  public void exitStructured_data(Rfc5424Parser.Structured_dataContext ctx) {

  }

  @Override
  public void enterSdElement(Rfc5424Parser.SdElementContext ctx) {

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
  public void enterSdParam(Rfc5424Parser.SdParamContext ctx) {

  }

  @Override
  public void exitSdParam(Rfc5424Parser.SdParamContext ctx) {

  }

  @Override
  public void enterSd_id(Rfc5424Parser.Sd_idContext ctx) {

  }

  @Override
  public void exitSd_id(Rfc5424Parser.Sd_idContext ctx) {

  }

  @Override
  public void enterParamName(Rfc5424Parser.ParamNameContext ctx) {

  }

  @Override
  public void exitParamName(Rfc5424Parser.ParamNameContext ctx) {

  }

  @Override
  public void enterParamValue(Rfc5424Parser.ParamValueContext ctx) {

  }

  @Override
  public void exitParamValue(Rfc5424Parser.ParamValueContext ctx) {

  }

  @Override
  public void enterSd_name(Rfc5424Parser.Sd_nameContext ctx) {

  }

  @Override
  public void exitSd_name(Rfc5424Parser.Sd_nameContext ctx) {

  }

  @Override
  public void enterMsgAny(Rfc5424Parser.MsgAnyContext ctx) {

  }

  @Override
  public void exitMsgAny(Rfc5424Parser.MsgAnyContext ctx) {

  }

  @Override
  public void enterMsgUTF8(Rfc5424Parser.MsgUTF8Context ctx) {

  }

  @Override
  public void exitMsgUTF8(Rfc5424Parser.MsgUTF8Context ctx) {

  }

  @Override
  public void enterMsg_any(Rfc5424Parser.Msg_anyContext ctx) {

  }

  @Override
  public void exitMsg_any(Rfc5424Parser.Msg_anyContext ctx) {
    msgMap.put(keyProvider.getMessage(), ctx.getText().trim());
  }

  @Override
  public void enterMsg_utf8(Rfc5424Parser.Msg_utf8Context ctx) {

  }

  @Override
  public void exitMsg_utf8(Rfc5424Parser.Msg_utf8Context ctx) {
    msgMap.put(keyProvider.getMessage(), ctx.getText().trim());
  }

  @Override
  public void enterBom(Rfc5424Parser.BomContext ctx) {

  }

  @Override
  public void exitBom(Rfc5424Parser.BomContext ctx) {

  }

  @Override
  public void enterUtf_8_string(Rfc5424Parser.Utf_8_stringContext ctx) {

  }

  @Override
  public void exitUtf_8_string(Rfc5424Parser.Utf_8_stringContext ctx) {

  }

  @Override
  public void enterOctet(Rfc5424Parser.OctetContext ctx) {

  }

  @Override
  public void exitOctet(Rfc5424Parser.OctetContext ctx) {

  }

  @Override
  public void enterSp(Rfc5424Parser.SpContext ctx) {

  }

  @Override
  public void exitSp(Rfc5424Parser.SpContext ctx) {

  }

  @Override
  public void enterPrintusascii(Rfc5424Parser.PrintusasciiContext ctx) {

  }

  @Override
  public void exitPrintusascii(Rfc5424Parser.PrintusasciiContext ctx) {

  }

  @Override
  public void enterPrintusasciinospecials(Rfc5424Parser.PrintusasciinospecialsContext ctx) {

  }

  @Override
  public void exitPrintusasciinospecials(Rfc5424Parser.PrintusasciinospecialsContext ctx) {

  }

  @Override
  public void enterNonzero_digit(Rfc5424Parser.Nonzero_digitContext ctx) {

  }

  @Override
  public void exitNonzero_digit(Rfc5424Parser.Nonzero_digitContext ctx) {

  }

  @Override
  public void enterZeroDigit(Rfc5424Parser.ZeroDigitContext ctx) {

  }

  @Override
  public void exitZeroDigit(Rfc5424Parser.ZeroDigitContext ctx) {

  }

  @Override
  public void enterNonZeroDigit(Rfc5424Parser.NonZeroDigitContext ctx) {

  }

  @Override
  public void exitNonZeroDigit(Rfc5424Parser.NonZeroDigitContext ctx) {

  }

  @Override
  public void enterNilvalue(Rfc5424Parser.NilvalueContext ctx) {

  }

  @Override
  public void exitNilvalue(Rfc5424Parser.NilvalueContext ctx) {

  }

  @Override
  public void visitTerminal(TerminalNode terminalNode) {

  }

  @Override
  public void visitErrorNode(ErrorNode errorNode) {

  }

  @Override
  public void enterEveryRule(ParserRuleContext parserRuleContext) {

  }

  @Override
  public void exitEveryRule(ParserRuleContext parserRuleContext) {

  }
}
