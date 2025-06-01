package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;

final class EmojiInputConnection extends InputConnectionWrapper {
   private final EmojiCompatDeleteHelper mEmojiCompatDeleteHelper;
   private final TextView mTextView;

   EmojiInputConnection(TextView var1, InputConnection var2, EditorInfo var3) {
      this(var1, var2, var3, new EmojiCompatDeleteHelper());
   }

   EmojiInputConnection(TextView var1, InputConnection var2, EditorInfo var3, EmojiCompatDeleteHelper var4) {
      super(var2, false);
      this.mTextView = var1;
      this.mEmojiCompatDeleteHelper = var4;
      var4.updateEditorInfoAttrs(var3);
   }

   private Editable getEditable() {
      return this.mTextView.getEditableText();
   }

   public boolean deleteSurroundingText(int var1, int var2) {
      boolean var3;
      if (!this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, this.getEditable(), var1, var2, false) && !super.deleteSurroundingText(var1, var2)) {
         var3 = false;
      } else {
         var3 = true;
      }

      return var3;
   }

   public boolean deleteSurroundingTextInCodePoints(int var1, int var2) {
      boolean var3;
      if (!this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, this.getEditable(), var1, var2, true) && !super.deleteSurroundingTextInCodePoints(var1, var2)) {
         var3 = false;
      } else {
         var3 = true;
      }

      return var3;
   }

   public static class EmojiCompatDeleteHelper {
      public boolean handleDeleteSurroundingText(InputConnection var1, Editable var2, int var3, int var4, boolean var5) {
         return EmojiCompat.handleDeleteSurroundingText(var1, var2, var3, var4, var5);
      }

      public void updateEditorInfoAttrs(EditorInfo var1) {
         if (EmojiCompat.isConfigured()) {
            EmojiCompat.get().updateEditorInfo(var1);
         }

      }
   }
}
