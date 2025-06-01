package com.hzbhd.canbus.car._185;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static int volKnobValue;
   private final int INVAILE_VALUE = -1;
   private int eachId;
   DecimalFormat format1 = new DecimalFormat("###0.00");
   DecimalFormat format2 = new DecimalFormat("000.0");
   DecimalFormat format3 = new DecimalFormat("00");
   DecimalFormat format4 = new DecimalFormat("000");
   int[] mAirData;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   int[] mRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private boolean mSeatBeltStatus;
   private boolean mSubSeatBeltStatus;
   String resulttemp;
   private UiMgr uiMgr;

   private void AirInfo0x31(int[] var1) {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem(var1));
      var1[13] = 0;
      if (!this.isAirDataNoChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var1[3]) ^ true;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(var1[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[4]);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_auto_wind_model = false;
         int var2 = var1[6];
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 3) {
                  if (var2 != 5) {
                     if (var2 != 6) {
                        switch (var2) {
                           case 12:
                              GeneralAirData.front_left_blow_head = false;
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_head = false;
                              GeneralAirData.front_right_blow_foot = true;
                              GeneralAirData.front_right_blow_window = true;
                              break;
                           case 13:
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_left_blow_foot = false;
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_head = true;
                              GeneralAirData.front_right_blow_foot = false;
                              GeneralAirData.front_right_blow_window = true;
                              break;
                           case 14:
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_head = true;
                              GeneralAirData.front_right_blow_foot = true;
                              GeneralAirData.front_right_blow_window = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_left_blow_window = false;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = false;
                        GeneralAirData.front_right_blow_window = false;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               GeneralAirData.front_auto_wind_model = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
         }

         switch (var1[7]) {
            case 0:
               GeneralAirData.front_wind_level = 0;
               break;
            case 1:
               GeneralAirData.front_wind_level = 1;
               break;
            case 2:
               GeneralAirData.front_wind_level = 2;
               break;
            case 3:
               GeneralAirData.front_wind_level = 3;
               break;
            case 4:
               GeneralAirData.front_wind_level = 4;
               break;
            case 5:
               GeneralAirData.front_wind_level = 5;
               break;
            case 6:
               GeneralAirData.front_wind_level = 6;
               break;
            case 7:
               GeneralAirData.front_wind_level = 7;
         }

         GeneralAirData.front_left_temperature = this.resolveAirTemperature(var1[8]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(var1[9]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void BodyDetailsInfo0x12(int[] var1) {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(var1[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(var1[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(var1[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(var1[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(var1[4]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void CarInfo0x11() {
      switch (this.mCanBusInfoInt[4]) {
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
            this.buttonKey(3);
            break;
         case 4:
            this.buttonKey(187);
            break;
         case 5:
            this.buttonKey(14);
            break;
         case 6:
            this.buttonKey(15);
         case 7:
         case 10:
         case 11:
         default:
            break;
         case 8:
            this.buttonKey(21);
            break;
         case 9:
            this.buttonKey(20);
            break;
         case 12:
            this.buttonKey(2);
      }

      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 10000, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void CentralControlInfo0x87() {
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_1");
      String var3;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_2");
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_2_0");
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_2_1");
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_3_0");
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_3_1");
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_3_2");
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_4_0");
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_4_1");
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_4");
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_3");
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_8");
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_6");
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_7");
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_0");
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131758591);
      } else {
         var3 = this.mContext.getResources().getString(2131758592);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_185_setting_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_A"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_6_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_6_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1)));
      this.updateGeneralSettingData(var5);
      this.updateGeneralDriveData(var4);
      this.updateSettingActivity((Bundle)null);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void OriginalCarStatusInformation0xE8() {
      Context var4 = this.mContext;
      int[] var5 = this.mCanBusInfoInt;
      int var1 = var5[4];
      boolean var3 = false;
      boolean var2;
      if (var1 != 1 && var5[5] != 1 && var5[6] != 1 && var5[7] != 1 && var5[8] != 1) {
         var2 = false;
      } else {
         var2 = true;
      }

      this.forceReverse(var4, var2);
      ArrayList var6 = new ArrayList();
      if (this.mCanBusInfoInt[7] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(0, var2));
      if (this.mCanBusInfoInt[8] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(1, var2));
      if (this.mCanBusInfoInt[6] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(2, var2));
      var2 = var3;
      if (this.mCanBusInfoInt[4] == 1) {
         var2 = true;
      }

      var6.add(new PanoramicBtnUpdateEntity(3, var2));
      GeneralParkData.dataList = var6;
      this.updateParkUi((Bundle)null, this.mContext);
      var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Right_camera_status"), this.mCanBusInfoInt[4]));
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_panorama_camera_status"), this.mCanBusInfoInt[5]));
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Left_camera_status"), this.mCanBusInfoInt[6]));
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Front_camera_status"), this.mCanBusInfoInt[7]));
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Rear_camera_status"), this.mCanBusInfoInt[8]));
      this.updateGeneralSettingData(var6);
      this.updateActivity((Bundle)null);
   }

   private void RadarInfo0x41() {
      if (!this.isRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var7 = this.mCanBusInfoInt;
         int var2 = var7[2];
         byte var5 = 0;
         int var1 = var2;
         if (var2 == 255) {
            var1 = 0;
         }

         int var3 = var7[3];
         var2 = var3;
         if (var3 == 255) {
            var2 = 0;
         }

         int var4 = var7[4];
         var3 = var4;
         if (var4 == 255) {
            var3 = 0;
         }

         int var6 = var7[5];
         var4 = var6;
         if (var6 == 255) {
            var4 = 0;
         }

         RadarInfoUtil.setRearRadarLocationData(4, var1, var2, var3, var4);
         var7 = this.mCanBusInfoInt;
         var2 = var7[6];
         var1 = var2;
         if (var2 == 255) {
            var1 = 0;
         }

         var3 = var7[7];
         var2 = var3;
         if (var3 == 255) {
            var2 = 0;
         }

         var4 = var7[8];
         var3 = var4;
         if (var4 == 255) {
            var3 = 0;
         }

         var4 = var7[9];
         if (var4 == 255) {
            var4 = var5;
         }

         RadarInfoUtil.setFrontRadarLocationData(4, var1, var2, var3, var4);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void VersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void WheelKeyInfo0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 37) {
               if (var1 != 43) {
                  if (var1 != 55) {
                     if (var1 != 67) {
                        if (var1 != 69) {
                           if (var1 == 70) {
                              this.buttonKey1(8);
                           }
                        } else {
                           this.buttonKey1(7);
                        }
                     } else {
                        this.buttonKey1(33);
                     }
                  } else {
                     this.buttonKey1(58);
                  }
               } else {
                  this.buttonKey1(52);
               }
            } else {
               this.buttonKey1(128);
            }
         } else {
            this.buttonKey1(1);
         }
      } else {
         this.buttonKey1(0);
      }

   }

   private void WheelSpinKey0x22() {
      if (this.mCanBusInfoInt[2] == 1) {
         int var1 = volKnobValue - this.mCanBusInfoByte[3];
         if (var1 < 0) {
            this.sendKnob_1(7, Math.abs(var1));
         } else if (var1 > 0) {
            this.sendKnob_1(8, Math.abs(var1));
         }

         volKnobValue = this.mCanBusInfoByte[3];
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

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void initCarMode() {
      switch (this.getCurrentEachCanId()) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 18, 53});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 11, 53});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 53});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 19, 53});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 20, 53});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 21, 53});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 22, 53});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 24, 53});
      }

   }

   private boolean isAirDataNoChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return true;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp && this.mSeatBeltStatus == GeneralDoorData.isSeatBeltTie && this.mSubSeatBeltStatus == GeneralDoorData.isSubSeatBeltTie) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
         this.mSubSeatBeltStatus = GeneralDoorData.isSubSeatBeltTie;
         this.mSeatBeltStatus = GeneralDoorData.isSeatBeltTie;
         return true;
      }
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   // $FF: synthetic method
   static void lambda$musicInfoChange$0(byte[] var0, byte[] var1) {
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -110}, var0));

      try {
         Thread.sleep(500L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -109}, var1));
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 254) {
         this.resulttemp = "Low_Temp";
      } else if (var1 == 255) {
         this.resulttemp = "High_Temp";
      } else {
         this.resulttemp = (float)var1 / 2.0F + this.getTempUnitC(this.mContext);
      }

      return this.resulttemp;
   }

   private String resolveOutDoorTem(int[] var1) {
      return (float)var1[13] / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private void sendKnob_1(int var1, int var2) {
      this.realKeyClick3_1(this.mContext, var1, 0, var2);
   }

   private void setSpeedInfo(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_185_speed1"), DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) + ""));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_185_speed2"), DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) + ""));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      this.initCarMode();
      this.getUiMgr(var1).initSettings();
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).sendMediaInfo0x91(133, "".getBytes());
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void buttonKey1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 49) {
                     if (var3 != 50) {
                        if (var3 != 65) {
                           if (var3 != 135) {
                              if (var3 != 232) {
                                 if (var3 == 240) {
                                    this.VersionInfo0xF0();
                                 }
                              } else {
                                 this.OriginalCarStatusInformation0xE8();
                              }
                           } else {
                              this.CentralControlInfo0x87();
                           }
                        } else {
                           this.RadarInfo0x41();
                        }
                     } else {
                        this.setSpeedInfo(var4);
                     }
                  } else {
                     this.AirInfo0x31(var4);
                  }
               } else {
                  this.WheelSpinKey0x22();
               }
            } else {
               this.WheelKeyInfo0x21();
            }
         } else {
            this.BodyDetailsInfo0x12(var4);
         }
      } else {
         this.CarInfo0x11();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      CanbusMsgSender.sendMsg(new byte[]{22, -116, 3, (byte)var1});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10, (byte)(var1 - 2000), (byte)var3, (byte)var4, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initCarMode();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      byte[] var26 = var21.getBytes(StandardCharsets.UTF_8);
      byte[] var25 = var22.getBytes(StandardCharsets.UTF_8);
      (new Thread(new MsgMgr$$ExternalSyntheticLambda0(DataHandleUtils.makeBytesFixedLength(var26, 32), DataHandleUtils.makeBytesFixedLength(var25, 32)))).start();
      var11 = var5 + var12 + var13;
      var2 = var11.length();

      for(var1 = 0; var1 < 12 - var2; ++var1) {
         var11 = var11 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(13, var11.getBytes());
   }

   public void onAccOn() {
      super.onAccOn();
      this.initCarMode();
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      this.initCarMode();
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var11;
      label43: {
         super.radioInfoChange(var1, var2, var3, var4, var5);
         boolean var8 = var2.equals("FM1");
         var4 = "AM";
         var11 = 1;
         if (!var8) {
            if (var2.equals("FM2")) {
               var11 = 2;
            } else if (var2.equals("FM3")) {
               var11 = 3;
            } else {
               if (var2.equals("AM1")) {
                  var11 = 4;
                  var2 = var4;
                  break label43;
               }

               if (var2.equals("AM2")) {
                  var11 = 5;
                  var2 = var4;
                  break label43;
               }
            }
         }

         var2 = "FM";
      }

      var4 = this.format3.format((long)var1);
      if (var2.equals("FM")) {
         var2 = this.format2.format(Double.parseDouble(var3));
      } else {
         var2 = this.format4.format((long)Integer.parseInt(var3));
      }

      char[] var10 = (var4 + " " + var2).toCharArray();
      byte[] var9 = new byte[12];
      byte var7 = 0;
      int var6 = 0;

      while(true) {
         var1 = var7;
         if (var6 >= 12) {
            while(var1 < var10.length) {
               var9[var1] = (byte)var10[var1];
               ++var1;
            }

            this.getUiMgr(this.mContext).sendMediaInfo0x91(var11, var9);
            return;
         }

         var9[var6] = 32;
         ++var6;
      }
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
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
         var8 = var5 + var12 + var13;
         var4 = var8.length();

         for(var3 = 0; var3 < 12 - var4; ++var3) {
            var8 = var8 + " ";
         }

         this.getUiMgr(this.mContext).sendMediaInfo0x91(13, var8.getBytes());
      }
   }
}
