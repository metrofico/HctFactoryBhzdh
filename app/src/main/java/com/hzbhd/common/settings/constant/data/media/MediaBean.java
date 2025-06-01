package com.hzbhd.common.settings.constant.data.media;

public class MediaBean {
   public static enum Key {
      private static final Key[] $VALUES;
      auto_close,
      default_play_list_type,
      media_ui;

      static {
         Key var0 = new Key("media_ui", 0);
         media_ui = var0;
         Key var2 = new Key("auto_close", 1);
         auto_close = var2;
         Key var1 = new Key("default_play_list_type", 2);
         default_play_list_type = var1;
         $VALUES = new Key[]{var0, var2, var1};
      }
   }
}
