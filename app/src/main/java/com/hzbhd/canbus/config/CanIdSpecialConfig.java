package com.hzbhd.canbus.config;

import com.android.internal.util.ArrayUtils;

public class CanIdSpecialConfig {
   private static int[] canType = new int[]{446};
   private static int[] have_SpeechModule_ID = new int[]{283, 163, 439, 455, 467};
   private static int[] supplement0x00InCanDataEndList = new int[]{466};
   private static int[] uiDifferentId = new int[]{283, 291, 206, 436};

   public static boolean haveSpeechModule(int var0) {
      return ArrayUtils.contains(have_SpeechModule_ID, var0);
   }

   public static boolean hideRadarLayoutCanID(int var0) {
      return ArrayUtils.contains(canType, var0);
   }

   public static boolean isCanNotSupplement0x00InCanDataEnd(int var0) {
      return ArrayUtils.contains(supplement0x00InCanDataEndList, var0);
   }

   public static boolean isNewVoiceCanID(int var0) {
      return ArrayUtils.contains(uiDifferentId, var0);
   }
}
