package com.hzbhd.common.settings.constant.data.avm;

public class AvmBean {
   public static enum Key {
      private static final Key[] $VALUES;
      enable,
      reverse_off_delay;

      static {
         Key var1 = new Key("enable", 0);
         enable = var1;
         Key var0 = new Key("reverse_off_delay", 1);
         reverse_off_delay = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
