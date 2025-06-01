package com.hzbhd.common.settings.constant.data.mcu;

public class McuBrightnessBean {
   public static enum Key {
      private static final Key[] $VALUES;
      level,
      status;

      static {
         Key var1 = new Key("status", 0);
         status = var1;
         Key var0 = new Key("level", 1);
         level = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
