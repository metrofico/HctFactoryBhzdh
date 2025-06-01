package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

class AppCompatTextViewAutoSizeHelper {
   private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
   private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
   private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
   private static final String TAG = "ACTVAutoSizeHelper";
   private static final RectF TEMP_RECTF = new RectF();
   static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0F;
   private static final int VERY_WIDE = 1048576;
   private static ConcurrentHashMap sTextViewFieldByNameCache = new ConcurrentHashMap();
   private static ConcurrentHashMap sTextViewMethodByNameCache = new ConcurrentHashMap();
   private float mAutoSizeMaxTextSizeInPx = -1.0F;
   private float mAutoSizeMinTextSizeInPx = -1.0F;
   private float mAutoSizeStepGranularityInPx = -1.0F;
   private int[] mAutoSizeTextSizesInPx = new int[0];
   private int mAutoSizeTextType = 0;
   private final Context mContext;
   private boolean mHasPresetAutoSizeValues = false;
   private final Impl mImpl;
   private boolean mNeedsAutoSizeText = false;
   private TextPaint mTempTextPaint;
   private final TextView mTextView;

   AppCompatTextViewAutoSizeHelper(TextView var1) {
      this.mTextView = var1;
      this.mContext = var1.getContext();
      if (VERSION.SDK_INT >= 29) {
         this.mImpl = new Impl29();
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new Impl23();
      } else {
         this.mImpl = new Impl();
      }

   }

   private static Object accessAndReturnWithDefault(Object param0, String param1, Object param2) {
      // $FF: Couldn't be decompiled
   }

   private int[] cleanupAutoSizePresetSizes(int[] var1) {
      int var4 = var1.length;
      if (var4 == 0) {
         return var1;
      } else {
         Arrays.sort(var1);
         ArrayList var6 = new ArrayList();
         byte var3 = 0;

         int var2;
         for(var2 = 0; var2 < var4; ++var2) {
            int var5 = var1[var2];
            if (var5 > 0 && Collections.binarySearch(var6, var5) < 0) {
               var6.add(var5);
            }
         }

         if (var4 == var6.size()) {
            return var1;
         } else {
            var4 = var6.size();
            var1 = new int[var4];

            for(var2 = var3; var2 < var4; ++var2) {
               var1[var2] = (Integer)var6.get(var2);
            }

            return var1;
         }
      }
   }

   private void clearAutoSizeConfiguration() {
      this.mAutoSizeTextType = 0;
      this.mAutoSizeMinTextSizeInPx = -1.0F;
      this.mAutoSizeMaxTextSizeInPx = -1.0F;
      this.mAutoSizeStepGranularityInPx = -1.0F;
      this.mAutoSizeTextSizesInPx = new int[0];
      this.mNeedsAutoSizeText = false;
   }

   private StaticLayout createStaticLayoutForMeasuring(CharSequence var1, Layout.Alignment var2, int var3, int var4) {
      StaticLayout.Builder var6 = Builder.obtain(var1, 0, var1.length(), this.mTempTextPaint, var3);
      StaticLayout.Builder var7 = var6.setAlignment(var2).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency());
      var3 = var4;
      if (var4 == -1) {
         var3 = Integer.MAX_VALUE;
      }

      var7.setMaxLines(var3);

      try {
         this.mImpl.computeAndSetTextDirection(var6, this.mTextView);
      } catch (ClassCastException var5) {
         Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
      }

      return var6.build();
   }

   private StaticLayout createStaticLayoutForMeasuringPre16(CharSequence var1, Layout.Alignment var2, int var3) {
      float var4 = (Float)accessAndReturnWithDefault(this.mTextView, "mSpacingMult", 1.0F);
      float var5 = (Float)accessAndReturnWithDefault(this.mTextView, "mSpacingAdd", 0.0F);
      boolean var6 = (Boolean)accessAndReturnWithDefault(this.mTextView, "mIncludePad", true);
      return new StaticLayout(var1, this.mTempTextPaint, var3, var2, var4, var5, var6);
   }

   private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence var1, Layout.Alignment var2, int var3) {
      float var5 = this.mTextView.getLineSpacingMultiplier();
      float var4 = this.mTextView.getLineSpacingExtra();
      boolean var6 = this.mTextView.getIncludeFontPadding();
      return new StaticLayout(var1, this.mTempTextPaint, var3, var2, var5, var4, var6);
   }

   private int findLargestTextSizeWhichFits(RectF var1) {
      int var4 = this.mAutoSizeTextSizesInPx.length;
      if (var4 != 0) {
         int var3 = 0;
         int var2 = 1;
         --var4;

         while(var2 <= var4) {
            var3 = (var2 + var4) / 2;
            if (this.suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[var3], var1)) {
               int var5 = var3 + 1;
               var3 = var2;
               var2 = var5;
            } else {
               --var3;
               var4 = var3;
            }
         }

         return this.mAutoSizeTextSizesInPx[var3];
      } else {
         throw new IllegalStateException("No available text sizes to choose from.");
      }
   }

   private static Field getTextViewField(String var0) {
      NoSuchFieldException var10000;
      label43: {
         boolean var10001;
         Field var2;
         try {
            var2 = (Field)sTextViewFieldByNameCache.get(var0);
         } catch (NoSuchFieldException var5) {
            var10000 = var5;
            var10001 = false;
            break label43;
         }

         Field var1 = var2;
         if (var2 != null) {
            return var1;
         }

         try {
            var2 = TextView.class.getDeclaredField(var0);
         } catch (NoSuchFieldException var4) {
            var10000 = var4;
            var10001 = false;
            break label43;
         }

         var1 = var2;
         if (var2 == null) {
            return var1;
         }

         try {
            var2.setAccessible(true);
            sTextViewFieldByNameCache.put(var0, var2);
         } catch (NoSuchFieldException var3) {
            var10000 = var3;
            var10001 = false;
            break label43;
         }

         var1 = var2;
         return var1;
      }

      NoSuchFieldException var6 = var10000;
      Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + var0 + " member", var6);
      return null;
   }

   private static Method getTextViewMethod(String var0) {
      Exception var10000;
      label43: {
         boolean var10001;
         Method var2;
         try {
            var2 = (Method)sTextViewMethodByNameCache.get(var0);
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label43;
         }

         Method var1 = var2;
         if (var2 != null) {
            return var1;
         }

         try {
            var2 = TextView.class.getDeclaredMethod(var0);
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label43;
         }

         var1 = var2;
         if (var2 == null) {
            return var1;
         }

         try {
            var2.setAccessible(true);
            sTextViewMethodByNameCache.put(var0, var2);
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
            break label43;
         }

         var1 = var2;
         return var1;
      }

      Exception var6 = var10000;
      Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + var0 + "() method", var6);
      return null;
   }

   static Object invokeAndReturnWithDefault(Object var0, String var1, Object var2) {
      try {
         var0 = getTextViewMethod(var1).invoke(var0);
      } catch (Exception var6) {
         StringBuilder var3 = new StringBuilder();
         Log.w("ACTVAutoSizeHelper", var3.append("Failed to invoke TextView#").append(var1).append("() method").toString(), var6);
         return var2;
      } finally {
         ;
      }

      var2 = var0;
      return var2;
   }

   private void setRawTextSize(float var1) {
      if (var1 != this.mTextView.getPaint().getTextSize()) {
         this.mTextView.getPaint().setTextSize(var1);
         boolean var2;
         if (VERSION.SDK_INT >= 18) {
            var2 = this.mTextView.isInLayout();
         } else {
            var2 = false;
         }

         if (this.mTextView.getLayout() != null) {
            this.mNeedsAutoSizeText = false;

            label36: {
               Exception var10000;
               label44: {
                  boolean var10001;
                  Method var3;
                  try {
                     var3 = getTextViewMethod("nullLayouts");
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                     break label44;
                  }

                  if (var3 == null) {
                     break label36;
                  }

                  try {
                     var3.invoke(this.mTextView);
                     break label36;
                  } catch (Exception var4) {
                     var10000 = var4;
                     var10001 = false;
                  }
               }

               Exception var6 = var10000;
               Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", var6);
            }

            if (!var2) {
               this.mTextView.requestLayout();
            } else {
               this.mTextView.forceLayout();
            }

            this.mTextView.invalidate();
         }
      }

   }

   private boolean setupAutoSizeText() {
      boolean var3 = this.supportsAutoSizeText();
      int var1 = 0;
      if (var3 && this.mAutoSizeTextType == 1) {
         if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
            int var2 = (int)Math.floor((double)((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) / this.mAutoSizeStepGranularityInPx)) + 1;

            int[] var4;
            for(var4 = new int[var2]; var1 < var2; ++var1) {
               var4[var1] = Math.round(this.mAutoSizeMinTextSizeInPx + (float)var1 * this.mAutoSizeStepGranularityInPx);
            }

            this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(var4);
         }

         this.mNeedsAutoSizeText = true;
      } else {
         this.mNeedsAutoSizeText = false;
      }

      return this.mNeedsAutoSizeText;
   }

   private void setupAutoSizeUniformPresetSizes(TypedArray var1) {
      int var3 = var1.length();
      int[] var4 = new int[var3];
      if (var3 > 0) {
         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2] = var1.getDimensionPixelSize(var2, -1);
         }

         this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(var4);
         this.setupAutoSizeUniformPresetSizesConfiguration();
      }

   }

   private boolean setupAutoSizeUniformPresetSizesConfiguration() {
      int[] var3 = this.mAutoSizeTextSizesInPx;
      int var1 = var3.length;
      boolean var2;
      if (var1 > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.mHasPresetAutoSizeValues = var2;
      if (var2) {
         this.mAutoSizeTextType = 1;
         this.mAutoSizeMinTextSizeInPx = (float)var3[0];
         this.mAutoSizeMaxTextSizeInPx = (float)var3[var1 - 1];
         this.mAutoSizeStepGranularityInPx = -1.0F;
      }

      return var2;
   }

   private boolean suggestedSizeFitsInSpace(int var1, RectF var2) {
      CharSequence var5 = this.mTextView.getText();
      TransformationMethod var6 = this.mTextView.getTransformationMethod();
      CharSequence var4 = var5;
      if (var6 != null) {
         CharSequence var8 = var6.getTransformation(var5, this.mTextView);
         var4 = var5;
         if (var8 != null) {
            var4 = var8;
         }
      }

      int var3;
      if (VERSION.SDK_INT >= 16) {
         var3 = this.mTextView.getMaxLines();
      } else {
         var3 = -1;
      }

      this.initTempTextPaint(var1);
      StaticLayout var7 = this.createLayout(var4, (Layout.Alignment)invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Alignment.ALIGN_NORMAL), Math.round(var2.right), var3);
      if (var3 != -1 && (var7.getLineCount() > var3 || var7.getLineEnd(var7.getLineCount() - 1) != var4.length())) {
         return false;
      } else {
         return !((float)var7.getHeight() > var2.bottom);
      }
   }

   private boolean supportsAutoSizeText() {
      return this.mTextView instanceof AppCompatEditText ^ true;
   }

   private void validateAndSetAutoSizeTextTypeUniformConfiguration(float var1, float var2, float var3) throws IllegalArgumentException {
      if (!(var1 <= 0.0F)) {
         if (!(var2 <= var1)) {
            if (!(var3 <= 0.0F)) {
               this.mAutoSizeTextType = 1;
               this.mAutoSizeMinTextSizeInPx = var1;
               this.mAutoSizeMaxTextSizeInPx = var2;
               this.mAutoSizeStepGranularityInPx = var3;
               this.mHasPresetAutoSizeValues = false;
            } else {
               throw new IllegalArgumentException("The auto-size step granularity (" + var3 + "px) is less or equal to (0px)");
            }
         } else {
            throw new IllegalArgumentException("Maximum auto-size text size (" + var2 + "px) is less or equal to minimum auto-size text size (" + var1 + "px)");
         }
      } else {
         throw new IllegalArgumentException("Minimum auto-size text size (" + var1 + "px) is less or equal to (0px)");
      }
   }

   void autoSizeText() {
      if (this.isAutoSizeEnabled()) {
         if (this.mNeedsAutoSizeText) {
            label255: {
               if (this.mTextView.getMeasuredHeight() <= 0 || this.mTextView.getMeasuredWidth() <= 0) {
                  return;
               }

               int var2;
               if (this.mImpl.isHorizontallyScrollable(this.mTextView)) {
                  var2 = 1048576;
               } else {
                  var2 = this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft() - this.mTextView.getTotalPaddingRight();
               }

               int var3 = this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom() - this.mTextView.getCompoundPaddingTop();
               if (var2 <= 0 || var3 <= 0) {
                  return;
               }

               RectF var5 = TEMP_RECTF;
               synchronized(var5){}

               Throwable var10000;
               boolean var10001;
               label245: {
                  try {
                     var5.setEmpty();
                     var5.right = (float)var2;
                     var5.bottom = (float)var3;
                     float var1 = (float)this.findLargestTextSizeWhichFits(var5);
                     if (var1 != this.mTextView.getTextSize()) {
                        this.setTextSizeInternal(0, var1);
                     }
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label245;
                  }

                  label242:
                  try {
                     break label255;
                  } catch (Throwable var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label242;
                  }
               }

               while(true) {
                  Throwable var4 = var10000;

                  try {
                     throw var4;
                  } catch (Throwable var15) {
                     var10000 = var15;
                     var10001 = false;
                     continue;
                  }
               }
            }
         }

         this.mNeedsAutoSizeText = true;
      }
   }

   StaticLayout createLayout(CharSequence var1, Layout.Alignment var2, int var3, int var4) {
      if (VERSION.SDK_INT >= 23) {
         return this.createStaticLayoutForMeasuring(var1, var2, var3, var4);
      } else {
         return VERSION.SDK_INT >= 16 ? this.createStaticLayoutForMeasuringPre23(var1, var2, var3) : this.createStaticLayoutForMeasuringPre16(var1, var2, var3);
      }
   }

   int getAutoSizeMaxTextSize() {
      return Math.round(this.mAutoSizeMaxTextSizeInPx);
   }

   int getAutoSizeMinTextSize() {
      return Math.round(this.mAutoSizeMinTextSizeInPx);
   }

   int getAutoSizeStepGranularity() {
      return Math.round(this.mAutoSizeStepGranularityInPx);
   }

   int[] getAutoSizeTextAvailableSizes() {
      return this.mAutoSizeTextSizesInPx;
   }

   int getAutoSizeTextType() {
      return this.mAutoSizeTextType;
   }

   void initTempTextPaint(int var1) {
      TextPaint var2 = this.mTempTextPaint;
      if (var2 == null) {
         this.mTempTextPaint = new TextPaint();
      } else {
         var2.reset();
      }

      this.mTempTextPaint.set(this.mTextView.getPaint());
      this.mTempTextPaint.setTextSize((float)var1);
   }

   boolean isAutoSizeEnabled() {
      boolean var1;
      if (this.supportsAutoSizeText() && this.mAutoSizeTextType != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   void loadFromAttributes(AttributeSet var1, int var2) {
      TypedArray var7 = this.mContext.obtainStyledAttributes(var1, R.styleable.AppCompatTextView, var2, 0);
      TextView var8 = this.mTextView;
      ViewCompat.saveAttributeDataForStyleable(var8, var8.getContext(), R.styleable.AppCompatTextView, var1, var7, var2, 0);
      if (var7.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
         this.mAutoSizeTextType = var7.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
      }

      float var3;
      if (var7.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity)) {
         var3 = var7.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0F);
      } else {
         var3 = -1.0F;
      }

      float var4;
      if (var7.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize)) {
         var4 = var7.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0F);
      } else {
         var4 = -1.0F;
      }

      float var5;
      if (var7.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize)) {
         var5 = var7.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0F);
      } else {
         var5 = -1.0F;
      }

      if (var7.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes)) {
         var2 = var7.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
         if (var2 > 0) {
            TypedArray var9 = var7.getResources().obtainTypedArray(var2);
            this.setupAutoSizeUniformPresetSizes(var9);
            var9.recycle();
         }
      }

      var7.recycle();
      if (this.supportsAutoSizeText()) {
         if (this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues) {
               DisplayMetrics var10 = this.mContext.getResources().getDisplayMetrics();
               float var6 = var4;
               if (var4 == -1.0F) {
                  var6 = TypedValue.applyDimension(2, 12.0F, var10);
               }

               var4 = var5;
               if (var5 == -1.0F) {
                  var4 = TypedValue.applyDimension(2, 112.0F, var10);
               }

               var5 = var3;
               if (var3 == -1.0F) {
                  var5 = 1.0F;
               }

               this.validateAndSetAutoSizeTextTypeUniformConfiguration(var6, var4, var5);
            }

            this.setupAutoSizeText();
         }
      } else {
         this.mAutoSizeTextType = 0;
      }

   }

   void setAutoSizeTextTypeUniformWithConfiguration(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      if (this.supportsAutoSizeText()) {
         DisplayMetrics var5 = this.mContext.getResources().getDisplayMetrics();
         this.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(var4, (float)var1, var5), TypedValue.applyDimension(var4, (float)var2, var5), TypedValue.applyDimension(var4, (float)var3, var5));
         if (this.setupAutoSizeText()) {
            this.autoSizeText();
         }
      }

   }

   void setAutoSizeTextTypeUniformWithPresetSizes(int[] var1, int var2) throws IllegalArgumentException {
      if (this.supportsAutoSizeText()) {
         int var4 = var1.length;
         int var3 = 0;
         if (var4 <= 0) {
            this.mHasPresetAutoSizeValues = false;
         } else {
            int[] var6 = new int[var4];
            int[] var5;
            if (var2 == 0) {
               var5 = Arrays.copyOf(var1, var4);
            } else {
               DisplayMetrics var7 = this.mContext.getResources().getDisplayMetrics();

               while(true) {
                  var5 = var6;
                  if (var3 >= var4) {
                     break;
                  }

                  var6[var3] = Math.round(TypedValue.applyDimension(var2, (float)var1[var3], var7));
                  ++var3;
               }
            }

            this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(var5);
            if (!this.setupAutoSizeUniformPresetSizesConfiguration()) {
               throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(var1));
            }
         }

         if (this.setupAutoSizeText()) {
            this.autoSizeText();
         }
      }

   }

   void setAutoSizeTextTypeWithDefaults(int var1) {
      if (this.supportsAutoSizeText()) {
         if (var1 != 0) {
            if (var1 != 1) {
               throw new IllegalArgumentException("Unknown auto-size text type: " + var1);
            }

            DisplayMetrics var2 = this.mContext.getResources().getDisplayMetrics();
            this.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0F, var2), TypedValue.applyDimension(2, 112.0F, var2), 1.0F);
            if (this.setupAutoSizeText()) {
               this.autoSizeText();
            }
         } else {
            this.clearAutoSizeConfiguration();
         }
      }

   }

   void setTextSizeInternal(int var1, float var2) {
      Context var3 = this.mContext;
      Resources var4;
      if (var3 == null) {
         var4 = Resources.getSystem();
      } else {
         var4 = var3.getResources();
      }

      this.setRawTextSize(TypedValue.applyDimension(var1, var2, var4.getDisplayMetrics()));
   }

   private static class Impl {
      Impl() {
      }

      void computeAndSetTextDirection(StaticLayout.Builder var1, TextView var2) {
      }

      boolean isHorizontallyScrollable(TextView var1) {
         return (Boolean)AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(var1, "getHorizontallyScrolling", false);
      }
   }

   private static class Impl23 extends Impl {
      Impl23() {
      }

      void computeAndSetTextDirection(StaticLayout.Builder var1, TextView var2) {
         var1.setTextDirection((TextDirectionHeuristic)AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(var2, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
      }
   }

   private static class Impl29 extends Impl23 {
      Impl29() {
      }

      void computeAndSetTextDirection(StaticLayout.Builder var1, TextView var2) {
         var1.setTextDirection(var2.getTextDirectionHeuristic());
      }

      boolean isHorizontallyScrollable(TextView var1) {
         return var1.isHorizontallyScrollable();
      }
   }
}
