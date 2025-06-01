package com.hzbhd.canbus.car_cus._283;

import android.widget.PopupWindow;
import com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter;

public final class DialogUtils$$ExternalSyntheticLambda3 implements TmpsAdapter.OnItemClickListener {
   public final TmpsAdapter.OnItemClickListener f$0;
   public final PopupWindow f$1;

   // $FF: synthetic method
   public DialogUtils$$ExternalSyntheticLambda3(TmpsAdapter.OnItemClickListener var1, PopupWindow var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void click(int var1) {
      DialogUtils.lambda$chooseTmps$0(this.f$0, this.f$1, var1);
   }
}
