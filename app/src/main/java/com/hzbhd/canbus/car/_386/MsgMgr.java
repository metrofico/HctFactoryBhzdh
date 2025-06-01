package com.hzbhd.canbus.car._386;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_386_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
   static final String SHARE_386_AMPLIFIER_BASS = "share_122_amplifier_bass";
   static final String SHARE_386_AMPLIFIER_FADE = "share_122_amplifier_fade";
   static final String SHARE_386_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
   static final String SHARE_386_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
   static final String SHARE_386_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
   static final int _386_AMPLIFIER_OFFSET = 0;
   static final int _386_AMPLIFIER_RANGE = 9;
   public boolean Down;
   public boolean Left;
   public boolean Right;
   public boolean Up;
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private HashMap mDriveItemHashMap;
   private HashMap mSettingItemHashMap;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private UiMgr mUiMgr;
   int memory;
   int memory2;
   int volume;

   private String ResolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "LOW_TEMP";
      }

      if (var1 == 255) {
         var2 = "HIGH_TEMP";
      }

      return var2;
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
   }

   private void finishTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private DriverUpdateEntity helperSetDriveDataValue(String var1, String var2) {
      if (!this.mDriveItemHashMap.containsKey(var1)) {
         this.mDriveItemHashMap.put(var1, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
      }

      return ((DriveDataUpdateHelper)this.mDriveItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2, boolean var3) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper((new SettingUpdateEntity(-1, -1, "null_value")).setEnable(false)));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2).setEnable(var3);
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_122_amplifier_volume", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_122_amplifier_balance", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_122_amplifier_fade", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_122_amplifier_bass", 0);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_122_amplifier_middle", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_122_amplifier_treble", 0);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
      byte[] var5 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 0 + 9)};
      byte var3 = (byte)(GeneralAmplifierData.frontRear + 0 + 9);
      byte[] var6 = new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 0)};
      byte var4 = (byte)(GeneralAmplifierData.bandMiddle + 0);
      byte var2 = (byte)(GeneralAmplifierData.bandTreble + 0);
      this.mTimerTask = new TimerTask(this, new byte[][]{var5, {22, -83, 3, var3}, var6, {22, -83, 5, var4}, {22, -83, 6, var2}}) {
         int index;
         final MsgMgr this$0;
         final byte[][] val$ampCmdArrays;

         {
            this.this$0 = var1;
            this.val$ampCmdArrays = var2;
            this.index = 0;
         }

         public void run() {
            int var1 = this.index;
            byte[][] var2 = this.val$ampCmdArrays;
            if (var1 < var2.length) {
               this.index = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.finishTimer();
            }

         }
      };
      this.startTimer(100L, 100);
   }

   private void initDriveItem(DriverDataPageUiSet var1) {
      this.mDriveItemHashMap = new HashMap();
      List var4 = var1.getList();

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         List var6 = ((DriverDataPageUiSet.Page)var4.get(var2)).getItemList();

         for(int var3 = 0; var3 < var6.size(); ++var3) {
            String var5 = ((DriverDataPageUiSet.Page.Item)var6.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var5, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var7 = var1.getList();

      for(int var2 = 0; var2 < var7.size(); ++var2) {
         List var4 = ((SettingPageUiSet.ListBean)var7.get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var5 = (SettingPageUiSet.ListBean.ItemListBean)var4.get(var3);
            String var6 = var5.getTitleSrn();
            this.mSettingItemHashMap.put(var6, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var5.getMin()));
         }
      }

   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private String resolveData(int var1) {
      String var2 = CommUtil.getStrByResId(this.mContext, "_386_item_11");
      if (DataHandleUtils.getBoolBit7(var1)) {
         var2 = CommUtil.getStrByResId(this.mContext, "_386_item_10");
      }

      return var2 + DataHandleUtils.getIntFromByteWithBit(var1, 0, 7) + "°";
   }

   private String resolveDataOne(int var1) {
      String var2 = CommUtil.getStrByResId(this.mContext, "_386_item_13");
      if (DataHandleUtils.getBoolBit7(var1)) {
         var2 = CommUtil.getStrByResId(this.mContext, "_386_item_12");
      }

      return var2 + DataHandleUtils.getIntFromByteWithBit(var1, 0, 7) + "°";
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_middle", GeneralAmplifierData.bandMiddle);
   }

   private void setAirInfo0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0) {
         if (var1 != 3) {
            if (var1 != 12) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_foot = false;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = false;
         }
      } else {
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[9]);
      var1 = this.mCanBusInfoInt[10];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.rear_left_blow_foot = false;
                     GeneralAirData.rear_right_blow_foot = false;
                     GeneralAirData.rear_left_blow_head = true;
                     GeneralAirData.rear_right_blow_head = true;
                     GeneralAirData.rear_left_blow_window = false;
                     GeneralAirData.rear_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_right_blow_head = true;
                  GeneralAirData.rear_left_blow_window = false;
                  GeneralAirData.rear_right_blow_window = false;
               }
            } else {
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_foot = true;
               GeneralAirData.rear_left_blow_head = false;
               GeneralAirData.rear_right_blow_head = false;
               GeneralAirData.rear_left_blow_window = false;
               GeneralAirData.rear_right_blow_window = false;
            }
         } else {
            GeneralAirData.rear_auto_wind_model = true;
         }
      } else {
         GeneralAirData.rear_left_blow_foot = false;
         GeneralAirData.rear_right_blow_foot = false;
         GeneralAirData.rear_left_blow_head = false;
         GeneralAirData.rear_right_blow_head = false;
         GeneralAirData.rear_left_blow_window = false;
         GeneralAirData.rear_right_blow_window = false;
      }

      GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
      GeneralAirData.rear_temperature = this.ResolveTemp(this.mCanBusInfoInt[12]);
      this.updateAirActivity(this.mContext, 1004);
   }

   private void setAmplifierInfo0xA6() {
      ArrayList var2 = new ArrayList();
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      int[] var1 = this.mCanBusInfoInt;
      this.volume = var1[2];
      GeneralAmplifierData.bandBass = var1[5];
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 10;
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 10;
      var2.add(this.checkEntity(this.helperSetValue("speed_linkage_volume_adjustment", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2))));
      var2.add(this.checkEntity(this.helperSetValue("surround_sound", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2))));
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData();
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var2);
   }

   private void setCarInfo0x32() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_1", var2.append((var3[4] << 8) + var3[5]).append("").toString())));
      StringBuilder var6 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_2", var6.append((var5[6] << 8) + var5[7]).append("").toString())));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_3", (float)this.mCanBusInfoInt[8] / 10.0F + "V")));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_4", (double)this.mCanBusInfoInt[9] * 4.5 + "hPa")));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_5", this.mCanBusInfoInt[11] - 40 + this.getTempUnitC(this.mContext))));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_6", this.resolveData(this.mCanBusInfoInt[12]))));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_7", this.resolveDataOne(this.mCanBusInfoInt[13]))));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_8", this.mCanBusInfoInt[14] + this.getTempUnitC(this.mContext))));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_386_item_9", this.mCanBusInfoInt[15] + "%")));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[6], var4[7]));
   }

   private void setDoorInfo0x12() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setOriginalCarVideoStatus0xE8() {
      ArrayList var1 = new ArrayList();
      var1.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
      this.Right = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      var1.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5])));
      var1.add(new PanoramicBtnUpdateEntity(2, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])));
      this.Left = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      var1.add(new PanoramicBtnUpdateEntity(3, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7])));
      this.Up = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
      var1.add(new PanoramicBtnUpdateEntity(4, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])));
      this.Down = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]);
      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
      int[] var2 = this.mCanBusInfoInt;
      if (var2[4] != 0) {
         this.forceReverse(this.mContext, true);
      } else if (var2[5] != 0) {
         this.forceReverse(this.mContext, true);
      } else if (var2[6] != 0) {
         this.forceReverse(this.mContext, true);
      } else if (var2[7] != 0) {
         this.forceReverse(this.mContext, true);
      } else if (var2[8] != 0) {
         this.forceReverse(this.mContext, true);
      } else {
         this.forceReverse(this.mContext, false);
      }
   }

   private void setRadarInfo0x41() {
      this.getUiMgr(this.mContext).getParkPageUiSet(this.mContext).setShowRadar(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]));
      int var1 = this.mCanBusInfoInt[13];
      int[] var2;
      if (var1 != 0) {
         if (var1 == 1) {
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(var2[2], var2[3], var2[4], var2[5]);
            var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarDistanceData(var2[6], var2[7], var2[8], var2[9]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         }
      } else {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationDataType2(4, var2[2], 10, var2[3], 10, var2[4], 4, var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationDataType2(4, var2[6], 7, var2[7], 7, var2[8], 4, var2[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingInfo0x62() {
      boolean var7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      boolean var5 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var2 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      boolean var10 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var8 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      boolean var9 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      boolean var4 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      boolean var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      boolean var6 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      boolean var11 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      ArrayList var12 = new ArrayList();
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) != this.memory || DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2) != this.memory2) {
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
         this.memory = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
         this.memory2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
         this.updateAirActivity(this.mContext, 1001);
      }

      var12.add(this.checkEntity(this.helperSetValue("_386_seat_chirapsia_driver", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
      var12.add(this.checkEntity(this.helperSetValue("_386_lumbar_support_driver", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3))));
      var12.add(this.checkEntity(this.helperSetValue("_386_seat_chirapsia_copilot", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
      var12.add(this.checkEntity(this.helperSetValue("_386_lumbar_support_copilot", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 3)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 3))));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_14", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2), var7)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_15", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2), var5)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_16", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2), var2)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_17", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1), var10)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_18", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1), var8)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_19", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1), var1)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_20", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2), var9)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_21", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1), var4)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_22", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1), var3)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_23", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1), var6)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_25", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1), var11)));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_26", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))));
      var12.add(this.checkEntity(this.helperSetValue("_386_item_27", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))));
      this.updateGeneralSettingData(var12);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackDate0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 == 12) {
                                 this.realKeyClick0x11(2);
                              }
                           } else {
                              this.realKeyClick0x11(46);
                           }
                        } else {
                           this.realKeyClick0x11(45);
                        }
                     } else {
                        this.realKeyClick0x11(15);
                     }
                  } else {
                     this.realKeyClick0x11(14);
                  }
               } else {
                  this.realKeyClick0x11(3);
               }
            } else {
               this.realKeyClick0x11(8);
            }
         } else {
            this.realKeyClick0x11(7);
         }
      } else {
         this.realKeyClick0x11(0);
      }

   }

   private void startTimer(long var1, int var3) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1, (long)var3);
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      AMapUtils.getInstance().startAMap(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 49) {
               if (var3 != 50) {
                  if (var3 != 65) {
                     if (var3 != 98) {
                        if (var3 != 166) {
                           if (var3 != 232) {
                              if (var3 == 240) {
                                 this.setVersionInfo0xF0();
                              }
                           } else {
                              this.setOriginalCarVideoStatus0xE8();
                           }
                        } else {
                           this.setAmplifierInfo0xA6();
                        }
                     } else {
                        this.setSettingInfo0x62();
                     }
                  } else {
                     this.setRadarInfo0x41();
                  }
               } else {
                  this.setCarInfo0x32();
               }
            } else {
               this.setAirInfo0x31();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
         this.setTrackDate0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 0, (byte)var8, (byte)var6}, 12));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
      this.initDriveItem(this.getUiMgr(var1).getDriverDataPageUiSet(var1));
      this.initSettingsItem(this.getUiMgr(this.mContext).getSettingUiSet(var1));
   }

   public void onAMapCallBack(AMapEntity var1) {
      super.onAMapCallBack(var1);
      switch (var1.getCarDirection()) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 0});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 4});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 1});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 5});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 2});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 7});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 3});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 6});
      }

   }

   public void updateSettingPano(int var1, boolean var2) {
      ArrayList var3;
      if (var1 != 0) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  var3 = new ArrayList();
                  var3.add(new PanoramicBtnUpdateEntity(0, false));
                  var3.add(new PanoramicBtnUpdateEntity(1, false));
                  var3.add(new PanoramicBtnUpdateEntity(2, false));
                  var3.add(new PanoramicBtnUpdateEntity(3, false));
                  var3.add(new PanoramicBtnUpdateEntity(4, var2));
                  GeneralParkData.dataList = var3;
                  this.updateParkUi((Bundle)null, this.mContext);
               }
            } else {
               var3 = new ArrayList();
               var3.add(new PanoramicBtnUpdateEntity(0, false));
               var3.add(new PanoramicBtnUpdateEntity(1, false));
               var3.add(new PanoramicBtnUpdateEntity(2, false));
               var3.add(new PanoramicBtnUpdateEntity(3, var2));
               var3.add(new PanoramicBtnUpdateEntity(4, false));
               GeneralParkData.dataList = var3;
               this.updateParkUi((Bundle)null, this.mContext);
            }
         } else {
            var3 = new ArrayList();
            var3.add(new PanoramicBtnUpdateEntity(0, false));
            var3.add(new PanoramicBtnUpdateEntity(1, false));
            var3.add(new PanoramicBtnUpdateEntity(2, var2));
            var3.add(new PanoramicBtnUpdateEntity(3, false));
            var3.add(new PanoramicBtnUpdateEntity(4, false));
            GeneralParkData.dataList = var3;
            this.updateParkUi((Bundle)null, this.mContext);
         }
      } else {
         var3 = new ArrayList();
         var3.add(new PanoramicBtnUpdateEntity(0, var2));
         var3.add(new PanoramicBtnUpdateEntity(1, false));
         var3.add(new PanoramicBtnUpdateEntity(2, false));
         var3.add(new PanoramicBtnUpdateEntity(3, false));
         var3.add(new PanoramicBtnUpdateEntity(4, false));
         GeneralParkData.dataList = var3;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private static class DriveDataUpdateHelper {
      private DriverUpdateEntity entity;

      public DriveDataUpdateHelper(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity getEntity() {
         return this.entity;
      }

      public void setEntity(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity setValue(String var1) {
         this.entity.setValue(var1);
         return this.entity;
      }
   }

   private static class SettingUpdateHelper {
      private SettingUpdateEntity entity;
      private int progressMin;

      public SettingUpdateHelper(SettingUpdateEntity var1) {
         this(var1, 0);
      }

      public SettingUpdateHelper(SettingUpdateEntity var1, int var2) {
         this.entity = var1;
         this.progressMin = var2;
      }

      public SettingUpdateEntity getEntity() {
         return this.entity;
      }

      public SettingUpdateEntity setEnable(boolean var1) {
         this.entity.setEnable(var1);
         return this.entity;
      }

      public SettingUpdateEntity setValue(Object var1) {
         if (var1 instanceof Integer) {
            SettingUpdateEntity var2 = this.entity;
            Integer var3 = (Integer)var1;
            var2.setValue(var3 + this.progressMin);
            this.entity.setProgress(var3);
         } else {
            this.entity.setValue(var1);
         }

         return this.entity;
      }
   }
}
