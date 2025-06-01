package com.hzbhd.canbus.car._276;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isDoorFirst;
   int BacksightDelay;
   Boolean BacksightDelayEnable;
   int ComeringLights;
   Boolean ComeringLightsEnable;
   int CourtesyLights;
   int CourtesyLightsDelay;
   Boolean CourtesyLightsDelayEnable;
   Boolean CourtesyLightsEnable;
   int DayLight;
   Boolean DayLightEnable;
   int DoorAutoLock;
   Boolean DoorAutoLockEnable;
   int FlashLightsWithLock;
   Boolean FlashLightsWithLockEnable;
   int HeadLightOffDelay;
   Boolean HeadLightOffDelayEnable;
   int LightSense;
   Boolean LightSenseEnable;
   int ParkSense;
   Boolean ParkSenseEnable;
   int RearParkSenseVOl;
   Boolean RearParkSenseVOlEnable;
   int RearViewCameraGuidelines;
   Boolean RearViewCameraGuidelinesEnable;
   int TripB;
   Boolean TripBEnable;
   int WiperSensorSwitch;
   Boolean WiperSensorSwitchEnable;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferentId;
   private int mEachId;
   private HashMap mSettingItemIndeHashMap;
   private UiMgr mUiMgr;
   private String mUnit1 = " l/100km";
   private String mUnit2 = " km";

   public MsgMgr() {
      Boolean var1 = true;
      this.DoorAutoLockEnable = var1;
      this.DayLightEnable = var1;
      this.RearViewCameraGuidelinesEnable = var1;
      this.RearParkSenseVOlEnable = var1;
      this.FlashLightsWithLockEnable = var1;
      this.ComeringLightsEnable = var1;
      this.CourtesyLightsEnable = var1;
      this.ParkSenseEnable = var1;
      this.TripBEnable = var1;
      this.LightSenseEnable = var1;
      this.WiperSensorSwitchEnable = var1;
      this.CourtesyLightsDelayEnable = var1;
      this.BacksightDelayEnable = var1;
      this.HeadLightOffDelayEnable = var1;
      this.mSettingItemIndeHashMap = new HashMap();
   }

   private void CarSetting() {
      this.DoorAutoLock = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
      this.DayLight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
      this.RearViewCameraGuidelines = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
      this.RearParkSenseVOl = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2);
      this.FlashLightsWithLock = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
      this.ComeringLights = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
      this.CourtesyLights = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
      this.ParkSense = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1);
      this.TripB = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
      this.LightSense = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
      this.WiperSensorSwitch = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1);
      this.CourtesyLightsDelay = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2);
      this.BacksightDelay = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
      this.HeadLightOffDelay = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      this.carSetting();
   }

   private void VehicleSettingInformationEnable() {
      this.DoorAutoLockEnable = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      this.DayLightEnable = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      this.RearViewCameraGuidelinesEnable = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      this.RearParkSenseVOlEnable = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.FlashLightsWithLockEnable = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      this.ComeringLightsEnable = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.CourtesyLightsEnable = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      this.ParkSenseEnable = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      this.TripBEnable = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      this.LightSenseEnable = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      this.WiperSensorSwitchEnable = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      this.CourtesyLightsDelayEnable = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      this.BacksightDelayEnable = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
      this.HeadLightOffDelayEnable = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
      this.carSetting();
   }

   private void carSetting() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mDifferentId;
      if (var1 != 2 && var1 != 7 && var1 != 9 && var1 != 10) {
         if (var1 == 3) {
            var2.add((new SettingUpdateEntity(2, 0, this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable));
            var2.add((new SettingUpdateEntity(2, 1, this.ParkSense)).setEnable(this.ParkSenseEnable));
            var2.add((new SettingUpdateEntity(2, 2, this.RearParkSenseVOl)).setEnable(this.RearParkSenseVOlEnable));
            var2.add((new SettingUpdateEntity(2, 3, this.CourtesyLights)).setEnable(this.CourtesyLightsEnable));
            var2.add((new SettingUpdateEntity(2, 4, this.ComeringLights)).setEnable(this.ComeringLightsEnable));
            var2.add((new SettingUpdateEntity(2, 5, this.FlashLightsWithLock)).setEnable(this.FlashLightsWithLockEnable));
            var2.add((new SettingUpdateEntity(2, 6, this.LightSense)).setEnable(this.LightSenseEnable));
         } else if (var1 == 4) {
            var2.add((new SettingUpdateEntity(2, 0, this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable));
            var2.add((new SettingUpdateEntity(2, 1, this.ParkSense)).setEnable(this.ParkSenseEnable));
            var2.add((new SettingUpdateEntity(2, 2, this.RearParkSenseVOl)).setEnable(this.RearParkSenseVOlEnable));
            var2.add((new SettingUpdateEntity(2, 3, this.CourtesyLights)).setEnable(this.CourtesyLightsEnable));
            var2.add((new SettingUpdateEntity(2, 4, this.ComeringLights)).setEnable(this.ComeringLightsEnable));
            var2.add((new SettingUpdateEntity(2, 5, this.FlashLightsWithLock)).setEnable(this.FlashLightsWithLockEnable));
            var2.add((new SettingUpdateEntity(2, 6, this.RearViewCameraGuidelines)).setEnable(this.RearViewCameraGuidelinesEnable));
            var2.add((new SettingUpdateEntity(2, 7, this.HeadLightOffDelay)).setEnable(this.HeadLightOffDelayEnable));
            var2.add((new SettingUpdateEntity(2, 8, this.BacksightDelay)).setEnable(this.BacksightDelayEnable));
         } else if (var1 == 5 || var1 == 6) {
            var2.add((new SettingUpdateEntity(2, 0, this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable));
            var2.add((new SettingUpdateEntity(2, 1, this.DayLight)).setEnable(this.DayLightEnable));
            var2.add((new SettingUpdateEntity(2, 2, this.TripB)).setEnable(this.TripBEnable));
            var2.add((new SettingUpdateEntity(2, 3, this.CourtesyLights)).setEnable(this.CourtesyLightsEnable));
            var2.add((new SettingUpdateEntity(2, 4, this.ComeringLights)).setEnable(this.ComeringLightsEnable));
            var2.add((new SettingUpdateEntity(2, 5, this.CourtesyLightsDelay)).setEnable(this.CourtesyLightsDelayEnable));
            var2.add((new SettingUpdateEntity(2, 6, this.LightSense)).setEnable(this.LightSenseEnable));
            var2.add((new SettingUpdateEntity(2, 7, this.WiperSensorSwitch)).setEnable(this.WiperSensorSwitchEnable));
         }
      } else {
         var2.add((new SettingUpdateEntity(2, 0, this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable));
         var2.add((new SettingUpdateEntity(2, 1, this.DayLight)).setEnable(this.DayLightEnable));
         var2.add((new SettingUpdateEntity(2, 2, this.TripB)).setEnable(this.TripBEnable));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private byte getAllBandTypeData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 2443:
            if (var1.equals("LW")) {
               var2 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var2 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var2 = 7;
            }
      }

      switch (var2) {
         case 0:
         case 2:
            return 4;
         case 1:
         case 3:
            return 5;
         case 4:
            return 1;
         case 5:
            return 2;
         case 6:
         case 7:
            return 3;
         default:
            return 0;
      }
   }

   private int getData(int... var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var1.length; ++var2) {
         var3 += var1[var2] << var2 * 8;
      }

      return (double)var3 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var3;
   }

   private String getInfo(int var1, float var2, String var3) {
      return var1 == -1 ? "---" : (new DecimalFormat("0.0")).format((double)((float)var1 * var2)) + var3;
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         this.mSettingItemIndeHashMap.put(var1, new SettingUpdateEntity(-1, -1, (Object)null));
         return this.getSettingUpdateEntity(var1);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initSettingsItem(Context var1) {
      this.mSettingItemIndeHashMap = new HashMap();
      SparseArray var4 = new SparseArray();
      List var6 = this.getUiMgr(var1).getSettingUiSet(var1).getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var7 = ((SettingPageUiSet.ListBean)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var7.size(); ++var3) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)var7.get(var3)).getTitleSrn();
            var4.append(var2 << 8 | var3, var5);
            this.mSettingItemIndeHashMap.put(var5, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isHave0xe1Info() {
      int var1 = this.mDifferentId;
      return var1 != 0 && var1 != 1 && var1 != 2;
   }

   private void keyControl0x11() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyClick1(0);
            break;
         case 1:
            this.realKeyClick1(7);
            break;
         case 2:
            this.realKeyClick1(8);
            break;
         case 3:
            this.realKeyClick1(3);
            break;
         case 4:
            this.realKeyClick1(187);
            break;
         case 5:
            this.realKeyClick1(14);
            break;
         case 6:
            this.realKeyClick1(15);
         case 7:
         case 10:
         default:
            break;
         case 8:
            this.realKeyClick1(46);
            break;
         case 9:
            this.realKeyClick1(45);
            break;
         case 11:
            this.realKeyClick1(2);
      }

      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void radarInfo() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2] + 1, var1[3] + 1, var1[4] + 1, var1[5] + 1);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void realKeyClick1(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[4], var2[5]);
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      return (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
   }

   private void set0x26CarInfo() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 != 0) {
         switch (var1) {
            case 32:
               var2 = "16款 Fiorino";
               break;
            case 33:
               var2 = "16款 Doblo";
               break;
            case 34:
               var2 = "16款 Aegea";
               break;
            case 35:
               var2 = "18款 Aegea";
               break;
            case 36:
               var2 = "19款 Aegea";
               break;
            case 37:
               var2 = "13款 500L";
               break;
            case 38:
               var2 = "19款 500L";
               break;
            case 39:
               var2 = "17款 Panda";
               break;
            case 40:
               var2 = "09款 Linea(FT05)";
               break;
            default:
               var2 = "未知";
         }
      } else {
         var2 = "无效";
      }

      Log.i("ljq", "set0x26CarInfo: 车型： " + var2);
   }

   private void setDoorData() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setDriveData0x13() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var3.append((float)((double)(var2[2] * 256 + var2[3]) * 0.1)).append(this.mUnit1).toString()));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var4.append(var5[4] * 256 + var5[5]).append(this.mUnit2).toString()));
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 2, var4.append(var5[6] * 256 * 256 + var5[7] * 256 + var5[8]).append(this.mUnit2).toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveDataTrip0x14() {
      int[] var5 = this.mCanBusInfoInt;
      int var1 = var5[10];
      int var2 = var5[11];
      int var4 = var5[9];
      int var3 = var4 / 60;
      String var6 = this.mContext.getResources().getString(2131768868);
      String var7 = this.mContext.getResources().getString(2131769310);
      ArrayList var10 = new ArrayList();
      StringBuilder var9 = new StringBuilder();
      int[] var8 = this.mCanBusInfoInt;
      var10.add(new DriverUpdateEntity(1, 0, var9.append((float)((double)(var8[2] * 256 + var8[3]) * 0.1)).append(this.mUnit1).toString()));
      var10.add(new DriverUpdateEntity(1, 1, this.mCanBusInfoInt[4] + " km/h"));
      var9 = new StringBuilder();
      var8 = this.mCanBusInfoInt;
      var10.add(new DriverUpdateEntity(1, 2, var9.append((float)((double)(var8[5] * 256 * 256 + var8[6] * 256 + var8[7]) * 0.1)).append(this.mUnit2).toString()));
      var10.add(new DriverUpdateEntity(1, 3, var1 * 256 + var2 + var3 + var6 + var4 % 60 + var7));
      this.updateGeneralDriveData(var10);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[4]);
   }

   private void setDriveDataTrip0x15() {
      int[] var5 = this.mCanBusInfoInt;
      int var3 = var5[10];
      int var4 = var5[11];
      int var1 = var5[9];
      int var2 = var1 / 60;
      String var7 = this.mContext.getResources().getString(2131768868);
      String var6 = this.mContext.getResources().getString(2131769310);
      ArrayList var10 = new ArrayList();
      StringBuilder var9 = new StringBuilder();
      int[] var8 = this.mCanBusInfoInt;
      var10.add(new DriverUpdateEntity(2, 0, var9.append((float)((double)(var8[2] * 256 + var8[3]) * 0.1)).append(this.mUnit1).toString()));
      var10.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[4] + " km/h"));
      StringBuilder var11 = new StringBuilder();
      int[] var12 = this.mCanBusInfoInt;
      var10.add(new DriverUpdateEntity(2, 2, var11.append((float)((double)(var12[5] * 256 * 256 + var12[6] * 256 + var12[7]) * 0.1)).append(this.mUnit2).toString()));
      var10.add(new DriverUpdateEntity(2, 3, var3 * 256 + var4 + var2 + var7 + var1 % 60 + var6));
      this.updateGeneralDriveData(var10);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setLanguage() {
      int[] var6 = this.mCanBusInfoInt;
      byte var4 = 2;
      int var5 = var6[2];
      byte var2 = 3;
      byte var1;
      if (var5 == 3) {
         var1 = 1;
      } else {
         var1 = 0;
      }

      byte var3 = 4;
      if (var5 == 4) {
         var1 = var4;
      }

      var4 = 5;
      if (var5 == 5) {
         var1 = var2;
      }

      var2 = 7;
      if (var5 == 7) {
         var1 = var3;
      }

      var3 = 8;
      if (var5 == 8) {
         var1 = var4;
      }

      if (var5 == 9) {
         var1 = 6;
      }

      if (var5 == 16) {
         var1 = var2;
      }

      if (var5 == 21) {
         var1 = var3;
      }

      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(1, 0, Integer.valueOf(var1)));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOutDoorTemp() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void settingData() {
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.mUnit1 = "mpg(uk)";
               }
            } else {
               this.mUnit1 = "mpg(us)";
            }
         } else {
            this.mUnit1 = "km/l";
         }
      } else {
         this.mUnit1 = "l/100km";
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         this.mUnit2 = "km";
      } else {
         this.mUnit2 = "mile";
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      UiMgrFactory.getCanUiMgr(var1);
      this.mEachId = this.getCurrentEachCanId();
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      if (this.isHave0xe1Info()) {
         byte[] var1 = "000".getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, var1), 15, 32));
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      String var6 = (new DecimalFormat("00")).format((long)(var4 / 60));
      String var5 = (new DecimalFormat("00")).format((long)(var4 % 60));
      var6 = "    " + var6 + var5 + "    ";
      if (this.isHave0xe1Info()) {
         var1 = var6.getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var1));
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 38) {
         if (var3 != 49) {
            if (var3 != 65) {
               if (var3 != 113) {
                  if (var3 != 118) {
                     if (var3 != 148) {
                        if (var3 != 193) {
                           if (var3 != 240) {
                              switch (var3) {
                                 case 17:
                                    this.keyControl0x11();
                                    break;
                                 case 18:
                                    if (this.isDoorMsgReturn(var2)) {
                                       return;
                                    }

                                    this.setDoorData();
                                    break;
                                 case 19:
                                    if (this.mDifferentId != 1) {
                                       this.setDriveData0x13();
                                    }
                                    break;
                                 case 20:
                                    if (this.mDifferentId != 1) {
                                       this.setDriveDataTrip0x14();
                                    }
                                    break;
                                 case 21:
                                    if (this.mDifferentId != 1) {
                                       this.setDriveDataTrip0x15();
                                    }
                              }
                           } else {
                              this.setVersionInfo();
                           }
                        } else {
                           var3 = this.mDifferentId;
                           if (var3 != 1 && var3 != 0) {
                              this.settingData();
                           }
                        }
                     } else {
                        var3 = this.mDifferentId;
                        if (var3 != 1 && var3 != 0) {
                           this.setLanguage();
                        }
                     }
                  } else {
                     var3 = this.mDifferentId;
                     if (var3 != 1 && var3 != 0) {
                        this.CarSetting();
                     }
                  }
               } else {
                  this.VehicleSettingInformationEnable();
               }
            } else if (this.mDifferentId != 1) {
               this.radarInfo();
            }
         } else {
            this.setOutDoorTemp();
         }
      } else {
         this.set0x26CarInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var10, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferentId = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 27});
      this.initSettingsItem(var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -31}, 15));
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -28}, 34));
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var12 = (new DecimalFormat("00")).format((long)var6);
      var11 = (new DecimalFormat("00")).format((long)var7);
      var13 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var12 + var11 + "    ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      if (this.isHave0xe1Info()) {
         Context var26 = this.mContext;
         var12 = SourceConstantsDef.SOURCE_ID.MUSIC.name();
         byte[] var27 = var13.getBytes();
         this.sendMediaMsg(var26, var12, DataHandleUtils.byteMerger(new byte[]{22, -31, var1}, var27));

         try {
            var26 = this.mContext;
            var12 = SourceConstantsDef.SOURCE_ID.MUSIC.name();
            var27 = var21.getBytes("unicode");
            this.sendMediaMsg(var26, var12, DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -28}, var27), 34));
         } catch (UnsupportedEncodingException var25) {
            var25.printStackTrace();
         }
      }

      FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var1) + " " + var3 + "     ";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var1) + " 0" + var3 + "     ";
         }
      } else if (var3.length() == 5) {
         var2 = (new DecimalFormat("00")).format((long)var1) + "  " + var3.substring(0, var3.length() - 1) + "    ";
      } else {
         var2 = (new DecimalFormat("00")).format((long)var1) + " " + var3.substring(0, var3.length() - 1) + "    ";
      }

      if (this.isHave0xe1Info()) {
         Context var7 = this.mContext;
         var4 = SourceConstantsDef.SOURCE_ID.FM.name();
         byte[] var8 = var2.getBytes();
         this.sendMediaMsg(var7, var4, DataHandleUtils.byteMerger(new byte[]{22, -31, var6}, var8));
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var11 = (new DecimalFormat("00")).format((long)var6);
      var8 = (new DecimalFormat("00")).format((long)var7);
      var12 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var11 + var8 + "    ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      if (this.isHave0xe1Info()) {
         Context var18 = this.mContext;
         var11 = SourceConstantsDef.SOURCE_ID.VIDEO.name();
         byte[] var19 = var12.getBytes();
         this.sendMediaMsg(var18, var11, DataHandleUtils.byteMerger(new byte[]{22, -31, var1}, var19));
      }

      FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
   }
}
