package com.hzbhd.canbus.car._237;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
   static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
   public static String UPDATE_SETTING_ACTION;
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   private BroadcastReceiver mBroadcastReceiver;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   int nowBandType = 0;
   int presetFreq1 = 0;
   int presetFreq2 = 0;
   int presetFreq3 = 0;
   int presetFreq4 = 0;
   int presetFreq5 = 0;
   int presetFreq6 = 0;
   private UiMgr uiMgr;

   private byte getFreqByteHi(String var1, String var2) {
      byte var3;
      int var4;
      if (this.isBandAm(var1)) {
         var4 = Integer.parseInt(var2);
      } else {
         if (!this.isBandFm(var1)) {
            var3 = 0;
            return var3;
         }

         var4 = (int)(Double.parseDouble(var2) * 100.0);
      }

      var3 = (byte)(var4 >> 8);
      return var3;
   }

   private byte getFreqByteHi2(String var1, String var2) {
      byte var3;
      int var4;
      if (this.isBandAm(var1)) {
         var4 = Integer.parseInt(var2);
      } else {
         if (!this.isBandFm(var1)) {
            var3 = 0;
            return var3;
         }

         var4 = (int)Double.parseDouble(var2);
      }

      var3 = (byte)(var4 >> 8);
      return var3;
   }

   private byte getFreqByteLo(String var1, String var2) {
      byte var3;
      int var4;
      if (this.isBandAm(var1)) {
         var4 = Integer.parseInt(var2);
      } else {
         if (!this.isBandFm(var1)) {
            var3 = 0;
            return var3;
         }

         var4 = (int)(Double.parseDouble(var2) * 100.0);
      }

      var3 = (byte)(var4 & 255);
      return var3;
   }

   private boolean getIsUseFunit() {
      int var1 = Integer.parseInt(SharePreUtil.getStringValue(this.mContext, "share_pre_is_use_f_unit", "0"));
      MyLog.temporaryTracking("单位状态" + var1);
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
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

   private String getTemperature(int var1) {
      String var2;
      if (var1 >= 0 && var1 <= 194) {
         StringBuilder var3 = new StringBuilder();
         var1 -= 40;
         var2 = var3.append(var1).append(this.getTempUnitC(this.mContext)).toString();
         if (this.getIsUseFunit()) {
            var2 = this.df_2Decimal.format((double)var1 * 1.8 + 32.0) + this.getTempUnitF(this.mContext);
         }
      } else if (var1 != 195 && var1 != 255) {
         var2 = this.mContext.getString(2131769395);
      } else {
         var2 = this.mContext.getString(2131769909);
      }

      return var2;
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, String var4) {
      boolean var6 = TextUtils.isEmpty(var2);
      byte var5 = 0;
      String[] var7;
      if (var6) {
         var7 = new String[]{"", var3, var4};
      } else {
         var7 = new String[]{var2, var3, var4};
         var5 = 1;
      }

      return new TireUpdateEntity(var1, var5, var7);
   }

   private String getTirePressure(int var1) {
      String var2;
      if (var1 == 255) {
         var2 = this.mContext.getString(2131769909);
      } else {
         var2 = String.valueOf(Math.floor((double)var1 * 1.37));
      }

      return var2 + this.mContext.getString(2131769654);
   }

   private String getTisWarmMsg(int var1) {
      if (var1 != 2) {
         if (var1 != 4) {
            if (var1 != 6) {
               if (var1 != 8) {
                  if (var1 != 10) {
                     if (var1 != 12) {
                        var1 = 0;
                     } else {
                        var1 = 2131770259;
                     }
                  } else {
                     var1 = 2131769901;
                  }
               } else {
                  var1 = 2131769902;
               }
            } else {
               var1 = 2131767753;
            }
         } else {
            var1 = 2131769160;
         }
      } else {
         var1 = 2131768720;
      }

      return var1 == 0 ? "" : this.mContext.getString(var1);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (36 == var1) {
         var2 = "LO";
      } else if (64 == var1) {
         var2 = "HI";
      } else if (255 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (37 <= var1 && var1 <= 63) {
         if (this.getIsUseFunit()) {
            var2 = this.tempCToTempF((double)(var1 - 1) * 0.5 + 0.5) + this.getTempUnitF(this.mContext);
         } else {
            var2 = (double)(var1 - 1) * 0.5 + 0.5 + this.getTempUnitC(this.mContext);
         }
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      String var2;
      if (this.getIsUseFunit()) {
         var2 = this.df_2Decimal.format((double)var1 * 1.8 + 32.0) + this.getTempUnitF(this.mContext);
      } else {
         var2 = var1 + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveTempLevel(int var1) {
      String var3 = "";
      String var2;
      if (1 == var1) {
         var2 = "LO";
      } else if (15 == var1) {
         var2 = "HI";
      } else {
         var2 = var3;
         if (2 <= var1) {
            var2 = var3;
            if (var1 <= 14) {
               var2 = var1 + "";
            }
         }
      }

      return var2;
   }

   private void set0x20WheelKeyInfo() {
      int[] var1 = this.mCanBusInfoInt;
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 20, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 21, var1[3]);
         case 5:
         case 8:
         default:
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 10:
            if (this.getCurrentCanDifferentId() == 0) {
               this.realKeyLongClick1(this.mContext, 187, this.mCanBusInfoInt[3]);
            } else {
               this.realKeyLongClick1(this.mContext, 15, this.mCanBusInfoInt[3]);
            }
      }

   }

   private void set0x21WheelKeyInfo() {
      int[] var1 = this.mCanBusInfoInt;
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 21, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 20, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 59, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 1, var1[3]);
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 151, var1[3]);
            break;
         case 10:
            this.realKeyLongClick1(this.mContext, 50, var1[3]);
            break;
         case 11:
            this.realKeyLongClick1(this.mContext, 49, var1[3]);
            break;
         case 12:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 128, var1[3]);
            break;
         case 14:
            this.realKeyLongClick1(this.mContext, 4112, var1[3]);
         case 15:
         case 16:
         default:
            break;
         case 17:
            this.realKeyLongClick1(this.mContext, 33, var1[3]);
            break;
         case 18:
            this.realKeyLongClick1(this.mContext, 34, var1[3]);
            break;
         case 19:
            this.realKeyLongClick1(this.mContext, 35, var1[3]);
            break;
         case 20:
            this.realKeyLongClick1(this.mContext, 36, var1[3]);
            break;
         case 21:
            this.realKeyLongClick1(this.mContext, 37, var1[3]);
            break;
         case 22:
            this.realKeyLongClick1(this.mContext, 38, var1[3]);
      }

   }

   private void set0x29TrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 5570, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setAirData0x24() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 7));
      } else {
         GeneralAirData.front_left_temperature = this.resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7));
      } else {
         GeneralAirData.front_right_temperature = this.resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
      }

      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setAirData0x60() {
      GeneralTireData.isNoTirePressureInfo = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ^ true;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         ArrayList var1 = new ArrayList();
         var1.add(this.getTireEntity(0, this.getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4)), this.getTirePressure(this.mCanBusInfoInt[5]), this.getTemperature(this.mCanBusInfoInt[9])));
         var1.add(this.getTireEntity(1, this.getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)), this.getTirePressure(this.mCanBusInfoInt[6]), this.getTemperature(this.mCanBusInfoInt[10])));
         var1.add(this.getTireEntity(2, this.getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)), this.getTirePressure(this.mCanBusInfoInt[7]), this.getTemperature(this.mCanBusInfoInt[11])));
         var1.add(this.getTireEntity(3, this.getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)), this.getTirePressure(this.mCanBusInfoInt[8]), this.getTemperature(this.mCanBusInfoInt[12])));
         GeneralTireData.dataList = var1;
      }

      this.updateTirePressureActivity((Bundle)null);
   }

   private void setDoorData0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadar0x23() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_front_radar_enable", true)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      } else {
         RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
      }

      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadar0x22() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_rear_radar_enable", true)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      } else {
         RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
      }

      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingData() {
      byte var1 = this.getIsUseFunit();
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, var1 ^ 1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private double tempCToTempF(double var1) {
      LogUtil.showLog("tempCToTempF:" + var1);

      try {
         DecimalFormat var3 = new DecimalFormat("0.0");
         var1 = Double.valueOf(var3.format(var1 * 1.8 + 32.0));
         return var1;
      } catch (Exception var4) {
         LogUtil.showLog("Exception:" + var4);
         return 0.0;
      }
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, 7}, 16));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, 4}, 16));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, 9}, 16));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 40) {
         if (var3 != 41) {
            if (var3 != 54) {
               if (var3 != 96) {
                  if (var3 != 127) {
                     switch (var3) {
                        case 32:
                           this.set0x20WheelKeyInfo();
                           break;
                        case 33:
                           this.set0x21WheelKeyInfo();
                           break;
                        case 34:
                           this.setRearRadar0x22();
                           break;
                        case 35:
                           this.setFrontRadar0x23();
                           break;
                        case 36:
                           if (this.isAirMsgRepeat(var2)) {
                              return;
                           }

                           this.setAirData0x24();
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.setAirData0x60();
               }
            } else {
               this.setAirData0x36();
            }
         } else {
            this.set0x29TrackInfo();
         }
      } else {
         if (this.isDoorMsgRepeat(var2)) {
            return;
         }

         this.setDoorData0x28();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      int var3;
      if (var1 >= 30) {
         var3 = 40;
      } else {
         var3 = var1;
      }

      if (var1 < 0) {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var3});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
      BroadcastReceiver var2 = new BroadcastReceiver(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onReceive(Context var1, Intent var2) {
            this.this$0.setSettingData();
         }
      };
      this.mBroadcastReceiver = var2;
      var1.registerReceiver(var2, new IntentFilter(UPDATE_SETTING_ACTION));
   }

   public boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == 404 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == 404;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 3;
      } else {
         var25 = 2;
      }

      byte[] var26 = DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, (byte)var25, var9, (byte)var3}, 16);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      int var8 = var2.hashCode();
      byte var9 = -1;
      switch (var8) {
         case 64901:
            if (var2.equals("AM1")) {
               var9 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var9 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var9 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var9 = 3;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var9 = 4;
            }
      }

      switch (var9) {
         case 0:
         case 1:
            var9 = 1;
            break;
         case 2:
         case 3:
         case 4:
         default:
            var9 = 0;
      }

      if (this.nowBandType != var9) {
         this.nowBandType = var9;
         this.presetFreq1 = 0;
         this.presetFreq2 = 0;
         this.presetFreq3 = 0;
         this.presetFreq4 = 0;
         this.presetFreq5 = 0;
         this.presetFreq6 = 0;
      }

      double var6;
      if (var9 == 0) {
         var6 = Double.parseDouble(var3) * 100.0;
      } else {
         var6 = Double.parseDouble(var3);
      }

      var8 = (int)var6;
      if (var1 == 1) {
         this.presetFreq1 = var8;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(var8), (byte)this.getLsb(this.presetFreq1), (byte)this.getMsb(this.presetFreq2), (byte)this.getLsb(this.presetFreq2), (byte)this.getMsb(this.presetFreq3), (byte)this.getLsb(this.presetFreq3), (byte)this.getMsb(this.presetFreq4), (byte)this.getLsb(this.presetFreq4), 0, (byte)var1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      } else if (var1 == 2) {
         this.presetFreq2 = var8;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(var8), (byte)this.getLsb(this.presetFreq2), (byte)this.getMsb(this.presetFreq3), (byte)this.getLsb(this.presetFreq3), (byte)this.getMsb(this.presetFreq4), (byte)this.getLsb(this.presetFreq4), (byte)this.getMsb(this.presetFreq5), (byte)this.getLsb(this.presetFreq5), 0, (byte)var1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      } else if (var1 == 3) {
         this.presetFreq3 = var8;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(var8), (byte)this.getLsb(this.presetFreq3), (byte)this.getMsb(this.presetFreq4), (byte)this.getLsb(this.presetFreq4), (byte)this.getMsb(this.presetFreq5), (byte)this.getLsb(this.presetFreq5), (byte)this.getMsb(this.presetFreq6), (byte)this.getLsb(this.presetFreq6), 0, (byte)var1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      } else if (var1 == 4) {
         this.presetFreq4 = var8;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(var8), (byte)this.getLsb(this.presetFreq4), (byte)this.getMsb(this.presetFreq5), (byte)this.getLsb(this.presetFreq5), (byte)this.getMsb(this.presetFreq6), (byte)this.getLsb(this.presetFreq6), 0, 0, 0, (byte)var1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      } else if (var1 == 5) {
         this.presetFreq5 = var8;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(var8), (byte)this.getLsb(this.presetFreq5), (byte)this.getMsb(this.presetFreq6), (byte)this.getLsb(this.presetFreq6), 0, 0, 0, 0, 0, (byte)var1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      } else if (var1 == 6) {
         this.presetFreq6 = var8;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(var8), (byte)this.getLsb(this.presetFreq6), 0, 0, 0, 0, 0, 0, 0, (byte)var1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var9, (byte)this.getMsb(this.presetFreq2), (byte)this.getLsb(this.presetFreq2), (byte)this.getMsb(this.presetFreq3), (byte)this.getLsb(this.presetFreq3), (byte)this.getMsb(this.presetFreq4), (byte)this.getLsb(this.presetFreq4), (byte)this.getMsb(this.presetFreq5), (byte)this.getLsb(this.presetFreq5), 0, 1, (byte)this.getMsb(var8), (byte)this.getLsb(var8), 0});
      }

   }

   public void showDialogAndRestartApp(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            (new SetAlertView()).showDialog(this.this$0.getActivity(), this.val$content);
            (new CountDownTimer(this, 3000L, 1000L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  System.exit(0);
               }

               public void onTick(long var1) {
               }
            }).start();
         }
      });
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 3;
      } else {
         var18 = 2;
      }

      byte[] var19 = DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, (byte)var18, var9, (byte)var3}, 16);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
   }
}
