package com.hzbhd.canbus.car._420;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   public UiMgr(Context var1) {
      this.getTireUiSet(var1).setHaveSpareTire(false);
   }
}
