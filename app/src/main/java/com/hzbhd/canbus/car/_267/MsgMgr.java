package com.hzbhd.canbus.car._267;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void keyControl0x20() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 7) {
                        this.realKeyClick1(15);
                        return;
                     }
                  } else {
                     this.realKeyClick1(45);
                  }

                  return;
               } else {
                  this.realKeyClick1(46);
                  return;
               }
            } else {
               this.realKeyClick1(8);
               return;
            }
         }
      } else {
         this.realKeyClick1(0);
      }

      this.realKeyClick1(7);
   }

   private void originalPanelKeyBtnInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         switch (var1) {
            case 17:
               this.realKeyClick1(76);
               break;
            case 18:
               this.realKeyClick1(77);
               break;
            case 19:
               this.realKeyClick1(141);
               break;
            case 20:
               this.realKeyClick1(30);
               break;
            case 21:
               this.realKeyClick1(49);
               break;
            default:
               switch (var1) {
                  case 25:
                     this.realKeyClick1(27);
                     break;
                  case 26:
                     this.realKeyClick1(4);
                     break;
                  case 27:
                     this.realKeyClick1(48);
                     break;
                  case 28:
                     this.realKeyClick1(47);
                     break;
                  case 29:
                     this.realKeyClick1(20);
                     break;
                  case 30:
                     this.realKeyClick1(21);
                     break;
                  case 31:
                     this.realKeyClick1(33);
                     break;
                  case 32:
                     this.realKeyClick1(34);
                     break;
                  case 33:
                     this.realKeyClick1(35);
                     break;
                  default:
                     switch (var1) {
                        case 36:
                           this.realKeyClick1(36);
                           break;
                        case 37:
                           this.realKeyClick1(37);
                           break;
                        case 38:
                           this.realKeyClick1(38);
                     }
               }
         }
      } else {
         this.realKeyClick1(0);
      }

   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[4], var3[5]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (31 == var1) {
         var2 = "HI";
      } else if (1 <= var1 && var1 <= 28) {
         if (GeneralAirData.fahrenheit_celsius) {
            var2 = var1 + 59 + this.getTempUnitF(this.mContext);
         } else {
            var2 = (float)(var1 + 31) * 0.5F + this.getTempUnitC(this.mContext);
         }
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setChannelInfo() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (this.mCanBusInfoInt[2] == 0) {
         var1 = "original_car_host";
      } else {
         var1 = "original_car_aux";
      }

      var2.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setMediaInfo() {
      int var1 = this.mCanBusInfoInt[2];
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var3 = "";
               } else {
                  var3 = this.mContext.getResources().getString(2131768086);
               }
            } else {
               var3 = this.mContext.getResources().getString(2131768085);
            }
         } else {
            var3 = this.mContext.getResources().getString(2131769755);
         }
      } else {
         var3 = this.mContext.getResources().getString(2131768083);
      }

      int[] var2;
      StringBuilder var4;
      String var5;
      String var10;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 0) {
         var4 = new StringBuilder();
         var2 = this.mCanBusInfoInt;
         var10 = var4.append((float)(var2[4] * 256 + var2[5]) * 0.01F).append("MHz").toString();
         var5 = "FM1";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 1) {
         var4 = new StringBuilder();
         var2 = this.mCanBusInfoInt;
         var10 = var4.append((float)(var2[4] * 256 + var2[5]) * 0.01F).append("MHz").toString();
         var5 = "FM2";
      } else {
         var4 = new StringBuilder();
         var2 = this.mCanBusInfoInt;
         var10 = var4.append(var2[4] * 256 + var2[5]).append("KHz").toString();
         var5 = "AM";
      }

      String var6;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 0) {
         var6 = this.mContext.getResources().getString(2131769421);
      } else {
         var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + "";
      }

      String var9;
      switch (this.mCanBusInfoInt[11]) {
         case 0:
            var9 = "LOAD";
            break;
         case 1:
            var9 = "READ";
            break;
         case 2:
            var9 = "EJECT";
            break;
         case 3:
            var9 = "NO DISC";
            break;
         case 4:
            var9 = "CHECK DISC";
            break;
         case 5:
            var9 = "BUSY";
            break;
         case 6:
            var9 = "OK";
            break;
         default:
            var9 = "";
      }

      DecimalFormat var7 = new DecimalFormat("00");
      String var8 = var7.format((long)this.mCanBusInfoInt[9]) + ":" + var7.format((long)this.mCanBusInfoInt[10]);
      ArrayList var11 = new ArrayList();
      var11.add(new DriverUpdateEntity(0, 0, var3));
      var11.add(new DriverUpdateEntity(0, 1, var5));
      var11.add(new DriverUpdateEntity(0, 2, var6));
      var11.add(new DriverUpdateEntity(0, 3, var10));
      var11.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[7] + ""));
      var11.add(new DriverUpdateEntity(0, 5, this.mCanBusInfoInt[8] + ""));
      var11.add(new DriverUpdateEntity(0, 6, var8));
      var11.add(new DriverUpdateEntity(0, 7, var9));
      this.updateGeneralDriveData(var11);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalVolumeInfo() {
      int var1 = this.mCanBusInfoInt[3];
      String var3 = "";
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769331);
      } else {
         var2 = var3;
         if (var1 <= 40) {
            var2 = var3;
            if (var1 > 0) {
               var2 = this.mCanBusInfoInt[3] + "";
            }
         }
      }

      ArrayList var4 = new ArrayList();
      if (this.mCanBusInfoInt[2] == 0) {
         var3 = this.mContext.getResources().getString(2131768209);
      } else {
         var3 = this.mContext.getResources().getString(2131769277);
      }

      var4.add(new SettingUpdateEntity(0, 1, var3));
      var4.add(new SettingUpdateEntity(0, 2, var2));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
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
      if (var3 != 32) {
         if (var3 != 35) {
            if (var3 != 48) {
               if (var3 != 120) {
                  if (var3 != 121) {
                     if (var3 != 123) {
                        if (var3 == 124) {
                           this.setMediaInfo();
                        }
                     } else {
                        this.setChannelInfo();
                     }
                  } else {
                     this.setOriginalVolumeInfo();
                  }
               } else {
                  this.originalPanelKeyBtnInfo();
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            if (this.isAirMsgReturn(var2)) {
               return;
            }

            this.setAirData();
         }
      } else {
         this.keyControl0x20();
      }

   }
}
