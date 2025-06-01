package com.android.common;

import android.content.SharedPreferences;
import android.text.format.Time;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class OperationScheduler {
   private static final String PREFIX = "OperationScheduler_";
   private final SharedPreferences mStorage;

   public OperationScheduler(SharedPreferences var1) {
      this.mStorage = var1;
   }

   private long getTimeBefore(String var1, long var2) {
      long var6 = this.mStorage.getLong(var1, 0L);
      long var4 = var6;
      if (var6 > var2) {
         SharedPreferencesCompat.apply(this.mStorage.edit().putLong(var1, var2));
         var4 = var2;
      }

      return var4;
   }

   public static Options parseOptions(String var0, Options var1) throws IllegalArgumentException {
      String[] var4 = var0.split(" +");
      int var3 = var4.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String var5 = var4[var2];
         if (var5.length() != 0) {
            if (var5.startsWith("backoff=")) {
               String[] var7 = var5.substring(8).split("\\+");
               if (var7.length > 3) {
                  StringBuilder var6 = new StringBuilder();
                  var6.append("bad value for backoff: [");
                  var6.append(var0);
                  var6.append("]");
                  throw new IllegalArgumentException(var6.toString());
               }

               if (var7.length > 0 && var7[0].length() > 0) {
                  var1.backoffFixedMillis = parseSeconds(var7[0]);
               }

               if (var7.length > 1 && var7[1].length() > 0) {
                  var1.backoffIncrementalMillis = parseSeconds(var7[1]);
               }

               if (var7.length > 2 && var7[2].length() > 0) {
                  var1.backoffExponentialMillis = (int)parseSeconds(var7[2]);
               }
            } else if (var5.startsWith("max=")) {
               var1.maxMoratoriumMillis = parseSeconds(var5.substring(4));
            } else if (var5.startsWith("min=")) {
               var1.minTriggerMillis = parseSeconds(var5.substring(4));
            } else if (var5.startsWith("period=")) {
               var1.periodicIntervalMillis = parseSeconds(var5.substring(7));
            } else {
               var1.periodicIntervalMillis = parseSeconds(var5);
            }
         }
      }

      return var1;
   }

   private static long parseSeconds(String var0) throws NumberFormatException {
      return (long)(Float.parseFloat(var0) * 1000.0F);
   }

   protected long currentTimeMillis() {
      return System.currentTimeMillis();
   }

   public long getLastAttemptTimeMillis() {
      return Math.max(this.mStorage.getLong("OperationScheduler_lastSuccessTimeMillis", 0L), this.mStorage.getLong("OperationScheduler_lastErrorTimeMillis", 0L));
   }

   public long getLastSuccessTimeMillis() {
      return this.mStorage.getLong("OperationScheduler_lastSuccessTimeMillis", 0L);
   }

   public long getNextTimeMillis(Options var1) {
      if (!this.mStorage.getBoolean("OperationScheduler_enabledState", true)) {
         return Long.MAX_VALUE;
      } else if (this.mStorage.getBoolean("OperationScheduler_permanentError", false)) {
         return Long.MAX_VALUE;
      } else {
         int var4 = this.mStorage.getInt("OperationScheduler_errorCount", 0);
         long var11 = this.currentTimeMillis();
         long var9 = this.getTimeBefore("OperationScheduler_lastSuccessTimeMillis", var11);
         long var7 = this.getTimeBefore("OperationScheduler_lastErrorTimeMillis", var11);
         long var5 = this.mStorage.getLong("OperationScheduler_triggerTimeMillis", Long.MAX_VALUE);
         var11 = this.getTimeBefore("OperationScheduler_moratoriumSetTimeMillis", var11);
         var11 = this.getTimeBefore("OperationScheduler_moratoriumTimeMillis", var1.maxMoratoriumMillis + var11);
         if (var1.periodicIntervalMillis > 0L) {
            var5 = Math.min(var5, var1.periodicIntervalMillis + var9);
         }

         var5 = Math.max(Math.max(var5, var11), var1.minTriggerMillis + var9);
         if (var4 > 0) {
            int var3 = var4 - 1;
            int var2 = var3;
            if (var3 > 30) {
               var2 = 30;
            }

            var5 = Math.max(var5, var7 + Math.min(var1.backoffFixedMillis + var1.backoffIncrementalMillis * (long)var4 + ((long)var1.backoffExponentialMillis << var2), var1.maxMoratoriumMillis));
         }

         return var5;
      }
   }

   public void onPermanentError() {
      SharedPreferencesCompat.apply(this.mStorage.edit().putBoolean("OperationScheduler_permanentError", true));
   }

   public void onSuccess() {
      this.resetTransientError();
      this.resetPermanentError();
      SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_errorCount").remove("OperationScheduler_lastErrorTimeMillis").remove("OperationScheduler_permanentError").remove("OperationScheduler_triggerTimeMillis").putLong("OperationScheduler_lastSuccessTimeMillis", this.currentTimeMillis()));
   }

   public void onTransientError() {
      SharedPreferences.Editor var1 = this.mStorage.edit();
      var1.putLong("OperationScheduler_lastErrorTimeMillis", this.currentTimeMillis());
      var1.putInt("OperationScheduler_errorCount", this.mStorage.getInt("OperationScheduler_errorCount", 0) + 1);
      SharedPreferencesCompat.apply(var1);
   }

   public void resetPermanentError() {
      SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_permanentError"));
   }

   public void resetTransientError() {
      SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_errorCount"));
   }

   public void setEnabledState(boolean var1) {
      SharedPreferencesCompat.apply(this.mStorage.edit().putBoolean("OperationScheduler_enabledState", var1));
   }

   public boolean setMoratoriumTimeHttp(String var1) {
      try {
         long var2 = Long.parseLong(var1);
         this.setMoratoriumTimeMillis(this.currentTimeMillis() + var2 * 1000L);
         return true;
      } catch (NumberFormatException var6) {
         try {
            this.setMoratoriumTimeMillis(LegacyHttpDateTime.parse(var1));
            return true;
         } catch (IllegalArgumentException var5) {
            return false;
         }
      }
   }

   public void setMoratoriumTimeMillis(long var1) {
      SharedPreferencesCompat.apply(this.mStorage.edit().putLong("OperationScheduler_moratoriumTimeMillis", var1).putLong("OperationScheduler_moratoriumSetTimeMillis", this.currentTimeMillis()));
   }

   public void setTriggerTimeMillis(long var1) {
      SharedPreferencesCompat.apply(this.mStorage.edit().putLong("OperationScheduler_triggerTimeMillis", var1));
   }

   public String toString() {
      StringBuilder var2 = new StringBuilder("[OperationScheduler:");
      Iterator var1 = (new TreeMap(this.mStorage.getAll())).entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry var5 = (Map.Entry)var1.next();
         String var3 = (String)var5.getKey();
         if (var3.startsWith("OperationScheduler_")) {
            if (var3.endsWith("TimeMillis")) {
               Time var4 = new Time();
               var4.set((Long)var5.getValue());
               var2.append(" ");
               var2.append(var3.substring("OperationScheduler_".length(), var3.length() - 10));
               var2.append("=");
               var2.append(var4.format("%Y-%m-%d/%H:%M:%S"));
            } else {
               var2.append(" ");
               var2.append(var3.substring("OperationScheduler_".length()));
               Object var6 = var5.getValue();
               if (var6 == null) {
                  var2.append("=(null)");
               } else {
                  var2.append("=");
                  var2.append(var6.toString());
               }
            }
         }
      }

      var2.append("]");
      return var2.toString();
   }

   public static class Options {
      public int backoffExponentialMillis = 0;
      public long backoffFixedMillis = 0L;
      public long backoffIncrementalMillis = 5000L;
      public long maxMoratoriumMillis = 86400000L;
      public long minTriggerMillis = 0L;
      public long periodicIntervalMillis = 0L;

      public String toString() {
         return this.backoffExponentialMillis > 0 ? String.format("OperationScheduler.Options[backoff=%.1f+%.1f+%.1f max=%.1f min=%.1f period=%.1f]", (double)this.backoffFixedMillis / 1000.0, (double)this.backoffIncrementalMillis / 1000.0, (double)this.backoffExponentialMillis / 1000.0, (double)this.maxMoratoriumMillis / 1000.0, (double)this.minTriggerMillis / 1000.0, (double)this.periodicIntervalMillis / 1000.0) : String.format("OperationScheduler.Options[backoff=%.1f+%.1f max=%.1f min=%.1f period=%.1f]", (double)this.backoffFixedMillis / 1000.0, (double)this.backoffIncrementalMillis / 1000.0, (double)this.maxMoratoriumMillis / 1000.0, (double)this.minTriggerMillis / 1000.0, (double)this.periodicIntervalMillis / 1000.0);
      }
   }
}
