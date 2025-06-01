package com.hzbhd.common.settings.constant.data.mcu;

public class McuPowerBean {
   public static enum Key {
      private static final Key[] $VALUES;
      acc_off_delay,
      acc_sleep_standy,
      fake_power;

      static {
         Key var1 = new Key("acc_off_delay", 0);
         acc_off_delay = var1;
         Key var2 = new Key("acc_sleep_standy", 1);
         acc_sleep_standy = var2;
         Key var0 = new Key("fake_power", 2);
         fake_power = var0;
         $VALUES = new Key[]{var1, var2, var0};
      }
   }
}
