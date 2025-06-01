package com.hzbhd.common.settings.constant.data.disc;

public class DiscSettingsBean {
   public static enum Key {
      private static final Key[] $VALUES;
      mpeg_32_eject_time,
      mpeg_32_enter_time;

      static {
         Key var1 = new Key("mpeg_32_enter_time", 0);
         mpeg_32_enter_time = var1;
         Key var0 = new Key("mpeg_32_eject_time", 1);
         mpeg_32_eject_time = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
