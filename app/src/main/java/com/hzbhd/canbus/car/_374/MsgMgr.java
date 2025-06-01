package com.hzbhd.canbus.car._374;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
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

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void adjustBrightness() {
      int var1 = FutureUtil.instance.getBrightness();
      if (var1 == 5) {
         FutureUtil.instance.setBrightness(0);
      } else {
         FutureUtil.instance.setBrightness(var1 + 1);
      }

   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private Object getAuxSourceState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131764553) : this.mContext.getString(2131764554);
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

   private int getRadarData(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? 0 : 1;
            } else {
               return 3;
            }
         } else {
            return 7;
         }
      } else {
         return 10;
      }
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

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
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

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
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
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
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

   private void set0X22RearRadarINfo() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x10TimeInfo() {
      ArrayList var2 = new ArrayList();
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2] * 256 + var3[3];
      if (var1 == 4095) {
         var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_374_car_time"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_374_car_time", "_374_car_time"), "invalid")).setValueStr(true));
      } else {
         var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_374_car_time"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_374_car_time", "_374_car_time"), var1 + "/" + this.mCanBusInfoInt[4] + "/" + this.mCanBusInfoInt[5] + "  " + this.mCanBusInfoInt[6] + ":" + this.mCanBusInfoInt[7])).setValueStr(true));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x20WheelKeyInfo() {
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
            this.buttonKey(20);
            break;
         case 4:
            this.buttonKey(21);
            break;
         case 5:
            this.buttonKey(14);
            break;
         case 6:
            this.buttonKey(3);
            break;
         case 7:
            this.buttonKey(2);
            break;
         case 8:
            this.buttonKey(187);
            break;
         case 9:
            this.buttonKey(14);
            break;
         case 10:
            this.buttonKey(15);
            break;
         case 11:
            this.buttonKey(49);
            break;
         case 12:
            this.buttonKey(50);
            break;
         case 13:
            this.knobKey(7);
            break;
         case 14:
            this.knobKey(8);
      }

   }

   private void set0x21WhellKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 128) {
         switch (var1) {
            case 0:
               this.buttonKey(0);
               break;
            case 1:
               this.buttonKey(33);
               break;
            case 2:
               this.buttonKey(34);
               break;
            case 3:
               this.buttonKey(35);
               break;
            case 4:
               this.buttonKey(36);
               break;
            case 5:
               this.buttonKey(37);
               break;
            case 6:
               this.buttonKey(38);
               break;
            case 7:
               this.buttonKey(39);
               break;
            case 8:
               this.buttonKey(40);
               break;
            case 9:
               this.buttonKey(41);
               break;
            case 10:
               this.buttonKey(42);
               break;
            case 11:
               this.buttonKey(12);
               break;
            case 12:
               this.buttonKey(67);
               break;
            default:
               switch (var1) {
                  case 16:
                     this.buttonKey(4112);
                     break;
                  case 17:
                     this.buttonKey(2);
                     break;
                  case 18:
                     this.buttonKey(14);
                     break;
                  case 19:
                     this.buttonKey(94);
                     break;
                  case 20:
                     this.buttonKey(47);
                     break;
                  case 21:
                     this.buttonKey(48);
                     break;
                  case 22:
                     this.buttonKey(151);
                     break;
                  case 23:
                     this.buttonKey(50);
                     break;
                  case 24:
                     this.buttonKey(1);
                     break;
                  case 25:
                     this.buttonKey(30);
                     break;
                  case 26:
                     this.buttonKey(128);
                     break;
                  case 27:
                     this.buttonKey(6);
                     break;
                  case 28:
                     this.knobKey(8);
                     break;
                  case 29:
                     this.knobKey(7);
                     break;
                  case 30:
                     this.knobKey(46);
                     break;
                  case 31:
                     this.knobKey(45);
                     break;
                  case 32:
                     this.forceReverse(this.mContext, false);
                     break;
                  case 33:
                     this.buttonKey(45);
                     break;
                  case 34:
                     this.buttonKey(46);
                     break;
                  case 35:
                     this.buttonKey(49);
                     break;
                  case 36:
                     this.buttonKey(151);
               }
         }
      } else {
         this.buttonKey(141);
      }

   }

   private void set0x23FromtRadarInfo() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x25AirInfo() {
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         if (!GeneralAirData.front_left_blow_window && !GeneralAirData.front_left_blow_head && !GeneralAirData.front_left_blow_foot) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
         } else {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
         }

         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 5);
         if (var1 > 18) {
            GeneralAirData.front_auto_wind_speed = true;
         } else {
            GeneralAirData.front_auto_wind_speed = false;
            GeneralAirData.front_wind_level = var1;
         }

         var1 = this.mCanBusInfoInt[4];
         if (var1 == 254) {
            GeneralAirData.front_left_temperature = "HI";
         } else if (var1 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var1 > 28) {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[4] + this.getTempUnitF(this.mContext);
         } else {
            GeneralAirData.front_left_temperature = (double)(this.mCanBusInfoInt[4] - 1) * 0.5 + 16.0 + this.getTempUnitC(this.mContext);
         }

         var1 = this.mCanBusInfoInt[5];
         if (var1 == 254) {
            GeneralAirData.front_right_temperature = "HI";
         } else if (var1 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var1 > 28) {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[5] + this.getTempUnitF(this.mContext);
         } else {
            GeneralAirData.front_right_temperature = (double)(this.mCanBusInfoInt[5] - 1) * 0.5 + 16.0 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x27AuxInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_374_car_aux"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_374_car_aux", "_374_car_aux"), this.getAuxSourceState(this.mCanBusInfoInt[2]))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x30VersionINfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
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
      if (var3 != 16) {
         if (var3 != 37) {
            if (var3 != 39) {
               if (var3 != 48) {
                  switch (var3) {
                     case 32:
                        this.set0x20WheelKeyInfo();
                        break;
                     case 33:
                        this.set0x21WhellKeyInfo();
                        break;
                     case 34:
                        this.set0X22RearRadarINfo();
                        break;
                     case 35:
                        this.set0x23FromtRadarInfo();
                  }
               } else {
                  this.set0x30VersionINfo();
               }
            } else {
               this.set0x27AuxInfo();
            }
         } else {
            this.set0x25AirInfo();
         }
      } else {
         this.set0x10TimeInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick3(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
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
}
