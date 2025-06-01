package com.hzbhd.common.settings.constant.data.video;

public class VideoCamerasBean {
   public static enum Key {
      private static final Key[] $VALUES;
      camera_id,
      cameras,
      channel,
      format,
      mirror,
      params,
      type;

      static {
         Key var5 = new Key("cameras", 0);
         cameras = var5;
         Key var0 = new Key("type", 1);
         type = var0;
         Key var6 = new Key("params", 2);
         params = var6;
         Key var2 = new Key("camera_id", 3);
         camera_id = var2;
         Key var3 = new Key("channel", 4);
         channel = var3;
         Key var4 = new Key("mirror", 5);
         mirror = var4;
         Key var1 = new Key("format", 6);
         format = var1;
         $VALUES = new Key[]{var5, var0, var6, var2, var3, var4, var1};
      }
   }
}
