package com.hzbhd.canbus.media.aux2.action;

public class ActionTag {
   public static enum TAG {
      private static final TAG[] $VALUES;
      EXIT,
      OPEN;

      static {
         TAG var1 = new TAG("OPEN", 0);
         OPEN = var1;
         TAG var0 = new TAG("EXIT", 1);
         EXIT = var0;
         $VALUES = new TAG[]{var1, var0};
      }
   }
}
