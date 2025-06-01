package com.hzbhd.canbus.car_cus._299;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;

public class MessageSender {
   public static void sendDzMsg(int var0, int var1, int var2, int var3, int var4) {
      byte var7 = (byte)var0;
      byte var9 = (byte)var1;
      byte var8 = (byte)var2;
      byte var5 = (byte)var3;
      byte var6 = (byte)var4;
      FutureUtil.instance.reportSmartPowerInfo(new byte[]{var7, var9, var8, var5, var6});
   }

   public static void sendMsg(byte[] var0) {
      CanbusMsgSender.sendMsg(var0);
   }
}
