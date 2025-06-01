package com.hzbhd.common.settings.constant.data.setting;

public class SettingItemHideBean {
   public static enum Key {
      private static final Key[] $VALUES;
      acc_off_delay,
      app_fullscreen_select,
      auxin,
      browser_select,
      buzzer,
      door_display_setup,
      driving_video,
      dvr_select,
      filter_audiofile_size,
      front_camera,
      head_light,
      navi_split_open,
      radio_antenna_power,
      radio_area,
      reversing_baseline,
      reversing_signal_detection,
      reversing_track,
      screenshot_save_path,
      set_camera_hardware_channel,
      sound_mix_ratio,
      switch_split_screen,
      ui_selected,
      usb_port0,
      usb_port1;

      static {
         Key var14 = new Key("driving_video", 0);
         driving_video = var14;
         Key var18 = new Key("set_camera_hardware_channel", 1);
         set_camera_hardware_channel = var18;
         Key var13 = new Key("reversing_signal_detection", 2);
         reversing_signal_detection = var13;
         Key var4 = new Key("reversing_track", 3);
         reversing_track = var4;
         Key var23 = new Key("reversing_baseline", 4);
         reversing_baseline = var23;
         Key var0 = new Key("dvr_select", 5);
         dvr_select = var0;
         Key var15 = new Key("browser_select", 6);
         browser_select = var15;
         Key var2 = new Key("app_fullscreen_select", 7);
         app_fullscreen_select = var2;
         Key var11 = new Key("door_display_setup", 8);
         door_display_setup = var11;
         Key var16 = new Key("navi_split_open", 9);
         navi_split_open = var16;
         Key var12 = new Key("switch_split_screen", 10);
         switch_split_screen = var12;
         Key var21 = new Key("radio_area", 11);
         radio_area = var21;
         Key var1 = new Key("filter_audiofile_size", 12);
         filter_audiofile_size = var1;
         Key var6 = new Key("buzzer", 13);
         buzzer = var6;
         Key var8 = new Key("usb_port0", 14);
         usb_port0 = var8;
         Key var19 = new Key("usb_port1", 15);
         usb_port1 = var19;
         Key var3 = new Key("head_light", 16);
         head_light = var3;
         Key var22 = new Key("acc_off_delay", 17);
         acc_off_delay = var22;
         Key var10 = new Key("sound_mix_ratio", 18);
         sound_mix_ratio = var10;
         Key var9 = new Key("radio_antenna_power", 19);
         radio_antenna_power = var9;
         Key var20 = new Key("screenshot_save_path", 20);
         screenshot_save_path = var20;
         Key var5 = new Key("auxin", 21);
         auxin = var5;
         Key var7 = new Key("front_camera", 22);
         front_camera = var7;
         Key var17 = new Key("ui_selected", 23);
         ui_selected = var17;
         $VALUES = new Key[]{var14, var18, var13, var4, var23, var0, var15, var2, var11, var16, var12, var21, var1, var6, var8, var19, var3, var22, var10, var9, var20, var5, var7, var17};
      }
   }
}
