package com.hzbhd.canbus.car._435;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mDoorData;
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

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
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
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataNoChange() {
      if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mDoorData = this.mCanBusInfoInt;
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

   private void setAirInfo() {
      int[] var2 = this.mCanBusInfoInt;
      var2[3] = this.blockBit(var2[3], 4);
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         int var1 = this.mCanBusInfoInt[4];
         if (var1 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var1 == 31) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (double)this.mCanBusInfoInt[4] * 0.5 + 17.5 + this.getTempUnitC(this.mContext);
         }

         var1 = this.mCanBusInfoInt[5];
         if (var1 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var1 == 31) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = (double)this.mCanBusInfoInt[5] * 0.5 + 17.5 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setDoorInfo() {
      if (this.mCanBusInfoInt[2] == 1) {
         if (this.isDoorDataNoChange()) {
            return;
         }

         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setEscInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setForntRadarInfo() {
      if (this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setPanelKey() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[3];
      if (var1 != 2) {
         int var2 = var3[2];
         if (var2 != 13) {
            if (var2 != 14) {
               switch (var2) {
                  case 0:
                     this.realKeyLongClick1(this.mContext, 0, var1);
                     break;
                  case 1:
                     this.realKeyLongClick1(this.mContext, 134, var1);
                     break;
                  case 2:
                     this.realKeyLongClick1(this.mContext, 2, var1);
                     break;
                  case 3:
                     this.realKeyLongClick1(this.mContext, 50, var1);
                     break;
                  case 4:
                     this.realKeyLongClick1(this.mContext, 52, var1);
                     break;
                  case 5:
                     this.realKeyLongClick1(this.mContext, 62, var1);
                     break;
                  case 6:
                     this.realKeyLongClick1(this.mContext, 21, var1);
                     break;
                  case 7:
                     this.realKeyLongClick1(this.mContext, 20, var1);
                     break;
                  case 8:
                     this.realKeyLongClick1(this.mContext, 49, var1);
                     break;
                  case 9:
                     this.realKeyLongClick1(this.mContext, 3, var1);
                     break;
                  case 10:
                     this.realKeyLongClick1(this.mContext, 128, var1);
                     break;
                  case 11:
                     this.realKeyLongClick1(this.mContext, 58, var1);
                     break;
                  default:
                     switch (var2) {
                        case 16:
                           this.realKeyLongClick1(this.mContext, 7, var1);
                           break;
                        case 17:
                           this.realKeyLongClick1(this.mContext, 8, var1);
                           break;
                        case 18:
                           this.realKeyLongClick1(this.mContext, 48, var1);
                           break;
                        case 19:
                           this.realKeyLongClick1(this.mContext, 47, var1);
                           break;
                        default:
                           switch (var2) {
                              case 33:
                                 this.realKeyLongClick1(this.mContext, 33, var1);
                                 break;
                              case 34:
                                 this.realKeyLongClick1(this.mContext, 34, var1);
                                 break;
                              case 35:
                                 this.realKeyLongClick1(this.mContext, 35, var1);
                                 break;
                              case 36:
                                 this.realKeyLongClick1(this.mContext, 36, var1);
                                 break;
                              case 37:
                                 this.realKeyLongClick1(this.mContext, 37, var1);
                                 break;
                              case 38:
                                 this.realKeyLongClick1(this.mContext, 38, var1);
                                 break;
                              case 39:
                                 this.realKeyLongClick1(this.mContext, 4, var1);
                                 break;
                              case 40:
                                 this.realKeyLongClick1(this.mContext, 30, var1);
                                 break;
                              case 41:
                                 this.realKeyLongClick1(this.mContext, 68, var1);
                                 break;
                              case 42:
                                 this.realKeyLongClick1(this.mContext, 187, var1);
                                 break;
                              case 43:
                                 this.realKeyLongClick1(this.mContext, 20, var1);
                                 break;
                              case 44:
                                 this.realKeyLongClick1(this.mContext, 21, var1);
                           }
                     }
               }
            } else {
               this.realKeyLongClick1(this.mContext, 15, var1);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 188, var1);
         }

      }
   }

   private void setRearRadarInfo() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSwcInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[3];
      if (var1 != 2) {
         switch (var2[2]) {
            case 0:
               this.realKeyLongClick1(this.mContext, 0, var1);
               break;
            case 1:
               this.realKeyLongClick1(this.mContext, 7, var1);
               break;
            case 2:
               this.realKeyLongClick1(this.mContext, 8, var1);
               break;
            case 3:
               this.realKeyLongClick1(this.mContext, 46, var1);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 45, var1);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 188, var1);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 3, var1);
               break;
            case 7:
               this.realKeyLongClick1(this.mContext, 2, var1);
               break;
            case 8:
               this.realKeyLongClick1(this.mContext, 14, var1);
         }

      }
   }

   private void setVersionInfo() {
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

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 38) {
         if (var3 != 48) {
            if (var3 != 65) {
               if (var3 != 80) {
                  switch (var3) {
                     case 32:
                        this.setSwcInfo();
                        break;
                     case 33:
                        this.setAirInfo();
                        break;
                     case 34:
                        this.setRearRadarInfo();
                        break;
                     case 35:
                        this.setForntRadarInfo();
                  }
               } else {
                  this.setPanelKey();
               }
            } else {
               this.setDoorInfo();
            }
         } else {
            this.setVersionInfo();
         }
      } else {
         this.setEscInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
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
