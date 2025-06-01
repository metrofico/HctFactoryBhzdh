package com.hzbhd.proxy.mcu.utils;

public class MCUParseUtil {
   public static int byteArrayToInt(byte[] var0) {
      int var2 = 0;

      int var1;
      for(var1 = 0; var2 < 4; ++var2) {
         var1 += (var0[var2] & 255) << (3 - var2) * 8;
      }

      return var1;
   }

   public static short byteArrayToShort(byte[] var0) {
      int var2 = 0;

      short var1;
      for(var1 = 0; var2 < 2; ++var2) {
         var1 = (short)(var1 + ((var0[var2] & 255) << (1 - var2) * 8));
      }

      return var1;
   }

   public static int getIntFromByteWithBit(byte var0, int var1, int var2) {
      return var0 + 256 >> var1 & (1 << var2) - 1;
   }

   public static byte[] intToByteArray(int var0) {
      return new byte[]{(byte)(var0 >> 24 & 255), (byte)(var0 >> 16 & 255), (byte)(var0 >> 8 & 255), (byte)(var0 & 255)};
   }

   public static byte[] shortToByteArray(short var0) {
      return new byte[]{(byte)(var0 >> 8 & 255), (byte)(var0 & 255)};
   }
}
