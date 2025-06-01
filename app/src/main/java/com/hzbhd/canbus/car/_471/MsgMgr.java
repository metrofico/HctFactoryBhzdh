package com.hzbhd.canbus.car._471;

import android.content.Context;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;

public class MsgMgr extends AbstractMsgMgr {
   CanDocking docking;

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.afterServiceNormalSetting(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.docking.canbusInfoChange(var1, var2);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.initCommand(var1);
   }
}
