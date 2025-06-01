package com.hzbhd.canbus.car._333;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   int[] accInfoInt;
   private int airData7 = 0;
   private int airRearKey = 0;
   private int computerInfoTag = 1;
   private int data0xCB1;
   private int data0xCB10;
   private int data0xCB11;
   private int data0xCB12;
   private int data0xCB13;
   private int data0xCB14;
   private int data0xCB15;
   private int data0xCB16;
   private int data0xCB17;
   private int data0xCB18;
   private int data0xCB19;
   private int data0xCB2;
   private int data0xCB20;
   private int data0xCB3;
   private int data0xCB4;
   private int data0xCB5;
   private int data0xCB6;
   private int data0xCB7;
   private int data0xCB8;
   private int data0xCB9;
   private int differentId;
   private int eachId;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   int[] mCarDoorData;
   int[] mCarDoorData2;
   Context mContext;
   int[] mFrontRadarData;
   int[] mRearRadarData;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");
   private int radioData0;
   private int radioData1;
   private int radioData2;
   private int radioData3;
   DecimalFormat timeFormat = new DecimalFormat("00");
   DecimalFormat towDecimal = new DecimalFormat("###0.00");
   private String unit;
   private int volInfoData0;

   private void adjustBrightness() {
      int var1 = MediaShareData.Screen.INSTANCE.getScreenBacklight();
      if (var1 == 5) {
         this.setBacklightLevel(1);
      } else {
         this.setBacklightLevel(var1 + 1);
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

   private String getAccState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763271) : this.mContext.getString(2131763272);
   }

   private String getBassState(int var1) {
      return "Bass " + (var1 - 10);
   }

   private String getCarModel(int var1) {
      return var1 == 5 ? "世嘉" : "暂无数据";
   }

   private String getCarSeries(int var1) {
      return var1 == 10 ? "标致、雪铁龙系列" : "暂无数据";
   }

   private String getCarStateMode(int var1) {
      if (var1 == 1) {
         return this.mContext.getString(2131763095);
      } else if (var1 == 2) {
         return this.mContext.getString(2131763096);
      } else if (var1 == 3) {
         return this.mContext.getString(2131763097);
      } else {
         return var1 == 4 ? this.mContext.getString(2131763098) : "无";
      }
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private String getEconomicModelState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763192) : this.mContext.getString(2131763193);
   }

   private String getFRBalanceState(int var1) {
      var1 -= 10;
      if (var1 > 0) {
         return "Front" + var1;
      } else {
         return var1 == 0 ? "Middle" : "Rear" + -var1;
      }
   }

   private int getFuelConsumptionUnit(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? 2 : 1;
      } else {
         return 0;
      }
   }

   private String getFuelState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763251) : this.mContext.getString(2131763252);
   }

   private String getHighState(int var1) {
      return "High " + (var1 - 10);
   }

   private String getLRBalanceState(int var1) {
      var1 -= 10;
      if (var1 > 0) {
         return "Left" + var1;
      } else {
         return var1 == 0 ? "Middle" : "Right" + -var1;
      }
   }

   private int getLangrage() {
      // $FF: Couldn't be decompiled
   }

   private String getLittleLampState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763271) : this.mContext.getString(2131763272);
   }

   private String getLoudnessState(boolean var1) {
      return var1 ? this.mContext.getString(2131763296) : this.mContext.getString(2131763295);
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

   private String getOutdoorTemperature() {
      return DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "-" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + this.getTempUnitC(this.mContext) : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + this.getTempUnitC(this.mContext);
   }

   private String getPState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763201) : this.mContext.getString(2131763202);
   }

   private String getParkingBasicState(boolean var1) {
      return var1 ? this.mContext.getString(2131763153) : this.mContext.getString(2131763152);
   }

   private String getParkingState(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131763155);
         case 2:
            return this.mContext.getString(2131763164);
         case 3:
            return this.mContext.getString(2131763165);
         case 4:
            return this.mContext.getString(2131763166);
         case 5:
            return this.mContext.getString(2131763167);
         case 6:
            return this.mContext.getString(2131763168);
         case 7:
            return this.mContext.getString(2131763169);
         case 8:
            return this.mContext.getString(2131763170);
         case 9:
            return this.mContext.getString(2131763171);
         case 10:
            return this.mContext.getString(2131763172);
         case 11:
            return this.mContext.getString(2131763173);
         case 12:
            return this.mContext.getString(2131763174);
         case 13:
            return this.mContext.getString(2131763175);
         case 14:
            return this.mContext.getString(2131763176);
         case 15:
            return this.mContext.getString(2131763177);
         case 16:
            return this.mContext.getString(2131763156);
         case 17:
            return this.mContext.getString(2131763157);
         case 18:
            return this.mContext.getString(2131763158);
         case 19:
            return this.mContext.getString(2131763159);
         case 20:
            return this.mContext.getString(2131763160);
         case 21:
            return this.mContext.getString(2131763161);
         case 22:
            return this.mContext.getString(2131763162);
         case 23:
            return this.mContext.getString(2131763163);
         default:
            return this.mContext.getString(2131763154);
      }
   }

   private int getRadarDistance(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  return var1 != 4 ? 0 : 10;
               } else {
                  return 7;
               }
            } else {
               return 5;
            }
         } else {
            return 3;
         }
      } else {
         return 1;
      }
   }

   private String getRadarDistanceStr(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  return var1 != 4 ? this.mContext.getString(2131763112) : this.mContext.getString(2131763111);
               } else {
                  return this.mContext.getString(2131763110);
               }
            } else {
               return this.mContext.getString(2131763109);
            }
         } else {
            return this.mContext.getString(2131763108);
         }
      } else {
         return this.mContext.getString(2131763107);
      }
   }

   private String getReversingState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763198) : this.mContext.getString(2131763199);
   }

   private String getSoundDistributionState(boolean var1) {
      return var1 ? this.mContext.getString(2131763072) : this.mContext.getString(2131763071);
   }

   private String getSoundEffects(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  return var1 != 5 ? this.mContext.getString(2131763075) : this.mContext.getString(2131763080);
               } else {
                  return this.mContext.getString(2131763079);
               }
            } else {
               return this.mContext.getString(2131763078);
            }
         } else {
            return this.mContext.getString(2131763077);
         }
      } else {
         return this.mContext.getString(2131763076);
      }
   }

   private String getSwitchState(int var1) {
      if (var1 == 1) {
         return this.mContext.getString(2131763102);
      } else {
         return var1 == 2 ? this.mContext.getString(2131763101) : "无";
      }
   }

   private String getTemperature1(int var1, String var2) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 255) {
         return "HI";
      } else {
         return var2.trim().equals("C") ? this.towDecimal.format((double)(var1 - 32) * 0.5 + 16.0) + this.getTempUnitC(this.mContext) : this.towDecimal.format((long)var1) + this.getTempUnitF(this.mContext);
      }
   }

   private String getTemperature2(int var1, String var2) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 255) {
         return "HI";
      } else {
         return var2.trim().equals("C") ? this.towDecimal.format((double)(var1 - 32) * 0.5 + 14.0) + this.getTempUnitC(this.mContext) : this.towDecimal.format((long)var1) + this.getTempUnitF(this.mContext);
      }
   }

   private UiMgr getmUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void haveDistanceInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.panoramicSwitch(false);
               }
            } else {
               this.panoramicSwitch(true);
               if (this.isFrontRadarDataChange()) {
                  RadarInfoUtil.mMinIsClose = true;
                  RadarInfoUtil.setRearRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[3]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[5]));
                  RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[6]), this.getRadarDistance(this.mCanBusInfoInt[7]), this.getRadarDistance(this.mCanBusInfoInt[7]), this.getRadarDistance(this.mCanBusInfoInt[8]));
                  GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                  this.updateParkUi((Bundle)null, this.mContext);
               }
            }
         } else {
            this.panoramicSwitch(false);
            ArrayList var2 = new ArrayList();
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_drivePage_front_radar_left"), this.getRadarDistanceStr(this.mCanBusInfoInt[6])));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_drivePage_front_radar_mid"), this.getRadarDistanceStr(this.mCanBusInfoInt[7])));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_drivePage_front_radar_right"), this.getRadarDistanceStr(this.mCanBusInfoInt[8])));
            this.updateGeneralDriveData(var2);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         this.panoramicSwitch(false);
      }

   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte[] var8 = new byte[]{22, -127, 1};
            byte[] var5 = new byte[]{22, -124, 1, 1};
            byte var3 = (byte)(-GeneralAmplifierData.frontRear + 10);
            byte[] var6 = new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.leftRight + 10)};
            byte[] var9 = new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandTreble + 3)};
            byte[] var7 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandBass + 3)};
            byte[] var4 = new byte[]{22, -124, 10, 1};
            this.iterator = Arrays.stream(new byte[][]{var8, var5, {22, -124, 3, var3}, var6, var9, var7, var4}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private boolean isAccInfoChange(int[] var1) {
      if (Arrays.equals(this.accInfoInt, var1)) {
         return true;
      } else {
         this.accInfoInt = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return true;
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

   private boolean isBasicInfoChange2() {
      if (Arrays.equals(this.mCarDoorData2, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData2 = Arrays.copyOf(var1, var1.length);
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

   private String isHaveAmplifier(boolean var1) {
      return var1 ? this.mContext.getString(2131763069) : this.mContext.getString(2131763068);
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

   private String isShowSpeedTag(boolean var1) {
      return var1 ? "显示" : "不显示";
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

   private void noHaveDistanceInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.panoramicSwitch(false);
               }
            } else {
               this.panoramicSwitch(true);
               if (this.isFrontRadarDataChange()) {
                  RadarInfoUtil.mMinIsClose = true;
                  RadarInfoUtil.setRearRadarLocationData(10, 0, 0, 0, 0);
                  RadarInfoUtil.setFrontRadarLocationData(10, 0, 0, 0, 0);
                  GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                  this.updateParkUi((Bundle)null, this.mContext);
               }
            }
         } else {
            this.panoramicSwitch(false);
         }
      } else {
         this.panoramicSwitch(false);
      }

      this.getmUigMgr(this.mContext).getParkPageUiSet(this.mContext).setShowRadar(false);
   }

   private void sendPhoneStateInfo(int var1, int var2) {
      this.getmUigMgr(this.mContext);
      UiMgr.phoneStateData1Bit4 = 1;
      if (var1 == 0) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit6 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit5 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit4 = 0;
      } else if (var1 == 1) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit6 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit5 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit4 = 1;
      } else if (var1 == 2) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit6 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit5 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit4 = 0;
      } else if (var1 == 3) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit6 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit5 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit4 = 1;
      } else if (var1 == 4) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit6 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit5 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit4 = 0;
      }

      if (var2 == 0) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit2 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit1 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit0 = 0;
      } else if (var2 == 1) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit2 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit1 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit0 = 1;
      } else if (var2 == 2) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit2 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit1 = 2;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit0 = 0;
      } else if (var2 == 3) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit2 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit1 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit0 = 1;
      } else if (var2 == 4) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit2 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit1 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData0Bit0 = 0;
      }

      this.getmUigMgr(this.mContext).sendPhoneStateInfo();
   }

   private void setAccInfo0xEA() {
      if (!this.isAccInfoChange(this.mCanBusInfoInt)) {
         MyLog.temporaryTracking("ACC状态发生改变");
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 0, this.getAccState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)))).setValueStr(true));
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)));
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 2, this.mCanBusInfoInt[3]));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 0) {
            this.getmUigMgr(this.mContext);
            UiMgr.accState = 0;
         } else {
            this.getmUigMgr(this.mContext);
            UiMgr.accState = 1;
         }

         this.getmUigMgr(this.mContext);
         UiMgr.delayTime = this.mCanBusInfoInt[3];
      }
   }

   private void setAirInfo0x21() {
      ArrayList var3 = new ArrayList();
      boolean var2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      Integer var5 = 0;
      Integer var4 = 1;
      if (var2) {
         this.unit = "F";
         var3.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 0, var4));
      } else {
         this.unit = "C";
         var3.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 0, var5));
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2) == 0) {
         var3.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 1, var5));
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2) == 1) {
         var3.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 1, var4));
      } else {
         var3.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 1, 2));
      }

      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
      this.mCanBusInfoInt[8] = 0;
      if (this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         if (this.eachId != 11) {
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         }

         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1;
         if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_auto_wind = true;
            if (this.eachId != 11) {
               GeneralAirData.front_right_auto_wind = true;
            }
         } else {
            GeneralAirData.front_left_auto_wind = false;
            if (this.eachId != 11) {
               GeneralAirData.front_right_auto_wind = false;
            }
         }

         int var1 = this.eachId;
         if (var1 == 11 && var1 == 14) {
            GeneralAirData.front_left_temperature = this.getTemperature2(this.mCanBusInfoInt[4], this.unit);
            GeneralAirData.front_right_temperature = this.getTemperature2(this.mCanBusInfoInt[5], this.unit);
         } else {
            GeneralAirData.front_left_temperature = this.getTemperature1(this.mCanBusInfoInt[4], this.unit);
            GeneralAirData.front_right_temperature = this.getTemperature1(this.mCanBusInfoInt[5], this.unit);
         }

         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         boolean var6;
         if (this.airRearKey != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)) {
            GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            this.airRearKey = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1);
            var6 = true;
         } else {
            var6 = false;
         }

         if (this.airData7 != this.mCanBusInfoInt[9]) {
            if (this.eachId == 5) {
               GeneralAirData.rear_left_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5) + "档";
               GeneralAirData.rear_right_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5) + "档";
               GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3);
               this.airData7 = this.mCanBusInfoInt[9];
            }

            this.airData7 = this.mCanBusInfoInt[9];
            var6 = true;
         }

         if (this.eachId == 11) {
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]);
            if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]) && !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10])) {
               GeneralAirData.front_right_auto_wind = true;
            } else {
               GeneralAirData.front_right_auto_wind = false;
            }
         }

         if (var6) {
            this.updateAirActivity(this.mContext, 1002);
         } else {
            this.updateAirActivity(this.mContext, 1001);
         }

      }
   }

   private void setAlertInfo0x37() {
      String var7 = this.mContext.getString(2131763088);
      int var2;
      String var4;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var4 = "" + "  【  " + var7 + 1 + "：" + this.mContext.getString(2131757958) + "】    ";
         var2 = 2;
      } else {
         var4 = "";
         var2 = 1;
      }

      String var5 = var4;
      int var1 = var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757959) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757960) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757961) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757962) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      int var3 = var2;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757963) + "】    ";
         var3 = var2 + 1;
      }

      var4 = var5;
      var1 = var3;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         var4 = var5 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131757964) + "】    ";
         var1 = var3 + 1;
      }

      String var6 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var6 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757965) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var5 = var6 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757993) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757994) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757995) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var3 = var1;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757996) + "】    ";
         var3 = var1 + 1;
      }

      var5 = var4;
      var2 = var3;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         var5 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131757997) + "】    ";
         var2 = var3 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757998) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757999) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758000) + "】    ";
         var1 = var2 + 1;
      }

      var6 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
         var6 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758001) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
         var5 = var6 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758002) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758003) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758004) + "】    ";
         var1 = var2 + 1;
      }

      var6 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
         var6 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758005) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
         var4 = var6 + "  【  " + var2 + "：" + this.mContext.getString(2131758006) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758007) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758008) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758009) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758010) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758011) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758012) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758013) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758014) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var3 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758015) + "】    ";
         var3 = var1 + 1;
      }

      var4 = var5;
      var2 = var3;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
         var4 = var5 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758016) + "】    ";
         var2 = var3 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758017) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var3 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758018) + "】    ";
         var3 = var1 + 1;
      }

      var5 = var4;
      var2 = var3;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
         var5 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758019) + "】    ";
         var2 = var3 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758020) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758021) + "】    ";
         var2 = var1 + 1;
      }

      var6 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
         var6 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758022) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var6;
      var2 = var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7])) {
         var4 = var6 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758023) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758024) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758025) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var3 = var2;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758026) + "】    ";
         var3 = var2 + 1;
      }

      var4 = var5;
      var1 = var3;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7])) {
         var4 = var5 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758027) + "】    ";
         var1 = var3 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758028) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758029) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var3 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758030) + "】    ";
         var3 = var1 + 1;
      }

      var4 = var5;
      var2 = var3;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])) {
         var4 = var5 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758031) + "】    ";
         var2 = var3 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758032) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var3 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758033) + "】    ";
         var3 = var1 + 1;
      }

      var5 = var4;
      var2 = var3;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8])) {
         var5 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758034) + "】    ";
         var2 = var3 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758035) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758036) + "】    ";
         var2 = var1 + 1;
      }

      var6 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
         var6 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758037) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var6;
      var2 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
         var4 = var6 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758038) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758039) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[9])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758040) + "】    ";
         var2 = var1 + 1;
      }

      var6 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9])) {
         var6 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758041) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var6;
      var3 = var1;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9])) {
         var5 = var6 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758042) + "】    ";
         var3 = var1 + 1;
      }

      var4 = var5;
      var2 = var3;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9])) {
         var4 = var5 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758043) + "】    ";
         var2 = var3 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758044) + "】    ";
         var1 = var2 + 1;
      }

      var6 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9])) {
         var6 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758045) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
         var4 = var6 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758046) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var3 = var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758047) + "】    ";
         var3 = var1 + 1;
      }

      var4 = var5;
      var2 = var3;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10])) {
         var4 = var5 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131758048) + "】    ";
         var2 = var3 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758049) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758050) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758051) + "】    ";
         var1 = var2 + 1;
      }

      var6 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10])) {
         var6 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758052) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10])) {
         var4 = var6 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758053) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758054) + "】    ";
         var2 = var1 + 1;
      }

      var6 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[11])) {
         var6 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758055) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var6;
      var2 = var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[11])) {
         var4 = var6 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758056) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[11])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758057) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758058) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758059) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758060) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131758061) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131758062) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757966) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var3 = var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757967) + "】    ";
         var3 = var1 + 1;
      }

      var6 = var4;
      var2 = var3;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12])) {
         var6 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131757968) + "】    ";
         var2 = var3 + 1;
      }

      var5 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12])) {
         var5 = var6 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757969) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757970) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      var1 = var2;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757971) + "】    ";
         var1 = var2 + 1;
      }

      var6 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12])) {
         var6 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757972) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])) {
         var4 = var6 + "  【  " + var2 + "：" + this.mContext.getString(2131757973) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757974) + "】    ";
         var2 = var1 + 1;
      }

      var6 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[13])) {
         var6 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757975) + "】    ";
         var1 = var2 + 1;
      }

      var4 = var6;
      var3 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[13])) {
         var4 = var6 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757976) + "】    ";
         var3 = var1 + 1;
      }

      var5 = var4;
      var2 = var3;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13])) {
         var5 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131757977) + "】    ";
         var2 = var3 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[13])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757978) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[13])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757979) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[13])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757980) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757981) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var3 = var2;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[14])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757982) + "】    ";
         var3 = var2 + 1;
      }

      var5 = var4;
      var1 = var3;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[14])) {
         var5 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131757983) + "】    ";
         var1 = var3 + 1;
      }

      var6 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[14])) {
         var6 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757984) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var6;
      var1 = var2;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[14])) {
         var4 = var6 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757985) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[14])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757986) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var1 = var2;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[14])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757987) + "】    ";
         var1 = var2 + 1;
      }

      var5 = var4;
      var2 = var1;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[14])) {
         var5 = var4 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757988) + "】    ";
         var2 = var1 + 1;
      }

      var4 = var5;
      var3 = var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])) {
         var4 = var5 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757989) + "】    ";
         var3 = var2 + 1;
      }

      var5 = var4;
      var1 = var3;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[15])) {
         var5 = var4 + "  【  " + var7 + var3 + "：" + this.mContext.getString(2131757990) + "】    ";
         var1 = var3 + 1;
      }

      var4 = var5;
      var2 = var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[15])) {
         var4 = var5 + "  【  " + var7 + var1 + "：" + this.mContext.getString(2131757991) + "】    ";
         var2 = var1 + 1;
      }

      var5 = var4;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[15])) {
         var5 = var4 + "  【  " + var7 + var2 + "：" + this.mContext.getString(2131757992) + "】    ";
      }

      if (!var5.equals("")) {
         this.showDialog(var5);
      }

      int[] var8 = this.mCanBusInfoInt;
      var8[0] = 0;
      var8[1] = 0;
      var8[2] = 0;
      var8[3] = 0;
      var8[4] = 0;
      var8[5] = 0;
      var8[6] = 0;
      var8[7] = 0;
      var8[8] = 0;
      var8[9] = 0;
      var8[10] = 0;
      var8[11] = 0;
      var8[12] = 0;
      var8[13] = 0;
      if (this.isBasicInfoChange2()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[16]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[16]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[16]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[16]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[16]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[16]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setAmplifierInfo0x17(Context var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)));
      var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 10;
      GeneralAmplifierData.frontRear = -(this.mCanBusInfoInt[3] - 10);
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 3;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[6] - 3;
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData(var1, this.getCanId());
   }

   private void setAutoParking0x28() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_settings_0"), 0, this.getParkingBasicState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) + this.getParkingState(this.mCanBusInfoInt[3]))).setValueStr(true));
      if (this.mCanBusInfoInt[2] == 0) {
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_settings_0"), 1, 0));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarFactionState0x39() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 0, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 1, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 2, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 3, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 4, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 5, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 6, this.getCarStateMode(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 7, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 8, this.getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarState0x38() {
      ArrayList var2 = new ArrayList();
      int var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_carState");
      var2.add(new SettingUpdateEntity(var1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var2.add((new SettingUpdateEntity(var1, 1, this.getFuelState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)))).setValueStr(true));
      var2.add(new SettingUpdateEntity(var1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var2.add(new SettingUpdateEntity(var1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var2.add(new SettingUpdateEntity(var1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      var2.add(new SettingUpdateEntity(var1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
      var2.add(new SettingUpdateEntity(var1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 3)));
      var2.add(new SettingUpdateEntity(var1, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var2.add(new SettingUpdateEntity(var1, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var2.add((new SettingUpdateEntity(var1, 9, this.getReversingState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)))).setValueStr(true));
      var2.add((new SettingUpdateEntity(var1, 10, this.getPState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)))).setValueStr(true));
      var2.add((new SettingUpdateEntity(var1, 11, this.getLittleLampState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)))).setValueStr(true));
      var2.add(new SettingUpdateEntity(var1, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)));
      var2.add(new SettingUpdateEntity(var1, 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2)));
      var2.add(new SettingUpdateEntity(var1, 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
      var2.add(new SettingUpdateEntity(var1, 15, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 2)));
      var2.add(new SettingUpdateEntity(var1, 16, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
      var2.add(new SettingUpdateEntity(var1, 17, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
      var2.add(new SettingUpdateEntity(var1, 18, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
      var2.add(new SettingUpdateEntity(var1, 19, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)));
      var2.add(new SettingUpdateEntity(var1, 20, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
      var2.add(new SettingUpdateEntity(var1, 21, this.getLangrage()));
      var2.add(new SettingUpdateEntity(var1, 22, this.getFuelConsumptionUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))));
      var2.add(new SettingUpdateEntity(var1, 23, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2)));
      var2.add(new SettingUpdateEntity(var1, 24, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)));
      var2.add(new SettingUpdateEntity(var1, 25, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1)));
      var2.add(new SettingUpdateEntity(var1, 26, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1)));
      var2.add(new SettingUpdateEntity(var1, 27, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 1)));
      var2.add(new SettingUpdateEntity(var1, 28, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
      var2.add(new SettingUpdateEntity(var1, 29, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2)));
      var2.add(new SettingUpdateEntity(var1, 30, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)));
      var2.add(new SettingUpdateEntity(var1, 31, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1)));
      var2.add(new SettingUpdateEntity(var1, 32, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      int[] var3 = this.mCanBusInfoInt;
      var3[3] = 0;
      var3[4] = 0;
      var3[5] = 0;
      var3[6] = 0;
      var3[7] = 0;
      var3[8] = 0;
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ^ true;
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ^ true;
         this.updateDoorView(this.mContext);
      }

   }

   private void setControlCommand0x01() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_carState"), 33, this.mCanBusInfoInt[4] + "")).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCruiseSpeedLimit0x3D() {
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit4 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit3 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit2 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit1 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeedLimit_Data0Bit0 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeed_Data1 = this.mCanBusInfoInt[2];
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeed_Data2 = this.mCanBusInfoInt[3];
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeed_Data3 = this.mCanBusInfoInt[4];
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeed_Data4 = this.mCanBusInfoInt[5];
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeed_Data5 = this.mCanBusInfoInt[6];
      this.getmUigMgr(this.mContext);
      UiMgr.cruiseSpeed_Data6 = this.mCanBusInfoInt[7];
      this.getmUigMgr(this.mContext);
      UiMgr.speedLimit_Data1 = this.mCanBusInfoInt[8];
      this.getmUigMgr(this.mContext);
      UiMgr.speedLimit_Data2 = this.mCanBusInfoInt[9];
      this.getmUigMgr(this.mContext);
      UiMgr.speedLimit_Data3 = this.mCanBusInfoInt[10];
      this.getmUigMgr(this.mContext);
      UiMgr.speedLimit_Data4 = this.mCanBusInfoInt[11];
      this.getmUigMgr(this.mContext);
      UiMgr.speedLimit_Data5 = this.mCanBusInfoInt[12];
      this.getmUigMgr(this.mContext);
      UiMgr.speedLimit_Data6 = this.mCanBusInfoInt[13];
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 0, this.mCanBusInfoInt[2]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 1, this.mCanBusInfoInt[3]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 2, this.mCanBusInfoInt[4]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 3, this.mCanBusInfoInt[5]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 4, this.mCanBusInfoInt[6]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 5, this.mCanBusInfoInt[7]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 0, this.mCanBusInfoInt[8]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 1, this.mCanBusInfoInt[9]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 2, this.mCanBusInfoInt[10]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 3, this.mCanBusInfoInt[11]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 4, this.mCanBusInfoInt[12]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 5, this.mCanBusInfoInt[13]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDiagnosticInfo0x3A() {
   }

   private void setDiagnosticInfo0x4A() {
      ArrayList var1 = new ArrayList();
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757835)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757836)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757837)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757838)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757839)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757840)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757841)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757842)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757843)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757844)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757845)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757846)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757847)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757848)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757849)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757850)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757851)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757852)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757853)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757854)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757855)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757856)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757857)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757858)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757858)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757860)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757861)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757862)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757863)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757864)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757865)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757866)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757867)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757868)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757869)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757870)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757871)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757872)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757873)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757770)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757874)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757771)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757772)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757875)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757773)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757774)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[11])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757775)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757776)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757777)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757778)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[13])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757779)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[13])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757780)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757781)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[14])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757782)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[14])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757783)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[14])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757784)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[14])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757785)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757786)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757787)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757788)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757789)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757790)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757791)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757792)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757793)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[16])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757794)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[16])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757795)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[16])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757796)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[16])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757797)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[16])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757798)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[17])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757799)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[17])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757800)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[17])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757801)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[17])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757802)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[17])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757803)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[17])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757804)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757876)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757806)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757807)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757808)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757809)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757810)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[18])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757811)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[19])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757812)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[19])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757813)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[19])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757814)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[19])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757816)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[19])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757815)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757817)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757818)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757819)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757820)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757821)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757822)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[20])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757823)));
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757824)));
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757825)));
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757826)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757827)));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757828)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757829)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757830)));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[21])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757831)));
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[22])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757832)));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[22])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757833)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[22])) {
         var1.add(new WarningEntity(this.mContext.getString(2131757834)));
      }

      GeneralWarningDataData.dataList = var1;
      this.updateWarningActivity((Bundle)null);
   }

   private void setEconomicModelInfo0x44() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 3, this.getEconomicModelState(this.mCanBusInfoInt[2]))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setEpsInfo0x29() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5450, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRadarInfo0x32() {
      if (this.isRearRadarDataChange()) {
         if (this.eachId == 14) {
            this.noHaveDistanceInfo();
         } else {
            this.haveDistanceInfo();
         }
      }

   }

   private void setRadarSoundInfo0x3E() {
      ArrayList var1 = new ArrayList();
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), 3, this.mContext.getString(2131763114)));
      } else {
         var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), 3, this.mContext.getString(2131763115)));
      }

      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSpeed0x3B() {
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit7 = 0;
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data0Bit1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data1 = this.mCanBusInfoInt[3];
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data2 = this.mCanBusInfoInt[4];
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data3 = this.mCanBusInfoInt[5];
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data4 = this.mCanBusInfoInt[6];
      this.getmUigMgr(this.mContext);
      UiMgr.speedMemory_data5 = this.mCanBusInfoInt[7];
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 0, this.isShowSpeedTag(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 7, this.mCanBusInfoInt[3]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 8, this.mCanBusInfoInt[4]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 9, this.mCanBusInfoInt[5]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 10, this.mCanBusInfoInt[6]));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 11, this.mCanBusInfoInt[7]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTripComputerInfo0x33() {
      ArrayList var2 = new ArrayList();
      int var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0");
      StringBuilder var3 = new StringBuilder();
      DecimalFormat var5 = this.oneDecimal;
      int[] var4 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 0, var3.append(var5.format((long)(this.getMsbLsbResult(var4[2], var4[3]) / 10))).append(" L/100KM").toString())).setValueStr(true));
      var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0");
      StringBuilder var7 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 1, var7.append(this.getMsbLsbResult(var6[4], var6[5])).append(" KM").toString())).setValueStr(true));
      var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0");
      var7 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 2, var7.append(this.getMsbLsbResult(var6[6], var6[7])).append(" KM").toString())).setValueStr(true));
      if (this.eachId == 3) {
         var2.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0"), 3, this.timeFormat.format((long)this.mCanBusInfoInt[8]) + ":" + this.timeFormat.format((long)this.mCanBusInfoInt[9]) + ":" + this.timeFormat.format((long)this.mCanBusInfoInt[10]))).setValueStr(true));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTripComputerInfo0x34() {
      this.getmUigMgr(this.mContext).ComputerInfoData3Bit7 = 0;
      this.getmUigMgr(this.mContext).ComputerInfoData3Bit6 = 0;
      ArrayList var2 = new ArrayList();
      int var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1");
      StringBuilder var3 = new StringBuilder();
      DecimalFormat var4 = this.oneDecimal;
      int[] var5 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 0, var3.append(var4.format((long)(this.getMsbLsbResult(var5[2], var5[3]) / 10))).append(" L/100Km").toString())).setValueStr(true));
      var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1");
      var3 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 1, var3.append(this.getMsbLsbResult(var7[4], var7[5])).append(" KM/H").toString())).setValueStr(true));
      var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1");
      StringBuilder var8 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 2, var8.append(this.getMsbLsbResult(var6[6], var6[7])).append(" KM").toString())).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTripComputerInfo0x35() {
      this.getmUigMgr(this.mContext).ComputerInfoData3Bit7 = 0;
      this.getmUigMgr(this.mContext).ComputerInfoData3Bit5 = 0;
      ArrayList var2 = new ArrayList();
      int var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2");
      StringBuilder var4 = new StringBuilder();
      DecimalFormat var3 = this.oneDecimal;
      int[] var5 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 0, var4.append(var3.format((long)(this.getMsbLsbResult(var5[2], var5[3]) / 10))).append(" L/100Km").toString())).setValueStr(true));
      var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2");
      var4 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 1, var4.append(this.getMsbLsbResult(var6[4], var6[5])).append(" KM/H").toString())).setValueStr(true));
      var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2");
      StringBuilder var7 = new StringBuilder();
      int[] var8 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 2, var7.append(this.getMsbLsbResult(var8[6], var8[7])).append(" KM").toString())).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo0x71() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 0, this.getCarSeries(this.mCanBusInfoInt[2]))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 1, this.getCarModel(this.mCanBusInfoInt[3]))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 2, this.mCanBusInfoInt[4] + 2000 - 6 + "年" + this.mCanBusInfoInt[5] + "月" + this.mCanBusInfoInt[6] + "日")).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 3, "V" + this.mCanBusInfoInt[8] + "." + this.mCanBusInfoInt[9] + "." + this.mCanBusInfoInt[10])).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setWheelKey0x20() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 2) {
            if (var1 != 48) {
               if (var1 != 80) {
                  if (var1 != 7) {
                     if (var1 != 8) {
                        if (var1 != 129) {
                           if (var1 != 130) {
                              switch (var1) {
                                 case 17:
                                    this.buttonKey(2);
                                    break;
                                 case 18:
                                    this.buttonKey(46);
                                    break;
                                 case 19:
                                    this.buttonKey(45);
                                    break;
                                 case 20:
                                    this.buttonKey(7);
                                    break;
                                 case 21:
                                    this.buttonKey(8);
                                    break;
                                 case 22:
                                    if (this.eachId != 11) {
                                       this.buttonKey(3);
                                    } else {
                                       this.buttonKey(134);
                                    }
                                    break;
                                 case 23:
                                    this.knobKey(21);
                                    break;
                                 case 24:
                                    this.knobKey(20);
                                    break;
                                 case 25:
                                    if (this.eachId == 4) {
                                       this.buttonKey(7);
                                    }
                                    break;
                                 case 26:
                                    if (this.eachId == 4) {
                                       this.buttonKey(8);
                                    }
                                    break;
                                 default:
                                    switch (var1) {
                                       case 31:
                                          this.buttonKey(187);
                                          break;
                                       case 32:
                                          if (var2[3] == 1) {
                                             return;
                                          }

                                          var1 = this.computerInfoTag;
                                          Context var3;
                                          if (var1 == 1) {
                                             var3 = this.mContext;
                                             this.startSettingActivity(var3, this.getmUigMgr(var3).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0"), 0);
                                             this.computerInfoTag = 2;
                                          } else if (var1 == 2) {
                                             var3 = this.mContext;
                                             this.startSettingActivity(var3, this.getmUigMgr(var3).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1"), 0);
                                             this.computerInfoTag = 3;
                                          } else if (var1 == 3) {
                                             var3 = this.mContext;
                                             this.startSettingActivity(var3, this.getmUigMgr(var3).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2"), 0);
                                             this.computerInfoTag = 1;
                                          }
                                          break;
                                       case 33:
                                          this.buttonKey(52);
                                          break;
                                       case 34:
                                          this.buttonKey(31);
                                          break;
                                       case 35:
                                          this.buttonKey(68);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 145:
                                                this.buttonKey(33);
                                                break;
                                             case 146:
                                                this.buttonKey(34);
                                                break;
                                             case 147:
                                                this.buttonKey(35);
                                                break;
                                             case 148:
                                                this.buttonKey(36);
                                                break;
                                             case 149:
                                                this.buttonKey(37);
                                                break;
                                             case 150:
                                                this.buttonKey(38);
                                                break;
                                             case 151:
                                                this.buttonKey(21);
                                                break;
                                             case 152:
                                                this.buttonKey(20);
                                                break;
                                             case 153:
                                                this.buttonKey(62);
                                                break;
                                             case 154:
                                                if (var2[3] == 1) {
                                                   return;
                                                }

                                                this.adjustBrightness();
                                                break;
                                             case 155:
                                                if (this.eachId == 11) {
                                                   this.buttonKey(133);
                                                } else {
                                                   this.buttonKey(151);
                                                }
                                                break;
                                             case 156:
                                                this.buttonKey(45);
                                                break;
                                             case 157:
                                                this.buttonKey(46);
                                                break;
                                             case 158:
                                                this.buttonKey(52);
                                                break;
                                             case 159:
                                                this.buttonKey(185);
                                                break;
                                             case 160:
                                                this.buttonKey(2);
                                                break;
                                             case 161:
                                                this.buttonKey(59);
                                                break;
                                             case 162:
                                                this.buttonKey(49);
                                                break;
                                             case 163:
                                                this.buttonKey(50);
                                                break;
                                             case 164:
                                                this.buttonKey(3);
                                                break;
                                             case 165:
                                                this.updateAirActivity(this.mContext, 1001);
                                                break;
                                             case 166:
                                                this.buttonKey(52);
                                                break;
                                             default:
                                                switch (var1) {
                                                   case 176:
                                                      this.buttonKey(467);
                                                      break;
                                                   case 177:
                                                      this.buttonKey(468);
                                                      break;
                                                   case 178:
                                                      this.buttonKey(50);
                                                      break;
                                                   case 179:
                                                      this.buttonKey(128);
                                                      break;
                                                   case 180:
                                                      this.buttonKey(128);
                                                      break;
                                                   case 181:
                                                      this.buttonKey(62);
                                                      break;
                                                   case 182:
                                                      this.buttonKey(2);
                                                      break;
                                                   case 183:
                                                      this.buttonKey(58);
                                                      break;
                                                   case 184:
                                                      this.buttonKey(128);
                                                      break;
                                                   case 185:
                                                      this.buttonKey(45);
                                                      break;
                                                   case 186:
                                                      this.buttonKey(46);
                                                      break;
                                                   case 187:
                                                      this.buttonKey(21);
                                                      break;
                                                   case 188:
                                                      this.buttonKey(20);
                                                      break;
                                                   case 189:
                                                      this.buttonKey(49);
                                                      break;
                                                   case 190:
                                                      this.buttonKey(7);
                                                      break;
                                                   case 191:
                                                      this.buttonKey(8);
                                                }
                                          }
                                    }
                              }
                           } else {
                              this.buttonKey(8);
                           }
                        } else {
                           this.buttonKey(7);
                        }
                     } else {
                        this.buttonKey(50);
                     }
                  } else {
                     this.buttonKey(49);
                  }
               } else {
                  this.buttonKey(14);
               }
            } else {
               this.buttonKey(2);
            }
         } else {
            this.buttonKey(151);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void showDialog(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$showContent;

         {
            this.this$0 = var1;
            this.val$showContent = var2;
         }

         public void callback() {
            (new AlertView()).showDialog(this.this$0.getActivity(), this.val$showContent);
         }
      });
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      this.getmUigMgr(var1);
      UiMgr.makeConnection();
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext).sendSourceInfo(0, 0, 0, 0, 0, 0, 0, 0);
      }
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext).sendSourceInfo(7, 48, 0, 0, 0, 0, 0, 0);
      }
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getmUigMgr(this.mContext).sendSourceInfo(11, 0, 0, 0, 0, 0, 0, 0);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit4 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit2 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit1 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit0 = 1;
         this.getmUigMgr(this.mContext).sendPhoneStateInfo();
      }
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit4 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit2 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit1 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit0 = 1;
         this.getmUigMgr(this.mContext).sendPhoneStateInfo();
      }
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      if (this.eachId == 4) {
         int[] var4 = this.getByteArrayToIntArray(var1);
         this.getmUigMgr(this.mContext).sendID3Info(var4[0], var4[1], var4[2], var4[3], var4[4], var4[5], var4[6], var4[7], var4[8], var4[9], var4[10]);
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit4 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit2 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit1 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit0 = 0;
         this.getmUigMgr(this.mContext).sendPhoneStateInfo();
      }
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext).sendSourceInfo(5, 64, 0, 0, 0, 0, 0, 0);
         this.sendPhoneStateInfo(var7, var8);
      }
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit4 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit2 = 1;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit1 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData1Bit0 = 0;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData2 = var4 / 3600;
         this.getmUigMgr(this.mContext);
         var4 %= 3600;
         UiMgr.phoneStateData3 = var4 / 60;
         this.getmUigMgr(this.mContext);
         UiMgr.phoneStateData4 = var4 % 60;
         this.getmUigMgr(this.mContext).sendPhoneStateInfo();
      }
   }

   public void buttonKey(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 23) {
            if (var3 != 68) {
               if (var3 != 74) {
                  if (var3 != 113) {
                     if (var3 != 234) {
                        if (var3 != 32) {
                           if (var3 != 33) {
                              if (var3 != 40) {
                                 if (var3 != 41) {
                                    if (var3 != 61) {
                                       if (var3 != 62) {
                                          switch (var3) {
                                             case 50:
                                                this.setRadarInfo0x32();
                                                break;
                                             case 51:
                                                this.setTripComputerInfo0x33();
                                                break;
                                             case 52:
                                                this.setTripComputerInfo0x34();
                                                break;
                                             case 53:
                                                this.setTripComputerInfo0x35();
                                                break;
                                             case 54:
                                                this.updateOutDoorTemp(this.mContext, this.getOutdoorTemperature());
                                                break;
                                             case 55:
                                                this.setAlertInfo0x37();
                                                break;
                                             case 56:
                                                this.setCarState0x38();
                                                break;
                                             case 57:
                                                this.setCarFactionState0x39();
                                                break;
                                             case 58:
                                                this.setDiagnosticInfo0x3A();
                                                break;
                                             case 59:
                                                this.setSpeed0x3B();
                                          }
                                       } else {
                                          this.setRadarSoundInfo0x3E();
                                       }
                                    } else {
                                       this.setCruiseSpeedLimit0x3D();
                                    }
                                 } else {
                                    this.setEpsInfo0x29();
                                 }
                              } else {
                                 this.setAutoParking0x28();
                              }
                           } else {
                              this.setAirInfo0x21();
                           }
                        } else {
                           this.setWheelKey0x20();
                        }
                     } else {
                        this.setAccInfo0xEA();
                     }
                  } else {
                     this.setVersionInfo0x71();
                  }
               } else {
                  this.setDiagnosticInfo0x4A();
               }
            } else {
               this.setEconomicModelInfo0x44();
            }
         } else {
            this.setAmplifierInfo0x17(var1);
         }
      } else {
         this.setControlCommand0x01();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      if (this.eachId == 4) {
         if (var1 <= 30) {
            if (var2) {
               this.volInfoData0 = 128;
            } else {
               this.volInfoData0 = var1;
            }

            this.getmUigMgr(this.mContext).sendVolInfo(this.volInfoData0);
         }
      }
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext).sendSourceInfo(2, 16, var5, var6, 0, 0, 0, 0);
      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      if (this.eachId == 4) {
         this.getmUigMgr(this.mContext).sendSourceInfo(0, 0, 0, 0, 0, 0, 0, 0);
      }
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.eachId == 4) {
         UiMgr var25 = this.getmUigMgr(this.mContext);
         var3 = var17 + 1;
         var25.sendSourceInfo(8, 19, this.getLsb(var3), this.getMsb(var3), this.getLsb(var4), this.getMsb(var4), 0, 0);
         int[] var26;
         if (var21 != null) {
            var26 = this.getByteArrayToIntArray(var21.getBytes(StandardCharsets.UTF_8));
            var3 = var26.length;
            MyLog.e("标题:" + var21, "长度:" + var3 + "");
            if (var3 > 0) {
               this.data0xCB1 = var26[0];
            } else {
               this.data0xCB1 = 32;
            }

            if (var3 > 1) {
               this.data0xCB2 = var26[1];
            } else {
               this.data0xCB2 = 32;
            }

            if (var3 > 2) {
               this.data0xCB3 = var26[2];
            } else {
               this.data0xCB3 = 32;
            }

            if (var3 > 3) {
               this.data0xCB4 = var26[3];
            } else {
               this.data0xCB4 = 32;
            }

            if (var3 > 4) {
               this.data0xCB5 = var26[4];
            } else {
               this.data0xCB5 = 32;
            }

            if (var3 > 5) {
               this.data0xCB6 = var26[5];
            } else {
               this.data0xCB6 = 32;
            }

            if (var3 > 6) {
               this.data0xCB7 = var26[6];
            } else {
               this.data0xCB7 = 32;
            }

            if (var3 > 7) {
               this.data0xCB8 = var26[7];
            } else {
               this.data0xCB8 = 32;
            }

            if (var3 > 8) {
               this.data0xCB9 = var26[8];
            } else {
               this.data0xCB9 = 32;
            }

            if (var3 > 9) {
               this.data0xCB10 = var26[9];
            } else {
               this.data0xCB10 = 32;
            }

            if (var3 > 10) {
               this.data0xCB11 = var26[10];
            } else {
               this.data0xCB11 = 32;
            }

            if (var3 > 11) {
               this.data0xCB12 = var26[11];
            } else {
               this.data0xCB12 = 32;
            }

            if (var3 > 12) {
               this.data0xCB13 = var26[12];
            } else {
               this.data0xCB13 = 32;
            }

            if (var3 > 13) {
               this.data0xCB14 = var26[13];
            } else {
               this.data0xCB14 = 32;
            }

            if (var3 > 14) {
               this.data0xCB15 = var26[14];
            } else {
               this.data0xCB15 = 32;
            }

            if (var3 > 15) {
               this.data0xCB16 = var26[15];
            } else {
               this.data0xCB16 = 32;
            }

            if (var3 > 16) {
               this.data0xCB17 = var26[16];
            } else {
               this.data0xCB17 = 32;
            }

            if (var3 > 17) {
               this.data0xCB18 = var26[17];
            } else {
               this.data0xCB18 = 32;
            }

            if (var3 > 18) {
               this.data0xCB19 = var26[18];
            } else {
               this.data0xCB19 = 32;
            }

            if (var3 > 19) {
               this.data0xCB20 = var26[19];
            } else {
               this.data0xCB20 = 32;
            }

            this.getmUigMgr(this.mContext).sendID3Info(2, this.data0xCB1, this.data0xCB2, this.data0xCB3, this.data0xCB4, this.data0xCB5, this.data0xCB6, this.data0xCB7, this.data0xCB8, this.data0xCB9, this.data0xCB10, this.data0xCB11, this.data0xCB12, this.data0xCB13, this.data0xCB14, this.data0xCB15, this.data0xCB16, this.data0xCB17, this.data0xCB18, this.data0xCB19, this.data0xCB20);
         }

         if (var23 != null) {
            var26 = this.getByteArrayToIntArray(var23.getBytes(StandardCharsets.UTF_8));
            var3 = var26.length;
            MyLog.e("歌手:" + var23, "长度:" + var3);
            if (var3 > 0) {
               this.data0xCB1 = var26[0];
            } else {
               this.data0xCB1 = 32;
            }

            if (var3 > 1) {
               this.data0xCB2 = var26[1];
            } else {
               this.data0xCB2 = 32;
            }

            if (var3 > 2) {
               this.data0xCB3 = var26[2];
            } else {
               this.data0xCB3 = 32;
            }

            if (var3 > 3) {
               this.data0xCB4 = var26[3];
            } else {
               this.data0xCB4 = 32;
            }

            if (var3 > 4) {
               this.data0xCB5 = var26[4];
            } else {
               this.data0xCB5 = 32;
            }

            if (var3 > 5) {
               this.data0xCB6 = var26[5];
            } else {
               this.data0xCB6 = 32;
            }

            if (var3 > 6) {
               this.data0xCB7 = var26[6];
            } else {
               this.data0xCB7 = 32;
            }

            if (var3 > 7) {
               this.data0xCB8 = var26[7];
            } else {
               this.data0xCB8 = 32;
            }

            if (var3 > 8) {
               this.data0xCB9 = var26[8];
            } else {
               this.data0xCB9 = 32;
            }

            if (var3 > 9) {
               this.data0xCB10 = var26[9];
            } else {
               this.data0xCB10 = 32;
            }

            if (var3 > 10) {
               this.data0xCB11 = var26[10];
            } else {
               this.data0xCB11 = 32;
            }

            if (var3 > 11) {
               this.data0xCB12 = var26[11];
            } else {
               this.data0xCB12 = 32;
            }

            if (var3 > 12) {
               this.data0xCB13 = var26[12];
            } else {
               this.data0xCB13 = 32;
            }

            if (var3 > 13) {
               this.data0xCB14 = var26[13];
            } else {
               this.data0xCB14 = 32;
            }

            if (var3 > 14) {
               this.data0xCB15 = var26[14];
            } else {
               this.data0xCB15 = 32;
            }

            if (var3 > 15) {
               this.data0xCB16 = var26[15];
            } else {
               this.data0xCB16 = 32;
            }

            if (var3 > 16) {
               this.data0xCB17 = var26[16];
            } else {
               this.data0xCB17 = 32;
            }

            if (var3 > 17) {
               this.data0xCB18 = var26[17];
            } else {
               this.data0xCB18 = 32;
            }

            if (var3 > 18) {
               this.data0xCB19 = var26[18];
            } else {
               this.data0xCB19 = 32;
            }

            if (var3 > 19) {
               this.data0xCB20 = var26[19];
            } else {
               this.data0xCB20 = 32;
            }

            this.getmUigMgr(this.mContext).sendID3Info(4, this.data0xCB1, this.data0xCB2, this.data0xCB3, this.data0xCB4, this.data0xCB5, this.data0xCB6, this.data0xCB7, this.data0xCB8, this.data0xCB9, this.data0xCB10, this.data0xCB11, this.data0xCB12, this.data0xCB13, this.data0xCB14, this.data0xCB15, this.data0xCB16, this.data0xCB17, this.data0xCB18, this.data0xCB19, this.data0xCB20);
         }
      }
   }

   public void panoramicSwitch(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.eachId == 4) {
         if (var2.trim().equals("FM1")) {
            this.radioData0 = 1;
         } else if (var2.trim().equals("FM2")) {
            this.radioData0 = 2;
         } else if (var2.trim().equals("FMAST")) {
            this.radioData0 = 3;
         } else if (var2.trim().equals("AM1") || var2.trim().equals("AM2")) {
            this.radioData0 = 4;
         }

         this.radioData1 = this.getLsb(Integer.parseInt(var3));
         this.radioData2 = this.getMsb(Integer.parseInt(var3));
         this.radioData3 = 0;
         this.getmUigMgr(this.mContext).sendRadioInfo(this.radioData0, this.radioData1, this.radioData2, this.radioData3);
         this.getmUigMgr(this.mContext).sendSourceInfo(1, 0, 0, 0, 0, 0, 0, 0);
      }
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, int var4) {
      SharePreUtil.setIntValue(var1, "EachCanId" + this.getCurrentEachCanId() + "L" + var2 + "R" + var3, var4);
      ArrayList var5 = new ArrayList();
      var5.add(new SettingUpdateEntity(var2, var3, var4));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }
}
