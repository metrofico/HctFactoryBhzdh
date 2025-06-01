package com.hzbhd.canbus.car._344;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr mUiMgr;

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

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
                     if (var1 != 9) {
                        if (var1 != 10) {
                           if (var1 != 13) {
                              if (var1 == 14) {
                                 this.buttonKey(47);
                              }
                           } else {
                              this.buttonKey(48);
                           }
                        } else {
                           this.buttonKey(15);
                        }
                     } else {
                        this.buttonKey(14);
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
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         switch (this.mCanBusInfoInt[3]) {
            case 1:
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = false;
               break;
            case 2:
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 3:
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 4:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 5:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_foot = false;
               break;
            case 6:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = false;
               break;
            case 7:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         int var1 = this.mCanBusInfoInt[5];
         if (var1 != 127) {
            GeneralAirData.front_left_temperature = this.getTemperature(var1, 1.0, 17.0, "C", 0, 31);
         } else {
            GeneralAirData.front_left_temperature = "X";
         }

         var1 = this.mCanBusInfoInt[6];
         if (var1 != 127) {
            GeneralAirData.front_right_temperature = this.getTemperature(var1, 1.0, 17.0, "C", 0, 31);
         } else {
            GeneralAirData.front_right_temperature = "X";
         }

         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
            this.updateOutDoorTemp(this.mContext, -DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) + this.getTempUnitC(this.mContext));
         } else {
            this.updateOutDoorTemp(this.mContext, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) + this.getTempUnitC(this.mContext));
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x7FVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
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
