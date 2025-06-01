package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

final class EmojiTextWatcher implements TextWatcher {
   private final EditText mEditText;
   private int mEmojiReplaceStrategy = 0;
   private boolean mEnabled;
   private final boolean mExpectInitializedEmojiCompat;
   private EmojiCompat.InitCallback mInitCallback;
   private int mMaxEmojiCount = Integer.MAX_VALUE;

   EmojiTextWatcher(EditText var1, boolean var2) {
      this.mEditText = var1;
      this.mExpectInitializedEmojiCompat = var2;
      this.mEnabled = true;
   }

   private EmojiCompat.InitCallback getInitCallback() {
      if (this.mInitCallback == null) {
         this.mInitCallback = new InitCallbackImpl(this.mEditText);
      }

      return this.mInitCallback;
   }

   static void processTextOnEnablingEvent(EditText var0, int var1) {
      if (var1 == 1 && var0 != null && var0.isAttachedToWindow()) {
         Editable var3 = var0.getEditableText();
         var1 = Selection.getSelectionStart(var3);
         int var2 = Selection.getSelectionEnd(var3);
         EmojiCompat.get().process(var3);
         EmojiInputFilter.updateSelection(var3, var1, var2);
      }

   }

   private boolean shouldSkipForDisabledOrNotConfigured() {
      boolean var1;
      if (!this.mEnabled || !this.mExpectInitializedEmojiCompat && !EmojiCompat.isConfigured()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void afterTextChanged(Editable var1) {
   }

   public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
   }

   int getEmojiReplaceStrategy() {
      return this.mEmojiReplaceStrategy;
   }

   int getMaxEmojiCount() {
      return this.mMaxEmojiCount;
   }

   public boolean isEnabled() {
      return this.mEnabled;
   }

   public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
      if (!this.mEditText.isInEditMode() && !this.shouldSkipForDisabledOrNotConfigured() && var3 <= var4 && var1 instanceof Spannable) {
         var3 = EmojiCompat.get().getLoadState();
         if (var3 != 0) {
            if (var3 == 1) {
               Spannable var5 = (Spannable)var1;
               EmojiCompat.get().process(var5, var2, var2 + var4, this.mMaxEmojiCount, this.mEmojiReplaceStrategy);
               return;
            }

            if (var3 != 3) {
               return;
            }
         }

         EmojiCompat.get().registerInitCallback(this.getInitCallback());
      }

   }

   void setEmojiReplaceStrategy(int var1) {
      this.mEmojiReplaceStrategy = var1;
   }

   public void setEnabled(boolean var1) {
      if (this.mEnabled != var1) {
         if (this.mInitCallback != null) {
            EmojiCompat.get().unregisterInitCallback(this.mInitCallback);
         }

         this.mEnabled = var1;
         if (var1) {
            processTextOnEnablingEvent(this.mEditText, EmojiCompat.get().getLoadState());
         }
      }

   }

   void setMaxEmojiCount(int var1) {
      this.mMaxEmojiCount = var1;
   }

   private static class InitCallbackImpl extends EmojiCompat.InitCallback {
      private final Reference mViewRef;

      InitCallbackImpl(EditText var1) {
         this.mViewRef = new WeakReference(var1);
      }

      public void onInitialized() {
         super.onInitialized();
         EmojiTextWatcher.processTextOnEnablingEvent((EditText)this.mViewRef.get(), 1);
      }
   }
}
