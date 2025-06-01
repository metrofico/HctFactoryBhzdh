package androidx.emoji2.viewsintegration;

import android.os.Build.VERSION;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;

public final class EmojiTextViewHelper {
   private final HelperInternal mHelper;

   public EmojiTextViewHelper(TextView var1) {
      this(var1, true);
   }

   public EmojiTextViewHelper(TextView var1, boolean var2) {
      Preconditions.checkNotNull(var1, "textView cannot be null");
      if (VERSION.SDK_INT < 19) {
         this.mHelper = new HelperInternal();
      } else if (!var2) {
         this.mHelper = new SkippingHelper19(var1);
      } else {
         this.mHelper = new HelperInternal19(var1);
      }

   }

   public InputFilter[] getFilters(InputFilter[] var1) {
      return this.mHelper.getFilters(var1);
   }

   public boolean isEnabled() {
      return this.mHelper.isEnabled();
   }

   public void setAllCaps(boolean var1) {
      this.mHelper.setAllCaps(var1);
   }

   public void setEnabled(boolean var1) {
      this.mHelper.setEnabled(var1);
   }

   public void updateTransformationMethod() {
      this.mHelper.updateTransformationMethod();
   }

   public TransformationMethod wrapTransformationMethod(TransformationMethod var1) {
      return this.mHelper.wrapTransformationMethod(var1);
   }

   static class HelperInternal {
      InputFilter[] getFilters(InputFilter[] var1) {
         return var1;
      }

      public boolean isEnabled() {
         return false;
      }

      void setAllCaps(boolean var1) {
      }

      void setEnabled(boolean var1) {
      }

      void updateTransformationMethod() {
      }

      TransformationMethod wrapTransformationMethod(TransformationMethod var1) {
         return var1;
      }
   }

   private static class HelperInternal19 extends HelperInternal {
      private final EmojiInputFilter mEmojiInputFilter;
      private boolean mEnabled;
      private final TextView mTextView;

      HelperInternal19(TextView var1) {
         this.mTextView = var1;
         this.mEnabled = true;
         this.mEmojiInputFilter = new EmojiInputFilter(var1);
      }

      private InputFilter[] addEmojiInputFilterIfMissing(InputFilter[] var1) {
         int var3 = var1.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            if (var1[var2] == this.mEmojiInputFilter) {
               return var1;
            }
         }

         InputFilter[] var4 = new InputFilter[var1.length + 1];
         System.arraycopy(var1, 0, var4, 0, var3);
         var4[var3] = this.mEmojiInputFilter;
         return var4;
      }

      private SparseArray getEmojiInputFilterPositionArray(InputFilter[] var1) {
         SparseArray var4 = new SparseArray(1);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            InputFilter var3 = var1[var2];
            if (var3 instanceof EmojiInputFilter) {
               var4.put(var2, var3);
            }
         }

         return var4;
      }

      private InputFilter[] removeEmojiInputFilterIfPresent(InputFilter[] var1) {
         SparseArray var6 = this.getEmojiInputFilterPositionArray(var1);
         if (var6.size() == 0) {
            return var1;
         } else {
            int var5 = var1.length;
            InputFilter[] var7 = new InputFilter[var1.length - var6.size()];
            int var4 = 0;

            int var2;
            for(int var3 = 0; var4 < var5; var3 = var2) {
               var2 = var3;
               if (var6.indexOfKey(var4) < 0) {
                  var7[var3] = var1[var4];
                  var2 = var3 + 1;
               }

               ++var4;
            }

            return var7;
         }
      }

      private TransformationMethod unwrapForDisabled(TransformationMethod var1) {
         TransformationMethod var2 = var1;
         if (var1 instanceof EmojiTransformationMethod) {
            var2 = ((EmojiTransformationMethod)var1).getOriginalTransformationMethod();
         }

         return var2;
      }

      private void updateFilters() {
         InputFilter[] var1 = this.mTextView.getFilters();
         this.mTextView.setFilters(this.getFilters(var1));
      }

      private TransformationMethod wrapForEnabled(TransformationMethod var1) {
         if (var1 instanceof EmojiTransformationMethod) {
            return var1;
         } else {
            return (TransformationMethod)(var1 instanceof PasswordTransformationMethod ? var1 : new EmojiTransformationMethod(var1));
         }
      }

      InputFilter[] getFilters(InputFilter[] var1) {
         return !this.mEnabled ? this.removeEmojiInputFilterIfPresent(var1) : this.addEmojiInputFilterIfMissing(var1);
      }

      public boolean isEnabled() {
         return this.mEnabled;
      }

      void setAllCaps(boolean var1) {
         if (var1) {
            this.updateTransformationMethod();
         }

      }

      void setEnabled(boolean var1) {
         this.mEnabled = var1;
         this.updateTransformationMethod();
         this.updateFilters();
      }

      void setEnabledUnsafe(boolean var1) {
         this.mEnabled = var1;
      }

      void updateTransformationMethod() {
         TransformationMethod var1 = this.wrapTransformationMethod(this.mTextView.getTransformationMethod());
         this.mTextView.setTransformationMethod(var1);
      }

      TransformationMethod wrapTransformationMethod(TransformationMethod var1) {
         return this.mEnabled ? this.wrapForEnabled(var1) : this.unwrapForDisabled(var1);
      }
   }

   private static class SkippingHelper19 extends HelperInternal {
      private final HelperInternal19 mHelperDelegate;

      SkippingHelper19(TextView var1) {
         this.mHelperDelegate = new HelperInternal19(var1);
      }

      private boolean skipBecauseEmojiCompatNotInitialized() {
         return EmojiCompat.isConfigured() ^ true;
      }

      InputFilter[] getFilters(InputFilter[] var1) {
         return this.skipBecauseEmojiCompatNotInitialized() ? var1 : this.mHelperDelegate.getFilters(var1);
      }

      public boolean isEnabled() {
         return this.mHelperDelegate.isEnabled();
      }

      void setAllCaps(boolean var1) {
         if (!this.skipBecauseEmojiCompatNotInitialized()) {
            this.mHelperDelegate.setAllCaps(var1);
         }
      }

      void setEnabled(boolean var1) {
         if (this.skipBecauseEmojiCompatNotInitialized()) {
            this.mHelperDelegate.setEnabledUnsafe(var1);
         } else {
            this.mHelperDelegate.setEnabled(var1);
         }

      }

      void updateTransformationMethod() {
         if (!this.skipBecauseEmojiCompatNotInitialized()) {
            this.mHelperDelegate.updateTransformationMethod();
         }
      }

      TransformationMethod wrapTransformationMethod(TransformationMethod var1) {
         return this.skipBecauseEmojiCompatNotInitialized() ? var1 : this.mHelperDelegate.wrapTransformationMethod(var1);
      }
   }
}
