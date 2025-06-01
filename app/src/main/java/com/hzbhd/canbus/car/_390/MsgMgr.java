package com.hzbhd.canbus.car._390;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
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
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int nowState;

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

   private String getMediaType() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 10) {
                  if (var1 != 12) {
                     return var1 != 13 ? "OTHER" : "USB";
                  } else {
                     return "AUX";
                  }
               } else {
                  return "PHONE";
               }
            } else {
               return "CD";
            }
         } else {
            return "FM";
         }
      } else {
         return "OFF";
      }
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

   private String getTimeStr() {
      int[] var3 = this.mCanBusInfoInt;
      String var2;
      if (var3[9] == 0) {
         var2 = "(12H)";
      } else {
         var2 = "(24H)";
      }

      int var1 = var3[8];
      StringBuilder var4;
      if (var1 != 0) {
         StringBuilder var5;
         int[] var6;
         if (var1 != 1) {
            if (var1 != 2) {
               var4 = new StringBuilder();
               var3 = this.mCanBusInfoInt;
               return var4.append(var3[2] * 256 + var3[3]).append("/").append(this.mCanBusInfoInt[4]).append("/").append(this.mCanBusInfoInt[5]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(var2).toString();
            } else {
               var5 = new StringBuilder();
               var6 = this.mCanBusInfoInt;
               return var5.append(var6[2] * 256 + var6[3]).append(".").append(this.mCanBusInfoInt[4]).append(".").append(this.mCanBusInfoInt[5]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(var2).toString();
            }
         } else {
            var5 = (new StringBuilder()).append(this.mCanBusInfoInt[4]).append("/").append(this.mCanBusInfoInt[5]).append("/");
            var6 = this.mCanBusInfoInt;
            return var5.append(var6[2] * 256 + var6[3]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(var2).toString();
         }
      } else {
         var4 = (new StringBuilder()).append(this.mCanBusInfoInt[5]).append(".").append(this.mCanBusInfoInt[4]).append(".");
         var3 = this.mCanBusInfoInt;
         return var4.append(var3[2] * 256 + var3[3]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(var2).toString();
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

   private void set0x81BasicInfo() {
      this.set0x81WheelKeyInfo();
      this.set0x81TrackInfo();
      this.set0x81RadarInfo();
   }

   private void set0x81RadarInfo() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[8], var1[9], var1[10], var1[11]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[12], var1[13], var1[14], var1[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x81TrackInfo() {
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0 && var1 != 255) {
         GeneralParkData.trackAngle = -var1 / 5;
      } else {
         GeneralParkData.trackAngle = var1 / 5;
      }

   }

   private void set0x81WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     switch (var1) {
                        case 8:
                           this.realKeyClick4(this.mContext, 45);
                           break;
                        case 9:
                           this.realKeyClick4(this.mContext, 46);
                           break;
                        case 10:
                           this.realKeyClick4(this.mContext, 2);
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 188);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 187);
               }
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         } else {
            this.realKeyClick4(this.mContext, 7);
         }
      } else {
         this.realKeyClick4(this.mContext, 0);
      }

   }

   private void set0x83Idrive() {
      this.set0x83WheelKeyInfo();
      this.set0x83Knob();
   }

   private void set0x83Knob() {
      int var1 = this.nowState;
      int var2 = this.mCanBusInfoInt[3];
      if (var1 < var2) {
         this.realKeyClick4(this.mContext, 7);
      } else if (var1 > var2) {
         this.realKeyClick4(this.mContext, 8);
      }

      this.nowState = this.mCanBusInfoInt[3];
   }

   private void set0x83WheelKeyInfo() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick4(this.mContext, 0);
            break;
         case 1:
            this.realKeyClick4(this.mContext, 53);
            break;
         case 2:
            this.realKeyClick4(this.mContext, 151);
            break;
         case 3:
            this.realKeyClick4(this.mContext, 14);
            break;
         case 4:
            this.realKeyClick4(this.mContext, 50);
            break;
         case 5:
            this.realKeyClick4(this.mContext, 58);
            break;
         case 6:
            this.realKeyClick4(this.mContext, 47);
            break;
         case 7:
            this.realKeyClick4(this.mContext, 48);
            break;
         case 8:
            this.realKeyClick4(this.mContext, 49);
      }

   }

   private void set0x84MediaState() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_390_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_390_drive1"), this.getMediaType()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x85TimeInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_390_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_390_drive2"), this.getTimeStr()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0xF0VersionInfo() {
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

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0xE1Media(12);
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
      if (var3 != 129) {
         if (var3 != 240) {
            switch (var3) {
               case 131:
                  this.set0x83Idrive();
                  break;
               case 132:
                  this.set0x84MediaState();
                  break;
               case 133:
                  this.set0x85TimeInfo();
            }
         } else {
            this.set0xF0VersionInfo();
         }
      } else {
         this.set0x81BasicInfo();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xE1Media(6);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.equals("FM1") || var2.equals("FM2") || var2.equals("FM3")) {
         this.getUiMgr(this.mContext).send0xE1Media(1);
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.getUiMgr(this.mContext).send0xE1Media(0);
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
