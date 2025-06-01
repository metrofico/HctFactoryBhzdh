package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

final class EmojiInputFilter implements InputFilter {
   private EmojiCompat.InitCallback mInitCallback;
   private final TextView mTextView;

   EmojiInputFilter(TextView var1) {
      this.mTextView = var1;
   }

   private EmojiCompat.InitCallback getInitCallback() {
      if (this.mInitCallback == null) {
         this.mInitCallback = new InitCallbackImpl(this.mTextView, this);
      }

      return this.mInitCallback;
   }

   static void updateSelection(Spannable var0, int var1, int var2) {
      if (var1 >= 0 && var2 >= 0) {
         Selection.setSelection(var0, var1, var2);
      } else if (var1 >= 0) {
         Selection.setSelection(var0, var1);
      } else if (var2 >= 0) {
         Selection.setSelection(var0, var2);
      }

   }

   public CharSequence filter(CharSequence var1, int var2, int var3, Spanned var4, int var5, int var6) {
      if (this.mTextView.isInEditMode()) {
         return var1;
      } else {
         int var7 = EmojiCompat.get().getLoadState();
         if (var7 != 0) {
            boolean var8 = true;
            if (var7 == 1) {
               boolean var10 = var8;
               if (var6 == 0) {
                  var10 = var8;
                  if (var5 == 0) {
                     var10 = var8;
                     if (var4.length() == 0) {
                        var10 = var8;
                        if (var1 == this.mTextView.getText()) {
                           var10 = false;
                        }
                     }
                  }
               }

               CharSequence var9 = var1;
               if (var10) {
                  var9 = var1;
                  if (var1 != null) {
                     if (var2 != 0 || var3 != var1.length()) {
                        var1 = var1.subSequence(var2, var3);
                     }

                     var9 = EmojiCompat.get().process(var1, 0, var1.length());
                  }
               }

               return var9;
            }

            if (var7 != 3) {
               return var1;
            }
         }

         EmojiCompat.get().registerInitCallback(this.getInitCallback());
         return var1;
      }
   }

   private static class InitCallbackImpl extends EmojiCompat.InitCallback {
      private final Reference mEmojiInputFilterReference;
      private final Reference mViewRef;

      InitCallbackImpl(TextView var1, EmojiInputFilter var2) {
         this.mViewRef = new WeakReference(var1);
         this.mEmojiInputFilterReference = new WeakReference(var2);
      }

      private boolean isInputFilterCurrentlyRegisteredOnTextView(TextView var1, InputFilter var2) {
         if (var2 != null && var1 != null) {
            InputFilter[] var4 = var1.getFilters();
            if (var4 == null) {
               return false;
            }

            for(int var3 = 0; var3 < var4.length; ++var3) {
               if (var4[var3] == var2) {
                  return true;
               }
            }
         }

         return false;
      }

      public void onInitialized() {
         super.onInitialized();
         TextView var4 = (TextView)this.mViewRef.get();
         if (this.isInputFilterCurrentlyRegisteredOnTextView(var4, (InputFilter)this.mEmojiInputFilterReference.get())) {
            if (var4.isAttachedToWindow()) {
               CharSequence var3 = EmojiCompat.get().process(var4.getText());
               int var1 = Selection.getSelectionStart(var3);
               int var2 = Selection.getSelectionEnd(var3);
               var4.setText(var3);
               if (var3 instanceof Spannable) {
                  EmojiInputFilter.updateSelection((Spannable)var3, var1, var2);
               }
            }

         }
      }
   }
}
