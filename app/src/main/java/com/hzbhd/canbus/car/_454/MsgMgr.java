package com.hzbhd.canbus.car._454;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private String[] arr0 = new String[1];
   private String[] arr1 = new String[1];
   private String[] arr2 = new String[1];
   private String[] arr3 = new String[1];
   private int differentId;
   private int eachId;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   int[] mCarDiveData;
   int[] mCarDoorData;
   private Context mContext;
   int[] mRadarData;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private List tyreInfoList = new ArrayList();

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getSwitchState(boolean var1) {
      return var1 ? "ON" : "OFF";
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDriveInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDiveData, var1)) {
         return false;
      } else {
         this.mCarDiveData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRadarData, var1)) {
         return false;
      } else {
         this.mRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var2 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var2, var2.length);
         return true;
      }
   }

   private void set0x03VersionInfo(byte[] var1) {
      this.updateVersionInfo(this.mContext, this.getVersionStr(var1));
   }

   private void set0x20Swc(int[] var1) {
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 188, var1[3]);
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 8:
            this.realKeyClick4(this.mContext, 48);
            break;
         case 9:
            this.realKeyClick4(this.mContext, 47);
            break;
         case 10:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 11:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 12:
            this.realKeyLongClick1(this.mContext, 47, var1[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 48, var1[3]);
         case 14:
         default:
            break;
         case 15:
            this.realKeyLongClick1(this.mContext, 49, var1[3]);
            break;
         case 16:
            this.realKeyLongClick1(this.mContext, 52, var1[3]);
            break;
         case 17:
            this.realKeyLongClick1(this.mContext, 151, var1[3]);
      }

   }

   private void set0x21DriveInfo(int[] var1) {
      if (this.isDriveInfoChange(var1)) {
         ArrayList var2 = new ArrayList();
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_speed"), var1[2] + "km/h"));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_0il_quantity"), var1[3] + ""));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_light_level"), var1[4] + ""));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Airbag_yellow"), this.getSwitchState(DataHandleUtils.getBoolBit0(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Airbag_red"), this.getSwitchState(DataHandleUtils.getBoolBit1(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Tire_pressure_yellow"), this.getSwitchState(DataHandleUtils.getBoolBit2(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Tire_pressure_red"), this.getSwitchState(DataHandleUtils.getBoolBit3(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Front_fog_lamp"), this.getSwitchState(DataHandleUtils.getBoolBit4(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Rear_fog_lamp"), this.getSwitchState(DataHandleUtils.getBoolBit5(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Left_turn"), this.getSwitchState(DataHandleUtils.getBoolBit6(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Right_turn"), this.getSwitchState(DataHandleUtils.getBoolBit7(var1[5]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_safety_belt"), this.getSwitchState(DataHandleUtils.getBoolBit0(var1[6]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_ESP"), this.getSwitchState(DataHandleUtils.getBoolBit1(var1[6]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_cruise_control"), this.getSwitchState(DataHandleUtils.getBoolBit2(var1[6]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_engine_green"), this.getSwitchState(DataHandleUtils.getBoolBit3(var1[6]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_engine_yellow"), this.getSwitchState(DataHandleUtils.getBoolBit4(var1[6]))));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x22Radar(int[] var1) {
      if (this.isRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         int var3 = var1[2];
         int var2 = var1[3];
         RadarInfoUtil.setRearRadarLocationData(5, var3, var2, var2, var1[4]);
         var3 = var1[5];
         var2 = var1[6];
         RadarInfoUtil.setFrontRadarLocationData(5, var3, var2, var2, var1[7]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23Esp(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         int var2 = var1[2];
         if (var2 >= 0 && var2 <= 32) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getLsb(var2), (byte)this.getMsb(var1[2]), 0, 32, 8);
            this.updateParkUi((Bundle)null, this.mContext);
         } else if (var2 >= 224 && var2 <= 255) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)this.getLsb(255 - var2), (byte)this.getMsb(255 - var1[2]), 0, 32, 8);
            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   private void set0x24Door(int[] var1) {
      if (this.isBasicInfoChange(var1) && DataHandleUtils.getBoolBit0(var1[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x34DriveInfo(int[] var1) {
      ArrayList var7 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer0");
      int var4 = var1[2];
      String var6 = "--";
      String var5;
      if (var4 != 255 && var1[3] != 255) {
         var5 = var1[2] + ":" + var1[3];
      } else {
         var5 = "--";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer1");
      var5 = var6;
      if (var1[4] != 255) {
         var5 = var6;
         if (var1[5] != 255) {
            if (var1[6] == 255) {
               var5 = var6;
            } else {
               var5 = DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) + ":" + var1[6];
            }
         }
      }

      var7.add(new DriverUpdateEntity(var3, var2, var5));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer2"), DataHandleUtils.getMsbLsbResult(var1[7], var1[8]) + "km"));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer3"), (float)DataHandleUtils.getMsbLsbResult(var1[9], var1[10]) / 10.0F + ""));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer4"), (float)DataHandleUtils.getMsbLsbResult(var1[11], var1[12]) / 10.0F + "km/h"));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x38Settings(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting0"), DataHandleUtils.getIntFromByteWithBit(var1[2], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting1"), DataHandleUtils.getIntFromByteWithBit(var1[2], 6, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting2"), DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting3"), DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting4"), DataHandleUtils.getIntFromByteWithBit(var1[2], 3, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting5"), DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting6"), DataHandleUtils.getIntFromByteWithBit(var1[2], 1, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting7"), DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting8"), DataHandleUtils.getIntFromByteWithBit(var1[3], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting9"), DataHandleUtils.getIntFromByteWithBit(var1[3], 5, 6)));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting10"), var1[4])).setProgress(var1[4]));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting11"), DataHandleUtils.getMsbLsbResult(var1[5], var1[6]))).setProgress(DataHandleUtils.getMsbLsbResult(var1[5], var1[6]) - 6));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting12"), var1[7]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDrive0x35(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive2"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive2_0"), DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) + ""));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive2"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive2_1"), (float)DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) / 10.0F + ""));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive2"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive2_2"), (float)DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) / 10.0F + ""));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTire0x36(int[] var1) {
      if (DataHandleUtils.getBoolBit7(var1[2])) {
         this.arr0[0] = this.mContext.getString(2131756592);
         this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
      } else {
         this.arr0[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
      }

      if (DataHandleUtils.getBoolBit6(var1[2])) {
         this.arr1[0] = this.mContext.getString(2131756592);
         this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
      } else {
         this.arr1[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      }

      if (DataHandleUtils.getBoolBit5(var1[2])) {
         this.arr2[0] = this.mContext.getString(2131756592);
         this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
      } else {
         this.arr2[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      }

      if (DataHandleUtils.getBoolBit4(var1[2])) {
         this.arr3[0] = this.mContext.getString(2131756592);
         this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
      } else {
         this.arr3[0] = this.mContext.getString(2131756593);
         this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      }

      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 3) {
         if (var3 != 56) {
            switch (var3) {
               case 32:
                  this.set0x20Swc(var4);
                  break;
               case 33:
                  this.set0x21DriveInfo(var4);
                  break;
               case 34:
                  this.set0x22Radar(var4);
                  break;
               case 35:
                  this.set0x23Esp(var4);
                  break;
               case 36:
                  this.set0x24Door(var4);
                  break;
               default:
                  switch (var3) {
                     case 52:
                        this.set0x34DriveInfo(var4);
                        break;
                     case 53:
                        this.setDrive0x35(var4);
                        break;
                     case 54:
                        this.setTire0x36(var4);
                  }
            }
         } else {
            this.set0x38Settings(var4);
         }
      } else {
         this.set0x03VersionInfo(var2);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      GeneralTireData.isHaveSpareTire = false;
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
