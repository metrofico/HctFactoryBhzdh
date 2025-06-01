package com.hzbhd.canbus.car._179;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private String mAirUnit;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private boolean mFrontStatus;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 31) {
         return "HI";
      } else if (var1 >= 1 && var1 <= 17) {
         float var2 = (float)(var1 + 35);
         return this.mDecimalFormat0p0.format((double)(var2 / 2.0F)) + this.getTempUnitC(this.mContext);
      } else {
         return "";
      }
   }

   private String resolveOutdoorTemperature(int var1) {
      return this.mCanBusInfoInt[6] == 240 ? DataHandleUtils.rangeNumber(var1, 127) - 40 + this.mAirUnit : this.mDecimalFormat0p0.format((double)((float)((DataHandleUtils.rangeNumber(var1, 127) - 40) * 9) / 5.0F + 32.0F)) + this.mAirUnit;
   }

   private void set0X30VersionData() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x20WheelKey() {
      int var2 = this.mKeyStatus;
      int var1 = this.mCanBusInfoInt[2];
      if (var2 != var1) {
         this.mKeyStatus = var1;
      }

      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 5) {
                  this.realKeyClick(14);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void set0x21AirData(Context var1) {
      if (this.mCanBusInfoInt[6] == 240) {
         this.mAirUnit = this.getTempUnitC(var1);
      } else {
         this.mAirUnit = this.getTempUnitF(var1);
      }

      this.updateOutDoorTemp(var1, this.resolveOutdoorTemperature(this.mCanBusInfoInt[5]));
      byte[] var2 = this.mCanBusInfoByte;
      var2[3] = (byte)(var2[3] & 239);
      var2[5] = 0;
      if (!this.isAirMsgRepeat(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         this.updateAirActivity(var1, 1001);
      }
   }

   private void set0x24BaseData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x26TrackData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var1 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)var1[2], (byte)var1[3], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 33) {
            if (var3 != 36) {
               if (var3 != 38) {
                  if (var3 != 48) {
                     return;
                  }
               } else {
                  this.set0x26TrackData();
               }

               this.set0X30VersionData();
            } else {
               this.set0x24BaseData(var1);
            }
         } else {
            this.set0x21AirData(var1);
         }
      } else {
         this.set0x20WheelKey();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
