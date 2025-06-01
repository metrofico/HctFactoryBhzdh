package com.hzbhd.common.settings.constant.data.misc;

public class MiscReverseBean {
   public static enum Key {
      private static final Key[] $VALUES;
      auto_in_fcamera,
      auto_out_fcamera,
      automatic_recognition,
      baseline,
      camera_source,
      track;

      static {
         Key var1 = new Key("camera_source", 0);
         camera_source = var1;
         Key var2 = new Key("track", 1);
         track = var2;
         Key var5 = new Key("baseline", 2);
         baseline = var5;
         Key var0 = new Key("auto_in_fcamera", 3);
         auto_in_fcamera = var0;
         Key var4 = new Key("auto_out_fcamera", 4);
         auto_out_fcamera = var4;
         Key var3 = new Key("automatic_recognition", 5);
         automatic_recognition = var3;
         $VALUES = new Key[]{var1, var2, var5, var0, var4, var3};
      }
   }
}
