package com.hzbhd.canbus.car._336;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int EspInfoData6;
   int EspInfoData7;
   TextView content;
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   AlertDialog dialog;
   int differentId;
   int driveInfoData0;
   int eachId;
   Button i_know;
   boolean isFrontCAmera = false;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   int nowData6Bit4567 = 0;
   View view;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      int var4 = var1.length + var2.length;
      byte[] var6 = new byte[var4];
      int var5 = var1.length;
      int var3 = 0;
      System.arraycopy(var1, 0, var6, 0, var5);
      System.arraycopy(var2, 0, var6, var1.length, var2.length);
      MyLog.e("长度", var4 + "");

      while(var3 < var4) {
         MyLog.e("内容" + var3, var6[var3] + "");
         ++var3;
      }

      return var6;
   }

   private void adjustBrightness() {
      int var1 = MediaShareData.Screen.INSTANCE.getScreenBacklight();
      if (var1 == 5) {
         this.setBacklightLevel(1);
      } else {
         this.setBacklightLevel(var1 + 1);
      }

   }

   private int assignRadarProgress(int var1) {
      if (var1 == 1) {
         return 1;
      } else if (var1 == 2) {
         return 5;
      } else {
         return var1 == 3 ? 10 : 0;
      }
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

   private boolean driveInfoChange() {
      int var2 = this.driveInfoData0;
      int var1 = this.mCanBusInfoInt[2];
      if (var2 == var1) {
         return false;
      } else {
         this.driveInfoData0 = var1;
         return true;
      }
   }

   private void frontCameraFunction() {
      if (this.mCanBusInfoInt[5] != 0) {
         if (!this.isFrontCAmera) {
            this.switchFCamera(this.mContext, true);
            this.isFrontCAmera = true;
         } else {
            this.switchFCamera(this.mContext, false);
            this.isFrontCAmera = false;
         }

      }
   }

   private String getAccState(boolean var1) {
      return var1 ? this.mContext.getString(2131763683) : this.mContext.getString(2131763684);
   }

   private String getAir1State(boolean var1, int var2) {
      String var4;
      if (var1) {
         var4 = this.mContext.getString(2131763710);
      } else {
         var4 = this.mContext.getString(2131763721);
      }

      String var3 = var4;
      if (var2 == 0) {
         var3 = var4 + "/" + this.mContext.getString(2131763711);
      }

      if (var2 == 1) {
         var4 = var3 + "/" + this.mContext.getString(2131763712);
      } else {
         var4 = var3;
         if (var2 == 2) {
            var4 = var3 + "/" + this.mContext.getString(2131763713);
         }
      }

      return var4;
   }

   private String getAir2State(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 0) {
         var3 = var3 + "/" + this.mContext.getString(2131763714);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763715);
      }

      return var3;
   }

   private String getAir3State(boolean var1, int var2) {
      String var4;
      if (var1) {
         var4 = this.mContext.getString(2131763710);
      } else {
         var4 = this.mContext.getString(2131763721);
      }

      String var3;
      if (var2 == 0) {
         var3 = var4 + "/" + this.mContext.getString(2131763723);
      } else if (var2 == 1) {
         var3 = var4 + "/" + this.mContext.getString(2131763727);
      } else if (var2 == 2) {
         var3 = var4 + "/" + this.mContext.getString(2131763728);
      } else {
         var3 = var4;
         if (var2 == 3) {
            var3 = var4 + "/" + this.mContext.getString(2131763729);
         }
      }

      return var3;
   }

   private String getAir4State(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 0) {
         var3 = var3 + "/" + this.mContext.getString(2131763723);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763724);
      }

      return var3;
   }

   private String getAir5State(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 0) {
         var3 = var3 + "/" + this.mContext.getString(2131763723);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763724);
      }

      return var3;
   }

   private String getAlertContent() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      String var2 = "";
      if (var1) {
         var2 = "" + this.mContext.getString(2131763700);
      }

      String var3 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
         var3 = var2 + this.mContext.getString(2131763701);
      }

      var2 = var3;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
         var2 = var3 + this.mContext.getString(2131763702);
      }

      var3 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
         var3 = var2 + this.mContext.getString(2131763703);
      }

      return var3;
   }

   private String getData5Bit1State() {
      return DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]) ? this.mContext.getString(2131763716) : this.mContext.getString(2131763717);
   }

   private String getILLState(boolean var1) {
      return var1 ? this.mContext.getString(2131763681) : this.mContext.getString(2131763682);
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

   private String getParkState(boolean var1) {
      return var1 ? this.mContext.getString(2131763695) : this.mContext.getString(2131763696);
   }

   private String getRadarDisplayModel(int var1) {
      return var1 == 1 ? this.mContext.getString(2131763690) : this.mContext.getString(2131763689);
   }

   private String getRadarDisplayState(int var1) {
      if (var1 == 1) {
         this.panoramicSwitch(true);
         return this.mContext.getString(2131763686);
      } else {
         this.panoramicSwitch(false);
         return this.mContext.getString(2131763687);
      }
   }

   private String getRemoteControlState(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 0) {
         var3 = var3 + "/" + this.mContext.getString(2131763723);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763724);
      }

      return var3;
   }

   private String getRevState(boolean var1) {
      return var1 ? this.mContext.getString(2131763697) : this.mContext.getString(2131763698);
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private String getTravel1State(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 1) {
         var3 = var3 + "/" + this.mContext.getString(2131763716);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763717);
      }

      return var3;
   }

   private String getTravel2State(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 1) {
         var3 = var3 + "/" + this.mContext.getString(2131763718);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763719);
      }

      return var3;
   }

   private String getTravel3State(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 1) {
         var3 = var3 + "/" + this.mContext.getString(2131763720);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763722);
      }

      return var3;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getVoiceState(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = this.mContext.getString(2131763710);
      } else {
         var3 = this.mContext.getString(2131763721);
      }

      if (var2 == 0) {
         var3 = var3 + "/" + this.mContext.getString(2131763725);
      } else {
         var3 = var3 + "/" + this.mContext.getString(2131763726);
      }

      return var3;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isEspInfoChange() {
      int var2 = this.EspInfoData6;
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[8];
      if (var2 == var1 && this.EspInfoData7 == var3[9]) {
         return false;
      } else {
         this.EspInfoData6 = var1;
         this.EspInfoData7 = var3[9];
         return true;
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
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x11CarBasicInfo() {
      if (this.driveInfoChange()) {
         this.set0x11DriveInfo();
      }

      this.set0x11WhellKeyInfo();
      if (this.isEspInfoChange()) {
         this.set0x11EspInfo();
      }

   }

   private void set0x11DriveInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_2"), this.getParkState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_3"), this.getRevState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_4"), this.getILLState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_5"), this.getAccState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x11EspInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x11WhellKeyInfo() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 57) {
                           switch (var1) {
                              case 8:
                                 this.buttonKey(45);
                                 break;
                              case 9:
                                 this.buttonKey(46);
                                 break;
                              case 10:
                                 this.buttonKey(2);
                           }
                        } else {
                           this.frontCameraFunction();
                        }
                     } else {
                        this.buttonKey(15);
                     }
                  } else {
                     this.buttonKey(14);
                  }
               } else {
                  this.buttonKey(187);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void set0x12CarDetailInfo() {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x26CarModelInfo() {
   }

   private void set0x31AirInfo() {
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var1 = this.mCanBusInfoInt[6];
         if (var1 == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
         } else if (var1 == 3) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
         } else if (var1 == 5) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
         } else if (var1 == 6) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
         } else if (var1 == 11) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
         } else if (var1 == 12) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
         } else if (var1 == 13) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
         } else if (var1 == 14) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
         } else {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
         }

         if (this.mCanBusInfoInt[7] == 19) {
            GeneralAirData.front_auto_wind_speed = true;
         } else {
            GeneralAirData.front_auto_wind_speed = false;
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         }

         var1 = this.mCanBusInfoInt[8];
         if (var1 == 254) {
            GeneralAirData.front_left_temperature = "LOW";
         } else if (var1 == 255) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = this.df_2Decimal.format((double)this.mCanBusInfoInt[8] * 0.5) + this.getTempUnitC(this.mContext);
         }

         var1 = this.mCanBusInfoInt[9];
         if (var1 == 254) {
            GeneralAirData.front_right_temperature = "LOW";
         } else if (var1 == 255) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = this.df_2Decimal.format((double)this.mCanBusInfoInt[9] * 0.5) + this.getTempUnitC(this.mContext);
         }

         this.updateAirActivity(this.mContext, 1002);
      }
   }

   private void set0x41RadarInfo() {
      if (this.isTrackInfoChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.assignRadarProgress(this.mCanBusInfoInt[2]), this.assignRadarProgress(this.mCanBusInfoInt[3]), this.assignRadarProgress(this.mCanBusInfoInt[4]), this.assignRadarProgress(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_14"), this.getRadarDisplayState(this.mCanBusInfoInt[12])));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_17"), this.getRadarDisplayModel(this.mCanBusInfoInt[13])));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0xF0VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setAlert() {
      if (this.view == null) {
         this.view = LayoutInflater.from(this.mContext).inflate(2131558513, (ViewGroup)null, true);
      }

      if (this.dialog == null) {
         this.dialog = (new AlertDialog.Builder(this.mContext)).setView(this.view).create();
      }

      if (this.content == null) {
         this.content = (TextView)this.view.findViewById(2131361915);
      }

      this.content.setText(this.getAlertContent());
      if (this.i_know == null) {
         this.i_know = (Button)this.view.findViewById(2131362380);
      }

      this.i_know.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.dialog.dismiss();
         }
      });
      this.dialog.setCancelable(false);
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.getWindow().setType(2003);
      this.dialog.show();
   }

   private void setDriveInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air1"), this.getAir1State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air2"), this.getAir2State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air3"), this.getAir3State(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air4"), this.getAir4State(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air5"), this.getAir5State(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice1"), this.getVoiceState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice2"), this.getVoiceState(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice3"), this.getVoiceState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice4"), this.getVoiceState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control1"), this.getRemoteControlState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control2"), this.getRemoteControlState(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control3"), this.getRemoteControlState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control4"), this.getRemoteControlState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_travel"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_travel1"), this.getTravel1State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_travel"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_travel2"), this.getTravel2State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_travel"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_travel3"), this.getTravel3State(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOx61CarSettingsInfo() {
      this.setDriveInfo();
      this.setSettingInfo();
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4) != 0) {
         if (this.nowData6Bit4567 != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4)) {
            this.nowData6Bit4567 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
            this.setAlert();
         }
      } else {
         this.nowData6Bit4567 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
         AlertDialog var1 = this.dialog;
         if (var1 != null) {
            var1.dismiss();
         }
      }

   }

   private void setSettingInfo() {
      ArrayList var2 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_336_car_settings");
      var2.add((new SettingUpdateEntity(var1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(var1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(var1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(var1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(var1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])));
      var2.add((new SettingUpdateEntity(var1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])));
      var2.add((new SettingUpdateEntity(var1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])));
      var2.add((new SettingUpdateEntity(var1, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])));
      var2.add((new SettingUpdateEntity(var1, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])));
      var2.add((new SettingUpdateEntity(var1, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])));
      var2.add((new SettingUpdateEntity(var1, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])));
      var2.add((new SettingUpdateEntity(var1, 11, this.getData5Bit1State())).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 38) {
               if (var3 != 49) {
                  if (var3 != 65) {
                     if (var3 != 97) {
                        if (var3 == 240) {
                           this.set0xF0VersionInfo();
                        }
                     } else {
                        this.setOx61CarSettingsInfo();
                     }
                  } else {
                     this.set0x41RadarInfo();
                  }
               } else {
                  this.set0x31AirInfo();
               }
            } else {
               this.set0x26CarModelInfo();
            }
         } else {
            this.set0x12CarDetailInfo();
         }
      } else {
         this.set0x11CarBasicInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void panoramicSwitch(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
