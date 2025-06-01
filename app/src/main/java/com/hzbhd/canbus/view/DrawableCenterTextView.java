package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class DrawableCenterTextView extends AppCompatTextView {
   public DrawableCenterTextView(Context var1) {
      super(var1);
   }

   public DrawableCenterTextView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public DrawableCenterTextView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   protected void onDraw(Canvas var1) {
      Drawable[] var6 = this.getCompoundDrawables();
      if (var6 != null) {
         Drawable var7 = var6[0];
         if (var7 != null) {
            float var3 = this.getPaint().measureText(this.getText().toString());
            int var5 = this.getCompoundDrawablePadding();
            float var2 = (float)var7.getIntrinsicWidth();
            float var4 = (float)var5;
            var1.translate(((float)this.getWidth() - (var3 + var2 + var4)) / 2.0F, 0.0F);
         }
      }

      super.onDraw(var1);
   }
}
