package com.hzbhd.canbus.car._411;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;

   private String getTemperature(int var1) {
      if (var1 == 1) {
         return "LO";
      } else {
         return var1 == 255 ? "HI" : var1 + this.getTempUnitC(this.mContext);
      }
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private void set0x72Swc() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
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

   private void set0x73Air() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
         this.updateOutDoorTemp(this.mContext, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) - 40 + this.getTempUnitC(this.mContext));
      }

      this.mCanBusInfoInt[8] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         boolean var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 2) {
            var1 = true;
         } else {
            var1 = false;
         }

         GeneralAirData.econ = var1;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void set0xF0Version() {
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
      if (var3 != 114) {
         if (var3 != 115) {
            if (var3 == 240) {
               this.set0xF0Version();
            }
         } else {
            this.set0x73Air();
         }
      } else {
         this.set0x72Swc();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
