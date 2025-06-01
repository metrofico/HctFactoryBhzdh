package com.hzbhd.canbus.car._371;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mRearRadarData;
   byte[] mTrackData;
   private UiMgr mUiMgr;

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

   private String get0xC7Data0State(boolean var1) {
      return var1 ? this.mContext.getString(2131764446) : this.mContext.getString(2131764445);
   }

   private String get0xC7Data1State(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? "NO INFO" : this.mContext.getString(2131764452);
            } else {
               return this.mContext.getString(2131764451);
            }
         } else {
            return this.mContext.getString(2131764450);
         }
      } else {
         return this.mContext.getString(2131764449);
      }
   }

   private String getAscllContent() {
      return "Ascll RESULT";
   }

   private String getBtSignalState(int var1) {
      return var1 == 0 ? "NO INFO" : var1 - 1 + "Lever";
   }

   private String getBtState(boolean var1) {
      return var1 ? this.mContext.getString(2131764470) : this.mContext.getString(2131764469);
   }

   private String getContent() {
      String var1;
      if (this.mCanBusInfoInt[3] == 0) {
         MyLog.temporaryTracking("解ASCLL");
         var1 = this.getAscllContent();
         MyLog.temporaryTracking("ASCLL结果：" + var1);
      } else {
         var1 = this.getUnicodeContent();
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getUnicodeContent() {
      ArrayList var3 = new ArrayList();
      int var1 = 5;

      for(String var2 = ""; var1 < this.mCanBusInfoInt.length; ++var1) {
         if (var1 % 2 != 0) {
            var2 = "u" + String.format("%02x", this.mCanBusInfoInt[var1]);
         } else {
            var2 = var2 + String.format("%02x", this.mCanBusInfoInt[var1]);
            MyLog.temporaryTracking("第" + var1 + "次：" + var2);
            var3.add(var2);
            var2 = "";
         }
      }

      StringBuffer var4 = new StringBuffer();
      Iterator var5 = var3.iterator();

      while(var5.hasNext()) {
         var4.append((char)Integer.valueOf(((String)var5.next()).substring(1), 16));
      }

      return var4.toString();
   }

   private String getUsbPlayState(int var1) {
      switch (var1) {
         case 0:
            return this.mContext.getString(2131764455);
         case 1:
            return this.mContext.getString(2131764456);
         case 2:
            return this.mContext.getString(2131764457);
         case 3:
            return this.mContext.getString(2131764458);
         case 4:
            return this.mContext.getString(2131764459);
         case 5:
            return this.mContext.getString(2131764460);
         case 6:
            return this.mContext.getString(2131764461);
         default:
            return "NO INFO";
      }
   }

   private Object getUsbState(boolean var1) {
      return var1 ? this.mContext.getString(2131764464) : this.mContext.getString(2131764463);
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

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
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

   private void set0x20WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 16) {
                        if (var1 != 23) {
                           switch (var1) {
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
                           }
                        } else {
                           this.buttonKey(151);
                        }
                     } else {
                        this.buttonKey(182);
                     }
                  } else {
                     this.buttonKey(45);
                  }
               } else {
                  this.buttonKey(46);
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

   private void set0x22RearRadar() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadar() {
      if (this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x24BaicInfo() {
      int[] var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 0);
      var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 2);
      var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 3);
      var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 4);
      var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 5);
      var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 6);
      var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 7);
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isShowHandBrake = true;
         GeneralDoorData.isHandBrakeUp = true ^ DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x29EspInfo() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x77Info() {
      int var1 = this.mCanBusInfoInt[2];
      ArrayList var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  var2 = new ArrayList();
                  var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4_2"), this.getContent()));
                  this.updateGeneralDriveData(var2);
                  this.updateDriveDataActivity((Bundle)null);
               }
            } else {
               var2 = new ArrayList();
               var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4_1"), this.getContent()));
               this.updateGeneralDriveData(var2);
               this.updateDriveDataActivity((Bundle)null);
            }
         } else {
            var2 = new ArrayList();
            var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4_0"), this.getContent()));
            this.updateGeneralDriveData(var2);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         var2 = new ArrayList();
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4"), this.getContent()));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x78WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 29) {
            if (var1 != 30) {
               switch (var1) {
                  case 17:
                     this.buttonKey(76);
                     break;
                  case 18:
                     this.buttonKey(77);
                     break;
                  case 19:
                     this.buttonKey(61);
                     break;
                  case 20:
                     this.buttonKey(59);
                     break;
                  case 21:
                     this.buttonKey(151);
                     break;
                  case 22:
                     this.buttonKey(17);
                     break;
                  default:
                     switch (var1) {
                        case 24:
                           this.buttonKey(50);
                           break;
                        case 25:
                           this.buttonKey(49);
                           break;
                        case 26:
                           this.buttonKey(4);
                     }
               }
            } else {
               this.buttonKey(45);
            }
         } else {
            this.buttonKey(46);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void set0x79VolInfo() {
      String var1;
      if (this.mCanBusInfoInt[2] == 0) {
         var1 = "No information";
      } else {
         var1 = this.mCanBusInfoInt[3] + "";
      }

      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads", "_371_ads1"), var1)).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x7ATimeInfo() {
      String var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            var1 = "Afternoon " + this.df_2Integer.format((long)this.mCanBusInfoInt[3]) + ":" + this.df_2Integer.format((long)this.mCanBusInfoInt[4]);
         } else {
            var1 = "Morning " + this.df_2Integer.format((long)this.mCanBusInfoInt[3]) + ":" + this.df_2Integer.format((long)this.mCanBusInfoInt[4]);
         }
      } else {
         var1 = "" + this.df_2Integer.format((long)this.mCanBusInfoInt[3]) + ":" + this.df_2Integer.format((long)this.mCanBusInfoInt[4]);
      }

      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads", "_371_ads2"), var1)).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x7BInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads", "_371_ads3"), this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x7CInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads5", "_371_ads5_7"), this.get0xC7Data0State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads5", "_371_ads5_6"), this.get0xC7Data0State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads5", "_371_ads5_5"), this.get0xC7Data0State(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads6", "_371_ads6_0"), this.get0xC7Data1State(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads7", "_371_ads77"), this.getUsbState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads7", "_371_ads73_0"), this.getUsbPlayState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads8", "_371_ads87"), this.getBtState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads8", "_371_ads85"), this.getBtSignalState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3)))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads8", "_371_ads82"), this.getBtSignalState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3)))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
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
      if (var3 != 32) {
         if (var3 != 41) {
            if (var3 != 48) {
               switch (var3) {
                  case 34:
                     this.set0x22RearRadar();
                     break;
                  case 35:
                     this.set0x23FrontRadar();
                     break;
                  case 36:
                     this.set0x24BaicInfo();
                     break;
                  default:
                     switch (var3) {
                        case 119:
                           this.set0x77Info();
                           break;
                        case 120:
                           this.set0x78WheelKeyInfo();
                           break;
                        case 121:
                           this.set0x79VolInfo();
                           break;
                        case 122:
                           this.set0x7ATimeInfo();
                           break;
                        case 123:
                           this.set0x7BInfo();
                           break;
                        case 124:
                           this.set0x7CInfo();
                     }
               }
            } else {
               this.set0x30VersionInfo();
            }
         } else {
            this.set0x29EspInfo();
         }
      } else {
         this.set0x20WheelKeyInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
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

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }
}
