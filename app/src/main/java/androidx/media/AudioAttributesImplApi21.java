package androidx.media;

import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AudioAttributesImplApi21 implements AudioAttributesImpl {
   private static final String TAG = "AudioAttributesCompat21";
   static Method sAudioAttributesToLegacyStreamType;
   AudioAttributes mAudioAttributes;
   int mLegacyStreamType;

   AudioAttributesImplApi21() {
      this.mLegacyStreamType = -1;
   }

   AudioAttributesImplApi21(AudioAttributes var1) {
      this(var1, -1);
   }

   AudioAttributesImplApi21(AudioAttributes var1, int var2) {
      this.mAudioAttributes = var1;
      this.mLegacyStreamType = var2;
   }

   public static AudioAttributesImpl fromBundle(Bundle var0) {
      if (var0 == null) {
         return null;
      } else {
         AudioAttributes var1 = (AudioAttributes)var0.getParcelable("androidx.media.audio_attrs.FRAMEWORKS");
         return var1 == null ? null : new AudioAttributesImplApi21(var1, var0.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
      }
   }

   static Method getAudioAttributesToLegacyStreamTypeMethod() {
      try {
         if (sAudioAttributesToLegacyStreamType == null) {
            sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
         }
      } catch (NoSuchMethodException var1) {
         return null;
      }

      return sAudioAttributesToLegacyStreamType;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof AudioAttributesImplApi21)) {
         return false;
      } else {
         AudioAttributesImplApi21 var2 = (AudioAttributesImplApi21)var1;
         return this.mAudioAttributes.equals(var2.mAudioAttributes);
      }
   }

   public Object getAudioAttributes() {
      return this.mAudioAttributes;
   }

   public int getContentType() {
      return this.mAudioAttributes.getContentType();
   }

   public int getFlags() {
      return this.mAudioAttributes.getFlags();
   }

   public int getLegacyStreamType() {
      int var1 = this.mLegacyStreamType;
      if (var1 != -1) {
         return var1;
      } else {
         Method var2 = getAudioAttributesToLegacyStreamTypeMethod();
         if (var2 == null) {
            Log.w("AudioAttributesCompat21", "No AudioAttributes#toLegacyStreamType() on API: " + VERSION.SDK_INT);
            return -1;
         } else {
            Object var5;
            try {
               var1 = (Integer)var2.invoke((Object)null, this.mAudioAttributes);
               return var1;
            } catch (InvocationTargetException var3) {
               var5 = var3;
            } catch (IllegalAccessException var4) {
               var5 = var4;
            }

            Log.w("AudioAttributesCompat21", "getLegacyStreamType() failed on API: " + VERSION.SDK_INT, (Throwable)var5);
            return -1;
         }
      }
   }

   public int getRawLegacyStreamType() {
      return this.mLegacyStreamType;
   }

   public int getUsage() {
      return this.mAudioAttributes.getUsage();
   }

   public int getVolumeControlStream() {
      return VERSION.SDK_INT >= 26 ? this.mAudioAttributes.getVolumeControlStream() : AudioAttributesCompat.toVolumeStreamType(true, this.getFlags(), this.getUsage());
   }

   public int hashCode() {
      return this.mAudioAttributes.hashCode();
   }

   public Bundle toBundle() {
      Bundle var2 = new Bundle();
      var2.putParcelable("androidx.media.audio_attrs.FRAMEWORKS", this.mAudioAttributes);
      int var1 = this.mLegacyStreamType;
      if (var1 != -1) {
         var2.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", var1);
      }

      return var2;
   }

   public String toString() {
      return "AudioAttributesCompat: audioattributes=" + this.mAudioAttributes;
   }
}
