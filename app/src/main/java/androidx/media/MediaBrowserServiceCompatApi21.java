package androidx.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserServiceCompatApi21 {
   private MediaBrowserServiceCompatApi21() {
   }

   public static Object createService(Context var0, ServiceCompatProxy var1) {
      return new MediaBrowserServiceAdaptor(var0, var1);
   }

   public static void notifyChildrenChanged(Object var0, String var1) {
      ((MediaBrowserService)var0).notifyChildrenChanged(var1);
   }

   public static IBinder onBind(Object var0, Intent var1) {
      return ((MediaBrowserService)var0).onBind(var1);
   }

   public static void onCreate(Object var0) {
      ((MediaBrowserService)var0).onCreate();
   }

   public static void setSessionToken(Object var0, Object var1) {
      ((MediaBrowserService)var0).setSessionToken((MediaSession.Token)var1);
   }

   static class BrowserRoot {
      final Bundle mExtras;
      final String mRootId;

      BrowserRoot(String var1, Bundle var2) {
         this.mRootId = var1;
         this.mExtras = var2;
      }
   }

   static class MediaBrowserServiceAdaptor extends MediaBrowserService {
      final ServiceCompatProxy mServiceProxy;

      MediaBrowserServiceAdaptor(Context var1, ServiceCompatProxy var2) {
         this.attachBaseContext(var1);
         this.mServiceProxy = var2;
      }

      public MediaBrowserService.BrowserRoot onGetRoot(String var1, int var2, Bundle var3) {
         MediaSessionCompat.ensureClassLoader(var3);
         ServiceCompatProxy var5 = this.mServiceProxy;
         Object var4 = null;
         if (var3 == null) {
            var3 = null;
         } else {
            var3 = new Bundle(var3);
         }

         BrowserRoot var6 = var5.onGetRoot(var1, var2, var3);
         MediaBrowserService.BrowserRoot var7;
         if (var6 == null) {
            var7 = (MediaBrowserService.BrowserRoot)var4;
         } else {
            var7 = new MediaBrowserService.BrowserRoot(var6.mRootId, var6.mExtras);
         }

         return var7;
      }

      public void onLoadChildren(String var1, MediaBrowserService.Result var2) {
         this.mServiceProxy.onLoadChildren(var1, new ResultWrapper(var2));
      }
   }

   static class ResultWrapper {
      MediaBrowserService.Result mResultObj;

      ResultWrapper(MediaBrowserService.Result var1) {
         this.mResultObj = var1;
      }

      public void detach() {
         this.mResultObj.detach();
      }

      List parcelListToItemList(List var1) {
         if (var1 == null) {
            return null;
         } else {
            ArrayList var2 = new ArrayList();
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
               Parcel var3 = (Parcel)var4.next();
               var3.setDataPosition(0);
               var2.add(MediaItem.CREATOR.createFromParcel(var3));
               var3.recycle();
            }

            return var2;
         }
      }

      public void sendResult(Object var1) {
         if (var1 instanceof List) {
            this.mResultObj.sendResult(this.parcelListToItemList((List)var1));
         } else if (var1 instanceof Parcel) {
            Parcel var2 = (Parcel)var1;
            var2.setDataPosition(0);
            this.mResultObj.sendResult(MediaItem.CREATOR.createFromParcel(var2));
            var2.recycle();
         } else {
            this.mResultObj.sendResult((Object)null);
         }

      }
   }

   public interface ServiceCompatProxy {
      BrowserRoot onGetRoot(String var1, int var2, Bundle var3);

      void onLoadChildren(String var1, ResultWrapper var2);
   }
}
