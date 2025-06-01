package androidx.media;

import android.content.Context;
import android.service.media.MediaBrowserService;

class MediaBrowserServiceCompatApi23 {
   private MediaBrowserServiceCompatApi23() {
   }

   public static Object createService(Context var0, ServiceCompatProxy var1) {
      return new MediaBrowserServiceAdaptor(var0, var1);
   }

   static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor {
      MediaBrowserServiceAdaptor(Context var1, ServiceCompatProxy var2) {
         super(var1, var2);
      }

      public void onLoadItem(String var1, MediaBrowserService.Result var2) {
         ((ServiceCompatProxy)this.mServiceProxy).onLoadItem(var1, new MediaBrowserServiceCompatApi21.ResultWrapper(var2));
      }
   }

   public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
      void onLoadItem(String var1, MediaBrowserServiceCompatApi21.ResultWrapper var2);
   }
}
