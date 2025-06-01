package com.hzbhd.canbus.car._373;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   LocationListener locationListener = new LocationListener(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLocationChanged(Location var1) {
         MyLog.temporaryTracking("指南针数据：onLocationChanged");
         if (var1 != null) {
            this.this$0.sndCurCompassSt(var1);
         }

      }

      public void onProviderDisabled(String var1) {
         MyLog.temporaryTracking("指南针数据：temporaryTracking");
      }

      public void onProviderEnabled(String var1) {
         MyLog.temporaryTracking("指南针数据：onProviderEnabled");
      }

      public void onStatusChanged(String var1, int var2, Bundle var3) {
         MyLog.temporaryTracking("指南针数据：onStatusChanged");
      }
   };
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   private LocationManager mLocationManager;
   int[] mPanoramicInfo;
   int[] mRearAirData;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;

   private String getAccState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? "Invalid Info" : "ACC ON";
            } else {
               return "ACC";
            }
         } else {
            return "ACC OFF";
         }
      } else {
         return "Key out";
      }
   }

   private int getCarCotrolRight(String var1) {
      return this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_373_setting_car_control", var1);
   }

   private String getCompassState(int var1) {
      if (var1 != 15) {
         switch (var1) {
            case 0:
               return this.mContext.getString(2131764510);
            case 1:
               return this.mContext.getString(2131764511);
            case 2:
               return this.mContext.getString(2131764512);
            case 3:
               return this.mContext.getString(2131764513);
            case 4:
               return this.mContext.getString(2131764514);
            case 5:
               return this.mContext.getString(2131764515);
            case 6:
               return this.mContext.getString(2131764516);
            case 7:
               return this.mContext.getString(2131764517);
            default:
               return "NO INFO";
         }
      } else {
         return this.mContext.getString(2131764518);
      }
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private int getOtherSettingRight(String var1) {
      return this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_373_other_seting", var1);
   }

   private int getSeatState(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 1) {
         return 1;
      } else {
         return var1 == 3 ? 2 : 0;
      }
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

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearAirDataNoChange() {
      if (Arrays.equals(this.mRearAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mRearAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return true;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void set0x01WheelKeyInfo() {
      switch (this.mCanBusInfoInt[2]) {
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
            this.buttonKey(45);
            break;
         case 4:
            this.buttonKey(46);
            break;
         case 5:
            this.buttonKey(2);
            break;
         case 6:
            this.buttonKey(62);
            break;
         case 7:
            this.buttonKey(187);
            break;
         case 8:
            this.buttonKey(14);
      }

   }

   private void set0x03SpeedInfo() {
      ArrayList var4 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data1");
      StringBuilder var5 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var3[2], var3[3])).append("Hm/h").toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var3[2], var3[3]));
   }

   private void set0x04WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            switch (var1) {
               case 4:
                  this.buttonKey(49);
                  break;
               case 5:
                  this.buttonKey(8);
                  break;
               case 6:
                  this.buttonKey(7);
                  break;
               case 7:
                  this.knobKey(21);
                  break;
               case 8:
                  this.knobKey(20);
                  break;
               case 9:
                  this.buttonKey(31);
            }
         } else {
            this.buttonKey(134);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void set0x05FrontAir() {
      this.updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[9] - 40 + this.getTempUnitC(this.mContext));
      this.mCanBusInfoInt[9] = 0;
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var1 = this.mCanBusInfoInt[5];
         String var3 = "HI";
         String var2;
         if (var1 == 0) {
            var2 = "LO";
         } else if (var1 == 127) {
            var2 = "HI";
         } else if (var1 > 59) {
            var2 = this.mCanBusInfoInt[5] + this.getTempUnitF(this.mContext);
         } else {
            var2 = this.mCanBusInfoInt[5] + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_left_temperature = var2;
         var1 = this.mCanBusInfoInt[6];
         if (var1 == 0) {
            var2 = "LO";
         } else if (var1 == 127) {
            var2 = var3;
         } else if (var1 > 59) {
            var2 = this.mCanBusInfoInt[6] + this.getTempUnitF(this.mContext);
         } else {
            var2 = this.mCanBusInfoInt[6] + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_right_temperature = var2;
         GeneralAirData.front_left_seat_heat_level = this.getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2));
         GeneralAirData.front_right_seat_heat_level = this.getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2));
         GeneralAirData.front_left_seat_cold_level = this.getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2));
         GeneralAirData.front_right_seat_cold_level = this.getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2));
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
         this.updateAirActivity(this.mContext, 1003);
      }
   }

   private void set0x06RearAir() {
      if (!this.isRearAirDataNoChange()) {
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         int var1 = this.mCanBusInfoInt[4];
         String var2;
         if (var1 == 0) {
            var2 = "LO";
         } else if (var1 == 127) {
            var2 = "HI";
         } else if (var1 > 30) {
            var2 = this.mCanBusInfoInt[4] + this.getTempUnitF(this.mContext);
         } else {
            var2 = this.mCanBusInfoInt[4] + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.rear_temperature = var2;
         this.updateAirActivity(this.mContext, 1003);
      }
   }

   private void set0x07CarControl() {
      ArrayList var2 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_373_setting_car_control");
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control_add_function"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
      var2.add(new SettingUpdateEntity(var1, this.getCarCotrolRight("_373_setting_car_control9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x09EspInfo() {
      if (!this.isTrackInfoChange()) {
         if (this.mCanBusInfoInt[2] < 128) {
            GeneralParkData.trackAngle = this.mCanBusInfoByte[2] / 5 - 25;
         } else {
            GeneralParkData.trackAngle = this.mCanBusInfoByte[2] / 5 + 25;
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x0ACarState() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data2"), this.getAccState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data3"), this.getSwitchState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data4"), this.getSwitchState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data5"), this.getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.setBacklightLevel(this.mCanBusInfoInt[4] / 2);
      int[] var2 = this.mCanBusInfoInt;
      var2[2] = 0;
      var2[4] = 0;
      if (!this.isBasicInfoChange()) {
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x0BCompassInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_compass"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_compass1"), this.getCompassState(this.mCanBusInfoInt[2])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x70AmpInfo() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 10;
      GeneralAmplifierData.frontRear = -this.mCanBusInfoInt[4] + 10;
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 1;
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 1;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 1;
      this.updateAmplifierActivity(new Bundle());
      AmpUtil.saveAmpUIValue(this.mContext);
      ArrayList var2 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_373_other_seting");
      var2.add(new SettingUpdateEntity(var1, this.getOtherSettingRight("_373_amp_seting1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2)));
      var2.add(new SettingUpdateEntity(var1, this.getOtherSettingRight("_373_amp_seting2"), this.mCanBusInfoInt[9]));
      var2.add(new SettingUpdateEntity(var1, this.getOtherSettingRight("_373_amp_seting3"), this.mCanBusInfoInt[10] - 1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x71VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void sndCurCompassSt(Location var1) {
      int var4 = (int)var1.getBearing();
      byte var3 = 0;
      byte var2 = var3;
      if (var4 != 0) {
         if (var4 == 360) {
            var2 = var3;
         } else if (var4 > 0 && var4 < 90) {
            var2 = 1;
         } else if (var4 == 90) {
            var2 = 2;
         } else if (var4 > 90 && var4 < 180) {
            var2 = 3;
         } else if (var4 == 180) {
            var2 = 4;
         } else if (var4 > 180 && var4 < 270) {
            var2 = 5;
         } else if (var4 == 270) {
            var2 = 6;
         } else {
            var2 = var3;
            if (var4 > 270) {
               var2 = var3;
               if (var4 < 360) {
                  var2 = 7;
               }
            }
         }
      }

      this.getUiMgr(this.mContext).send0x99CompassInfo(var2);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 7, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
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
      if (var3 != 1) {
         if (var3 != 3) {
            if (var3 != 4) {
               if (var3 != 5) {
                  if (var3 != 6) {
                     if (var3 != 7) {
                        if (var3 != 112) {
                           if (var3 != 113) {
                              switch (var3) {
                                 case 9:
                                    this.set0x09EspInfo();
                                    break;
                                 case 10:
                                    this.set0x0ACarState();
                                    break;
                                 case 11:
                                    this.set0x0BCompassInfo();
                              }
                           } else {
                              this.set0x71VersionInfo();
                           }
                        } else {
                           this.set0x70AmpInfo();
                        }
                     } else {
                        this.set0x07CarControl();
                     }
                  } else {
                     this.set0x06RearAir();
                  }
               } else {
                  this.set0x05FrontAir();
               }
            } else {
               this.set0x04WheelKeyInfo();
            }
         } else {
            this.set0x03SpeedInfo();
         }
      } else {
         this.set0x01WheelKeyInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0x98TimeInfo(var10, var5, var6, var7);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 3, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).send0x81MakeConnection();
      (new AmpUtil()).initAmpData(this.mContext);
      if (this.mLocationManager == null) {
         this.mLocationManager = (LocationManager)this.mContext.getSystemService("location");
      }

      try {
         this.mLocationManager.requestLocationUpdates("gps", 1000L, 1.0F, this.locationListener);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 4, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6;
      if (var2.equals("FM") || var2.equals("FM1") || var2.equals("FM2") || var2.equals("FM3") || !var2.equals("AM") && !var2.equals("AM1") && !var2.equals("AM2")) {
         var6 = 1;
      } else {
         var6 = 2;
      }

      this.getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, (byte)var6, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 0, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
   }

   public void updateAmp() {
      this.updateAmplifierActivity((Bundle)null);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 4, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
   }
}
