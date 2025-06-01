package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.text.BidiFormatter;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;

public class ChipDrawable extends Drawable implements TintAwareDrawable, Drawable.Callback {
   private static final boolean DEBUG = false;
   private static final int[] DEFAULT_STATE = new int[]{16842910};
   private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
   private int alpha;
   private boolean checkable;
   private Drawable checkedIcon;
   private boolean checkedIconVisible;
   private ColorStateList chipBackgroundColor;
   private float chipCornerRadius;
   private float chipEndPadding;
   private Drawable chipIcon;
   private float chipIconSize;
   private ColorStateList chipIconTint;
   private boolean chipIconVisible;
   private float chipMinHeight;
   private final Paint chipPaint;
   private float chipStartPadding;
   private ColorStateList chipStrokeColor;
   private float chipStrokeWidth;
   private Drawable closeIcon;
   private CharSequence closeIconContentDescription;
   private float closeIconEndPadding;
   private float closeIconSize;
   private float closeIconStartPadding;
   private int[] closeIconStateSet;
   private ColorStateList closeIconTint;
   private boolean closeIconVisible;
   private ColorFilter colorFilter;
   private ColorStateList compatRippleColor;
   private final Context context;
   private boolean currentChecked;
   private int currentChipBackgroundColor;
   private int currentChipStrokeColor;
   private int currentCompatRippleColor;
   private int currentTextColor;
   private int currentTint;
   private final Paint debugPaint;
   private WeakReference delegate;
   private final ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback(this) {
      final ChipDrawable this$0;

      {
         this.this$0 = var1;
      }

      public void onFontRetrievalFailed(int var1) {
      }

      public void onFontRetrieved(Typeface var1) {
         this.this$0.textWidthDirty = true;
         this.this$0.onSizeChange();
         this.this$0.invalidateSelf();
      }
   };
   private final Paint.FontMetrics fontMetrics;
   private MotionSpec hideMotionSpec;
   private float iconEndPadding;
   private float iconStartPadding;
   private int maxWidth;
   private final PointF pointF;
   private CharSequence rawText;
   private final RectF rectF;
   private ColorStateList rippleColor;
   private boolean shouldDrawText;
   private MotionSpec showMotionSpec;
   private TextAppearance textAppearance;
   private float textEndPadding;
   private final TextPaint textPaint;
   private float textStartPadding;
   private float textWidth;
   private boolean textWidthDirty;
   private ColorStateList tint;
   private PorterDuffColorFilter tintFilter;
   private PorterDuff.Mode tintMode;
   private TextUtils.TruncateAt truncateAt;
   private CharSequence unicodeWrappedText;
   private boolean useCompatRipple;

   private ChipDrawable(Context var1) {
      TextPaint var2 = new TextPaint(1);
      this.textPaint = var2;
      this.chipPaint = new Paint(1);
      this.fontMetrics = new Paint.FontMetrics();
      this.rectF = new RectF();
      this.pointF = new PointF();
      this.alpha = 255;
      this.tintMode = Mode.SRC_IN;
      this.delegate = new WeakReference((Object)null);
      this.textWidthDirty = true;
      this.context = var1;
      this.rawText = "";
      var2.density = var1.getResources().getDisplayMetrics().density;
      this.debugPaint = null;
      int[] var3 = DEFAULT_STATE;
      this.setState(var3);
      this.setCloseIconState(var3);
      this.shouldDrawText = true;
   }

   private void applyChildDrawable(Drawable var1) {
      if (var1 != null) {
         var1.setCallback(this);
         DrawableCompat.setLayoutDirection(var1, DrawableCompat.getLayoutDirection(this));
         var1.setLevel(this.getLevel());
         var1.setVisible(this.isVisible(), false);
         if (var1 == this.closeIcon) {
            if (var1.isStateful()) {
               var1.setState(this.getCloseIconState());
            }

            DrawableCompat.setTintList(var1, this.closeIconTint);
         } else if (var1.isStateful()) {
            var1.setState(this.getState());
         }
      }

   }

   private void calculateChipIconBounds(Rect var1, RectF var2) {
      var2.setEmpty();
      if (this.showsChipIcon() || this.showsCheckedIcon()) {
         float var3 = this.chipStartPadding + this.iconStartPadding;
         if (DrawableCompat.getLayoutDirection(this) == 0) {
            var2.left = (float)var1.left + var3;
            var2.right = var2.left + this.chipIconSize;
         } else {
            var2.right = (float)var1.right - var3;
            var2.left = var2.right - this.chipIconSize;
         }

         var2.top = var1.exactCenterY() - this.chipIconSize / 2.0F;
         var2.bottom = var2.top + this.chipIconSize;
      }

   }

   private void calculateChipTouchBounds(Rect var1, RectF var2) {
      var2.set(var1);
      if (this.showsCloseIcon()) {
         float var3 = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
         if (DrawableCompat.getLayoutDirection(this) == 0) {
            var2.right = (float)var1.right - var3;
         } else {
            var2.left = (float)var1.left + var3;
         }
      }

   }

   private void calculateCloseIconBounds(Rect var1, RectF var2) {
      var2.setEmpty();
      if (this.showsCloseIcon()) {
         float var3 = this.chipEndPadding + this.closeIconEndPadding;
         if (DrawableCompat.getLayoutDirection(this) == 0) {
            var2.right = (float)var1.right - var3;
            var2.left = var2.right - this.closeIconSize;
         } else {
            var2.left = (float)var1.left + var3;
            var2.right = var2.left + this.closeIconSize;
         }

         var2.top = var1.exactCenterY() - this.closeIconSize / 2.0F;
         var2.bottom = var2.top + this.closeIconSize;
      }

   }

   private void calculateCloseIconTouchBounds(Rect var1, RectF var2) {
      var2.setEmpty();
      if (this.showsCloseIcon()) {
         float var3 = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
         if (DrawableCompat.getLayoutDirection(this) == 0) {
            var2.right = (float)var1.right;
            var2.left = var2.right - var3;
         } else {
            var2.left = (float)var1.left;
            var2.right = (float)var1.left + var3;
         }

         var2.top = (float)var1.top;
         var2.bottom = (float)var1.bottom;
      }

   }

   private float calculateCloseIconWidth() {
      return this.showsCloseIcon() ? this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding : 0.0F;
   }

   private void calculateTextBounds(Rect var1, RectF var2) {
      var2.setEmpty();
      if (this.unicodeWrappedText != null) {
         float var3 = this.chipStartPadding + this.calculateChipIconWidth() + this.textStartPadding;
         float var4 = this.chipEndPadding + this.calculateCloseIconWidth() + this.textEndPadding;
         if (DrawableCompat.getLayoutDirection(this) == 0) {
            var2.left = (float)var1.left + var3;
            var2.right = (float)var1.right - var4;
         } else {
            var2.left = (float)var1.left + var4;
            var2.right = (float)var1.right - var3;
         }

         var2.top = (float)var1.top;
         var2.bottom = (float)var1.bottom;
      }

   }

   private float calculateTextCenterFromBaseline() {
      this.textPaint.getFontMetrics(this.fontMetrics);
      return (this.fontMetrics.descent + this.fontMetrics.ascent) / 2.0F;
   }

   private float calculateTextWidth(CharSequence var1) {
      return var1 == null ? 0.0F : this.textPaint.measureText(var1, 0, var1.length());
   }

   private boolean canShowCheckedIcon() {
      boolean var1;
      if (this.checkedIconVisible && this.checkedIcon != null && this.checkable) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static ChipDrawable createFromAttributes(Context var0, AttributeSet var1, int var2, int var3) {
      ChipDrawable var4 = new ChipDrawable(var0);
      var4.loadFromAttributes(var1, var2, var3);
      return var4;
   }

   public static ChipDrawable createFromResource(Context var0, int var1) {
      Object var20;
      label80: {
         XmlPullParserException var23;
         label79: {
            IOException var10000;
            label78: {
               XmlResourceParser var4;
               boolean var10001;
               try {
                  var4 = var0.getResources().getXml(var1);
               } catch (XmlPullParserException var17) {
                  var23 = var17;
                  var10001 = false;
                  break label79;
               } catch (IOException var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label78;
               }

               while(true) {
                  int var2;
                  try {
                     var2 = var4.next();
                  } catch (XmlPullParserException var15) {
                     var23 = var15;
                     var10001 = false;
                     break label79;
                  } catch (IOException var16) {
                     var10000 = var16;
                     var10001 = false;
                     break;
                  }

                  if (var2 == 2 || var2 == 1) {
                     XmlPullParserException var19;
                     if (var2 == 2) {
                        int var3;
                        AttributeSet var21;
                        label64: {
                           try {
                              if (TextUtils.equals(var4.getName(), "chip")) {
                                 var21 = Xml.asAttributeSet(var4);
                                 var3 = var21.getStyleAttribute();
                                 break label64;
                              }
                           } catch (XmlPullParserException var11) {
                              var23 = var11;
                              var10001 = false;
                              break label79;
                           } catch (IOException var12) {
                              var10000 = var12;
                              var10001 = false;
                              break;
                           }

                           try {
                              var19 = new XmlPullParserException("Must have a <chip> start tag");
                              throw var19;
                           } catch (XmlPullParserException var9) {
                              var23 = var9;
                              var10001 = false;
                              break label79;
                           } catch (IOException var10) {
                              var10000 = var10;
                              var10001 = false;
                              break;
                           }
                        }

                        var2 = var3;
                        if (var3 == 0) {
                           try {
                              var2 = R.style.Widget_MaterialComponents_Chip_Entry;
                           } catch (XmlPullParserException var7) {
                              var23 = var7;
                              var10001 = false;
                              break label79;
                           } catch (IOException var8) {
                              var10000 = var8;
                              var10001 = false;
                              break;
                           }
                        }

                        try {
                           return createFromAttributes(var0, var21, R.attr.chipStandaloneStyle, var2);
                        } catch (XmlPullParserException var5) {
                           var23 = var5;
                           var10001 = false;
                           break label79;
                        } catch (IOException var6) {
                           var10000 = var6;
                           var10001 = false;
                           break;
                        }
                     } else {
                        try {
                           var19 = new XmlPullParserException("No start tag found");
                           throw var19;
                        } catch (XmlPullParserException var13) {
                           var23 = var13;
                           var10001 = false;
                           break label79;
                        } catch (IOException var14) {
                           var10000 = var14;
                           var10001 = false;
                           break;
                        }
                     }
                  }
               }
            }

            var20 = var10000;
            break label80;
         }

         var20 = var23;
      }

      Resources.NotFoundException var22 = new Resources.NotFoundException("Can't load chip resource ID #0x" + Integer.toHexString(var1));
      var22.initCause((Throwable)var20);
      throw var22;
   }

   private void drawCheckedIcon(Canvas var1, Rect var2) {
      if (this.showsCheckedIcon()) {
         this.calculateChipIconBounds(var2, this.rectF);
         float var3 = this.rectF.left;
         float var4 = this.rectF.top;
         var1.translate(var3, var4);
         this.checkedIcon.setBounds(0, 0, (int)this.rectF.width(), (int)this.rectF.height());
         this.checkedIcon.draw(var1);
         var1.translate(-var3, -var4);
      }

   }

   private void drawChipBackground(Canvas var1, Rect var2) {
      this.chipPaint.setColor(this.currentChipBackgroundColor);
      this.chipPaint.setStyle(Style.FILL);
      this.chipPaint.setColorFilter(this.getTintColorFilter());
      this.rectF.set(var2);
      RectF var4 = this.rectF;
      float var3 = this.chipCornerRadius;
      var1.drawRoundRect(var4, var3, var3, this.chipPaint);
   }

   private void drawChipIcon(Canvas var1, Rect var2) {
      if (this.showsChipIcon()) {
         this.calculateChipIconBounds(var2, this.rectF);
         float var4 = this.rectF.left;
         float var3 = this.rectF.top;
         var1.translate(var4, var3);
         this.chipIcon.setBounds(0, 0, (int)this.rectF.width(), (int)this.rectF.height());
         this.chipIcon.draw(var1);
         var1.translate(-var4, -var3);
      }

   }

   private void drawChipStroke(Canvas var1, Rect var2) {
      if (this.chipStrokeWidth > 0.0F) {
         this.chipPaint.setColor(this.currentChipStrokeColor);
         this.chipPaint.setStyle(Style.STROKE);
         this.chipPaint.setColorFilter(this.getTintColorFilter());
         this.rectF.set((float)var2.left + this.chipStrokeWidth / 2.0F, (float)var2.top + this.chipStrokeWidth / 2.0F, (float)var2.right - this.chipStrokeWidth / 2.0F, (float)var2.bottom - this.chipStrokeWidth / 2.0F);
         float var3 = this.chipCornerRadius - this.chipStrokeWidth / 2.0F;
         var1.drawRoundRect(this.rectF, var3, var3, this.chipPaint);
      }

   }

   private void drawCloseIcon(Canvas var1, Rect var2) {
      if (this.showsCloseIcon()) {
         this.calculateCloseIconBounds(var2, this.rectF);
         float var3 = this.rectF.left;
         float var4 = this.rectF.top;
         var1.translate(var3, var4);
         this.closeIcon.setBounds(0, 0, (int)this.rectF.width(), (int)this.rectF.height());
         this.closeIcon.draw(var1);
         var1.translate(-var3, -var4);
      }

   }

   private void drawCompatRipple(Canvas var1, Rect var2) {
      this.chipPaint.setColor(this.currentCompatRippleColor);
      this.chipPaint.setStyle(Style.FILL);
      this.rectF.set(var2);
      RectF var4 = this.rectF;
      float var3 = this.chipCornerRadius;
      var1.drawRoundRect(var4, var3, var3, this.chipPaint);
   }

   private void drawDebug(Canvas var1, Rect var2) {
      Paint var3 = this.debugPaint;
      if (var3 != null) {
         var3.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
         var1.drawRect(var2, this.debugPaint);
         if (this.showsChipIcon() || this.showsCheckedIcon()) {
            this.calculateChipIconBounds(var2, this.rectF);
            var1.drawRect(this.rectF, this.debugPaint);
         }

         if (this.unicodeWrappedText != null) {
            var1.drawLine((float)var2.left, var2.exactCenterY(), (float)var2.right, var2.exactCenterY(), this.debugPaint);
         }

         if (this.showsCloseIcon()) {
            this.calculateCloseIconBounds(var2, this.rectF);
            var1.drawRect(this.rectF, this.debugPaint);
         }

         this.debugPaint.setColor(ColorUtils.setAlphaComponent(-65536, 127));
         this.calculateChipTouchBounds(var2, this.rectF);
         var1.drawRect(this.rectF, this.debugPaint);
         this.debugPaint.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
         this.calculateCloseIconTouchBounds(var2, this.rectF);
         var1.drawRect(this.rectF, this.debugPaint);
      }

   }

   private void drawText(Canvas var1, Rect var2) {
      if (this.unicodeWrappedText != null) {
         Paint.Align var6 = this.calculateTextOriginAndAlignment(var2, this.pointF);
         this.calculateTextBounds(var2, this.rectF);
         if (this.textAppearance != null) {
            this.textPaint.drawableState = this.getState();
            this.textAppearance.updateDrawState(this.context, this.textPaint, this.fontCallback);
         }

         this.textPaint.setTextAlign(var6);
         int var5 = Math.round(this.getTextWidth());
         int var3 = Math.round(this.rectF.width());
         int var4 = 0;
         boolean var8;
         if (var5 > var3) {
            var8 = true;
         } else {
            var8 = false;
         }

         if (var8) {
            var4 = var1.save();
            var1.clipRect(this.rectF);
         }

         CharSequence var9 = this.unicodeWrappedText;
         CharSequence var7 = var9;
         if (var8) {
            var7 = var9;
            if (this.truncateAt != null) {
               var7 = TextUtils.ellipsize(var9, this.textPaint, this.rectF.width(), this.truncateAt);
            }
         }

         var1.drawText(var7, 0, var7.length(), this.pointF.x, this.pointF.y, this.textPaint);
         if (var8) {
            var1.restoreToCount(var4);
         }
      }

   }

   private float getTextWidth() {
      if (!this.textWidthDirty) {
         return this.textWidth;
      } else {
         float var1 = this.calculateTextWidth(this.unicodeWrappedText);
         this.textWidth = var1;
         this.textWidthDirty = false;
         return var1;
      }
   }

   private ColorFilter getTintColorFilter() {
      Object var1 = this.colorFilter;
      if (var1 == null) {
         var1 = this.tintFilter;
      }

      return (ColorFilter)var1;
   }

   private static boolean hasState(int[] var0, int var1) {
      if (var0 == null) {
         return false;
      } else {
         int var3 = var0.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            if (var0[var2] == var1) {
               return true;
            }
         }

         return false;
      }
   }

   private static boolean isStateful(ColorStateList var0) {
      boolean var1;
      if (var0 != null && var0.isStateful()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static boolean isStateful(Drawable var0) {
      boolean var1;
      if (var0 != null && var0.isStateful()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static boolean isStateful(TextAppearance var0) {
      boolean var1;
      if (var0 != null && var0.textColor != null && var0.textColor.isStateful()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void loadFromAttributes(AttributeSet var1, int var2, int var3) {
      TypedArray var4 = ThemeEnforcement.obtainStyledAttributes(this.context, var1, R.styleable.Chip, var2, var3);
      this.setChipBackgroundColor(MaterialResources.getColorStateList(this.context, var4, R.styleable.Chip_chipBackgroundColor));
      this.setChipMinHeight(var4.getDimension(R.styleable.Chip_chipMinHeight, 0.0F));
      this.setChipCornerRadius(var4.getDimension(R.styleable.Chip_chipCornerRadius, 0.0F));
      this.setChipStrokeColor(MaterialResources.getColorStateList(this.context, var4, R.styleable.Chip_chipStrokeColor));
      this.setChipStrokeWidth(var4.getDimension(R.styleable.Chip_chipStrokeWidth, 0.0F));
      this.setRippleColor(MaterialResources.getColorStateList(this.context, var4, R.styleable.Chip_rippleColor));
      this.setText(var4.getText(R.styleable.Chip_android_text));
      this.setTextAppearance(MaterialResources.getTextAppearance(this.context, var4, R.styleable.Chip_android_textAppearance));
      var2 = var4.getInt(R.styleable.Chip_android_ellipsize, 0);
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 == 3) {
               this.setEllipsize(TruncateAt.END);
            }
         } else {
            this.setEllipsize(TruncateAt.MIDDLE);
         }
      } else {
         this.setEllipsize(TruncateAt.START);
      }

      this.setChipIconVisible(var4.getBoolean(R.styleable.Chip_chipIconVisible, false));
      if (var1 != null && var1.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && var1.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
         this.setChipIconVisible(var4.getBoolean(R.styleable.Chip_chipIconEnabled, false));
      }

      this.setChipIcon(MaterialResources.getDrawable(this.context, var4, R.styleable.Chip_chipIcon));
      this.setChipIconTint(MaterialResources.getColorStateList(this.context, var4, R.styleable.Chip_chipIconTint));
      this.setChipIconSize(var4.getDimension(R.styleable.Chip_chipIconSize, 0.0F));
      this.setCloseIconVisible(var4.getBoolean(R.styleable.Chip_closeIconVisible, false));
      if (var1 != null && var1.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && var1.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
         this.setCloseIconVisible(var4.getBoolean(R.styleable.Chip_closeIconEnabled, false));
      }

      this.setCloseIcon(MaterialResources.getDrawable(this.context, var4, R.styleable.Chip_closeIcon));
      this.setCloseIconTint(MaterialResources.getColorStateList(this.context, var4, R.styleable.Chip_closeIconTint));
      this.setCloseIconSize(var4.getDimension(R.styleable.Chip_closeIconSize, 0.0F));
      this.setCheckable(var4.getBoolean(R.styleable.Chip_android_checkable, false));
      this.setCheckedIconVisible(var4.getBoolean(R.styleable.Chip_checkedIconVisible, false));
      if (var1 != null && var1.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && var1.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
         this.setCheckedIconVisible(var4.getBoolean(R.styleable.Chip_checkedIconEnabled, false));
      }

      this.setCheckedIcon(MaterialResources.getDrawable(this.context, var4, R.styleable.Chip_checkedIcon));
      this.setShowMotionSpec(MotionSpec.createFromAttribute(this.context, var4, R.styleable.Chip_showMotionSpec));
      this.setHideMotionSpec(MotionSpec.createFromAttribute(this.context, var4, R.styleable.Chip_hideMotionSpec));
      this.setChipStartPadding(var4.getDimension(R.styleable.Chip_chipStartPadding, 0.0F));
      this.setIconStartPadding(var4.getDimension(R.styleable.Chip_iconStartPadding, 0.0F));
      this.setIconEndPadding(var4.getDimension(R.styleable.Chip_iconEndPadding, 0.0F));
      this.setTextStartPadding(var4.getDimension(R.styleable.Chip_textStartPadding, 0.0F));
      this.setTextEndPadding(var4.getDimension(R.styleable.Chip_textEndPadding, 0.0F));
      this.setCloseIconStartPadding(var4.getDimension(R.styleable.Chip_closeIconStartPadding, 0.0F));
      this.setCloseIconEndPadding(var4.getDimension(R.styleable.Chip_closeIconEndPadding, 0.0F));
      this.setChipEndPadding(var4.getDimension(R.styleable.Chip_chipEndPadding, 0.0F));
      this.setMaxWidth(var4.getDimensionPixelSize(R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
      var4.recycle();
   }

   private boolean onStateChange(int[] var1, int[] var2) {
      boolean var8 = super.onStateChange(var1);
      ColorStateList var10 = this.chipBackgroundColor;
      int var5 = 0;
      int var4;
      if (var10 != null) {
         var4 = var10.getColorForState(var1, this.currentChipBackgroundColor);
      } else {
         var4 = 0;
      }

      int var6 = this.currentChipBackgroundColor;
      boolean var9 = true;
      if (var6 != var4) {
         this.currentChipBackgroundColor = var4;
         var8 = true;
      }

      var10 = this.chipStrokeColor;
      if (var10 != null) {
         var4 = var10.getColorForState(var1, this.currentChipStrokeColor);
      } else {
         var4 = 0;
      }

      if (this.currentChipStrokeColor != var4) {
         this.currentChipStrokeColor = var4;
         var8 = true;
      }

      var10 = this.compatRippleColor;
      if (var10 != null) {
         var4 = var10.getColorForState(var1, this.currentCompatRippleColor);
      } else {
         var4 = 0;
      }

      boolean var7 = var8;
      if (this.currentCompatRippleColor != var4) {
         this.currentCompatRippleColor = var4;
         var7 = var8;
         if (this.useCompatRipple) {
            var7 = true;
         }
      }

      TextAppearance var12 = this.textAppearance;
      if (var12 != null && var12.textColor != null) {
         var4 = this.textAppearance.textColor.getColorForState(var1, this.currentTextColor);
      } else {
         var4 = 0;
      }

      if (this.currentTextColor != var4) {
         this.currentTextColor = var4;
         var7 = true;
      }

      if (hasState(this.getState(), 16842912) && this.checkable) {
         var8 = true;
      } else {
         var8 = false;
      }

      boolean var11;
      if (this.currentChecked != var8 && this.checkedIcon != null) {
         float var3 = this.calculateChipIconWidth();
         this.currentChecked = var8;
         if (var3 != this.calculateChipIconWidth()) {
            var7 = true;
            var11 = true;
         } else {
            var11 = false;
            var7 = true;
         }
      } else {
         var11 = false;
      }

      var10 = this.tint;
      if (var10 != null) {
         var5 = var10.getColorForState(var1, this.currentTint);
      }

      if (this.currentTint != var5) {
         this.currentTint = var5;
         this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
         var8 = var9;
      } else {
         var8 = var7;
      }

      var7 = var8;
      if (isStateful(this.chipIcon)) {
         var7 = var8 | this.chipIcon.setState(var1);
      }

      var8 = var7;
      if (isStateful(this.checkedIcon)) {
         var8 = var7 | this.checkedIcon.setState(var1);
      }

      var7 = var8;
      if (isStateful(this.closeIcon)) {
         var7 = var8 | this.closeIcon.setState(var2);
      }

      if (var7) {
         this.invalidateSelf();
      }

      if (var11) {
         this.onSizeChange();
      }

      return var7;
   }

   private boolean showsCheckedIcon() {
      boolean var1;
      if (this.checkedIconVisible && this.checkedIcon != null && this.currentChecked) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean showsChipIcon() {
      boolean var1;
      if (this.chipIconVisible && this.chipIcon != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean showsCloseIcon() {
      boolean var1;
      if (this.closeIconVisible && this.closeIcon != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void unapplyChildDrawable(Drawable var1) {
      if (var1 != null) {
         var1.setCallback((Drawable.Callback)null);
      }

   }

   private void updateCompatRippleColor() {
      ColorStateList var1;
      if (this.useCompatRipple) {
         var1 = RippleUtils.convertToRippleDrawableColor(this.rippleColor);
      } else {
         var1 = null;
      }

      this.compatRippleColor = var1;
   }

   float calculateChipIconWidth() {
      return !this.showsChipIcon() && !this.showsCheckedIcon() ? 0.0F : this.iconStartPadding + this.chipIconSize + this.iconEndPadding;
   }

   Paint.Align calculateTextOriginAndAlignment(Rect var1, PointF var2) {
      var2.set(0.0F, 0.0F);
      Paint.Align var4 = Align.LEFT;
      if (this.unicodeWrappedText != null) {
         float var3 = this.chipStartPadding + this.calculateChipIconWidth() + this.textStartPadding;
         if (DrawableCompat.getLayoutDirection(this) == 0) {
            var2.x = (float)var1.left + var3;
            var4 = Align.LEFT;
         } else {
            var2.x = (float)var1.right - var3;
            var4 = Align.RIGHT;
         }

         var2.y = (float)var1.centerY() - this.calculateTextCenterFromBaseline();
      }

      return var4;
   }

   public void draw(Canvas var1) {
      Rect var3 = this.getBounds();
      if (!var3.isEmpty() && this.getAlpha() != 0) {
         int var2 = 0;
         if (this.alpha < 255) {
            var2 = CanvasCompat.saveLayerAlpha(var1, (float)var3.left, (float)var3.top, (float)var3.right, (float)var3.bottom, this.alpha);
         }

         this.drawChipBackground(var1, var3);
         this.drawChipStroke(var1, var3);
         this.drawCompatRipple(var1, var3);
         this.drawChipIcon(var1, var3);
         this.drawCheckedIcon(var1, var3);
         if (this.shouldDrawText) {
            this.drawText(var1, var3);
         }

         this.drawCloseIcon(var1, var3);
         this.drawDebug(var1, var3);
         if (this.alpha < 255) {
            var1.restoreToCount(var2);
         }
      }

   }

   public int getAlpha() {
      return this.alpha;
   }

   public Drawable getCheckedIcon() {
      return this.checkedIcon;
   }

   public ColorStateList getChipBackgroundColor() {
      return this.chipBackgroundColor;
   }

   public float getChipCornerRadius() {
      return this.chipCornerRadius;
   }

   public float getChipEndPadding() {
      return this.chipEndPadding;
   }

   public Drawable getChipIcon() {
      Drawable var1 = this.chipIcon;
      if (var1 != null) {
         var1 = DrawableCompat.unwrap(var1);
      } else {
         var1 = null;
      }

      return var1;
   }

   public float getChipIconSize() {
      return this.chipIconSize;
   }

   public ColorStateList getChipIconTint() {
      return this.chipIconTint;
   }

   public float getChipMinHeight() {
      return this.chipMinHeight;
   }

   public float getChipStartPadding() {
      return this.chipStartPadding;
   }

   public ColorStateList getChipStrokeColor() {
      return this.chipStrokeColor;
   }

   public float getChipStrokeWidth() {
      return this.chipStrokeWidth;
   }

   public void getChipTouchBounds(RectF var1) {
      this.calculateChipTouchBounds(this.getBounds(), var1);
   }

   public Drawable getCloseIcon() {
      Drawable var1 = this.closeIcon;
      if (var1 != null) {
         var1 = DrawableCompat.unwrap(var1);
      } else {
         var1 = null;
      }

      return var1;
   }

   public CharSequence getCloseIconContentDescription() {
      return this.closeIconContentDescription;
   }

   public float getCloseIconEndPadding() {
      return this.closeIconEndPadding;
   }

   public float getCloseIconSize() {
      return this.closeIconSize;
   }

   public float getCloseIconStartPadding() {
      return this.closeIconStartPadding;
   }

   public int[] getCloseIconState() {
      return this.closeIconStateSet;
   }

   public ColorStateList getCloseIconTint() {
      return this.closeIconTint;
   }

   public void getCloseIconTouchBounds(RectF var1) {
      this.calculateCloseIconTouchBounds(this.getBounds(), var1);
   }

   public ColorFilter getColorFilter() {
      return this.colorFilter;
   }

   public TextUtils.TruncateAt getEllipsize() {
      return this.truncateAt;
   }

   public MotionSpec getHideMotionSpec() {
      return this.hideMotionSpec;
   }

   public float getIconEndPadding() {
      return this.iconEndPadding;
   }

   public float getIconStartPadding() {
      return this.iconStartPadding;
   }

   public int getIntrinsicHeight() {
      return (int)this.chipMinHeight;
   }

   public int getIntrinsicWidth() {
      return Math.min(Math.round(this.chipStartPadding + this.calculateChipIconWidth() + this.textStartPadding + this.getTextWidth() + this.textEndPadding + this.calculateCloseIconWidth() + this.chipEndPadding), this.maxWidth);
   }

   public int getMaxWidth() {
      return this.maxWidth;
   }

   public int getOpacity() {
      return -3;
   }

   public void getOutline(Outline var1) {
      Rect var2 = this.getBounds();
      if (!var2.isEmpty()) {
         var1.setRoundRect(var2, this.chipCornerRadius);
      } else {
         var1.setRoundRect(0, 0, this.getIntrinsicWidth(), this.getIntrinsicHeight(), this.chipCornerRadius);
      }

      var1.setAlpha((float)this.getAlpha() / 255.0F);
   }

   public ColorStateList getRippleColor() {
      return this.rippleColor;
   }

   public MotionSpec getShowMotionSpec() {
      return this.showMotionSpec;
   }

   public CharSequence getText() {
      return this.rawText;
   }

   public TextAppearance getTextAppearance() {
      return this.textAppearance;
   }

   public float getTextEndPadding() {
      return this.textEndPadding;
   }

   public float getTextStartPadding() {
      return this.textStartPadding;
   }

   public boolean getUseCompatRipple() {
      return this.useCompatRipple;
   }

   public void invalidateDrawable(Drawable var1) {
      Drawable.Callback var2 = this.getCallback();
      if (var2 != null) {
         var2.invalidateDrawable(this);
      }

   }

   public boolean isCheckable() {
      return this.checkable;
   }

   @Deprecated
   public boolean isCheckedIconEnabled() {
      return this.isCheckedIconVisible();
   }

   public boolean isCheckedIconVisible() {
      return this.checkedIconVisible;
   }

   @Deprecated
   public boolean isChipIconEnabled() {
      return this.isChipIconVisible();
   }

   public boolean isChipIconVisible() {
      return this.chipIconVisible;
   }

   @Deprecated
   public boolean isCloseIconEnabled() {
      return this.isCloseIconVisible();
   }

   public boolean isCloseIconStateful() {
      return isStateful(this.closeIcon);
   }

   public boolean isCloseIconVisible() {
      return this.closeIconVisible;
   }

   public boolean isStateful() {
      boolean var1;
      if (!isStateful(this.chipBackgroundColor) && !isStateful(this.chipStrokeColor) && (!this.useCompatRipple || !isStateful(this.compatRippleColor)) && !isStateful(this.textAppearance) && !this.canShowCheckedIcon() && !isStateful(this.chipIcon) && !isStateful(this.checkedIcon) && !isStateful(this.tint)) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public boolean onLayoutDirectionChanged(int var1) {
      boolean var2 = super.onLayoutDirectionChanged(var1);
      boolean var3 = var2;
      if (this.showsChipIcon()) {
         var3 = var2 | this.chipIcon.setLayoutDirection(var1);
      }

      var2 = var3;
      if (this.showsCheckedIcon()) {
         var2 = var3 | this.checkedIcon.setLayoutDirection(var1);
      }

      var3 = var2;
      if (this.showsCloseIcon()) {
         var3 = var2 | this.closeIcon.setLayoutDirection(var1);
      }

      if (var3) {
         this.invalidateSelf();
      }

      return true;
   }

   protected boolean onLevelChange(int var1) {
      boolean var3 = super.onLevelChange(var1);
      boolean var2 = var3;
      if (this.showsChipIcon()) {
         var2 = var3 | this.chipIcon.setLevel(var1);
      }

      var3 = var2;
      if (this.showsCheckedIcon()) {
         var3 = var2 | this.checkedIcon.setLevel(var1);
      }

      var2 = var3;
      if (this.showsCloseIcon()) {
         var2 = var3 | this.closeIcon.setLevel(var1);
      }

      if (var2) {
         this.invalidateSelf();
      }

      return var2;
   }

   protected void onSizeChange() {
      Delegate var1 = (Delegate)this.delegate.get();
      if (var1 != null) {
         var1.onChipDrawableSizeChange();
      }

   }

   protected boolean onStateChange(int[] var1) {
      return this.onStateChange(var1, this.getCloseIconState());
   }

   public void scheduleDrawable(Drawable var1, Runnable var2, long var3) {
      Drawable.Callback var5 = this.getCallback();
      if (var5 != null) {
         var5.scheduleDrawable(this, var2, var3);
      }

   }

   public void setAlpha(int var1) {
      if (this.alpha != var1) {
         this.alpha = var1;
         this.invalidateSelf();
      }

   }

   public void setCheckable(boolean var1) {
      if (this.checkable != var1) {
         this.checkable = var1;
         float var2 = this.calculateChipIconWidth();
         if (!var1 && this.currentChecked) {
            this.currentChecked = false;
         }

         float var3 = this.calculateChipIconWidth();
         this.invalidateSelf();
         if (var2 != var3) {
            this.onSizeChange();
         }
      }

   }

   public void setCheckableResource(int var1) {
      this.setCheckable(this.context.getResources().getBoolean(var1));
   }

   public void setCheckedIcon(Drawable var1) {
      if (this.checkedIcon != var1) {
         float var3 = this.calculateChipIconWidth();
         this.checkedIcon = var1;
         float var2 = this.calculateChipIconWidth();
         this.unapplyChildDrawable(this.checkedIcon);
         this.applyChildDrawable(this.checkedIcon);
         this.invalidateSelf();
         if (var3 != var2) {
            this.onSizeChange();
         }
      }

   }

   @Deprecated
   public void setCheckedIconEnabled(boolean var1) {
      this.setCheckedIconVisible(var1);
   }

   @Deprecated
   public void setCheckedIconEnabledResource(int var1) {
      this.setCheckedIconVisible(this.context.getResources().getBoolean(var1));
   }

   public void setCheckedIconResource(int var1) {
      this.setCheckedIcon(AppCompatResources.getDrawable(this.context, var1));
   }

   public void setCheckedIconVisible(int var1) {
      this.setCheckedIconVisible(this.context.getResources().getBoolean(var1));
   }

   public void setCheckedIconVisible(boolean var1) {
      if (this.checkedIconVisible != var1) {
         boolean var3 = this.showsCheckedIcon();
         this.checkedIconVisible = var1;
         var1 = this.showsCheckedIcon();
         boolean var2;
         if (var3 != var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            if (var1) {
               this.applyChildDrawable(this.checkedIcon);
            } else {
               this.unapplyChildDrawable(this.checkedIcon);
            }

            this.invalidateSelf();
            this.onSizeChange();
         }
      }

   }

   public void setChipBackgroundColor(ColorStateList var1) {
      if (this.chipBackgroundColor != var1) {
         this.chipBackgroundColor = var1;
         this.onStateChange(this.getState());
      }

   }

   public void setChipBackgroundColorResource(int var1) {
      this.setChipBackgroundColor(AppCompatResources.getColorStateList(this.context, var1));
   }

   public void setChipCornerRadius(float var1) {
      if (this.chipCornerRadius != var1) {
         this.chipCornerRadius = var1;
         this.invalidateSelf();
      }

   }

   public void setChipCornerRadiusResource(int var1) {
      this.setChipCornerRadius(this.context.getResources().getDimension(var1));
   }

   public void setChipEndPadding(float var1) {
      if (this.chipEndPadding != var1) {
         this.chipEndPadding = var1;
         this.invalidateSelf();
         this.onSizeChange();
      }

   }

   public void setChipEndPaddingResource(int var1) {
      this.setChipEndPadding(this.context.getResources().getDimension(var1));
   }

   public void setChipIcon(Drawable var1) {
      Drawable var4 = this.getChipIcon();
      if (var4 != var1) {
         float var3 = this.calculateChipIconWidth();
         if (var1 != null) {
            var1 = DrawableCompat.wrap(var1).mutate();
         } else {
            var1 = null;
         }

         this.chipIcon = var1;
         float var2 = this.calculateChipIconWidth();
         this.unapplyChildDrawable(var4);
         if (this.showsChipIcon()) {
            this.applyChildDrawable(this.chipIcon);
         }

         this.invalidateSelf();
         if (var3 != var2) {
            this.onSizeChange();
         }
      }

   }

   @Deprecated
   public void setChipIconEnabled(boolean var1) {
      this.setChipIconVisible(var1);
   }

   @Deprecated
   public void setChipIconEnabledResource(int var1) {
      this.setChipIconVisible(var1);
   }

   public void setChipIconResource(int var1) {
      this.setChipIcon(AppCompatResources.getDrawable(this.context, var1));
   }

   public void setChipIconSize(float var1) {
      if (this.chipIconSize != var1) {
         float var2 = this.calculateChipIconWidth();
         this.chipIconSize = var1;
         var1 = this.calculateChipIconWidth();
         this.invalidateSelf();
         if (var2 != var1) {
            this.onSizeChange();
         }
      }

   }

   public void setChipIconSizeResource(int var1) {
      this.setChipIconSize(this.context.getResources().getDimension(var1));
   }

   public void setChipIconTint(ColorStateList var1) {
      if (this.chipIconTint != var1) {
         this.chipIconTint = var1;
         if (this.showsChipIcon()) {
            DrawableCompat.setTintList(this.chipIcon, var1);
         }

         this.onStateChange(this.getState());
      }

   }

   public void setChipIconTintResource(int var1) {
      this.setChipIconTint(AppCompatResources.getColorStateList(this.context, var1));
   }

   public void setChipIconVisible(int var1) {
      this.setChipIconVisible(this.context.getResources().getBoolean(var1));
   }

   public void setChipIconVisible(boolean var1) {
      if (this.chipIconVisible != var1) {
         boolean var3 = this.showsChipIcon();
         this.chipIconVisible = var1;
         var1 = this.showsChipIcon();
         boolean var2;
         if (var3 != var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            if (var1) {
               this.applyChildDrawable(this.chipIcon);
            } else {
               this.unapplyChildDrawable(this.chipIcon);
            }

            this.invalidateSelf();
            this.onSizeChange();
         }
      }

   }

   public void setChipMinHeight(float var1) {
      if (this.chipMinHeight != var1) {
         this.chipMinHeight = var1;
         this.invalidateSelf();
         this.onSizeChange();
      }

   }

   public void setChipMinHeightResource(int var1) {
      this.setChipMinHeight(this.context.getResources().getDimension(var1));
   }

   public void setChipStartPadding(float var1) {
      if (this.chipStartPadding != var1) {
         this.chipStartPadding = var1;
         this.invalidateSelf();
         this.onSizeChange();
      }

   }

   public void setChipStartPaddingResource(int var1) {
      this.setChipStartPadding(this.context.getResources().getDimension(var1));
   }

   public void setChipStrokeColor(ColorStateList var1) {
      if (this.chipStrokeColor != var1) {
         this.chipStrokeColor = var1;
         this.onStateChange(this.getState());
      }

   }

   public void setChipStrokeColorResource(int var1) {
      this.setChipStrokeColor(AppCompatResources.getColorStateList(this.context, var1));
   }

   public void setChipStrokeWidth(float var1) {
      if (this.chipStrokeWidth != var1) {
         this.chipStrokeWidth = var1;
         this.chipPaint.setStrokeWidth(var1);
         this.invalidateSelf();
      }

   }

   public void setChipStrokeWidthResource(int var1) {
      this.setChipStrokeWidth(this.context.getResources().getDimension(var1));
   }

   public void setCloseIcon(Drawable var1) {
      Drawable var4 = this.getCloseIcon();
      if (var4 != var1) {
         float var2 = this.calculateCloseIconWidth();
         if (var1 != null) {
            var1 = DrawableCompat.wrap(var1).mutate();
         } else {
            var1 = null;
         }

         this.closeIcon = var1;
         float var3 = this.calculateCloseIconWidth();
         this.unapplyChildDrawable(var4);
         if (this.showsCloseIcon()) {
            this.applyChildDrawable(this.closeIcon);
         }

         this.invalidateSelf();
         if (var2 != var3) {
            this.onSizeChange();
         }
      }

   }

   public void setCloseIconContentDescription(CharSequence var1) {
      if (this.closeIconContentDescription != var1) {
         this.closeIconContentDescription = BidiFormatter.getInstance().unicodeWrap(var1);
         this.invalidateSelf();
      }

   }

   @Deprecated
   public void setCloseIconEnabled(boolean var1) {
      this.setCloseIconVisible(var1);
   }

   @Deprecated
   public void setCloseIconEnabledResource(int var1) {
      this.setCloseIconVisible(var1);
   }

   public void setCloseIconEndPadding(float var1) {
      if (this.closeIconEndPadding != var1) {
         this.closeIconEndPadding = var1;
         this.invalidateSelf();
         if (this.showsCloseIcon()) {
            this.onSizeChange();
         }
      }

   }

   public void setCloseIconEndPaddingResource(int var1) {
      this.setCloseIconEndPadding(this.context.getResources().getDimension(var1));
   }

   public void setCloseIconResource(int var1) {
      this.setCloseIcon(AppCompatResources.getDrawable(this.context, var1));
   }

   public void setCloseIconSize(float var1) {
      if (this.closeIconSize != var1) {
         this.closeIconSize = var1;
         this.invalidateSelf();
         if (this.showsCloseIcon()) {
            this.onSizeChange();
         }
      }

   }

   public void setCloseIconSizeResource(int var1) {
      this.setCloseIconSize(this.context.getResources().getDimension(var1));
   }

   public void setCloseIconStartPadding(float var1) {
      if (this.closeIconStartPadding != var1) {
         this.closeIconStartPadding = var1;
         this.invalidateSelf();
         if (this.showsCloseIcon()) {
            this.onSizeChange();
         }
      }

   }

   public void setCloseIconStartPaddingResource(int var1) {
      this.setCloseIconStartPadding(this.context.getResources().getDimension(var1));
   }

   public boolean setCloseIconState(int[] var1) {
      if (!Arrays.equals(this.closeIconStateSet, var1)) {
         this.closeIconStateSet = var1;
         if (this.showsCloseIcon()) {
            return this.onStateChange(this.getState(), var1);
         }
      }

      return false;
   }

   public void setCloseIconTint(ColorStateList var1) {
      if (this.closeIconTint != var1) {
         this.closeIconTint = var1;
         if (this.showsCloseIcon()) {
            DrawableCompat.setTintList(this.closeIcon, var1);
         }

         this.onStateChange(this.getState());
      }

   }

   public void setCloseIconTintResource(int var1) {
      this.setCloseIconTint(AppCompatResources.getColorStateList(this.context, var1));
   }

   public void setCloseIconVisible(int var1) {
      this.setCloseIconVisible(this.context.getResources().getBoolean(var1));
   }

   public void setCloseIconVisible(boolean var1) {
      if (this.closeIconVisible != var1) {
         boolean var3 = this.showsCloseIcon();
         this.closeIconVisible = var1;
         var1 = this.showsCloseIcon();
         boolean var2;
         if (var3 != var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            if (var1) {
               this.applyChildDrawable(this.closeIcon);
            } else {
               this.unapplyChildDrawable(this.closeIcon);
            }

            this.invalidateSelf();
            this.onSizeChange();
         }
      }

   }

   public void setColorFilter(ColorFilter var1) {
      if (this.colorFilter != var1) {
         this.colorFilter = var1;
         this.invalidateSelf();
      }

   }

   public void setDelegate(Delegate var1) {
      this.delegate = new WeakReference(var1);
   }

   public void setEllipsize(TextUtils.TruncateAt var1) {
      this.truncateAt = var1;
   }

   public void setHideMotionSpec(MotionSpec var1) {
      this.hideMotionSpec = var1;
   }

   public void setHideMotionSpecResource(int var1) {
      this.setHideMotionSpec(MotionSpec.createFromResource(this.context, var1));
   }

   public void setIconEndPadding(float var1) {
      if (this.iconEndPadding != var1) {
         float var2 = this.calculateChipIconWidth();
         this.iconEndPadding = var1;
         var1 = this.calculateChipIconWidth();
         this.invalidateSelf();
         if (var2 != var1) {
            this.onSizeChange();
         }
      }

   }

   public void setIconEndPaddingResource(int var1) {
      this.setIconEndPadding(this.context.getResources().getDimension(var1));
   }

   public void setIconStartPadding(float var1) {
      if (this.iconStartPadding != var1) {
         float var2 = this.calculateChipIconWidth();
         this.iconStartPadding = var1;
         var1 = this.calculateChipIconWidth();
         this.invalidateSelf();
         if (var2 != var1) {
            this.onSizeChange();
         }
      }

   }

   public void setIconStartPaddingResource(int var1) {
      this.setIconStartPadding(this.context.getResources().getDimension(var1));
   }

   public void setMaxWidth(int var1) {
      this.maxWidth = var1;
   }

   public void setRippleColor(ColorStateList var1) {
      if (this.rippleColor != var1) {
         this.rippleColor = var1;
         this.updateCompatRippleColor();
         this.onStateChange(this.getState());
      }

   }

   public void setRippleColorResource(int var1) {
      this.setRippleColor(AppCompatResources.getColorStateList(this.context, var1));
   }

   void setShouldDrawText(boolean var1) {
      this.shouldDrawText = var1;
   }

   public void setShowMotionSpec(MotionSpec var1) {
      this.showMotionSpec = var1;
   }

   public void setShowMotionSpecResource(int var1) {
      this.setShowMotionSpec(MotionSpec.createFromResource(this.context, var1));
   }

   public void setText(CharSequence var1) {
      Object var2 = var1;
      if (var1 == null) {
         var2 = "";
      }

      if (this.rawText != var2) {
         this.rawText = (CharSequence)var2;
         this.unicodeWrappedText = BidiFormatter.getInstance().unicodeWrap((CharSequence)var2);
         this.textWidthDirty = true;
         this.invalidateSelf();
         this.onSizeChange();
      }

   }

   public void setTextAppearance(TextAppearance var1) {
      if (this.textAppearance != var1) {
         this.textAppearance = var1;
         if (var1 != null) {
            var1.updateMeasureState(this.context, this.textPaint, this.fontCallback);
            this.textWidthDirty = true;
         }

         this.onStateChange(this.getState());
         this.onSizeChange();
      }

   }

   public void setTextAppearanceResource(int var1) {
      this.setTextAppearance(new TextAppearance(this.context, var1));
   }

   public void setTextEndPadding(float var1) {
      if (this.textEndPadding != var1) {
         this.textEndPadding = var1;
         this.invalidateSelf();
         this.onSizeChange();
      }

   }

   public void setTextEndPaddingResource(int var1) {
      this.setTextEndPadding(this.context.getResources().getDimension(var1));
   }

   public void setTextResource(int var1) {
      this.setText(this.context.getResources().getString(var1));
   }

   public void setTextStartPadding(float var1) {
      if (this.textStartPadding != var1) {
         this.textStartPadding = var1;
         this.invalidateSelf();
         this.onSizeChange();
      }

   }

   public void setTextStartPaddingResource(int var1) {
      this.setTextStartPadding(this.context.getResources().getDimension(var1));
   }

   public void setTintList(ColorStateList var1) {
      if (this.tint != var1) {
         this.tint = var1;
         this.onStateChange(this.getState());
      }

   }

   public void setTintMode(PorterDuff.Mode var1) {
      if (this.tintMode != var1) {
         this.tintMode = var1;
         this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, var1);
         this.invalidateSelf();
      }

   }

   public void setUseCompatRipple(boolean var1) {
      if (this.useCompatRipple != var1) {
         this.useCompatRipple = var1;
         this.updateCompatRippleColor();
         this.onStateChange(this.getState());
      }

   }

   public boolean setVisible(boolean var1, boolean var2) {
      boolean var4 = super.setVisible(var1, var2);
      boolean var3 = var4;
      if (this.showsChipIcon()) {
         var3 = var4 | this.chipIcon.setVisible(var1, var2);
      }

      var4 = var3;
      if (this.showsCheckedIcon()) {
         var4 = var3 | this.checkedIcon.setVisible(var1, var2);
      }

      var3 = var4;
      if (this.showsCloseIcon()) {
         var3 = var4 | this.closeIcon.setVisible(var1, var2);
      }

      if (var3) {
         this.invalidateSelf();
      }

      return var3;
   }

   boolean shouldDrawText() {
      return this.shouldDrawText;
   }

   public void unscheduleDrawable(Drawable var1, Runnable var2) {
      Drawable.Callback var3 = this.getCallback();
      if (var3 != null) {
         var3.unscheduleDrawable(this, var2);
      }

   }

   public interface Delegate {
      void onChipDrawableSizeChange();
   }
}
