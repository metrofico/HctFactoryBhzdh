package com.hzbhd.canbus.car._442;

import android.content.Context;

public final class MsgMgr$$ExternalSyntheticLambda0 implements DvrDataKAdapter.OnDvrDataReadListener {
   public final MsgMgr f$0;
   public final Context f$1;

   // $FF: synthetic method
   public MsgMgr$$ExternalSyntheticLambda0(MsgMgr var1, Context var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void onDataRead(byte[] var1) {
      this.f$0.lambda$initCommand$0$com_hzbhd_canbus_car__442_MsgMgr(this.f$1, var1);
   }
}
