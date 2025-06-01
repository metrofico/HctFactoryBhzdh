package com.hzbhd.common.settings.constant.data.setting;

public class SettingInitBean {
   public static enum Key {
      private static final Key[] $VALUES;
      isCarplay,
      language,
      mic_1_gain,
      mic_2_gain;

      static {
         Key var2 = new Key("language", 0);
         language = var2;
         Key var1 = new Key("isCarplay", 1);
         isCarplay = var1;
         Key var0 = new Key("mic_1_gain", 2);
         mic_1_gain = var0;
         Key var3 = new Key("mic_2_gain", 3);
         mic_2_gain = var3;
         $VALUES = new Key[]{var2, var1, var0, var3};
      }
   }
}
