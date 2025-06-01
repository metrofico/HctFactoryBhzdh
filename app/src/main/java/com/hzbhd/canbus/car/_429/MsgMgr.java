package com.hzbhd.canbus.car._429;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr mUiMgr;
   private int nowBackLight = 100;

   private String getTemperature(int var1, int var2, int var3) {
      if (var1 == var2) {
         return "LO";
      } else if (var1 == var3) {
         return "HI";
      } else {
         return DataHandleUtils.getBoolBit7(var1) ? (double)DataHandleUtils.getIntFromByteWithBit(var1, 0, 7) + 0.5 + this.getTempUnitC(this.mContext) : var1 + this.getTempUnitC(this.mContext);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private void setAir0x73() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
         this.updateOutDoorTemp(this.mContext, this.df_2Decimal.format((long)(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) - 40)));
      }

      this.mCanBusInfoInt[8] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 1, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 1, 255);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void setBacklight0x72() {
      int var1 = this.mCanBusInfoInt[5];
      if (var1 != this.nowBackLight) {
         this.nowBackLight = var1;
         this.setBacklightLevel(var1 / 20 + 1);
      }
   }

   private void setRadar0x72() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[8], var1[9], var1[10], var1[11]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[12], var1[13], var1[14], var1[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSpeed0x72() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_427_drive"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_427_drive1"), this.mCanBusInfoInt[3] + "km/h"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSwc0x72() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 10) {
                           if (var1 != 13) {
                              if (var1 == 14) {
                                 this.realKeyClick4(this.mContext, 46);
                              }
                           } else {
                              this.realKeyClick4(this.mContext, 45);
                           }
                        } else {
                           this.realKeyClick4(this.mContext, 2);
                        }
                     } else {
                        this.realKeyClick4(this.mContext, 15);
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 14);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 3);
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

   private void setTrack0x72() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(8);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(12);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(11);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(10);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 114) {
         if (var3 != 115) {
            if (var3 == 240) {
               this.setVersion0xF0();
            }
         } else {
            this.setAir0x73();
         }
      } else {
         this.setSpeed0x72();
         this.setSwc0x72();
         this.setBacklight0x72();
         this.setTrack0x72();
         this.setRadar0x72();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(8);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(13);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.equals("FM1")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xD2(1);
      } else if (var2.equals("FM2")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xD2(2);
      } else if (var2.equals("FM3")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xD2(3);
      } else if (var2.equals("AM1")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xD2(4);
      } else if (var2.equals("AM2")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xD2(5);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.getUiMgr(this.mContext).sendMediaInfo0xD2(13);
   }
}
