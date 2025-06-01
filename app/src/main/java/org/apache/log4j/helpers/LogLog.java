package org.apache.log4j.helpers;

public class LogLog {
   public static final String CONFIG_DEBUG_KEY = "log4j.configDebug";
   public static final String DEBUG_KEY = "log4j.debug";
   private static final String ERR_PREFIX = "log4j:ERROR ";
   private static final String PREFIX = "log4j: ";
   private static final String WARN_PREFIX = "log4j:WARN ";
   protected static boolean debugEnabled;
   private static boolean quietMode;

   static {
      String var1 = OptionConverter.getSystemProperty("log4j.debug", (String)null);
      String var0 = var1;
      if (var1 == null) {
         var0 = OptionConverter.getSystemProperty("log4j.configDebug", (String)null);
      }

      if (var0 != null) {
         debugEnabled = OptionConverter.toBoolean(var0, true);
      }

   }

   public static void debug(String var0) {
      if (debugEnabled && !quietMode) {
         System.out.println("log4j: " + var0);
      }

   }

   public static void debug(String var0, Throwable var1) {
      if (debugEnabled && !quietMode) {
         System.out.println("log4j: " + var0);
         if (var1 != null) {
            var1.printStackTrace(System.out);
         }
      }

   }

   public static void error(String var0) {
      if (!quietMode) {
         System.err.println("log4j:ERROR " + var0);
      }
   }

   public static void error(String var0, Throwable var1) {
      if (!quietMode) {
         System.err.println("log4j:ERROR " + var0);
         if (var1 != null) {
            var1.printStackTrace();
         }

      }
   }

   public static void setInternalDebugging(boolean var0) {
      debugEnabled = var0;
   }

   public static void setQuietMode(boolean var0) {
      quietMode = var0;
   }

   public static void warn(String var0) {
      if (!quietMode) {
         System.err.println("log4j:WARN " + var0);
      }
   }

   public static void warn(String var0, Throwable var1) {
      if (!quietMode) {
         System.err.println("log4j:WARN " + var0);
         if (var1 != null) {
            var1.printStackTrace();
         }

      }
   }
}
