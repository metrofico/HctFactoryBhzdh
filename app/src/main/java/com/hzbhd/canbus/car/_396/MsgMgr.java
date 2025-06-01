package com.hzbhd.canbus.car._396;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
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
   private int nowVol = -1;
   SystemButton systemButton;
   private List tyreInfoList = new ArrayList();

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
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private Object getEnergyState(int var1) {
      if (var1 == 0) {
         return "LOW";
      } else if (var1 == 1) {
         return "MID";
      } else {
         return var1 == 2 ? "HI" : "NO STATE";
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private String getModel(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131765346);
         case 2:
            return this.mContext.getString(2131765347);
         case 3:
            return this.mContext.getString(2131765348);
         case 4:
            return this.mContext.getString(2131765349);
         case 5:
            return this.mContext.getString(2131765350);
         case 6:
            return this.mContext.getString(2131765351);
         case 7:
            return this.mContext.getString(2131765352);
         default:
            return this.mContext.getString(2131765345);
      }
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getPmState(int var1) {
      if (var1 <= 37) {
         return "☺ ■□□□□ ☹";
      } else if (var1 > 37 && var1 <= 75) {
         return "☺ ■■□□□ ☹";
      } else if (var1 > 75 && var1 <= 125) {
         return "☺ ■■■□□ ☹";
      } else if (var1 > 125 && var1 <= 250) {
         return "☺ ■■■■□ ☹";
      } else {
         return var1 > 250 && var1 <= 255 ? "☺ ■■■■■ ☹" : "NO STATE";
      }
   }

   private String getProgress(int var1) {
      byte var4 = 0;
      String var6 = " ";
      int var3 = 0;

      while(true) {
         int var5 = var1 / 10;
         int var2 = var4;
         String var7 = var6;
         if (var3 >= var5) {
            while(var2 < 25 - var5) {
               var7 = var7 + "□";
               ++var2;
            }

            return var7 + "【" + this.df_1Decimal.format((double)var1 * 0.1) + "l/100km】";
         }

         var6 = var6 + "■";
         ++var3;
      }
   }

   private String getProgressOil(int var1) {
      byte var4 = 0;
      String var6 = " ";
      int var2 = 0;

      while(true) {
         int var5 = var1 / 10;
         int var3 = var4;
         String var7 = var6;
         if (var2 >= var5) {
            while(var3 < 12 - var5) {
               var7 = var7 + "□";
               ++var3;
            }

            return var7 + "【" + (var1 - 25) + "kwh/km】";
         }

         var6 = var6 + "■";
         ++var2;
      }
   }

   private String getSettingState(boolean var1) {
      return var1 ? "ON" : "OFF";
   }

   private int getSwitch(boolean var1) {
      return var1 ? 1 : 0;
   }

   private String getTemperature(int var1) {
      if (var1 == 254) {
         return "LO";
      } else {
         return var1 == 255 ? "HI" : this.df_1Decimal.format((double)var1 * 0.5) + this.getTempUnitC(this.mContext);
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

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isI5() {
      boolean var1;
      if (this.getCurrentEachCanId() == 16) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isNotDoorInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
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

   private boolean isRX3() {
      boolean var1;
      if (this.getCurrentEachCanId() == 20) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
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

   private void set0x04PanoraKnoy() {
      int var1 = this.nowVol;
      if (var1 == -1) {
         this.nowVol = this.mCanBusInfoInt[3];
      } else {
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var3[2];
         if (var2 == 1) {
            if (var1 < var3[3]) {
               this.realKeyClick4(this.mContext, 7);
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         } else if (var2 == 2) {
            if (var1 < var3[3]) {
               this.realKeyClick4(this.mContext, 48);
            } else {
               this.realKeyClick4(this.mContext, 47);
            }
         }

      }
   }

   private void set0x11SWC() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      if (var1 != 24) {
         if (var1 != 64) {
            switch (var1) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var2[5]);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 7, var2[5]);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 8, var2[5]);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 3, var2[5]);
                  break;
               case 4:
                  this.realKeyLongClick1(this.mContext, 187, var2[5]);
                  break;
               case 5:
                  this.realKeyLongClick1(this.mContext, 14, var2[5]);
                  break;
               case 6:
                  this.realKeyLongClick1(this.mContext, 15, var2[5]);
                  break;
               default:
                  switch (var1) {
                     case 8:
                        this.realKeyLongClick1(this.mContext, 45, var2[5]);
                        break;
                     case 9:
                        this.realKeyLongClick1(this.mContext, 46, var2[5]);
                        break;
                     case 10:
                        this.realKeyLongClick1(this.mContext, 58, var2[5]);
                        break;
                     case 11:
                        this.realKeyLongClick1(this.mContext, 2, var2[5]);
                  }
            }
         } else {
            this.realKeyLongClick1(this.mContext, 39, var2[5]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 188, var2[5]);
      }

   }

   private void set0x11Track() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x12Door() {
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void set0x14Oli() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy0"), this.getProgress(this.mCanBusInfoInt[2])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy1"), this.getProgress(this.mCanBusInfoInt[3])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy2"), this.getProgress(this.mCanBusInfoInt[4])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy3"), this.getProgress(this.mCanBusInfoInt[5])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy4"), this.getProgress(this.mCanBusInfoInt[6])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy5"), this.getProgress(this.mCanBusInfoInt[7])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy6"), this.getProgress(this.mCanBusInfoInt[8])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy7"), this.getProgress(this.mCanBusInfoInt[9])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy8"), this.getProgress(this.mCanBusInfoInt[10])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy9"), this.getProgress(this.mCanBusInfoInt[11])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy10"), this.getProgress(this.mCanBusInfoInt[12])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy11"), this.getProgress(this.mCanBusInfoInt[13])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy12"), this.getProgress(this.mCanBusInfoInt[14])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy13"), this.getProgress(this.mCanBusInfoInt[15])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy14"), this.getProgress(this.mCanBusInfoInt[16])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy15"), this.getProgress(this.mCanBusInfoInt[17])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy16"), this.getProgress(this.mCanBusInfoInt[18])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy17"), this.getProgress(this.mCanBusInfoInt[19])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy18"), this.getProgress(this.mCanBusInfoInt[20])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy19"), this.getProgress(this.mCanBusInfoInt[21])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy20"), this.getProgress(this.mCanBusInfoInt[22])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy21"), this.getProgress(this.mCanBusInfoInt[23])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy22"), this.getProgress(this.mCanBusInfoInt[24])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy23"), this.getProgress(this.mCanBusInfoInt[25])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy24"), this.getProgress(this.mCanBusInfoInt[26])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy25"), this.getProgress(this.mCanBusInfoInt[27])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy26"), this.getProgress(this.mCanBusInfoInt[28])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy27"), this.getProgress(this.mCanBusInfoInt[29])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy28"), this.getProgress(this.mCanBusInfoInt[30])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy29"), this.getProgress(this.mCanBusInfoInt[31])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy30"), this.getProgress(this.mCanBusInfoInt[32])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy31"), this.getProgress(this.mCanBusInfoInt[33])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy32"), this.getProgress(this.mCanBusInfoInt[34])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy33"), this.getProgress(this.mCanBusInfoInt[35])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy34"), this.getProgress(this.mCanBusInfoInt[36])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy35"), this.getProgress(this.mCanBusInfoInt[37])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy36"), this.getProgress(this.mCanBusInfoInt[38])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy37"), this.getProgress(this.mCanBusInfoInt[39])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy38"), this.getProgress(this.mCanBusInfoInt[40])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy39"), this.getProgress(this.mCanBusInfoInt[41])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy40"), this.getProgress(this.mCanBusInfoInt[42])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy41"), this.getProgress(this.mCanBusInfoInt[43])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy42"), this.getProgress(this.mCanBusInfoInt[44])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy43"), this.getProgress(this.mCanBusInfoInt[45])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy44"), this.getProgress(this.mCanBusInfoInt[46])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy45"), this.getProgress(this.mCanBusInfoInt[47])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy46"), this.getProgress(this.mCanBusInfoInt[48])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy47"), this.getProgress(this.mCanBusInfoInt[49])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy48"), this.getProgress(this.mCanBusInfoInt[50])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy49"), this.getProgress(this.mCanBusInfoInt[51])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy50"), this.getProgress(this.mCanBusInfoInt[52])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x15Power() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d0"), this.getProgressOil(this.mCanBusInfoInt[2])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d1"), this.getProgressOil(this.mCanBusInfoInt[3])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d2"), this.getProgressOil(this.mCanBusInfoInt[4])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d3"), this.getProgressOil(this.mCanBusInfoInt[5])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d4"), this.getProgressOil(this.mCanBusInfoInt[6])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d5"), this.getProgressOil(this.mCanBusInfoInt[7])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d6"), this.getProgressOil(this.mCanBusInfoInt[8])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d7"), this.getProgressOil(this.mCanBusInfoInt[9])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d8"), this.getProgressOil(this.mCanBusInfoInt[10])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d9"), this.getProgressOil(this.mCanBusInfoInt[11])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d10"), this.getProgressOil(this.mCanBusInfoInt[12])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d11"), this.getProgressOil(this.mCanBusInfoInt[13])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d12"), this.getProgressOil(this.mCanBusInfoInt[14])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d13"), this.getProgressOil(this.mCanBusInfoInt[15])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d14"), this.getProgressOil(this.mCanBusInfoInt[16])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d15"), this.getProgressOil(this.mCanBusInfoInt[17])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d16"), this.getProgressOil(this.mCanBusInfoInt[18])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d17"), this.getProgressOil(this.mCanBusInfoInt[19])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d18"), this.getProgressOil(this.mCanBusInfoInt[20])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d19"), this.getProgressOil(this.mCanBusInfoInt[21])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d20"), this.getProgressOil(this.mCanBusInfoInt[22])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d21"), this.getProgressOil(this.mCanBusInfoInt[23])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d22"), this.getProgressOil(this.mCanBusInfoInt[24])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d23"), this.getProgressOil(this.mCanBusInfoInt[25])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d24"), this.getProgressOil(this.mCanBusInfoInt[26])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d25"), this.getProgressOil(this.mCanBusInfoInt[27])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d26"), this.getProgressOil(this.mCanBusInfoInt[28])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d27"), this.getProgressOil(this.mCanBusInfoInt[29])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d28"), this.getProgressOil(this.mCanBusInfoInt[30])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d29"), this.getProgressOil(this.mCanBusInfoInt[31])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d30"), this.getProgressOil(this.mCanBusInfoInt[32])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d31"), this.getProgressOil(this.mCanBusInfoInt[33])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d32"), this.getProgressOil(this.mCanBusInfoInt[34])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d33"), this.getProgressOil(this.mCanBusInfoInt[35])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d34"), this.getProgressOil(this.mCanBusInfoInt[36])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d35"), this.getProgressOil(this.mCanBusInfoInt[37])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d36"), this.getProgressOil(this.mCanBusInfoInt[38])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d37"), this.getProgressOil(this.mCanBusInfoInt[39])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d38"), this.getProgressOil(this.mCanBusInfoInt[40])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d39"), this.getProgressOil(this.mCanBusInfoInt[41])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d40"), this.getProgressOil(this.mCanBusInfoInt[42])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d41"), this.getProgressOil(this.mCanBusInfoInt[43])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d42"), this.getProgressOil(this.mCanBusInfoInt[44])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d43"), this.getProgressOil(this.mCanBusInfoInt[45])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d44"), this.getProgressOil(this.mCanBusInfoInt[46])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d45"), this.getProgressOil(this.mCanBusInfoInt[47])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d46"), this.getProgressOil(this.mCanBusInfoInt[48])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d47"), this.getProgressOil(this.mCanBusInfoInt[49])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d48"), this.getProgressOil(this.mCanBusInfoInt[50])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d49"), this.getProgressOil(this.mCanBusInfoInt[51])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d50"), this.getProgressOil(this.mCanBusInfoInt[52])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x1ADoor() {
      if (!this.isNotDoorInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x1APanoro() {
      Context var3 = this.mContext;
      int var1 = this.mCanBusInfoInt[10];
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      this.forceReverse(var3, var2);
   }

   private void set0x1ASpeed() {
      ArrayList var5 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive_speed");
      StringBuilder var6 = new StringBuilder();
      DecimalFormat var4 = this.df_2Decimal;
      int[] var3 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var6.append(var4.format((double)this.getMsbLsbResult(var3[5], var3[6]) * 0.1)).append("km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x1ATrack() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x21PanoraKey() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 6) {
                     if (var1 != 9) {
                        if (var1 != 32) {
                           if (var1 != 47) {
                              if (var1 != 55) {
                                 if (var1 != 69) {
                                    if (var1 != 70) {
                                       switch (var1) {
                                          case 43:
                                             this.realKeyLongClick1(this.mContext, 52, var2[3]);
                                             break;
                                          case 44:
                                             this.realKeyLongClick1(this.mContext, 2, var2[3]);
                                             break;
                                          case 45:
                                             this.realKeyLongClick1(this.mContext, 59, var2[3]);
                                       }
                                    } else {
                                       this.realKeyLongClick1(this.mContext, 8, var2[3]);
                                    }
                                 } else {
                                    this.realKeyLongClick1(this.mContext, 7, var2[3]);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 58, var2[3]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 151, var2[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 128, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 3, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 50, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 20, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 21, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 1, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void set0x31Air() {
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 0) {
            if (var1 != 12) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 5) {
                        if (var1 == 6) {
                           GeneralAirData.front_left_blow_window = false;
                           GeneralAirData.front_right_blow_window = false;
                           GeneralAirData.front_left_blow_foot = false;
                           GeneralAirData.front_right_blow_foot = false;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_window = false;
                        GeneralAirData.front_right_blow_window = false;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_window = false;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_right_blow_head = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_foot = false;
                  GeneralAirData.front_right_blow_foot = false;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_right_blow_head = false;
               }
            } else {
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
            }
         } else {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[9]);
         this.updateOutDoorTemp(this.mContext, this.df_1Decimal.format((double)this.mCanBusInfoInt[13] * 0.5 - 40.0) + this.getTempUnitC(this.mContext));
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void set0x32CarBody() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[4], var4[5])).append(" RPM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[6], var4[7])).append(" KM/H").toString()));
      var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_394_drive_v"), this.df_1Decimal.format((double)this.mCanBusInfoInt[8] * 0.1) + 3 + " V"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var6[6], var6[7]));
   }

   private void set0x34Oli() {
      ArrayList var5 = new ArrayList();
      int var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive1");
      StringBuilder var6 = new StringBuilder();
      DecimalFormat var8 = this.df_2Decimal;
      int[] var7 = this.mCanBusInfoInt;
      int var3 = var7[6];
      int var1 = var7[7];
      var5.add(new DriverUpdateEntity(var4, var2, var6.append(var8.format((double)(var7[8] & 255 | (var3 & 255) << 16 | (var1 & 255) << 8) * 0.1)).append("KM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive2");
      var6 = new StringBuilder();
      DecimalFormat var10 = this.df_2Decimal;
      int[] var12 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var6.append(var10.format((double)this.getMsbLsbResult(var12[9], var12[10]) * 0.1)).append("L/100KM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive3");
      var6 = new StringBuilder();
      var10 = this.df_2Decimal;
      var12 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var6.append(var10.format((long)this.getMsbLsbResult(var12[12], var12[13]))).append("KM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive4");
      StringBuilder var14 = new StringBuilder();
      DecimalFormat var9 = this.df_2Decimal;
      var7 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var14.append(var9.format((double)this.getMsbLsbResult(var7[14], var7[15]) * 0.1)).append("kWh/100KM").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive5");
      var14 = new StringBuilder();
      var9 = this.df_2Decimal;
      var7 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var14.append(var9.format((long)this.getMsbLsbResult(var7[17], var7[18]))).append("KM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive6");
      StringBuilder var13 = new StringBuilder();
      var8 = this.df_2Decimal;
      int[] var11 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var13.append(var8.format((long)this.getMsbLsbResult(var11[22], var11[23]))).append("KM").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x35AirSetting() {
      ArrayList var3 = new ArrayList();
      SettingUpdateEntity var4 = new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      boolean var1 = this.isI5();
      boolean var2 = false;
      if (!var1 && !this.isRX3()) {
         var1 = false;
      } else {
         var1 = true;
      }

      var3.add(var4.setEnable(var1));
      var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))).setEnable(this.isRX3()));
      var4 = new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1));
      if (!this.isI5() && !this.isRX3()) {
         var1 = false;
      } else {
         var1 = true;
      }

      label21: {
         var3.add(var4.setEnable(var1));
         var4 = new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1));
         if (!this.isI5()) {
            var1 = var2;
            if (!this.isRX3()) {
               break label21;
            }
         }

         var1 = true;
      }

      var3.add(var4.setEnable(var1));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x3Fpower() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd1");
      StringBuilder var4 = new StringBuilder();
      DecimalFormat var6 = this.df_1Decimal;
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var4.append(var6.format((double)this.getMsbLsbResult(var5[3], var5[4]) * 0.1)).append("V").toString()));
      var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd2"), this.getModel(this.mCanBusInfoInt[5])));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd3");
      StringBuilder var9 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var9.append(this.getMsbLsbResult(var7[8], var7[9])).append("RPM").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd4");
      StringBuilder var10 = new StringBuilder();
      DecimalFormat var8 = this.df_1Decimal;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var10.append(var8.format((double)this.getMsbLsbResult(var5[10], var5[11]) * 0.1)).append("A").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x41RadarInfo() {
      if (this.mCanBusInfoInt[12] != 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         GeneralParkData.isShowLeftTopOneDistanceUi = true;
         GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[11] + "CM";
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         if (var1[2] == 255) {
            var1[2] = 0;
         }

         if (var1[3] == 255) {
            var1[3] = 0;
         }

         if (var1[4] == 255) {
            var1[4] = 0;
         }

         if (var1[5] == 255) {
            var1[5] = 0;
         }

         if (var1[6] == 255) {
            var1[6] = 0;
         }

         if (var1[7] == 255) {
            var1[7] = 0;
         }

         if (var1[8] == 255) {
            var1[8] = 0;
         }

         if (var1[9] == 255) {
            var1[8] = 0;
         }

         RadarInfoUtil.setRearRadarLocationData(15, var1[2], var1[3], var1[4], var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(15, var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x48TireInfo() {
      GeneralTireData.isHaveSpareTire = false;
      this.arr0[0] = this.df_2Decimal.format((double)this.mCanBusInfoInt[4] * 0.1) + "BAR";
      this.arr0[1] = this.mCanBusInfoInt[9] - 60 + this.getTempUnitC(this.mContext);
      this.arr1[0] = this.df_2Decimal.format((double)this.mCanBusInfoInt[5] * 0.1) + "BAR";
      this.arr1[1] = this.mCanBusInfoInt[10] - 60 + this.getTempUnitC(this.mContext);
      this.arr2[0] = this.df_2Decimal.format((double)this.mCanBusInfoInt[6] * 0.1) + "BAR";
      this.arr2[1] = this.mCanBusInfoInt[11] - 60 + this.getTempUnitC(this.mContext);
      this.arr3[0] = this.df_2Decimal.format((double)this.mCanBusInfoInt[7] * 0.1) + "BAR";
      this.arr3[1] = this.mCanBusInfoInt[12] - 60 + this.getTempUnitC(this.mContext);
      this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
      this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x66Setting() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting1"), this.getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting2"), this.getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting3"), this.getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting5"), this.getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting6"), this.getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[0]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting7"), this.getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[0]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting8"), this.getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[0]))));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting9"), this.mCanBusInfoInt[15])).setProgress(this.mCanBusInfoInt[15]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting10"), this.getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting11"), this.getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting12"), this.getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting13"), this.getSwitch(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting14"), this.getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting15"), this.getSwitch(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting16"), this.getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting17"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting18"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting19"), this.getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting20"), this.getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting21"), this.getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting22"), this.getSwitch(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting23"), this.getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting24"), this.getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting25"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting26"), this.getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]))));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting27"), this.mCanBusInfoInt[16])).setProgress(this.mCanBusInfoInt[16] - 51));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting28"), this.getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting29"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting30"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting31"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting33"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting34"), this.getSwitch(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting35"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting36"), this.getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting37"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting38"), this.getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting39"), this.getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting40"), this.getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting41"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting42"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting43"), this.getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting44"), this.getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting45"), this.getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting46"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting47"), this.getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting48"), this.getSwitch(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting49"), this.getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting50"), this.getSwitch(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting51"), this.getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]))));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting52"), this.mCanBusInfoInt[13] / 5)).setProgress(this.mCanBusInfoInt[13] / 5 - 6));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting55_0"), this.getSettingState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting55"), this.getSettingState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting56"), this.getSettingState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting57"), this.getSettingState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting58"), this.getSettingState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting59"), this.getSettingState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting60"), this.getSettingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting61"), this.getEnergyState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2)))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting62"), this.getPmState(this.mCanBusInfoInt[14]))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xE8Panoro() {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_panoro"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_panoro", "_396_panoro1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_panoro"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_panoro", "_396_panoro2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_panoro"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_panoro", "_396_panoro3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
      Context var5 = this.mContext;
      int var1 = this.mCanBusInfoInt[5];
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.forceReverse(var5, var2);
      var4 = new ArrayList();
      if (this.mCanBusInfoInt[3] == 5) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(0, var2));
      if (this.mCanBusInfoInt[3] == 6) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(1, var2));
      if (this.mCanBusInfoInt[3] == 7) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(2, var2));
      if (this.mCanBusInfoInt[3] == 8) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(3, var2));
      if (this.mCanBusInfoInt[3] == 10) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(4, var2));
      if (this.mCanBusInfoInt[3] == 11) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(5, var2));
      if (this.mCanBusInfoInt[3] == 12) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(new PanoramicBtnUpdateEntity(6, var2));
      var2 = var3;
      if (this.mCanBusInfoInt[3] == 13) {
         var2 = true;
      }

      var4.add(new PanoramicBtnUpdateEntity(7, var2));
      var4.add(new PanoramicBtnUpdateEntity(8, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ^ true));
      var4.add(new PanoramicBtnUpdateEntity(9, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      GeneralParkData.dataList = var4;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0xF0Version() {
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
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 20) {
               if (var3 != 21) {
                  if (var3 != 26) {
                     if (var3 != 63) {
                        if (var3 != 65) {
                           if (var3 != 72) {
                              if (var3 != 102) {
                                 if (var3 != 232) {
                                    if (var3 != 240) {
                                       if (var3 != 33) {
                                          if (var3 != 34) {
                                             if (var3 != 49) {
                                                if (var3 != 50) {
                                                   if (var3 != 52) {
                                                      if (var3 == 53) {
                                                         this.set0x35AirSetting();
                                                      }
                                                   } else {
                                                      this.set0x34Oli();
                                                   }
                                                } else {
                                                   this.set0x32CarBody();
                                                }
                                             } else {
                                                this.set0x31Air();
                                             }
                                          } else {
                                             this.set0x04PanoraKnoy();
                                          }
                                       } else {
                                          this.set0x21PanoraKey();
                                       }
                                    } else {
                                       this.set0xF0Version();
                                    }
                                 } else {
                                    this.set0xE8Panoro();
                                 }
                              } else {
                                 this.set0x66Setting();
                              }
                           } else {
                              this.set0x48TireInfo();
                           }
                        } else {
                           this.set0x41RadarInfo();
                        }
                     } else {
                        this.set0x3Fpower();
                     }
                  } else {
                     this.set0x1ADoor();
                     this.set0x1ASpeed();
                     this.set0x1ATrack();
                     this.set0x1APanoro();
                  }
               } else {
                  this.set0x15Power();
               }
            } else {
               this.set0x14Oli();
            }
         } else {
            this.set0x12Door();
         }
      } else {
         this.set0x11SWC();
         this.set0x11Track();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      this.getUiMgr(this.mContext).sendTime0xCB(0, var5, var6, 0, 0, var10, var1 - 2000, var3, var4, 0);
   }

   public void hideP360Button() {
      if (this.systemButton == null) {
         this.systemButton = new SystemButton(this.getActivity(), "360", new SystemButton.PanoramaListener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void clickEvent() {
               MsgMgr var1 = this.this$0;
               var1.getUiMgr(var1.mContext).sendPanoro0xF2(15, 1);
            }
         });
      }

      this.systemButton.hide();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).sendCarType0x24(this.getCurrentEachCanId());
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
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
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoro0xF2(15, 1);
                  }
               });
            }

            this.this$0.systemButton.show();
         }
      });
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
