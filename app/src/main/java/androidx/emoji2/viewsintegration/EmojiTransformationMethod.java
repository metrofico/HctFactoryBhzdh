package androidx.emoji2.viewsintegration;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

class EmojiTransformationMethod implements TransformationMethod {
   private final TransformationMethod mTransformationMethod;

   EmojiTransformationMethod(TransformationMethod var1) {
      this.mTransformationMethod = var1;
   }

   public TransformationMethod getOriginalTransformationMethod() {
      return this.mTransformationMethod;
   }

   public CharSequence getTransformation(CharSequence var1, View var2) {
      if (var2.isInEditMode()) {
         return var1;
      } else {
         TransformationMethod var4 = this.mTransformationMethod;
         CharSequence var3 = var1;
         if (var4 != null) {
            var3 = var4.getTransformation(var1, var2);
         }

         var1 = var3;
         if (var3 != null) {
            if (EmojiCompat.get().getLoadState() != 1) {
               var1 = var3;
            } else {
               var1 = EmojiCompat.get().process(var3);
            }
         }

         return var1;
      }
   }

   public void onFocusChanged(View var1, CharSequence var2, boolean var3, int var4, Rect var5) {
      TransformationMethod var6 = this.mTransformationMethod;
      if (var6 != null) {
         var6.onFocusChanged(var1, var2, var3, var4, var5);
      }

   }
}
