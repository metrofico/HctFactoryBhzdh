package com.hzbhd.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\b\u0010\u0012\u001a\u00020\u0010H\u0014R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lcom/hzbhd/ui/base/BhdRelativeLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "viewDrawAble", "Lcom/hzbhd/ui/base/ViewDrawAble;", "onAttachedToWindow", "", "onDetachedFromWindow", "onFinishInflate", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public class BhdRelativeLayout extends RelativeLayout {
   private ViewDrawAble viewDrawAble;

   public BhdRelativeLayout(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
   }

   public BhdRelativeLayout(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2);
      ViewDrawAble var3;
      if (var2 == null) {
         var3 = null;
      } else {
         var3 = new ViewDrawAble((View)this, var1, var2);
      }

      this.viewDrawAble = var3;
   }

   public BhdRelativeLayout(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      ViewDrawAble var4;
      if (var2 == null) {
         var4 = null;
      } else {
         var4 = new ViewDrawAble((View)this, var1, var2);
      }

      this.viewDrawAble = var4;
   }

   public BhdRelativeLayout(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      ViewDrawAble var5;
      if (var2 == null) {
         var5 = null;
      } else {
         var5 = new ViewDrawAble((View)this, var1, var2);
      }

      this.viewDrawAble = var5;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      ViewDrawAble var1 = this.viewDrawAble;
      if (var1 != null) {
         var1.onAttachedToWindow();
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      ViewDrawAble var1 = this.viewDrawAble;
      if (var1 != null) {
         var1.onDetachedFromWindow();
      }

   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      ViewDrawAble var1 = this.viewDrawAble;
      if (var1 != null) {
         var1.resetBackground();
      }

   }
}
