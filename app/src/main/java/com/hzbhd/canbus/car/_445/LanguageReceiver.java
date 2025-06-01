package com.hzbhd.canbus.car._445;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;

public class LanguageReceiver extends BroadcastReceiver {
   public static int getLanguage(Context var0) {
      if (var0 == null) {
         return 2;
      } else {
         String var1 = var0.getResources().getConfiguration().getLocales().get(0).getCountry();
         if (var1.endsWith("CN")) {
            return 0;
         } else {
            return !var1.equals("TW") && !var1.equals("HK") && !var1.equals("MO") ? 2 : 1;
         }
      }
   }

   public void onReceive(Context var1, Intent var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)MsgMgr.hour, (byte)MsgMgr.minute, (byte)getLanguage(var1)});
      MsgMgr.setLanguage(var1);
   }
}
