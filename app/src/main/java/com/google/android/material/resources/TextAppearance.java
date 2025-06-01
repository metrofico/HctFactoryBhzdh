package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.R;

public class TextAppearance {
   private static final String TAG = "TextAppearance";
   private static final int TYPEFACE_MONOSPACE = 3;
   private static final int TYPEFACE_SANS = 1;
   private static final int TYPEFACE_SERIF = 2;
   private Typeface font;
   public final String fontFamily;
   private final int fontFamilyResourceId;
   private boolean fontResolved = false;
   public final ColorStateList shadowColor;
   public final float shadowDx;
   public final float shadowDy;
   public final float shadowRadius;
   public final boolean textAllCaps;
   public final ColorStateList textColor;
   public final ColorStateList textColorHint;
   public final ColorStateList textColorLink;
   public final float textSize;
   public final int textStyle;
   public final int typeface;

   public TextAppearance(Context var1, int var2) {
      TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.TextAppearance);
      this.textSize = var3.getDimension(R.styleable.TextAppearance_android_textSize, 0.0F);
      this.textColor = MaterialResources.getColorStateList(var1, var3, R.styleable.TextAppearance_android_textColor);
      this.textColorHint = MaterialResources.getColorStateList(var1, var3, R.styleable.TextAppearance_android_textColorHint);
      this.textColorLink = MaterialResources.getColorStateList(var1, var3, R.styleable.TextAppearance_android_textColorLink);
      this.textStyle = var3.getInt(R.styleable.TextAppearance_android_textStyle, 0);
      this.typeface = var3.getInt(R.styleable.TextAppearance_android_typeface, 1);
      var2 = MaterialResources.getIndexWithValue(var3, R.styleable.TextAppearance_fontFamily, R.styleable.TextAppearance_android_fontFamily);
      this.fontFamilyResourceId = var3.getResourceId(var2, 0);
      this.fontFamily = var3.getString(var2);
      this.textAllCaps = var3.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
      this.shadowColor = MaterialResources.getColorStateList(var1, var3, R.styleable.TextAppearance_android_shadowColor);
      this.shadowDx = var3.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
      this.shadowDy = var3.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
      this.shadowRadius = var3.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
      var3.recycle();
   }

   private void createFallbackTypeface() {
      if (this.font == null) {
         this.font = Typeface.create(this.fontFamily, this.textStyle);
      }

      if (this.font == null) {
         int var1 = this.typeface;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  this.font = Typeface.DEFAULT;
               } else {
                  this.font = Typeface.MONOSPACE;
               }
            } else {
               this.font = Typeface.SERIF;
            }
         } else {
            this.font = Typeface.SANS_SERIF;
         }

         Typeface var2 = this.font;
         if (var2 != null) {
            this.font = Typeface.create(var2, this.textStyle);
         }
      }

   }

   public Typeface getFont(Context var1) {
      if (this.fontResolved) {
         return this.font;
      } else {
         if (!var1.isRestricted()) {
            label33: {
               Exception var10000;
               label39: {
                  boolean var10001;
                  Typeface var6;
                  try {
                     var6 = ResourcesCompat.getFont(var1, this.fontFamilyResourceId);
                     this.font = var6;
                  } catch (Resources.NotFoundException | UnsupportedOperationException var4) {
                     var10001 = false;
                     break label33;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                     break label39;
                  }

                  if (var6 == null) {
                     break label33;
                  }

                  try {
                     this.font = Typeface.create(var6, this.textStyle);
                     break label33;
                  } catch (Resources.NotFoundException | UnsupportedOperationException var2) {
                     var10001 = false;
                     break label33;
                  } catch (Exception var3) {
                     var10000 = var3;
                     var10001 = false;
                  }
               }

               Exception var7 = var10000;
               Log.d("TextAppearance", "Error loading font " + this.fontFamily, var7);
            }
         }

         this.createFallbackTypeface();
         this.fontResolved = true;
         return this.font;
      }
   }

   public void getFontAsync(Context var1, TextPaint var2, ResourcesCompat.FontCallback var3) {
      if (this.fontResolved) {
         this.updateTextPaintMeasureState(var2, this.font);
      } else {
         this.createFallbackTypeface();
         if (var1.isRestricted()) {
            this.fontResolved = true;
            this.updateTextPaintMeasureState(var2, this.font);
         } else {
            try {
               int var4 = this.fontFamilyResourceId;
               ResourcesCompat.FontCallback var5 = new ResourcesCompat.FontCallback(this, var2, var3) {
                  final TextAppearance this$0;
                  final ResourcesCompat.FontCallback val$callback;
                  final TextPaint val$textPaint;

                  {
                     this.this$0 = var1;
                     this.val$textPaint = var2;
                     this.val$callback = var3;
                  }

                  public void onFontRetrievalFailed(int var1) {
                     this.this$0.createFallbackTypeface();
                     this.this$0.fontResolved = true;
                     this.val$callback.onFontRetrievalFailed(var1);
                  }

                  public void onFontRetrieved(Typeface var1) {
                     TextAppearance var2 = this.this$0;
                     var2.font = Typeface.create(var1, var2.textStyle);
                     this.this$0.updateTextPaintMeasureState(this.val$textPaint, var1);
                     this.this$0.fontResolved = true;
                     this.val$callback.onFontRetrieved(var1);
                  }
               };
               ResourcesCompat.getFont(var1, var4, var5, (Handler)null);
            } catch (Resources.NotFoundException | UnsupportedOperationException var6) {
            } catch (Exception var7) {
               Log.d("TextAppearance", "Error loading font " + this.fontFamily, var7);
            }

         }
      }
   }

   public void updateDrawState(Context var1, TextPaint var2, ResourcesCompat.FontCallback var3) {
      this.updateMeasureState(var1, var2, var3);
      ColorStateList var8 = this.textColor;
      int var7;
      if (var8 != null) {
         var7 = var8.getColorForState(var2.drawableState, this.textColor.getDefaultColor());
      } else {
         var7 = -16777216;
      }

      var2.setColor(var7);
      float var6 = this.shadowRadius;
      float var4 = this.shadowDx;
      float var5 = this.shadowDy;
      var8 = this.shadowColor;
      if (var8 != null) {
         var7 = var8.getColorForState(var2.drawableState, this.shadowColor.getDefaultColor());
      } else {
         var7 = 0;
      }

      var2.setShadowLayer(var6, var4, var5, var7);
   }

   public void updateMeasureState(Context var1, TextPaint var2, ResourcesCompat.FontCallback var3) {
      if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
         this.updateTextPaintMeasureState(var2, this.getFont(var1));
      } else {
         this.getFontAsync(var1, var2, var3);
         if (!this.fontResolved) {
            this.updateTextPaintMeasureState(var2, this.font);
         }
      }

   }

   public void updateTextPaintMeasureState(TextPaint var1, Typeface var2) {
      var1.setTypeface(var2);
      int var4 = this.textStyle;
      var4 &= ~var2.getStyle();
      boolean var5;
      if ((var4 & 1) != 0) {
         var5 = true;
      } else {
         var5 = false;
      }

      var1.setFakeBoldText(var5);
      float var3;
      if ((var4 & 2) != 0) {
         var3 = -0.25F;
      } else {
         var3 = 0.0F;
      }

      var1.setTextSkewX(var3);
      var1.setTextSize(this.textSize);
   }
}
