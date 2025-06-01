package com.hzbhd.common.settings.constant.data.audio;

public class AudioSubwooferBean {
   public static enum Key {
      private static final Key[] $VALUES;
      enable,
      freq,
      q,
      source_id,
      sub,
      subwoofer,
      vol;

      static {
         Key var4 = new Key("enable", 0);
         enable = var4;
         Key var5 = new Key("subwoofer", 1);
         subwoofer = var5;
         Key var0 = new Key("source_id", 2);
         source_id = var0;
         Key var6 = new Key("sub", 3);
         sub = var6;
         Key var2 = new Key("freq", 4);
         freq = var2;
         Key var3 = new Key("q", 5);
         q = var3;
         Key var1 = new Key("vol", 6);
         vol = var1;
         $VALUES = new Key[]{var4, var5, var0, var6, var2, var3, var1};
      }
   }
}
