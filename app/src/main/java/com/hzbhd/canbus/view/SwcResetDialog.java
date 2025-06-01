package com.hzbhd.canbus.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
   d2 = {"Lcom/hzbhd/canbus/view/SwcResetDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "onConfirm", "Lkotlin/Function0;", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "btnCancel", "Landroid/widget/Button;", "btnConfirm", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SwcResetDialog extends Dialog {
   private final Button btnCancel;
   private final Button btnConfirm;

   // $FF: synthetic method
   public static void $r8$lambda$9_RYW2Fz5w4vmhjmnEMbTz03v7A(SwcResetDialog var0, View var1) {
      lambda_3$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$CHMHuVcqh8dYQH_2p_UqnK8w2II(Function0 var0, SwcResetDialog var1, View var2) {
      lambda_1$lambda_0(var0, var1, var2);
   }

   public SwcResetDialog(Context var1, Function0 var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "onConfirm");
      super(var1, 2131821125);
      this.setContentView(2131558819);
      View var3 = this.findViewById(2131362016);
      Button var4 = (Button)var3;
      var4.setOnClickListener(new SwcResetDialog$$ExternalSyntheticLambda0(var2, this));
      Intrinsics.checkNotNullExpressionValue(var3, "findViewById<Button>(R.i…    dismiss()\n        } }");
      this.btnConfirm = var4;
      View var5 = this.findViewById(2131362010);
      var4 = (Button)var5;
      var4.setOnClickListener(new SwcResetDialog$$ExternalSyntheticLambda1(this));
      Intrinsics.checkNotNullExpressionValue(var5, "findViewById<Button>(R.i…kListener { dismiss() } }");
      this.btnCancel = var4;
   }

   private static final void lambda_1$lambda_0(Function0 var0, SwcResetDialog var1, View var2) {
      Intrinsics.checkNotNullParameter(var0, "$onConfirm");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      var0.invoke();
      var1.dismiss();
   }

   private static final void lambda_3$lambda_2(SwcResetDialog var0, View var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.dismiss();
   }
}
