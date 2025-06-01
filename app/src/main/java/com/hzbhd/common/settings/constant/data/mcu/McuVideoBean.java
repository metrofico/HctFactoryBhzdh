package com.hzbhd.common.settings.constant.data.mcu;

public class McuVideoBean {
   public static enum Key {
      private static final Key[] $VALUES;
      camera_input_gain,
      camera_mirror,
      front_camera_mirror,
      parking,
      reversing_signal_detection,
      tv_input_gain,
      video_input_gain;

      static {
         Key var2 = new Key("parking", 0);
         parking = var2;
         Key var3 = new Key("camera_mirror", 1);
         camera_mirror = var3;
         Key var1 = new Key("front_camera_mirror", 2);
         front_camera_mirror = var1;
         Key var4 = new Key("video_input_gain", 3);
         video_input_gain = var4;
         Key var0 = new Key("camera_input_gain", 4);
         camera_input_gain = var0;
         Key var5 = new Key("tv_input_gain", 5);
         tv_input_gain = var5;
         Key var6 = new Key("reversing_signal_detection", 6);
         reversing_signal_detection = var6;
         $VALUES = new Key[]{var2, var3, var1, var4, var0, var5, var6};
      }
   }
}
