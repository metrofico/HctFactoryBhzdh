package com.hzbhd.common.settings.constant.data.setting;

public class SettingMainBean {
   public static enum Key {
      private static final Key[] $VALUES;
      bluetooth,
      can,
      canbus_pwd,
      car_logo,
      color_led,
      gain,
      general,
      gesture_learning,
      info,
      language,
      lcdsetting,
      navi,
      original_screen,
      swc,
      video,
      vol,
      wifi;

      static {
         Key var3 = new Key("navi", 0);
         navi = var3;
         Key var7 = new Key("color_led", 1);
         color_led = var7;
         Key var6 = new Key("car_logo", 2);
         car_logo = var6;
         Key var12 = new Key("swc", 3);
         swc = var12;
         Key var1 = new Key("info", 4);
         info = var1;
         Key var14 = new Key("gesture_learning", 5);
         gesture_learning = var14;
         Key var5 = new Key("original_screen", 6);
         original_screen = var5;
         Key var8 = new Key("lcdsetting", 7);
         lcdsetting = var8;
         Key var9 = new Key("wifi", 8);
         wifi = var9;
         Key var13 = new Key("bluetooth", 9);
         bluetooth = var13;
         Key var10 = new Key("language", 10);
         language = var10;
         Key var0 = new Key("can", 11);
         can = var0;
         Key var2 = new Key("vol", 12);
         vol = var2;
         Key var15 = new Key("gain", 13);
         gain = var15;
         Key var11 = new Key("video", 14);
         video = var11;
         Key var4 = new Key("general", 15);
         general = var4;
         Key var16 = new Key("canbus_pwd", 16);
         canbus_pwd = var16;
         $VALUES = new Key[]{var3, var7, var6, var12, var1, var14, var5, var8, var9, var13, var10, var0, var2, var15, var11, var4, var16};
      }
   }
}
