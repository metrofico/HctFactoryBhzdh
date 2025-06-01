package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextPaint;

public final class TypefaceEmojiSpan extends EmojiSpan {
   private static Paint sDebugPaint;

   public TypefaceEmojiSpan(EmojiMetadata var1) {
      super(var1);
   }

   private static Paint getDebugPaint() {
      if (sDebugPaint == null) {
         TextPaint var0 = new TextPaint();
         sDebugPaint = var0;
         var0.setColor(EmojiCompat.get().getEmojiSpanIndicatorColor());
         sDebugPaint.setStyle(Style.FILL);
      }

      return sDebugPaint;
   }

   public void draw(Canvas var1, CharSequence var2, int var3, int var4, float var5, int var6, int var7, int var8, Paint var9) {
      if (EmojiCompat.get().isEmojiSpanIndicatorEnabled()) {
         var1.drawRect(var5, (float)var6, var5 + (float)this.getWidth(), (float)var8, getDebugPaint());
      }

      this.getMetadata().draw(var1, var5, (float)var7, var9);
   }
}
