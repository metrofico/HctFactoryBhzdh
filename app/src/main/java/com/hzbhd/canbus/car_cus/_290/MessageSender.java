package com.hzbhd.canbus.car_cus._290;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;

public class MessageSender {
   private static int[] mCommonSwitchs;
   private static Context mContext;

   private static int change(int var0, int var1, boolean var2, int var3) {
      var0 = DataHandleUtils.setIntByteWithBit(var0, var1, var2);
      changeCommonSwitchIndex(var3, var0);
      return var0;
   }

   private static int changeAir(int var0, int var1, boolean var2) {
      return DataHandleUtils.setIntByteWithBit(var0, var1, var2);
   }

   private static void changeCommonSwitchIndex(int var0, int var1) {
      mCommonSwitchs[var0] = var1;
   }

   public static String getCommonSwitch() {
      return SharePreUtil.getStringValue(mContext, "_273_commonSwitch", "");
   }

   public static int[] getmCommonSwitchs() {
      return mCommonSwitchs;
   }

   private void saveCanBusInfo(int[] var1, int var2) {
      StringBuilder var5 = new StringBuilder();
      int var4 = var1.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         var5.append(var1[var3]);
         var5.append(",");
      }

      LogUtil.showLog(var5.toString());
      if (var2 == 1) {
         SharePreUtil.setStringValue(mContext, "_273_airInfo1", var5.toString());
      } else if (var2 == 2) {
         SharePreUtil.setStringValue(mContext, "_273_airInfo2", var5.toString());
      }

   }

   public static void saveCommonSwitch(int[] var0) {
      StringBuilder var3 = new StringBuilder();
      int var2 = var0.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         var3.append(var0[var1]);
         var3.append(",");
      }

      LogUtil.showLog(var3.toString());
      SharePreUtil.setStringValue(mContext, "_273_commonSwitch", var3.toString());
   }

   public static void setCommonSwitchs(int[] var0) {
      mCommonSwitchs = var0;
   }

   public static void setContext(Context var0) {
      mContext = var0;
   }

   public static void showAirSender(int var0, int var1, boolean var2) {
      byte var11 = 0;
      int var10;
      if (var0 == 0) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var4 = (byte)var10;
      if (var0 == 1) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var5 = (byte)var10;
      if (var0 == 2) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var6 = (byte)var10;
      if (var0 == 3) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var8 = (byte)var10;
      if (var0 == 4) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var7 = (byte)var10;
      if (var0 == 5) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var9 = (byte)var10;
      if (var0 == 6) {
         var10 = changeAir(0, var1, var2);
      } else {
         var10 = 0;
      }

      byte var3 = (byte)var10;
      var10 = var11;
      if (var0 == 7) {
         var10 = changeAir(0, var1, var2);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 1, 24, -1, -61, 25, 0, var4, var5, var6, var8, var7, var9, var3, (byte)var10});
   }

   public static void showCommonSwitch(int var0, int var1, boolean var2) {
      int[] var11 = mCommonSwitchs;
      int var10;
      if (var0 == 0) {
         var10 = change(var11[0], var1, var2, var0);
      } else {
         var10 = var11[0];
      }

      byte var3 = (byte)var10;
      var11 = mCommonSwitchs;
      if (var0 == 1) {
         var10 = change(var11[1], var1, var2, var0);
      } else {
         var10 = var11[1];
      }

      byte var6 = (byte)var10;
      var11 = mCommonSwitchs;
      if (var0 == 2) {
         var10 = change(var11[2], var1, var2, var0);
      } else {
         var10 = var11[2];
      }

      byte var9 = (byte)var10;
      var11 = mCommonSwitchs;
      if (var0 == 3) {
         var10 = change(var11[3], var1, var2, var0);
      } else {
         var10 = var11[3];
      }

      byte var8 = (byte)var10;
      var11 = mCommonSwitchs;
      if (var0 == 4) {
         var10 = change(var11[4], var1, var2, var0);
      } else {
         var10 = var11[4];
      }

      byte var7 = (byte)var10;
      var11 = mCommonSwitchs;
      if (var0 == 5) {
         var10 = change(var11[5], var1, var2, var0);
      } else {
         var10 = var11[5];
      }

      byte var4 = (byte)var10;
      var11 = mCommonSwitchs;
      if (var0 == 6) {
         var10 = change(var11[6], var1, var2, var0);
      } else {
         var10 = var11[6];
      }

      byte var5 = (byte)var10;
      if (var0 == 7) {
         var0 = change(mCommonSwitchs[7], var1, var2, var0);
      } else {
         var0 = mCommonSwitchs[7];
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 1, 24, -2, -87, 23, 0, var3, var6, var9, var8, var7, var4, var5, (byte)var0});
   }
}
