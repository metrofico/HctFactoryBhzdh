package com.hzbhd.canbus.car._242;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (31 == var1) {
         var2 = "HI";
      } else if (255 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (1 <= var1 && 29 >= var1) {
         var2 = (float)(var1 + 35) * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDorrTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2 = "-" + var1;
      } else {
         var2 = "" + var1;
      }

      return var2 + this.getTempUnitC(this.mContext);
   }

   private void set0x20WhellKeyInfo() {
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
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
      }

   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.amb = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
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

      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDorrTem());
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrack() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
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
      if (var3 != 36) {
         if (var3 != 48) {
            if (var3 != 38) {
               if (var3 != 39) {
                  switch (var3) {
                     case 32:
                        this.set0x20WhellKeyInfo();
                        break;
                     case 33:
                        if (this.isAirMsgRepeat(var2)) {
                           return;
                        }

                        this.setAirData0x21();
                        break;
                     case 34:
                        this.setRadar();
                  }
               } else {
                  this.setAirData0x27();
               }
            } else {
               this.setTrack();
            }
         } else {
            this.setVersionInfo();
         }
      } else {
         if (this.isDoorMsgRepeat(var2)) {
            return;
         }

         if (this.getCurrentCanDifferentId() == 0) {
            return;
         }

         this.setDoorData0x24();
      }

   }
}
