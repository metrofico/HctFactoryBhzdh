package com.hzbhd.common.settings.constant.data.video;

public class VideoReverseBean {
   public static enum Key {
      private static final Key[] $VALUES;
      baseline,
      mute,
      radar_display,
      track;

      static {
         Key var2 = new Key("track", 0);
         track = var2;
         Key var1 = new Key("baseline", 1);
         baseline = var1;
         Key var0 = new Key("mute", 2);
         mute = var0;
         Key var3 = new Key("radar_display", 3);
         radar_display = var3;
         $VALUES = new Key[]{var2, var1, var0, var3};
      }
   }
}
