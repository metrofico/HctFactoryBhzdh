package com.hzbhd.canbus.car._343;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr mUiMgr;

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private void set0x21WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     switch (var1) {
                        case 9:
                           this.buttonKey(14);
                           break;
                        case 10:
                           this.buttonKey(15);
                           break;
                        case 11:
                           this.buttonKey(45);
                           break;
                        case 12:
                           this.buttonKey(46);
                           break;
                        default:
                           switch (var1) {
                              case 32:
                                 this.buttonKey(30);
                                 break;
                              case 33:
                                 this.buttonKey(129);
                                 break;
                              case 34:
                                 this.buttonKey(75);
                                 break;
                              case 35:
                                 this.buttonKey(47);
                                 break;
                              case 36:
                                 this.buttonKey(48);
                                 break;
                              case 37:
                                 this.buttonKey(27);
                                 break;
                              case 38:
                                 this.buttonKey(29);
                                 break;
                              case 39:
                                 this.buttonKey(15);
                                 break;
                              case 40:
                                 this.buttonKey(14);
                                 break;
                              case 41:
                                 this.buttonKey(45);
                                 break;
                              case 42:
                                 this.buttonKey(46);
                                 break;
                              case 43:
                                 this.buttonKey(7);
                                 break;
                              case 44:
                                 this.buttonKey(8);
                                 break;
                              case 45:
                                 this.buttonKey(134);
                                 break;
                              case 46:
                                 this.buttonKey(31);
                                 break;
                              case 47:
                                 this.buttonKey(4);
                                 break;
                              case 48:
                                 this.buttonKey(58);
                           }
                     }
                  } else {
                     this.buttonKey(2);
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

   private void set0x23AirInfo() {
      if (!this.isAirDataChange()) {
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         int var1 = this.mCanBusInfoInt[3];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_left_blow_head = false;
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_right_blow_head = false;
                        GeneralAirData.front_right_blow_foot = false;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_right_blow_head = false;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_window = false;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         if (GeneralAirData.front_wind_level == 1) {
            GeneralAirData.power = false;
         } else {
            GeneralAirData.power = true;
         }

         GeneralAirData.center_wheel = this.mCanBusInfoInt[5] + "LEVEL";
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x7FVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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
      if (var3 != 33) {
         if (var3 != 35) {
            if (var3 == 127) {
               this.set0x7FVersionInfo();
            }
         } else {
            this.set0x23AirInfo();
         }
      } else {
         this.set0x21WheelKeyInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUigMgr(this.mContext).makeConnection();
   }
}
