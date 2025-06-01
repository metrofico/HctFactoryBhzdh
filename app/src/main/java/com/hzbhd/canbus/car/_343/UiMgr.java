package com.hzbhd.canbus.car._343;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
