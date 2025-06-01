package com.hzbhd.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0014J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0014J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u001cH\u0002R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"},
   d2 = {"Lcom/hzbhd/ui/base/BhdSeekBar;", "Landroid/widget/SeekBar;", "Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "background_n", "background_n1", "focusId", "getFocusId", "()I", "setFocusId", "(I)V", "progressdrawable_n", "progressdrawable_n1", "progressdrawable_p", "progressdrawable_p1", "thumb_n", "thumb_n1", "thumb_p", "thumb_p1", "init", "", "onAttachedToWindow", "onColorChange", "onDetachedFromWindow", "onFocusChange", "isFocus", "", "updateView", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public class BhdSeekBar extends SeekBar implements BhdViewUtil.ViewUtilListener {
   private int background_n;
   private int background_n1;
   private int focusId;
   private int progressdrawable_n;
   private int progressdrawable_n1;
   private int progressdrawable_p;
   private int progressdrawable_p1;
   private int thumb_n;
   private int thumb_n1;
   private int thumb_p;
   private int thumb_p1;

   public BhdSeekBar(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.focusId = -1;
   }

   public BhdSeekBar(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2);
      this.focusId = -1;
      if (var2 != null) {
         this.init(var1, var2);
      }

   }

   public BhdSeekBar(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.focusId = -1;
      if (var2 != null) {
         this.init(var1, var2);
      }

   }

   private final void init(Context var1, AttributeSet var2) {
      TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.seek_bar_attr);
      this.thumb_n = var3.getResourceId(R.styleable.seek_bar_attr_thumb_n, 0);
      this.thumb_p = var3.getResourceId(R.styleable.seek_bar_attr_thumb_p, 0);
      this.progressdrawable_n = var3.getResourceId(R.styleable.seek_bar_attr_progressdrawable_n, 0);
      this.progressdrawable_p = var3.getResourceId(R.styleable.seek_bar_attr_progressdrawable_p, 0);
      this.thumb_n1 = var3.getResourceId(R.styleable.seek_bar_attr_thumb_n1, 0);
      this.thumb_p1 = var3.getResourceId(R.styleable.seek_bar_attr_thumb_p1, 0);
      this.progressdrawable_n1 = var3.getResourceId(R.styleable.seek_bar_attr_progressdrawable_n1, 0);
      this.progressdrawable_p1 = var3.getResourceId(R.styleable.seek_bar_attr_progressdrawable_p1, 0);
      this.background_n = var3.getResourceId(R.styleable.seek_bar_attr_background_n, 0);
      this.background_n1 = var3.getResourceId(R.styleable.seek_bar_attr_background_n1, 0);
      this.updateView();
      var3.recycle();
   }

   private final void updateView() {
      int var1;
      if (BhdViewUtil.INSTANCE.getColorStyle() == 1) {
         if (this.thumb_n1 != 0) {
            this.setThumb(this.getContext().getDrawable(this.thumb_n1));
         }

         if (LogUtil.log5()) {
            LogUtil.d(Intrinsics.stringPlus("updateView(): ", this.progressdrawable_n1));
         }

         if (this.progressdrawable_n1 != 0) {
            this.setProgressDrawableTiled(this.getContext().getDrawable(this.progressdrawable_n1));
         }

         var1 = this.background_n1;
         if (var1 != 0) {
            this.setBackgroundResource(var1);
         }
      } else {
         if (this.thumb_n != 0) {
            this.setThumb(this.getContext().getDrawable(this.thumb_n));
         }

         if (this.progressdrawable_n != 0) {
            this.setProgressDrawableTiled(this.getContext().getDrawable(this.progressdrawable_n));
         }

         var1 = this.background_n;
         if (var1 != 0) {
            this.setBackgroundResource(var1);
         }
      }

   }

   public final int getFocusId() {
      return this.focusId;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      BhdViewUtil.INSTANCE.addListener((BhdViewUtil.ViewUtilListener)this, this.focusId);
   }

   public void onColorChange() {
      this.updateView();
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      BhdViewUtil.INSTANCE.removeListener((BhdViewUtil.ViewUtilListener)this, this.focusId);
   }

   public void onFocusChange(boolean var1) {
   }

   public final void setFocusId(int var1) {
      this.focusId = var1;
   }
}
