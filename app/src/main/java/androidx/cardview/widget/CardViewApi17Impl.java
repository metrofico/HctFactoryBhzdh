package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class CardViewApi17Impl extends CardViewBaseImpl {
   public void initStatic() {
      RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper(this) {
         final CardViewApi17Impl this$0;

         {
            this.this$0 = var1;
         }

         public void drawRoundRect(Canvas var1, RectF var2, float var3, Paint var4) {
            var1.drawRoundRect(var2, var3, var3, var4);
         }
      };
   }
}
