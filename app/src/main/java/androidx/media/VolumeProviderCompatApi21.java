package androidx.media;

import android.media.VolumeProvider;

class VolumeProviderCompatApi21 {
   private VolumeProviderCompatApi21() {
   }

   public static Object createVolumeProvider(int var0, int var1, int var2, Delegate var3) {
      return new VolumeProvider(var0, var1, var2, var3) {
         final Delegate val$delegate;

         {
            this.val$delegate = var4;
         }

         public void onAdjustVolume(int var1) {
            this.val$delegate.onAdjustVolume(var1);
         }

         public void onSetVolumeTo(int var1) {
            this.val$delegate.onSetVolumeTo(var1);
         }
      };
   }

   public static void setCurrentVolume(Object var0, int var1) {
      ((VolumeProvider)var0).setCurrentVolume(var1);
   }

   public interface Delegate {
      void onAdjustVolume(int var1);

      void onSetVolumeTo(int var1);
   }
}
