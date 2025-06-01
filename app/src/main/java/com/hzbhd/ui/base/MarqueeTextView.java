package com.hzbhd.ui.base;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0014J0\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0014J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0014J\u001c\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"},
   d2 = {"Lcom/hzbhd/ui/base/MarqueeTextView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "refreshText", "", "isFocused", "onAttachedToWindow", "", "onLayout", "changed", "left", "top", "right", "bottom", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "setText", "text", "", "type", "Landroid/widget/TextView$BufferType;", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public class MarqueeTextView extends TextView {
   private boolean refreshText;

   public MarqueeTextView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.setEllipsize(TruncateAt.MARQUEE);
      this.setMarqueeRepeatLimit(-1);
      this.setSingleLine();
   }

   public MarqueeTextView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.setEllipsize(TruncateAt.MARQUEE);
      this.setMarqueeRepeatLimit(-1);
      this.setSingleLine();
   }

   public MarqueeTextView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3);
      this.setEllipsize(TruncateAt.MARQUEE);
      this.setMarqueeRepeatLimit(-1);
      this.setSingleLine();
   }

   public boolean isFocused() {
      return true;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.refreshText = true;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      if (!TextUtils.isEmpty(this.getText())) {
         if (LogUtil.log5()) {
            LogUtil.d(Intrinsics.stringPlus("onLayout: ", this.getText()));
         }

         if (this.refreshText) {
            this.refreshText = false;
            super.setText(this.getText(), BufferType.NORMAL);
         }
      }

   }

   protected void onVisibilityChanged(View var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "changedView");
      super.onVisibilityChanged(var1, var2);
      if (LogUtil.log5()) {
         LogUtil.d(Intrinsics.stringPlus("onVisibilityChanged: ", var2));
      }

      if (var2 == 0) {
         this.refreshText = true;
         this.requestLayout();
      }

   }

   public void setText(CharSequence var1, BufferType var2) {
      if (!TextUtils.isEmpty(var1)) {
         boolean var3 = false;
         if (var1 != null && !var1.equals(this.getText())) {
            var3 = true;
         }

         if (var3) {
            super.setText(var1, var2);
         }
      } else {
         super.setText(var1, var2);
      }

   }
}
