package com.hzbhd.canbus.car._85;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;

public final class MsgMgr$$ExternalSyntheticLambda0 implements AbstractMsgMgr.CallBackInterface {
   public final MsgMgr f$0;
   public final Context f$1;

   // $FF: synthetic method
   public MsgMgr$$ExternalSyntheticLambda0(MsgMgr var1, Context var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void callback() {
      this.f$0.lambda$set0x28ActiveParkData$0$com_hzbhd_canbus_car__85_MsgMgr(this.f$1);
   }
}
