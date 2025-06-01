package com.hzbhd.commontools;

public class KeySourceKeyAppType {
   public static enum Type {
      private static final Type[] $VALUES;
      ACTIONID,
      APPID,
      SOURCEID;

      static {
         Type var1 = new Type("SOURCEID", 0);
         SOURCEID = var1;
         Type var0 = new Type("APPID", 1);
         APPID = var0;
         Type var2 = new Type("ACTIONID", 2);
         ACTIONID = var2;
         $VALUES = new Type[]{var1, var0, var2};
      }
   }
}
