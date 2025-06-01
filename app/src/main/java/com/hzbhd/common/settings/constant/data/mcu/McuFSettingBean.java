package com.hzbhd.common.settings.constant.data.mcu;

public class McuFSettingBean {
   public static enum Key {
      private static final Key[] $VALUES;
      amp_close,
      android_sleep_way,
      ant_close,
      can_serial_port_level,
      car_wire_controlled_capacitor,
      color_led_status,
      dtv_module,
      encoding_potentiometer_resistance_value,
      front_camera_power_close,
      ir_out_way,
      memory_screen_status,
      panel_type,
      real_shutdown,
      reverse_close,
      rotate_screen_run_time,
      rotate_screen_stop_delay,
      sleep_mode,
      support_disc,
      support_dtv,
      support_fan,
      support_front_camera,
      support_quick_charge,
      swap_swc_left_right,
      swap_swc_up_down,
      vehicle_amp;

      static {
         Key var2 = new Key("sleep_mode", 0);
         sleep_mode = var2;
         Key var3 = new Key("memory_screen_status", 1);
         memory_screen_status = var3;
         Key var7 = new Key("real_shutdown", 2);
         real_shutdown = var7;
         Key var10 = new Key("support_fan", 3);
         support_fan = var10;
         Key var1 = new Key("vehicle_amp", 4);
         vehicle_amp = var1;
         Key var19 = new Key("color_led_status", 5);
         color_led_status = var19;
         Key var6 = new Key("android_sleep_way", 6);
         android_sleep_way = var6;
         Key var9 = new Key("ir_out_way", 7);
         ir_out_way = var9;
         Key var22 = new Key("support_front_camera", 8);
         support_front_camera = var22;
         Key var4 = new Key("support_dtv", 9);
         support_dtv = var4;
         Key var0 = new Key("support_disc", 10);
         support_disc = var0;
         Key var5 = new Key("swap_swc_left_right", 11);
         swap_swc_left_right = var5;
         Key var16 = new Key("swap_swc_up_down", 12);
         swap_swc_up_down = var16;
         Key var20 = new Key("support_quick_charge", 13);
         support_quick_charge = var20;
         Key var14 = new Key("ant_close", 14);
         ant_close = var14;
         Key var18 = new Key("reverse_close", 15);
         reverse_close = var18;
         Key var23 = new Key("amp_close", 16);
         amp_close = var23;
         Key var15 = new Key("front_camera_power_close", 17);
         front_camera_power_close = var15;
         Key var8 = new Key("dtv_module", 18);
         dtv_module = var8;
         Key var17 = new Key("rotate_screen_stop_delay", 19);
         rotate_screen_stop_delay = var17;
         Key var21 = new Key("rotate_screen_run_time", 20);
         rotate_screen_run_time = var21;
         Key var13 = new Key("panel_type", 21);
         panel_type = var13;
         Key var11 = new Key("can_serial_port_level", 22);
         can_serial_port_level = var11;
         Key var12 = new Key("encoding_potentiometer_resistance_value", 23);
         encoding_potentiometer_resistance_value = var12;
         Key var24 = new Key("car_wire_controlled_capacitor", 24);
         car_wire_controlled_capacitor = var24;
         $VALUES = new Key[]{var2, var3, var7, var10, var1, var19, var6, var9, var22, var4, var0, var5, var16, var20, var14, var18, var23, var15, var8, var17, var21, var13, var11, var12, var24};
      }
   }
}
