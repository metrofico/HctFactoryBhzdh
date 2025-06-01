package com.hzbhd.canbus.car_cus._283;

import android.content.ComponentName;
import android.widget.PopupWindow;
import com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter;

public final class DialogUtils$$ExternalSyntheticLambda4 implements MainMediaAdapter.OnItemClickListener {
   public final MainMediaAdapter.OnItemClickListener f$0;
   public final PopupWindow f$1;

   // $FF: synthetic method
   public DialogUtils$$ExternalSyntheticLambda4(MainMediaAdapter.OnItemClickListener var1, PopupWindow var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void click(int var1, ComponentName var2) {
      DialogUtils.lambda$mainMedia$2(this.f$0, this.f$1, var1, var2);
   }
}
