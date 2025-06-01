package com.hzbhd.canbus.util;

public class TrackInfoUtil {
   private static final int TRACK_DATA_LENGTH = 16;
   public static final int TRACK_MAX_ANGLE = 25;

   public static int culTrackAngle(short var0, int var1) {
      float var2 = (float)var1 / 25.0F;
      return DataHandleUtils.rangeNumber((int)((float)var0 / var2), -25, 25);
   }

   private static int getOffset(int var0) {
      return 16 - var0;
   }

   public static int getTrackAngle0(byte var0, byte var1, int var2, int var3, int var4) {
      return culTrackAngle((short)((short)((short)((short)(var0 & 255 | var1 << 8 & '\uff00') - var2) << getOffset(var4)) >> getOffset(var4)), var3 - var2);
   }

   public static int getTrackAngle1(byte var0, byte var1, int var2, int var3, int var4) {
      short var5 = (short)(var0 & 255 | var1 << 8 & '\uff00');
      var1 = var4 - 1;
      var0 = (short)(1 << var1);
      short var6 = (short)((var5 & var0) << getOffset(var4));
      return culTrackAngle((short)((short)((short)(var5 & (short)(~var0)) ^ var6 >> 15) + (var0 >> var1)), var3 - var2);
   }
}
