package com.hzbhd.canbus.car._464;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   public static final int AUX_MODE = 252;
   public static final int CD_DVD_PLAY_MODE = 250;
   public static final int CD_MODE = 255;
   public static final int DVD_MODE = 254;
   public static final int MEDIA_OFF_MODE = 253;
   public static final int RADIO_MODE = 251;
   public static final int REAR_DISC_MODE = 249;
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   int[] mAmplData;
   int[] mCarDoorData;
   Context mContext;
   int[] mDeviceData;
   int[] mDiscData;
   int[] mFrontRadarData;
   private SparseArray mOriginalDeviceDataArray;
   int[] mRearRadarData;
   int[] mTrackData;
   private UiMgr mUiMgr;
   public int nowModeTag = 255;

   private String getCycleState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "NONE" : this.mContext.getString(2131766565);
         } else {
            return this.mContext.getString(2131766564);
         }
      } else {
         return this.mContext.getString(2131766563);
      }
   }

   private String getOrderState(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "NONE" : this.mContext.getString(2131766568);
      } else {
         return this.mContext.getString(2131766567);
      }
   }

   private String getRadioRunState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            switch (var1) {
               case 16:
                  return "AM";
               case 17:
                  return "AM1";
               case 18:
                  return "AM2";
               default:
                  return "FM";
            }
         } else {
            return "FM2";
         }
      } else {
         return "FM1";
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initCarConfig(Context var1) {
      if (this.getCurrentEachCanId() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 101});
      }

      if (this.getCurrentEachCanId() == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 100});
      }

   }

   private void initOriginalDeviceDataArray() {
      this.mOriginalDeviceDataArray = new SparseArray();
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_0", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_1", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_3", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_4", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_5", (String)null));
      this.mOriginalDeviceDataArray.put(255, new OriginalDeviceData(var1, new String[]{"prev_disc", "left", "up", "radio_scan", "down", "right", "next_disc", "random", "repeat"}));
      var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_0", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_1", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_3", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_4", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_5", (String)null));
      this.mOriginalDeviceDataArray.put(254, new OriginalDeviceData(var1, new String[]{"left", "up", "play", "pause", "stop", "down", "right"}));
      var1 = new ArrayList();
      this.mOriginalDeviceDataArray.put(253, new OriginalDeviceData(var1, new String[0]));
      var1 = new ArrayList();
      this.mOriginalDeviceDataArray.put(252, new OriginalDeviceData(var1, new String[0]));
      var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_0", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_1", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_3", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_4", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_5", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_6", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_7", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_8", (String)null));
      this.mOriginalDeviceDataArray.put(251, new OriginalDeviceData(var1, new String[]{"left", "up", "radio_scan", "down", "right"}));
      var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_0", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_1", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_3", (String)null));
      this.mOriginalDeviceDataArray.put(250, new OriginalDeviceData(var1, new String[0]));
      var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_rear_disc_1", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_rear_disc_2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_rear_disc_3", (String)null));
      this.mOriginalDeviceDataArray.put(249, new OriginalDeviceData(var1, new String[0]));
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAmplDataChange(int[] var1) {
      if (Arrays.equals(this.mAmplData, var1)) {
         return false;
      } else {
         this.mAmplData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDeviceDataNoChange(int[] var1) {
      if (Arrays.equals(this.mDeviceData, var1)) {
         return true;
      } else {
         this.mDeviceData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDiscDataNoChange(int[] var1) {
      if (Arrays.equals(this.mDiscData, var1)) {
         return true;
      } else {
         this.mDiscData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setAir0x28(int[] var1) {
      this.updateOutDoorTemp(this.mContext, (float)var1[7] / 2.0F - 40.0F + this.getTempUnitC(this.mContext));
      var1[7] = 0;
      int[] var4 = this.mAirData;
      boolean var2;
      if (var4 == null || var4.length == 0 || var1[8] == var4[8] && var1[9] == var4[9] && var1[10] == var4[10]) {
         var2 = false;
      } else {
         var2 = true;
      }

      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit5(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
         int var3 = var1[4];
         if (var3 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var3 == 31) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (double)((float)var1[4] / 2.0F) + 17.5 + this.getTempUnitC(this.mContext);
         }

         var3 = var1[5];
         if (var3 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var3 == 31) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = (double)((float)var1[5] / 2.0F) + 17.5 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(var1[6]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(var1[6]);
         var3 = var1[8];
         if (var3 == 0) {
            GeneralAirData.rear_left_temperature = "LO";
         } else if (var3 == 31) {
            GeneralAirData.rear_left_temperature = "HI";
         } else {
            GeneralAirData.rear_left_temperature = (double)((float)var1[8] / 2.0F) + 17.5 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(var1[9]);
         GeneralAirData.rear_temperature = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 3) + "L";
         var3 = var1[10];
         if (var3 == 0) {
            GeneralAirData.rear_right_temperature = "LO";
         } else if (var3 == 31) {
            GeneralAirData.rear_right_temperature = "HI";
         } else {
            GeneralAirData.rear_right_temperature = (double)((float)var1[10] / 2.0F) + 17.5 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var1[11]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[11]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[11]);
         if (var2) {
            this.updateAirActivity(this.mContext, 1002);
         } else {
            this.updateAirActivity(this.mContext, 1001);
         }
      }

   }

   private void setAmpl0x31(int[] var1) {
      if (this.isAmplDataChange(var1)) {
         GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4) - 7;
         GeneralAmplifierData.frontRear = -DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 4) + 7;
         GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4) - 2;
         GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(var1[4], 4, 4) - 2;
         GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) - 2;
         GeneralAmplifierData.volume = var1[5];
         this.updateAmplifierActivity(new Bundle());
         ArrayList var6 = new ArrayList();
         int var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl");
         int var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_0");
         int var2;
         Context var5;
         if (DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 1) == 0) {
            var5 = this.mContext;
            var2 = 2131766537;
         } else {
            var5 = this.mContext;
            var2 = 2131766538;
         }

         var6.add((new SettingUpdateEntity(var3, var4, var5.getString(var2))).setValueStr(true));
         var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_1"), DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 2)));
         this.updateGeneralSettingData(var6);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setAmpl0x32(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_4"), DataHandleUtils.getIntFromByteWithBit(var1[2], 6, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_5"), DataHandleUtils.getIntFromByteWithBit(var1[2], 7, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAux(int[] var1) {
      GeneralOriginalCarDeviceData.mList = new ArrayList();
      GeneralOriginalCarDeviceData.cdStatus = "AUX";
      GeneralOriginalCarDeviceData.runningState = "Running";
      this.nowModeTag = 252;
      OriginalDeviceData var2 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(252);
      OriginalCarDevicePageUiSet var3 = this.getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      var3.setItems(var2.getItemList());
      var3.setRowBottomBtnAction(var2.getBottomBtns());
      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private void setBasicInfo0x24(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setCdDvdInfo(int[] var1) {
      ArrayList var5 = new ArrayList();
      GeneralOriginalCarDeviceData.mList = var5;
      GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
      GeneralOriginalCarDeviceData.runningState = "Disc " + DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4);
      var5.add(new OriginalCarDeviceUpdateEntity(0, this.getCycleState(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4))));
      var5.add(new OriginalCarDeviceUpdateEntity(1, this.getOrderState(DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4))));
      int var3 = DataHandleUtils.getMsbLsbResult(var1[7], var1[6]);
      int var2 = DataHandleUtils.getMsbLsbResult(var1[9], var1[8]);
      String var4 = this.formatInteger2.format((long)var1[10]) + ":" + this.formatInteger2.format((long)var1[11]) + ":" + this.formatInteger2.format((long)var1[12]);
      String var6 = this.formatInteger2.format((long)var1[13]) + ":" + this.formatInteger2.format((long)var1[14]) + ":" + this.formatInteger2.format((long)var1[15]);
      var5.add(new OriginalCarDeviceUpdateEntity(2, var2 + "/" + var3));
      var5.add(new OriginalCarDeviceUpdateEntity(3, var6 + "/" + var4));
      this.nowModeTag = 250;
      OriginalDeviceData var9 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(250);
      OriginalCarDevicePageUiSet var7 = this.getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      var7.setItems(var9.getItemList());
      var7.setRowBottomBtnAction(var9.getBottomBtns());
      Bundle var8 = new Bundle();
      var8.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var8);
   }

   private void setDevice0x62(int[] var1) {
      if (!this.isDeviceDataNoChange(var1)) {
         int var2 = var1[2];
         if (var2 == 0) {
            this.setMediaOff(var1);
         } else if (var2 == 1) {
            this.setRadioInfo(var1);
         } else if (var2 == 2) {
            this.setCdDvdInfo(var1);
         } else if (var2 == 3) {
            this.setAux(var1);
         } else if (var2 == 4) {
            this.setRearDisc(var1);
         }

      }
   }

   private void setDiscInfo0x61(int[] var1) {
      if (!this.isDiscDataNoChange(var1)) {
         String var10;
         if (DataHandleUtils.getBoolBit0(var1[2])) {
            var10 = this.mContext.getString(2131766572);
         } else {
            var10 = this.mContext.getString(2131766571);
         }

         String var11;
         if (DataHandleUtils.getBoolBit1(var1[2])) {
            var11 = this.mContext.getString(2131766572);
         } else {
            var11 = this.mContext.getString(2131766571);
         }

         String var12;
         if (DataHandleUtils.getBoolBit2(var1[2])) {
            var12 = this.mContext.getString(2131766572);
         } else {
            var12 = this.mContext.getString(2131766571);
         }

         String var13;
         if (DataHandleUtils.getBoolBit3(var1[2])) {
            var13 = this.mContext.getString(2131766572);
         } else {
            var13 = this.mContext.getString(2131766571);
         }

         String var14;
         if (DataHandleUtils.getBoolBit4(var1[2])) {
            var14 = this.mContext.getString(2131766572);
         } else {
            var14 = this.mContext.getString(2131766571);
         }

         String var15;
         if (DataHandleUtils.getBoolBit5(var1[2])) {
            var15 = this.mContext.getString(2131766572);
         } else {
            var15 = this.mContext.getString(2131766571);
         }

         String var16;
         if (!DataHandleUtils.getBoolBit0(var1[5])) {
            var16 = this.mContext.getString(2131766597);
         } else {
            var16 = this.mContext.getString(2131766598);
         }

         String var17;
         if (!DataHandleUtils.getBoolBit1(var1[5])) {
            var17 = this.mContext.getString(2131766597);
         } else {
            var17 = this.mContext.getString(2131766598);
         }

         String var18;
         if (!DataHandleUtils.getBoolBit2(var1[5])) {
            var18 = this.mContext.getString(2131766597);
         } else {
            var18 = this.mContext.getString(2131766598);
         }

         String var19;
         if (!DataHandleUtils.getBoolBit3(var1[5])) {
            var19 = this.mContext.getString(2131766597);
         } else {
            var19 = this.mContext.getString(2131766598);
         }

         String var20;
         if (!DataHandleUtils.getBoolBit4(var1[5])) {
            var20 = this.mContext.getString(2131766597);
         } else {
            var20 = this.mContext.getString(2131766598);
         }

         String var21;
         if (!DataHandleUtils.getBoolBit5(var1[5])) {
            var21 = this.mContext.getString(2131766597);
         } else {
            var21 = this.mContext.getString(2131766598);
         }

         String var3;
         String var5;
         String var6;
         String var7;
         String var8;
         String var9;
         String var22;
         label155: {
            String var4;
            label154: {
               label153: {
                  label152: {
                     int var2 = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4);
                     var6 = "DISC1";
                     if (var2 == 1) {
                        if (var16.equals("CD")) {
                           this.nowModeTag = 255;
                        } else {
                           this.nowModeTag = 254;
                        }

                        var3 = "●";
                        var5 = "";
                     } else {
                        if (DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) != 2) {
                           if (DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) != 3) {
                              if (DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) != 4) {
                                 if (DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) == 5) {
                                    if (var20.equals("CD")) {
                                       this.nowModeTag = 255;
                                    } else {
                                       this.nowModeTag = 254;
                                    }

                                    var4 = "DISC4";
                                    var8 = "●";
                                    var3 = "";
                                    var5 = "";
                                    var6 = var5;
                                    var7 = var5;
                                    var9 = var5;
                                 } else {
                                    if (DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) != 6) {
                                       String var23 = "";
                                       var22 = "";
                                       var9 = var22;
                                       var8 = var22;
                                       var5 = var6;
                                       var7 = var22;
                                       var6 = var22;
                                       var3 = var22;
                                       var22 = var23;
                                       break label155;
                                    }

                                    if (var21.equals("CD")) {
                                       this.nowModeTag = 255;
                                    } else {
                                       this.nowModeTag = 254;
                                    }

                                    var4 = "DISC5";
                                    var9 = "●";
                                    var3 = "";
                                    var5 = "";
                                    var6 = var5;
                                    var7 = var5;
                                    var8 = var5;
                                 }
                                 break label154;
                              }

                              if (var19.equals("CD")) {
                                 this.nowModeTag = 255;
                              } else {
                                 this.nowModeTag = 254;
                              }

                              var4 = "DISC3";
                              var7 = "●";
                              var3 = "";
                              var5 = "";
                              var6 = var5;
                              var8 = var5;
                              break label153;
                           }

                           if (var18.equals("CD")) {
                              this.nowModeTag = 255;
                           } else {
                              this.nowModeTag = 254;
                           }

                           var4 = "DISC2";
                           var6 = "●";
                           var7 = "";
                           var5 = "";
                           var3 = var7;
                           break label152;
                        }

                        if (var17.equals("CD")) {
                           this.nowModeTag = 255;
                        } else {
                           this.nowModeTag = 254;
                        }

                        var5 = "●";
                        var3 = "";
                     }

                     var7 = "";
                     var4 = var6;
                     var6 = var7;
                  }

                  var7 = "";
                  var8 = "";
               }

               var9 = var8;
            }

            var22 = var3;
            var3 = var5;
            var5 = var4;
         }

         String var24;
         if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 0) {
            var24 = this.mContext.getString(2131766590);
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 1) {
            var24 = this.mContext.getString(2131766591);
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 2) {
            var24 = this.mContext.getString(2131766592);
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 3) {
            var24 = this.mContext.getString(2131766593);
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 4) {
            var24 = this.mContext.getString(2131766594);
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 5) {
            var24 = this.mContext.getString(2131766595);
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 15) {
            var24 = this.mContext.getString(2131766596);
         } else {
            var24 = "NONE";
         }

         ArrayList var27 = new ArrayList();
         var27.add(new OriginalCarDeviceUpdateEntity(0, ": " + var10 + "  " + var16 + "  " + var22));
         var27.add(new OriginalCarDeviceUpdateEntity(1, ": " + var11 + "  " + var17 + "  " + var3));
         var27.add(new OriginalCarDeviceUpdateEntity(2, ": " + var12 + "  " + var18 + "  " + var6));
         var27.add(new OriginalCarDeviceUpdateEntity(3, ": " + var13 + "  " + var19 + "  " + var7));
         var27.add(new OriginalCarDeviceUpdateEntity(4, ": " + var14 + "  " + var20 + "  " + var8));
         var27.add(new OriginalCarDeviceUpdateEntity(5, ": " + var15 + "  " + var21 + "  " + var9));
         GeneralOriginalCarDeviceData.mList = var27;
         GeneralOriginalCarDeviceData.cdStatus = var5;
         GeneralOriginalCarDeviceData.runningState = var24;
         OriginalDeviceData var28 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(this.nowModeTag);
         OriginalCarDevicePageUiSet var25 = this.getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
         var25.setItems(var28.getItemList());
         var25.setRowBottomBtnAction(var28.getBottomBtns());
         Bundle var26 = new Bundle();
         var26.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var26);
      }
   }

   private void setDriveDate0x35(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_0"), (float)DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) / 10.0F + "L/100km"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_1"), (float)DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) / 10.0F + "L/100km"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_2"), DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) + "km"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_3"), DataHandleUtils.getMsbLsbResult(var1[8], var1[9]) + "km/h"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_4"), DataHandleUtils.getMsbLsbResult(var1[10], var1[11]) + "km"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadarInfo(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setMediaOff(int[] var1) {
      GeneralOriginalCarDeviceData.mList = new ArrayList();
      GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
      GeneralOriginalCarDeviceData.runningState = "NONE";
      this.nowModeTag = 253;
      OriginalDeviceData var3 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(253);
      OriginalCarDevicePageUiSet var2 = this.getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      var2.setItems(var3.getItemList());
      var2.setRowBottomBtnAction(var3.getBottomBtns());
      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private void setPanelKry0x64(int[] var1) {
      int var2 = var1[2];
      if (var2 != 16) {
         if (var2 != 17) {
            switch (var2) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var1[3]);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 77, var1[3]);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 76, var1[3]);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 76, var1[3]);
                  break;
               case 4:
                  this.realKeyLongClick1(this.mContext, 141, var1[3]);
                  break;
               case 5:
                  this.realKeyLongClick1(this.mContext, 128, var1[3]);
                  break;
               case 6:
                  this.realKeyLongClick1(this.mContext, 41, var1[3]);
                  break;
               case 7:
                  this.realKeyLongClick1(this.mContext, 151, var1[3]);
                  break;
               case 8:
                  this.realKeyLongClick1(this.mContext, 39, var1[3]);
                  break;
               case 9:
                  this.realKeyLongClick1(this.mContext, 57, var1[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 40, var1[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 53, var1[3]);
      }

   }

   private void setRadioInfo(int[] var1) {
      ArrayList var5 = new ArrayList();
      GeneralOriginalCarDeviceData.mList = var5;
      GeneralOriginalCarDeviceData.cdStatus = "RADIO";
      GeneralOriginalCarDeviceData.runningState = this.getRadioRunState(var1[4]);
      int var2;
      if (var1[3] == 0) {
         boolean var3 = DataHandleUtils.getBoolBit7(var1[5]);
         Context var4 = this.mContext;
         if (var3) {
            var2 = 2131766575;
         } else {
            var2 = 2131766574;
         }

         var5.add(new OriginalCarDeviceUpdateEntity(0, var4.getString(var2)));
         String var8;
         if (DataHandleUtils.getBoolBit5(var1[5])) {
            var8 = "ON";
         } else {
            var8 = "OFF";
         }

         var5.add(new OriginalCarDeviceUpdateEntity(1, var8));
         if (var1[7] != 255 && var1[6] != 255) {
            var2 = var1[4];
            if (var2 != 0 && var2 != 1 && var2 != 2) {
               if (var2 == 16 || var2 == 17 || var2 == 18) {
                  var5.add(new OriginalCarDeviceUpdateEntity(2, DataHandleUtils.getMsbLsbResult(var1[7], var1[6]) + "kHz"));
               }
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(2, (float)DataHandleUtils.getMsbLsbResult(var1[7], var1[6]) / 100.0F + "mHz"));
            }
         } else {
            var5.add(new OriginalCarDeviceUpdateEntity(2, "NONE"));
         }
      } else {
         var2 = var1[4];
         if (var2 != 0 && var2 != 1 && var2 != 2) {
            if (var2 == 16 || var2 == 17 || var2 == 18) {
               if (var1[6] != 255 && var1[5] != 255) {
                  var5.add(new OriginalCarDeviceUpdateEntity(3, DataHandleUtils.getMsbLsbResult(var1[6], var1[5]) + "kHz"));
               } else {
                  var5.add(new OriginalCarDeviceUpdateEntity(3, "NONE"));
               }

               if (var1[8] != 255 && var1[7] != 255) {
                  var5.add(new OriginalCarDeviceUpdateEntity(4, DataHandleUtils.getMsbLsbResult(var1[8], var1[7]) + "kHz"));
               } else {
                  var5.add(new OriginalCarDeviceUpdateEntity(4, "NONE"));
               }

               if (var1[10] != 255 && var1[9] != 255) {
                  var5.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getMsbLsbResult(var1[10], var1[9]) + "kHz"));
               } else {
                  var5.add(new OriginalCarDeviceUpdateEntity(5, "NONE"));
               }

               if (var1[12] != 255 && var1[11] != 255) {
                  var5.add(new OriginalCarDeviceUpdateEntity(6, DataHandleUtils.getMsbLsbResult(var1[12], var1[11]) + "kHz"));
               } else {
                  var5.add(new OriginalCarDeviceUpdateEntity(6, "NONE"));
               }

               if (var1[14] != 255 && var1[13] != 255) {
                  var5.add(new OriginalCarDeviceUpdateEntity(7, DataHandleUtils.getMsbLsbResult(var1[14], var1[13]) + "kHz"));
               } else {
                  var5.add(new OriginalCarDeviceUpdateEntity(7, "NONE"));
               }

               if (var1[16] != 255 && var1[15] != 255) {
                  var5.add(new OriginalCarDeviceUpdateEntity(8, DataHandleUtils.getMsbLsbResult(var1[16], var1[15]) + "kHz"));
               } else {
                  var5.add(new OriginalCarDeviceUpdateEntity(8, "NONE"));
               }
            }
         } else {
            if (var1[6] != 255 && var1[5] != 255) {
               var5.add(new OriginalCarDeviceUpdateEntity(3, (float)DataHandleUtils.getMsbLsbResult(var1[6], var1[5]) / 100.0F + "mHz"));
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(3, "NONE"));
            }

            if (var1[8] != 255 && var1[7] != 255) {
               var5.add(new OriginalCarDeviceUpdateEntity(4, (float)DataHandleUtils.getMsbLsbResult(var1[8], var1[7]) / 100.0F + "mHz"));
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(4, "NONE"));
            }

            if (var1[10] != 255 && var1[9] != 255) {
               var5.add(new OriginalCarDeviceUpdateEntity(5, (float)DataHandleUtils.getMsbLsbResult(var1[10], var1[9]) / 100.0F + "mHz"));
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(5, "NONE"));
            }

            if (var1[12] != 255 && var1[11] != 255) {
               var5.add(new OriginalCarDeviceUpdateEntity(6, (float)DataHandleUtils.getMsbLsbResult(var1[12], var1[11]) / 100.0F + "mHz"));
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(6, "NONE"));
            }

            if (var1[14] != 255 && var1[13] != 255) {
               var5.add(new OriginalCarDeviceUpdateEntity(7, (float)DataHandleUtils.getMsbLsbResult(var1[14], var1[13]) / 100.0F + "mHz"));
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(7, "NONE"));
            }

            if (var1[16] != 255 && var1[15] != 255) {
               var5.add(new OriginalCarDeviceUpdateEntity(8, (float)DataHandleUtils.getMsbLsbResult(var1[16], var1[15]) / 100.0F + "mHz"));
            } else {
               var5.add(new OriginalCarDeviceUpdateEntity(8, "NONE"));
            }
         }
      }

      this.nowModeTag = 251;
      OriginalDeviceData var9 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(251);
      OriginalCarDevicePageUiSet var6 = this.getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      var6.setItems(var9.getItemList());
      var6.setRowBottomBtnAction(var9.getBottomBtns());
      Bundle var7 = new Bundle();
      var7.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var7);
   }

   private void setRearDisc(int[] var1) {
      ArrayList var4 = new ArrayList();
      int var2;
      Context var3;
      if (DataHandleUtils.getBoolBit6(var1[6])) {
         var3 = this.mContext;
         var2 = 2131766587;
      } else {
         var3 = this.mContext;
         var2 = 2131766586;
      }

      var4.add(new OriginalCarDeviceUpdateEntity(0, var3.getString(var2)));
      var4.add(new OriginalCarDeviceUpdateEntity(1, DataHandleUtils.getMsbLsbResult(var1[8], var1[7]) + ""));
      var4.add(new OriginalCarDeviceUpdateEntity(2, this.formatInteger2.format((long)var1[9]) + ":" + this.formatInteger2.format((long)var1[10]) + ":" + this.formatInteger2.format((long)var1[11])));
      String var5;
      if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 0) {
         var5 = this.mContext.getString(2131766590);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 1) {
         var5 = this.mContext.getString(2131766591);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 2) {
         var5 = this.mContext.getString(2131766592);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 3) {
         var5 = this.mContext.getString(2131766593);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 4) {
         var5 = this.mContext.getString(2131766594);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 5) {
         var5 = this.mContext.getString(2131766595);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4) == 15) {
         var5 = this.mContext.getString(2131766596);
      } else {
         var5 = "NONE";
      }

      GeneralOriginalCarDeviceData.mList = var4;
      GeneralOriginalCarDeviceData.cdStatus = this.mContext.getString(2131766584);
      GeneralOriginalCarDeviceData.runningState = var5;
      this.nowModeTag = 249;
      OriginalDeviceData var6 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(249);
      OriginalCarDevicePageUiSet var7 = this.getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      var7.setItems(var6.getItemList());
      var7.setRowBottomBtnAction(var6.getBottomBtns());
      Bundle var8 = new Bundle();
      var8.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var8);
   }

   private void setRearRadarInfo(int[] var1) {
      if (this.isRearRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSpeed0x16(int[] var1) {
      this.updateSpeedInfo((int)((float)DataHandleUtils.getMsbLsbResult(var1[3], var1[2]) / 16.0F));
   }

   private void setSpeed0x50(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_5"), DataHandleUtils.getMsbLsbResult(var1[3], var1[2]) + "rpm"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSwc0x20(int[] var1) {
      int var2 = var1[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 19) {
                  if (var2 != 20) {
                     if (var2 != 135) {
                        switch (var2) {
                           case 7:
                              this.realKeyLongClick1(this.mContext, 2, var1[3]);
                              break;
                           case 8:
                              this.realKeyLongClick1(this.mContext, 187, var1[3]);
                              break;
                           case 9:
                              this.realKeyLongClick1(this.mContext, 14, var1[3]);
                              break;
                           case 10:
                              this.realKeyLongClick1(this.mContext, 15, var1[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 3, var1[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46, var1[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45, var1[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var1[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var1[3]);
      }

   }

   private void setSwc0x60Mode(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_settings_swc"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_settings_swc", "_464_settings_swc0"), var1[2]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrack0x29(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         boolean var3 = DataHandleUtils.getBoolBit3(var1[3]);
         int var2 = DataHandleUtils.blockBit(var1[3], 3);
         var1[3] = var2;
         var2 = DataHandleUtils.blockBit(var2, 4);
         var1[3] = var2;
         var2 = DataHandleUtils.blockBit(var2, 5);
         var1[3] = var2;
         var2 = DataHandleUtils.blockBit(var2, 6);
         var1[3] = var2;
         var2 = DataHandleUtils.blockBit(var2, 7);
         var1[3] = var2;
         if (var3) {
            GeneralParkData.trackAngle = (4095 - (var1[2] & 255 | (var2 & 255) << 8)) / 15 - 136;
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)var1[2], (byte)var2, 0, 380, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   private void setVersionInfo0x30(byte[] var1) {
      this.updateVersionInfo(this.mContext, this.getVersionStr(var1));
   }

   private void setYGInfo0x65(int[] var1) {
      int var2 = var1[2];
      switch (var2) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 48, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 48, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 48, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 47, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 47, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 47, var1[3]);
            break;
         default:
            switch (var2) {
               case 16:
                  this.realKeyClick4(this.mContext, 48);
                  break;
               case 17:
                  this.realKeyClick4(this.mContext, 47);
                  break;
               case 18:
                  this.realKeyLongClick1(this.mContext, 49, var1[3]);
                  break;
               case 19:
                  this.realKeyLongClick1(this.mContext, 50, var1[3]);
                  break;
               case 20:
                  this.realKeyLongClick1(this.mContext, 151, var1[3]);
                  break;
               case 21:
                  this.realKeyLongClick1(this.mContext, 53, var1[3]);
                  break;
               case 22:
                  this.realKeyLongClick1(this.mContext, 128, var1[3]);
            }
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 22) {
         if (var3 != 32) {
            if (var3 != 36) {
               if (var3 != 53) {
                  if (var3 != 80) {
                     if (var3 != 29) {
                        if (var3 != 30) {
                           if (var3 != 40) {
                              if (var3 != 41) {
                                 if (var3 != 100) {
                                    if (var3 != 101) {
                                       switch (var3) {
                                          case 48:
                                             this.setVersionInfo0x30(var2);
                                             break;
                                          case 49:
                                             this.setAmpl0x31(var4);
                                             break;
                                          case 50:
                                             this.setAmpl0x32(var4);
                                             break;
                                          default:
                                             switch (var3) {
                                                case 96:
                                                   this.setSwc0x60Mode(var4);
                                                   break;
                                                case 97:
                                                   this.setDiscInfo0x61(var4);
                                                   break;
                                                case 98:
                                                   this.setDevice0x62(var4);
                                             }
                                       }
                                    } else {
                                       this.setYGInfo0x65(var4);
                                    }
                                 } else {
                                    this.setPanelKry0x64(var4);
                                 }
                              } else {
                                 this.setTrack0x29(var4);
                              }
                           } else {
                              this.setAir0x28(var4);
                           }
                        } else {
                           this.setRearRadarInfo(var4);
                        }
                     } else {
                        this.setFrontRadarInfo(var4);
                     }
                  } else {
                     this.setSpeed0x50(var4);
                  }
               } else {
                  this.setDriveDate0x35(var4);
               }
            } else {
               this.setBasicInfo0x24(var4);
            }
         } else {
            this.setSwc0x20(var4);
         }
      } else {
         this.setSpeed0x16(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initCarConfig(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalDeviceDataArray();
   }

   private static class OriginalDeviceData {
      private final String[] bottomBtns;
      private final List list;

      public OriginalDeviceData(List var1) {
         this(var1, (String[])null);
      }

      public OriginalDeviceData(List var1, String[] var2) {
         this.list = var1;
         this.bottomBtns = var2;
      }

      public String[] getBottomBtns() {
         return this.bottomBtns;
      }

      public List getItemList() {
         return this.list;
      }
   }
}
