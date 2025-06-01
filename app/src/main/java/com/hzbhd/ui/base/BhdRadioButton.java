package com.hzbhd.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.hzbhd.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0017J \u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u000e2\u0006\u0010N\u001a\u00020\t2\u0006\u0010O\u001a\u00020\tH\u0002J\u001a\u0010P\u001a\u00020I2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010Q\u001a\u00020I2\u0006\u0010R\u001a\u00020SH\u0016R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\"\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001a\u0010%\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R\u001c\u0010-\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0010\"\u0004\b/\u0010\u0012R\u001a\u00100\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010'\"\u0004\b2\u0010)R\u001a\u00103\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010'\"\u0004\b5\u0010)R\u001c\u00106\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0010\"\u0004\b8\u0010\u0012R\u001a\u00109\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010'\"\u0004\b;\u0010)R\u001a\u0010<\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010'\"\u0004\b>\u0010)R\u001c\u0010?\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0010\"\u0004\bA\u0010\u0012R\u001a\u0010B\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010'\"\u0004\bD\u0010)R\u001a\u0010E\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010'\"\u0004\bG\u0010)¨\u0006T"},
   d2 = {"Lcom/hzbhd/ui/base/BhdRadioButton;", "Landroid/widget/RadioButton;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "fullBg", "Landroid/graphics/Bitmap;", "getFullBg", "()Landroid/graphics/Bitmap;", "setFullBg", "(Landroid/graphics/Bitmap;)V", "fullBgP", "getFullBgP", "setFullBgP", "fullFg", "getFullFg", "setFullFg", "fullFgP", "getFullFgP", "setFullFgP", "mDrawablePaint", "Landroid/graphics/Paint;", "getMDrawablePaint", "()Landroid/graphics/Paint;", "mDrawablePaint$delegate", "Lkotlin/Lazy;", "minBg", "getMinBg", "setMinBg", "minBgHeight", "getMinBgHeight", "()I", "setMinBgHeight", "(I)V", "minBgLeft", "getMinBgLeft", "setMinBgLeft", "minBgP", "getMinBgP", "setMinBgP", "minBgTop", "getMinBgTop", "setMinBgTop", "minBgWidth", "getMinBgWidth", "setMinBgWidth", "minFg", "getMinFg", "setMinFg", "minFgHeight", "getMinFgHeight", "setMinFgHeight", "minFgLeft", "getMinFgLeft", "setMinFgLeft", "minFgP", "getMinFgP", "setMinFgP", "minFgTop", "getMinFgTop", "setMinFgTop", "minFgWidth", "getMinFgWidth", "setMinFgWidth", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getDrawableBitmap", "bitmap", "drawWidth", "drawHeight", "init", "setChecked", "checked", "", "bhdview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public final class BhdRadioButton extends RadioButton {
   private Bitmap fullBg;
   private Bitmap fullBgP;
   private Bitmap fullFg;
   private Bitmap fullFgP;
   private final Lazy mDrawablePaint$delegate;
   private Bitmap minBg;
   private int minBgHeight;
   private int minBgLeft;
   private Bitmap minBgP;
   private int minBgTop;
   private int minBgWidth;
   private Bitmap minFg;
   private int minFgHeight;
   private int minFgLeft;
   private Bitmap minFgP;
   private int minFgTop;
   private int minFgWidth;

   public BhdRadioButton(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, (AttributeSet)null);
   }

   public BhdRadioButton(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, var2);
   }

   public BhdRadioButton(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, var2);
   }

   public BhdRadioButton(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3, var4);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, var2);
   }

   private final Bitmap getDrawableBitmap(Bitmap var1, int var2, int var3) {
      Matrix var4 = new Matrix();
      if (var2 != 0 && var3 != 0) {
         var4.postScale((float)var2 / (float)var1.getWidth(), (float)var3 / (float)var1.getHeight());
      }

      var1 = Bitmap.createBitmap(var1, 0, 0, var1.getWidth(), var1.getHeight(), var4, true);
      Intrinsics.checkNotNullExpressionValue(var1, "createBitmap(bitmap, 0, …map.height, matrix, true)");
      return var1;
   }

   private final Paint getMDrawablePaint() {
      return (Paint)this.mDrawablePaint$delegate.getValue();
   }

   private final void init(Context var1, AttributeSet var2) {
      this.setButtonDrawable((Drawable)null);
      if (var2 != null) {
         TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.radioButtonAttrs);
         this.minBgWidth = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_w, 0);
         this.minBgHeight = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_h, 0);
         this.minFgWidth = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_w, 0);
         this.minFgHeight = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_h, 0);
         this.minBgLeft = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_l, 0);
         this.minBgTop = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_bg_t, 0);
         this.minFgLeft = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_l, 0);
         this.minFgTop = var3.getLayoutDimension(R.styleable.radioButtonAttrs_min_fg_t, 0);
         this.fullBg = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_full_bg, 0));
         this.minBg = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_min_bg, 0));
         this.fullFg = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_full_fg, 0));
         this.minFg = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_min_fg, 0));
         this.fullBgP = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_full_bg_p, 0));
         this.minBgP = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_min_bg_p, 0));
         this.fullFgP = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_full_fg_p, 0));
         this.minFgP = BitmapFactory.decodeResource(this.getResources(), var3.getResourceId(R.styleable.radioButtonAttrs_min_fg_p, 0));
         var3.recycle();
      }

   }

   public void draw(Canvas var1) {
      Intrinsics.checkNotNullParameter(var1, "canvas");
      if (LogUtil.log2()) {
         LogUtil.d("draw: " + this.getMeasuredWidth() + ',' + this.getMeasuredHeight() + "   " + this.getMinWidth() + ',' + this.getMinHeight());
      }

      new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
      Bitmap var2;
      if (this.isChecked()) {
         var2 = this.fullBgP;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var2 = this.minBgP;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMinBgWidth(), this.getMinBgHeight()), (float)this.getMinBgLeft(), (float)this.getMinBgTop(), this.getMDrawablePaint());
         }
      } else {
         var2 = this.fullBg;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var2 = this.minBg;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMinBgWidth(), this.getMinBgHeight()), (float)this.getMinBgLeft(), (float)this.getMinBgTop(), this.getMDrawablePaint());
         }
      }

      super.onDraw(var1);
      this.dispatchDraw(var1);
      if (this.minFgWidth == 0) {
         this.minFgWidth = this.minBgWidth;
      }

      if (this.minFgHeight == 0) {
         this.minFgHeight = this.minBgHeight;
      }

      if (this.minFgLeft == 0) {
         this.minFgLeft = this.minBgLeft;
      }

      if (this.minFgTop == 0) {
         this.minFgTop = this.minBgTop;
      }

      if (this.isChecked()) {
         var2 = this.fullFgP;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var2 = this.minFgP;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMinFgWidth(), this.getMinFgHeight()), (float)this.getMinFgLeft(), (float)this.getMinFgTop(), this.getMDrawablePaint());
         }
      } else {
         var2 = this.fullFg;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var2 = this.minFg;
         if (var2 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var2, this.getMinFgWidth(), this.getMinFgHeight()), (float)this.getMinFgLeft(), (float)this.getMinFgTop(), this.getMDrawablePaint());
         }
      }

   }

   public final Bitmap getFullBg() {
      return this.fullBg;
   }

   public final Bitmap getFullBgP() {
      return this.fullBgP;
   }

   public final Bitmap getFullFg() {
      return this.fullFg;
   }

   public final Bitmap getFullFgP() {
      return this.fullFgP;
   }

   public final Bitmap getMinBg() {
      return this.minBg;
   }

   public final int getMinBgHeight() {
      return this.minBgHeight;
   }

   public final int getMinBgLeft() {
      return this.minBgLeft;
   }

   public final Bitmap getMinBgP() {
      return this.minBgP;
   }

   public final int getMinBgTop() {
      return this.minBgTop;
   }

   public final int getMinBgWidth() {
      return this.minBgWidth;
   }

   public final Bitmap getMinFg() {
      return this.minFg;
   }

   public final int getMinFgHeight() {
      return this.minFgHeight;
   }

   public final int getMinFgLeft() {
      return this.minFgLeft;
   }

   public final Bitmap getMinFgP() {
      return this.minFgP;
   }

   public final int getMinFgTop() {
      return this.minFgTop;
   }

   public final int getMinFgWidth() {
      return this.minFgWidth;
   }

   public void setChecked(boolean var1) {
      super.setChecked(var1);
      this.invalidate();
   }

   public final void setFullBg(Bitmap var1) {
      this.fullBg = var1;
   }

   public final void setFullBgP(Bitmap var1) {
      this.fullBgP = var1;
   }

   public final void setFullFg(Bitmap var1) {
      this.fullFg = var1;
   }

   public final void setFullFgP(Bitmap var1) {
      this.fullFgP = var1;
   }

   public final void setMinBg(Bitmap var1) {
      this.minBg = var1;
   }

   public final void setMinBgHeight(int var1) {
      this.minBgHeight = var1;
   }

   public final void setMinBgLeft(int var1) {
      this.minBgLeft = var1;
   }

   public final void setMinBgP(Bitmap var1) {
      this.minBgP = var1;
   }

   public final void setMinBgTop(int var1) {
      this.minBgTop = var1;
   }

   public final void setMinBgWidth(int var1) {
      this.minBgWidth = var1;
   }

   public final void setMinFg(Bitmap var1) {
      this.minFg = var1;
   }

   public final void setMinFgHeight(int var1) {
      this.minFgHeight = var1;
   }

   public final void setMinFgLeft(int var1) {
      this.minFgLeft = var1;
   }

   public final void setMinFgP(Bitmap var1) {
      this.minFgP = var1;
   }

   public final void setMinFgTop(int var1) {
      this.minFgTop = var1;
   }

   public final void setMinFgWidth(int var1) {
      this.minFgWidth = var1;
   }
}
