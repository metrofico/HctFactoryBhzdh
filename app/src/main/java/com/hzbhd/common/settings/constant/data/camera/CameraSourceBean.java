package com.hzbhd.common.settings.constant.data.camera;

public class CameraSourceBean {
   public static enum Key {
      private static final Key[] $VALUES;
      brightness,
      camera_setup,
      channel,
      contrast,
      format,
      mirror,
      picture,
      rotate,
      saturation,
      source_camera,
      source_id;

      static {
         Key var2 = new Key("source_camera", 0);
         source_camera = var2;
         Key var1 = new Key("source_id", 1);
         source_id = var1;
         Key var7 = new Key("camera_setup", 2);
         camera_setup = var7;
         Key var8 = new Key("mirror", 3);
         mirror = var8;
         Key var3 = new Key("rotate", 4);
         rotate = var3;
         Key var5 = new Key("channel", 5);
         channel = var5;
         Key var4 = new Key("format", 6);
         format = var4;
         Key var9 = new Key("picture", 7);
         picture = var9;
         Key var6 = new Key("brightness", 8);
         brightness = var6;
         Key var0 = new Key("contrast", 9);
         contrast = var0;
         Key var10 = new Key("saturation", 10);
         saturation = var10;
         $VALUES = new Key[]{var2, var1, var7, var8, var3, var5, var4, var9, var6, var0, var10};
      }
   }
}
