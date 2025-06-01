package com.hzbhd.canbus.car._217;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   int data0 = 0;
   int data1 = 0;
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr mUiMgr;

   private String getACCSatate(boolean var1) {
      return var1 ? this.mContext.getString(2131759227) : this.mContext.getString(2131759226);
   }

   private String getILLSatate(boolean var1) {
      return var1 ? this.mContext.getString(2131759229) : this.mContext.getString(2131759228);
   }

   private String getKeySatate(boolean var1) {
      return var1 ? this.mContext.getString(2131759096) : this.mContext.getString(2131759095);
   }

   private String getParkSatate(boolean var1) {
      return var1 ? this.mContext.getString(2131759231) : this.mContext.getString(2131759230);
   }

   private String getREVSatate(boolean var1) {
      return var1 ? this.mContext.getString(2131759233) : this.mContext.getString(2131759232);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void set0x72CarInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != this.data0) {
         this.data0 = var1;
         this.setDriveInfo();
      }

      var1 = this.mCanBusInfoInt[4];
      if (var1 != this.data1) {
         this.data1 = var1;
         this.setWheelKeyInfo();
      }

   }

   private void setDriveInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit4"), this.getKeySatate(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit3"), this.getParkSatate(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit2"), this.getREVSatate(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit1"), this.getILLSatate(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit0"), this.getACCSatate(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setWheelKeyInfo() {
      switch (this.mCanBusInfoInt[4]) {
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
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (var3[1] == 114) {
         this.set0x72CarInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
