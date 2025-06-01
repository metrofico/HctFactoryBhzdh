package com.hzbhd.common.settings.constant.data.bt;

public class BtSettingsBean {
   public static enum Key {
      private static final Key[] $VALUES;
      localDeviceName,
      microphoneExternal,
      pair_mode;

      static {
         Key var0 = new Key("localDeviceName", 0);
         localDeviceName = var0;
         Key var1 = new Key("pair_mode", 1);
         pair_mode = var1;
         Key var2 = new Key("microphoneExternal", 2);
         microphoneExternal = var2;
         $VALUES = new Key[]{var0, var1, var2};
      }
   }
}
