package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.os.Build.VERSION;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

class AppCompatTextHelper {
   private static final int MONOSPACE = 3;
   private static final int SANS = 1;
   private static final int SERIF = 2;
   private static final int TEXT_FONT_WEIGHT_UNSPECIFIED = -1;
   private boolean mAsyncFontPending;
   private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
   private TintInfo mDrawableBottomTint;
   private TintInfo mDrawableEndTint;
   private TintInfo mDrawableLeftTint;
   private TintInfo mDrawableRightTint;
   private TintInfo mDrawableStartTint;
   private TintInfo mDrawableTint;
   private TintInfo mDrawableTopTint;
   private Typeface mFontTypeface;
   private int mFontWeight = -1;
   private int mStyle = 0;
   private final TextView mView;

   AppCompatTextHelper(TextView var1) {
      this.mView = var1;
      this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(var1);
   }

   private void applyCompoundDrawableTint(Drawable var1, TintInfo var2) {
      if (var1 != null && var2 != null) {
         AppCompatDrawableManager.tintDrawable(var1, var2, this.mView.getDrawableState());
      }

   }

   private static TintInfo createTintInfo(Context var0, AppCompatDrawableManager var1, int var2) {
      ColorStateList var3 = var1.getTintList(var0, var2);
      if (var3 != null) {
         TintInfo var4 = new TintInfo();
         var4.mHasTintList = true;
         var4.mTintList = var3;
         return var4;
      } else {
         return null;
      }
   }

   private void setCompoundDrawables(Drawable var1, Drawable var2, Drawable var3, Drawable var4, Drawable var5, Drawable var6) {
      TextView var7;
      if (VERSION.SDK_INT < 17 || var5 == null && var6 == null) {
         if (var1 != null || var2 != null || var3 != null || var4 != null) {
            Drawable[] var10;
            if (VERSION.SDK_INT >= 17) {
               var10 = this.mView.getCompoundDrawablesRelative();
               var5 = var10[0];
               if (var5 != null || var10[2] != null) {
                  var7 = this.mView;
                  if (var2 == null) {
                     var2 = var10[1];
                  }

                  var3 = var10[2];
                  if (var4 == null) {
                     var4 = var10[3];
                  }

                  var7.setCompoundDrawablesRelativeWithIntrinsicBounds(var5, var2, var3, var4);
                  return;
               }
            }

            var10 = this.mView.getCompoundDrawables();
            TextView var9 = this.mView;
            if (var1 == null) {
               var1 = var10[0];
            }

            if (var2 == null) {
               var2 = var10[1];
            }

            if (var3 == null) {
               var3 = var10[2];
            }

            if (var4 == null) {
               var4 = var10[3];
            }

            var9.setCompoundDrawablesWithIntrinsicBounds(var1, var2, var3, var4);
         }
      } else {
         Drawable[] var8 = this.mView.getCompoundDrawablesRelative();
         var7 = this.mView;
         if (var5 == null) {
            var5 = var8[0];
         }

         if (var2 == null) {
            var2 = var8[1];
         }

         if (var6 == null) {
            var6 = var8[2];
         }

         if (var4 == null) {
            var4 = var8[3];
         }

         var7.setCompoundDrawablesRelativeWithIntrinsicBounds(var5, var2, var6, var4);
      }

   }

   private void setCompoundTints() {
      TintInfo var1 = this.mDrawableTint;
      this.mDrawableLeftTint = var1;
      this.mDrawableTopTint = var1;
      this.mDrawableRightTint = var1;
      this.mDrawableBottomTint = var1;
      this.mDrawableStartTint = var1;
      this.mDrawableEndTint = var1;
   }

   private void setTextSizeInternal(int var1, float var2) {
      this.mAutoSizeTextHelper.setTextSizeInternal(var1, var2);
   }

   private void updateTypefaceAndStyle(Context var1, TintTypedArray var2) {
      this.mStyle = var2.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
      int var3 = VERSION.SDK_INT;
      boolean var7 = false;
      if (var3 >= 28) {
         var3 = var2.getInt(R.styleable.TextAppearance_android_textFontWeight, -1);
         this.mFontWeight = var3;
         if (var3 != -1) {
            this.mStyle = this.mStyle & 2 | 0;
         }
      }

      if (!var2.hasValue(R.styleable.TextAppearance_android_fontFamily) && !var2.hasValue(R.styleable.TextAppearance_fontFamily)) {
         if (var2.hasValue(R.styleable.TextAppearance_android_typeface)) {
            this.mAsyncFontPending = false;
            var3 = var2.getInt(R.styleable.TextAppearance_android_typeface, 1);
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     this.mFontTypeface = Typeface.MONOSPACE;
                  }
               } else {
                  this.mFontTypeface = Typeface.SERIF;
               }
            } else {
               this.mFontTypeface = Typeface.SANS_SERIF;
            }
         }

      } else {
         this.mFontTypeface = null;
         if (var2.hasValue(R.styleable.TextAppearance_fontFamily)) {
            var3 = R.styleable.TextAppearance_fontFamily;
         } else {
            var3 = R.styleable.TextAppearance_android_fontFamily;
         }

         int var4 = this.mFontWeight;
         int var5 = this.mStyle;
         boolean var6;
         Typeface var15;
         if (!var1.isRestricted()) {
            label127: {
               ResourcesCompat.FontCallback var14 = new ResourcesCompat.FontCallback(this, var4, var5, new WeakReference(this.mView)) {
                  final AppCompatTextHelper this$0;
                  final int val$fontWeight;
                  final int val$style;
                  final WeakReference val$textViewWeak;

                  {
                     this.this$0 = var1;
                     this.val$fontWeight = var2;
                     this.val$style = var3;
                     this.val$textViewWeak = var4;
                  }

                  public void onFontRetrievalFailed(int var1) {
                  }

                  public void onFontRetrieved(Typeface var1) {
                     Typeface var4 = var1;
                     if (VERSION.SDK_INT >= 28) {
                        int var2 = this.val$fontWeight;
                        var4 = var1;
                        if (var2 != -1) {
                           boolean var3;
                           if ((this.val$style & 2) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var4 = Typeface.create(var1, var2, var3);
                        }
                     }

                     this.this$0.onAsyncTypefaceReceived(this.val$textViewWeak, var4);
                  }
               };

               boolean var10001;
               try {
                  var15 = var2.getFont(var3, this.mStyle, var14);
               } catch (Resources.NotFoundException | UnsupportedOperationException var13) {
                  var10001 = false;
                  break label127;
               }

               if (var15 != null) {
                  label107: {
                     label124: {
                        label105: {
                           label104: {
                              try {
                                 if (VERSION.SDK_INT < 28 || this.mFontWeight == -1) {
                                    break label124;
                                 }

                                 var15 = Typeface.create(var15, 0);
                                 var4 = this.mFontWeight;
                                 if ((this.mStyle & 2) != 0) {
                                    break label104;
                                 }
                              } catch (Resources.NotFoundException | UnsupportedOperationException var12) {
                                 var10001 = false;
                                 break label127;
                              }

                              var6 = false;
                              break label105;
                           }

                           var6 = true;
                        }

                        try {
                           this.mFontTypeface = Typeface.create(var15, var4, var6);
                           break label107;
                        } catch (Resources.NotFoundException | UnsupportedOperationException var11) {
                           var10001 = false;
                           break label127;
                        }
                     }

                     try {
                        this.mFontTypeface = var15;
                     } catch (Resources.NotFoundException | UnsupportedOperationException var10) {
                        var10001 = false;
                        break label127;
                     }
                  }
               }

               label88: {
                  label87: {
                     try {
                        if (this.mFontTypeface == null) {
                           break label87;
                        }
                     } catch (Resources.NotFoundException | UnsupportedOperationException var9) {
                        var10001 = false;
                        break label127;
                     }

                     var6 = false;
                     break label88;
                  }

                  var6 = true;
               }

               try {
                  this.mAsyncFontPending = var6;
               } catch (Resources.NotFoundException | UnsupportedOperationException var8) {
                  var10001 = false;
               }
            }
         }

         if (this.mFontTypeface == null) {
            String var16 = var2.getString(var3);
            if (var16 != null) {
               if (VERSION.SDK_INT >= 28 && this.mFontWeight != -1) {
                  var15 = Typeface.create(var16, 0);
                  var3 = this.mFontWeight;
                  var6 = var7;
                  if ((this.mStyle & 2) != 0) {
                     var6 = true;
                  }

                  this.mFontTypeface = Typeface.create(var15, var3, var6);
               } else {
                  this.mFontTypeface = Typeface.create(var16, this.mStyle);
               }
            }
         }

      }
   }

   void applyCompoundDrawablesTints() {
      Drawable[] var1;
      if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
         var1 = this.mView.getCompoundDrawables();
         this.applyCompoundDrawableTint(var1[0], this.mDrawableLeftTint);
         this.applyCompoundDrawableTint(var1[1], this.mDrawableTopTint);
         this.applyCompoundDrawableTint(var1[2], this.mDrawableRightTint);
         this.applyCompoundDrawableTint(var1[3], this.mDrawableBottomTint);
      }

      if (VERSION.SDK_INT >= 17 && (this.mDrawableStartTint != null || this.mDrawableEndTint != null)) {
         var1 = this.mView.getCompoundDrawablesRelative();
         this.applyCompoundDrawableTint(var1[0], this.mDrawableStartTint);
         this.applyCompoundDrawableTint(var1[2], this.mDrawableEndTint);
      }

   }

   void autoSizeText() {
      this.mAutoSizeTextHelper.autoSizeText();
   }

   int getAutoSizeMaxTextSize() {
      return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
   }

   int getAutoSizeMinTextSize() {
      return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
   }

   int getAutoSizeStepGranularity() {
      return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
   }

   int[] getAutoSizeTextAvailableSizes() {
      return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
   }

   int getAutoSizeTextType() {
      return this.mAutoSizeTextHelper.getAutoSizeTextType();
   }

   ColorStateList getCompoundDrawableTintList() {
      TintInfo var1 = this.mDrawableTint;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.mTintList;
      } else {
         var2 = null;
      }

      return var2;
   }

   PorterDuff.Mode getCompoundDrawableTintMode() {
      TintInfo var1 = this.mDrawableTint;
      PorterDuff.Mode var2;
      if (var1 != null) {
         var2 = var1.mTintMode;
      } else {
         var2 = null;
      }

      return var2;
   }

   boolean isAutoSizeEnabled() {
      return this.mAutoSizeTextHelper.isAutoSizeEnabled();
   }

   void loadFromAttributes(AttributeSet var1, int var2) {
      Context var15 = this.mView.getContext();
      AppCompatDrawableManager var16 = AppCompatDrawableManager.get();
      TintTypedArray var7 = TintTypedArray.obtainStyledAttributes(var15, var1, R.styleable.AppCompatTextHelper, var2, 0);
      TextView var8 = this.mView;
      ViewCompat.saveAttributeDataForStyleable(var8, var8.getContext(), R.styleable.AppCompatTextHelper, var1, var7.getWrappedTypeArray(), var2, 0);
      int var3 = var7.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
      if (var7.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
         this.mDrawableLeftTint = createTintInfo(var15, var16, var7.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
      }

      if (var7.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
         this.mDrawableTopTint = createTintInfo(var15, var16, var7.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
      }

      if (var7.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
         this.mDrawableRightTint = createTintInfo(var15, var16, var7.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
      }

      if (var7.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
         this.mDrawableBottomTint = createTintInfo(var15, var16, var7.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
      }

      if (VERSION.SDK_INT >= 17) {
         if (var7.hasValue(R.styleable.AppCompatTextHelper_android_drawableStart)) {
            this.mDrawableStartTint = createTintInfo(var15, var16, var7.getResourceId(R.styleable.AppCompatTextHelper_android_drawableStart, 0));
         }

         if (var7.hasValue(R.styleable.AppCompatTextHelper_android_drawableEnd)) {
            this.mDrawableEndTint = createTintInfo(var15, var16, var7.getResourceId(R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
         }
      }

      var7.recycle();
      boolean var6 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
      boolean var5;
      String var9;
      String var10;
      ColorStateList var12;
      boolean var21;
      ColorStateList var22;
      ColorStateList var23;
      ColorStateList var26;
      if (var3 != -1) {
         TintTypedArray var11 = TintTypedArray.obtainStyledAttributes(var15, var3, R.styleable.TextAppearance);
         if (!var6 && var11.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            var5 = var11.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
            var21 = true;
         } else {
            var5 = false;
            var21 = false;
         }

         this.updateTypefaceAndStyle(var15, var11);
         if (VERSION.SDK_INT < 23) {
            if (var11.hasValue(R.styleable.TextAppearance_android_textColor)) {
               var12 = var11.getColorStateList(R.styleable.TextAppearance_android_textColor);
            } else {
               var12 = null;
            }

            if (var11.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
               var22 = var11.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
            } else {
               var22 = null;
            }

            if (var11.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
               var23 = var11.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
            } else {
               var23 = null;
            }
         } else {
            var22 = null;
            var23 = null;
            var12 = null;
         }

         if (var11.hasValue(R.styleable.TextAppearance_textLocale)) {
            var10 = var11.getString(R.styleable.TextAppearance_textLocale);
         } else {
            var10 = null;
         }

         if (VERSION.SDK_INT >= 26 && var11.hasValue(R.styleable.TextAppearance_fontVariationSettings)) {
            var9 = var11.getString(R.styleable.TextAppearance_fontVariationSettings);
         } else {
            var9 = null;
         }

         var11.recycle();
         var26 = var23;
         var23 = var12;
      } else {
         var5 = false;
         var21 = false;
         var22 = null;
         var9 = null;
         var26 = null;
         var10 = null;
         var23 = null;
      }

      TintTypedArray var17 = TintTypedArray.obtainStyledAttributes(var15, var1, R.styleable.TextAppearance, var2, 0);
      if (!var6 && var17.hasValue(R.styleable.TextAppearance_textAllCaps)) {
         var5 = var17.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
         var21 = true;
      }

      ColorStateList var13 = var22;
      ColorStateList var14 = var26;
      var12 = var23;
      if (VERSION.SDK_INT < 23) {
         if (var17.hasValue(R.styleable.TextAppearance_android_textColor)) {
            var23 = var17.getColorStateList(R.styleable.TextAppearance_android_textColor);
         }

         if (var17.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
            var22 = var17.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
         }

         var13 = var22;
         var14 = var26;
         var12 = var23;
         if (var17.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
            var14 = var17.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
            var12 = var23;
            var13 = var22;
         }
      }

      if (var17.hasValue(R.styleable.TextAppearance_textLocale)) {
         var10 = var17.getString(R.styleable.TextAppearance_textLocale);
      }

      String var24 = var9;
      if (VERSION.SDK_INT >= 26) {
         var24 = var9;
         if (var17.hasValue(R.styleable.TextAppearance_fontVariationSettings)) {
            var24 = var17.getString(R.styleable.TextAppearance_fontVariationSettings);
         }
      }

      if (VERSION.SDK_INT >= 28 && var17.hasValue(R.styleable.TextAppearance_android_textSize) && var17.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0) {
         this.mView.setTextSize(0, 0.0F);
      }

      this.updateTypefaceAndStyle(var15, var17);
      var17.recycle();
      if (var12 != null) {
         this.mView.setTextColor(var12);
      }

      if (var13 != null) {
         this.mView.setHintTextColor(var13);
      }

      if (var14 != null) {
         this.mView.setLinkTextColor(var14);
      }

      if (!var6 && var21) {
         this.setAllCaps(var5);
      }

      Typeface var27 = this.mFontTypeface;
      if (var27 != null) {
         if (this.mFontWeight == -1) {
            this.mView.setTypeface(var27, this.mStyle);
         } else {
            this.mView.setTypeface(var27);
         }
      }

      if (var24 != null) {
         this.mView.setFontVariationSettings(var24);
      }

      if (var10 != null) {
         if (VERSION.SDK_INT >= 24) {
            this.mView.setTextLocales(LocaleList.forLanguageTags(var10));
         } else if (VERSION.SDK_INT >= 21) {
            var24 = var10.substring(0, var10.indexOf(44));
            this.mView.setTextLocale(Locale.forLanguageTag(var24));
         }
      }

      this.mAutoSizeTextHelper.loadFromAttributes(var1, var2);
      if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
         int[] var28 = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
         if (var28.length > 0) {
            if ((float)this.mView.getAutoSizeStepGranularity() != -1.0F) {
               this.mView.setAutoSizeTextTypeUniformWithConfiguration(this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
            } else {
               this.mView.setAutoSizeTextTypeUniformWithPresetSizes(var28, 0);
            }
         }
      }

      TintTypedArray var33 = TintTypedArray.obtainStyledAttributes(var15, var1, R.styleable.AppCompatTextView);
      var2 = var33.getResourceId(R.styleable.AppCompatTextView_drawableLeftCompat, -1);
      Drawable var18;
      if (var2 != -1) {
         var18 = var16.getDrawable(var15, var2);
      } else {
         var18 = null;
      }

      var2 = var33.getResourceId(R.styleable.AppCompatTextView_drawableTopCompat, -1);
      Drawable var29;
      if (var2 != -1) {
         var29 = var16.getDrawable(var15, var2);
      } else {
         var29 = null;
      }

      var2 = var33.getResourceId(R.styleable.AppCompatTextView_drawableRightCompat, -1);
      Drawable var30;
      if (var2 != -1) {
         var30 = var16.getDrawable(var15, var2);
      } else {
         var30 = null;
      }

      var2 = var33.getResourceId(R.styleable.AppCompatTextView_drawableBottomCompat, -1);
      Drawable var25;
      if (var2 != -1) {
         var25 = var16.getDrawable(var15, var2);
      } else {
         var25 = null;
      }

      var2 = var33.getResourceId(R.styleable.AppCompatTextView_drawableStartCompat, -1);
      Drawable var31;
      if (var2 != -1) {
         var31 = var16.getDrawable(var15, var2);
      } else {
         var31 = null;
      }

      var2 = var33.getResourceId(R.styleable.AppCompatTextView_drawableEndCompat, -1);
      Drawable var32;
      if (var2 != -1) {
         var32 = var16.getDrawable(var15, var2);
      } else {
         var32 = null;
      }

      this.setCompoundDrawables(var18, var29, var30, var25, var31, var32);
      if (var33.hasValue(R.styleable.AppCompatTextView_drawableTint)) {
         ColorStateList var19 = var33.getColorStateList(R.styleable.AppCompatTextView_drawableTint);
         TextViewCompat.setCompoundDrawableTintList(this.mView, var19);
      }

      if (var33.hasValue(R.styleable.AppCompatTextView_drawableTintMode)) {
         PorterDuff.Mode var20 = DrawableUtils.parseTintMode(var33.getInt(R.styleable.AppCompatTextView_drawableTintMode, -1), (PorterDuff.Mode)null);
         TextViewCompat.setCompoundDrawableTintMode(this.mView, var20);
      }

      var3 = var33.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
      var2 = var33.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
      int var4 = var33.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, -1);
      var33.recycle();
      if (var3 != -1) {
         TextViewCompat.setFirstBaselineToTopHeight(this.mView, var3);
      }

      if (var2 != -1) {
         TextViewCompat.setLastBaselineToBottomHeight(this.mView, var2);
      }

      if (var4 != -1) {
         TextViewCompat.setLineHeight(this.mView, var4);
      }

   }

   void onAsyncTypefaceReceived(WeakReference var1, Typeface var2) {
      if (this.mAsyncFontPending) {
         this.mFontTypeface = var2;
         TextView var3 = (TextView)var1.get();
         if (var3 != null) {
            if (ViewCompat.isAttachedToWindow(var3)) {
               var3.post(new Runnable(this, var3, var2, this.mStyle) {
                  final AppCompatTextHelper this$0;
                  final int val$style;
                  final TextView val$textView;
                  final Typeface val$typeface;

                  {
                     this.this$0 = var1;
                     this.val$textView = var2;
                     this.val$typeface = var3;
                     this.val$style = var4;
                  }

                  public void run() {
                     this.val$textView.setTypeface(this.val$typeface, this.val$style);
                  }
               });
            } else {
               var3.setTypeface(var2, this.mStyle);
            }
         }
      }

   }

   void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
         this.autoSizeText();
      }

   }

   void onSetCompoundDrawables() {
      this.applyCompoundDrawablesTints();
   }

   void onSetTextAppearance(Context var1, int var2) {
      TintTypedArray var3 = TintTypedArray.obtainStyledAttributes(var1, var2, R.styleable.TextAppearance);
      if (var3.hasValue(R.styleable.TextAppearance_textAllCaps)) {
         this.setAllCaps(var3.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
      }

      if (VERSION.SDK_INT < 23) {
         ColorStateList var4;
         if (var3.hasValue(R.styleable.TextAppearance_android_textColor)) {
            var4 = var3.getColorStateList(R.styleable.TextAppearance_android_textColor);
            if (var4 != null) {
               this.mView.setTextColor(var4);
            }
         }

         if (var3.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
            var4 = var3.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
            if (var4 != null) {
               this.mView.setLinkTextColor(var4);
            }
         }

         if (var3.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
            var4 = var3.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
            if (var4 != null) {
               this.mView.setHintTextColor(var4);
            }
         }
      }

      if (var3.hasValue(R.styleable.TextAppearance_android_textSize) && var3.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0) {
         this.mView.setTextSize(0, 0.0F);
      }

      this.updateTypefaceAndStyle(var1, var3);
      if (VERSION.SDK_INT >= 26 && var3.hasValue(R.styleable.TextAppearance_fontVariationSettings)) {
         String var5 = var3.getString(R.styleable.TextAppearance_fontVariationSettings);
         if (var5 != null) {
            this.mView.setFontVariationSettings(var5);
         }
      }

      var3.recycle();
      Typeface var6 = this.mFontTypeface;
      if (var6 != null) {
         this.mView.setTypeface(var6, this.mStyle);
      }

   }

   void populateSurroundingTextIfNeeded(TextView var1, InputConnection var2, EditorInfo var3) {
      if (VERSION.SDK_INT < 30 && var2 != null) {
         EditorInfoCompat.setInitialSurroundingText(var3, var1.getText());
      }

   }

   void setAllCaps(boolean var1) {
      this.mView.setAllCaps(var1);
   }

   void setAutoSizeTextTypeUniformWithConfiguration(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(var1, var2, var3, var4);
   }

   void setAutoSizeTextTypeUniformWithPresetSizes(int[] var1, int var2) throws IllegalArgumentException {
      this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(var1, var2);
   }

   void setAutoSizeTextTypeWithDefaults(int var1) {
      this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(var1);
   }

   void setCompoundDrawableTintList(ColorStateList var1) {
      if (this.mDrawableTint == null) {
         this.mDrawableTint = new TintInfo();
      }

      this.mDrawableTint.mTintList = var1;
      TintInfo var3 = this.mDrawableTint;
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      var3.mHasTintList = var2;
      this.setCompoundTints();
   }

   void setCompoundDrawableTintMode(PorterDuff.Mode var1) {
      if (this.mDrawableTint == null) {
         this.mDrawableTint = new TintInfo();
      }

      this.mDrawableTint.mTintMode = var1;
      TintInfo var3 = this.mDrawableTint;
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      var3.mHasTintMode = var2;
      this.setCompoundTints();
   }

   void setTextSize(int var1, float var2) {
      if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !this.isAutoSizeEnabled()) {
         this.setTextSizeInternal(var1, var2);
      }

   }
}
