package com.hzbhd.canbus.car._320;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private int[] m0x26Radar;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private boolean mFrontStatus;
   private boolean mHandBrakeUp;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private boolean mSeatBeltTie;

   private boolean is0x26DataChange() {
      if (Arrays.equals(this.m0x26Radar, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x26Radar = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDateChage() {
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

   private boolean isDoorDateChage1() {
      if (this.mSeatBeltTie == GeneralDoorData.isSeatMasterDriverBeltTie && this.mHandBrakeUp == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mSeatBeltTie = GeneralDoorData.isSeatMasterDriverBeltTie;
         this.mHandBrakeUp = GeneralDoorData.isHandBrakeUp;
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 30) {
         var2 = "HI";
      } else if (var1 > 0 && var1 < 30) {
         var2 = (float)var1 / 2.0F + 17.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      Log.i("cbc", "value is: " + var1);
      return var2;
   }

   private String resolveOutDoorTemp() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var2 = "-" + (127 - var1);
      } else {
         var2 = var1 + "";
      }

      return var2 + this.getTempUnitC(this.mContext);
   }

   private String resolveWaterTemp() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var2 = "-" + (127 - var1);
      } else {
         var2 = var1 + "";
      }

      return var2 + this.getTempUnitC(this.mContext);
   }

   private void set0x12AirData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         int var2 = this.mCanBusInfoInt[3];
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 5) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
         GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x21WheelData() {
      int var1 = this.mKeyStatus;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.mKeyStatus = var2;
      }

      switch (var2) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(21);
            break;
         case 4:
            this.realKeyClick(20);
         case 5:
         case 8:
         default:
            break;
         case 6:
            this.realKeyClick(184);
            break;
         case 7:
            this.realKeyClick(2);
            break;
         case 9:
            this.realKeyClick(14);
            break;
         case 10:
            this.realKeyClick(15);
            break;
         case 11:
            this.realKeyClick(187);
      }

   }

   private void set0x26RadarInfo(Context var1) {
      if (this.is0x26DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x28BaseData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isDoorDateChage()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x29TrackDate() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 7744, 13449, 16);
         this.updateParkUi((Bundle)null, this.mContext);
         this.updateSpeedInfo(this.mCanBusInfoInt[5]);
      }

   }

   private void set0x40CarInfo(Context var1) {
      GeneralDoorData.isShowMasterDriverSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (this.isDoorDateChage1()) {
         this.updateDoorView(var1);
      }

      this.updateOutDoorTemp(var1, this.resolveOutDoorTemp());
      ArrayList var10 = new ArrayList();
      var10.add(new DriverUpdateEntity(0, 0, this.resolveWaterTemp()));
      int[] var11 = this.mCanBusInfoInt;
      int var5 = var11[5];
      int var2 = var11[6];
      int var6 = var11[7];
      int var4 = var11[8];
      int var9 = var11[9];
      int var3 = var11[10];
      int var7 = var11[11];
      int var8 = var11[12];
      var10.add(new DriverUpdateEntity(0, 1, (var2 << 8 | var6) + " RPM"));
      var10.add(new DriverUpdateEntity(0, 2, (var4 << 8 | var9) + " KM"));
      var10.add(new DriverUpdateEntity(0, 3, (var8 | var7 << 8 | var3 << 16) + " KM"));
      var10.add(new DriverUpdateEntity(0, 4, var5 + " KM/H"));
      this.updateGeneralDriveData(var10);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x7fVersionDate() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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
      if (var3 != 33) {
         if (var3 != 35) {
            if (var3 != 38) {
               if (var3 != 64) {
                  if (var3 != 127) {
                     if (var3 != 40) {
                        if (var3 == 41) {
                           this.set0x29TrackDate();
                        }
                     } else {
                        this.set0x28BaseData(var1);
                     }
                  } else {
                     this.set0x7fVersionDate();
                  }
               } else {
                  this.set0x40CarInfo(var1);
               }
            } else {
               this.set0x26RadarInfo(var1);
            }
         } else {
            this.set0x12AirData(var1);
         }
      } else {
         this.set0x21WheelData();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
