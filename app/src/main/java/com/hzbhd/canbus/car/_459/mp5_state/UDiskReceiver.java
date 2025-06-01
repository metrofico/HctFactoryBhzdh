package com.hzbhd.canbus.car._459.mp5_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONObject;

public class UDiskReceiver extends BroadcastReceiver {
   public void onReceive(Context var1, Intent var2) {
      if ("android.intent.action.MEDIA_MOUNTED".equals(var2.getAction())) {
         this.sendData("MP5_USBSts", 1);
      } else if (!"android.intent.action.MEDIA_REMOVED".equals(var2.getAction()) && "android.intent.action.MEDIA_UNMOUNTED".equals(var2.getAction())) {
         this.sendData("MP5_USBSts", 0);
      }

   }

   public void sendData(String var1, int var2) {
      try {
         JSONObject var3 = new JSONObject();
         var3.put("TAG", "MP5_Sts");
         var3.put("KEY", var1);
         var3.put("VALUE", var2);
         ShareDataManager.getInstance().reportString("can.request.data.share", var3.toString());
      } catch (Exception var4) {
      }

   }
}
