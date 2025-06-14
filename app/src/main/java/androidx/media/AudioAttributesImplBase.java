package androidx.media;

import android.os.Bundle;
import java.util.Arrays;

class AudioAttributesImplBase implements AudioAttributesImpl {
   int mContentType;
   int mFlags;
   int mLegacyStream;
   int mUsage;

   AudioAttributesImplBase() {
      this.mUsage = 0;
      this.mContentType = 0;
      this.mFlags = 0;
      this.mLegacyStream = -1;
   }

   AudioAttributesImplBase(int var1, int var2, int var3, int var4) {
      this.mContentType = var1;
      this.mFlags = var2;
      this.mUsage = var3;
      this.mLegacyStream = var4;
   }

   public static AudioAttributesImpl fromBundle(Bundle var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1 = var0.getInt("androidx.media.audio_attrs.USAGE", 0);
         return new AudioAttributesImplBase(var0.getInt("androidx.media.audio_attrs.CONTENT_TYPE", 0), var0.getInt("androidx.media.audio_attrs.FLAGS", 0), var1, var0.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
      }
   }

   public boolean equals(Object var1) {
      boolean var2 = var1 instanceof AudioAttributesImplBase;
      boolean var3 = false;
      if (!var2) {
         return false;
      } else {
         AudioAttributesImplBase var4 = (AudioAttributesImplBase)var1;
         var2 = var3;
         if (this.mContentType == var4.getContentType()) {
            var2 = var3;
            if (this.mFlags == var4.getFlags()) {
               var2 = var3;
               if (this.mUsage == var4.getUsage()) {
                  var2 = var3;
                  if (this.mLegacyStream == var4.mLegacyStream) {
                     var2 = true;
                  }
               }
            }
         }

         return var2;
      }
   }

   public Object getAudioAttributes() {
      return null;
   }

   public int getContentType() {
      return this.mContentType;
   }

   public int getFlags() {
      int var2 = this.mFlags;
      int var3 = this.getLegacyStreamType();
      int var1;
      if (var3 == 6) {
         var1 = var2 | 4;
      } else {
         var1 = var2;
         if (var3 == 7) {
            var1 = var2 | 1;
         }
      }

      return var1 & 273;
   }

   public int getLegacyStreamType() {
      int var1 = this.mLegacyStream;
      return var1 != -1 ? var1 : AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
   }

   public int getRawLegacyStreamType() {
      return this.mLegacyStream;
   }

   public int getUsage() {
      return this.mUsage;
   }

   public int getVolumeControlStream() {
      return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream});
   }

   public Bundle toBundle() {
      Bundle var2 = new Bundle();
      var2.putInt("androidx.media.audio_attrs.USAGE", this.mUsage);
      var2.putInt("androidx.media.audio_attrs.CONTENT_TYPE", this.mContentType);
      var2.putInt("androidx.media.audio_attrs.FLAGS", this.mFlags);
      int var1 = this.mLegacyStream;
      if (var1 != -1) {
         var2.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", var1);
      }

      return var2;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("AudioAttributesCompat:");
      if (this.mLegacyStream != -1) {
         var1.append(" stream=").append(this.mLegacyStream);
         var1.append(" derived");
      }

      var1.append(" usage=").append(AudioAttributesCompat.usageToString(this.mUsage)).append(" content=").append(this.mContentType).append(" flags=0x").append(Integer.toHexString(this.mFlags).toUpperCase());
      return var1.toString();
   }
}
