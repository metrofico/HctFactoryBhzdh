package com.hzbhd.proxy.mcu.constant;

public enum MCU_MESSAGE_PEER {
   private static final MCU_MESSAGE_PEER[] $VALUES;
   BTIVT,
   DVR,
   MCU,
   TSMCU;

   static {
      MCU_MESSAGE_PEER var2 = new MCU_MESSAGE_PEER("MCU", 0);
      MCU = var2;
      MCU_MESSAGE_PEER var3 = new MCU_MESSAGE_PEER("DVR", 1);
      DVR = var3;
      MCU_MESSAGE_PEER var0 = new MCU_MESSAGE_PEER("BTIVT", 2);
      BTIVT = var0;
      MCU_MESSAGE_PEER var1 = new MCU_MESSAGE_PEER("TSMCU", 3);
      TSMCU = var1;
      $VALUES = new MCU_MESSAGE_PEER[]{var2, var3, var0, var1};
   }

   public static MCU_MESSAGE_PEER getPeerByValue(int var0) {
      MCU_MESSAGE_PEER[] var4 = values();
      int var2 = var4.length;
      int var1 = 0;

      MCU_MESSAGE_PEER var3;
      while(true) {
         if (var1 >= var2) {
            var3 = null;
            break;
         }

         var3 = var4[var1];
         if (var0 == var3.ordinal()) {
            break;
         }

         ++var1;
      }

      return var3;
   }
}
