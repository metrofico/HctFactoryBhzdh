package com.hzbhd.canbus.car._370;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
   static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
   static final int _1111_AMPLIFIER_BAND_MAX = 1;
   static final int _1111_AMPLIFIER_HALF_MAX = 10;
   private int differentId;
   private int eachId;
   private int[] m0xAEData;
   private int mAmplifierSwitch;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private TimerUtil mTimerUtil = new TimerUtil();
   int rdm;
   int rpt;
   private UiMgr uiMgr;

   private String getBandUnit(String var1) {
      if (var1.contains("FM")) {
         return " MKz";
      } else {
         return var1.contains("AM") ? " KKz" : " MKz";
      }
   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private String getRunStatus(int var1) {
      String var2 = "_370_divice_status_" + var1;
      return CommUtil.getStrByResId(this.mContext, var2);
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private int getSwapVal(int var1) {
      byte var3 = 0;
      byte var2 = var3;
      if (var1 >= 1) {
         var2 = var3;
         if (var1 == 10) {
            var2 = 1;
         }
      }

      return var2;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void initAmplifier(Context var1) {
      AmplifierPageUiSet var6 = this.getUiMgr(var1).getAmplifierPageUiSet(var1);
      this.getAmplifierData(var1, this.mCanId, var6);
      byte[] var7 = new byte[]{22, -124, 2, (byte)GeneralAmplifierData.volume};
      byte[] var9 = new byte[]{22, -124, 3, (byte)(10 - GeneralAmplifierData.frontRear)};
      byte var3 = (byte)(GeneralAmplifierData.leftRight + 10);
      byte var5 = (byte)(GeneralAmplifierData.bandBass + 1);
      byte var4 = (byte)(GeneralAmplifierData.bandTreble + 1);
      byte var2 = (byte)(GeneralAmplifierData.bandMiddle + 1);
      TimerUtil var8 = new TimerUtil();
      var8.startTimer(new TimerTask(this, new byte[][]{var7, var9, {22, -124, 4, var3}, {22, -124, 5, var5}, {22, -124, 6, var4}, {22, -124, 7, var2}}, var8) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$array;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$array = var2;
            this.val$timerUtil = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$array;
            if (var1 < var2.length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 100L, 133L);
      this.mAmplifierSwitch = 1;
      this.mTimerUtil.stopTimer();
      this.mTimerUtil.startTimer(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)this.this$0.mAmplifierSwitch});
         }
      }, 100L, 1000L);
   }

   private boolean is0xAEDataChange() {
      if (Arrays.equals(this.m0xAEData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xAEData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolveBackLight(int var1) {
      if (var1 == 0) {
         return "Min";
      } else {
         return var1 == 167 ? "Max" : var1 + "";
      }
   }

   private String resolveBrakeService(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131764401);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131764402);
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131764403);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131764404);
      } else if (var1 == 4) {
         var2 = this.mContext.getResources().getString(2131764405);
      } else if (var1 == 5) {
         var2 = this.mContext.getResources().getString(2131764406);
      } else if (var1 == 6) {
         var2 = this.mContext.getResources().getString(2131764407);
      } else if (var1 == 7) {
         var2 = this.mContext.getResources().getString(2131764408);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveRotating0x50(int var1, int var2) {
      return var1 + var2 * 256 + "rmp";
   }

   private String resolveSpeed0x50(int var1, int var2) {
      return (var1 + var2 * 256) / 16 + "km/h";
   }

   private String resolveTime(int var1, int var2, int var3, int var4, int var5, int var6) {
      int[] var8 = new int[]{var1, var2, var3, var4, var5, var6};
      String[] var7 = new String[]{"", "", "", "", "", ""};
      if (DataHandleUtils.getBoolBit7(var4)) {
         var1 = var8[3];
         if (var1 > 23) {
            var8[3] = var1 - 128;
         }
      }

      for(var1 = 0; var1 < 6; ++var1) {
         if (var8[var1] < 10) {
            var7[var1] = "0" + var8[var1];
         } else {
            var7[var1] = var8[var1] + "";
         }
      }

      return "20" + var7[0] + "-" + var7[1] + "-" + var7[2] + "-" + var7[3] + "-" + var7[4] + "-" + var7[5];
   }

   private String resolveTurnLight(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769288);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131769289);
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131769290);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131769291);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolvetemp(int var1, boolean var2, boolean var3, int var4) {
      String var5;
      if (!var3) {
         if (var1 == 0) {
            var5 = "LOW";
            return var5;
         }

         if (var1 == 255) {
            var5 = "HIGH";
            return var5;
         }

         if (!var2) {
            var5 = (float)var1 / 2.0F + this.getTempUnitC(this.mContext);
            return var5;
         }

         if (var2) {
            var5 = var1 + this.getTempUnitF(this.mContext);
            return var5;
         }
      } else {
         if (var4 == 0 || var4 == 3) {
            var5 = this.mContext.getResources().getString(2131764399);
            return var5;
         }

         if (var4 == 1) {
            var5 = this.mContext.getResources().getString(2131764380);
            return var5;
         }

         if (var4 == 2) {
            var5 = this.mContext.getResources().getString(2131764388);
            return var5;
         }
      }

      var5 = "";
      return var5;
   }

   private int resovleDelay(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 30) {
         return 1;
      } else if (var1 == 60) {
         return 2;
      } else {
         return var1 == 90 ? 3 : 0;
      }
   }

   private int resovleDelay2(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 3) {
         return 1;
      } else if (var1 == 20) {
         return 2;
      } else {
         return var1 == 40 ? 3 : 0;
      }
   }

   private void sendtype() {
      if (this.differentId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 1});
      }

      if (this.differentId == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0});
      }

   }

   private void setAirInfo0x21() {
      int[] var7 = this.mCanBusInfoInt;
      int var2 = var7[2];
      int var3 = var7[3];
      int var6 = var7[4];
      int var4 = var7[5];
      int var1 = var7[6];
      int var5 = var7[7];
      GeneralAirData.power = DataHandleUtils.getBoolBit7(var2);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(var2);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var2) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(var2);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(var2);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(var3);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(var3);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(var3);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(var3);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var3);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var3);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var3, 0, 4);
      GeneralAirData.front_left_temperature = this.resolvetemp(var6, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      GeneralAirData.front_right_temperature = this.resolvetemp(var4, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(var1);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var1);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var1);
      GeneralAirData.eco = DataHandleUtils.getBoolBit4(var1);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(var1);
      GeneralAirData.rear = DataHandleUtils.getBoolBit2(var1);
      GeneralAirData.auto_manual = DataHandleUtils.getBoolBit1(var1) ^ true;
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(var1);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var5, 4, 3);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var5, 0, 3);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAmplifier0x17() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
      GeneralAmplifierData.frontRear = -this.mCanBusInfoInt[4] + 10;
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      GeneralAmplifierData.leftRight = -(-var2[5] + 10);
      var2 = this.mCanBusInfoInt;
      var1 = var2[5];
      GeneralAmplifierData.bandBass = var2[6] - 1;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 1;
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[8] - 1;
      this.updateAmplifierActivity((Bundle)null);
   }

   private void setBacklightInfo0x14() {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_BackLight"), this.resolveBackLight(this.mCanBusInfoInt[2])));
      int var1 = this.mCanBusInfoInt[2];
      if (var1 > 0 && var1 <= 33) {
         this.setBacklightLevel(1);
      } else if (var1 > 33 && var1 <= 66) {
         this.setBacklightLevel(2);
      } else if (var1 > 66 && var1 <= 100) {
         this.setBacklightLevel(3);
      } else if (var1 > 100 && var1 <= 133) {
         this.setBacklightLevel(4);
      } else if (var1 > 133 && var1 <= 167) {
         this.setBacklightLevel(5);
      }

      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCDPlayInfo0x43() {
      if (this.is0xAEDataChange()) {
         GeneralOriginalCarDeviceData.cdStatus = "CD";
         DecimalFormat var5 = new DecimalFormat("00");
         int[] var6 = this.mCanBusInfoInt;
         int var3 = var6[7];
         int var4 = var6[8];
         int var1 = var6[9];
         GeneralOriginalCarDeviceData.startTime = var5.format((long)this.mCanBusInfoInt[7]) + ":" + var5.format((long)this.mCanBusInfoInt[8]) + ":" + var5.format((long)this.mCanBusInfoInt[9]);
         var6 = this.mCanBusInfoInt;
         int var2 = var6[4] * 3600 + var6[5] * 60 + var6[6];
         GeneralOriginalCarDeviceData.endTime = var5.format((long)this.mCanBusInfoInt[4]) + ":" + var5.format((long)this.mCanBusInfoInt[5]) + ":" + var5.format((long)this.mCanBusInfoInt[6]);
         if (var2 == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
         } else {
            GeneralOriginalCarDeviceData.progress = (var3 * 3600 + var4 * 60 + var1) * 100 / var2;
         }

         ArrayList var8 = new ArrayList();
         StringBuilder var7 = new StringBuilder();
         var6 = this.mCanBusInfoInt;
         var8.add(new OriginalCarDeviceUpdateEntity(0, var7.append(var6[10] * 256 + var6[11]).append("").toString()));
         StringBuilder var9 = new StringBuilder();
         int[] var10 = this.mCanBusInfoInt;
         var8.add(new OriginalCarDeviceUpdateEntity(1, var9.append(var10[2] * 256 + var10[3]).append("").toString()));
         GeneralOriginalCarDeviceData.mList = var8;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setCDstatus0x42() {
      if (this.is0xAEDataChange()) {
         GeneralOriginalCarDeviceData.cdStatus = "CD";
         GeneralOriginalCarDeviceData.runningState = this.getRunStatus(this.mCanBusInfoInt[2]);
         if (this.mCanBusInfoInt[2] == 5 && GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
         }

         GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            this.rpt = 1;
         } else {
            this.rpt = 0;
         }

         GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            this.rdm = 1;
         } else {
            this.rdm = 0;
         }

         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setCarbodytime0x26() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_Time");
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, this.resolveTime(var4[2], var4[3], var4[4], var4[5], var4[6], var4[7])));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCenterControlInfo0x40() {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 32) {
               if (var1 != 33) {
                  if (var1 != 48) {
                     if (var1 != 64) {
                        if (var1 != 144) {
                           if (var1 != 160) {
                              if (var1 != 176) {
                                 if (var1 != 192) {
                                    if (var1 == 208) {
                                       var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Sound_Settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Sound_Settings", "_370_Speed_adjustment_volume"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
                                       var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Sound_Settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Sound_Settings", "_370_Surround_Sound"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
                                    }
                                 } else {
                                    var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Brake"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Brake", "_370_Automatic_parking_brake"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
                                    var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_Parking_service"), this.resolveBrakeService(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 3))));
                                 }
                              } else {
                                 var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "compass"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "compass", "compass_direction"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
                                 var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "compass"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "compass", "compass_run_calibration"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
                                 var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "compass"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "compass", "compass_deviation"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1));
                              }
                           } else {
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_76"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data5_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data5_10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data5_32"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_32"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_54"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
                              var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
                           }
                        } else {
                           var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Auto_Open_Comfortable_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Auto_Open_Comfortable_System", "_370_Start_Open_Heat"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
                           var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Auto_Open_Comfortable_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Auto_Open_Comfortable_System", "_370_Remote_Start_Reminder"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
                        }
                     } else {
                        var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Engine_off"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Engine_off", "_370_Engine_Off_Light_delay"), this.resovleDelay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 8))));
                        var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Engine_off"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Engine_off", "_370_Engine_Off_Power_delay"), this.resovleDelay2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8))));
                        var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Engine_off"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Engine_off", "_370_Driver_Seat_Convenient"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
                     }
                  } else {
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x62_data3_4_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_65"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "_370_Remote_Start"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "_370_Remote_unlock"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1)));
                  }
               } else {
                  var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_765"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
               }
            } else {
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data2_10"), this.resovleDelay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7))));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data2_32"), this.resovleDelay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7))));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data1_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "_112_rear_mirror_dimmer"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data2_76"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 2)));
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
            }
         } else {
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Measure"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Fuel_consumption"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2)));
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Mileage"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Temp_Unit"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Air_Pressure"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
         }
      } else {
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), this.getSwapVal(this.mCanBusInfoInt[3])));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCurrentRotatingSpeed0x50() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, this.resolveRotating0x50(var4[2], var4[3])));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorInfo0x24() {
      int var1 = this.mCanBusInfoInt[2];
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRaderInfo0x23() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = false;
      if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_front_radar_enable", true)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      } else {
         RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
      }

      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setOutDoortempInfo0x27() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[3];
      String var2 = "——";
      if (var1 == 0) {
         if (var3[2] != 0) {
            var2 = this.mCanBusInfoInt[2] / 2 - 39 + this.getTempUnitC(this.mContext);
         }
      } else if (var1 == 1) {
         var1 = var3[2];
         if (var1 != 0) {
            var1 /= 2;
            var2 = (double)(var1 - 39) * 1.8 + 32.0 + this.getTempUnitF(this.mContext);
         }
      } else {
         var2 = "温度出错了";
      }

      this.updateOutDoorTemp(this.mContext, var2);
   }

   private void setRearRaderInfo0x22() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = false;
      if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_rear_radar_enable", true)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      } else {
         RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
      }

      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSpeedInfo0x16() {
      ArrayList var4 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
      int[] var3 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, var1, this.resolveSpeed0x50(var3[2], var3[3])));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var3[3], var3[2]));
   }

   private void setTrackInfo0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTurnLight0x67() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "mazda_binary_car_set77"), this.resolveTurnLight(this.mCanBusInfoInt[2])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     label100: {
                        if (var1 != 5) {
                           if (var1 == 32) {
                              this.buttonKey(46);
                              return;
                           }

                           if (var1 == 33) {
                              break label100;
                           }

                           switch (var1) {
                              case 7:
                                 this.buttonKey(2);
                                 return;
                              case 8:
                                 this.buttonKey(187);
                                 return;
                              case 9:
                                 this.buttonKey(467);
                                 return;
                              case 10:
                                 this.buttonKey(468);
                                 return;
                              default:
                                 switch (var1) {
                                    case 18:
                                    case 22:
                                       this.buttonKey(49);
                                       return;
                                    case 19:
                                       break;
                                    case 20:
                                       break label100;
                                    case 21:
                                       this.buttonKey(50);
                                       return;
                                    case 23:
                                       this.buttonKey(7);
                                       return;
                                    case 24:
                                       this.buttonKey(8);
                                       return;
                                    case 25:
                                       this.buttonKey(45);
                                       return;
                                    default:
                                       return;
                                 }
                           }
                        }

                        this.buttonKey(3);
                        return;
                     }

                     this.buttonKey(134);
                  } else {
                     this.buttonKey(21);
                  }
               } else {
                  this.buttonKey(20);
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      String var2 = DataHandleUtils.makeMediaInfoCenteredInBytes(18, "AUX");
      byte[] var1 = new byte[36];

      label13: {
         byte[] var4;
         try {
            var4 = var2.getBytes("unicode");
         } catch (Exception var3) {
            var3.printStackTrace();
            break label13;
         }

         var1 = var4;
      }

      var1 = DataHandleUtils.exceptBOMHead(var1);
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 7}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var1);
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
      if (var3 != 20) {
         if (var3 != 41) {
            if (var3 != 48) {
               if (var3 != 64) {
                  if (var3 != 80) {
                     if (var3 != 103) {
                        if (var3 != 22) {
                           if (var3 != 23) {
                              if (var3 != 38) {
                                 if (var3 != 39) {
                                    if (var3 != 66) {
                                       if (var3 != 67) {
                                          switch (var3) {
                                             case 32:
                                                this.setWheelKeyInfo();
                                                break;
                                             case 33:
                                                if (this.eachId == 2) {
                                                   return;
                                                }

                                                this.setAirInfo0x21();
                                                break;
                                             case 34:
                                                this.setRearRaderInfo0x22();
                                                break;
                                             case 35:
                                                this.setFrontRaderInfo0x23();
                                                break;
                                             case 36:
                                                this.setDoorInfo0x24();
                                          }
                                       } else {
                                          if (this.eachId != 1) {
                                             return;
                                          }

                                          this.setCDPlayInfo0x43();
                                       }
                                    } else {
                                       if (this.eachId != 1) {
                                          return;
                                       }

                                       this.setCDstatus0x42();
                                    }
                                 } else {
                                    if (this.eachId == 2) {
                                       return;
                                    }

                                    this.setOutDoortempInfo0x27();
                                 }
                              } else {
                                 this.setCarbodytime0x26();
                              }
                           } else {
                              if (this.eachId == 2) {
                                 return;
                              }

                              this.setAmplifier0x17();
                           }
                        } else {
                           if (this.eachId != 1) {
                              return;
                           }

                           this.setSpeedInfo0x16();
                        }
                     } else {
                        this.setTurnLight0x67();
                     }
                  } else {
                     if (this.eachId != 1) {
                        return;
                     }

                     this.setCurrentRotatingSpeed0x50();
                  }
               } else {
                  this.setCenterControlInfo0x40();
               }
            } else {
               this.setVersionInfo0x30();
            }
         } else {
            this.setTrackInfo0x29();
         }
      } else {
         this.setBacklightInfo0x14();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
      this.sendtype();
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      DecimalFormat var15 = new DecimalFormat("00");
      var12 = var5 + "/" + var6 + "  " + var15.format((long)this.getHour(var2)) + ":" + var15.format((long)this.getMinute(var2)) + ":" + var15.format((long)this.getSecond(var2));
      byte[] var16 = new byte[]{22, -64, 16};

      label13: {
         byte[] var17;
         try {
            var17 = DataHandleUtils.byteMerger(var16, var12.getBytes("Unicode"));
         } catch (Exception var14) {
            var14.printStackTrace();
            break label13;
         }

         var16 = var17;
      }

      var16 = DataHandleUtils.makeBytesFixedLength(var16, 39);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var16);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
      this.sendtype();
      this.initAmplifier(this.mContext);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var26 = new byte[]{22, -64, 8};
      var12 = (var9 & 255) * 256 + var3 + "/" + var4 + "  " + var12 + ":" + var13;

      label13: {
         byte[] var27;
         try {
            var27 = DataHandleUtils.byteMerger(var26, DataHandleUtils.exceptBOMHead(var12.getBytes("Unicode")));
         } catch (Exception var25) {
            var25.printStackTrace();
            break label13;
         }

         var26 = var27;
      }

      var26 = DataHandleUtils.makeBytesFixedLength(var26, 39);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var3 = var2 + " " + var3 + this.getBandUnit(var2);
      byte[] var7 = new byte[]{22, -107, 1};

      label13: {
         byte[] var8;
         try {
            var8 = DataHandleUtils.byteMerger(var7, DataHandleUtils.exceptBOMHead(var3.getBytes("Unicode")));
         } catch (Exception var6) {
            var6.printStackTrace();
            break label13;
         }

         var7 = var8;
      }

      var7 = DataHandleUtils.makeBytesFixedLength(var7, 39);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   void updateAmplifierSwitch(int var1) {
      this.mAmplifierSwitch = var1;
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var19 = new byte[]{22, -64, 8};
      var11 = (var9 & 255) * 256 + var3 + "/" + var4 + "  " + var11 + ":" + var12 + ":" + var13;

      label13: {
         byte[] var20;
         try {
            var20 = Util.byteMerger(var19, Util.exceptBOMHead(var11.getBytes("Unicode")));
         } catch (UnsupportedEncodingException var18) {
            var18.printStackTrace();
            break label13;
         }

         var19 = var20;
      }

      var19 = Util.makeBytesFixedLength(var19, 39);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
   }
}
