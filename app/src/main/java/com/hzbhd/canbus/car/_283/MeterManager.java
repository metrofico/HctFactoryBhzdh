package com.hzbhd.canbus.car._283;

import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;

public class MeterManager {
   public static byte[] m0xE6Data = new byte[]{22, -26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

   public static void send0xE6() {
      CommUtil.printHexString("scyscyscy0xE6：", m0xE6Data);
      MessageSender.sendMsg(m0xE6Data);
   }

   public static void send0xE6Null(int var0) {
      Log.d("scyscyscy", "---------->" + var0);
      m0xE6Data[6] = (byte)DataHandleUtils.setIntByteWithBit(0, 7, true);
      send0xE6();
   }

   public static void sendMediaMeterData(String var0, String var1, String var2, String var3) {
      String var4 = var0;
      if (TextUtils.isEmpty(var0)) {
         var4 = " ";
      }

      var0 = var1;
      if (TextUtils.isEmpty(var1)) {
         var0 = " ";
      }

      var1 = var2;
      if (TextUtils.isEmpty(var2)) {
         var1 = " ";
      }

      var2 = var3;
      if (TextUtils.isEmpty(var3)) {
         var2 = " ";
      }

      sendSourceMsg1(var4);
      sendSourceMsg2(var0, 146);
      sendSourceMsg2(var1, 147);
      sendSourceMsg2(var2, 148);
   }

   private static void sendSourceMsg1(String var0) {
      byte[] var1 = var0.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var1), 28));
   }

   private static void sendSourceMsg2(String var0, int var1) {
      byte[] var2 = var0.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, (byte)var1, 0}, var2), 27));
   }
}
