package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;

class AppCompatEmojiTextHelper {
   private final EmojiTextViewHelper mEmojiTextViewHelper;
   private final TextView mView;

   AppCompatEmojiTextHelper(TextView var1) {
      this.mView = var1;
      this.mEmojiTextViewHelper = new EmojiTextViewHelper(var1, false);
   }

   InputFilter[] getFilters(InputFilter[] var1) {
      return this.mEmojiTextViewHelper.getFilters(var1);
   }

   public boolean isEnabled() {
      return this.mEmojiTextViewHelper.isEnabled();
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

   void setAllCaps(boolean var1) {
      this.mEmojiTextViewHelper.setAllCaps(var1);
   }

   void setEnabled(boolean var1) {
      this.mEmojiTextViewHelper.setEnabled(var1);
   }

   public TransformationMethod wrapTransformationMethod(TransformationMethod var1) {
      return this.mEmojiTextViewHelper.wrapTransformationMethod(var1);
   }
}
