package com.hzbhd.proxy.sourcemanager;

public class SourceUtils {
   public static int getDispIdFromDispType(int var0) {
      return var0 & 255;
   }

   public static int getDispListenerType(int var0, int var1) {
      return var0 + (var1 << 8);
   }

   public static int getDispSource(int var0, int var1) {
      return var0 + (var1 << 8);
   }

   public static int getTypeFromDispType(int var0) {
      return var0 >> 8;
   }
}
