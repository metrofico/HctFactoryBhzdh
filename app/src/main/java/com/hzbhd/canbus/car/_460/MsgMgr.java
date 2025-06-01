package com.hzbhd.canbus.car._460;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   Context context;
   CanDocking docking;

   private void set0x191EspInfo(int[] var1) {
      int var2 = (int)(((double)DataHandleUtils.getMsbLsbResult(var1[7], var1[8]) * 0.1 - 780.0) / 30.0);
      Log.d("fxHou新特转角", "mCanBusInfoInt[7]=" + var1[7]);
      Log.d("fxHou新特转角", "mCanBusInfoInt[8]=" + var1[8]);
      Log.d("fxHou新特转角", "result=" + var2);
      GeneralParkData.trackAngle = var2;
      this.updateParkUi((Bundle)null, this.context);
   }

   private void set0x3a1Info(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[10], 0, 3);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[10], 3, 3);
      Log.d("fxHou新特雷达", "mCanBusInfoInt[10]=" + var1[10]);
      Log.d("fxHou新特雷达", "left=" + var2);
      Log.d("fxHou新特雷达", "right=" + var3);
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(4, var2, 0, 0, var3);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.context);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.context = var1;
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.afterServiceNormalSetting(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.docking.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      if (this.getMsDataType(var3) == 401) {
         this.set0x191EspInfo(var3);
      }

      if (this.getMsDataType(var3) == 929) {
         this.set0x3a1Info(var3);
      }

   }

   protected int getMsDataType(int[] var1) {
      int var3 = var1[2];
      int var2 = var1[3];
      int var4 = var1[4];
      return var1[5] & 255 | (var3 & 255) << 24 | (var2 & 255) << 16 | (var4 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.initCommand(var1);
   }
}
