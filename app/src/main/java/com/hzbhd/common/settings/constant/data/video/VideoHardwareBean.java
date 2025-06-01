package com.hzbhd.common.settings.constant.data.video;

public class VideoHardwareBean {
   public static enum Key {
      private static final Key[] $VALUES;
      camera_hardware,
      hdmi_resolution,
      tv_mode,
      vid_6158c,
      vid_n3;

      static {
         Key var0 = new Key("camera_hardware", 0);
         camera_hardware = var0;
         Key var1 = new Key("vid_n3", 1);
         vid_n3 = var1;
         Key var3 = new Key("vid_6158c", 2);
         vid_6158c = var3;
         Key var4 = new Key("hdmi_resolution", 3);
         hdmi_resolution = var4;
         Key var2 = new Key("tv_mode", 4);
         tv_mode = var2;
         $VALUES = new Key[]{var0, var1, var3, var4, var2};
      }
   }
}
