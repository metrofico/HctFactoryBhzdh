package com.android.common;

import android.util.EventLog;

public class GoogleLogTags {
   public static final int C2DM = 204005;
   public static final int EXP_DET_SNET = 206003;
   public static final int GLS_ACCOUNT_SAVED = 205009;
   public static final int GLS_ACCOUNT_TRIED = 205008;
   public static final int GLS_AUTHENTICATE = 205010;
   public static final int GOOGLE_HTTP_REQUEST = 203002;
   public static final int GOOGLE_MAIL_SWITCH = 205011;
   public static final int GTALKSERVICE = 204001;
   public static final int GTALK_CONNECTION = 204002;
   public static final int GTALK_CONN_CLOSE = 204003;
   public static final int GTALK_HEARTBEAT_RESET = 204004;
   public static final int METRICS_HEARTBEAT = 208000;
   public static final int SETUP_COMPLETED = 205007;
   public static final int SETUP_IO_ERROR = 205003;
   public static final int SETUP_NO_DATA_NETWORK = 205006;
   public static final int SETUP_REQUIRED_CAPTCHA = 205002;
   public static final int SETUP_RETRIES_EXHAUSTED = 205005;
   public static final int SETUP_SERVER_ERROR = 205004;
   public static final int SETUP_SERVER_TIMEOUT = 205001;
   public static final int SNET = 206001;
   public static final int SYNC_DETAILS = 203001;
   public static final int SYSTEM_UPDATE = 201001;
   public static final int SYSTEM_UPDATE_USER = 201002;
   public static final int TRANSACTION_EVENT = 202901;
   public static final int VENDING_RECONSTRUCT = 202001;

   private GoogleLogTags() {
   }

   public static void writeC2Dm(int var0, String var1, int var2, int var3) {
      EventLog.writeEvent(204005, new Object[]{var0, var1, var2, var3});
   }

   public static void writeExpDetSnet(String var0) {
      EventLog.writeEvent(206003, var0);
   }

   public static void writeGlsAccountSaved(int var0) {
      EventLog.writeEvent(205009, var0);
   }

   public static void writeGlsAccountTried(int var0) {
      EventLog.writeEvent(205008, var0);
   }

   public static void writeGlsAuthenticate(int var0, String var1) {
      EventLog.writeEvent(205010, new Object[]{var0, var1});
   }

   public static void writeGoogleHttpRequest(long var0, int var2, String var3, int var4) {
      EventLog.writeEvent(203002, new Object[]{var0, var2, var3, var4});
   }

   public static void writeGoogleMailSwitch(int var0) {
      EventLog.writeEvent(205011, var0);
   }

   public static void writeGtalkConnClose(int var0, int var1) {
      EventLog.writeEvent(204003, new Object[]{var0, var1});
   }

   public static void writeGtalkConnection(int var0) {
      EventLog.writeEvent(204002, var0);
   }

   public static void writeGtalkHeartbeatReset(int var0, String var1) {
      EventLog.writeEvent(204004, new Object[]{var0, var1});
   }

   public static void writeGtalkservice(int var0) {
      EventLog.writeEvent(204001, var0);
   }

   public static void writeMetricsHeartbeat() {
      EventLog.writeEvent(208000, new Object[0]);
   }

   public static void writeSetupCompleted() {
      EventLog.writeEvent(205007, new Object[0]);
   }

   public static void writeSetupIoError(String var0) {
      EventLog.writeEvent(205003, var0);
   }

   public static void writeSetupNoDataNetwork() {
      EventLog.writeEvent(205006, new Object[0]);
   }

   public static void writeSetupRequiredCaptcha(String var0) {
      EventLog.writeEvent(205002, var0);
   }

   public static void writeSetupRetriesExhausted() {
      EventLog.writeEvent(205005, new Object[0]);
   }

   public static void writeSetupServerError() {
      EventLog.writeEvent(205004, new Object[0]);
   }

   public static void writeSetupServerTimeout() {
      EventLog.writeEvent(205001, new Object[0]);
   }

   public static void writeSnet(String var0) {
      EventLog.writeEvent(206001, var0);
   }

   public static void writeSyncDetails(String var0, int var1, int var2, String var3) {
      EventLog.writeEvent(203001, new Object[]{var0, var1, var2, var3});
   }

   public static void writeSystemUpdate(int var0, int var1, long var2, String var4) {
      EventLog.writeEvent(201001, new Object[]{var0, var1, var2, var4});
   }

   public static void writeSystemUpdateUser(String var0) {
      EventLog.writeEvent(201002, var0);
   }

   public static void writeTransactionEvent(String var0) {
      EventLog.writeEvent(202901, var0);
   }

   public static void writeVendingReconstruct(int var0) {
      EventLog.writeEvent(202001, var0);
   }
}
