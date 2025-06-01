package com.hzbhd.canbus.car._325;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final String IS_AIR_LEFT_TEMP = "is_air_left_temp";
   static final String IS_AIR_RIGHT_TEMP = "is_air_right_temp";
   static final String IS_AIR_WIND_LEVEL = "is_air_right_temp";
   private final int DATA_TYPE = 1;
   private int[] m0x11Data;
   private int[] m0x41Data;
   private byte[] mAirData;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mEachId;
   private boolean mFrontStatus;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap = new HashMap();
   private UiMgr mUiMgr;

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private String getBandUnit(String var1) {
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
         case 1:
            return "KHz";
         case 2:
         case 3:
         case 4:
            return "MHz";
         default:
            return "";
      }
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
      List var5 = this.getUiMgr(var1).getSettingUiSet(var1).getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var7 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var7.size(); ++var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)var7.get(var3)).getTitleSrn();
            var4.append(var2 << 8 | var3, var6);
            this.mSettingItemIndeHashMap.put(var6, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean is0x1dDataChange() {
      if (Arrays.equals(this.m0x41Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x41Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorChange() {
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

   private boolean isTrackDataChange() {
      if (Arrays.equals(this.m0x11Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x11Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolverAirTemperature(int var1) {
      if (var1 == 254) {
         return "LO";
      } else {
         return var1 == 255 ? "HI" : (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      }
   }

   private void set0x11CarBaseData(Context var1) {
      this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      int var3 = this.mKeyStatus;
      int var2 = this.mCanBusInfoInt[4];
      if (var3 != var2) {
         this.mKeyStatus = var2;
      }

      switch (var2) {
         case 0:
            this.realKeyLongClick2(this.mContext, 0);
            break;
         case 1:
            this.realKeyLongClick2(this.mContext, 7);
            break;
         case 2:
            this.realKeyLongClick2(this.mContext, 8);
            break;
         case 3:
            this.realKeyLongClick2(this.mContext, 3);
         case 4:
         case 7:
         default:
            break;
         case 5:
            this.realKeyLongClick2(this.mContext, 14);
            break;
         case 6:
            this.realKeyLongClick2(this.mContext, 15);
            break;
         case 8:
            this.realKeyLongClick2(this.mContext, 45);
            break;
         case 9:
            this.realKeyLongClick2(this.mContext, 46);
            break;
         case 10:
            this.realKeyLongClick2(this.mContext, 2);
      }

      if (this.isTrackDataChange()) {
         int[] var5 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var5[9], (byte)var5[8], 0, 480, 16);
      }

      this.updateParkUi((Bundle)null, var1);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11]);
      if (this.isDoorChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x19MileageInfo() {
      int[] var6 = this.mCanBusInfoInt;
      int var5 = var6[2];
      int var4 = var6[3];
      int var1 = var6[4];
      int var3 = var6[10];
      int var2 = var6[11];
      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(1, 6, String.valueOf((var5 << 16) + (var4 << 8) + var1)));
      var7.add(new DriverUpdateEntity(1, 7, String.valueOf((var3 << 8) + var2)));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x31AirData() {
      if (this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         this.cleanAllBlow();
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 11:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           break;
                        case 12:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_foot = true;
                           break;
                        case 13:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                           break;
                        case 14:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_auto_wind_model = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         SharePreUtil.setIntValue(this.mContext, "is_air_right_temp", this.mCanBusInfoInt[7] & 15);
         GeneralAirData.front_left_temperature = this.resolverAirTemperature(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.resolverAirTemperature(this.mCanBusInfoInt[9]);
         SharePreUtil.setIntValue(this.mContext, "is_air_left_temp", this.mCanBusInfoInt[8]);
         SharePreUtil.setIntValue(this.mContext, "is_air_right_temp", this.mCanBusInfoInt[9]);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x41RadarInfo(Context var1) {
      if (this.isAirDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(255, var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(255, var2[6], var2[7], var2[8], var2[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x46TyresInfo() {
      ArrayList var4 = new ArrayList();
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_325_setting_2");
      int var1 = this.mCanBusInfoInt[3];
      boolean var3 = true;
      var5 = var5.setValue(DataHandleUtils.getIntFromByteWithBit(var1, 7, 1));
      boolean var2;
      if ((this.mCanBusInfoInt[2] >> 6 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_325_setting_3").setValue(this.mCanBusInfoInt[4]);
      if ((this.mCanBusInfoInt[3] >> 7 & 1) == 1) {
         var2 = var3;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x48AIDSInfo() {
      ArrayList var3 = new ArrayList();
      SettingUpdateEntity var4 = this.getSettingUpdateEntity("_303_setting_content_3");
      int var1 = this.mCanBusInfoInt[3];
      boolean var2 = true;
      var4 = var4.setValue(DataHandleUtils.getIntFromByteWithBit(var1, 7, 1));
      if ((this.mCanBusInfoInt[2] >> 7 & 1) != 1) {
         var2 = false;
      }

      var3.add(var4.setEnable(var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x64WindowAndCC() {
      ArrayList var4 = new ArrayList();
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_303_setting_content_11");
      int var1 = this.mCanBusInfoInt[4];
      boolean var3 = true;
      var5 = var5.setValue(DataHandleUtils.getIntFromByteWithBit(var1, 7, 1));
      boolean var2;
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_12").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1));
      if ((this.mCanBusInfoInt[2] >> 6 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_13").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1));
      if ((this.mCanBusInfoInt[2] >> 5 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_14").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1));
      if ((this.mCanBusInfoInt[3] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_15").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1));
      if ((this.mCanBusInfoInt[3] >> 6 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_16").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1));
      if ((this.mCanBusInfoInt[3] >> 5 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_17").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1));
      if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
         var2 = var3;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x68LightInfo() {
      ArrayList var4 = new ArrayList();
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_303_setting_content_4");
      int var1 = this.mCanBusInfoInt[5];
      boolean var3 = true;
      var5 = var5.setValue(DataHandleUtils.getIntFromByteWithBit(var1, 6, 1));
      boolean var2;
      if ((this.mCanBusInfoInt[2] >> 5 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1));
      if ((this.mCanBusInfoInt[2] >> 4 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1));
      if ((this.mCanBusInfoInt[2] >> 3 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_8").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2));
      if ((this.mCanBusInfoInt[2] >> 1 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_9").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2));
      if ((this.mCanBusInfoInt[2] >> 0 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_325_light_1").setValue(this.mCanBusInfoInt[6]).setProgress(this.mCanBusInfoInt[6]);
      if ((this.mCanBusInfoInt[2] >> 2 & 1) == 1) {
         var2 = var3;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var4.add(this.getSettingUpdateEntity("_303_setting_content_10").setValue(this.mCanBusInfoInt[7]).setProgress(this.mCanBusInfoInt[7]));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x71ParkAlarm() {
      ArrayList var4 = new ArrayList();
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_303_setting_content_22");
      int var1 = this.mCanBusInfoInt[3];
      boolean var3 = true;
      var5 = var5.setValue(DataHandleUtils.getIntFromByteWithBit(var1, 7, 1));
      boolean var2;
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_18").setValue(this.mCanBusInfoInt[4]).setProgress(this.mCanBusInfoInt[4]);
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_19").setValue(this.mCanBusInfoInt[5]).setProgress(this.mCanBusInfoInt[5]);
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_20").setValue(this.mCanBusInfoInt[6]).setProgress(this.mCanBusInfoInt[6]);
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_21").setValue(this.mCanBusInfoInt[7]).setProgress(this.mCanBusInfoInt[7]);
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = var3;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x7dKeyTotal() {
      ArrayList var3 = new ArrayList();
      SettingUpdateEntity var4 = this.getSettingUpdateEntity("_303_setting_content_39").setValue(this.mCanBusInfoInt[3]).setProgress(this.mCanBusInfoInt[3]);
      int var1 = this.mCanBusInfoInt[2];
      boolean var2 = true;
      if ((var1 >> 7 & 1) != 1) {
         var2 = false;
      }

      var3.add(var4.setEnable(var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC1UnitInfo() {
      ArrayList var4 = new ArrayList();
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_303_setting_content_23");
      int var1 = this.mCanBusInfoInt[3];
      boolean var3 = true;
      var5 = var5.setValue(DataHandleUtils.getIntFromByteWithBit(var1, 7, 1));
      boolean var2;
      if ((this.mCanBusInfoInt[2] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_24").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1));
      if ((this.mCanBusInfoInt[2] >> 6 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_25").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1));
      if ((this.mCanBusInfoInt[2] >> 5 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_26").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2));
      if ((this.mCanBusInfoInt[2] >> 4 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_27").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2));
      if ((this.mCanBusInfoInt[2] >> 3 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_303_setting_content_28").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      if ((this.mCanBusInfoInt[2] >> 2 & 1) == 1) {
         var2 = var3;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC3MileageAndMaintenanceInfo() {
      int var1;
      Context var2;
      if (this.mCanBusInfoInt[4] == 1) {
         var2 = this.mContext;
         var1 = 2131770540;
      } else {
         var2 = this.mContext;
         var1 = 2131770538;
      }

      String var3 = var2.getString(var1);
      String var7 = this.mContext.getString(2131770118);
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(1, 0, String.valueOf(this.mCanBusInfoInt[2])));
      var4.add(new DriverUpdateEntity(1, 1, var3));
      StringBuilder var6 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 2, var6.append((var5[5] * 256 + var5[6]) * 100).append(var3).toString()));
      StringBuilder var9 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 3, var9.append(var10[7] * 256 + var10[8]).append(var7).toString()));
      var4.add(new DriverUpdateEntity(1, 4, this.mCanBusInfoInt[9] * 100 + var3));
      var4.add(new DriverUpdateEntity(1, 5, this.mCanBusInfoInt[10] + var7));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      ArrayList var8 = new ArrayList();
      var8.add(this.getSettingUpdateEntity("_303_setting_content_30").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1)));
      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC4TirePressure() {
      ArrayList var1 = new ArrayList();
      var1.add(this.getSettingUpdateEntity("_303_setting_content_37").setValue(this.mCanBusInfoInt[3] - 1));
      var1.add(this.getSettingUpdateEntity("_303_setting_content_38").setValue(this.mCanBusInfoInt[4]).setProgress(this.mCanBusInfoInt[4]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.initSettingsItem(var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 12, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "BT MUSIC    ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var1));
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      byte[] var10 = var2;
      if (var2.length < 12) {
         var10 = new byte[12 - var2.length];
         Arrays.fill(var10, (byte)32);
         var10 = DataHandleUtils.byteMerger(var10, var2);
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var10));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      switch (var3[1]) {
         case 17:
            this.set0x11CarBaseData(var1);
            break;
         case 25:
            this.set0x19MileageInfo();
            break;
         case 49:
            this.set0x31AirData();
            break;
         case 65:
            this.set0x41RadarInfo(var1);
            break;
         case 70:
            this.set0x46TyresInfo();
            break;
         case 72:
            this.set0x48AIDSInfo();
            break;
         case 100:
            this.set0x64WindowAndCC();
            break;
         case 104:
            this.set0x68LightInfo();
            break;
         case 113:
            this.set0x71ParkAlarm();
            break;
         case 125:
            this.set0x7dKeyTotal();
            break;
         case 193:
            this.set0xC1UnitInfo();
            break;
         case 195:
            this.set0xC3MileageAndMaintenanceInfo();
            break;
         case 196:
            this.set0xC4TirePressure();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      byte var16 = (byte)DataHandleUtils.setIntByteWithBit(0, 7, var12);
      byte var15 = (byte)var8;
      byte var17 = (byte)var6;
      byte var14 = (byte)0;
      CanbusMsgSender.sendMsg(new byte[]{22, -53, var16, var15, var17, var14, var14, (byte)var10, (byte)(var2 + 208), (byte)var3, (byte)var4, (byte)var9});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 7, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var25 = ((var9 & 255) * 256 + var3 + "/" + var4 + " " + var12 + ":" + var13 + "     ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var25));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      var5 = var2.hashCode();
      byte var9 = -1;
      switch (var5) {
         case 64901:
            if (var2.equals("AM1")) {
               var9 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var9 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var9 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var9 = 3;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var9 = 4;
            }
      }

      var4 = "01";
      switch (var9) {
         case 0:
            var9 = 4;
            break;
         case 1:
            var9 = 5;
            var4 = "03";
            break;
         case 2:
         default:
            var9 = 1;
            break;
         case 3:
            var4 = "10";
            var9 = 2;
            break;
         case 4:
            var4 = "10";
            var9 = 3;
      }

      String var8;
      if (var2.contains("FM") && var2.length() == 4) {
         var8 = "  ";
      } else {
         var8 = " ";
      }

      String var7;
      if (var2.contains("FM") && var2.length() == 4) {
         var7 = " " + var3;
      } else {
         var7 = var3;
         if (var2.contains("AM")) {
            if (var2.length() == 3) {
               var7 = var3 + "  ";
            } else {
               var7 = var3 + " ";
            }
         }
      }

      var2 = var4 + var8 + var7 + this.getBandUnit(var2) + " ";
      byte var6 = (byte)var9;
      byte[] var10 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, var6}, var10));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = ((var9 & 255) * 256 + var3 + "/" + var4 + " " + var12 + ":" + var13 + "     ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var18));
   }
}
