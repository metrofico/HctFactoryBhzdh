package com.hzbhd.canbus.util.amap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.util.LogUtil;
import java.util.Objects;
import java.util.regex.Pattern;

public class AMapBroadcast extends BroadcastReceiver {
   private final String INIT_TIME = "0:0";
   private int TempAllDistance = 0;
   private int TempCarDirection = 0;
   private int TempDestinationDistance = 0;
   private int TempIcon;
   private int TempNextDistance = 0;
   private String TempNextWayName = "";
   private String TempPlanTime = "0:0";
   private int TempSurplusAllTime = 0;
   private String TempSurplusAllTimeStr = "0:0";
   private AMapEntity oldAMapEntity;
   private String tempTime = "0:0";

   private int getCarDirection(int var1) {
      double var2 = (double)var1;
      if (var2 > 67.5 && var2 < 112.5) {
         return 1;
      } else if (var2 > 112.5 && var2 < 157.5) {
         return 2;
      } else if (var2 > 157.5 && var2 < 202.5) {
         return 3;
      } else if (var2 > 202.5 && var2 < 247.5) {
         return 4;
      } else if (var2 > 247.5 && var2 < 292.5) {
         return 5;
      } else if (var2 > 292.5 && var2 < 337.5) {
         return 6;
      } else if (var2 > 337.5 && var1 <= 360 || var1 > 0 && var2 < 22.5) {
         return 7;
      } else {
         return var2 > 22.5 && var2 < 67.5 ? 8 : 0;
      }
   }

   private String removeChinese(String var1) {
      return TextUtils.isEmpty(var1) ? "" : Pattern.compile("[一-龥]").matcher(var1.trim()).replaceAll("");
   }

   private String removeChinese2(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return "";
      } else {
         String[] var2 = Pattern.compile("[一-龥]").matcher(var1.trim()).replaceAll(":").replace("::", ":").split(":");
         if (var1.contains("时") && !var1.contains("分") && var2.length == 1) {
            var1 = var2[0] + ":0";
            this.tempTime = var1;
            return var1;
         } else if (var2.length == 2) {
            var1 = var2[0] + ":" + var2[1];
            this.tempTime = var1;
            return var1;
         } else if (var2.length == 1) {
            var1 = "0:" + var2[0];
            this.tempTime = var1;
            return var1;
         } else {
            return this.tempTime;
         }
      }
   }

   public void onReceive(Context var1, Intent var2) {
      String var10 = var2.getAction();
      if (!TextUtils.isEmpty(var10)) {
         if (var10.equals("AUTONAVI_STANDARD_BROADCAST_SEND")) {
            Bundle var12 = var2.getExtras();
            int var9 = var12.getInt("EXTRA_STATE");
            int var3 = var12.getInt("ROUTE_ALL_DIS");
            int var4 = var12.getInt("SEG_REMAIN_DIS");
            int var5 = var12.getInt("ROUTE_REMAIN_DIS");
            int var8 = var12.getInt("ICON");
            int var6 = var12.getInt("ROUTE_REMAIN_TIME");
            var10 = this.removeChinese2(var12.getString("ROUTE_REMAIN_TIME_AUTO"));
            String var13 = this.removeChinese(var12.getString("ETA_TEXT"));
            String var11 = var12.getString("NEXT_ROAD_NAME");
            int var7 = var12.getInt("CAR_DIRECTION");
            if (var3 == 0) {
               var3 = this.TempAllDistance;
            } else {
               this.TempAllDistance = var3;
            }

            if (var4 == 0) {
               var4 = this.TempNextDistance;
            } else {
               this.TempNextDistance = var4;
            }

            if (var5 == 0) {
               var5 = this.TempDestinationDistance;
            } else {
               this.TempDestinationDistance = var5;
            }

            if (var6 == 0) {
               var6 = this.TempSurplusAllTime;
            } else {
               this.TempSurplusAllTime = var6;
            }

            if (TextUtils.isEmpty(var13)) {
               var13 = this.TempPlanTime;
            } else {
               this.TempPlanTime = var13;
            }

            if (TextUtils.isEmpty(var10)) {
               var10 = this.TempSurplusAllTimeStr;
            } else {
               this.TempSurplusAllTimeStr = var10;
            }

            if (var7 == 0) {
               var7 = this.TempCarDirection;
            } else {
               this.TempCarDirection = var7;
            }

            if (var8 == 0) {
               var8 = this.TempIcon;
            } else {
               this.TempIcon = var8;
            }

            if (TextUtils.isEmpty(var11)) {
               var11 = this.TempNextWayName;
            } else {
               this.TempNextWayName = var11;
            }

            AMapEntity var15 = new AMapEntity(var9, var3, var4, var5, var8, var6, this.getCarDirection(var7), var10, var13, var11);
            AMapEntity var14 = this.oldAMapEntity;
            if (var14 != null && var14.equals(var15)) {
               return;
            }

            this.oldAMapEntity = var15;
            ((MsgMgrInterfaceUtil)Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(var1))).onAMapCallBack(var15);
            if (LogUtil.log5()) {
               LogUtil.d("---------->" + var15.toString());
            }
         } else if (LogUtil.log5()) {
            LogUtil.d("---------->action 是空置");
         }
      }

   }
}
