package com.hzbhd.canbus.car_cus._446.util;

import com.hzbhd.canbus.CanbusMsgSender;

public class SendUtil {
   private static byte[] SplicingByte(byte[] var0, byte[] var1) {
      byte[] var2 = new byte[var0.length + var1.length];
      System.arraycopy(var0, 0, var2, 0, var0.length);
      System.arraycopy(var1, 0, var2, var0.length, var1.length);
      return var2;
   }

   public static void send(byte[] var0) {
      int var1 = 0;

      for(byte[] var2 = SplicingByte(var0, new byte[]{1}); var1 < 5; ++var1) {
         try {
            Thread.sleep(100L);
            CanbusMsgSender.sendMsg(var2);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      }

   }
}
