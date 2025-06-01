package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserCompatUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class MediaBrowserCompat {
   public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
   public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
   static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
   public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
   public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
   public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
   public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
   static final String TAG = "MediaBrowserCompat";
   private final MediaBrowserImpl mImpl;

   public MediaBrowserCompat(Context var1, ComponentName var2, ConnectionCallback var3, Bundle var4) {
      if (VERSION.SDK_INT >= 26) {
         this.mImpl = new MediaBrowserImplApi26(var1, var2, var3, var4);
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new MediaBrowserImplApi23(var1, var2, var3, var4);
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new MediaBrowserImplApi21(var1, var2, var3, var4);
      } else {
         this.mImpl = new MediaBrowserImplBase(var1, var2, var3, var4);
      }

   }

   public void connect() {
      this.mImpl.connect();
   }

   public void disconnect() {
      this.mImpl.disconnect();
   }

   public Bundle getExtras() {
      return this.mImpl.getExtras();
   }

   public void getItem(String var1, ItemCallback var2) {
      this.mImpl.getItem(var1, var2);
   }

   public Bundle getNotifyChildrenChangedOptions() {
      return this.mImpl.getNotifyChildrenChangedOptions();
   }

   public String getRoot() {
      return this.mImpl.getRoot();
   }

   public ComponentName getServiceComponent() {
      return this.mImpl.getServiceComponent();
   }

   public MediaSessionCompat.Token getSessionToken() {
      return this.mImpl.getSessionToken();
   }

   public boolean isConnected() {
      return this.mImpl.isConnected();
   }

   public void search(String var1, Bundle var2, SearchCallback var3) {
      if (!TextUtils.isEmpty(var1)) {
         if (var3 != null) {
            this.mImpl.search(var1, var2, var3);
         } else {
            throw new IllegalArgumentException("callback cannot be null");
         }
      } else {
         throw new IllegalArgumentException("query cannot be empty");
      }
   }

   public void sendCustomAction(String var1, Bundle var2, CustomActionCallback var3) {
      if (!TextUtils.isEmpty(var1)) {
         this.mImpl.sendCustomAction(var1, var2, var3);
      } else {
         throw new IllegalArgumentException("action cannot be empty");
      }
   }

   public void subscribe(String var1, Bundle var2, SubscriptionCallback var3) {
      if (!TextUtils.isEmpty(var1)) {
         if (var3 != null) {
            if (var2 != null) {
               this.mImpl.subscribe(var1, var2, var3);
            } else {
               throw new IllegalArgumentException("options are null");
            }
         } else {
            throw new IllegalArgumentException("callback is null");
         }
      } else {
         throw new IllegalArgumentException("parentId is empty");
      }
   }

   public void subscribe(String var1, SubscriptionCallback var2) {
      if (!TextUtils.isEmpty(var1)) {
         if (var2 != null) {
            this.mImpl.subscribe(var1, (Bundle)null, var2);
         } else {
            throw new IllegalArgumentException("callback is null");
         }
      } else {
         throw new IllegalArgumentException("parentId is empty");
      }
   }

   public void unsubscribe(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         this.mImpl.unsubscribe(var1, (SubscriptionCallback)null);
      } else {
         throw new IllegalArgumentException("parentId is empty");
      }
   }

   public void unsubscribe(String var1, SubscriptionCallback var2) {
      if (!TextUtils.isEmpty(var1)) {
         if (var2 != null) {
            this.mImpl.unsubscribe(var1, var2);
         } else {
            throw new IllegalArgumentException("callback is null");
         }
      } else {
         throw new IllegalArgumentException("parentId is empty");
      }
   }

   private static class CallbackHandler extends Handler {
      private final WeakReference mCallbackImplRef;
      private WeakReference mCallbacksMessengerRef;

      CallbackHandler(MediaBrowserServiceCallbackImpl var1) {
         this.mCallbackImplRef = new WeakReference(var1);
      }

      public void handleMessage(Message var1) {
         WeakReference var3 = this.mCallbacksMessengerRef;
         if (var3 != null && var3.get() != null && this.mCallbackImplRef.get() != null) {
            Bundle var5 = var1.getData();
            MediaSessionCompat.ensureClassLoader(var5);
            MediaBrowserServiceCallbackImpl var13 = (MediaBrowserServiceCallbackImpl)this.mCallbackImplRef.get();
            Messenger var4 = (Messenger)this.mCallbacksMessengerRef.get();

            label50: {
               boolean var10001;
               int var2;
               try {
                  var2 = var1.what;
               } catch (BadParcelableException var12) {
                  var10001 = false;
                  break label50;
               }

               Bundle var6;
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        try {
                           StringBuilder var14 = new StringBuilder();
                           Log.w("MediaBrowserCompat", var14.append("Unhandled message: ").append(var1).append("\n  Client version: ").append(1).append("\n  Service version: ").append(var1.arg1).toString());
                           return;
                        } catch (BadParcelableException var8) {
                           var10001 = false;
                        }
                     } else {
                        try {
                           var6 = var5.getBundle("data_options");
                           MediaSessionCompat.ensureClassLoader(var6);
                           Bundle var7 = var5.getBundle("data_notify_children_changed_options");
                           MediaSessionCompat.ensureClassLoader(var7);
                           var13.onLoadChildren(var4, var5.getString("data_media_item_id"), var5.getParcelableArrayList("data_media_item_list"), var6, var7);
                           return;
                        } catch (BadParcelableException var9) {
                           var10001 = false;
                        }
                     }
                  } else {
                     try {
                        var13.onConnectionFailed(var4);
                        return;
                     } catch (BadParcelableException var10) {
                        var10001 = false;
                     }
                  }
               } else {
                  try {
                     var6 = var5.getBundle("data_root_hints");
                     MediaSessionCompat.ensureClassLoader(var6);
                     var13.onServiceConnected(var4, var5.getString("data_media_item_id"), (MediaSessionCompat.Token)var5.getParcelable("data_media_session_token"), var6);
                     return;
                  } catch (BadParcelableException var11) {
                     var10001 = false;
                  }
               }
            }

            Log.e("MediaBrowserCompat", "Could not unparcel the data.");
            if (var1.what == 1) {
               var13.onConnectionFailed(var4);
            }
         }

      }

      void setCallbacksMessenger(Messenger var1) {
         this.mCallbacksMessengerRef = new WeakReference(var1);
      }
   }

   public static class ConnectionCallback {
      ConnectionCallbackInternal mConnectionCallbackInternal;
      final Object mConnectionCallbackObj;

      public ConnectionCallback() {
         if (VERSION.SDK_INT >= 21) {
            this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21(this));
         } else {
            this.mConnectionCallbackObj = null;
         }

      }

      public void onConnected() {
      }

      public void onConnectionFailed() {
      }

      public void onConnectionSuspended() {
      }

      void setInternalConnectionCallback(ConnectionCallbackInternal var1) {
         this.mConnectionCallbackInternal = var1;
      }

      interface ConnectionCallbackInternal {
         void onConnected();

         void onConnectionFailed();

         void onConnectionSuspended();
      }

      private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
         final ConnectionCallback this$0;

         StubApi21(ConnectionCallback var1) {
            this.this$0 = var1;
         }

         public void onConnected() {
            if (this.this$0.mConnectionCallbackInternal != null) {
               this.this$0.mConnectionCallbackInternal.onConnected();
            }

            this.this$0.onConnected();
         }

         public void onConnectionFailed() {
            if (this.this$0.mConnectionCallbackInternal != null) {
               this.this$0.mConnectionCallbackInternal.onConnectionFailed();
            }

            this.this$0.onConnectionFailed();
         }

         public void onConnectionSuspended() {
            if (this.this$0.mConnectionCallbackInternal != null) {
               this.this$0.mConnectionCallbackInternal.onConnectionSuspended();
            }

            this.this$0.onConnectionSuspended();
         }
      }
   }

   public abstract static class CustomActionCallback {
      public void onError(String var1, Bundle var2, Bundle var3) {
      }

      public void onProgressUpdate(String var1, Bundle var2, Bundle var3) {
      }

      public void onResult(String var1, Bundle var2, Bundle var3) {
      }
   }

   private static class CustomActionResultReceiver extends ResultReceiver {
      private final String mAction;
      private final CustomActionCallback mCallback;
      private final Bundle mExtras;

      CustomActionResultReceiver(String var1, Bundle var2, CustomActionCallback var3, Handler var4) {
         super(var4);
         this.mAction = var1;
         this.mExtras = var2;
         this.mCallback = var3;
      }

      protected void onReceiveResult(int var1, Bundle var2) {
         if (this.mCallback != null) {
            MediaSessionCompat.ensureClassLoader(var2);
            if (var1 != -1) {
               if (var1 != 0) {
                  if (var1 != 1) {
                     Log.w("MediaBrowserCompat", "Unknown result code: " + var1 + " (extras=" + this.mExtras + ", resultData=" + var2 + ")");
                  } else {
                     this.mCallback.onProgressUpdate(this.mAction, this.mExtras, var2);
                  }
               } else {
                  this.mCallback.onResult(this.mAction, this.mExtras, var2);
               }
            } else {
               this.mCallback.onError(this.mAction, this.mExtras, var2);
            }

         }
      }
   }

   public abstract static class ItemCallback {
      final Object mItemCallbackObj;

      public ItemCallback() {
         if (VERSION.SDK_INT >= 23) {
            this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23(this));
         } else {
            this.mItemCallbackObj = null;
         }

      }

      public void onError(String var1) {
      }

      public void onItemLoaded(MediaItem var1) {
      }

      private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
         final ItemCallback this$0;

         StubApi23(ItemCallback var1) {
            this.this$0 = var1;
         }

         public void onError(String var1) {
            this.this$0.onError(var1);
         }

         public void onItemLoaded(Parcel var1) {
            if (var1 == null) {
               this.this$0.onItemLoaded((MediaItem)null);
            } else {
               var1.setDataPosition(0);
               MediaItem var2 = (MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(var1);
               var1.recycle();
               this.this$0.onItemLoaded(var2);
            }

         }
      }
   }

   private static class ItemReceiver extends ResultReceiver {
      private final ItemCallback mCallback;
      private final String mMediaId;

      ItemReceiver(String var1, ItemCallback var2, Handler var3) {
         super(var3);
         this.mMediaId = var1;
         this.mCallback = var2;
      }

      protected void onReceiveResult(int var1, Bundle var2) {
         MediaSessionCompat.ensureClassLoader(var2);
         if (var1 == 0 && var2 != null && var2.containsKey("media_item")) {
            Parcelable var3 = var2.getParcelable("media_item");
            if (var3 != null && !(var3 instanceof MediaItem)) {
               this.mCallback.onError(this.mMediaId);
            } else {
               this.mCallback.onItemLoaded((MediaItem)var3);
            }

         } else {
            this.mCallback.onError(this.mMediaId);
         }
      }
   }

   interface MediaBrowserImpl {
      void connect();

      void disconnect();

      Bundle getExtras();

      void getItem(String var1, ItemCallback var2);

      Bundle getNotifyChildrenChangedOptions();

      String getRoot();

      ComponentName getServiceComponent();

      MediaSessionCompat.Token getSessionToken();

      boolean isConnected();

      void search(String var1, Bundle var2, SearchCallback var3);

      void sendCustomAction(String var1, Bundle var2, CustomActionCallback var3);

      void subscribe(String var1, Bundle var2, SubscriptionCallback var3);

      void unsubscribe(String var1, SubscriptionCallback var2);
   }

   static class MediaBrowserImplApi21 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal {
      protected final Object mBrowserObj;
      protected Messenger mCallbacksMessenger;
      final Context mContext;
      protected final CallbackHandler mHandler = new CallbackHandler(this);
      private MediaSessionCompat.Token mMediaSessionToken;
      private Bundle mNotifyChildrenChangedOptions;
      protected final Bundle mRootHints;
      protected ServiceBinderWrapper mServiceBinderWrapper;
      protected int mServiceVersion;
      private final ArrayMap mSubscriptions = new ArrayMap();

      MediaBrowserImplApi21(Context var1, ComponentName var2, ConnectionCallback var3, Bundle var4) {
         this.mContext = var1;
         Bundle var5 = new Bundle;
         if (var4 != null) {
            var5.<init>(var4);
         } else {
            var5.<init>();
         }

         this.mRootHints = var5;
         var5.putInt("extra_client_version", 1);
         var3.setInternalConnectionCallback(this);
         this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(var1, var2, var3.mConnectionCallbackObj, var5);
      }

      public void connect() {
         MediaBrowserCompatApi21.connect(this.mBrowserObj);
      }

      public void disconnect() {
         ServiceBinderWrapper var1 = this.mServiceBinderWrapper;
         if (var1 != null) {
            Messenger var2 = this.mCallbacksMessenger;
            if (var2 != null) {
               try {
                  var1.unregisterCallbackMessenger(var2);
               } catch (RemoteException var3) {
                  Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
               }
            }
         }

         MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
      }

      public Bundle getExtras() {
         return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
      }

      public void getItem(String var1, ItemCallback var2) {
         if (!TextUtils.isEmpty(var1)) {
            if (var2 != null) {
               if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
                  Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                  this.mHandler.post(new Runnable(this, var2, var1) {
                     final MediaBrowserImplApi21 this$0;
                     final ItemCallback val$cb;
                     final String val$mediaId;

                     {
                        this.this$0 = var1;
                        this.val$cb = var2;
                        this.val$mediaId = var3;
                     }

                     public void run() {
                        this.val$cb.onError(this.val$mediaId);
                     }
                  });
               } else if (this.mServiceBinderWrapper == null) {
                  this.mHandler.post(new Runnable(this, var2, var1) {
                     final MediaBrowserImplApi21 this$0;
                     final ItemCallback val$cb;
                     final String val$mediaId;

                     {
                        this.this$0 = var1;
                        this.val$cb = var2;
                        this.val$mediaId = var3;
                     }

                     public void run() {
                        this.val$cb.onError(this.val$mediaId);
                     }
                  });
               } else {
                  ItemReceiver var3 = new ItemReceiver(var1, var2, this.mHandler);

                  try {
                     this.mServiceBinderWrapper.getMediaItem(var1, var3, this.mCallbacksMessenger);
                  } catch (RemoteException var4) {
                     Log.i("MediaBrowserCompat", "Remote error getting media item: " + var1);
                     this.mHandler.post(new Runnable(this, var2, var1) {
                        final MediaBrowserImplApi21 this$0;
                        final ItemCallback val$cb;
                        final String val$mediaId;

                        {
                           this.this$0 = var1;
                           this.val$cb = var2;
                           this.val$mediaId = var3;
                        }

                        public void run() {
                           this.val$cb.onError(this.val$mediaId);
                        }
                     });
                  }

               }
            } else {
               throw new IllegalArgumentException("cb is null");
            }
         } else {
            throw new IllegalArgumentException("mediaId is empty");
         }
      }

      public Bundle getNotifyChildrenChangedOptions() {
         return this.mNotifyChildrenChangedOptions;
      }

      public String getRoot() {
         return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
      }

      public ComponentName getServiceComponent() {
         return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
      }

      public MediaSessionCompat.Token getSessionToken() {
         if (this.mMediaSessionToken == null) {
            this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
         }

         return this.mMediaSessionToken;
      }

      public boolean isConnected() {
         return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
      }

      public void onConnected() {
         Bundle var1 = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
         if (var1 != null) {
            this.mServiceVersion = var1.getInt("extra_service_version", 0);
            IBinder var2 = BundleCompat.getBinder(var1, "extra_messenger");
            if (var2 != null) {
               this.mServiceBinderWrapper = new ServiceBinderWrapper(var2, this.mRootHints);
               Messenger var5 = new Messenger(this.mHandler);
               this.mCallbacksMessenger = var5;
               this.mHandler.setCallbacksMessenger(var5);

               try {
                  this.mServiceBinderWrapper.registerCallbackMessenger(this.mContext, this.mCallbacksMessenger);
               } catch (RemoteException var3) {
                  Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
               }
            }

            IMediaSession var4 = IMediaSession.Stub.asInterface(BundleCompat.getBinder(var1, "extra_session_binder"));
            if (var4 != null) {
               this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj), var4);
            }

         }
      }

      public void onConnectionFailed() {
      }

      public void onConnectionFailed(Messenger var1) {
      }

      public void onConnectionSuspended() {
         this.mServiceBinderWrapper = null;
         this.mCallbacksMessenger = null;
         this.mMediaSessionToken = null;
         this.mHandler.setCallbacksMessenger((Messenger)null);
      }

      public void onLoadChildren(Messenger var1, String var2, List var3, Bundle var4, Bundle var5) {
         if (this.mCallbacksMessenger == var1) {
            Subscription var6 = (Subscription)this.mSubscriptions.get(var2);
            if (var6 == null) {
               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + var2);
               }

            } else {
               SubscriptionCallback var7 = var6.getCallback(var4);
               if (var7 != null) {
                  if (var4 == null) {
                     if (var3 == null) {
                        var7.onError(var2);
                     } else {
                        this.mNotifyChildrenChangedOptions = var5;
                        var7.onChildrenLoaded(var2, var3);
                        this.mNotifyChildrenChangedOptions = null;
                     }
                  } else if (var3 == null) {
                     var7.onError(var2, var4);
                  } else {
                     this.mNotifyChildrenChangedOptions = var5;
                     var7.onChildrenLoaded(var2, var3, var4);
                     this.mNotifyChildrenChangedOptions = null;
                  }
               }

            }
         }
      }

      public void onServiceConnected(Messenger var1, String var2, MediaSessionCompat.Token var3, Bundle var4) {
      }

      public void search(String var1, Bundle var2, SearchCallback var3) {
         if (this.isConnected()) {
            if (this.mServiceBinderWrapper == null) {
               Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
               this.mHandler.post(new Runnable(this, var3, var1, var2) {
                  final MediaBrowserImplApi21 this$0;
                  final SearchCallback val$callback;
                  final Bundle val$extras;
                  final String val$query;

                  {
                     this.this$0 = var1;
                     this.val$callback = var2;
                     this.val$query = var3;
                     this.val$extras = var4;
                  }

                  public void run() {
                     this.val$callback.onError(this.val$query, this.val$extras);
                  }
               });
            } else {
               SearchResultReceiver var4 = new SearchResultReceiver(var1, var2, var3, this.mHandler);

               try {
                  this.mServiceBinderWrapper.search(var1, var2, var4, this.mCallbacksMessenger);
               } catch (RemoteException var5) {
                  Log.i("MediaBrowserCompat", "Remote error searching items with query: " + var1, var5);
                  this.mHandler.post(new Runnable(this, var3, var1, var2) {
                     final MediaBrowserImplApi21 this$0;
                     final SearchCallback val$callback;
                     final Bundle val$extras;
                     final String val$query;

                     {
                        this.this$0 = var1;
                        this.val$callback = var2;
                        this.val$query = var3;
                        this.val$extras = var4;
                     }

                     public void run() {
                        this.val$callback.onError(this.val$query, this.val$extras);
                     }
                  });
               }

            }
         } else {
            throw new IllegalStateException("search() called while not connected");
         }
      }

      public void sendCustomAction(String var1, Bundle var2, CustomActionCallback var3) {
         if (!this.isConnected()) {
            throw new IllegalStateException("Cannot send a custom action (" + var1 + ") with " + "extras " + var2 + " because the browser is not connected to the " + "service.");
         } else {
            if (this.mServiceBinderWrapper == null) {
               Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
               if (var3 != null) {
                  this.mHandler.post(new Runnable(this, var3, var1, var2) {
                     final MediaBrowserImplApi21 this$0;
                     final String val$action;
                     final CustomActionCallback val$callback;
                     final Bundle val$extras;

                     {
                        this.this$0 = var1;
                        this.val$callback = var2;
                        this.val$action = var3;
                        this.val$extras = var4;
                     }

                     public void run() {
                        this.val$callback.onError(this.val$action, this.val$extras, (Bundle)null);
                     }
                  });
               }
            }

            CustomActionResultReceiver var4 = new CustomActionResultReceiver(var1, var2, var3, this.mHandler);

            try {
               this.mServiceBinderWrapper.sendCustomAction(var1, var2, var4, this.mCallbacksMessenger);
            } catch (RemoteException var5) {
               Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + var1 + ", extras=" + var2, var5);
               if (var3 != null) {
                  this.mHandler.post(new Runnable(this, var3, var1, var2) {
                     final MediaBrowserImplApi21 this$0;
                     final String val$action;
                     final CustomActionCallback val$callback;
                     final Bundle val$extras;

                     {
                        this.this$0 = var1;
                        this.val$callback = var2;
                        this.val$action = var3;
                        this.val$extras = var4;
                     }

                     public void run() {
                        this.val$callback.onError(this.val$action, this.val$extras, (Bundle)null);
                     }
                  });
               }
            }

         }
      }

      public void subscribe(String var1, Bundle var2, SubscriptionCallback var3) {
         Subscription var5 = (Subscription)this.mSubscriptions.get(var1);
         Subscription var4 = var5;
         if (var5 == null) {
            var4 = new Subscription();
            this.mSubscriptions.put(var1, var4);
         }

         var3.setSubscription(var4);
         if (var2 == null) {
            var2 = null;
         } else {
            var2 = new Bundle(var2);
         }

         var4.putCallback(var2, var3);
         ServiceBinderWrapper var7 = this.mServiceBinderWrapper;
         if (var7 == null) {
            MediaBrowserCompatApi21.subscribe(this.mBrowserObj, var1, var3.mSubscriptionCallbackObj);
         } else {
            try {
               var7.addSubscription(var1, var3.mToken, var2, this.mCallbacksMessenger);
            } catch (RemoteException var6) {
               Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + var1);
            }
         }

      }

      public void unsubscribe(String var1, SubscriptionCallback var2) {
         Subscription var4 = (Subscription)this.mSubscriptions.get(var1);
         if (var4 != null) {
            ServiceBinderWrapper var5 = this.mServiceBinderWrapper;
            int var3;
            List var6;
            List var10;
            if (var5 == null) {
               if (var2 == null) {
                  MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, var1);
               } else {
                  var6 = var4.getCallbacks();
                  var10 = var4.getOptionsList();

                  for(var3 = var6.size() - 1; var3 >= 0; --var3) {
                     if (var6.get(var3) == var2) {
                        var6.remove(var3);
                        var10.remove(var3);
                     }
                  }

                  if (var6.size() == 0) {
                     MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, var1);
                  }
               }
            } else {
               label68: {
                  boolean var10001;
                  if (var2 == null) {
                     try {
                        var5.removeSubscription(var1, (IBinder)null, this.mCallbacksMessenger);
                        break label68;
                     } catch (RemoteException var7) {
                        var10001 = false;
                     }
                  } else {
                     label66: {
                        try {
                           var6 = var4.getCallbacks();
                           var10 = var4.getOptionsList();
                           var3 = var6.size() - 1;
                        } catch (RemoteException var9) {
                           var10001 = false;
                           break label66;
                        }

                        while(true) {
                           if (var3 < 0) {
                              break label68;
                           }

                           try {
                              if (var6.get(var3) == var2) {
                                 this.mServiceBinderWrapper.removeSubscription(var1, var2.mToken, this.mCallbacksMessenger);
                                 var6.remove(var3);
                                 var10.remove(var3);
                              }
                           } catch (RemoteException var8) {
                              var10001 = false;
                              break;
                           }

                           --var3;
                        }
                     }
                  }

                  Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + var1);
               }
            }

            if (var4.isEmpty() || var2 == null) {
               this.mSubscriptions.remove(var1);
            }

         }
      }
   }

   static class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
      MediaBrowserImplApi23(Context var1, ComponentName var2, ConnectionCallback var3, Bundle var4) {
         super(var1, var2, var3, var4);
      }

      public void getItem(String var1, ItemCallback var2) {
         if (this.mServiceBinderWrapper == null) {
            MediaBrowserCompatApi23.getItem(this.mBrowserObj, var1, var2.mItemCallbackObj);
         } else {
            super.getItem(var1, var2);
         }

      }
   }

   static class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
      MediaBrowserImplApi26(Context var1, ComponentName var2, ConnectionCallback var3, Bundle var4) {
         super(var1, var2, var3, var4);
      }

      public void subscribe(String var1, Bundle var2, SubscriptionCallback var3) {
         if (this.mServiceBinderWrapper != null && this.mServiceVersion >= 2) {
            super.subscribe(var1, var2, var3);
         } else if (var2 == null) {
            MediaBrowserCompatApi21.subscribe(this.mBrowserObj, var1, var3.mSubscriptionCallbackObj);
         } else {
            MediaBrowserCompatApi26.subscribe(this.mBrowserObj, var1, var2, var3.mSubscriptionCallbackObj);
         }

      }

      public void unsubscribe(String var1, SubscriptionCallback var2) {
         if (this.mServiceBinderWrapper != null && this.mServiceVersion >= 2) {
            super.unsubscribe(var1, var2);
         } else if (var2 == null) {
            MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, var1);
         } else {
            MediaBrowserCompatApi26.unsubscribe(this.mBrowserObj, var1, var2.mSubscriptionCallbackObj);
         }

      }
   }

   static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
      static final int CONNECT_STATE_CONNECTED = 3;
      static final int CONNECT_STATE_CONNECTING = 2;
      static final int CONNECT_STATE_DISCONNECTED = 1;
      static final int CONNECT_STATE_DISCONNECTING = 0;
      static final int CONNECT_STATE_SUSPENDED = 4;
      final ConnectionCallback mCallback;
      Messenger mCallbacksMessenger;
      final Context mContext;
      private Bundle mExtras;
      final CallbackHandler mHandler = new CallbackHandler(this);
      private MediaSessionCompat.Token mMediaSessionToken;
      private Bundle mNotifyChildrenChangedOptions;
      final Bundle mRootHints;
      private String mRootId;
      ServiceBinderWrapper mServiceBinderWrapper;
      final ComponentName mServiceComponent;
      MediaServiceConnection mServiceConnection;
      int mState = 1;
      private final ArrayMap mSubscriptions = new ArrayMap();

      public MediaBrowserImplBase(Context var1, ComponentName var2, ConnectionCallback var3, Bundle var4) {
         if (var1 != null) {
            if (var2 != null) {
               if (var3 != null) {
                  this.mContext = var1;
                  this.mServiceComponent = var2;
                  this.mCallback = var3;
                  Bundle var5;
                  if (var4 == null) {
                     var5 = null;
                  } else {
                     var5 = new Bundle(var4);
                  }

                  this.mRootHints = var5;
               } else {
                  throw new IllegalArgumentException("connection callback must not be null");
               }
            } else {
               throw new IllegalArgumentException("service component must not be null");
            }
         } else {
            throw new IllegalArgumentException("context must not be null");
         }
      }

      private static String getStateLabel(int var0) {
         if (var0 != 0) {
            if (var0 != 1) {
               if (var0 != 2) {
                  if (var0 != 3) {
                     return var0 != 4 ? "UNKNOWN/" + var0 : "CONNECT_STATE_SUSPENDED";
                  } else {
                     return "CONNECT_STATE_CONNECTED";
                  }
               } else {
                  return "CONNECT_STATE_CONNECTING";
               }
            } else {
               return "CONNECT_STATE_DISCONNECTED";
            }
         } else {
            return "CONNECT_STATE_DISCONNECTING";
         }
      }

      private boolean isCurrent(Messenger var1, String var2) {
         int var3;
         if (this.mCallbacksMessenger == var1) {
            var3 = this.mState;
            if (var3 != 0 && var3 != 1) {
               return true;
            }
         }

         var3 = this.mState;
         if (var3 != 0 && var3 != 1) {
            Log.i("MediaBrowserCompat", var2 + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
         }

         return false;
      }

      public void connect() {
         int var1 = this.mState;
         if (var1 != 0 && var1 != 1) {
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + getStateLabel(this.mState) + ")");
         } else {
            this.mState = 2;
            this.mHandler.post(new Runnable(this) {
               final MediaBrowserImplBase this$0;

               {
                  this.this$0 = var1;
               }

               public void run() {
                  if (this.this$0.mState != 0) {
                     this.this$0.mState = 2;
                     if (MediaBrowserCompat.DEBUG && this.this$0.mServiceConnection != null) {
                        throw new RuntimeException("mServiceConnection should be null. Instead it is " + this.this$0.mServiceConnection);
                     } else if (this.this$0.mServiceBinderWrapper != null) {
                        throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.this$0.mServiceBinderWrapper);
                     } else if (this.this$0.mCallbacksMessenger == null) {
                        Intent var3 = new Intent("android.media.browse.MediaBrowserService");
                        var3.setComponent(this.this$0.mServiceComponent);
                        this.this$0.mServiceConnection = this.this$0.new MediaServiceConnection(this.this$0);
                        boolean var1 = false;

                        label34: {
                           boolean var2;
                           try {
                              var2 = this.this$0.mContext.bindService(var3, this.this$0.mServiceConnection, 1);
                           } catch (Exception var4) {
                              Log.e("MediaBrowserCompat", "Failed binding to service " + this.this$0.mServiceComponent);
                              break label34;
                           }

                           var1 = var2;
                        }

                        if (!var1) {
                           this.this$0.forceCloseConnection();
                           this.this$0.mCallback.onConnectionFailed();
                        }

                        if (MediaBrowserCompat.DEBUG) {
                           Log.d("MediaBrowserCompat", "connect...");
                           this.this$0.dump();
                        }

                     } else {
                        throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.this$0.mCallbacksMessenger);
                     }
                  }
               }
            });
         }
      }

      public void disconnect() {
         this.mState = 0;
         this.mHandler.post(new Runnable(this) {
            final MediaBrowserImplBase this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               if (this.this$0.mCallbacksMessenger != null) {
                  try {
                     this.this$0.mServiceBinderWrapper.disconnect(this.this$0.mCallbacksMessenger);
                  } catch (RemoteException var3) {
                     Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.this$0.mServiceComponent);
                  }
               }

               int var1 = this.this$0.mState;
               this.this$0.forceCloseConnection();
               if (var1 != 0) {
                  this.this$0.mState = var1;
               }

               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "disconnect...");
                  this.this$0.dump();
               }

            }
         });
      }

      void dump() {
         Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
         Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
         Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
         Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
         Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
         Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
         Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
         Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
         Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
         Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
      }

      void forceCloseConnection() {
         MediaServiceConnection var1 = this.mServiceConnection;
         if (var1 != null) {
            this.mContext.unbindService(var1);
         }

         this.mState = 1;
         this.mServiceConnection = null;
         this.mServiceBinderWrapper = null;
         this.mCallbacksMessenger = null;
         this.mHandler.setCallbacksMessenger((Messenger)null);
         this.mRootId = null;
         this.mMediaSessionToken = null;
      }

      public Bundle getExtras() {
         if (this.isConnected()) {
            return this.mExtras;
         } else {
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
         }
      }

      public void getItem(String var1, ItemCallback var2) {
         if (!TextUtils.isEmpty(var1)) {
            if (var2 != null) {
               if (!this.isConnected()) {
                  Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                  this.mHandler.post(new Runnable(this, var2, var1) {
                     final MediaBrowserImplBase this$0;
                     final ItemCallback val$cb;
                     final String val$mediaId;

                     {
                        this.this$0 = var1;
                        this.val$cb = var2;
                        this.val$mediaId = var3;
                     }

                     public void run() {
                        this.val$cb.onError(this.val$mediaId);
                     }
                  });
               } else {
                  ItemReceiver var3 = new ItemReceiver(var1, var2, this.mHandler);

                  try {
                     this.mServiceBinderWrapper.getMediaItem(var1, var3, this.mCallbacksMessenger);
                  } catch (RemoteException var4) {
                     Log.i("MediaBrowserCompat", "Remote error getting media item: " + var1);
                     this.mHandler.post(new Runnable(this, var2, var1) {
                        final MediaBrowserImplBase this$0;
                        final ItemCallback val$cb;
                        final String val$mediaId;

                        {
                           this.this$0 = var1;
                           this.val$cb = var2;
                           this.val$mediaId = var3;
                        }

                        public void run() {
                           this.val$cb.onError(this.val$mediaId);
                        }
                     });
                  }

               }
            } else {
               throw new IllegalArgumentException("cb is null");
            }
         } else {
            throw new IllegalArgumentException("mediaId is empty");
         }
      }

      public Bundle getNotifyChildrenChangedOptions() {
         return this.mNotifyChildrenChangedOptions;
      }

      public String getRoot() {
         if (this.isConnected()) {
            return this.mRootId;
         } else {
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
         }
      }

      public ComponentName getServiceComponent() {
         if (this.isConnected()) {
            return this.mServiceComponent;
         } else {
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
         }
      }

      public MediaSessionCompat.Token getSessionToken() {
         if (this.isConnected()) {
            return this.mMediaSessionToken;
         } else {
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
         }
      }

      public boolean isConnected() {
         boolean var1;
         if (this.mState == 3) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void onConnectionFailed(Messenger var1) {
         Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
         if (this.isCurrent(var1, "onConnectFailed")) {
            if (this.mState != 2) {
               Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
            } else {
               this.forceCloseConnection();
               this.mCallback.onConnectionFailed();
            }
         }
      }

      public void onLoadChildren(Messenger var1, String var2, List var3, Bundle var4, Bundle var5) {
         if (this.isCurrent(var1, "onLoadChildren")) {
            if (MediaBrowserCompat.DEBUG) {
               Log.d("MediaBrowserCompat", "onLoadChildren for " + this.mServiceComponent + " id=" + var2);
            }

            Subscription var6 = (Subscription)this.mSubscriptions.get(var2);
            if (var6 == null) {
               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + var2);
               }

            } else {
               SubscriptionCallback var7 = var6.getCallback(var4);
               if (var7 != null) {
                  if (var4 == null) {
                     if (var3 == null) {
                        var7.onError(var2);
                     } else {
                        this.mNotifyChildrenChangedOptions = var5;
                        var7.onChildrenLoaded(var2, var3);
                        this.mNotifyChildrenChangedOptions = null;
                     }
                  } else if (var3 == null) {
                     var7.onError(var2, var4);
                  } else {
                     this.mNotifyChildrenChangedOptions = var5;
                     var7.onChildrenLoaded(var2, var3, var4);
                     this.mNotifyChildrenChangedOptions = null;
                  }
               }

            }
         }
      }

      public void onServiceConnected(Messenger var1, String var2, MediaSessionCompat.Token var3, Bundle var4) {
         if (this.isCurrent(var1, "onConnect")) {
            if (this.mState != 2) {
               Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
            } else {
               this.mRootId = var2;
               this.mMediaSessionToken = var3;
               this.mExtras = var4;
               this.mState = 3;
               if (MediaBrowserCompat.DEBUG) {
                  Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                  this.dump();
               }

               this.mCallback.onConnected();

               label43: {
                  Iterator var10;
                  boolean var10001;
                  try {
                     var10 = this.mSubscriptions.entrySet().iterator();
                  } catch (RemoteException var8) {
                     var10001 = false;
                     break label43;
                  }

                  label40:
                  while(true) {
                     String var9;
                     List var12;
                     List var14;
                     try {
                        if (!var10.hasNext()) {
                           return;
                        }

                        Map.Entry var11 = (Map.Entry)var10.next();
                        var9 = (String)var11.getKey();
                        Subscription var13 = (Subscription)var11.getValue();
                        var12 = var13.getCallbacks();
                        var14 = var13.getOptionsList();
                     } catch (RemoteException var7) {
                        var10001 = false;
                        break;
                     }

                     int var5 = 0;

                     while(true) {
                        try {
                           if (var5 >= var12.size()) {
                              break;
                           }

                           this.mServiceBinderWrapper.addSubscription(var9, ((SubscriptionCallback)var12.get(var5)).mToken, (Bundle)var14.get(var5), this.mCallbacksMessenger);
                        } catch (RemoteException var6) {
                           var10001 = false;
                           break label40;
                        }

                        ++var5;
                     }
                  }
               }

               Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
            }
         }
      }

      public void search(String var1, Bundle var2, SearchCallback var3) {
         if (this.isConnected()) {
            SearchResultReceiver var4 = new SearchResultReceiver(var1, var2, var3, this.mHandler);

            try {
               this.mServiceBinderWrapper.search(var1, var2, var4, this.mCallbacksMessenger);
            } catch (RemoteException var5) {
               Log.i("MediaBrowserCompat", "Remote error searching items with query: " + var1, var5);
               this.mHandler.post(new Runnable(this, var3, var1, var2) {
                  final MediaBrowserImplBase this$0;
                  final SearchCallback val$callback;
                  final Bundle val$extras;
                  final String val$query;

                  {
                     this.this$0 = var1;
                     this.val$callback = var2;
                     this.val$query = var3;
                     this.val$extras = var4;
                  }

                  public void run() {
                     this.val$callback.onError(this.val$query, this.val$extras);
                  }
               });
            }

         } else {
            throw new IllegalStateException("search() called while not connected (state=" + getStateLabel(this.mState) + ")");
         }
      }

      public void sendCustomAction(String var1, Bundle var2, CustomActionCallback var3) {
         if (this.isConnected()) {
            CustomActionResultReceiver var4 = new CustomActionResultReceiver(var1, var2, var3, this.mHandler);

            try {
               this.mServiceBinderWrapper.sendCustomAction(var1, var2, var4, this.mCallbacksMessenger);
            } catch (RemoteException var5) {
               Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + var1 + ", extras=" + var2, var5);
               if (var3 != null) {
                  this.mHandler.post(new Runnable(this, var3, var1, var2) {
                     final MediaBrowserImplBase this$0;
                     final String val$action;
                     final CustomActionCallback val$callback;
                     final Bundle val$extras;

                     {
                        this.this$0 = var1;
                        this.val$callback = var2;
                        this.val$action = var3;
                        this.val$extras = var4;
                     }

                     public void run() {
                        this.val$callback.onError(this.val$action, this.val$extras, (Bundle)null);
                     }
                  });
               }
            }

         } else {
            throw new IllegalStateException("Cannot send a custom action (" + var1 + ") with " + "extras " + var2 + " because the browser is not connected to the " + "service.");
         }
      }

      public void subscribe(String var1, Bundle var2, SubscriptionCallback var3) {
         Subscription var5 = (Subscription)this.mSubscriptions.get(var1);
         Subscription var4 = var5;
         if (var5 == null) {
            var4 = new Subscription();
            this.mSubscriptions.put(var1, var4);
         }

         if (var2 == null) {
            var2 = null;
         } else {
            var2 = new Bundle(var2);
         }

         var4.putCallback(var2, var3);
         if (this.isConnected()) {
            try {
               this.mServiceBinderWrapper.addSubscription(var1, var3.mToken, var2, this.mCallbacksMessenger);
            } catch (RemoteException var6) {
               Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + var1);
            }
         }

      }

      public void unsubscribe(String var1, SubscriptionCallback var2) {
         Subscription var4 = (Subscription)this.mSubscriptions.get(var1);
         if (var4 != null) {
            label59: {
               boolean var10001;
               if (var2 == null) {
                  try {
                     if (this.isConnected()) {
                        this.mServiceBinderWrapper.removeSubscription(var1, (IBinder)null, this.mCallbacksMessenger);
                     }
                     break label59;
                  } catch (RemoteException var7) {
                     var10001 = false;
                  }
               } else {
                  label57: {
                     int var3;
                     List var5;
                     List var6;
                     try {
                        var5 = var4.getCallbacks();
                        var6 = var4.getOptionsList();
                        var3 = var5.size() - 1;
                     } catch (RemoteException var10) {
                        var10001 = false;
                        break label57;
                     }

                     while(true) {
                        if (var3 < 0) {
                           break label59;
                        }

                        label51: {
                           try {
                              if (var5.get(var3) != var2) {
                                 break label51;
                              }

                              if (this.isConnected()) {
                                 this.mServiceBinderWrapper.removeSubscription(var1, var2.mToken, this.mCallbacksMessenger);
                              }
                           } catch (RemoteException var9) {
                              var10001 = false;
                              break;
                           }

                           try {
                              var5.remove(var3);
                              var6.remove(var3);
                           } catch (RemoteException var8) {
                              var10001 = false;
                              break;
                           }
                        }

                        --var3;
                     }
                  }
               }

               Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + var1);
            }

            if (var4.isEmpty() || var2 == null) {
               this.mSubscriptions.remove(var1);
            }

         }
      }

      private class MediaServiceConnection implements ServiceConnection {
         final MediaBrowserImplBase this$0;

         MediaServiceConnection(MediaBrowserImplBase var1) {
            this.this$0 = var1;
         }

         private void postOrRun(Runnable var1) {
            if (Thread.currentThread() == this.this$0.mHandler.getLooper().getThread()) {
               var1.run();
            } else {
               this.this$0.mHandler.post(var1);
            }

         }

         boolean isCurrent(String var1) {
            if (this.this$0.mServiceConnection == this && this.this$0.mState != 0 && this.this$0.mState != 1) {
               return true;
            } else {
               if (this.this$0.mState != 0 && this.this$0.mState != 1) {
                  Log.i("MediaBrowserCompat", var1 + " for " + this.this$0.mServiceComponent + " with mServiceConnection=" + this.this$0.mServiceConnection + " this=" + this);
               }

               return false;
            }
         }

         public void onServiceConnected(ComponentName var1, IBinder var2) {
            this.postOrRun(new Runnable(this, var1, var2) {
               final MediaServiceConnection this$1;
               final IBinder val$binder;
               final ComponentName val$name;

               {
                  this.this$1 = var1;
                  this.val$name = var2;
                  this.val$binder = var3;
               }

               public void run() {
                  if (MediaBrowserCompat.DEBUG) {
                     Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.val$name + " binder=" + this.val$binder);
                     this.this$1.this$0.dump();
                  }

                  if (this.this$1.isCurrent("onServiceConnected")) {
                     this.this$1.this$0.mServiceBinderWrapper = new ServiceBinderWrapper(this.val$binder, this.this$1.this$0.mRootHints);
                     this.this$1.this$0.mCallbacksMessenger = new Messenger(this.this$1.this$0.mHandler);
                     this.this$1.this$0.mHandler.setCallbacksMessenger(this.this$1.this$0.mCallbacksMessenger);
                     this.this$1.this$0.mState = 2;

                     try {
                        if (MediaBrowserCompat.DEBUG) {
                           Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                           this.this$1.this$0.dump();
                        }

                        this.this$1.this$0.mServiceBinderWrapper.connect(this.this$1.this$0.mContext, this.this$1.this$0.mCallbacksMessenger);
                     } catch (RemoteException var2) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.this$1.this$0.mServiceComponent);
                        if (MediaBrowserCompat.DEBUG) {
                           Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                           this.this$1.this$0.dump();
                        }
                     }

                  }
               }
            });
         }

         public void onServiceDisconnected(ComponentName var1) {
            this.postOrRun(new Runnable(this, var1) {
               final MediaServiceConnection this$1;
               final ComponentName val$name;

               {
                  this.this$1 = var1;
                  this.val$name = var2;
               }

               public void run() {
                  if (MediaBrowserCompat.DEBUG) {
                     Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.val$name + " this=" + this + " mServiceConnection=" + this.this$1.this$0.mServiceConnection);
                     this.this$1.this$0.dump();
                  }

                  if (this.this$1.isCurrent("onServiceDisconnected")) {
                     this.this$1.this$0.mServiceBinderWrapper = null;
                     this.this$1.this$0.mCallbacksMessenger = null;
                     this.this$1.this$0.mHandler.setCallbacksMessenger((Messenger)null);
                     this.this$1.this$0.mState = 4;
                     this.this$1.this$0.mCallback.onConnectionSuspended();
                  }
               }
            });
         }
      }
   }

   interface MediaBrowserServiceCallbackImpl {
      void onConnectionFailed(Messenger var1);

      void onLoadChildren(Messenger var1, String var2, List var3, Bundle var4, Bundle var5);

      void onServiceConnected(Messenger var1, String var2, MediaSessionCompat.Token var3, Bundle var4);
   }

   public static class MediaItem implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public MediaItem createFromParcel(Parcel var1) {
            return new MediaItem(var1);
         }

         public MediaItem[] newArray(int var1) {
            return new MediaItem[var1];
         }
      };
      public static final int FLAG_BROWSABLE = 1;
      public static final int FLAG_PLAYABLE = 2;
      private final MediaDescriptionCompat mDescription;
      private final int mFlags;

      MediaItem(Parcel var1) {
         this.mFlags = var1.readInt();
         this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(var1);
      }

      public MediaItem(MediaDescriptionCompat var1, int var2) {
         if (var1 != null) {
            if (!TextUtils.isEmpty(var1.getMediaId())) {
               this.mFlags = var2;
               this.mDescription = var1;
            } else {
               throw new IllegalArgumentException("description must have a non-empty media id");
            }
         } else {
            throw new IllegalArgumentException("description cannot be null");
         }
      }

      public static MediaItem fromMediaItem(Object var0) {
         if (var0 != null && VERSION.SDK_INT >= 21) {
            int var1 = MediaBrowserCompatApi21.MediaItem.getFlags(var0);
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(var0)), var1);
         } else {
            return null;
         }
      }

      public static List fromMediaItemList(List var0) {
         if (var0 != null && VERSION.SDK_INT >= 21) {
            ArrayList var1 = new ArrayList(var0.size());
            Iterator var2 = var0.iterator();

            while(var2.hasNext()) {
               var1.add(fromMediaItem(var2.next()));
            }

            return var1;
         } else {
            return null;
         }
      }

      public int describeContents() {
         return 0;
      }

      public MediaDescriptionCompat getDescription() {
         return this.mDescription;
      }

      public int getFlags() {
         return this.mFlags;
      }

      public String getMediaId() {
         return this.mDescription.getMediaId();
      }

      public boolean isBrowsable() {
         int var1 = this.mFlags;
         boolean var2 = true;
         if ((var1 & 1) == 0) {
            var2 = false;
         }

         return var2;
      }

      public boolean isPlayable() {
         boolean var1;
         if ((this.mFlags & 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder("MediaItem{");
         var1.append("mFlags=").append(this.mFlags);
         var1.append(", mDescription=").append(this.mDescription);
         var1.append('}');
         return var1.toString();
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeInt(this.mFlags);
         this.mDescription.writeToParcel(var1, var2);
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface Flags {
      }
   }

   public abstract static class SearchCallback {
      public void onError(String var1, Bundle var2) {
      }

      public void onSearchResult(String var1, Bundle var2, List var3) {
      }
   }

   private static class SearchResultReceiver extends ResultReceiver {
      private final SearchCallback mCallback;
      private final Bundle mExtras;
      private final String mQuery;

      SearchResultReceiver(String var1, Bundle var2, SearchCallback var3, Handler var4) {
         super(var4);
         this.mQuery = var1;
         this.mExtras = var2;
         this.mCallback = var3;
      }

      protected void onReceiveResult(int var1, Bundle var2) {
         MediaSessionCompat.ensureClassLoader(var2);
         if (var1 == 0 && var2 != null && var2.containsKey("search_results")) {
            Parcelable[] var5 = var2.getParcelableArray("search_results");
            ArrayList var6 = null;
            if (var5 != null) {
               ArrayList var4 = new ArrayList();
               int var3 = var5.length;
               var1 = 0;

               while(true) {
                  var6 = var4;
                  if (var1 >= var3) {
                     break;
                  }

                  var4.add((MediaItem)var5[var1]);
                  ++var1;
               }
            }

            this.mCallback.onSearchResult(this.mQuery, this.mExtras, var6);
         } else {
            this.mCallback.onError(this.mQuery, this.mExtras);
         }
      }
   }

   private static class ServiceBinderWrapper {
      private Messenger mMessenger;
      private Bundle mRootHints;

      public ServiceBinderWrapper(IBinder var1, Bundle var2) {
         this.mMessenger = new Messenger(var1);
         this.mRootHints = var2;
      }

      private void sendRequest(int var1, Bundle var2, Messenger var3) throws RemoteException {
         Message var4 = Message.obtain();
         var4.what = var1;
         var4.arg1 = 1;
         var4.setData(var2);
         var4.replyTo = var3;
         this.mMessenger.send(var4);
      }

      void addSubscription(String var1, IBinder var2, Bundle var3, Messenger var4) throws RemoteException {
         Bundle var5 = new Bundle();
         var5.putString("data_media_item_id", var1);
         BundleCompat.putBinder(var5, "data_callback_token", var2);
         var5.putBundle("data_options", var3);
         this.sendRequest(3, var5, var4);
      }

      void connect(Context var1, Messenger var2) throws RemoteException {
         Bundle var3 = new Bundle();
         var3.putString("data_package_name", var1.getPackageName());
         var3.putBundle("data_root_hints", this.mRootHints);
         this.sendRequest(1, var3, var2);
      }

      void disconnect(Messenger var1) throws RemoteException {
         this.sendRequest(2, (Bundle)null, var1);
      }

      void getMediaItem(String var1, ResultReceiver var2, Messenger var3) throws RemoteException {
         Bundle var4 = new Bundle();
         var4.putString("data_media_item_id", var1);
         var4.putParcelable("data_result_receiver", var2);
         this.sendRequest(5, var4, var3);
      }

      void registerCallbackMessenger(Context var1, Messenger var2) throws RemoteException {
         Bundle var3 = new Bundle();
         var3.putString("data_package_name", var1.getPackageName());
         var3.putBundle("data_root_hints", this.mRootHints);
         this.sendRequest(6, var3, var2);
      }

      void removeSubscription(String var1, IBinder var2, Messenger var3) throws RemoteException {
         Bundle var4 = new Bundle();
         var4.putString("data_media_item_id", var1);
         BundleCompat.putBinder(var4, "data_callback_token", var2);
         this.sendRequest(4, var4, var3);
      }

      void search(String var1, Bundle var2, ResultReceiver var3, Messenger var4) throws RemoteException {
         Bundle var5 = new Bundle();
         var5.putString("data_search_query", var1);
         var5.putBundle("data_search_extras", var2);
         var5.putParcelable("data_result_receiver", var3);
         this.sendRequest(8, var5, var4);
      }

      void sendCustomAction(String var1, Bundle var2, ResultReceiver var3, Messenger var4) throws RemoteException {
         Bundle var5 = new Bundle();
         var5.putString("data_custom_action", var1);
         var5.putBundle("data_custom_action_extras", var2);
         var5.putParcelable("data_result_receiver", var3);
         this.sendRequest(9, var5, var4);
      }

      void unregisterCallbackMessenger(Messenger var1) throws RemoteException {
         this.sendRequest(7, (Bundle)null, var1);
      }
   }

   private static class Subscription {
      private final List mCallbacks = new ArrayList();
      private final List mOptionsList = new ArrayList();

      public Subscription() {
      }

      public SubscriptionCallback getCallback(Bundle var1) {
         for(int var2 = 0; var2 < this.mOptionsList.size(); ++var2) {
            if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(var2), var1)) {
               return (SubscriptionCallback)this.mCallbacks.get(var2);
            }
         }

         return null;
      }

      public List getCallbacks() {
         return this.mCallbacks;
      }

      public List getOptionsList() {
         return this.mOptionsList;
      }

      public boolean isEmpty() {
         return this.mCallbacks.isEmpty();
      }

      public void putCallback(Bundle var1, SubscriptionCallback var2) {
         for(int var3 = 0; var3 < this.mOptionsList.size(); ++var3) {
            if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(var3), var1)) {
               this.mCallbacks.set(var3, var2);
               return;
            }
         }

         this.mCallbacks.add(var2);
         this.mOptionsList.add(var1);
      }
   }

   public abstract static class SubscriptionCallback {
      final Object mSubscriptionCallbackObj;
      WeakReference mSubscriptionRef;
      final IBinder mToken = new Binder();

      public SubscriptionCallback() {
         if (VERSION.SDK_INT >= 26) {
            this.mSubscriptionCallbackObj = MediaBrowserCompatApi26.createSubscriptionCallback(new StubApi26(this));
         } else if (VERSION.SDK_INT >= 21) {
            this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21(this));
         } else {
            this.mSubscriptionCallbackObj = null;
         }

      }

      public void onChildrenLoaded(String var1, List var2) {
      }

      public void onChildrenLoaded(String var1, List var2, Bundle var3) {
      }

      public void onError(String var1) {
      }

      public void onError(String var1, Bundle var2) {
      }

      void setSubscription(Subscription var1) {
         this.mSubscriptionRef = new WeakReference(var1);
      }

      private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
         final SubscriptionCallback this$0;

         StubApi21(SubscriptionCallback var1) {
            this.this$0 = var1;
         }

         List applyOptions(List var1, Bundle var2) {
            if (var1 == null) {
               return null;
            } else {
               int var3 = var2.getInt("android.media.browse.extra.PAGE", -1);
               int var6 = var2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
               if (var3 == -1 && var6 == -1) {
                  return var1;
               } else {
                  int var5 = var6 * var3;
                  int var4 = var5 + var6;
                  if (var3 >= 0 && var6 >= 1 && var5 < var1.size()) {
                     var3 = var4;
                     if (var4 > var1.size()) {
                        var3 = var1.size();
                     }

                     return var1.subList(var5, var3);
                  } else {
                     return Collections.emptyList();
                  }
               }
            }
         }

         public void onChildrenLoaded(String var1, List var2) {
            Subscription var4;
            if (this.this$0.mSubscriptionRef == null) {
               var4 = null;
            } else {
               var4 = (Subscription)this.this$0.mSubscriptionRef.get();
            }

            if (var4 == null) {
               this.this$0.onChildrenLoaded(var1, MediaBrowserCompat.MediaItem.fromMediaItemList(var2));
            } else {
               var2 = MediaBrowserCompat.MediaItem.fromMediaItemList(var2);
               List var5 = var4.getCallbacks();
               List var6 = var4.getOptionsList();

               for(int var3 = 0; var3 < var5.size(); ++var3) {
                  Bundle var7 = (Bundle)var6.get(var3);
                  if (var7 == null) {
                     this.this$0.onChildrenLoaded(var1, var2);
                  } else {
                     this.this$0.onChildrenLoaded(var1, this.applyOptions(var2, var7), var7);
                  }
               }
            }

         }

         public void onError(String var1) {
            this.this$0.onError(var1);
         }
      }

      private class StubApi26 extends StubApi21 implements MediaBrowserCompatApi26.SubscriptionCallback {
         final SubscriptionCallback this$0;

         StubApi26(SubscriptionCallback var1) {
            super(var1);
            this.this$0 = var1;
         }

         public void onChildrenLoaded(String var1, List var2, Bundle var3) {
            this.this$0.onChildrenLoaded(var1, MediaBrowserCompat.MediaItem.fromMediaItemList(var2), var3);
         }

         public void onError(String var1, Bundle var2) {
            this.this$0.onError(var1, var2);
         }
      }
   }
}
