package com.hzbhd.canbus.car._239;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private void realKeyClick0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 23) {
         if (var1 != 24) {
            switch (var1) {
               case 0:
                  this.realKeyClick1(0);
                  break;
               case 1:
                  this.realKeyClick1(7);
                  break;
               case 2:
                  this.realKeyClick1(8);
                  break;
               case 3:
                  if (this.getCurrentCanDifferentId() == 2) {
                     this.realKeyClick1(207);
                  } else {
                     this.realKeyClick1(20);
                  }
                  break;
               case 4:
                  if (this.getCurrentCanDifferentId() == 2) {
                     this.realKeyClick1(206);
                  } else {
                     this.realKeyClick1(21);
                  }
                  break;
               case 5:
                  this.realKeyClick1(14);
                  break;
               case 6:
                  this.realKeyClick1(3);
                  break;
               case 7:
                  this.realKeyClick1(2);
                  break;
               case 8:
                  this.realKeyClick1(187);
                  break;
               case 9:
                  this.realKeyClick1(14);
                  break;
               case 10:
                  this.realKeyClick1(15);
                  break;
               case 11:
                  this.realKeyClick1(45);
                  break;
               case 12:
                  this.realKeyClick1(46);
                  break;
               case 13:
                  this.realKeyClick1(47);
                  break;
               case 14:
                  this.realKeyClick1(48);
                  break;
               case 15:
                  this.realKeyClick1(49);
            }
         } else {
            this.realKeyClick1(152);
         }
      } else {
         this.realKeyClick1(52);
      }

   }

   private void realKeyClick0x22() {
      int var1 = this.mCanBusInfoInt[2];
      switch (var1) {
         case 0:
            this.realKeyClick1(0);
            break;
         case 1:
            this.realKeyClick1(134);
            break;
         case 2:
            this.realKeyClick1(21);
            break;
         case 3:
            this.realKeyClick1(20);
            break;
         case 4:
            this.realKeyClick1(58);
            break;
         case 5:
            this.realKeyClick1(4);
            break;
         case 6:
            this.realKeyClick1(50);
            break;
         case 7:
            this.realKeyClick1(129);
            break;
         case 8:
            this.requestMpeg();
            break;
         case 9:
            this.realKeyClick1(3);
            break;
         case 10:
            this.realKeyClick1(33);
            break;
         case 11:
            this.realKeyClick1(34);
            break;
         case 12:
            this.realKeyClick1(35);
            break;
         case 13:
            this.realKeyClick1(36);
            break;
         case 14:
            this.realKeyClick1(37);
            break;
         case 15:
            this.realKeyClick1(38);
            break;
         case 16:
            this.realKeyClick1(39);
            break;
         case 17:
            this.realKeyClick1(40);
            break;
         case 18:
            this.realKeyClick1(41);
            break;
         case 19:
            this.realKeyClick1(32);
            break;
         case 20:
            this.realKeyClick1(31);
            break;
         case 21:
            this.realKeyClick1(31);
            break;
         case 22:
            this.startMainActivity(this.mContext);
            break;
         case 23:
            this.realKeyClick1(183);
            break;
         case 24:
            this.realKeyClick1(66);
            break;
         case 25:
            this.realKeyClick1(75);
            break;
         default:
            switch (var1) {
               case 32:
                  this.realKeyClick1(49);
                  break;
               case 33:
                  this.realKeyClick3_1(7);
                  break;
               case 34:
                  this.realKeyClick3_1(8);
                  break;
               case 35:
                  this.realKeyClick3(45);
                  break;
               case 36:
                  this.realKeyClick3(46);
                  break;
               case 37:
                  this.realKeyClick1(17);
                  break;
               case 38:
                  this.realKeyClick1(45);
                  break;
               case 39:
                  this.realKeyClick1(46);
                  break;
               case 40:
                  this.realKeyClick1(141);
                  break;
               case 41:
                  this.realKeyClick3_2(48);
                  break;
               default:
                  switch (var1) {
                     case 48:
                        this.realKeyClick3_2(47);
                        break;
                     case 49:
                        this.realKeyClick1(2);
                        break;
                     case 50:
                        this.realKeyClick1(14);
                        break;
                     case 51:
                        this.realKeyClick1(15);
                        break;
                     case 52:
                        this.realKeyClick1(75);
                        break;
                     case 53:
                        this.realKeyClick1(75);
                        break;
                     case 54:
                        this.realKeyClick1(52);
                        break;
                     case 55:
                        this.realKeyClick1(146);
                  }
            }
      }

   }

   private void realKeyClick1(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
   }

   private void realKeyClick3(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick3_1(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var3, var1, var2[2], var2[3]);
   }

   private void realKeyClick3_2(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var3, var1, var2[2], var2[3]);
   }

   private String resolveLeftAndRightTemp(int var1, boolean var2) {
      String var3;
      if (var1 == 0) {
         var3 = "LO";
      } else if (31 == var1) {
         var3 = "HI";
      } else if (255 == var1) {
         var3 = this.mContext.getString(2131769395);
      } else if (1 <= var1 && 28 >= var1) {
         if (var2) {
            var3 = var1 + 59 + "°F";
         } else {
            var3 = (double)var1 * 0.5 + 15.5 + this.getTempUnitC(this.mContext);
         }
      } else if (32 <= var1 && 36 >= var1) {
         if (var2) {
            var3 = var1 + 59 + "°F";
         } else {
            var3 = (double)var1 * 0.5 + 14.0 + this.getTempUnitC(this.mContext);
         }
      } else {
         var3 = "";
      }

      return var3;
   }

   private String resolveLeftAndRightTemp2(int var1) {
      return String.valueOf(DataHandleUtils.rangeNumber(var1, 18, 32) - 17);
   }

   private void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_window = true;
         GeneralAirData.front_right_blow_window = true;
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = (byte)DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4), 0, 7);
      if (this.getCurrentCanDifferentId() == 3) {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp2(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp2(this.mCanBusInfoInt[4]);
      } else {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4], DataHandleUtils.getBoolBit0(this.mCanBusInfoByte[6]));
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5], DataHandleUtils.getBoolBit0(this.mCanBusInfoByte[6]));
      }

      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setDoorData0x28() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setRadar0x24() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 254;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingData0x31() {
      int var1 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrack0x30() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(var1[2], var1[3], 0, 6144, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      String var1 = CommUtil.subArrayToString(this.mCanBusInfoByte, 0, 16);
      this.updateVersionInfo(this.mContext, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 40) {
         if (var3 != 127) {
            if (var3 != 48) {
               if (var3 != 49) {
                  switch (var3) {
                     case 33:
                        this.realKeyClick0x21();
                        break;
                     case 34:
                        this.realKeyClick0x22();
                        break;
                     case 35:
                        if (this.isAirMsgRepeat(var2)) {
                           return;
                        }

                        this.setAirData0x23();
                        break;
                     case 36:
                        this.setRadar0x24();
                  }
               } else {
                  this.setSettingData0x31();
               }
            } else {
               this.setTrack0x30();
            }
         } else {
            this.setVersionInfo();
         }
      } else {
         if (this.isDoorMsgRepeat(var2)) {
            return;
         }

         this.setDoorData0x28();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 127});
   }
}
