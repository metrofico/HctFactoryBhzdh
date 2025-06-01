package androidx.emoji2.text;

import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.core.util.Preconditions;

public abstract class EmojiSpan extends ReplacementSpan {
   private short mHeight = -1;
   private final EmojiMetadata mMetadata;
   private float mRatio = 1.0F;
   private final Paint.FontMetricsInt mTmpFontMetrics = new Paint.FontMetricsInt();
   private short mWidth = -1;

   EmojiSpan(EmojiMetadata var1) {
      Preconditions.checkNotNull(var1, "metadata cannot be null");
      this.mMetadata = var1;
   }

   public final int getHeight() {
      return this.mHeight;
   }

   public final int getId() {
      return this.getMetadata().getId();
   }

   public final EmojiMetadata getMetadata() {
      return this.mMetadata;
   }

   final float getRatio() {
      return this.mRatio;
   }

   public int getSize(Paint var1, CharSequence var2, int var3, int var4, Paint.FontMetricsInt var5) {
      var1.getFontMetricsInt(this.mTmpFontMetrics);
      this.mRatio = (float)Math.abs(this.mTmpFontMetrics.descent - this.mTmpFontMetrics.ascent) * 1.0F / (float)this.mMetadata.getHeight();
      this.mHeight = (short)((int)((float)this.mMetadata.getHeight() * this.mRatio));
      this.mWidth = (short)((int)((float)this.mMetadata.getWidth() * this.mRatio));
      if (var5 != null) {
         var5.ascent = this.mTmpFontMetrics.ascent;
         var5.descent = this.mTmpFontMetrics.descent;
         var5.top = this.mTmpFontMetrics.top;
         var5.bottom = this.mTmpFontMetrics.bottom;
      }

      return this.mWidth;
   }

   final int getWidth() {
      return this.mWidth;
   }
}
