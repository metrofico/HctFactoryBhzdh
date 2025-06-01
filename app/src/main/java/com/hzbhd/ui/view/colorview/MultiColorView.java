package com.hzbhd.ui.view.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.hzbhd.ui.view.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001:\u00018B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0002\u0010\u000bJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\"H\u0014J\u0012\u0010'\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0002J\b\u0010.\u001a\u00020\"H\u0016J0\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\bH\u0014J\b\u00105\u001a\u00020%H\u0002J\u000e\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\bR\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d¨\u00069"},
   d2 = {"Lcom/hzbhd/ui/view/colorview/MultiColorView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "drawBottom", "drawFgBottom", "drawFgGravity", "drawFgHeight", "drawFgLeft", "drawFgRight", "drawFgTop", "drawFgWidth", "drawGravity", "drawHeight", "drawLeft", "drawRight", "drawTop", "drawWidth", "fgResIdArray", "", "Lcom/hzbhd/ui/view/colorview/MultiColorView$ResId;", "[Lcom/hzbhd/ui/view/colorview/MultiColorView$ResId;", "gradientEndColor", "gradientStartColor", "index", "isColorView", "", "resIdArray", "dispatchSetPressed", "", "pressed", "draw", "canvas", "Landroid/graphics/Canvas;", "getGravityFactor", "", "gravity", "axis", "isFocused", "onLayout", "changed", "left", "top", "right", "bottom", "setBackground", "setIndex", "value", "ResId", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class MultiColorView extends TextView {
   private final int drawBottom;
   private final int drawFgBottom;
   private final int drawFgGravity;
   private final int drawFgHeight;
   private final int drawFgLeft;
   private final int drawFgRight;
   private final int drawFgTop;
   private final int drawFgWidth;
   private final int drawGravity;
   private final int drawHeight;
   private final int drawLeft;
   private final int drawRight;
   private final int drawTop;
   private final int drawWidth;
   private final ResId[] fgResIdArray;
   private final int gradientEndColor;
   private final int gradientStartColor;
   private int index;
   private final boolean isColorView;
   private final ResId[] resIdArray;

   public MultiColorView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      this(var1, var2, 0);
   }

   public MultiColorView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      this(var1, var2, var3, 0);
   }

   public MultiColorView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3, var4);
      ResId[] var6 = new ResId[4];

      for(var3 = 0; var3 < 4; ++var3) {
         var6[var3] = null;
      }

      this.resIdArray = var6;
      var6 = new ResId[4];

      for(var3 = 0; var3 < 4; ++var3) {
         var6[var3] = null;
      }

      this.fgResIdArray = var6;
      TypedArray var7 = var1.obtainStyledAttributes(var2, R.styleable.gradientAttr);
      this.gradientStartColor = var7.getColor(R.styleable.gradientAttr_gradient_startColor, -1);
      this.gradientEndColor = var7.getColor(R.styleable.gradientAttr_gradient_endColor, -1);
      var7.recycle();
      var7 = var1.obtainStyledAttributes(var2, R.styleable.color_attr);
      boolean var5 = var7.getBoolean(R.styleable.color_attr_is_color_view, true);
      this.isColorView = var5;
      this.resIdArray[0] = new ResId(var7.getResourceId(R.styleable.color_attr_n, 0), var7.getResourceId(R.styleable.color_attr_p, 0), var7.getResourceId(R.styleable.color_attr_s, 0), var7.getResourceId(R.styleable.color_attr_d, 0));
      this.drawGravity = var7.getInt(R.styleable.color_attr_draw_gravity, 51);
      this.drawLeft = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_left, 0);
      this.drawTop = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_top, 0);
      this.drawRight = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_right, 0);
      this.drawBottom = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_bottom, 0);
      this.drawWidth = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_width, 0);
      this.drawHeight = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_height, 0);
      this.drawFgGravity = var7.getInt(R.styleable.color_attr_draw_fg_gravity, 51);
      this.drawFgLeft = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_left, 0);
      this.drawFgTop = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_top, 0);
      this.drawFgRight = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_right, 0);
      this.drawFgBottom = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_bottom, 0);
      this.drawFgWidth = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_width, 0);
      this.drawFgHeight = var7.getDimensionPixelOffset(R.styleable.color_attr_draw_fg_height, 0);
      var7.recycle();
      var7 = var1.obtainStyledAttributes(var2, R.styleable.radioButtonAttrs);
      var6[0] = new ResId(var7.getResourceId(R.styleable.radioButtonAttrs_min_fg, 0), var7.getResourceId(R.styleable.radioButtonAttrs_min_fg_p, 0), 0, 0, 12, (DefaultConstructorMarker)null);
      var7.recycle();
      TypedArray var9 = var1.obtainStyledAttributes(var2, R.styleable.multi_color_attr);
      this.resIdArray[1] = new ResId(var9.getResourceId(R.styleable.multi_color_attr_n2, 0), var9.getResourceId(R.styleable.multi_color_attr_p2, 0), var9.getResourceId(R.styleable.multi_color_attr_s2, 0), var9.getResourceId(R.styleable.multi_color_attr_d2, 0));
      this.resIdArray[2] = new ResId(var9.getResourceId(R.styleable.multi_color_attr_n3, 0), var9.getResourceId(R.styleable.multi_color_attr_p3, 0), var9.getResourceId(R.styleable.multi_color_attr_s3, 0), var9.getResourceId(R.styleable.multi_color_attr_d3, 0));
      this.resIdArray[3] = new ResId(var9.getResourceId(R.styleable.multi_color_attr_n4, 0), var9.getResourceId(R.styleable.multi_color_attr_p4, 0), var9.getResourceId(R.styleable.multi_color_attr_s4, 0), var9.getResourceId(R.styleable.multi_color_attr_d4, 0));
      var9.recycle();
      TypedArray var8 = var1.obtainStyledAttributes(var2, R.styleable.fontAttr);
      String var10 = var8.getString(R.styleable.fontAttr_font_path);
      if (var10 != null) {
         this.setTypeface(Typeface.createFromAsset(var1.getAssets(), var10));
      }

      var8.recycle();
      if (!var5) {
         this.setBackground();
      }

      var1.getContentResolver().registerContentObserver(System.getUriFor("changeAllColor"), false, (ContentObserver)(new ContentObserver(var1, this, new Handler(Looper.getMainLooper())) {
         final Context $context;
         final MultiColorView this$0;

         {
            this.$context = var1;
            this.this$0 = var2;
         }

         public void onChange(boolean var1) {
            super.onChange(var1);
            int var2 = System.getInt(this.$context.getContentResolver(), "changeAllColor", 0);
            this.this$0.setIndex(var2);
         }
      }));
   }

   private final float getGravityFactor(int var1, int var2) {
      var1 = var1 >> var2 & 15 ^ 1;
      float var4 = 0.0F;
      float var3;
      if (var1 != 0) {
         var3 = var4;
         if (var1 != 2) {
            if (var1 != 4) {
               var3 = var4;
            } else {
               var3 = 1.0F;
            }
         }
      } else {
         var3 = 0.5F;
      }

      return var3;
   }

   private final void setBackground() {
      ResId var1 = this.resIdArray[this.index];
      if (var1 != null && var1.getN() != 0) {
         this.setBackground(ContextCompat.getDrawable(this.getContext(), var1.getN()));
      }

   }

   protected void dispatchSetPressed(boolean var1) {
      super.dispatchSetPressed(var1);
      this.postInvalidate();
   }

   public void draw(Canvas var1) {
      float var2;
      int var3;
      int var4;
      TextPaint var8;
      if (this.isColorView) {
         ResId var7 = this.resIdArray[this.index];
         boolean var6 = this.isPressed();
         var8 = null;
         Integer var10;
         if (var6 && var7 != null && var7.getP() != 0) {
            var10 = var7.getP();
         } else if (this.isSelected() && var7 != null && var7.getS() != 0) {
            var10 = var7.getS();
         } else if (!this.isEnabled() && var7 != null && var7.getD() != 0) {
            var10 = var7.getD();
         } else if (var7 != null && var7.getN() != 0) {
            var10 = var7.getN();
         } else {
            var10 = null;
         }

         int var5;
         Drawable var11;
         if (var10 != null) {
            var3 = ((Number)var10).intValue();
            var11 = ContextCompat.getDrawable(this.getContext(), var3);
            if (this.drawWidth != 0 && this.drawHeight != 0) {
               var4 = (int)(this.getGravityFactor(this.drawGravity, 0) * (float)(this.getMeasuredWidth() - this.drawWidth) + (float)this.drawLeft) - this.drawRight;
               var2 = this.getGravityFactor(this.drawGravity, 4);
               var5 = this.getMeasuredHeight();
               var3 = this.drawHeight;
               var5 = (int)(var2 * (float)(var5 - var3) + (float)this.drawTop) - this.drawBottom;
               if (var11 != null) {
                  var11.setBounds(var4, var5, this.drawWidth + var4, var3 + var5);
               }
            } else if (var11 != null) {
               var11.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }

            if (var11 != null) {
               Intrinsics.checkNotNull(var1);
               var11.draw(var1);
            }
         }

         ResId var9 = this.fgResIdArray[this.index];
         if (this.isPressed() && var9 != null && var9.getP() != 0) {
            var10 = var9.getP();
         } else if (this.isSelected() && var9 != null && var9.getS() != 0) {
            var10 = var9.getS();
         } else if (!this.isEnabled() && var9 != null && var9.getD() != 0) {
            var10 = var9.getD();
         } else {
            var10 = var8;
            if (var9 != null) {
               var10 = var8;
               if (var9.getN() != 0) {
                  var10 = var9.getN();
               }
            }
         }

         if (var10 != null) {
            var3 = ((Number)var10).intValue();
            var11 = ContextCompat.getDrawable(this.getContext(), var3);
            if (this.drawFgWidth != 0 && this.drawFgHeight != 0) {
               var4 = (int)(this.getGravityFactor(this.drawFgGravity, 0) * (float)(this.getMeasuredWidth() - this.drawFgWidth) + (float)this.drawFgLeft) - this.drawFgRight;
               var2 = this.getGravityFactor(this.drawFgGravity, 4);
               var5 = this.getMeasuredHeight();
               var3 = this.drawFgHeight;
               var5 = (int)(var2 * (float)(var5 - var3) + (float)this.drawFgTop) - this.drawFgBottom;
               if (var11 != null) {
                  var11.setBounds(var4, var5, this.drawFgWidth + var4, var3 + var5);
               }
            } else if (var11 != null) {
               var11.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }

            if (var11 != null) {
               Intrinsics.checkNotNull(var1);
               var11.draw(var1);
            }
         }
      }

      if (this.gradientStartColor != -1 && this.gradientEndColor != -1) {
         var8 = this.getPaint();
         var2 = this.getTextSize();
         var3 = this.gradientStartColor;
         var4 = this.gradientEndColor;
         TileMode var12 = TileMode.CLAMP;
         var8.setShader((Shader)(new LinearGradient(0.0F, 0.0F, 0.0F, var2, new int[]{var3, var4}, new float[]{0.5F, 1.0F}, var12)));
      }

      super.draw(var1);
   }

   public boolean isFocused() {
      return true;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      if (!TextUtils.isEmpty(this.getText())) {
         this.setText(this.getText());
      }

   }

   public final void setIndex(int var1) {
      if (var1 < this.resIdArray.length && var1 < this.fgResIdArray.length) {
         this.index = var1;
         if (this.isColorView) {
            this.postInvalidate();
         } else {
            this.setBackground();
         }
      }

   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/ui/view/colorview/MultiColorView$ResId;", "", "n", "", "p", "s", "d", "(IIII)V", "getD", "()I", "getN", "getP", "getS", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class ResId {
      private final int d;
      private final int n;
      private final int p;
      private final int s;

      public ResId(int var1, int var2, int var3, int var4) {
         this.n = var1;
         this.p = var2;
         this.s = var3;
         this.d = var4;
      }

      // $FF: synthetic method
      public ResId(int var1, int var2, int var3, int var4, int var5, DefaultConstructorMarker var6) {
         if ((var5 & 2) != 0) {
            var2 = 0;
         }

         if ((var5 & 4) != 0) {
            var3 = 0;
         }

         if ((var5 & 8) != 0) {
            var4 = 0;
         }

         this(var1, var2, var3, var4);
      }

      // $FF: synthetic method
      public static ResId copy$default(ResId var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.n;
         }

         if ((var5 & 2) != 0) {
            var2 = var0.p;
         }

         if ((var5 & 4) != 0) {
            var3 = var0.s;
         }

         if ((var5 & 8) != 0) {
            var4 = var0.d;
         }

         return var0.copy(var1, var2, var3, var4);
      }

      public final int component1() {
         return this.n;
      }

      public final int component2() {
         return this.p;
      }

      public final int component3() {
         return this.s;
      }

      public final int component4() {
         return this.d;
      }

      public final ResId copy(int var1, int var2, int var3, int var4) {
         return new ResId(var1, var2, var3, var4);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof ResId)) {
            return false;
         } else {
            ResId var2 = (ResId)var1;
            if (this.n != var2.n) {
               return false;
            } else if (this.p != var2.p) {
               return false;
            } else if (this.s != var2.s) {
               return false;
            } else {
               return this.d == var2.d;
            }
         }
      }

      public final int getD() {
         return this.d;
      }

      public final int getN() {
         return this.n;
      }

      public final int getP() {
         return this.p;
      }

      public final int getS() {
         return this.s;
      }

      public int hashCode() {
         return ((Integer.hashCode(this.n) * 31 + Integer.hashCode(this.p)) * 31 + Integer.hashCode(this.s)) * 31 + Integer.hashCode(this.d);
      }

      public String toString() {
         return "ResId(n=" + this.n + ", p=" + this.p + ", s=" + this.s + ", d=" + this.d + ')';
      }
   }
}
