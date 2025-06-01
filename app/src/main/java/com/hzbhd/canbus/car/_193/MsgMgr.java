package com.hzbhd.canbus.car._193;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
   static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
   private static final String TAG = "123";
   DecimalFormat decimalFormat;
   int differentId;
   private int eachId;
   int[] mAirData;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private byte mFreqHi;
   private byte mFreqLo;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private MsgMgr msgMgr;
   private UiMgr uiMgr;

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

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
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

   private boolean isSupport0x10() {
      int var1 = this.eachId;
      return var1 != 2 && var1 != 3 && var1 != 8 && var1 != 9;
   }

   private boolean isSupport0x21() {
      switch (this.eachId) {
         case 2:
         case 3:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 13:
         case 14:
         case 16:
         case 17:
         case 18:
            return false;
         case 4:
         case 12:
         case 15:
         default:
            return true;
      }
   }

   private boolean isSupport0x22() {
      int var1 = this.eachId;
      return var1 != 11 && var1 != 13 && var1 != 16;
   }

   private boolean isSupport0x23() {
      int var1 = this.eachId;
      return var1 != 11 && var1 != 13 && var1 != 16;
   }

   private boolean isSupport0x40() {
      switch (this.eachId) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 11:
         case 12:
         case 13:
         case 14:
         case 16:
         case 17:
            return false;
         case 10:
         case 15:
         default:
            return true;
      }
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 30) {
         var2 = "HI";
      } else if (var1 >= 0 && var1 <= 29) {
         var2 = (float)(var1 + 35) / 2.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "---";
      }

      return var2;
   }

   private String resolveMode(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131762576);
      } else {
         var2 = this.mContext.getResources().getString(2131762626);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[3];
      String var2;
      if (var1 >= 0 && var1 <= 180) {
         if (GeneralAirData.fahrenheit_celsius) {
            var2 = (new DecimalFormat("0.0")).format((double)(((float)var1 / 2.0F - 40.0F) * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(this.mContext);
         } else {
            var2 = (float)var1 / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
         }
      } else {
         var2 = " ";
      }

      return var2;
   }

   private void sendMusic(String var1, String var2) {
      try {
         byte[] var4 = DataHandleUtils.exceptBOMHead(var1.getBytes("UTF8"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 18}, var4));
         var4 = DataHandleUtils.exceptBOMHead(var2.getBytes("UTF8"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 113, 18}, var4));
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   private void sendcarType() {
      if (this.eachId == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 1});
      }

      if (this.eachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 2});
      }

      if (this.eachId == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 3});
      }

      if (this.eachId == 8) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 4});
      }

      if (this.eachId == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 5});
      }

      if (this.eachId == 10) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 6});
      }

      if (this.eachId == 11) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 7});
      }

      if (this.eachId == 13) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 8});
      }

      if (this.eachId == 14) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 9});
      }

      if (this.eachId == 16) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 10});
      }

      if (this.eachId == 17) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 11});
      }

      if (this.eachId == 18) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 12});
      }

      if (this.eachId == 19) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 13});
      }

      if (this.eachId == 20) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 14});
      }

   }

   private void setAirData0x21() {
      if (!this.isSupport0x21()) {
         if (!this.isAirDataChange()) {
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            switch (this.mCanBusInfoInt[3]) {
               case 0:
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = false;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = false;
                  GeneralAirData.max_front = false;
                  break;
               case 1:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = false;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = false;
                  GeneralAirData.max_front = false;
                  break;
               case 2:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.max_front = false;
                  break;
               case 3:
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.max_front = false;
                  break;
               case 4:
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_defog = true;
                  GeneralAirData.max_front = false;
                  break;
               case 5:
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = false;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = false;
                  GeneralAirData.front_defog = true;
                  GeneralAirData.max_front = false;
                  break;
               case 6:
                  GeneralAirData.max_front = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowLittleLight = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isShowRev = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isShowHandBrake = true;
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadar0x23() {
      if (!this.isSupport0x23()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = false;
         if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_front_radar_enable", true)) {
            int[] var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         } else {
            RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
         }

         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setOutDoorTemp0x10() {
      int var1 = this.eachId;
      if (var1 == 2 || var1 == 3 || var1 == 8 || var1 == 9) {
         this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[3] * 0.5 - 55.0 + this.getTempUnitC(this.mContext));
      }
   }

   private void setRearRadar0x22() {
      if (!this.isSupport0x22()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = false;
         if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_rear_radar_enable", true)) {
            int[] var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         } else {
            RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
         }

         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setSettingstate0x40() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 254) {
         if (var1 != 255) {
            switch (var1) {
               case 0:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_0_0"), this.mCanBusInfoInt[3]));
                  break;
               case 1:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_0"), this.mCanBusInfoInt[3]));
                  break;
               case 2:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_1"), this.mCanBusInfoInt[3]));
                  break;
               case 3:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_2"), this.mCanBusInfoInt[3]));
                  break;
               case 4:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_0"), this.mCanBusInfoInt[3]));
                  break;
               case 5:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_1"), this.mCanBusInfoInt[3]));
                  break;
               case 6:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_0"), this.mCanBusInfoInt[3]));
                  break;
               case 7:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_1"), this.mCanBusInfoInt[3]));
                  break;
               case 8:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_2"), this.mCanBusInfoInt[3]));
                  break;
               case 9:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_0"), this.mCanBusInfoInt[3]));
                  break;
               case 10:
                  var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_1"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 1));
                  break;
               case 11:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_2"), this.mCanBusInfoInt[3]));
                  break;
               case 12:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_3"), this.mCanBusInfoInt[3]));
                  break;
               case 13:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_4"), this.mCanBusInfoInt[3]));
                  break;
               case 14:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_5"), this.mCanBusInfoInt[3]));
                  break;
               case 15:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_6"), this.mCanBusInfoInt[3]));
                  break;
               case 16:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_7"), this.mCanBusInfoInt[3]));
                  break;
               case 17:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_8"), this.mCanBusInfoInt[3]));
                  break;
               case 18:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_9"), this.mCanBusInfoInt[3]));
                  break;
               case 19:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_A"), this.mCanBusInfoInt[3]));
                  break;
               case 20:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_3"), this.mCanBusInfoInt[3]));
                  break;
               case 21:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_4"), this.mCanBusInfoInt[3]));
                  break;
               case 22:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_5"), this.mCanBusInfoInt[3]));
                  break;
               case 23:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_B"), this.mCanBusInfoInt[3]));
                  break;
               case 24:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_0"), this.mCanBusInfoInt[3]));
                  break;
               case 25:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_0"), this.mCanBusInfoInt[3]));
                  break;
               case 26:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_1"), this.mCanBusInfoInt[3]));
                  break;
               case 27:
                  var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_C"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 50));
                  break;
               case 28:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_7", "_1193_setting_7_0"), this.mCanBusInfoInt[3]));
                  break;
               case 29:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_7", "_1193_setting_7_1"), this.mCanBusInfoInt[3]));
                  break;
               case 30:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_1"), this.mCanBusInfoInt[3]));
                  break;
               case 31:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_2"), this.mCanBusInfoInt[3]));
                  break;
               case 32:
                  var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_3"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 1));
                  break;
               case 33:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_4"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 34:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_0"), this.mCanBusInfoInt[3]));
                  break;
               case 35:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_1"), this.mCanBusInfoInt[3]));
                  break;
               case 36:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_2"), this.mCanBusInfoInt[3]));
                  break;
               case 37:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_3"), this.mCanBusInfoInt[3]));
                  break;
               case 38:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_4"), this.mCanBusInfoInt[3]));
                  break;
               case 39:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_5"), this.mCanBusInfoInt[3]));
                  break;
               case 40:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_5_3"), this.mCanBusInfoInt[3]));
                  break;
               case 41:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_7", "_1193_setting_7_2"), this.mCanBusInfoInt[3]));
                  break;
               case 42:
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_2"), this.mCanBusInfoInt[3]));
            }
         } else {
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_9"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_9", "_1193_setting_9"), this.mCanBusInfoInt[3]));
         }
      } else {
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_3"), this.mCanBusInfoInt[3]));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingstate20x41() {
      if (this.eachId == 18) {
         ArrayList var3 = new ArrayList();
         ArrayList var6 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "seatDriveProfile"), this.resolveMode(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_D"), DataHandleUtils.getBoolBit0(var1)));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_E"), DataHandleUtils.getBoolBit1(var1)));
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_F"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 1));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_2"), this.mCanBusInfoInt[4]));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_4"), DataHandleUtils.getBoolBit2(var1)));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_5"), DataHandleUtils.getBoolBit3(var1)));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_10", "_1193_setting_10_0"), DataHandleUtils.getBoolBit4(var1)));
         var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_10", "_1193_setting_10_1"), DataHandleUtils.getBoolBit5(var1)));
         int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
         var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "nissan_raise_mileage_title");
         StringBuilder var5 = new StringBuilder();
         int[] var4 = this.mCanBusInfoInt;
         var6.add(new DriverUpdateEntity(var2, var1, var5.append((double)(var4[5] * 256 + var4[6]) * 0.1).append("KM").toString()));
         var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_16_min_value_1"), (double)this.mCanBusInfoInt[7] * 0.2 + "KMH/100KM"));
         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
         this.updateGeneralDriveData(var6);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 32768, 41472, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
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
            this.buttonKey(45);
            break;
         case 4:
            this.buttonKey(46);
            break;
         case 5:
            this.buttonKey(467);
            if (this.eachId == 18) {
               this.buttonKey(3);
            }
            break;
         case 6:
            this.buttonKey(468);
            break;
         case 7:
            this.buttonKey(2);
            break;
         case 8:
            this.buttonKey(187);
            break;
         default:
            switch (var1) {
               case 16:
                  this.buttonKey(76);
                  break;
               case 17:
                  this.buttonKey(77);
                  break;
               case 18:
                  this.buttonKey(1);
                  break;
               case 19:
                  this.buttonKey(21);
                  break;
               case 20:
                  this.buttonKey(20);
                  break;
               case 21:
                  this.buttonKey(3);
                  break;
               case 22:
                  this.buttonKey(30);
                  break;
               case 23:
                  this.buttonKey(2);
                  break;
               case 24:
                  this.buttonKey(75);
                  break;
               case 25:
                  this.buttonKey(58);
                  break;
               default:
                  switch (var1) {
                     case 32:
                        this.buttonKey(7);
                        break;
                     case 33:
                        this.buttonKey(8);
                        break;
                     case 34:
                        this.buttonKey(4);
                        break;
                     case 35:
                        this.buttonKey(151);
                        break;
                     case 36:
                        this.buttonKey(188);
                        break;
                     case 37:
                        this.buttonKey(62);
                        break;
                     case 38:
                        this.buttonKey(52);
                        break;
                     case 39:
                        this.buttonKey(220);
                        break;
                     case 40:
                        var1 = this.eachId;
                        if (var1 != 2 && var1 != 5 && var1 != 6 && var1 != 8 && var1 != 9) {
                           this.buttonKey(128);
                        } else {
                           this.buttonKey(467);
                        }
                        break;
                     case 41:
                        this.buttonKey(128);
                        break;
                     case 42:
                        this.buttonKey(182);
                        break;
                     case 43:
                        this.buttonKey(49);
                        break;
                     case 44:
                        this.buttonKey(45);
                        break;
                     case 45:
                        this.buttonKey(46);
                        break;
                     case 46:
                        this.buttonKey(7);
                        break;
                     case 47:
                        this.buttonKey(8);
                  }
            }
      }

   }

   private void tokingNowTime(int var1) {
      this.decimalFormat = new DecimalFormat("00");
      int var3 = var1 / 1000;
      int var2 = var3 % 3600;
      var1 = var2 / 60;
      var3 /= 3600;
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)(var2 % 60), (byte)var1, (byte)var3, 0});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 1});
      this.tokingNowTime(0);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1, 1});
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.tokingNowTime(var4);
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
      if (var3 != 16) {
         if (var3 != 41) {
            if (var3 != 48) {
               if (var3 != 64) {
                  if (var3 != 65) {
                     switch (var3) {
                        case 32:
                           this.setWheelKeyInfo0x20();
                           break;
                        case 33:
                           this.setAirData0x21();
                           break;
                        case 34:
                           this.setRearRadar0x22();
                           break;
                        case 35:
                           this.setFrontRadar0x23();
                           break;
                        case 36:
                           this.setDoorData0x24();
                     }
                  } else {
                     this.setSettingstate20x41();
                  }
               } else {
                  this.setSettingstate0x40();
               }
            } else {
               this.setVersionInfo0x30();
            }
         } else {
            this.setTrackData0x29();
         }
      } else {
         this.setOutDoorTemp0x10();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      int var3;
      if (this.eachId == 1) {
         var3 = var1;
         if (var1 > 28) {
            var3 = 28;
         }
      } else {
         var3 = var1;
         if (var1 > 31) {
            var3 = 31;
         }
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var3});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -90, 0, 0, 0, (byte)var8, (byte)var6, (byte)var7});
      this.sendcarType();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.sendcarType();
   }

   void initRadar() {
      RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
      RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var1 = (byte)(var4 & 255);
         var5 = (byte)(var4 >> 8 & 255);
         var2 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, var1, var5, var2, var9, var6, var7});
         this.sendMusic(var21, var23);
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

   void updateParkingBtn(int var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == 4) {
         var2.add(new PanoramicBtnUpdateEntity(var1, false));
      } else {
         var2.add(new PanoramicBtnUpdateEntity(var1, true));
      }

      this.updateParkUi((Bundle)null, this.mContext);
      this.updateParkingBtn(var1);
   }

   void updateSetting(int var1, int var2, int var3) {
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
