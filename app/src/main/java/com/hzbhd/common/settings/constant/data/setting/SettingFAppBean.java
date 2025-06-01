package com.hzbhd.common.settings.constant.data.setting;

public class SettingFAppBean {
   public static enum Key {
      private static final Key[] $VALUES;
      aux_in,
      front_camera;

      static {
         Key var1 = new Key("front_camera", 0);
         front_camera = var1;
         Key var0 = new Key("aux_in", 1);
         aux_in = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
