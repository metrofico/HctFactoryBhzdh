package com.hzbhd.constant.share.bluetooth;

public class BluetoothInfoShare {
   public static enum Key {
      private static final Key[] $VALUES;
      name,
      number,
      status;

      static {
         Key var1 = new Key("status", 0);
         status = var1;
         Key var0 = new Key("name", 1);
         name = var0;
         Key var2 = new Key("number", 2);
         number = var2;
         $VALUES = new Key[]{var1, var0, var2};
      }
   }

   public static enum key_status {
      private static final key_status[] $VALUES;
      call,
      close,
      connect,
      contact,
      disconnect,
      hangup,
      idle,
      incoming,
      none,
      offhook,
      open,
      reject;

      static {
         key_status var5 = new key_status("none", 0);
         none = var5;
         key_status var11 = new key_status("open", 1);
         open = var11;
         key_status var7 = new key_status("close", 2);
         close = var7;
         key_status var4 = new key_status("connect", 3);
         connect = var4;
         key_status var8 = new key_status("disconnect", 4);
         disconnect = var8;
         key_status var2 = new key_status("idle", 5);
         idle = var2;
         key_status var3 = new key_status("incoming", 6);
         incoming = var3;
         key_status var9 = new key_status("call", 7);
         call = var9;
         key_status var6 = new key_status("hangup", 8);
         hangup = var6;
         key_status var0 = new key_status("reject", 9);
         reject = var0;
         key_status var1 = new key_status("offhook", 10);
         offhook = var1;
         key_status var10 = new key_status("contact", 11);
         contact = var10;
         $VALUES = new key_status[]{var5, var11, var7, var4, var8, var2, var3, var9, var6, var0, var1, var10};
      }
   }
}
