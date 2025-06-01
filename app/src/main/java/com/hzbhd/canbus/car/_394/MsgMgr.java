package com.hzbhd.canbus.car._394;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static int volKnobValue;
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
   SystemButton systemButton;
   SystemButton systemDvrButton;
   private UiMgr uiMgr;

   private void AirInfo0x31(int[] var1) {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      var1[13] = 0;
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var1[3]) ^ true;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(var1[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[4]);
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

   private void BodyDetailsInfo0x12() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
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

      GeneralDoorData.isShowRev = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowAcc = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 10000, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void CarTypeInfo0x26() {
   }

   private void CentralControlInfo0x87() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_1"), NewUtil.getBoolBit7(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_185_setting_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_2"), NewUtil.getBoolBit4(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_0"), NewUtil.getBoolBit3(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_1"), NewUtil.getBoolBit2(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_0"), NewUtil.getBoolBit1(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_1"), NewUtil.getBoolBit0(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_4"), NewUtil.getBoolBit3(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_3"), NewUtil.getBoolBit2(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_8"), NewUtil.getBoolBit1(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_6"), NewUtil.getBoolBit0(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_7"), NewUtil.getBoolBit7(this.mCanBusInfoInt[7]))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_9"), NewUtil.getBoolBit4(this.mCanBusInfoInt[7])));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_A"), NewUtil.getBoolBit3(this.mCanBusInfoInt[7])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void OriginalCarStatusInformation0xE8() {
      ArrayList var3 = new ArrayList();
      boolean var2 = false;
      var3.add(new PanoramicBtnUpdateEntity(0, false));
      boolean var1;
      if (this.mCanBusInfoInt[7] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(1, var1));
      if (this.mCanBusInfoInt[8] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(2, var1));
      if (this.mCanBusInfoInt[6] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(3, var1));
      if (this.mCanBusInfoInt[4] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(4, var1));
      if (this.mCanBusInfoInt[5] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      Context var5;
      label41: {
         var3.add(new PanoramicBtnUpdateEntity(5, var1));
         GeneralParkData.dataList = var3;
         this.updateParkUi((Bundle)null, this.mContext);
         var5 = this.mContext;
         int[] var4 = this.mCanBusInfoInt;
         if (var4[4] != 1 && var4[5] != 1 && var4[6] != 1 && var4[7] != 1) {
            var1 = var2;
            if (var4[8] != 1) {
               break label41;
            }
         }

         var1 = true;
      }

      this.forceReverse(var5, var1);
   }

   private void RadarInfo0x41() {
      if (!this.isRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(3, var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
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
                        this.buttonKey1(39);
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

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
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

   private String resolveOutDoorTem() {
      return (float)this.mCanBusInfoInt[13] / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private void sendKnob_1(int var1, int var2) {
      this.realKeyClick3_1(this.mContext, var1, 0, var2);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 3});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = " AUX  MEDIA ".getBytes(StandardCharsets.UTF_8);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 12}, var1));
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      byte[] var9 = " BT  MEDIA  ".getBytes(StandardCharsets.UTF_8);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, -123}, var9));
      byte var5 = 0;
      var9 = new byte[0];

      byte[] var8;
      try {
         var8 = var1.getBytes("unicode");
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
         var8 = var9;
      }

      var9 = var8;
      int var4;
      if (var8.length < 32) {
         var9 = new byte[32 - var8.length];

         for(var4 = 0; var4 < 32 - var8.length; ++var4) {
            var9[var4] = 0;
         }

         var9 = DataHandleUtils.byteMerger(var8, var9);
      }

      var8 = new byte[32];

      for(var4 = 0; var4 < 32; ++var4) {
         var8[var4] = var9[var4];
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -110}, var8));
      var8 = new byte[0];

      label50: {
         try {
            var9 = var3.getBytes("unicode");
         } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            break label50;
         }

         var8 = var9;
      }

      var9 = var8;
      if (var8.length < 32) {
         var9 = new byte[32 - var8.length];

         for(var4 = 0; var4 < 32 - var8.length; ++var4) {
            var9[var4] = 0;
         }

         var9 = DataHandleUtils.byteMerger(var8, var9);
      }

      var8 = new byte[32];

      for(var4 = var5; var4 < 32; ++var4) {
         var8[var4] = var9[var4];
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -109}, var8));
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
                  if (var3 != 38) {
                     if (var3 != 49) {
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
                        this.AirInfo0x31(var4);
                     }
                  } else {
                     this.CarTypeInfo0x26();
                  }
               } else {
                  this.WheelSpinKey0x22();
               }
            } else {
               this.WheelKeyInfo0x21();
            }
         } else {
            this.BodyDetailsInfo0x12();
         }
      } else {
         this.CarInfo0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10, (byte)(var1 - 1999), (byte)var3, (byte)var4, 0});
   }

   public void hideDvrButton() {
      if (this.systemDvrButton == null) {
         this.systemDvrButton = new SystemButton(this.getActivity(), "DVR", new SystemButton.PanoramaListener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void clickEvent() {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 8});
               MsgMgr var1 = this.this$0;
               var1.realKeyClick4(var1.mContext, 141);
            }
         });
      }

      this.systemDvrButton.hide();
   }

   public void hideP360Button() {
      if (this.systemButton == null) {
         this.systemButton = new SystemButton(this.getActivity(), "360", new SystemButton.PanoramaListener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void clickEvent() {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 1});
            }
         });
      }

      this.systemButton.hide();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (this.getCurrentCanDifferentId() == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 23, 53});
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      byte[] var27 = " SUB  MEDIA ".getBytes(StandardCharsets.UTF_8);
      if (var1 == 9) {
         var1 = 25;
         var27 = " SD  MEDIA  ".getBytes(StandardCharsets.UTF_8);
      } else {
         var1 = 13;
      }

      var2 = 0;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, (byte)var1}, var27));
      var27 = new byte[0];

      byte[] var28;
      label72: {
         try {
            var28 = var21.getBytes("unicode");
         } catch (UnsupportedEncodingException var26) {
            var26.printStackTrace();
            break label72;
         }

         var27 = var28;
      }

      var28 = var27;
      if (var27.length < 32) {
         var28 = new byte[32 - var27.length];

         for(var1 = 0; var1 < 32 - var27.length; ++var1) {
            var28[var1] = 0;
         }

         var28 = DataHandleUtils.byteMerger(var27, var28);
      }

      var27 = new byte[32];

      for(var1 = 0; var1 < 32; ++var1) {
         var27[var1] = var28[var1];
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -110}, var27));
      var27 = new byte[0];

      label53: {
         try {
            var28 = var22.getBytes("unicode");
         } catch (UnsupportedEncodingException var25) {
            var25.printStackTrace();
            break label53;
         }

         var27 = var28;
      }

      var28 = var27;
      if (var27.length < 32) {
         var28 = new byte[32 - var27.length];

         for(var1 = 0; var1 < 32 - var27.length; ++var1) {
            var28[var1] = 0;
         }

         var28 = DataHandleUtils.byteMerger(var27, var28);
      }

      var27 = new byte[32];

      for(var1 = var2; var1 < 32; ++var1) {
         var27[var1] = var28[var1];
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -109}, var27));
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
      var1 = 0;

      while(true) {
         int var6 = var7;
         if (var1 >= 12) {
            while(var6 < var10.length) {
               var9[var6] = (byte)var10[var6];
               ++var6;
            }

            this.sendMediaInfo0x91(var11, var9);
            return;
         }

         var9[var1] = 32;
         ++var1;
      }
   }

   public void sendMediaInfo0x91(int var1, byte[] var2) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -111, (byte)var1}, var2));
   }

   public void showDvrButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemDvrButton == null) {
               this.this$0.systemDvrButton = new SystemButton(this.this$0.getActivity(), "DVR", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 8});
                     this.this$1.this$0.realKeyClick4(this.this$1.this$0.mContext, 141);
                  }
               });
            }

            this.this$0.systemDvrButton.show();
         }
      });
   }

   public void showP360Button() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "360", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 1});
                  }
               });
            }

            this.this$0.systemButton.show();
         }
      });
   }

   public void toast(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$centent;

         {
            this.this$0 = var1;
            this.val$centent = var2;
         }

         public void callback() {
            Toast.makeText(this.this$0.getActivity(), this.val$centent, 0).show();
         }
      });
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var19 = " SUB  MEDIA ".getBytes(StandardCharsets.UTF_8);
      byte var18;
      if (var1 == 9) {
         var18 = 25;
         var19 = " SD  MEDIA  ".getBytes(StandardCharsets.UTF_8);
      } else {
         var18 = 13;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, (byte)var18}, var19));
      if (var1 != 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
      }
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      var1.hashCode();
      if (!var1.equals("skylight.open")) {
         if (var1.equals("skylight.close")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 25, 0});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -116, 25, 1});
      }

   }
}
