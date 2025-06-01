package com.hzbhd.common.settings.constant.data.mcu;

public class McuColorLedBean {
   public static enum Key {
      private static final Key[] $VALUES;
      color,
      mode;

      static {
         Key var1 = new Key("mode", 0);
         mode = var1;
         Key var0 = new Key("color", 1);
         color = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
