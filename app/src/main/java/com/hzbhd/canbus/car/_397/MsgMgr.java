package com.hzbhd.canbus.car._397;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   private static int DownValue;
   private static int LeftValue;
   private static int RightValue;
   private static int UPValue;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int mDifferentID;

   private void PanelKnobClick(int var1, int var2) {
      this.realKeyClick3(this.mContext, var1, var2, 1);
   }

   private String ResolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "LOW_TEMP";
      }

      if (var1 == 255) {
         var2 = "HIGH_TEMP";
      }

      return var2;
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick0x74(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   private void setAirInfo0x31() {
      GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     if (var1 != 12) {
                        if (var1 == 13) {
                           GeneralAirData.front_left_blow_foot = false;
                           GeneralAirData.front_right_blow_foot = false;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                        }
                     } else {
                        var1 = this.mDifferentID;
                        if (var1 == 2 || var1 == 3) {
                           return;
                        }

                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_head = false;
                        GeneralAirData.front_right_blow_head = false;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_right_blow_foot = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
            }
         } else {
            var1 = this.mDifferentID;
            if (var1 == 2 || var1 == 3) {
               return;
            }

            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setDoorInfo0x12() {
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setTrackDate0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTurnKey0x74() {
      int[] var1 = this.mCanBusInfoInt;
      switch (var1[2]) {
         case 0:
            this.realKeyClick0x74(0);
            break;
         case 1:
            this.PanelKnobClick(45, var1[3]);
            break;
         case 2:
            this.PanelKnobClick(46, var1[3]);
            break;
         case 3:
            this.PanelKnobClick(47, var1[3]);
            break;
         case 4:
            this.PanelKnobClick(48, var1[3]);
            break;
         case 5:
            this.realKeyClick0x74(33);
            break;
         case 6:
            this.realKeyClick0x74(34);
            break;
         case 7:
            this.realKeyClick0x74(35);
            break;
         case 8:
            this.realKeyClick0x74(36);
            break;
         case 9:
            this.realKeyClick0x74(49);
            break;
         case 10:
            this.realKeyClick0x74(50);
            break;
         case 11:
            this.realKeyClick0x74(151);
            break;
         case 12:
            this.realKeyClick0x74(76);
            break;
         case 13:
            this.realKeyClick0x74(68);
      }

   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     switch (var1) {
                        case 8:
                           this.realKeyClick0x11(45);
                           break;
                        case 9:
                           this.realKeyClick0x11(46);
                           break;
                        case 10:
                           this.realKeyClick0x11(2);
                     }
                  } else {
                     this.realKeyClick0x11(188);
                  }
               } else {
                  this.realKeyClick0x11(3);
               }
            } else {
               this.realKeyClick0x11(8);
            }
         } else {
            this.realKeyClick0x11(7);
         }
      } else {
         this.realKeyClick0x11(0);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mDifferentID = this.getCurrentCanDifferentId();
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 49) {
               if (var3 != 116) {
                  if (var3 == 240) {
                     this.setVersionInfo0xF0();
                  }
               } else {
                  this.setTurnKey0x74();
               }
            } else {
               this.setAirInfo0x31();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
         this.setTrackDate0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      int var2 = this.getCurrentCanDifferentId();
      this.mDifferentID = var2;
      if (var2 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 16, 0});
      }

   }
}
