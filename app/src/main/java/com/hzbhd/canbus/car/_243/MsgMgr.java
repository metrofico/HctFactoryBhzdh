package com.hzbhd.canbus.car._243;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private String resolveLeftAndRightTemp(int var1) {
      String var3 = "";
      String var2 = var3;
      if (1 <= var1) {
         var2 = var3;
         if (29 >= var1) {
            var2 = var1 + "";
         }
      }

      return var2;
   }

   private void set0x02WheelKey() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 17) {
                     switch (var1) {
                        case 7:
                           this.realKeyLongClick1(this.mContext, 62, var2[3]);
                           break;
                        case 8:
                           this.realKeyLongClick1(this.mContext, 141, var2[3]);
                           break;
                        case 9:
                           this.realKeyLongClick1(this.mContext, 3, var2[3]);
                           break;
                        case 10:
                           this.realKeyLongClick1(this.mContext, 33, var2[3]);
                           break;
                        case 11:
                           this.realKeyLongClick1(this.mContext, 34, var2[3]);
                           break;
                        case 12:
                           this.realKeyLongClick1(this.mContext, 35, var2[3]);
                           break;
                        case 13:
                           this.realKeyLongClick1(this.mContext, 36, var2[3]);
                           break;
                        case 14:
                           this.realKeyLongClick1(this.mContext, 37, var2[3]);
                           break;
                        case 15:
                           this.realKeyLongClick1(this.mContext, 38, var2[3]);
                           break;
                        default:
                           switch (var1) {
                              case 22:
                                 this.realKeyLongClick1(this.mContext, 49, var2[3]);
                                 break;
                              case 23:
                                 this.realKeyClick4(this.mContext, 7);
                                 break;
                              case 24:
                                 this.realKeyClick4(this.mContext, 8);
                                 break;
                              default:
                                 switch (var1) {
                                    case 28:
                                       this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                       break;
                                    case 29:
                                       this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                       break;
                                    case 30:
                                       this.realKeyLongClick1(this.mContext, 27, var2[3]);
                                       break;
                                    case 31:
                                       this.realKeyLongClick1(this.mContext, 29, var2[3]);
                                       break;
                                    case 32:
                                       this.realKeyLongClick1(this.mContext, 2, var2[3]);
                                       break;
                                    case 33:
                                       this.realKeyLongClick1(this.mContext, 58, var2[3]);
                                       break;
                                    case 34:
                                       this.realKeyLongClick1(this.mContext, 30, var2[3]);
                                       break;
                                    case 35:
                                       this.realKeyLongClick1(this.mContext, 75, var2[3]);
                                 }
                           }
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 31, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 20, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 21, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 1, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setAirData0x03() {
      GeneralAirData.max_cool = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 8);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (var1 != 2) {
         if (var1 != 3) {
            if (var1 != 4) {
               if (var1 != 5) {
                  if (var1 == 8) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_window = true;
         GeneralAirData.front_right_blow_window = true;
      }

      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      this.updateAirActivity(this.mContext, 1001);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 2) {
         if (var3 == 3) {
            if (this.isAirMsgRepeat(var2)) {
               return;
            }

            this.setAirData0x03();
         }
      } else {
         this.set0x02WheelKey();
      }

   }
}
