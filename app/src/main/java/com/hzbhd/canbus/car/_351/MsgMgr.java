package com.hzbhd.canbus.car._351;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;

public class MsgMgr extends AbstractMsgMgr {
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   private Context mContext;

   private void setOx20WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 == 7) {
                           this.buttonKey(2);
                        }
                     } else {
                        this.buttonKey(14);
                     }
                  } else {
                     this.buttonKey(45);
                  }
               } else {
                  this.buttonKey(46);
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

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (var3[1] == 32) {
         this.setOx20WheelKeyInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }
}
