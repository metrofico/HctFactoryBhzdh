package com.hzbhd.canbus.car._160;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private void set0x55AirInfo() {
      if (!this.isAirDataChange()) {
         GeneralAirData.front_left_temperature = (double)this.mCanBusInfoInt[2] * 0.5 + this.getTempUnitC(this.mContext);
         GeneralAirData.front_right_temperature = (double)this.mCanBusInfoInt[3] * 0.5 + this.getTempUnitC(this.mContext);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_right_blow_head = false;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
         }

         GeneralAirData.auto = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
            GeneralAirData.in_out_cycle = false;
         } else if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
            GeneralAirData.in_out_cycle = true;
         }

         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            if (this.mCanBusInfoInt[7] > 200) {
               this.updateOutDoorTemp(this.mContext, -(255 - this.mCanBusInfoInt[7]) + this.getTempUnitC(this.mContext));
            } else {
               this.updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + this.getTempUnitC(this.mContext));
            }
         } else {
            this.updateOutDoorTemp(this.mContext, "");
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (var3[1] == 85) {
         this.set0x55AirInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
