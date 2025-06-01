package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.core.util.Preconditions;

final class AppCompatTextClassifierHelper {
   private TextClassifier mTextClassifier;
   private TextView mTextView;

   AppCompatTextClassifierHelper(TextView var1) {
      this.mTextView = (TextView)Preconditions.checkNotNull(var1);
   }

   public TextClassifier getTextClassifier() {
      TextClassifier var2 = this.mTextClassifier;
      TextClassifier var1 = var2;
      if (var2 == null) {
         TextClassificationManager var3 = (TextClassificationManager)this.mTextView.getContext().getSystemService(TextClassificationManager.class);
         if (var3 != null) {
            return var3.getTextClassifier();
         }

         var1 = TextClassifier.NO_OP;
      }

      return var1;
   }

   public void setTextClassifier(TextClassifier var1) {
      this.mTextClassifier = var1;
   }
}
