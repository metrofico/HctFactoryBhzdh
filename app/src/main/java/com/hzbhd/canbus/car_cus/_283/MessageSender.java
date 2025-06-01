package com.hzbhd.canbus.car_cus._283;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.CommUtil;

public class MessageSender {
   public static void getMsg(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, 10, 1, (byte)var0});
   }

   public static void sendDzMsg(int var0, int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{23, (byte)var0, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public static void sendMsg(byte var0, byte var1, boolean var2) {
      byte[] var3 = new byte[]{22, var0, var1, (byte)(var2 ^ 1)};
      CanbusMsgSender.sendMsg(var3);
      if (var0 == 74) {
         CommUtil.printHexString("111111发：", var3);
      }

   }

   public static void sendMsg(byte var0, byte[] var1) {
      FutureUtil.instance.reportCanbusInfoOther(var0, var1);
   }

   public static void sendMsg(byte[] var0) {
      CanbusMsgSender.sendMsg(var0);
   }

   public static void sendMsg0x78(byte[] var0) {
      FutureUtil.instance.reportCanbusInfoOther((byte)120, var0);
      CommUtil.printHexString("scyscyscy 0x78 send2:", var0);
   }

   public static void sendVoiceMsg(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{24, -103, (byte)var0});
   }
}
