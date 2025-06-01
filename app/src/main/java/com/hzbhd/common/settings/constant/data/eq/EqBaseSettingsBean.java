package com.hzbhd.common.settings.constant.data.eq;

public class EqBaseSettingsBean {
   public static enum Key {
      private static final Key[] $VALUES;
      band_num,
      source_user,
      user_num;

      static {
         Key var2 = new Key("band_num", 0);
         band_num = var2;
         Key var1 = new Key("user_num", 1);
         user_num = var1;
         Key var0 = new Key("source_user", 2);
         source_user = var0;
         $VALUES = new Key[]{var2, var1, var0};
      }
   }
}
