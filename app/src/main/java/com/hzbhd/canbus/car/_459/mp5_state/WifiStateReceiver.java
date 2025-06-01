package com.hzbhd.canbus.car._459.mp5_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONObject;

public class WifiStateReceiver extends BroadcastReceiver {
   public void onReceive(Context var1, Intent var2) {
      int var3 = ((WifiManager)var1.getSystemService("wifi")).getWifiState();
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     Log.d("WIFI_STATE", "未知状态");
                     this.sendData("MP5_WIFlSts", 2);
                  }
               } else {
                  Log.d("WIFI_STATE", "WIFI已启动");
               }
            } else {
               Log.d("WIFI_STATE", "WIFI启动中");
            }
         } else {
            Log.d("WIFI_STATE", "WIFI状态已禁用");
            this.sendData("MP5_WIFlSts", 0);
         }
      } else {
         Log.d("WIFI_STATE", "WIFI禁用中");
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
