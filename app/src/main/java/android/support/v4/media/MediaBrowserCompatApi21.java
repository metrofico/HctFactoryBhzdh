package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import java.util.List;

class MediaBrowserCompatApi21 {
   static final String NULL_MEDIA_ITEM_ID = "android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM";

   private MediaBrowserCompatApi21() {
   }

   public static void connect(Object var0) {
      ((MediaBrowser)var0).connect();
   }

   public static Object createBrowser(Context var0, ComponentName var1, Object var2, Bundle var3) {
      return new MediaBrowser(var0, var1, (MediaBrowser.ConnectionCallback)var2, var3);
   }

   public static Object createConnectionCallback(ConnectionCallback var0) {
      return new ConnectionCallbackProxy(var0);
   }

   public static Object createSubscriptionCallback(SubscriptionCallback var0) {
      return new SubscriptionCallbackProxy(var0);
   }

   public static void disconnect(Object var0) {
      ((MediaBrowser)var0).disconnect();
   }

   public static Bundle getExtras(Object var0) {
      return ((MediaBrowser)var0).getExtras();
   }

   public static String getRoot(Object var0) {
      return ((MediaBrowser)var0).getRoot();
   }

   public static ComponentName getServiceComponent(Object var0) {
      return ((MediaBrowser)var0).getServiceComponent();
   }

   public static Object getSessionToken(Object var0) {
      return ((MediaBrowser)var0).getSessionToken();
   }

   public static boolean isConnected(Object var0) {
      return ((MediaBrowser)var0).isConnected();
   }

   public static void subscribe(Object var0, String var1, Object var2) {
      ((MediaBrowser)var0).subscribe(var1, (MediaBrowser.SubscriptionCallback)var2);
   }

   public static void unsubscribe(Object var0, String var1) {
      ((MediaBrowser)var0).unsubscribe(var1);
   }

   interface ConnectionCallback {
      void onConnected();

      void onConnectionFailed();

      void onConnectionSuspended();
   }

   static class ConnectionCallbackProxy extends MediaBrowser.ConnectionCallback {
      protected final ConnectionCallback mConnectionCallback;

      public ConnectionCallbackProxy(ConnectionCallback var1) {
         this.mConnectionCallback = var1;
      }

      public void onConnected() {
         this.mConnectionCallback.onConnected();
      }

      public void onConnectionFailed() {
         this.mConnectionCallback.onConnectionFailed();
      }

      public void onConnectionSuspended() {
         this.mConnectionCallback.onConnectionSuspended();
      }
   }

   static class MediaItem {
      private MediaItem() {
      }

      public static Object getDescription(Object var0) {
         return ((MediaBrowser.MediaItem)var0).getDescription();
      }

      public static int getFlags(Object var0) {
         return ((MediaBrowser.MediaItem)var0).getFlags();
      }
   }

   interface SubscriptionCallback {
      void onChildrenLoaded(String var1, List var2);

      void onError(String var1);
   }

   static class SubscriptionCallbackProxy extends MediaBrowser.SubscriptionCallback {
      protected final SubscriptionCallback mSubscriptionCallback;

      public SubscriptionCallbackProxy(SubscriptionCallback var1) {
         this.mSubscriptionCallback = var1;
      }

      public void onChildrenLoaded(String var1, List var2) {
         this.mSubscriptionCallback.onChildrenLoaded(var1, var2);
      }

      public void onError(String var1) {
         this.mSubscriptionCallback.onError(var1);
      }
   }
}
