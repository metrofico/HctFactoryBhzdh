package com.hzbhd.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nB)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0014J0\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0014J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0014J\u001c\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""},
   d2 = {"Lcom/hzbhd/ui/view/FocusTextView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "defStyleAttr", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "refreshText", "", "isFocused", "onAttachedToWindow", "", "onLayout", "changed", "left", "top", "right", "bottom", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "setText", "text", "", "type", "Landroid/widget/TextView$BufferType;", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class FocusTextView extends TextView {
   private boolean refreshText;

   public FocusTextView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FocusTextView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FocusTextView(Context var1, AttributeSet var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public FocusTextView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      TypedArray var6;
      if (var1 != null) {
         var6 = var1.obtainStyledAttributes(var2, R.styleable.fontAttr);
      } else {
         var6 = null;
      }

      if (var6 != null) {
         String var5 = var6.getString(R.styleable.fontAttr_font_path);
         if (var5 != null) {
            this.setTypeface(Typeface.createFromAsset(var1.getAssets(), var5));
         }
      }

      if (var6 != null) {
         var6.recycle();
      }

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
         if (LogUtil.log2()) {
            LogUtil.d("onLayout: " + this.getText());
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
         LogUtil.d("onVisibilityChanged: " + var2);
      }

      if (var2 == 0) {
         this.refreshText = true;
         this.requestLayout();
      }

   }

   public void setText(CharSequence var1, BufferType var2) {
      if (!TextUtils.isEmpty(var1)) {
         boolean var4 = false;
         boolean var3 = var4;
         if (var1 != null) {
            var3 = var4;
            if (!var1.equals(this.getText())) {
               var3 = true;
            }
         }

         if (var3) {
            super.setText(var1, var2);
         }
      } else {
         super.setText(var1, var2);
      }

   }
}
