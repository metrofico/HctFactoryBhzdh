package com.hzbhd.proxy.keydispatcher.constants;

import com.hzbhd.commontools.SourceConstantsDef;

public class KeyDispatcherConstant {
   public static int getAppId(int var0) {
      return (APP_TYPE.USER_APP.ordinal() << 8) + var0;
   }

   public static int getAppId(SourceConstantsDef.MODULE_ID var0) {
      return (APP_TYPE.SYSTEM_MODULE.ordinal() << 8) + var0.getValue();
   }

   public static int getAppId(SourceConstantsDef.SOURCE_ID var0) {
      return (APP_TYPE.SOURCE_APP.ordinal() << 8) + var0.getValue();
   }

   public static APP_TYPE getAppType(int var0) {
      return APP_TYPE.values()[var0 >> 8];
   }

   public static enum APP_TYPE {
      private static final APP_TYPE[] $VALUES;
      SOURCE_APP,
      SYSTEM_MODULE,
      USER_APP;

      static {
         APP_TYPE var2 = new APP_TYPE("SYSTEM_MODULE", 0);
         SYSTEM_MODULE = var2;
         APP_TYPE var0 = new APP_TYPE("SOURCE_APP", 1);
         SOURCE_APP = var0;
         APP_TYPE var1 = new APP_TYPE("USER_APP", 2);
         USER_APP = var1;
         $VALUES = new APP_TYPE[]{var2, var0, var1};
      }
   }
}
