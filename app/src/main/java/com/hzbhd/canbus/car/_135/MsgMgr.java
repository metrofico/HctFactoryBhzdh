package com.hzbhd.canbus.car._135;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
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

   private void set0xFCWheelKeyInfo() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.buttonKey(0);
            break;
         case 1:
            this.buttonKey(7);
            break;
         case 2:
            this.buttonKey(8);
            break;
         case 3:
            this.buttonKey(3);
         case 4:
         case 7:
         default:
            break;
         case 5:
            this.buttonKey(14);
            break;
         case 6:
            this.buttonKey(15);
            break;
         case 8:
            this.buttonKey(45);
            break;
         case 9:
            this.buttonKey(46);
            break;
         case 10:
            this.buttonKey(2);
      }

   }

   private void set0xFDAirInfo() {
      if (!this.isAirDataChange()) {
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         GeneralAirData.center_wheel = this.mCanBusInfoInt[2] + "." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + this.getTempUnitC(this.mContext);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      MyLog.temporaryTracking("—————HIWORLD DATA COMING—————");
      int var3 = 0;

      while(true) {
         int[] var4 = this.mCanBusInfoInt;
         if (var3 >= var4.length) {
            var3 = var4[1];
            if (var3 != 18) {
               if (var3 == 19) {
                  this.set0xFDAirInfo();
               }
            } else {
               this.set0xFCWheelKeyInfo();
            }

            return;
         }

         MyLog.temporaryTracking("mCanBusInfoInt[" + var3 + "]=" + this.mCanBusInfoInt[var3]);
         ++var3;
      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
