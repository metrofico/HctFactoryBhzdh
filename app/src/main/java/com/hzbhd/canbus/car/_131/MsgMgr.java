package com.hzbhd.canbus.car._131;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   int _0x33_data0;
   int _0x33_data1;
   private final String _131_BACK_OPEN = "_131_back_open";
   private final String _131_FRONT_OPEN = "_131_front_open";
   private final String _131_LEFT_FRONT_DOOR_OPEN = "_131_left_front_door_open";
   private final String _131_LEFT_REAR_DOOR_OPEN = "_131_left_rear_door_open";
   private final String _131_RIGHT_FRONT_DOOR_OPEN = "_131_right_front_door_open";
   private final String _131_RIGHT_REAR_DOOR_OPEN = "_131_right_rear_door_open";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "_131_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_131_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_131_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_131_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_131_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "_131_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private void parkingStatus() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1 = "open";
      } else {
         var1 = "close";
      }

      var2.add(new DriverUpdateEntity(0, 2, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x20WheelKeyInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 16) {
         if (var1 != 17) {
            switch (var1) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var2[3]);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 7, var2[3]);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 8, var2[3]);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 20, var2[3]);
                  break;
               case 4:
                  this.realKeyLongClick1(this.mContext, 21, var2[3]);
                  break;
               case 5:
                  this.realKeyLongClick1(this.mContext, 14, var2[3]);
                  break;
               case 6:
                  this.realKeyLongClick1(this.mContext, 3, var2[3]);
                  break;
               default:
                  switch (var1) {
                     case 8:
                        this.realKeyLongClick1(this.mContext, 187, var2[3]);
                        break;
                     case 9:
                        this.realKeyLongClick1(this.mContext, 14, var2[3]);
                        break;
                     case 10:
                        this.realKeyLongClick1(this.mContext, 15, var2[3]);
                        break;
                     default:
                        switch (var1) {
                           case 19:
                              this.realKeyLongClick1(this.mContext, 45, var2[3]);
                              break;
                           case 20:
                              this.realKeyLongClick1(this.mContext, 46, var2[3]);
                              break;
                           case 21:
                              this.realKeyLongClick1(this.mContext, 50, var2[3]);
                              break;
                           case 22:
                              this.realKeyLongClick1(this.mContext, 49, var2[3]);
                              break;
                           default:
                              switch (var1) {
                                 case 129:
                                    this.realKeyLongClick1(this.mContext, 58, var2[3]);
                                    break;
                                 case 130:
                                    this.realKeyLongClick1(this.mContext, 128, var2[3]);
                                    break;
                                 case 131:
                                    this.realKeyLongClick1(this.mContext, 4, var2[3]);
                                    break;
                                 case 132:
                                    this.realKeyLongClick1(this.mContext, 7, var2[3]);
                                    break;
                                 case 133:
                                    this.realKeyLongClick1(this.mContext, 8, var2[3]);
                              }
                        }
                  }
            }
         } else {
            this.realKeyLongClick1(this.mContext, 48, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 47, var2[3]);
      }

   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "_131_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_131_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_131_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_131_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_131_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "_131_front_open", GeneralDoorData.isFrontOpen);
         this.updateDoorView(this.mContext);
      }

      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var1 = "Not P";
      } else {
         var1 = "P";
      }

      var2.add(new DriverUpdateEntity(0, 0, var1));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(0, 1, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void settingInfo() {
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
      byte var4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      int var1 = this.mCanBusInfoInt[2];
      byte var2 = 0;
      int var7 = DataHandleUtils.getIntFromByteWithBit(var1, 0, 2);
      int var6 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      ArrayList var8 = new ArrayList();

      for(var1 = 0; var1 < 5; ++var1) {
         var8.add(new SettingUpdateEntity(0, var1, (new int[]{var5, var4, var3, var7, var6})[var1]));
      }

      var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
      var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2);
      var4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
      var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3);

      for(var1 = 0; var1 < 5; ++var1) {
         var8.add(new SettingUpdateEntity(1, var1, (new int[]{var7, var6, var4, var5, var3})[var1]));
      }

      var4 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);

      for(var1 = var2; var1 < 2; ++var1) {
         var8.add(new SettingUpdateEntity(2, var1, (new int[]{var4, var3})[var1]));
      }

      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void settingTips() {
      int[] var1 = this.mCanBusInfoInt;
      this._0x33_data0 = var1[2];
      this._0x33_data1 = var1[3];
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 48) {
            if (var3 != 50) {
               if (var3 != 51) {
                  switch (var3) {
                     case 34:
                        this.setRearRadarInfo();
                        break;
                     case 35:
                        this.setFrontRadarInfo();
                        break;
                     case 36:
                        if (this.isDoorMsgRepeat(var2)) {
                           return;
                        }

                        this.setDoorData0x24();
                        break;
                     case 37:
                        this.parkingStatus();
                  }
               } else {
                  this.settingTips();
               }
            } else {
               this.settingInfo();
            }
         } else {
            this.setVersionInfo();
         }
      } else {
         this.set0x20WheelKeyInfo();
      }

   }
}
