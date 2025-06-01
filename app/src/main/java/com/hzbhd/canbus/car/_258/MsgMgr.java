package com.hzbhd.canbus.car._258;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static int data1;
   private static boolean isAirFirst;
   private final String SWC_DATA_1 = "swc_data_1";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwcInfoCopy;
   private byte[] mCanbusAirInfoCopy;
   private Context mContext;

   private boolean isData1Change() {
      Context var2 = this.mContext;
      boolean var1 = false;
      if (SharePreUtil.getIntValue(var2, "swc_data_1", 0) != data1) {
         var1 = true;
      }

      return var1;
   }

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyControl() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 8) {
                           if (var1 == 9) {
                              this.realKeyClick(46);
                           }
                        } else {
                           this.realKeyClick(45);
                        }
                     } else {
                        this.realKeyClick(202);
                     }
                  } else {
                     this.realKeyClick(14);
                  }
               } else {
                  this.realKeyClick(3);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void setAirData0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      int var1 = this.mCanBusInfoInt[6];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 6) {
               if (var1 != 11) {
                  if (var1 == 12) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
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

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 49) {
            if (var3 == 240) {
               this.setVersionInfo();
            }
         } else {
            if (this.isAirMsgRepeat(var2)) {
               return;
            }

            this.setAirData0x31();
         }
      } else {
         if (this.isSwcMsgReturn(var2)) {
            return;
         }

         this.realKeyControl();
      }

   }

   protected boolean isAirMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }
}
