package com.hzbhd.canbus.car._285;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private int[] mTrackDataNow;

   private boolean isOnlyOutDoorTempChange(int var1) {
      return SharePreUtil.getIntValue(this.mContext, "_285_outdoor_temp", 0) != var1;
   }

   private boolean isTrackDataSame() {
      if (Arrays.equals(this.mTrackDataNow, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTrackDataNow = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (30 == var1) {
         var2 = "HI";
      } else if (var1 > 0 && var1 < 30) {
         var2 = (float)(var1 + 34) * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[7];
      String var2;
      if (var1 == 255) {
         var2 = "--℃";
      } else if (var1 == 254) {
         var2 = "  ";
      } else {
         var2 = var1 - 40 + "℃";
      }

      return var2;
   }

   private void setAirData0x11() {
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.in_out_cycle = var2;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
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

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
      if (this.isOnlyOutDoorTempChange(this.mCanBusInfoInt[7])) {
         SharePreUtil.setIntValue(this.mContext, "_285_outdoor_temp", this.mCanBusInfoInt[7]);
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setDoorData0x28() {
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadarInfo() {
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadarInfo() {
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSwc0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     switch (var1) {
                        case 9:
                           this.realKeyClick(201);
                           break;
                        case 10:
                           this.realKeyClick(15);
                           break;
                        case 11:
                           this.realKeyClick(45);
                           break;
                        case 12:
                           this.realKeyClick(46);
                     }
                  } else {
                     this.realKeyClick(2);
                  }
               } else {
                  this.realKeyClick(3);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setTrackInfo() {
      if (!this.isTrackDataSame()) {
         int[] var1 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte)var1[3], (byte)var1[2], 0, 12000, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 40) {
            if (var3 != 48) {
               if (var3 != 127) {
                  switch (var3) {
                     case 33:
                        this.setSwc0x21();
                        break;
                     case 34:
                        this.setRearRadarInfo();
                        break;
                     case 35:
                        this.setFrontRadarInfo();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setTrackInfo();
            }
         } else {
            this.setDoorData0x28();
         }
      } else {
         this.setAirData0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }
}
