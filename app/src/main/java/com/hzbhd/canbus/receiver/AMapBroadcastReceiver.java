package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import java.util.regex.Pattern;

public class AMapBroadcastReceiver extends BroadcastReceiver {
   public static int getCarDirection(int var0, String var1) {
      byte var4;
      label81: {
         if (!TextUtils.isEmpty(var1) && !var1.equals("东")) {
            if (var1.equals("南")) {
               var4 = 2;
               break label81;
            }

            if (var1.equals("西")) {
               var4 = 4;
               break label81;
            }

            if (var1.equals("北")) {
               var4 = 6;
               break label81;
            }
         }

         var4 = 0;
      }

      double var2 = (double)var0;
      if (var2 > 337.5 && var0 <= 360 || var0 > 0 && var2 < 22.5) {
         return resultDirection(7, var4);
      } else if (var2 > 22.5 && var2 < 67.5) {
         return resultDirection(8, var4);
      } else if (var2 > 67.5 && var2 < 112.5) {
         return resultDirection(1, var4);
      } else if (var2 > 112.5 && var2 < 157.5) {
         return resultDirection(2, var4);
      } else if (var2 > 157.5 && var2 < 202.5) {
         return resultDirection(3, var4);
      } else if (var2 > 202.5 && var2 < 247.5) {
         return resultDirection(4, var4);
      } else if (var2 > 247.5 && var2 < 292.5) {
         return resultDirection(5, var4);
      } else {
         return var2 > 292.5 && var2 < 337.5 ? resultDirection(6, var4) : 0;
      }
   }

   private String getSurplus(int var1) {
      int var3 = var1 / 3600;
      int var4 = var1 - var3 * 3600;
      int var2 = var4 / 60;
      var1 = var2;
      if (var4 - var2 * 60 >= 30) {
         var1 = var2 + 1;
      }

      return var3 + ":" + var1;
   }

   private String removeChinese(String var1) {
      return TextUtils.isEmpty(var1) ? "" : Pattern.compile("[一-龥]").matcher(var1.trim()).replaceAll("");
   }

   private static int resultDirection(int var0, int var1) {
      return var0 <= var1 ? 8 - var0 : var0 - var1;
   }

   public void onReceive(Context var1, Intent var2) {
      String var3 = var2.getAction();
      if (!TextUtils.isEmpty(var3)) {
         if (var3.equals("AUTONAVI_STANDARD_BROADCAST_SEND")) {
            Bundle var4 = var2.getExtras();
            MsgMgrFactory.getCanMsgMgrUtil(var1).aMapCallBack(var4);
         }

      }
   }
}
