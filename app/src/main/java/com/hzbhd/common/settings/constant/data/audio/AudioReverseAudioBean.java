package com.hzbhd.common.settings.constant.data.audio;

public class AudioReverseAudioBean {
   public static enum Key {
      private static final Key[] $VALUES;
      def_volume_rate,
      volume_rate;

      static {
         Key var0 = new Key("volume_rate", 0);
         volume_rate = var0;
         Key var1 = new Key("def_volume_rate", 1);
         def_volume_rate = var1;
         $VALUES = new Key[]{var0, var1};
      }
   }
}
