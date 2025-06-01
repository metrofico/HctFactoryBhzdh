package com.hzbhd.common.settings.constant.data.audio;

public class AudioSourceVolumeBean {
   public static enum Key {
      private static final Key[] $VALUES;
      def,
      source_id,
      source_volume,
      volume,
      volume_max;

      static {
         Key var2 = new Key("volume_max", 0);
         volume_max = var2;
         Key var4 = new Key("source_volume", 1);
         source_volume = var4;
         Key var1 = new Key("source_id", 2);
         source_id = var1;
         Key var3 = new Key("volume", 3);
         volume = var3;
         Key var0 = new Key("def", 4);
         def = var0;
         $VALUES = new Key[]{var2, var4, var1, var3, var0};
      }
   }
}
