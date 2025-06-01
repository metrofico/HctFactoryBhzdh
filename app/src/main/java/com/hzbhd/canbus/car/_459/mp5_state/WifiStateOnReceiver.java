package com.hzbhd.canbus.car._459.mp5_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"},
   d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/WifiStateOnReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "sendData", "key", "", "value", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class WifiStateOnReceiver extends BroadcastReceiver {
   public void onReceive(Context var1, Intent var2) {
      Object var3 = null;
      String var4;
      if (var2 != null) {
         var4 = var2.getAction();
      } else {
         var4 = null;
      }

      if (LogUtil.log5()) {
         LogUtil.d("onReceive(): -----------");
      }

      if (Intrinsics.areEqual((Object)"android.net.wifi.STATE_CHANGE", (Object)var4)) {
         NetworkInfo var5 = (NetworkInfo)var2.getParcelableExtra("networkInfo");
         NetworkInfo.State var6 = (NetworkInfo.State)var3;
         if (var5 != null) {
            var6 = var5.getState();
         }

         if (var6 == State.CONNECTED) {
            this.sendData("MP5_WIFlSts", 1);
         } else {
            this.sendData("MP5_WIFlSts", 0);
         }
      }

   }

   public final void sendData(String var1, int var2) {
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
