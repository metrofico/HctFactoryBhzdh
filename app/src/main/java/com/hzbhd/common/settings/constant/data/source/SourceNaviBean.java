package com.hzbhd.common.settings.constant.data.source;

public class SourceNaviBean {
   public static enum Key {
      private static final Key[] $VALUES;
      clsName,
      navi_app,
      pkgName,
      start_mode;

      static {
         Key var3 = new Key("start_mode", 0);
         start_mode = var3;
         Key var2 = new Key("navi_app", 1);
         navi_app = var2;
         Key var0 = new Key("pkgName", 2);
         pkgName = var0;
         Key var1 = new Key("clsName", 3);
         clsName = var1;
         $VALUES = new Key[]{var3, var2, var0, var1};
      }
   }
}
