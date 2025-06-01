package com.hzbhd.ui.view.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import com.hzbhd.util.LogUtil;

public class TextDrawable extends BitmapDrawable {
   private int mGravity;
   private Paint mPaint;
   private String mText;

   public TextDrawable(Context var1, Bitmap var2, int var3, Paint var4) {
      super(var1.getResources(), var2);
      this.mGravity = var3;
      this.mPaint = var4;
   }

   public void draw(Canvas var1) {
      super.draw(var1);
      if (LogUtil.log5()) {
         LogUtil.d("draw: " + this.mText);
      }

      if (!TextUtils.isEmpty(this.mText)) {
         Rect var2 = new Rect();
         Paint var3 = this.mPaint;
         String var4 = this.mText;
         var3.getTextBounds(var4, 0, var4.length(), var2);
         if (this.mGravity == 17) {
            var1.drawText(this.mText, (float)this.getBounds().left + (float)(this.getBounds().right - this.getBounds().left - var2.right + var2.left) / 2.0F, (float)this.getBounds().bottom - (float)(this.getBounds().bottom - this.getBounds().top - var2.bottom + var2.top) / 2.0F, this.mPaint);
         }
      }

   }

   public void setText(String var1) {
      this.mText = var1;
   }
}
