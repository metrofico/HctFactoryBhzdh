package com.hzbhd.common.settings.constant.data.mcu;

public class McuGeneralBean {
   public static enum Key {
      private static final Key[] $VALUES;
      beep,
      clock_mode,
      factory_reset,
      gps_time_sync,
      lamp_control,
      microphone,
      reverse_mute,
      reverse_track,
      tv_area;

      static {
         Key var6 = new Key("clock_mode", 0);
         clock_mode = var6;
         Key var4 = new Key("beep", 1);
         beep = var4;
         Key var7 = new Key("reverse_mute", 2);
         reverse_mute = var7;
         Key var2 = new Key("microphone", 3);
         microphone = var2;
         Key var1 = new Key("tv_area", 4);
         tv_area = var1;
         Key var0 = new Key("gps_time_sync", 5);
         gps_time_sync = var0;
         Key var8 = new Key("factory_reset", 6);
         factory_reset = var8;
         Key var3 = new Key("lamp_control", 7);
         lamp_control = var3;
         Key var5 = new Key("reverse_track", 8);
         reverse_track = var5;
         $VALUES = new Key[]{var6, var4, var7, var2, var1, var0, var8, var3, var5};
      }
   }
}
