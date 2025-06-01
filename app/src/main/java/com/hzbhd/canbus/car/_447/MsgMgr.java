package com.hzbhd.canbus.car._447;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.commontools.utils.FgeString;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr implements RadarUartDevice.IUartDataReport {
   private Context mContext;
   int[] mFrontRadarData;
   int[] mRearRadarData;
   private UiMgr mUiMgr;

   private int getShowLevel(byte var1) {
      var1 &= 255;
      if (var1 <= 30) {
         return 1;
      } else if (var1 <= 60) {
         return 2;
      } else if (var1 <= 100) {
         return 3;
      } else {
         return var1 <= 150 ? 4 : 0;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setFrontRadar0x23(byte[] var1, Context var2) {
      if (this.isFrontRadarDataChange(this.getByteArrayToIntArray(var1))) {
         ID447Data.frontLeftRange = var1[3];
         ID447Data.frontLeftMidRange = var1[4];
         ID447Data.frontRightMidRange = var1[5];
         ID447Data.frontRightRange = var1[6];
         this.updateId447Radar(var2);
      }

   }

   private void setRearRadar0x55(byte[] var1, Context var2) {
      if (this.isRearRadarDataChange(this.getByteArrayToIntArray(var1))) {
         ID447Data.rearLeftRange = this.getShowLevel(var1[1]);
         ID447Data.rearLeftMidRange = this.getShowLevel(var1[2]);
         ID447Data.rearRightMidRange = this.getShowLevel(var1[3]);
         ID447Data.rearRightRange = this.getShowLevel(var1[4]);
         this.updateId447Radar(var2);
      }

   }

   private void updateId447Radar(Context var1) {
      if (this.getUiMgr(var1).id447RadarView != null) {
         this.getUiMgr(var1).id447RadarView.updateRadar();
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
   }

   public void initCommand(Context var1) {
      this.mContext = var1;
      super.initCommand(var1);
      RadarUartDevice.getRadarUartDevice(this).start();
   }

   public void onRead(byte[] var1) {
      Log.d("onRead:", "uartdata:  " + FgeString.bytes2HexString(var1, var1.length));
      this.setRearRadar0x55(var1, this.mContext);
   }
}
