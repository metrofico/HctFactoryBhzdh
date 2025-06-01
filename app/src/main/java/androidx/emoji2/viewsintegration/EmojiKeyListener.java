package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

final class EmojiKeyListener implements KeyListener {
   private final EmojiCompatHandleKeyDownHelper mEmojiCompatHandleKeyDownHelper;
   private final KeyListener mKeyListener;

   EmojiKeyListener(KeyListener var1) {
      this(var1, new EmojiCompatHandleKeyDownHelper());
   }

   EmojiKeyListener(KeyListener var1, EmojiCompatHandleKeyDownHelper var2) {
      this.mKeyListener = var1;
      this.mEmojiCompatHandleKeyDownHelper = var2;
   }

   public void clearMetaKeyState(View var1, Editable var2, int var3) {
      this.mKeyListener.clearMetaKeyState(var1, var2, var3);
   }

   public int getInputType() {
      return this.mKeyListener.getInputType();
   }

   public boolean onKeyDown(View var1, Editable var2, int var3, KeyEvent var4) {
      boolean var5;
      if (!this.mEmojiCompatHandleKeyDownHelper.handleKeyDown(var2, var3, var4) && !this.mKeyListener.onKeyDown(var1, var2, var3, var4)) {
         var5 = false;
      } else {
         var5 = true;
      }

      return var5;
   }

   public boolean onKeyOther(View var1, Editable var2, KeyEvent var3) {
      return this.mKeyListener.onKeyOther(var1, var2, var3);
   }

   public boolean onKeyUp(View var1, Editable var2, int var3, KeyEvent var4) {
      return this.mKeyListener.onKeyUp(var1, var2, var3, var4);
   }

   public static class EmojiCompatHandleKeyDownHelper {
      public boolean handleKeyDown(Editable var1, int var2, KeyEvent var3) {
         return EmojiCompat.handleOnKeyDown(var1, var2, var3);
      }
   }
}
