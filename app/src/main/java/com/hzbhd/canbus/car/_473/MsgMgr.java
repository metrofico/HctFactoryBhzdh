package com.hzbhd.canbus.car._473;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Unit;

public class MsgMgr extends AbstractMsgMgr {
   static final boolean $assertionsDisabled = false;
   CanDocking docking;
   Context mContext;
   private UiMgr mUiMgr;
   private SimpleDateFormat simpleDateFormat1;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   // $FF: synthetic method
   static Unit lambda$set0x560ParkingSensorInfo$1(int[] var0) {
      if (LogUtil.log5()) {
         LogUtil.d("set0x560ParkingSensorInfo(): +++" + var0[11] + "----" + var0[12]);
      }

      if (LogUtil.log5()) {
         LogUtil.d("set0x560ParkingSensorInfo(): +++" + var0[13] + "----" + var0[14]);
      }

      MdRadarData.reverse_left_rear_vertical = var0[11];
      MdRadarData.reverse_left_rear_horizontal = var0[12];
      MdRadarData.reverse_right_rear_vertical = var0[13];
      MdRadarData.reverse_right_rear_horizontal = var0[14];
      return null;
   }

   private void setTime0x318(int[] var1) {
      if (this.simpleDateFormat1 == null) {
         this.simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
      }

      int var2 = var1[12];
      boolean var8 = false;
      int var6 = DataHandleUtils.getIntFromByteWithBit(var2, 0, 5);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[13], 0, 4);
      int var7 = DataHandleUtils.getIntFromByteWithBit(var1[9], 1, 5);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[10], 3, 5);
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[11], 0, 6);
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[7], 1, 1);
      if (LogUtil.log5()) {
         LogUtil.d("setTime0x318(): -------" + var4);
      }

      SendKeyManager var11 = SendKeyManager.getInstance();
      if (var4 != 0) {
         var8 = true;
      }

      var11.setAppMute(1, var8);

      try {
         SimpleDateFormat var12 = this.simpleDateFormat1;
         StringBuilder var9 = new StringBuilder();
         SystemClock.setCurrentTimeMillis(var12.parse(var9.append(var6 + 2014).append("-").append(var3).append("-").append(var7).append(" ").append(var5).append(":").append(var2).toString()).getTime());
      } catch (ParseException var10) {
         Log.e("TIME_SYNC_ERROR", var10.toString());
         var10.printStackTrace();
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.afterServiceNormalSetting(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.docking.canbusInfoChange(var1, var2);
      BaseUtil.INSTANCE.runUi(new MsgMgr$$ExternalSyntheticLambda1(this, var2));
   }

   protected int getMsDataType(int[] var1) {
      int var4 = var1[2];
      int var3 = var1[3];
      int var2 = var1[4];
      return var1[5] & 255 | (var4 & 255) << 24 | (var3 & 255) << 16 | (var2 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.initCommand(var1);
   }

   // $FF: synthetic method
   Unit lambda$canbusInfoChange$0$com_hzbhd_canbus_car__473_MsgMgr(byte[] var1) {
      int[] var3 = this.getByteArrayToIntArray(var1);
      if (LogUtil.log5()) {
         LogUtil.d("canbusInfoChange(): " + this.getMsDataType(var3));
      }

      int var2 = this.getMsDataType(var3);
      if (var2 != 792) {
         if (var2 == 1376) {
            this.set0x560ParkingSensorInfo(var3);
         }
      } else {
         this.setTime0x318(var3);
      }

      this.getUiMgr(this.mContext).refreshRadar();
      return null;
   }

   public void set0x560ParkingSensorInfo(int[] var1) {
      BaseUtil.INSTANCE.runUi(new MsgMgr$$ExternalSyntheticLambda0(var1));
   }
}
