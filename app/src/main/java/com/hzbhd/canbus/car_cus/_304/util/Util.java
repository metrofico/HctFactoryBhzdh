package com.hzbhd.canbus.car_cus._304.util;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;

public class Util {
   public static void sendAvmCommand(int var0) {
      FutureUtil.instance.reqMcuKey(new byte[]{-47, (byte)var0});
      CanbusMsgSender.sendMsg(new byte[]{22, -80, (byte)(var0 << 6), 0});
   }
}
