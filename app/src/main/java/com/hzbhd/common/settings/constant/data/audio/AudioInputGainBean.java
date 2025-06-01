package com.hzbhd.common.settings.constant.data.audio;

public class AudioInputGainBean {
   public static enum Key {
      private static final Key[] $VALUES;
      def_gain,
      gain,
      input,
      max,
      param,
      source_id,
      stream_gain,
      stream_max,
      type;

      static {
         Key var8 = new Key("input", 0);
         input = var8;
         Key var0 = new Key("source_id", 1);
         source_id = var0;
         Key var5 = new Key("param", 2);
         param = var5;
         Key var6 = new Key("type", 3);
         type = var6;
         Key var7 = new Key("max", 4);
         max = var7;
         Key var3 = new Key("gain", 5);
         gain = var3;
         Key var1 = new Key("stream_max", 6);
         stream_max = var1;
         Key var2 = new Key("stream_gain", 7);
         stream_gain = var2;
         Key var4 = new Key("def_gain", 8);
         def_gain = var4;
         $VALUES = new Key[]{var8, var0, var5, var6, var7, var3, var1, var2, var4};
      }
   }
}
