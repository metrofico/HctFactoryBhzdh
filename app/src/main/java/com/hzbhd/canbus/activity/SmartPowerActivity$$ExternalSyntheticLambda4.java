package com.hzbhd.canbus.activity;

import com.hzbhd.canbus.smartpower.SmartPowerManager;

public final class SmartPowerActivity$$ExternalSyntheticLambda4 implements SmartPowerManager.OnSmartPowerChangeListener {
   public final SmartPowerActivity f$0;

   // $FF: synthetic method
   public SmartPowerActivity$$ExternalSyntheticLambda4(SmartPowerActivity var1) {
      this.f$0 = var1;
   }

   public final void onDataChange(byte[] var1) {
      this.f$0.lambda$onCreate$0$com_hzbhd_canbus_activity_SmartPowerActivity(var1);
   }
}
