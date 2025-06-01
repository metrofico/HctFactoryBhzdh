package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;

class MediaBrowserCompatApi23 {
   private MediaBrowserCompatApi23() {
   }

   public static Object createItemCallback(ItemCallback var0) {
      return new ItemCallbackProxy(var0);
   }

   public static void getItem(Object var0, String var1, Object var2) {
      ((MediaBrowser)var0).getItem(var1, (MediaBrowser.ItemCallback)var2);
   }

   interface ItemCallback {
      void onError(String var1);

      void onItemLoaded(Parcel var1);
   }

   static class ItemCallbackProxy extends MediaBrowser.ItemCallback {
      protected final ItemCallback mItemCallback;

      public ItemCallbackProxy(ItemCallback var1) {
         this.mItemCallback = var1;
      }

      public void onError(String var1) {
         this.mItemCallback.onError(var1);
      }

      public void onItemLoaded(MediaBrowser.MediaItem var1) {
         if (var1 == null) {
            this.mItemCallback.onItemLoaded((Parcel)null);
         } else {
            Parcel var2 = Parcel.obtain();
            var1.writeToParcel(var2, 0);
            this.mItemCallback.onItemLoaded(var2);
         }

      }
   }
}
