package androidx.media;

import android.os.Build.VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class VolumeProviderCompat {
   public static final int VOLUME_CONTROL_ABSOLUTE = 2;
   public static final int VOLUME_CONTROL_FIXED = 0;
   public static final int VOLUME_CONTROL_RELATIVE = 1;
   private Callback mCallback;
   private final int mControlType;
   private int mCurrentVolume;
   private final int mMaxVolume;
   private Object mVolumeProviderObj;

   public VolumeProviderCompat(int var1, int var2, int var3) {
      this.mControlType = var1;
      this.mMaxVolume = var2;
      this.mCurrentVolume = var3;
   }

   public final int getCurrentVolume() {
      return this.mCurrentVolume;
   }

   public final int getMaxVolume() {
      return this.mMaxVolume;
   }

   public final int getVolumeControl() {
      return this.mControlType;
   }

   public Object getVolumeProvider() {
      if (this.mVolumeProviderObj == null && VERSION.SDK_INT >= 21) {
         this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new VolumeProviderCompatApi21.Delegate(this) {
            final VolumeProviderCompat this$0;

            {
               this.this$0 = var1;
            }

            public void onAdjustVolume(int var1) {
               this.this$0.onAdjustVolume(var1);
            }

            public void onSetVolumeTo(int var1) {
               this.this$0.onSetVolumeTo(var1);
            }
         });
      }

      return this.mVolumeProviderObj;
   }

   public void onAdjustVolume(int var1) {
   }

   public void onSetVolumeTo(int var1) {
   }

   public void setCallback(Callback var1) {
      this.mCallback = var1;
   }

   public final void setCurrentVolume(int var1) {
      this.mCurrentVolume = var1;
      Object var2 = this.getVolumeProvider();
      if (var2 != null && VERSION.SDK_INT >= 21) {
         VolumeProviderCompatApi21.setCurrentVolume(var2, var1);
      }

      Callback var3 = this.mCallback;
      if (var3 != null) {
         var3.onVolumeChanged(this);
      }

   }

   public abstract static class Callback {
      public abstract void onVolumeChanged(VolumeProviderCompat var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ControlType {
   }
}
