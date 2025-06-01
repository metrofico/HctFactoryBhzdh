package com.hzbhd.canbus.car_cus._283;

import android.widget.PopupWindow;
import com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter;

public final class DialogUtils$$ExternalSyntheticLambda1 implements SettingDialogAdapter.OnItemClickListener {
   public final SettingDialogAdapter.OnItemClickListener f$0;
   public final PopupWindow f$1;

   // $FF: synthetic method
   public DialogUtils$$ExternalSyntheticLambda1(SettingDialogAdapter.OnItemClickListener var1, PopupWindow var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void onClick(int var1) {
      DialogUtils.lambda$showSettingDialog$4(this.f$0, this.f$1, var1);
   }
}
