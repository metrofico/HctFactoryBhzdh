package com.hzbhd.canbus.car._156;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void realKeyClick2(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void resolveOutDoorTem(int[] var1) {
      int var2;
      if (var1[2] == 1) {
         var2 = (var1[3] & 255) * -1;
      } else {
         var2 = var1[3] & 255;
      }

      if (var2 <= 59 && var2 >= -40) {
         this.updateOutDoorTemp(this.mContext, var2 + this.getTempUnitC(this.mContext));
      } else {
         this.updateOutDoorTemp(this.mContext, " ");
      }

   }

   private void setDoorData0x24() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setSwc() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick2(0);
            break;
         case 1:
            this.realKeyClick2(7);
            break;
         case 2:
            this.realKeyClick2(8);
            break;
         case 3:
            this.realKeyClick2(46);
            break;
         case 4:
            this.realKeyClick2(45);
            break;
         case 5:
            this.realKeyClick2(14);
            break;
         case 6:
            this.realKeyClick2(3);
            break;
         case 7:
            if (this.getCurrentCanDifferentId() == 1) {
               this.realKeyClick2(59);
            } else {
               this.realKeyClick2(2);
            }
            break;
         case 8:
            this.realKeyClick2(20);
            break;
         case 9:
            this.realKeyClick2(21);
            break;
         case 10:
            this.realKeyClick2(49);
            break;
         case 11:
            if (this.getCurrentCanDifferentId() == 1) {
               this.realKeyClick2(129);
            } else {
               this.realKeyClick2(128);
            }
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      LogUtil.showLog("canbusInfoChange:" + this.mCanBusInfoInt[1]);
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 160) {
            if (var3 == 208) {
               if (this.isDoorMsgReturn(var2)) {
                  return;
               }

               this.setDoorData0x24();
            }
         } else {
            this.resolveOutDoorTem(var4);
         }
      } else {
         this.setSwc();
      }

   }
}
