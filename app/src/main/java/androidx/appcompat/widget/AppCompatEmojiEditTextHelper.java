package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;

class AppCompatEmojiEditTextHelper {
   private final EmojiEditTextHelper mEmojiEditTextHelper;
   private final EditText mView;

   AppCompatEmojiEditTextHelper(EditText var1) {
      this.mView = var1;
      this.mEmojiEditTextHelper = new EmojiEditTextHelper(var1, false);
   }

   KeyListener getKeyListener(KeyListener var1) {
      KeyListener var2 = var1;
      if (this.isEmojiCapableKeyListener(var1)) {
         var2 = this.mEmojiEditTextHelper.getKeyListener(var1);
      }

      return var2;
   }

   boolean isEmojiCapableKeyListener(KeyListener var1) {
      return var1 instanceof NumberKeyListener ^ true;
   }

   boolean isEnabled() {
      return this.mEmojiEditTextHelper.isEnabled();
   }

   void loadFromAttributes(AttributeSet var1, int var2) {
      TypedArray var5 = this.mView.getContext().obtainStyledAttributes(var1, R.styleable.AppCompatTextView, var2, 0);

      boolean var3;
      label71: {
         Throwable var10000;
         label75: {
            boolean var10001;
            boolean var4;
            try {
               var4 = var5.hasValue(R.styleable.AppCompatTextView_emojiCompatEnabled);
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               break label75;
            }

            var3 = true;
            if (!var4) {
               break label71;
            }

            label66:
            try {
               var3 = var5.getBoolean(R.styleable.AppCompatTextView_emojiCompatEnabled, true);
               break label71;
            } catch (Throwable var10) {
               var10000 = var10;
               var10001 = false;
               break label66;
            }
         }

         Throwable var12 = var10000;
         var5.recycle();
         throw var12;
      }

      var5.recycle();
      this.setEnabled(var3);
   }

   InputConnection onCreateInputConnection(InputConnection var1, EditorInfo var2) {
      return this.mEmojiEditTextHelper.onCreateInputConnection(var1, var2);
   }

   void setEnabled(boolean var1) {
      this.mEmojiEditTextHelper.setEnabled(var1);
   }
}
