package androidx.emoji2.viewsintegration;

import android.os.Build.VERSION;
import android.text.method.KeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.core.util.Preconditions;

public final class EmojiEditTextHelper {
   private int mEmojiReplaceStrategy;
   private final HelperInternal mHelper;
   private int mMaxEmojiCount;

   public EmojiEditTextHelper(EditText var1) {
      this(var1, true);
   }

   public EmojiEditTextHelper(EditText var1, boolean var2) {
      this.mMaxEmojiCount = Integer.MAX_VALUE;
      this.mEmojiReplaceStrategy = 0;
      Preconditions.checkNotNull(var1, "editText cannot be null");
      if (VERSION.SDK_INT < 19) {
         this.mHelper = new HelperInternal();
      } else {
         this.mHelper = new HelperInternal19(var1, var2);
      }

   }

   public int getEmojiReplaceStrategy() {
      return this.mEmojiReplaceStrategy;
   }

   public KeyListener getKeyListener(KeyListener var1) {
      return this.mHelper.getKeyListener(var1);
   }

   public int getMaxEmojiCount() {
      return this.mMaxEmojiCount;
   }

   public boolean isEnabled() {
      return this.mHelper.isEnabled();
   }

   public InputConnection onCreateInputConnection(InputConnection var1, EditorInfo var2) {
      return var1 == null ? null : this.mHelper.onCreateInputConnection(var1, var2);
   }

   public void setEmojiReplaceStrategy(int var1) {
      this.mEmojiReplaceStrategy = var1;
      this.mHelper.setEmojiReplaceStrategy(var1);
   }

   public void setEnabled(boolean var1) {
      this.mHelper.setEnabled(var1);
   }

   public void setMaxEmojiCount(int var1) {
      Preconditions.checkArgumentNonnegative(var1, "maxEmojiCount should be greater than 0");
      this.mMaxEmojiCount = var1;
      this.mHelper.setMaxEmojiCount(var1);
   }

   static class HelperInternal {
      KeyListener getKeyListener(KeyListener var1) {
         return var1;
      }

      boolean isEnabled() {
         return false;
      }

      InputConnection onCreateInputConnection(InputConnection var1, EditorInfo var2) {
         return var1;
      }

      void setEmojiReplaceStrategy(int var1) {
      }

      void setEnabled(boolean var1) {
      }

      void setMaxEmojiCount(int var1) {
      }
   }

   private static class HelperInternal19 extends HelperInternal {
      private final EditText mEditText;
      private final EmojiTextWatcher mTextWatcher;

      HelperInternal19(EditText var1, boolean var2) {
         this.mEditText = var1;
         EmojiTextWatcher var3 = new EmojiTextWatcher(var1, var2);
         this.mTextWatcher = var3;
         var1.addTextChangedListener(var3);
         var1.setEditableFactory(EmojiEditableFactory.getInstance());
      }

      KeyListener getKeyListener(KeyListener var1) {
         if (var1 instanceof EmojiKeyListener) {
            return var1;
         } else {
            return var1 == null ? null : new EmojiKeyListener(var1);
         }
      }

      boolean isEnabled() {
         return this.mTextWatcher.isEnabled();
      }

      InputConnection onCreateInputConnection(InputConnection var1, EditorInfo var2) {
         return (InputConnection)(var1 instanceof EmojiInputConnection ? var1 : new EmojiInputConnection(this.mEditText, var1, var2));
      }

      void setEmojiReplaceStrategy(int var1) {
         this.mTextWatcher.setEmojiReplaceStrategy(var1);
      }

      void setEnabled(boolean var1) {
         this.mTextWatcher.setEnabled(var1);
      }

      void setMaxEmojiCount(int var1) {
         this.mTextWatcher.setMaxEmojiCount(var1);
      }
   }
}
