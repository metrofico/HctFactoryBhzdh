package com.hzbhd.ui.view;

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
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0017J \u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0002J\u0010\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\tH\u0002J\u001a\u0010T\u001a\u00020J2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010U\u001a\u00020J2\u0006\u0010V\u001a\u00020WH\u0016R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u001c\u0010#\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\u001a\u0010&\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R\u001c\u0010.\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0011\"\u0004\b0\u0010\u0013R\u001a\u00101\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010(\"\u0004\b3\u0010*R\u001a\u00104\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010(\"\u0004\b6\u0010*R\u001c\u00107\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0011\"\u0004\b9\u0010\u0013R\u001a\u0010:\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010(\"\u0004\b<\u0010*R\u001a\u0010=\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010(\"\u0004\b?\u0010*R\u001c\u0010@\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0011\"\u0004\bB\u0010\u0013R\u001a\u0010C\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010(\"\u0004\bE\u0010*R\u001a\u0010F\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010(\"\u0004\bH\u0010*¨\u0006X"},
   d2 = {"Lcom/hzbhd/ui/view/MyRadioButton;", "Landroid/widget/RadioButton;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "drawGravity", "fullBg", "Landroid/graphics/Bitmap;", "getFullBg", "()Landroid/graphics/Bitmap;", "setFullBg", "(Landroid/graphics/Bitmap;)V", "fullBgP", "getFullBgP", "setFullBgP", "fullFg", "getFullFg", "setFullFg", "fullFgP", "getFullFgP", "setFullFgP", "mDrawablePaint", "Landroid/graphics/Paint;", "getMDrawablePaint", "()Landroid/graphics/Paint;", "mDrawablePaint$delegate", "Lkotlin/Lazy;", "minBg", "getMinBg", "setMinBg", "minBgHeight", "getMinBgHeight", "()I", "setMinBgHeight", "(I)V", "minBgLeft", "getMinBgLeft", "setMinBgLeft", "minBgP", "getMinBgP", "setMinBgP", "minBgTop", "getMinBgTop", "setMinBgTop", "minBgWidth", "getMinBgWidth", "setMinBgWidth", "minFg", "getMinFg", "setMinFg", "minFgHeight", "getMinFgHeight", "setMinFgHeight", "minFgLeft", "getMinFgLeft", "setMinFgLeft", "minFgP", "getMinFgP", "setMinFgP", "minFgTop", "getMinFgTop", "setMinFgTop", "minFgWidth", "getMinFgWidth", "setMinFgWidth", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getDrawableBitmap", "bitmap", "drawWidth", "drawHeight", "getGravityFactor", "", "axis", "init", "setChecked", "checked", "", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class MyRadioButton extends RadioButton {
   private int drawGravity;
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

   public MyRadioButton(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, (AttributeSet)null);
   }

   public MyRadioButton(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, var2);
   }

   public MyRadioButton(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3);
      this.mDrawablePaint$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.init(var1, var2);
   }

   public MyRadioButton(Context var1, AttributeSet var2, int var3, int var4) {
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

   private final float getGravityFactor(int var1) {
      var1 = this.drawGravity >> var1 & 15 ^ 1;
      float var3 = 0.0F;
      float var2;
      if (var1 != 0) {
         var2 = var3;
         if (var1 != 2) {
            if (var1 != 4) {
               var2 = var3;
            } else {
               var2 = 1.0F;
            }
         }
      } else {
         var2 = 0.5F;
      }

      return var2;
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
         TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.color_attr);
         this.drawGravity = var3.getInteger(R.styleable.color_attr_draw_gravity, 51);
         var4.recycle();
      }

   }

   public void draw(Canvas var1) {
      Intrinsics.checkNotNullParameter(var1, "canvas");
      if (LogUtil.log2()) {
         LogUtil.d("draw: " + this.getMeasuredWidth() + ',' + this.getMeasuredHeight() + "   " + this.getMinWidth() + ',' + this.getMinHeight());
      }

      new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
      int var2 = (int)(this.getGravityFactor(0) * (float)(this.getMeasuredWidth() - this.minBgWidth));
      int var3 = (int)(this.getGravityFactor(4) * (float)(this.getMeasuredHeight() - this.minBgHeight));
      Bitmap var4;
      if (this.isChecked()) {
         var4 = this.fullBgP;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var4 = this.minBgP;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.minBgWidth, this.minBgHeight), (float)this.minBgLeft + (float)var2, (float)this.minBgTop + (float)var3, this.getMDrawablePaint());
         }
      } else {
         var4 = this.fullBg;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var4 = this.minBg;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.minBgWidth, this.minBgHeight), (float)this.minBgLeft + (float)var2, (float)this.minBgTop + (float)var3, this.getMDrawablePaint());
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
         this.minFgLeft = this.minBgLeft + var2;
      }

      if (this.minFgTop == 0) {
         this.minFgTop = this.minBgTop + var3;
      }

      if (this.isChecked()) {
         var4 = this.fullFgP;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var4 = this.minFgP;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.minFgWidth, this.minFgHeight), (float)this.minFgLeft, (float)this.minFgTop, this.getMDrawablePaint());
         }
      } else {
         var4 = this.fullFg;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.getMeasuredWidth(), this.getMeasuredHeight()), 0.0F, 0.0F, this.getMDrawablePaint());
         }

         var4 = this.minFg;
         if (var4 != null) {
            var1.drawBitmap(this.getDrawableBitmap(var4, this.minFgWidth, this.minFgHeight), (float)this.minFgLeft, (float)this.minFgTop, this.getMDrawablePaint());
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
