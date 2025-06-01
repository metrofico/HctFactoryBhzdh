package com.hzbhd.canbus.car._228;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   String ResultTemp;
   int differentId;
   private int eachId;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private byte mFreqHi;
   private byte mFreqLo;
   int[] mFrontRadarData;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   int[] mRearRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private UiMgr uiMgr;

   private void VersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private byte getAllBandTypeData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 16;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.mFreqHi = (byte)(var3 >> 8);
            this.mFreqLo = (byte)(var3 & 255);
         }
      } else {
         this.mFreqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.mFreqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         this.ResultTemp = "LOW";
      } else if (var1 == 31) {
         this.ResultTemp = "HIGH";
      } else {
         this.ResultTemp = (double)((float)var1 / 2.0F) + 15.5 + this.getTempUnitC(this.mContext);
      }

      return this.ResultTemp;
   }

   private String resolveBackLight(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131763014);
      } else if (var1 == 227) {
         var2 = this.mContext.getResources().getString(2131759539);
      } else if (var1 == 22) {
         var2 = this.mContext.getResources().getString(2131759538);
      } else {
         var2 = var1 + "";
      }

      return var2;
   }

   private String resolveBackLightState(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131763014);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131763005);
      } else {
         var2 = null;
      }

      return var2;
   }

   private String resolveCar(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131759530);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131759531);
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131759532);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131759533);
      } else {
         var2 = null;
      }

      return var2;
   }

   private String resolveOutDoorTem(int var1) {
      return (float)var1 / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private String resolveTempOfLeft(int var1) {
      int var2 = var1;
      if (this.eachId == 4) {
         var2 = var1;
         if (var1 > 9) {
            var2 = 9;
         }
      }

      return var2 + this.mContext.getResources().getString(2131769099);
   }

   private void setAirInfo0x21() {
      int[] var8 = this.mCanBusInfoInt;
      int var1 = var8[2];
      int var2 = var8[3];
      int var7 = var8[4];
      int var3 = var8[5];
      int var4 = var8[6];
      int var5 = var8[7];
      int var6 = var8[8];
      GeneralAirData.power = DataHandleUtils.getBoolBit7(var1);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1);
      GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(var1);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(var1);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit0(var1);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var2);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var2);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var2);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var2);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var2);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var2);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var2, 0, 4);
      GeneralAirData.front_left_temperature = this.resolveTempOfLeft(var7);
      GeneralAirData.front_right_temperature = this.resolveAirTemperature(var3);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(var4);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var4);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var4);
      GeneralAirData.eco = DataHandleUtils.getBoolBit4(var4);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(var4);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(var4);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var5, 4, 3);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var5, 0, 3);
      GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(var6, 0, 2);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setBackLightInfo0x14() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_228_driveInfo_1"), this.resolveBackLightState(this.mCanBusInfoInt[2])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_228_driveInfo_0"), this.resolveBackLight(this.mCanBusInfoInt[3])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarTypeInfo0x34() {
      (new ArrayList()).add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_228_car_type"), this.resolveCar(this.mCanBusInfoInt[2])));
   }

   private void setDoorInfo0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadarInfo0x22() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setPanoramicInfo0x33() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_228_setting_0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_228_setting_0", "_228_initial_perspective"), this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadarInfo0x22() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setTrackData0x29() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 10000, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setWheelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 32) {
         if (var1 != 33) {
            switch (var1) {
               case 0:
                  this.buttonKey(0);
                  break;
               case 1:
                  this.buttonKey(7);
                  break;
               case 2:
                  this.buttonKey(8);
                  break;
               case 3:
                  this.buttonKey(20);
                  break;
               case 4:
                  this.buttonKey(21);
                  break;
               case 5:
                  var1 = this.eachId;
                  if (var1 == 1 || var1 == 6) {
                     this.buttonKey(188);
                  }

                  var1 = this.eachId;
                  if (var1 == 2 || var1 == 7 || var1 == 4) {
                     this.buttonKey(14);
                  }
                  break;
               case 6:
                  this.buttonKey(15);
                  break;
               case 7:
                  this.buttonKey(2);
                  break;
               case 8:
                  this.buttonKey(7);
                  break;
               case 9:
                  this.buttonKey(8);
                  break;
               case 10:
                  this.buttonKey(1);
                  break;
               default:
                  switch (var1) {
                     case 22:
                        this.buttonKey(3);
                        break;
                     case 23:
                        this.buttonKey(52);
                        break;
                     case 24:
                        this.buttonKey(50);
                        break;
                     case 25:
                        this.buttonKey(128);
                  }
            }
         } else {
            this.buttonKey(2);
         }
      } else {
         this.buttonKey(57);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 41) {
            if (var3 != 48) {
               if (var3 != 51) {
                  if (var3 != 52) {
                     switch (var3) {
                        case 32:
                           this.setWheelKeyInfo0x20();
                           break;
                        case 33:
                           var3 = this.eachId;
                           if (var3 != 3 && var3 != 5 && var3 != 4) {
                              return;
                           }

                           this.setAirInfo0x21();
                           break;
                        case 34:
                           var3 = this.eachId;
                           if (var3 != 6 && var3 != 7) {
                              return;
                           }

                           this.setRearRadarInfo0x22();
                           break;
                        case 35:
                           if (this.eachId != 6) {
                              return;
                           }

                           this.setFrontRadarInfo0x22();
                           break;
                        case 36:
                           this.setDoorInfo0x24();
                     }
                  } else {
                     if (this.eachId != 5) {
                        return;
                     }

                     this.setCarTypeInfo0x34();
                  }
               } else {
                  if (this.eachId != 7) {
                     return;
                  }

                  this.setPanoramicInfo0x33();
               }
            } else {
               this.VersionInfo0x30();
            }
         } else {
            var3 = this.eachId;
            if (var3 == 3 || var3 == 5 || var3 == 7) {
               return;
            }

            this.setTrackData0x29();
         }
      } else {
         this.setBackLightInfo0x14();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var1 = (byte)(var4 & 255);
         var2 = (byte)(var4 >> 8 & 255);
         var5 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, var1, var2, var5, var9, var6, var7});
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.mContext != null) {
         byte var6 = this.getAllBandTypeData(var2);
         this.getFreqByteHiLo(var2, var3);
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, (byte)var6, this.mFreqLo, this.mFreqHi, (byte)var1, 0, 0});
      }
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
      }
   }
}
