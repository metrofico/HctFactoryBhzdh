package com.hzbhd.canbus.util;

import android.content.DialogInterface;
import android.widget.SeekBar;

public final class DialogUtil$$ExternalSyntheticLambda3 implements DialogInterface.OnClickListener {
   public final DialogUtil.SeekbarDialogCallBak f$0;
   public final SeekBar f$1;
   public final int f$2;

   // $FF: synthetic method
   public DialogUtil$$ExternalSyntheticLambda3(DialogUtil.SeekbarDialogCallBak var1, SeekBar var2, int var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void onClick(DialogInterface var1, int var2) {
      DialogUtil.lambda$showSeekDialog$3(this.f$0, this.f$1, this.f$2, var1, var2);
   }
}
