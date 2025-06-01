package com.hzbhd.common.settings.constant.data.audio;

public class AudioBean {
   public static enum Key {
      private static final Key[] $VALUES;
      auto_play_music,
      bu32107,
      device_id,
      filter_audio_filesize,
      percentage_of_navigation_mix;

      static {
         Key var2 = new Key("device_id", 0);
         device_id = var2;
         Key var4 = new Key("bu32107", 1);
         bu32107 = var4;
         Key var3 = new Key("filter_audio_filesize", 2);
         filter_audio_filesize = var3;
         Key var0 = new Key("auto_play_music", 3);
         auto_play_music = var0;
         Key var1 = new Key("percentage_of_navigation_mix", 4);
         percentage_of_navigation_mix = var1;
         $VALUES = new Key[]{var2, var4, var3, var0, var1};
      }
   }
}
