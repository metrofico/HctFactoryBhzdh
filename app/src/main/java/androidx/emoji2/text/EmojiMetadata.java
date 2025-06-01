package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EmojiMetadata {
   public static final int HAS_GLYPH_ABSENT = 1;
   public static final int HAS_GLYPH_EXISTS = 2;
   public static final int HAS_GLYPH_UNKNOWN = 0;
   private static final ThreadLocal sMetadataItem = new ThreadLocal();
   private volatile int mHasGlyph = 0;
   private final int mIndex;
   private final MetadataRepo mMetadataRepo;

   EmojiMetadata(MetadataRepo var1, int var2) {
      this.mMetadataRepo = var1;
      this.mIndex = var2;
   }

   private MetadataItem getMetadataItem() {
      ThreadLocal var3 = sMetadataItem;
      MetadataItem var2 = (MetadataItem)var3.get();
      MetadataItem var1 = var2;
      if (var2 == null) {
         var1 = new MetadataItem();
         var3.set(var1);
      }

      this.mMetadataRepo.getMetadataList().list(var1, this.mIndex);
      return var1;
   }

   public void draw(Canvas var1, float var2, float var3, Paint var4) {
      Typeface var7 = this.mMetadataRepo.getTypeface();
      Typeface var6 = var4.getTypeface();
      var4.setTypeface(var7);
      int var5 = this.mIndex;
      var1.drawText(this.mMetadataRepo.getEmojiCharArray(), var5 * 2, 2, var2, var3, var4);
      var4.setTypeface(var6);
   }

   public int getCodepointAt(int var1) {
      return this.getMetadataItem().codepoints(var1);
   }

   public int getCodepointsLength() {
      return this.getMetadataItem().codepointsLength();
   }

   public short getCompatAdded() {
      return this.getMetadataItem().compatAdded();
   }

   public int getHasGlyph() {
      return this.mHasGlyph;
   }

   public short getHeight() {
      return this.getMetadataItem().height();
   }

   public int getId() {
      return this.getMetadataItem().id();
   }

   public short getSdkAdded() {
      return this.getMetadataItem().sdkAdded();
   }

   public Typeface getTypeface() {
      return this.mMetadataRepo.getTypeface();
   }

   public short getWidth() {
      return this.getMetadataItem().width();
   }

   public boolean isDefaultEmoji() {
      return this.getMetadataItem().emojiStyle();
   }

   public void resetHasGlyphCache() {
      this.mHasGlyph = 0;
   }

   public void setHasGlyph(boolean var1) {
      byte var2;
      if (var1) {
         var2 = 2;
      } else {
         var2 = 1;
      }

      this.mHasGlyph = var2;
   }

   public String toString() {
      StringBuilder var3 = new StringBuilder();
      var3.append(super.toString());
      var3.append(", id:");
      var3.append(Integer.toHexString(this.getId()));
      var3.append(", codepoints:");
      int var2 = this.getCodepointsLength();

      for(int var1 = 0; var1 < var2; ++var1) {
         var3.append(Integer.toHexString(this.getCodepointAt(var1)));
         var3.append(" ");
      }

      return var3.toString();
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface HasGlyph {
   }
}
