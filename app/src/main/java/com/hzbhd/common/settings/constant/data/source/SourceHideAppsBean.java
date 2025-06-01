package com.hzbhd.common.settings.constant.data.source;

public class SourceHideAppsBean {
   public static enum Key {
      private static final Key[] $VALUES;
      clsName,
      hide,
      hide_app,
      name,
      pkgName;

      static {
         Key var3 = new Key("hide_app", 0);
         hide_app = var3;
         Key var4 = new Key("name", 1);
         name = var4;
         Key var0 = new Key("pkgName", 2);
         pkgName = var0;
         Key var1 = new Key("clsName", 3);
         clsName = var1;
         Key var2 = new Key("hide", 4);
         hide = var2;
         $VALUES = new Key[]{var3, var4, var0, var1, var2};
      }
   }
}
