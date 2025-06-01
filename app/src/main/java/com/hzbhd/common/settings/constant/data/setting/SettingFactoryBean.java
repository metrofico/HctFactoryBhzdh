package com.hzbhd.common.settings.constant.data.setting;

public class SettingFactoryBean {
   public static enum Key {
      private static final Key[] $VALUES;
      customer_list,
      customer_pwd,
      developer_pwd,
      factory_list,
      factory_pwd;

      static {
         Key var3 = new Key("developer_pwd", 0);
         developer_pwd = var3;
         Key var1 = new Key("factory_pwd", 1);
         factory_pwd = var1;
         Key var4 = new Key("customer_pwd", 2);
         customer_pwd = var4;
         Key var2 = new Key("factory_list", 3);
         factory_list = var2;
         Key var0 = new Key("customer_list", 4);
         customer_list = var0;
         $VALUES = new Key[]{var3, var1, var4, var2, var0};
      }
   }
}
