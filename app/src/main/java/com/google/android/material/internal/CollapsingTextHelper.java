package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;

public final class CollapsingTextHelper {
   private static final boolean DEBUG_DRAW = false;
   private static final Paint DEBUG_DRAW_PAINT;
   private static final boolean USE_SCALING_TEXTURE;
   private boolean boundsChanged;
   private final Rect collapsedBounds;
   private float collapsedDrawX;
   private float collapsedDrawY;
   private int collapsedShadowColor;
   private float collapsedShadowDx;
   private float collapsedShadowDy;
   private float collapsedShadowRadius;
   private ColorStateList collapsedTextColor;
   private int collapsedTextGravity = 16;
   private float collapsedTextSize = 15.0F;
   private Typeface collapsedTypeface;
   private final RectF currentBounds;
   private float currentDrawX;
   private float currentDrawY;
   private float currentTextSize;
   private Typeface currentTypeface;
   private boolean drawTitle;
   private final Rect expandedBounds;
   private float expandedDrawX;
   private float expandedDrawY;
   private float expandedFraction;
   private int expandedShadowColor;
   private float expandedShadowDx;
   private float expandedShadowDy;
   private float expandedShadowRadius;
   private ColorStateList expandedTextColor;
   private int expandedTextGravity = 16;
   private float expandedTextSize = 15.0F;
   private Bitmap expandedTitleTexture;
   private Typeface expandedTypeface;
   private boolean isRtl;
   private TimeInterpolator positionInterpolator;
   private float scale;
   private int[] state;
   private CharSequence text;
   private final TextPaint textPaint;
   private TimeInterpolator textSizeInterpolator;
   private CharSequence textToDraw;
   private float textureAscent;
   private float textureDescent;
   private Paint texturePaint;
   private final TextPaint tmpPaint;
   private boolean useTexture;
   private final View view;

   static {
      boolean var0;
      if (VERSION.SDK_INT < 18) {
         var0 = true;
      } else {
         var0 = false;
      }

      USE_SCALING_TEXTURE = var0;
      DEBUG_DRAW_PAINT = null;
   }

   public CollapsingTextHelper(View var1) {
      this.view = var1;
      TextPaint var2 = new TextPaint(129);
      this.textPaint = var2;
      this.tmpPaint = new TextPaint(var2);
      this.collapsedBounds = new Rect();
      this.expandedBounds = new Rect();
      this.currentBounds = new RectF();
   }

   private static int blendColors(int var0, int var1, float var2) {
      float var7 = 1.0F - var2;
      float var3 = (float)Color.alpha(var0);
      float var5 = (float)Color.alpha(var1);
      float var6 = (float)Color.red(var0);
      float var8 = (float)Color.red(var1);
      float var9 = (float)Color.green(var0);
      float var11 = (float)Color.green(var1);
      float var4 = (float)Color.blue(var0);
      float var10 = (float)Color.blue(var1);
      return Color.argb((int)(var3 * var7 + var5 * var2), (int)(var6 * var7 + var8 * var2), (int)(var9 * var7 + var11 * var2), (int)(var4 * var7 + var10 * var2));
   }

   private void calculateBaseOffsets() {
      float var3 = this.currentTextSize;
      this.calculateUsingTextSize(this.collapsedTextSize);
      CharSequence var8 = this.textToDraw;
      float var2 = 0.0F;
      float var1;
      if (var8 != null) {
         var1 = this.textPaint.measureText(var8, 0, var8.length());
      } else {
         var1 = 0.0F;
      }

      int var6 = GravityCompat.getAbsoluteGravity(this.collapsedTextGravity, this.isRtl);
      int var7 = var6 & 112;
      float var4;
      if (var7 != 48) {
         if (var7 != 80) {
            float var5 = (this.textPaint.descent() - this.textPaint.ascent()) / 2.0F;
            var4 = this.textPaint.descent();
            this.collapsedDrawY = (float)this.collapsedBounds.centerY() + (var5 - var4);
         } else {
            this.collapsedDrawY = (float)this.collapsedBounds.bottom;
         }
      } else {
         this.collapsedDrawY = (float)this.collapsedBounds.top - this.textPaint.ascent();
      }

      var6 &= 8388615;
      if (var6 != 1) {
         if (var6 != 5) {
            this.collapsedDrawX = (float)this.collapsedBounds.left;
         } else {
            this.collapsedDrawX = (float)this.collapsedBounds.right - var1;
         }
      } else {
         this.collapsedDrawX = (float)this.collapsedBounds.centerX() - var1 / 2.0F;
      }

      this.calculateUsingTextSize(this.expandedTextSize);
      var8 = this.textToDraw;
      var1 = var2;
      if (var8 != null) {
         var1 = this.textPaint.measureText(var8, 0, var8.length());
      }

      var6 = GravityCompat.getAbsoluteGravity(this.expandedTextGravity, this.isRtl);
      var7 = var6 & 112;
      if (var7 != 48) {
         if (var7 != 80) {
            var4 = (this.textPaint.descent() - this.textPaint.ascent()) / 2.0F;
            var2 = this.textPaint.descent();
            this.expandedDrawY = (float)this.expandedBounds.centerY() + (var4 - var2);
         } else {
            this.expandedDrawY = (float)this.expandedBounds.bottom;
         }
      } else {
         this.expandedDrawY = (float)this.expandedBounds.top - this.textPaint.ascent();
      }

      var6 &= 8388615;
      if (var6 != 1) {
         if (var6 != 5) {
            this.expandedDrawX = (float)this.expandedBounds.left;
         } else {
            this.expandedDrawX = (float)this.expandedBounds.right - var1;
         }
      } else {
         this.expandedDrawX = (float)this.expandedBounds.centerX() - var1 / 2.0F;
      }

      this.clearTexture();
      this.setInterpolatedTextSize(var3);
   }

   private void calculateCurrentOffsets() {
      this.calculateOffsets(this.expandedFraction);
   }

   private boolean calculateIsRtl(CharSequence var1) {
      int var3 = ViewCompat.getLayoutDirection(this.view);
      boolean var2 = true;
      if (var3 != 1) {
         var2 = false;
      }

      TextDirectionHeuristicCompat var4;
      if (var2) {
         var4 = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
      } else {
         var4 = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
      }

      return var4.isRtl((CharSequence)var1, 0, var1.length());
   }

   private void calculateOffsets(float var1) {
      this.interpolateBounds(var1);
      this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, var1, this.positionInterpolator);
      this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, var1, this.positionInterpolator);
      this.setInterpolatedTextSize(lerp(this.expandedTextSize, this.collapsedTextSize, var1, this.textSizeInterpolator));
      if (this.collapsedTextColor != this.expandedTextColor) {
         this.textPaint.setColor(blendColors(this.getCurrentExpandedTextColor(), this.getCurrentCollapsedTextColor(), var1));
      } else {
         this.textPaint.setColor(this.getCurrentCollapsedTextColor());
      }

      this.textPaint.setShadowLayer(lerp(this.expandedShadowRadius, this.collapsedShadowRadius, var1, (TimeInterpolator)null), lerp(this.expandedShadowDx, this.collapsedShadowDx, var1, (TimeInterpolator)null), lerp(this.expandedShadowDy, this.collapsedShadowDy, var1, (TimeInterpolator)null), blendColors(this.expandedShadowColor, this.collapsedShadowColor, var1));
      ViewCompat.postInvalidateOnAnimation(this.view);
   }

   private void calculateUsingTextSize(float var1) {
      if (this.text != null) {
         float var3 = (float)this.collapsedBounds.width();
         float var4 = (float)this.expandedBounds.width();
         boolean var8 = isClose(var1, this.collapsedTextSize);
         boolean var7 = true;
         float var2;
         boolean var5;
         Typeface var9;
         Typeface var10;
         if (var8) {
            var2 = this.collapsedTextSize;
            this.scale = 1.0F;
            var10 = this.currentTypeface;
            var9 = this.collapsedTypeface;
            if (var10 != var9) {
               this.currentTypeface = var9;
               var5 = true;
               var1 = var3;
            } else {
               var5 = false;
               var1 = var3;
            }
         } else {
            var2 = this.expandedTextSize;
            var10 = this.currentTypeface;
            var9 = this.expandedTypeface;
            if (var10 != var9) {
               this.currentTypeface = var9;
               var5 = true;
            } else {
               var5 = false;
            }

            if (isClose(var1, var2)) {
               this.scale = 1.0F;
            } else {
               this.scale = var1 / this.expandedTextSize;
            }

            var1 = this.collapsedTextSize / this.expandedTextSize;
            if (var4 * var1 > var3) {
               var1 = Math.min(var3 / var1, var4);
            } else {
               var1 = var4;
            }
         }

         boolean var6 = var5;
         if (var1 > 0.0F) {
            if (this.currentTextSize == var2 && !this.boundsChanged && !var5) {
               var5 = false;
            } else {
               var5 = true;
            }

            this.currentTextSize = var2;
            this.boundsChanged = false;
            var6 = var5;
         }

         if (this.textToDraw == null || var6) {
            this.textPaint.setTextSize(this.currentTextSize);
            this.textPaint.setTypeface(this.currentTypeface);
            TextPaint var11 = this.textPaint;
            if (this.scale == 1.0F) {
               var7 = false;
            }

            var11.setLinearText(var7);
            CharSequence var12 = TextUtils.ellipsize(this.text, this.textPaint, var1, TruncateAt.END);
            if (!TextUtils.equals(var12, this.textToDraw)) {
               this.textToDraw = var12;
               this.isRtl = this.calculateIsRtl(var12);
            }
         }

      }
   }

   private void clearTexture() {
      Bitmap var1 = this.expandedTitleTexture;
      if (var1 != null) {
         var1.recycle();
         this.expandedTitleTexture = null;
      }

   }

   private void ensureExpandedTexture() {
      if (this.expandedTitleTexture == null && !this.expandedBounds.isEmpty() && !TextUtils.isEmpty(this.textToDraw)) {
         this.calculateOffsets(0.0F);
         this.textureAscent = this.textPaint.ascent();
         this.textureDescent = this.textPaint.descent();
         TextPaint var3 = this.textPaint;
         CharSequence var4 = this.textToDraw;
         int var2 = Math.round(var3.measureText(var4, 0, var4.length()));
         int var1 = Math.round(this.textureDescent - this.textureAscent);
         if (var2 > 0 && var1 > 0) {
            this.expandedTitleTexture = Bitmap.createBitmap(var2, var1, Config.ARGB_8888);
            Canvas var5 = new Canvas(this.expandedTitleTexture);
            var4 = this.textToDraw;
            var5.drawText(var4, 0, var4.length(), 0.0F, (float)var1 - this.textPaint.descent(), this.textPaint);
            if (this.texturePaint == null) {
               this.texturePaint = new Paint(3);
            }
         }
      }

   }

   private int getCurrentExpandedTextColor() {
      int[] var1 = this.state;
      return var1 != null ? this.expandedTextColor.getColorForState(var1, 0) : this.expandedTextColor.getDefaultColor();
   }

   private void getTextPaintCollapsed(TextPaint var1) {
      var1.setTextSize(this.collapsedTextSize);
      var1.setTypeface(this.collapsedTypeface);
   }

   private void interpolateBounds(float var1) {
      this.currentBounds.left = lerp((float)this.expandedBounds.left, (float)this.collapsedBounds.left, var1, this.positionInterpolator);
      this.currentBounds.top = lerp(this.expandedDrawY, this.collapsedDrawY, var1, this.positionInterpolator);
      this.currentBounds.right = lerp((float)this.expandedBounds.right, (float)this.collapsedBounds.right, var1, this.positionInterpolator);
      this.currentBounds.bottom = lerp((float)this.expandedBounds.bottom, (float)this.collapsedBounds.bottom, var1, this.positionInterpolator);
   }

   private static boolean isClose(float var0, float var1) {
      boolean var2;
      if (Math.abs(var0 - var1) < 0.001F) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static float lerp(float var0, float var1, float var2, TimeInterpolator var3) {
      float var4 = var2;
      if (var3 != null) {
         var4 = var3.getInterpolation(var2);
      }

      return AnimationUtils.lerp(var0, var1, var4);
   }

   private Typeface readFontFamilyTypeface(int var1) {
      TypedArray var2 = this.view.getContext().obtainStyledAttributes(var1, new int[]{16843692});

      Throwable var10000;
      label78: {
         boolean var10001;
         String var3;
         try {
            var3 = var2.getString(0);
         } catch (Throwable var9) {
            var10000 = var9;
            var10001 = false;
            break label78;
         }

         if (var3 == null) {
            var2.recycle();
            return null;
         }

         Typeface var11;
         try {
            var11 = Typeface.create(var3, 0);
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         var2.recycle();
         return var11;
      }

      Throwable var10 = var10000;
      var2.recycle();
      throw var10;
   }

   private static boolean rectEquals(Rect var0, int var1, int var2, int var3, int var4) {
      boolean var5;
      if (var0.left == var1 && var0.top == var2 && var0.right == var3 && var0.bottom == var4) {
         var5 = true;
      } else {
         var5 = false;
      }

      return var5;
   }

   private void setInterpolatedTextSize(float var1) {
      this.calculateUsingTextSize(var1);
      boolean var2;
      if (USE_SCALING_TEXTURE && this.scale != 1.0F) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.useTexture = var2;
      if (var2) {
         this.ensureExpandedTexture();
      }

      ViewCompat.postInvalidateOnAnimation(this.view);
   }

   public float calculateCollapsedTextWidth() {
      if (this.text == null) {
         return 0.0F;
      } else {
         this.getTextPaintCollapsed(this.tmpPaint);
         TextPaint var2 = this.tmpPaint;
         CharSequence var1 = this.text;
         return var2.measureText(var1, 0, var1.length());
      }
   }

   public void draw(Canvas var1) {
      int var7 = var1.save();
      if (this.textToDraw != null && this.drawTitle) {
         float var5 = this.currentDrawX;
         float var4 = this.currentDrawY;
         boolean var6;
         if (this.useTexture && this.expandedTitleTexture != null) {
            var6 = true;
         } else {
            var6 = false;
         }

         float var3;
         if (var6) {
            var3 = this.textureAscent * this.scale;
         } else {
            var3 = this.textPaint.ascent() * this.scale;
            this.textPaint.descent();
         }

         float var2 = var4;
         if (var6) {
            var2 = var4 + var3;
         }

         var3 = this.scale;
         if (var3 != 1.0F) {
            var1.scale(var3, var3, var5, var2);
         }

         if (var6) {
            var1.drawBitmap(this.expandedTitleTexture, var5, var2, this.texturePaint);
         } else {
            CharSequence var8 = this.textToDraw;
            var1.drawText(var8, 0, var8.length(), var5, var2, this.textPaint);
         }
      }

      var1.restoreToCount(var7);
   }

   public void getCollapsedTextActualBounds(RectF var1) {
      boolean var3 = this.calculateIsRtl(this.text);
      Rect var4 = this.collapsedBounds;
      float var2;
      if (!var3) {
         var2 = (float)var4.left;
      } else {
         var2 = (float)var4.right - this.calculateCollapsedTextWidth();
      }

      var1.left = var2;
      var1.top = (float)this.collapsedBounds.top;
      if (!var3) {
         var2 = var1.left + this.calculateCollapsedTextWidth();
      } else {
         var2 = (float)this.collapsedBounds.right;
      }

      var1.right = var2;
      var1.bottom = (float)this.collapsedBounds.top + this.getCollapsedTextHeight();
   }

   public ColorStateList getCollapsedTextColor() {
      return this.collapsedTextColor;
   }

   public int getCollapsedTextGravity() {
      return this.collapsedTextGravity;
   }

   public float getCollapsedTextHeight() {
      this.getTextPaintCollapsed(this.tmpPaint);
      return -this.tmpPaint.ascent();
   }

   public float getCollapsedTextSize() {
      return this.collapsedTextSize;
   }

   public Typeface getCollapsedTypeface() {
      Typeface var1 = this.collapsedTypeface;
      if (var1 == null) {
         var1 = Typeface.DEFAULT;
      }

      return var1;
   }

   public int getCurrentCollapsedTextColor() {
      int[] var1 = this.state;
      return var1 != null ? this.collapsedTextColor.getColorForState(var1, 0) : this.collapsedTextColor.getDefaultColor();
   }

   public ColorStateList getExpandedTextColor() {
      return this.expandedTextColor;
   }

   public int getExpandedTextGravity() {
      return this.expandedTextGravity;
   }

   public float getExpandedTextSize() {
      return this.expandedTextSize;
   }

   public Typeface getExpandedTypeface() {
      Typeface var1 = this.expandedTypeface;
      if (var1 == null) {
         var1 = Typeface.DEFAULT;
      }

      return var1;
   }

   public float getExpansionFraction() {
      return this.expandedFraction;
   }

   public CharSequence getText() {
      return this.text;
   }

   public final boolean isStateful() {
      ColorStateList var2 = this.collapsedTextColor;
      boolean var1;
      if (var2 == null || !var2.isStateful()) {
         var2 = this.expandedTextColor;
         if (var2 == null || !var2.isStateful()) {
            var1 = false;
            return var1;
         }
      }

      var1 = true;
      return var1;
   }

   void onBoundsChanged() {
      boolean var1;
      if (this.collapsedBounds.width() > 0 && this.collapsedBounds.height() > 0 && this.expandedBounds.width() > 0 && this.expandedBounds.height() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.drawTitle = var1;
   }

   public void recalculate() {
      if (this.view.getHeight() > 0 && this.view.getWidth() > 0) {
         this.calculateBaseOffsets();
         this.calculateCurrentOffsets();
      }

   }

   public void setCollapsedBounds(int var1, int var2, int var3, int var4) {
      if (!rectEquals(this.collapsedBounds, var1, var2, var3, var4)) {
         this.collapsedBounds.set(var1, var2, var3, var4);
         this.boundsChanged = true;
         this.onBoundsChanged();
      }

   }

   public void setCollapsedTextAppearance(int var1) {
      TintTypedArray var2 = TintTypedArray.obtainStyledAttributes(this.view.getContext(), var1, R.styleable.TextAppearance);
      if (var2.hasValue(R.styleable.TextAppearance_android_textColor)) {
         this.collapsedTextColor = var2.getColorStateList(R.styleable.TextAppearance_android_textColor);
      }

      if (var2.hasValue(R.styleable.TextAppearance_android_textSize)) {
         this.collapsedTextSize = (float)var2.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)this.collapsedTextSize);
      }

      this.collapsedShadowColor = var2.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
      this.collapsedShadowDx = var2.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
      this.collapsedShadowDy = var2.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
      this.collapsedShadowRadius = var2.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
      var2.recycle();
      if (VERSION.SDK_INT >= 16) {
         this.collapsedTypeface = this.readFontFamilyTypeface(var1);
      }

      this.recalculate();
   }

   public void setCollapsedTextColor(ColorStateList var1) {
      if (this.collapsedTextColor != var1) {
         this.collapsedTextColor = var1;
         this.recalculate();
      }

   }

   public void setCollapsedTextGravity(int var1) {
      if (this.collapsedTextGravity != var1) {
         this.collapsedTextGravity = var1;
         this.recalculate();
      }

   }

   public void setCollapsedTextSize(float var1) {
      if (this.collapsedTextSize != var1) {
         this.collapsedTextSize = var1;
         this.recalculate();
      }

   }

   public void setCollapsedTypeface(Typeface var1) {
      if (this.collapsedTypeface != var1) {
         this.collapsedTypeface = var1;
         this.recalculate();
      }

   }

   public void setExpandedBounds(int var1, int var2, int var3, int var4) {
      if (!rectEquals(this.expandedBounds, var1, var2, var3, var4)) {
         this.expandedBounds.set(var1, var2, var3, var4);
         this.boundsChanged = true;
         this.onBoundsChanged();
      }

   }

   public void setExpandedTextAppearance(int var1) {
      TintTypedArray var2 = TintTypedArray.obtainStyledAttributes(this.view.getContext(), var1, R.styleable.TextAppearance);
      if (var2.hasValue(R.styleable.TextAppearance_android_textColor)) {
         this.expandedTextColor = var2.getColorStateList(R.styleable.TextAppearance_android_textColor);
      }

      if (var2.hasValue(R.styleable.TextAppearance_android_textSize)) {
         this.expandedTextSize = (float)var2.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)this.expandedTextSize);
      }

      this.expandedShadowColor = var2.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
      this.expandedShadowDx = var2.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
      this.expandedShadowDy = var2.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
      this.expandedShadowRadius = var2.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
      var2.recycle();
      if (VERSION.SDK_INT >= 16) {
         this.expandedTypeface = this.readFontFamilyTypeface(var1);
      }

      this.recalculate();
   }

   public void setExpandedTextColor(ColorStateList var1) {
      if (this.expandedTextColor != var1) {
         this.expandedTextColor = var1;
         this.recalculate();
      }

   }

   public void setExpandedTextGravity(int var1) {
      if (this.expandedTextGravity != var1) {
         this.expandedTextGravity = var1;
         this.recalculate();
      }

   }

   public void setExpandedTextSize(float var1) {
      if (this.expandedTextSize != var1) {
         this.expandedTextSize = var1;
         this.recalculate();
      }

   }

   public void setExpandedTypeface(Typeface var1) {
      if (this.expandedTypeface != var1) {
         this.expandedTypeface = var1;
         this.recalculate();
      }

   }

   public void setExpansionFraction(float var1) {
      var1 = MathUtils.clamp(var1, 0.0F, 1.0F);
      if (var1 != this.expandedFraction) {
         this.expandedFraction = var1;
         this.calculateCurrentOffsets();
      }

   }

   public void setPositionInterpolator(TimeInterpolator var1) {
      this.positionInterpolator = var1;
      this.recalculate();
   }

   public final boolean setState(int[] var1) {
      this.state = var1;
      if (this.isStateful()) {
         this.recalculate();
         return true;
      } else {
         return false;
      }
   }

   public void setText(CharSequence var1) {
      if (var1 == null || !var1.equals(this.text)) {
         this.text = var1;
         this.textToDraw = null;
         this.clearTexture();
         this.recalculate();
      }

   }

   public void setTextSizeInterpolator(TimeInterpolator var1) {
      this.textSizeInterpolator = var1;
      this.recalculate();
   }

   public void setTypefaces(Typeface var1) {
      this.expandedTypeface = var1;
      this.collapsedTypeface = var1;
      this.recalculate();
   }
}
