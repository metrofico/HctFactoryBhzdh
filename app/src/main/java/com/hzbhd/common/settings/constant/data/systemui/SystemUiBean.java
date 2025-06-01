package com.hzbhd.common.settings.constant.data.systemui;

public class SystemUiBean {
   public static enum Key {
      private static final Key[] $VALUES;
      navi_split_location,
      navi_split_screen;

      static {
         Key var0 = new Key("navi_split_screen", 0);
         navi_split_screen = var0;
         Key var1 = new Key("navi_split_location", 1);
         navi_split_location = var1;
         $VALUES = new Key[]{var0, var1};
      }
   }
}
