package com.hzbhd.canbus.car_cus._448;

import android.util.Log;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.dvr.DvrManager;

public class DvrSender {
   private static byte[] SplicingByte(byte[] var0, byte[] var1) {
      byte[] var2 = new byte[var0.length + var1.length];
      System.arraycopy(var0, 0, var2, 0, var0.length);
      System.arraycopy(var1, 0, var2, var0.length, var1.length);
      return var2;
   }

   public static void send(byte[] var0) {
      Log.d("DvrData", "发送数据-----------------------------------");
      var0 = SplicingByte(new byte[]{-86, 68, 77, (byte)(var0.length + 5)}, var0);
      int var1 = 0;

      int var2;
      for(var2 = 0; var1 < var0.length; ++var1) {
         var2 += var0[var1];
      }

      var0 = SplicingByte(var0, new byte[]{(byte)DataHandleUtils.getIntFromByteWithBit(var2, 0, 8)});
      DvrManager.INSTANCE.sendData(var0);
   }
}
