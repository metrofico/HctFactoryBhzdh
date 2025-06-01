package com.hzbhd.common.settings.constant.data.mcu;

public class McuVcomBean {
   public static enum Key {
      private static final Key[] $VALUES;
      max_value,
      min_value,
      status,
      voltage;

      static {
         Key var0 = new Key("status", 0);
         status = var0;
         Key var3 = new Key("voltage", 1);
         voltage = var3;
         Key var2 = new Key("min_value", 2);
         min_value = var2;
         Key var1 = new Key("max_value", 3);
         max_value = var1;
         $VALUES = new Key[]{var0, var3, var2, var1};
      }
   }
}
