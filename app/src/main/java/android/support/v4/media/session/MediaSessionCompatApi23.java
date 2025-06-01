package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaSessionCompatApi23 {
   private MediaSessionCompatApi23() {
   }

   public static Object createCallback(Callback var0) {
      return new CallbackProxy(var0);
   }

   public interface Callback extends MediaSessionCompatApi21.Callback {
      void onPlayFromUri(Uri var1, Bundle var2);
   }

   static class CallbackProxy extends MediaSessionCompatApi21.CallbackProxy {
      public CallbackProxy(Callback var1) {
         super(var1);
      }

      public void onPlayFromUri(Uri var1, Bundle var2) {
         MediaSessionCompat.ensureClassLoader(var2);
         ((Callback)this.mCallback).onPlayFromUri(var1, var2);
      }
   }
}
