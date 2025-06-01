package com.hzbhd.common.settings.constant.data.setting;

public class SettingCameraBean {
   public static enum Key {
      private static final Key[] $VALUES;
      aux,
      back_camera,
      channel,
      front_camera;

      static {
         Key var2 = new Key("channel", 0);
         channel = var2;
         Key var3 = new Key("back_camera", 1);
         back_camera = var3;
         Key var1 = new Key("front_camera", 2);
         front_camera = var1;
         Key var0 = new Key("aux", 3);
         aux = var0;
         $VALUES = new Key[]{var2, var3, var1, var0};
      }
   }
}
