package com.hzbhd.canbus.car._104;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private final String TAG = "_1104_MsgMgt";
   private byte[] m0x04Datas = new byte[7];
   private byte[] m0x07Datas = new byte[5];
   private int[] m0x1CData;
   private int[] m0x24Data;
   private boolean mBackStatus;
   private SparseArray mCanbusDataArray = new SparseArray();
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private String mDistanceUnit = "KM";
   private boolean mFrontStatus;
   private String mFuelUnit = "L/100KM";
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private String mTemperatureUnit = "℃";

   private boolean is0x1CDataChange() {
      if (Arrays.equals(this.m0x1CData, this.mCanbusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanbusInfoInt;
         this.m0x1CData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x24DataChange() {
      if (Arrays.equals(this.m0x24Data, this.mCanbusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanbusInfoInt;
         this.m0x24Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         return true;
      }
   }

   private void set0x03VehicleInfo(Context var1) {
      int[] var9 = this.mCanbusInfoInt;
      int var8 = var9[2] << 8 | var9[3];
      int var5 = var9[4];
      int var6 = var9[5];
      int var7 = var9[6];
      int var2 = var9[7];
      byte var3 = this.mCanbusInfoByte[8];
      int var4 = var9[9];
      String var10;
      if (this.mTemperatureUnit.equals(this.getTempUnitC(var1))) {
         var10 = (float)var8 * 0.5F + this.mTemperatureUnit;
      } else {
         var10 = var8 + " " + this.mTemperatureUnit;
      }

      ArrayList var11 = new ArrayList();
      var11.add(new DriverUpdateEntity(0, 0, var10));
      var11.add(new DriverUpdateEntity(0, 1, (var5 << 8 | var6) + " " + this.mDistanceUnit));
      var11.add(new DriverUpdateEntity(0, 2, (float)(var7 << 8 | var2) / 10.0F + " " + this.mDistanceUnit + "/h"));
      var11.add(new DriverUpdateEntity(0, 3, (float)(var4 | var3 << 8) / 10.0F + " " + this.mFuelUnit));
      this.updateGeneralDriveData(var11);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x04SystemInfoDate(Context var1) {
      byte[] var3 = this.mCanbusInfoByte;
      this.m0x04Datas = Arrays.copyOf(var3, var3.length);
      if (this.isDataChange(this.mCanbusInfoInt)) {
         int[] var5 = this.mCanbusInfoInt;
         int var2 = var5[2];
         if (var2 != 0) {
            if (var2 == 1) {
               this.mDistanceUnit = "mls";
            }
         } else {
            this.mDistanceUnit = "km";
         }

         var2 = var5[4];
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3) {
                     this.mFuelUnit = "km/l";
                  }
               } else {
                  this.mFuelUnit = "mpg(uk)";
               }
            } else {
               this.mFuelUnit = "mpg(us)";
            }
         } else {
            this.mFuelUnit = "l/100km";
         }

         var2 = var5[5];
         if (var2 != 0) {
            if (var2 == 1) {
               this.mTemperatureUnit = this.getTempUnitF(var1);
            }
         } else {
            this.mTemperatureUnit = this.getTempUnitC(var1);
         }

         ArrayList var4 = new ArrayList();
         var4.add(new SettingUpdateEntity(0, 0, this.mCanbusInfoInt[2]));
         var4.add(new SettingUpdateEntity(0, 1, this.mCanbusInfoInt[3]));
         var4.add(new SettingUpdateEntity(0, 2, this.mCanbusInfoInt[4]));
         var4.add(new SettingUpdateEntity(0, 3, this.mCanbusInfoInt[5]));
         var4.add(new SettingUpdateEntity(0, 4, this.mCanbusInfoInt[6]));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x06WheelKeyData(Context var1) {
      int[] var3 = this.mCanbusInfoInt;
      short var2 = 2;
      switch (var3[2]) {
         case 1:
            var2 = 7;
            break;
         case 2:
            var2 = 8;
         case 3:
            break;
         case 4:
            var2 = 49;
            break;
         case 5:
            var2 = 46;
            break;
         case 6:
            var2 = 45;
            break;
         case 7:
            var2 = 187;
            break;
         case 8:
            var2 = 14;
            break;
         default:
            var2 = 0;
      }

      this.realKeyLongClick1(var1, var2, var3[3]);
   }

   private void set0x07ControlInfo(Context var1) {
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 4);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 4);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 1);
      String var5 = "close";
      String var8;
      if (var4 == 0) {
         var8 = "close";
      } else {
         var8 = "_18_level_" + var4;
      }

      if (var2 != 0) {
         var5 = "_18_level_" + var2;
      }

      String var6;
      if (var3 == 0) {
         var6 = "_1104_radar_close";
      } else {
         var6 = "_1104_radar_open";
      }

      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(3, 0, var8));
      var7.add(new SettingUpdateEntity(3, 1, var5));
      var7.add(new SettingUpdateEntity(1, 0, var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x08DoorInfo(Context var1) {
      Log.i("_1104_MsgMgt", "set0x08DoorInfo: in");
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x1CRadarDate(Context var1) {
      if (this.is0x1CDataChange()) {
         int[] var2 = this.mCanbusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanbusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var2[6], var2[7], var2[8], var2[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24TrackData(Context var1) {
      if (this.is0x24DataChange()) {
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanbusInfoByte[2], (byte)0, 0, 50, 8);
         Log.i("_1104_MsgMgt", "set0x24TrackData: GeneralParkData.trackAngle <--> " + GeneralParkData.trackAngle);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void setBackLight() {
      int var1 = this.mCanbusInfoInt[2];
      if (var1 != 128) {
         if (DataHandleUtils.getBoolBit7(var1)) {
            this.setBacklightLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 7));
         }

      }
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanbusInfoInt = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoByte = var2;
      byte var3 = var2[1];
      if (var3 != 3) {
         if (var3 != 4) {
            if (var3 != 6) {
               if (var3 != 7) {
                  if (var3 != 8) {
                     if (var3 != 20) {
                        if (var3 != 28) {
                           if (var3 == 36) {
                              this.set0x24TrackData(var1);
                           }
                        } else {
                           this.set0x1CRadarDate(var1);
                        }
                     } else {
                        this.setBackLight();
                     }
                  } else {
                     this.set0x08DoorInfo(var1);
                  }
               } else {
                  this.set0x07ControlInfo(var1);
               }
            } else {
               this.set0x06WheelKeyData(var1);
            }
         } else {
            this.set0x04SystemInfoDate(var1);
         }
      } else {
         this.set0x03VehicleInfo(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var8, (byte)var6, (byte)var4, (byte)var3, (byte)var2});
   }

   byte[] get0x04Datas() {
      return this.m0x04Datas;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   void updateSettingsItem(int var1, int var2, Object var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
