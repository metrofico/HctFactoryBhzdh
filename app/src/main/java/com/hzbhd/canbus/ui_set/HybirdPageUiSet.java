package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;

public class HybirdPageUiSet {
   private OnHybirdPageStatusListener onHybirdPageStatusListener;

   public OnHybirdPageStatusListener getOnHybirdPageStatusListener() {
      return this.onHybirdPageStatusListener;
   }

   public void setOnHybirdPageStatusListener(OnHybirdPageStatusListener var1) {
      this.onHybirdPageStatusListener = var1;
   }
}
