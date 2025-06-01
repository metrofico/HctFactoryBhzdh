package com.hzbhd.canbus.car._418;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
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
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int[] door0x12;
   int[] door0x1A;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
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

   private boolean isNotDoor0x12Change() {
      if (Arrays.equals(this.door0x12, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.door0x12 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotDoor0x1AChange() {
      if (Arrays.equals(this.door0x1A, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.door0x1A = Arrays.copyOf(var1, var1.length);
         return false;
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

   private void set0x11Swc() {
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
                                 this.buttonKey(2);
                              }
                           } else {
                              this.buttonKey(46);
                           }
                        } else {
                           this.buttonKey(45);
                        }
                     } else {
                        this.buttonKey(15);
                     }
                  } else {
                     this.buttonKey(14);
                  }
               } else {
                  this.buttonKey(3);
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

   private void set0x12Door() {
      if (!this.isNotDoor0x12Change()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x13CarInfo() {
      ArrayList var5 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive1");
      StringBuilder var6 = new StringBuilder();
      DecimalFormat var8 = this.df_1Decimal;
      int[] var7 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var6.append(var8.format((long)this.getMsbLsbResult(var7[2], var7[3]))).append("L/100km").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive2");
      var6 = new StringBuilder();
      DecimalFormat var9 = this.df_1Decimal;
      int[] var10 = this.mCanBusInfoInt;
      int var3 = var10[4];
      var2 = var10[5];
      var5.add(new DriverUpdateEntity(var1, var4, var6.append(var9.format((double)((float)(var10[6] & 255 | (var3 & 255) << 16 | (var2 & 255) << 8) / 10.0F))).append("Km").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive3");
      var6 = new StringBuilder();
      var7 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var6.append(this.getMsbLsbResult(var7[10], var7[11])).append("km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x32Car() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive4"), this.df_1Decimal.format((double)this.mCanBusInfoInt[8] * 0.1) + "V"));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive5"), this.df_1Decimal.format((double)this.mCanBusInfoInt[9] * 0.1) + "A"));
      this.updateOutDoorTemp(this.mContext, this.df_1Decimal.format((double)this.mCanBusInfoInt[10] * 0.5 - 40.0) + this.getTempUnitC(this.mContext));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive6"), this.mCanBusInfoInt[11] + "%"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x48TireInfo() {
      GeneralTireData.isHaveSpareTire = false;
      this.arr0[0] = this.mCanBusInfoInt[6] + "BAR";
      this.arr0[1] = this.mCanBusInfoInt[2] + this.getTempUnitC(this.mContext);
      this.arr1[0] = this.mCanBusInfoInt[7] + "BAR";
      this.arr1[1] = this.mCanBusInfoInt[3] + this.getTempUnitC(this.mContext);
      this.arr2[0] = this.mCanBusInfoInt[8] + "BAR";
      this.arr2[1] = this.mCanBusInfoInt[4] + this.getTempUnitC(this.mContext);
      this.arr3[0] = this.mCanBusInfoInt[9] + "BAR";
      this.arr3[1] = this.mCanBusInfoInt[5] + this.getTempUnitC(this.mContext);
      this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
      this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x61Setting() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_418_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_418_setting", "_418_setting0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_418_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_418_setting", "_418_setting1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_418_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_418_setting", "_418_setting2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xF0VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set360Info() {
      Context var3 = this.mContext;
      int var1 = this.mCanBusInfoInt[10];
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      this.forceReverse(var3, var2);
   }

   private void setDoorInfo0x1A() {
      if (!this.isNotDoor0x1AChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setSpeedInfo0x1A() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive7");
      StringBuilder var6 = new StringBuilder();
      DecimalFormat var5 = this.df_1Decimal;
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var6.append(var5.format((double)this.getMsbLsbResult(var4[5], var4[6]) * 0.1)).append("km/h").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_418_drive");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_418_drive8");
      StringBuilder var8 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var8.append(this.getMsbLsbResult(var4[11], var4[12])).append("RPM").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var7 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var7[5], var7[6]) / 10);
   }

   private void setTrack0x1A() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
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
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 26) {
         if (var3 != 50) {
            if (var3 != 72) {
               if (var3 != 97) {
                  if (var3 != 240) {
                     switch (var3) {
                        case 17:
                           this.set0x11Swc();
                           break;
                        case 18:
                           this.set0x12Door();
                           break;
                        case 19:
                           this.set0x13CarInfo();
                     }
                  } else {
                     this.set0xF0VersionInfo();
                  }
               } else {
                  this.set0x61Setting();
               }
            } else {
               this.set0x48TireInfo();
            }
         } else {
            this.set0x32Car();
         }
      } else {
         this.setDoorInfo0x1A();
         this.setSpeedInfo0x1A();
         this.setTrack0x1A();
         this.set360Info();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
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
