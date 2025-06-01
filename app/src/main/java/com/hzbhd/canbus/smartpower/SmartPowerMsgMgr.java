package com.hzbhd.canbus.smartpower;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.utils.ConfigUtil;

public class SmartPowerMsgMgr {
   private static SmartPowerMsgMgr instance;
   private int data3;
   private int data4;
   private int data5;
   private int data6;
   private int data7;
   private int[] drivingMode = new int[]{2131768177, 2131768178, 2131768174, 2131768176, 2131768175};
   private int mode;
   private int mode_int;
   private int version;

   private int[] getByteArrayToIntArray(byte[] var1) {
      int[] var4 = new int[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         byte var3 = var1[var2];
         if ((var3 & 128) == 0) {
            var4[var2] = var3;
         } else {
            var4[var2] = var3 & 255;
         }
      }

      return var4;
   }

   public static SmartPowerMsgMgr getInstance() {
      if (ConfigUtil.getDeviceId().contains("DZ")) {
         if (instance == null) {
            instance = new SmartPowerMsgMgr();
         }

         return instance;
      } else {
         return null;
      }
   }

   private int getModeInt(int[] var1) {
      if (var1.length <= 4) {
         return GeneralDzSmartData.mode;
      } else {
         int var4 = var1[1];
         int var2 = var1[2];
         byte var3 = 0;
         if (var4 == 3 || var4 == 4) {
            var2 = var1[3];
         }

         if (var4 == 3 || var4 == 0) {
            var3 = 4;
         }

         return DataHandleUtils.getIntFromByteWithBit(var2, var3, 4) - 1;
      }
   }

   private void updateSystemUIDrivingPattern(Context var1, int var2) {
      if (var2 >= 0 && var2 <= 4) {
         Intent var3 = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
         var3.putExtra("_283_car_mode", var1.getString(this.drivingMode[var2]));
         var1.sendBroadcast(var3);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2, OnRefreshUiListener var3) {
      if (var1 != null) {
         if (var2[0] == 87) {
            int[] var4 = this.getByteArrayToIntArray(var2);
            if (var4.length >= 7) {
               GeneralDzSmartData.mode = var4[1];
               GeneralDzSmartData.mode_int = this.getModeInt(var4);
               GeneralDzSmartData.data3 = var4[2];
               GeneralDzSmartData.data4 = var4[3];
               GeneralDzSmartData.data5 = var4[4];
               GeneralDzSmartData.data6 = var4[5];
               GeneralDzSmartData.data7 = var4[6];
               GeneralDzSmartData.version = var4[7];
               GeneralDzSmartData.isHasData = true;
               if (this.mode != GeneralDzSmartData.mode || this.mode_int != GeneralDzSmartData.mode_int || this.data3 != GeneralDzSmartData.data3 || this.data4 != GeneralDzSmartData.data4 || this.data5 != GeneralDzSmartData.data5 || this.data6 != GeneralDzSmartData.data6 || this.data7 != GeneralDzSmartData.data7 || this.version != GeneralDzSmartData.version) {
                  this.mode = GeneralDzSmartData.mode;
                  this.mode_int = GeneralDzSmartData.mode_int;
                  this.data3 = GeneralDzSmartData.data3;
                  this.data4 = GeneralDzSmartData.data4;
                  this.data5 = GeneralDzSmartData.data5;
                  this.data6 = GeneralDzSmartData.data6;
                  this.data7 = GeneralDzSmartData.data7;
                  this.version = GeneralDzSmartData.version;
                  GeneralDzSmartData.hardware_model = DataHandleUtils.getIntFromByteWithBit(this.data6, 6, 2);
                  GeneralDzSmartData.power_model = (this.data6 & 63) << 8 | this.data5;
                  GeneralDzSmartData.data7_0 = DataHandleUtils.getBoolBit0(this.data7);
                  GeneralDzSmartData.data7_1 = DataHandleUtils.getBoolBit1(this.data7);
                  GeneralDzSmartData.data7_2 = DataHandleUtils.getBoolBit2(this.data7);
                  GeneralDzSmartData.data7_3 = DataHandleUtils.getBoolBit3(this.data7);
                  GeneralDzSmartData.data7_4 = DataHandleUtils.getBoolBit4(this.data7);
                  this.updateSystemUIDrivingPattern(var1, GeneralDzSmartData.mode);
                  if (var3 != null) {
                     var3.onRefreshUi();
                  }
               }
            }
         }

      }
   }

   public interface OnRefreshUiListener {
      void onRefreshUi();
   }
}
