package com.hzbhd.common.settings.constant.data.audio;

public class AudioSoundFieldBean {
   public static enum Key {
      private static final Key[] $VALUES;
      fader_balance,
      fb_fl,
      fb_fr,
      fb_rl,
      fb_rr,
      loundness;

      static {
         Key var4 = new Key("fader_balance", 0);
         fader_balance = var4;
         Key var1 = new Key("fb_fl", 1);
         fb_fl = var1;
         Key var0 = new Key("fb_fr", 2);
         fb_fr = var0;
         Key var5 = new Key("fb_rl", 3);
         fb_rl = var5;
         Key var3 = new Key("fb_rr", 4);
         fb_rr = var3;
         Key var2 = new Key("loundness", 5);
         loundness = var2;
         $VALUES = new Key[]{var4, var1, var0, var5, var3, var2};
      }
   }
}
