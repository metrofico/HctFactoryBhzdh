package com.hzbhd.canbus;

import android.util.Log;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.proxy.mcu.core.MCUMainManager;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;

public class CanbusMsgSender {
   public static DecimalFormat df = new DecimalFormat("00");

   public static void logCanData(byte[] var0) {
      boolean var2;
      try {
         var2 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getCanDataLogSwith();
      } catch (Exception var6) {
         var2 = true;
      }

      if (var2) {
         String var3 = "27 16————>";

         for(int var1 = 1; var1 < var0.length; ++var1) {
            String var5 = Integer.toHexString(CanbusMsgSender$$ExternalSyntheticBackport0.m(var0[var1]));
            String var4 = var5;
            if (var5.length() == 1) {
               var4 = "0" + var5;
            }

            var3 = var3 + var4 + " ";
         }

         Log.d("CAN_TX", var3);
      }

   }

   public static void sendMsg(byte[] var0) {
      logCanData(var0);

      try {
         MCUMainManager.getInstance().sendMCUCanboxData(39, var0);
      } catch (Exception var2) {
         if (LogUtil.log5()) {
            LogUtil.d("sendMsg: error: " + FgeString.bytes2HexString(var0, var0.length));
         }
      }

   }
}
