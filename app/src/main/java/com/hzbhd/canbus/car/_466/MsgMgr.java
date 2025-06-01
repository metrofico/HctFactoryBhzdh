package com.hzbhd.canbus.car._466;

import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;

public class MsgMgr extends AbstractMsgMgr {
   CanDocking docking;

   public MsgMgrInterface getInstance() {
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      return this.docking;
   }
}
