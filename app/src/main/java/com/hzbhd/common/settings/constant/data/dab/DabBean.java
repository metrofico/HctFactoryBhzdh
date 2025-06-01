package com.hzbhd.common.settings.constant.data.dab;

public class DabBean {
   public static enum Key {
      private static final Key[] $VALUES;
      dab_baudrate,
      dab_id,
      dab_module,
      dab_port;

      static {
         Key var3 = new Key("dab_module", 0);
         dab_module = var3;
         Key var0 = new Key("dab_id", 1);
         dab_id = var0;
         Key var1 = new Key("dab_port", 2);
         dab_port = var1;
         Key var2 = new Key("dab_baudrate", 3);
         dab_baudrate = var2;
         $VALUES = new Key[]{var3, var0, var1, var2};
      }
   }
}
