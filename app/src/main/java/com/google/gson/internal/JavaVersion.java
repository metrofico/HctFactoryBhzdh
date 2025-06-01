package com.google.gson.internal;

public final class JavaVersion {
   private static final int majorJavaVersion = determineMajorJavaVersion();

   private JavaVersion() {
   }

   private static int determineMajorJavaVersion() {
      return getMajorJavaVersion(System.getProperty("java.version"));
   }

   private static int extractBeginningInt(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static int getMajorJavaVersion() {
      return majorJavaVersion;
   }

   static int getMajorJavaVersion(String var0) {
      int var2 = parseDotted(var0);
      int var1 = var2;
      if (var2 == -1) {
         var1 = extractBeginningInt(var0);
      }

      return var1 == -1 ? 6 : var1;
   }

   public static boolean isJava9OrLater() {
      boolean var0;
      if (majorJavaVersion >= 9) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   private static int parseDotted(String param0) {
      // $FF: Couldn't be decompiled
   }
}
