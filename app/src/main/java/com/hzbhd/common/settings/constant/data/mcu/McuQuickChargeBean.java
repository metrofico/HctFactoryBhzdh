package com.hzbhd.common.settings.constant.data.mcu;

public class McuQuickChargeBean {
   public static enum Key {
      private static final Key[] $VALUES;
      is_show,
      is_support,
      switch_on;

      static {
         Key var1 = new Key("switch_on", 0);
         switch_on = var1;
         Key var0 = new Key("is_support", 1);
         is_support = var0;
         Key var2 = new Key("is_show", 2);
         is_show = var2;
         $VALUES = new Key[]{var1, var0, var2};
      }
   }
}
