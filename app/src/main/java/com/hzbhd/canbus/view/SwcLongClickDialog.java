package com.hzbhd.canbus.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.SwcLongKeyAdapter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0016\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/view/SwcLongClickDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "onItemClick", "Lkotlin/Function2;", "", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function2;)V", "ibClose", "Landroid/widget/ImageButton;", "rvLongKeys", "Landroidx/recyclerview/widget/RecyclerView;", "shortKey", "show", "longKey", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SwcLongClickDialog extends Dialog {
   private ImageButton ibClose;
   private RecyclerView rvLongKeys;
   private int shortKey;

   // $FF: synthetic method
   public static void $r8$lambda$JtqYzTI3UDd5rqj_MrKCdo41GS8(SwcLongClickDialog var0, View var1) {
      lambda_2$lambda_1(var0, var1);
   }

   public SwcLongClickDialog(Context var1, Function2 var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "onItemClick");
      super(var1, 2131821125);
      this.shortKey = -1;
      this.setContentView(2131558817);
      View var3 = this.findViewById(2131363221);
      RecyclerView var4 = (RecyclerView)var3;
      var4.setLayoutManager((RecyclerView.LayoutManager)(new GridLayoutManager(var1) {
         public boolean canScrollVertically() {
            return false;
         }
      }));
      var4.setAdapter((RecyclerView.Adapter)(new SwcLongKeyAdapter(var1, (Function1)(new Function1(var2, this) {
         final Function2 $onItemClick;
         final SwcLongClickDialog this$0;

         {
            this.$onItemClick = var1;
            this.this$0 = var2;
         }

         public final void invoke(int var1) {
            this.$onItemClick.invoke(this.this$0.shortKey, var1);
            this.this$0.dismiss();
         }
      }))));
      Intrinsics.checkNotNullExpressionValue(var3, "findViewById<RecyclerVie…)\n            }\n        }");
      this.rvLongKeys = var4;
      View var6 = this.findViewById(2131362384);
      ImageButton var5 = (ImageButton)var6;
      var5.setOnClickListener(new SwcLongClickDialog$$ExternalSyntheticLambda0(this));
      Intrinsics.checkNotNullExpressionValue(var6, "findViewById<ImageButton…kListener { dismiss() } }");
      this.ibClose = var5;
   }

   private static final void lambda_2$lambda_1(SwcLongClickDialog var0, View var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.dismiss();
   }

   public final void show(int var1, int var2) {
      this.shortKey = var1;
      RecyclerView.Adapter var3 = this.rvLongKeys.getAdapter();
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.adapter.SwcLongKeyAdapter");
      ((SwcLongKeyAdapter)var3).setSelectedKey(var2);
      var3 = this.rvLongKeys.getAdapter();
      if (var3 != null) {
         var3.notifyDataSetChanged();
      }

      this.show();
   }
}
