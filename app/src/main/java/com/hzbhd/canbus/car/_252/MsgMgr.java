package com.hzbhd.canbus.car._252;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (17 == var1) {
         var2 = "LO";
      } else if (32 == var1) {
         var2 = "HI";
      } else if (17 <= var1 && 31 >= var1) {
         var2 = var1 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      int var2;
      String var3;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2 = var1;
         if (var1 > 40) {
            var2 = 40;
         }

         var3 = "-" + var2;
      } else {
         var2 = var1;
         if (var1 > 120) {
            var2 = 120;
         }

         var3 = var2 + "";
      }

      return var3 + this.getTempUnitC(this.mContext);
   }

   private void set0x21WheelKeyInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     if (var1 != 7) {
                        if (var1 != 11) {
                           if (var1 != 12) {
                              if (var1 != 50) {
                                 switch (var1) {
                                    case 129:
                                       this.realKeyLongClick1(this.mContext, 52, var2[3]);
                                       break;
                                    case 130:
                                       this.realKeyLongClick1(this.mContext, 50, var2[3]);
                                       break;
                                    case 131:
                                       this.realKeyLongClick1(this.mContext, 59, var2[3]);
                                       break;
                                    case 132:
                                       this.realKeyLongClick1(this.mContext, 182, var2[3]);
                                       break;
                                    case 133:
                                       this.realKeyLongClick1(this.mContext, 47, var2[3]);
                                       break;
                                    case 134:
                                       this.realKeyLongClick1(this.mContext, 76, var2[3]);
                                       break;
                                    case 135:
                                       this.realKeyLongClick1(this.mContext, 1, var2[3]);
                                       break;
                                    case 136:
                                       this.realKeyLongClick1(this.mContext, 48, var2[3]);
                                       break;
                                    case 137:
                                       this.realKeyLongClick1(this.mContext, 77, var2[3]);
                                       break;
                                    case 138:
                                       this.realKeyLongClick1(this.mContext, 61, var2[3]);
                                       break;
                                    case 139:
                                       this.realKeyLongClick1(this.mContext, 68, var2[3]);
                                       break;
                                    case 140:
                                       this.realKeyLongClick1(this.mContext, 58, var2[3]);
                                       break;
                                    case 141:
                                       this.realKeyLongClick1(this.mContext, 4, var2[3]);
                                       break;
                                    case 142:
                                       this.forceReverse(this.mContext, true);
                                       break;
                                    case 143:
                                       this.realKeyLongClick1(this.mContext, 75, var2[3]);
                                       break;
                                    case 144:
                                       this.realKeyLongClick1(this.mContext, 2, var2[3]);
                                       break;
                                    case 145:
                                       this.realKeyLongClick1(this.mContext, 151, var2[3]);
                                       break;
                                    case 146:
                                       this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                       break;
                                    case 147:
                                       this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                       break;
                                    case 148:
                                       this.realKeyLongClick1(this.mContext, 47, var2[3]);
                                       break;
                                    case 149:
                                       this.realKeyLongClick1(this.mContext, 48, var2[3]);
                                       break;
                                    case 150:
                                       this.realKeyLongClick1(this.mContext, 49, var2[3]);
                                       break;
                                    case 151:
                                       this.realKeyLongClick1(this.mContext, 3, var2[3]);
                                       break;
                                    case 152:
                                       this.realKeyLongClick1(this.mContext, 182, var2[3]);
                                       break;
                                    case 153:
                                       this.realKeyLongClick1(this.mContext, 17, var2[3]);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 241:
                                             this.realKeyLongClick1(this.mContext, 8, var2[3]);
                                             break;
                                          case 242:
                                             this.realKeyLongClick1(this.mContext, 7, var2[3]);
                                             break;
                                          case 243:
                                             this.realKeyLongClick1(this.mContext, 21, var2[3]);
                                             break;
                                          case 244:
                                             this.realKeyLongClick1(this.mContext, 20, var2[3]);
                                       }
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 128, var2[3]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 46, var2[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 45, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 2, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 3, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 184, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
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

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setDoorData0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 35) {
            if (var3 != 37) {
               if (var3 != 40) {
                  if (var3 != 54) {
                     if (var3 == 127) {
                        this.setVersionInfo();
                     }
                  } else {
                     this.setAirData0x36();
                  }
               } else {
                  if (this.isDoorMsgRepeat(var2)) {
                     return;
                  }

                  this.setDoorData0x28();
               }
            } else {
               this.setRadar();
            }
         } else {
            if (this.isAirMsgRepeat(var2)) {
               return;
            }

            this.setAirData0x23();
         }
      } else {
         this.set0x21WheelKeyInfo();
      }

   }
}
