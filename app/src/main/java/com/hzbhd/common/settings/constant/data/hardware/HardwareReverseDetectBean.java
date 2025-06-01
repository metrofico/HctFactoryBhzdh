package com.hzbhd.common.settings.constant.data.hardware;

public class HardwareReverseDetectBean {
   public static enum Key {
      private static final Key[] $VALUES;
      enable,
      platform;

      static {
         Key var1 = new Key("platform", 0);
         platform = var1;
         Key var0 = new Key("enable", 1);
         enable = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
