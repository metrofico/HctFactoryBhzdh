package com.hzbhd.common.settings.constant.data.system;

public class SystemStatusBean {
   public static enum Key {
      private static final Key[] $VALUES;
      driving_video,
      rotate_screen;

      static {
         Key var1 = new Key("driving_video", 0);
         driving_video = var1;
         Key var0 = new Key("rotate_screen", 1);
         rotate_screen = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
