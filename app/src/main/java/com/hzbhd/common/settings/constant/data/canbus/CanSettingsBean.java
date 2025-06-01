package com.hzbhd.common.settings.constant.data.canbus;

public class CanSettingsBean {
   public static enum Key {
      private static final Key[] $VALUES;
      air_display_setup,
      can_eq,
      door_display_setup,
      swap_left_and_right_of_ac_temperature;

      static {
         Key var1 = new Key("door_display_setup", 0);
         door_display_setup = var1;
         Key var3 = new Key("swap_left_and_right_of_ac_temperature", 1);
         swap_left_and_right_of_ac_temperature = var3;
         Key var0 = new Key("air_display_setup", 2);
         air_display_setup = var0;
         Key var2 = new Key("can_eq", 3);
         can_eq = var2;
         $VALUES = new Key[]{var1, var3, var0, var2};
      }
   }
}
