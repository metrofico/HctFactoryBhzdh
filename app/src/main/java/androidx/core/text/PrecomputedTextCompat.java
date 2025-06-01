package androidx.core.text;

import android.os.Build.VERSION;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.Layout.Alignment;
import android.text.style.MetricAffectingSpan;
import androidx.core.os.TraceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PrecomputedTextCompat implements Spannable {
   private static final char LINE_FEED = '\n';
   private static Executor sExecutor;
   private static final Object sLock = new Object();
   private final int[] mParagraphEnds;
   private final Params mParams;
   private final Spannable mText;
   private final PrecomputedText mWrapped;

   private PrecomputedTextCompat(PrecomputedText var1, Params var2) {
      this.mText = var1;
      this.mParams = var2;
      this.mParagraphEnds = null;
      if (VERSION.SDK_INT < 29) {
         var1 = null;
      }

      this.mWrapped = var1;
   }

   private PrecomputedTextCompat(CharSequence var1, Params var2, int[] var3) {
      this.mText = new SpannableString(var1);
      this.mParams = var2;
      this.mParagraphEnds = var3;
      this.mWrapped = null;
   }

   public static PrecomputedTextCompat create(CharSequence var0, Params var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);

      PrecomputedTextCompat var97;
      label954: {
         Throwable var10000;
         label960: {
            boolean var10001;
            try {
               TraceCompat.beginSection("PrecomputedText");
               if (VERSION.SDK_INT >= 29 && var1.mWrapped != null) {
                  var97 = new PrecomputedTextCompat(PrecomputedText.create(var0, var1.mWrapped), var1);
                  break label954;
               }
            } catch (Throwable var95) {
               var10000 = var95;
               var10001 = false;
               break label960;
            }

            int var3;
            ArrayList var4;
            try {
               var4 = new ArrayList();
               var3 = var0.length();
            } catch (Throwable var94) {
               var10000 = var94;
               var10001 = false;
               break label960;
            }

            int var2 = 0;

            while(var2 < var3) {
               try {
                  var2 = TextUtils.indexOf(var0, '\n', var2, var3);
               } catch (Throwable var93) {
                  var10000 = var93;
                  var10001 = false;
                  break label960;
               }

               if (var2 < 0) {
                  var2 = var3;
               } else {
                  ++var2;
               }

               try {
                  var4.add(var2);
               } catch (Throwable var92) {
                  var10000 = var92;
                  var10001 = false;
                  break label960;
               }
            }

            int[] var5;
            try {
               var5 = new int[var4.size()];
            } catch (Throwable var91) {
               var10000 = var91;
               var10001 = false;
               break label960;
            }

            var2 = 0;

            while(true) {
               try {
                  if (var2 >= var4.size()) {
                     break;
                  }

                  var5[var2] = (Integer)var4.get(var2);
               } catch (Throwable var90) {
                  var10000 = var90;
                  var10001 = false;
                  break label960;
               }

               ++var2;
            }

            label917: {
               try {
                  if (VERSION.SDK_INT >= 23) {
                     android.text.StaticLayout.Builder.obtain(var0, 0, var0.length(), var1.getTextPaint(), Integer.MAX_VALUE).setBreakStrategy(var1.getBreakStrategy()).setHyphenationFrequency(var1.getHyphenationFrequency()).setTextDirection(var1.getTextDirection()).build();
                     break label917;
                  }
               } catch (Throwable var89) {
                  var10000 = var89;
                  var10001 = false;
                  break label960;
               }

               try {
                  if (VERSION.SDK_INT >= 21) {
                     new StaticLayout(var0, var1.getTextPaint(), Integer.MAX_VALUE, Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
                  }
               } catch (Throwable var88) {
                  var10000 = var88;
                  var10001 = false;
                  break label960;
               }
            }

            try {
               var97 = new PrecomputedTextCompat(var0, var1, var5);
            } catch (Throwable var87) {
               var10000 = var87;
               var10001 = false;
               break label960;
            }

            TraceCompat.endSection();
            return var97;
         }

         Throwable var96 = var10000;
         TraceCompat.endSection();
         throw var96;
      }

      TraceCompat.endSection();
      return var97;
   }

   public static Future getTextFuture(CharSequence var0, Params var1, Executor var2) {
      PrecomputedTextFutureTask var17 = new PrecomputedTextFutureTask(var1, var0);
      Executor var15 = var2;
      if (var2 == null) {
         label150: {
            Object var18 = sLock;
            synchronized(var18){}

            Throwable var10000;
            boolean var10001;
            label144: {
               try {
                  if (sExecutor == null) {
                     sExecutor = Executors.newFixedThreadPool(1);
                  }
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label144;
               }

               label141:
               try {
                  var15 = sExecutor;
                  break label150;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label141;
               }
            }

            while(true) {
               Throwable var16 = var10000;

               try {
                  throw var16;
               } catch (Throwable var12) {
                  var10000 = var12;
                  var10001 = false;
                  continue;
               }
            }
         }
      }

      var15.execute(var17);
      return var17;
   }

   public char charAt(int var1) {
      return this.mText.charAt(var1);
   }

   public int getParagraphCount() {
      return VERSION.SDK_INT >= 29 ? this.mWrapped.getParagraphCount() : this.mParagraphEnds.length;
   }

   public int getParagraphEnd(int var1) {
      Preconditions.checkArgumentInRange(var1, 0, this.getParagraphCount(), "paraIndex");
      return VERSION.SDK_INT >= 29 ? this.mWrapped.getParagraphEnd(var1) : this.mParagraphEnds[var1];
   }

   public int getParagraphStart(int var1) {
      int var3 = this.getParagraphCount();
      byte var2 = 0;
      Preconditions.checkArgumentInRange(var1, 0, var3, "paraIndex");
      if (VERSION.SDK_INT >= 29) {
         return this.mWrapped.getParagraphStart(var1);
      } else {
         if (var1 == 0) {
            var1 = var2;
         } else {
            var1 = this.mParagraphEnds[var1 - 1];
         }

         return var1;
      }
   }

   public Params getParams() {
      return this.mParams;
   }

   public PrecomputedText getPrecomputedText() {
      Spannable var1 = this.mText;
      return var1 instanceof PrecomputedText ? (PrecomputedText)var1 : null;
   }

   public int getSpanEnd(Object var1) {
      return this.mText.getSpanEnd(var1);
   }

   public int getSpanFlags(Object var1) {
      return this.mText.getSpanFlags(var1);
   }

   public int getSpanStart(Object var1) {
      return this.mText.getSpanStart(var1);
   }

   public Object[] getSpans(int var1, int var2, Class var3) {
      return VERSION.SDK_INT >= 29 ? this.mWrapped.getSpans(var1, var2, var3) : this.mText.getSpans(var1, var2, var3);
   }

   public int length() {
      return this.mText.length();
   }

   public int nextSpanTransition(int var1, int var2, Class var3) {
      return this.mText.nextSpanTransition(var1, var2, var3);
   }

   public void removeSpan(Object var1) {
      if (!(var1 instanceof MetricAffectingSpan)) {
         if (VERSION.SDK_INT >= 29) {
            this.mWrapped.removeSpan(var1);
         } else {
            this.mText.removeSpan(var1);
         }

      } else {
         throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
      }
   }

   public void setSpan(Object var1, int var2, int var3, int var4) {
      if (!(var1 instanceof MetricAffectingSpan)) {
         if (VERSION.SDK_INT >= 29) {
            this.mWrapped.setSpan(var1, var2, var3, var4);
         } else {
            this.mText.setSpan(var1, var2, var3, var4);
         }

      } else {
         throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
      }
   }

   public CharSequence subSequence(int var1, int var2) {
      return this.mText.subSequence(var1, var2);
   }

   public String toString() {
      return this.mText.toString();
   }

   public static final class Params {
      private final int mBreakStrategy;
      private final int mHyphenationFrequency;
      private final TextPaint mPaint;
      private final TextDirectionHeuristic mTextDir;
      final PrecomputedText.Params mWrapped;

      public Params(PrecomputedText.Params var1) {
         this.mPaint = var1.getTextPaint();
         this.mTextDir = var1.getTextDirection();
         this.mBreakStrategy = var1.getBreakStrategy();
         this.mHyphenationFrequency = var1.getHyphenationFrequency();
         if (VERSION.SDK_INT < 29) {
            var1 = null;
         }

         this.mWrapped = var1;
      }

      Params(TextPaint var1, TextDirectionHeuristic var2, int var3, int var4) {
         if (VERSION.SDK_INT >= 29) {
            this.mWrapped = (new PrecomputedText.Builder(var1)).setBreakStrategy(var3).setHyphenationFrequency(var4).setTextDirection(var2).build();
         } else {
            this.mWrapped = null;
         }

         this.mPaint = var1;
         this.mTextDir = var2;
         this.mBreakStrategy = var3;
         this.mHyphenationFrequency = var4;
      }

      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Params)) {
            return false;
         } else {
            Params var2 = (Params)var1;
            if (!this.equalsWithoutTextDirection(var2)) {
               return false;
            } else {
               return VERSION.SDK_INT < 18 || this.mTextDir == var2.getTextDirection();
            }
         }
      }

      public boolean equalsWithoutTextDirection(Params var1) {
         if (VERSION.SDK_INT >= 23) {
            if (this.mBreakStrategy != var1.getBreakStrategy()) {
               return false;
            }

            if (this.mHyphenationFrequency != var1.getHyphenationFrequency()) {
               return false;
            }
         }

         if (this.mPaint.getTextSize() != var1.getTextPaint().getTextSize()) {
            return false;
         } else if (this.mPaint.getTextScaleX() != var1.getTextPaint().getTextScaleX()) {
            return false;
         } else if (this.mPaint.getTextSkewX() != var1.getTextPaint().getTextSkewX()) {
            return false;
         } else {
            if (VERSION.SDK_INT >= 21) {
               if (this.mPaint.getLetterSpacing() != var1.getTextPaint().getLetterSpacing()) {
                  return false;
               }

               if (!TextUtils.equals(this.mPaint.getFontFeatureSettings(), var1.getTextPaint().getFontFeatureSettings())) {
                  return false;
               }
            }

            if (this.mPaint.getFlags() != var1.getTextPaint().getFlags()) {
               return false;
            } else {
               if (VERSION.SDK_INT >= 24) {
                  if (!this.mPaint.getTextLocales().equals(var1.getTextPaint().getTextLocales())) {
                     return false;
                  }
               } else if (VERSION.SDK_INT >= 17 && !this.mPaint.getTextLocale().equals(var1.getTextPaint().getTextLocale())) {
                  return false;
               }

               if (this.mPaint.getTypeface() == null) {
                  if (var1.getTextPaint().getTypeface() != null) {
                     return false;
                  }
               } else if (!this.mPaint.getTypeface().equals(var1.getTextPaint().getTypeface())) {
                  return false;
               }

               return true;
            }
         }
      }

      public int getBreakStrategy() {
         return this.mBreakStrategy;
      }

      public int getHyphenationFrequency() {
         return this.mHyphenationFrequency;
      }

      public TextDirectionHeuristic getTextDirection() {
         return this.mTextDir;
      }

      public TextPaint getTextPaint() {
         return this.mPaint;
      }

      public int hashCode() {
         if (VERSION.SDK_INT >= 24) {
            return ObjectsCompat.hash(this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getLetterSpacing(), this.mPaint.getFlags(), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), this.mPaint.isElegantTextHeight(), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
         } else if (VERSION.SDK_INT >= 21) {
            return ObjectsCompat.hash(this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getLetterSpacing(), this.mPaint.getFlags(), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mPaint.isElegantTextHeight(), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
         } else if (VERSION.SDK_INT >= 18) {
            return ObjectsCompat.hash(this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getFlags(), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
         } else {
            return VERSION.SDK_INT >= 17 ? ObjectsCompat.hash(this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getFlags(), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency) : ObjectsCompat.hash(this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getFlags(), this.mPaint.getTypeface(), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
         }
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder("{");
         var1.append("textSize=" + this.mPaint.getTextSize());
         var1.append(", textScaleX=" + this.mPaint.getTextScaleX());
         var1.append(", textSkewX=" + this.mPaint.getTextSkewX());
         if (VERSION.SDK_INT >= 21) {
            var1.append(", letterSpacing=" + this.mPaint.getLetterSpacing());
            var1.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
         }

         if (VERSION.SDK_INT >= 24) {
            var1.append(", textLocale=" + this.mPaint.getTextLocales());
         } else if (VERSION.SDK_INT >= 17) {
            var1.append(", textLocale=" + this.mPaint.getTextLocale());
         }

         var1.append(", typeface=" + this.mPaint.getTypeface());
         if (VERSION.SDK_INT >= 26) {
            var1.append(", variationSettings=" + this.mPaint.getFontVariationSettings());
         }

         var1.append(", textDir=" + this.mTextDir);
         var1.append(", breakStrategy=" + this.mBreakStrategy);
         var1.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
         var1.append("}");
         return var1.toString();
      }

      public static class Builder {
         private int mBreakStrategy;
         private int mHyphenationFrequency;
         private final TextPaint mPaint;
         private TextDirectionHeuristic mTextDir;

         public Builder(TextPaint var1) {
            this.mPaint = var1;
            if (VERSION.SDK_INT >= 23) {
               this.mBreakStrategy = 1;
               this.mHyphenationFrequency = 1;
            } else {
               this.mHyphenationFrequency = 0;
               this.mBreakStrategy = 0;
            }

            if (VERSION.SDK_INT >= 18) {
               this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            } else {
               this.mTextDir = null;
            }

         }

         public Params build() {
            return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
         }

         public Builder setBreakStrategy(int var1) {
            this.mBreakStrategy = var1;
            return this;
         }

         public Builder setHyphenationFrequency(int var1) {
            this.mHyphenationFrequency = var1;
            return this;
         }

         public Builder setTextDirection(TextDirectionHeuristic var1) {
            this.mTextDir = var1;
            return this;
         }
      }
   }

   private static class PrecomputedTextFutureTask extends FutureTask {
      PrecomputedTextFutureTask(Params var1, CharSequence var2) {
         super(new PrecomputedTextCallback(var1, var2));
      }

      private static class PrecomputedTextCallback implements Callable {
         private Params mParams;
         private CharSequence mText;

         PrecomputedTextCallback(Params var1, CharSequence var2) {
            this.mParams = var1;
            this.mText = var2;
         }

         public PrecomputedTextCompat call() throws Exception {
            return PrecomputedTextCompat.create(this.mText, this.mParams);
         }
      }
   }
}
