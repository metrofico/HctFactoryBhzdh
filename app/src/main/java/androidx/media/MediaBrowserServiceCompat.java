package androidx.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.core.util.Pair;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MediaBrowserServiceCompat extends Service {
   static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);
   private static final float EPSILON = 1.0E-5F;
   public static final String KEY_MEDIA_ITEM = "media_item";
   public static final String KEY_SEARCH_RESULTS = "search_results";
   public static final int RESULT_ERROR = -1;
   static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
   static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
   static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
   public static final int RESULT_OK = 0;
   public static final int RESULT_PROGRESS_UPDATE = 1;
   public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
   static final String TAG = "MBServiceCompat";
   final ArrayMap mConnections = new ArrayMap();
   ConnectionRecord mCurConnection;
   final ServiceHandler mHandler = new ServiceHandler(this);
   private MediaBrowserServiceImpl mImpl;
   MediaSessionCompat.Token mSession;

   void addSubscription(String var1, ConnectionRecord var2, IBinder var3, Bundle var4) {
      List var6 = (List)var2.subscriptions.get(var1);
      Object var5 = var6;
      if (var6 == null) {
         var5 = new ArrayList();
      }

      Iterator var8 = ((List)var5).iterator();

      Pair var7;
      do {
         if (!var8.hasNext()) {
            ((List)var5).add(new Pair(var3, var4));
            var2.subscriptions.put(var1, var5);
            this.performLoadChildren(var1, var2, var4, (Bundle)null);
            this.mCurConnection = var2;
            this.onSubscribe(var1, var4);
            this.mCurConnection = null;
            return;
         }

         var7 = (Pair)var8.next();
      } while(var3 != var7.first || !MediaBrowserCompatUtils.areSameOptions(var4, (Bundle)var7.second));

   }

   List applyOptions(List var1, Bundle var2) {
      if (var1 == null) {
         return null;
      } else {
         int var6 = var2.getInt("android.media.browse.extra.PAGE", -1);
         int var3 = var2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
         if (var6 == -1 && var3 == -1) {
            return var1;
         } else {
            int var5 = var3 * var6;
            int var4 = var5 + var3;
            if (var6 >= 0 && var3 >= 1 && var5 < var1.size()) {
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

   public void attachToBaseContext(Context var1) {
      this.attachBaseContext(var1);
   }

   public void dump(FileDescriptor var1, PrintWriter var2, String[] var3) {
   }

   public final Bundle getBrowserRootHints() {
      return this.mImpl.getBrowserRootHints();
   }

   public final MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
      return this.mImpl.getCurrentBrowserInfo();
   }

   public MediaSessionCompat.Token getSessionToken() {
      return this.mSession;
   }

   boolean isValidPackage(String var1, int var2) {
      if (var1 == null) {
         return false;
      } else {
         String[] var4 = this.getPackageManager().getPackagesForUid(var2);
         int var3 = var4.length;

         for(var2 = 0; var2 < var3; ++var2) {
            if (var4[var2].equals(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo var1, String var2, Bundle var3) {
      if (var1 != null) {
         if (var2 != null) {
            if (var3 != null) {
               this.mImpl.notifyChildrenChanged(var1, var2, var3);
            } else {
               throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
            }
         } else {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
         }
      } else {
         throw new IllegalArgumentException("remoteUserInfo cannot be null in notifyChildrenChanged");
      }
   }

   public void notifyChildrenChanged(String var1) {
      if (var1 != null) {
         this.mImpl.notifyChildrenChanged(var1, (Bundle)null);
      } else {
         throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
      }
   }

   public void notifyChildrenChanged(String var1, Bundle var2) {
      if (var1 != null) {
         if (var2 != null) {
            this.mImpl.notifyChildrenChanged(var1, var2);
         } else {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
         }
      } else {
         throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
      }
   }

   public IBinder onBind(Intent var1) {
      return this.mImpl.onBind(var1);
   }

   public void onCreate() {
      super.onCreate();
      if (VERSION.SDK_INT >= 28) {
         this.mImpl = new MediaBrowserServiceImplApi28(this);
      } else if (VERSION.SDK_INT >= 26) {
         this.mImpl = new MediaBrowserServiceImplApi26(this);
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new MediaBrowserServiceImplApi23(this);
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new MediaBrowserServiceImplApi21(this);
      } else {
         this.mImpl = new MediaBrowserServiceImplBase(this);
      }

      this.mImpl.onCreate();
   }

   public void onCustomAction(String var1, Bundle var2, Result var3) {
      var3.sendError((Bundle)null);
   }

   public abstract BrowserRoot onGetRoot(String var1, int var2, Bundle var3);

   public abstract void onLoadChildren(String var1, Result var2);

   public void onLoadChildren(String var1, Result var2, Bundle var3) {
      var2.setFlags(1);
      this.onLoadChildren(var1, var2);
   }

   public void onLoadItem(String var1, Result var2) {
      var2.setFlags(2);
      var2.sendResult((Object)null);
   }

   public void onSearch(String var1, Bundle var2, Result var3) {
      var3.setFlags(4);
      var3.sendResult((Object)null);
   }

   public void onSubscribe(String var1, Bundle var2) {
   }

   public void onUnsubscribe(String var1) {
   }

   void performCustomAction(String var1, Bundle var2, ConnectionRecord var3, ResultReceiver var4) {
      Result var5 = new Result(this, var1, var4) {
         final MediaBrowserServiceCompat this$0;
         final ResultReceiver val$receiver;

         {
            this.this$0 = var1;
            this.val$receiver = var3;
         }

         void onErrorSent(Bundle var1) {
            this.val$receiver.send(-1, var1);
         }

         void onProgressUpdateSent(Bundle var1) {
            this.val$receiver.send(1, var1);
         }

         void onResultSent(Bundle var1) {
            this.val$receiver.send(0, var1);
         }
      };
      this.mCurConnection = var3;
      this.onCustomAction(var1, var2, var5);
      this.mCurConnection = null;
      if (!var5.isDone()) {
         throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + var1 + " extras=" + var2);
      }
   }

   void performLoadChildren(String var1, ConnectionRecord var2, Bundle var3, Bundle var4) {
      Result var5 = new Result(this, var1, var2, var1, var3, var4) {
         final MediaBrowserServiceCompat this$0;
         final ConnectionRecord val$connection;
         final Bundle val$notifyChildrenChangedOptions;
         final String val$parentId;
         final Bundle val$subscribeOptions;

         {
            this.this$0 = var1;
            this.val$connection = var3;
            this.val$parentId = var4;
            this.val$subscribeOptions = var5;
            this.val$notifyChildrenChangedOptions = var6;
         }

         void onResultSent(List var1) {
            if (this.this$0.mConnections.get(this.val$connection.callbacks.asBinder()) != this.val$connection) {
               if (MediaBrowserServiceCompat.DEBUG) {
                  Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.val$connection.pkg + " id=" + this.val$parentId);
               }

            } else {
               List var2 = var1;
               if ((this.getFlags() & 1) != 0) {
                  var2 = this.this$0.applyOptions(var1, this.val$subscribeOptions);
               }

               try {
                  this.val$connection.callbacks.onLoadChildren(this.val$parentId, var2, this.val$subscribeOptions, this.val$notifyChildrenChangedOptions);
               } catch (RemoteException var3) {
                  Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.val$parentId + " package=" + this.val$connection.pkg);
               }

            }
         }
      };
      this.mCurConnection = var2;
      if (var3 == null) {
         this.onLoadChildren(var1, var5);
      } else {
         this.onLoadChildren(var1, var5, var3);
      }

      this.mCurConnection = null;
      if (!var5.isDone()) {
         throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + var2.pkg + " id=" + var1);
      }
   }

   void performLoadItem(String var1, ConnectionRecord var2, ResultReceiver var3) {
      Result var4 = new Result(this, var1, var3) {
         final MediaBrowserServiceCompat this$0;
         final ResultReceiver val$receiver;

         {
            this.this$0 = var1;
            this.val$receiver = var3;
         }

         void onResultSent(MediaBrowserCompat.MediaItem var1) {
            if ((this.getFlags() & 2) != 0) {
               this.val$receiver.send(-1, (Bundle)null);
            } else {
               Bundle var2 = new Bundle();
               var2.putParcelable("media_item", var1);
               this.val$receiver.send(0, var2);
            }
         }
      };
      this.mCurConnection = var2;
      this.onLoadItem(var1, var4);
      this.mCurConnection = null;
      if (!var4.isDone()) {
         throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + var1);
      }
   }

   void performSearch(String var1, Bundle var2, ConnectionRecord var3, ResultReceiver var4) {
      Result var5 = new Result(this, var1, var4) {
         final MediaBrowserServiceCompat this$0;
         final ResultReceiver val$receiver;

         {
            this.this$0 = var1;
            this.val$receiver = var3;
         }

         void onResultSent(List var1) {
            if ((this.getFlags() & 4) == 0 && var1 != null) {
               Bundle var2 = new Bundle();
               var2.putParcelableArray("search_results", (Parcelable[])var1.toArray(new MediaBrowserCompat.MediaItem[0]));
               this.val$receiver.send(0, var2);
            } else {
               this.val$receiver.send(-1, (Bundle)null);
            }
         }
      };
      this.mCurConnection = var3;
      this.onSearch(var1, var2, var5);
      this.mCurConnection = null;
      if (!var5.isDone()) {
         throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + var1);
      }
   }

   boolean removeSubscription(String var1, ConnectionRecord var2, IBinder var3) {
      boolean var5 = true;
      boolean var6 = false;
      boolean var4 = false;
      Throwable var10000;
      boolean var10001;
      if (var3 == null) {
         label376: {
            Object var39;
            try {
               var39 = var2.subscriptions.remove(var1);
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               break label376;
            }

            if (var39 != null) {
               var4 = var5;
            } else {
               var4 = false;
            }

            this.mCurConnection = var2;
            this.onUnsubscribe(var1);
            this.mCurConnection = null;
            return var4;
         }
      } else {
         label377: {
            List var8;
            try {
               var8 = (List)var2.subscriptions.get(var1);
            } catch (Throwable var38) {
               var10000 = var38;
               var10001 = false;
               break label377;
            }

            var5 = var6;
            if (var8 != null) {
               label378: {
                  Iterator var7;
                  try {
                     var7 = var8.iterator();
                  } catch (Throwable var35) {
                     var10000 = var35;
                     var10001 = false;
                     break label377;
                  }

                  label366:
                  while(true) {
                     try {
                        do {
                           if (!var7.hasNext()) {
                              break label366;
                           }
                        } while(var3 != ((Pair)var7.next()).first);

                        var7.remove();
                     } catch (Throwable var37) {
                        var10000 = var37;
                        var10001 = false;
                        break label377;
                     }

                     var4 = true;
                  }

                  var5 = var4;

                  try {
                     if (var8.size() != 0) {
                        break label378;
                     }

                     var2.subscriptions.remove(var1);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label377;
                  }

                  var5 = var4;
               }
            }

            this.mCurConnection = var2;
            this.onUnsubscribe(var1);
            this.mCurConnection = null;
            return var5;
         }
      }

      Throwable var40 = var10000;
      this.mCurConnection = var2;
      this.onUnsubscribe(var1);
      this.mCurConnection = null;
      throw var40;
   }

   public void setSessionToken(MediaSessionCompat.Token var1) {
      if (var1 != null) {
         if (this.mSession == null) {
            this.mSession = var1;
            this.mImpl.setSessionToken(var1);
         } else {
            throw new IllegalStateException("The session token has already been set.");
         }
      } else {
         throw new IllegalArgumentException("Session token may not be null.");
      }
   }

   public static final class BrowserRoot {
      public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
      public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
      public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
      @Deprecated
      public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
      private final Bundle mExtras;
      private final String mRootId;

      public BrowserRoot(String var1, Bundle var2) {
         if (var1 != null) {
            this.mRootId = var1;
            this.mExtras = var2;
         } else {
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
         }
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      public String getRootId() {
         return this.mRootId;
      }
   }

   private class ConnectionRecord implements IBinder.DeathRecipient {
      public final MediaSessionManager.RemoteUserInfo browserInfo;
      public final ServiceCallbacks callbacks;
      public final int pid;
      public final String pkg;
      public BrowserRoot root;
      public final Bundle rootHints;
      public final HashMap subscriptions;
      final MediaBrowserServiceCompat this$0;
      public final int uid;

      ConnectionRecord(MediaBrowserServiceCompat var1, String var2, int var3, int var4, Bundle var5, ServiceCallbacks var6) {
         this.this$0 = var1;
         this.subscriptions = new HashMap();
         this.pkg = var2;
         this.pid = var3;
         this.uid = var4;
         this.browserInfo = new MediaSessionManager.RemoteUserInfo(var2, var3, var4);
         this.rootHints = var5;
         this.callbacks = var6;
      }

      public void binderDied() {
         this.this$0.mHandler.post(new Runnable(this) {
            final ConnectionRecord this$1;

            {
               this.this$1 = var1;
            }

            public void run() {
               this.this$1.this$0.mConnections.remove(this.this$1.callbacks.asBinder());
            }
         });
      }
   }

   interface MediaBrowserServiceImpl {
      Bundle getBrowserRootHints();

      MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo();

      void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo var1, String var2, Bundle var3);

      void notifyChildrenChanged(String var1, Bundle var2);

      IBinder onBind(Intent var1);

      void onCreate();

      void setSessionToken(MediaSessionCompat.Token var1);
   }

   class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy {
      Messenger mMessenger;
      final List mRootExtrasList;
      Object mServiceObj;
      final MediaBrowserServiceCompat this$0;

      MediaBrowserServiceImplApi21(MediaBrowserServiceCompat var1) {
         this.this$0 = var1;
         this.mRootExtrasList = new ArrayList();
      }

      public Bundle getBrowserRootHints() {
         Messenger var2 = this.mMessenger;
         Bundle var1 = null;
         if (var2 == null) {
            return null;
         } else if (this.this$0.mCurConnection != null) {
            if (this.this$0.mCurConnection.rootHints != null) {
               var1 = new Bundle(this.this$0.mCurConnection.rootHints);
            }

            return var1;
         } else {
            throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
         }
      }

      public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
         if (this.this$0.mCurConnection != null) {
            return this.this$0.mCurConnection.browserInfo;
         } else {
            throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
         }
      }

      public void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo var1, String var2, Bundle var3) {
         this.notifyChildrenChangedForCompat(var1, var2, var3);
      }

      public void notifyChildrenChanged(String var1, Bundle var2) {
         this.notifyChildrenChangedForFramework(var1, var2);
         this.notifyChildrenChangedForCompat(var1, var2);
      }

      void notifyChildrenChangedForCompat(MediaSessionManager.RemoteUserInfo var1, String var2, Bundle var3) {
         this.this$0.mHandler.post(new Runnable(this, var1, var2, var3) {
            final MediaBrowserServiceImplApi21 this$1;
            final Bundle val$options;
            final String val$parentId;
            final MediaSessionManager.RemoteUserInfo val$remoteUserInfo;

            {
               this.this$1 = var1;
               this.val$remoteUserInfo = var2;
               this.val$parentId = var3;
               this.val$options = var4;
            }

            public void run() {
               for(int var1 = 0; var1 < this.this$1.this$0.mConnections.size(); ++var1) {
                  ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.valueAt(var1);
                  if (var2.browserInfo.equals(this.val$remoteUserInfo)) {
                     this.this$1.notifyChildrenChangedForCompatOnHandler(var2, this.val$parentId, this.val$options);
                  }
               }

            }
         });
      }

      void notifyChildrenChangedForCompat(String var1, Bundle var2) {
         this.this$0.mHandler.post(new Runnable(this, var1, var2) {
            final MediaBrowserServiceImplApi21 this$1;
            final Bundle val$options;
            final String val$parentId;

            {
               this.this$1 = var1;
               this.val$parentId = var2;
               this.val$options = var3;
            }

            public void run() {
               Iterator var1 = this.this$1.this$0.mConnections.keySet().iterator();

               while(var1.hasNext()) {
                  IBinder var2 = (IBinder)var1.next();
                  ConnectionRecord var3 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var2);
                  this.this$1.notifyChildrenChangedForCompatOnHandler(var3, this.val$parentId, this.val$options);
               }

            }
         });
      }

      void notifyChildrenChangedForCompatOnHandler(ConnectionRecord var1, String var2, Bundle var3) {
         List var4 = (List)var1.subscriptions.get(var2);
         if (var4 != null) {
            Iterator var5 = var4.iterator();

            while(var5.hasNext()) {
               Pair var6 = (Pair)var5.next();
               if (MediaBrowserCompatUtils.hasDuplicatedItems(var3, (Bundle)var6.second)) {
                  this.this$0.performLoadChildren(var2, var1, (Bundle)var6.second, var3);
               }
            }
         }

      }

      void notifyChildrenChangedForFramework(String var1, Bundle var2) {
         MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, var1);
      }

      public IBinder onBind(Intent var1) {
         return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, var1);
      }

      public void onCreate() {
         Object var1 = MediaBrowserServiceCompatApi21.createService(this.this$0, this);
         this.mServiceObj = var1;
         MediaBrowserServiceCompatApi21.onCreate(var1);
      }

      public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String var1, int var2, Bundle var3) {
         Bundle var4;
         if (var3 != null && var3.getInt("extra_client_version", 0) != 0) {
            var3.remove("extra_client_version");
            this.mMessenger = new Messenger(this.this$0.mHandler);
            Bundle var5 = new Bundle();
            var5.putInt("extra_service_version", 2);
            BundleCompat.putBinder(var5, "extra_messenger", this.mMessenger.getBinder());
            if (this.this$0.mSession != null) {
               IMediaSession var8 = this.this$0.mSession.getExtraBinder();
               IBinder var9;
               if (var8 == null) {
                  var9 = null;
               } else {
                  var9 = var8.asBinder();
               }

               BundleCompat.putBinder(var5, "extra_session_binder", var9);
               var4 = var5;
            } else {
               this.mRootExtrasList.add(var5);
               var4 = var5;
            }
         } else {
            var4 = null;
         }

         this.this$0.mCurConnection = this.this$0.new ConnectionRecord(this.this$0, var1, -1, var2, var3, (ServiceCallbacks)null);
         BrowserRoot var7 = this.this$0.onGetRoot(var1, var2, var3);
         this.this$0.mCurConnection = null;
         if (var7 == null) {
            return null;
         } else {
            Bundle var6;
            if (var4 == null) {
               var6 = var7.getExtras();
            } else {
               var6 = var4;
               if (var7.getExtras() != null) {
                  var4.putAll(var7.getExtras());
                  var6 = var4;
               }
            }

            return new MediaBrowserServiceCompatApi21.BrowserRoot(var7.getRootId(), var6);
         }
      }

      public void onLoadChildren(String var1, MediaBrowserServiceCompatApi21.ResultWrapper var2) {
         Result var3 = new Result(this, var1, var2) {
            final MediaBrowserServiceImplApi21 this$1;
            final MediaBrowserServiceCompatApi21.ResultWrapper val$resultWrapper;

            {
               this.this$1 = var1;
               this.val$resultWrapper = var3;
            }

            public void detach() {
               this.val$resultWrapper.detach();
            }

            void onResultSent(List var1) {
               ArrayList var5;
               if (var1 != null) {
                  ArrayList var2 = new ArrayList();
                  Iterator var3 = var1.iterator();

                  while(true) {
                     var5 = var2;
                     if (!var3.hasNext()) {
                        break;
                     }

                     MediaBrowserCompat.MediaItem var6 = (MediaBrowserCompat.MediaItem)var3.next();
                     Parcel var4 = Parcel.obtain();
                     var6.writeToParcel(var4, 0);
                     var2.add(var4);
                  }
               } else {
                  var5 = null;
               }

               this.val$resultWrapper.sendResult(var5);
            }
         };
         this.this$0.onLoadChildren(var1, var3);
      }

      public void setSessionToken(MediaSessionCompat.Token var1) {
         this.this$0.mHandler.postOrRun(new Runnable(this, var1) {
            final MediaBrowserServiceImplApi21 this$1;
            final MediaSessionCompat.Token val$token;

            {
               this.this$1 = var1;
               this.val$token = var2;
            }

            public void run() {
               if (!this.this$1.mRootExtrasList.isEmpty()) {
                  IMediaSession var2 = this.val$token.getExtraBinder();
                  if (var2 != null) {
                     Iterator var1 = this.this$1.mRootExtrasList.iterator();

                     while(var1.hasNext()) {
                        BundleCompat.putBinder((Bundle)var1.next(), "extra_session_binder", var2.asBinder());
                     }
                  }

                  this.this$1.mRootExtrasList.clear();
               }

               MediaBrowserServiceCompatApi21.setSessionToken(this.this$1.mServiceObj, this.val$token.getToken());
            }
         });
      }
   }

   class MediaBrowserServiceImplApi23 extends MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {
      final MediaBrowserServiceCompat this$0;

      MediaBrowserServiceImplApi23(MediaBrowserServiceCompat var1) {
         super(var1);
         this.this$0 = var1;
      }

      public void onCreate() {
         this.mServiceObj = MediaBrowserServiceCompatApi23.createService(this.this$0, this);
         MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
      }

      public void onLoadItem(String var1, MediaBrowserServiceCompatApi21.ResultWrapper var2) {
         Result var3 = new Result(this, var1, var2) {
            final MediaBrowserServiceImplApi23 this$1;
            final MediaBrowserServiceCompatApi21.ResultWrapper val$resultWrapper;

            {
               this.this$1 = var1;
               this.val$resultWrapper = var3;
            }

            public void detach() {
               this.val$resultWrapper.detach();
            }

            void onResultSent(MediaBrowserCompat.MediaItem var1) {
               if (var1 == null) {
                  this.val$resultWrapper.sendResult((Object)null);
               } else {
                  Parcel var2 = Parcel.obtain();
                  var1.writeToParcel(var2, 0);
                  this.val$resultWrapper.sendResult(var2);
               }

            }
         };
         this.this$0.onLoadItem(var1, var3);
      }
   }

   class MediaBrowserServiceImplApi26 extends MediaBrowserServiceImplApi23 implements MediaBrowserServiceCompatApi26.ServiceCompatProxy {
      final MediaBrowserServiceCompat this$0;

      MediaBrowserServiceImplApi26(MediaBrowserServiceCompat var1) {
         super(var1);
         this.this$0 = var1;
      }

      public Bundle getBrowserRootHints() {
         if (this.this$0.mCurConnection != null) {
            Bundle var1;
            if (this.this$0.mCurConnection.rootHints == null) {
               var1 = null;
            } else {
               var1 = new Bundle(this.this$0.mCurConnection.rootHints);
            }

            return var1;
         } else {
            return MediaBrowserServiceCompatApi26.getBrowserRootHints(this.mServiceObj);
         }
      }

      void notifyChildrenChangedForFramework(String var1, Bundle var2) {
         if (var2 != null) {
            MediaBrowserServiceCompatApi26.notifyChildrenChanged(this.mServiceObj, var1, var2);
         } else {
            super.notifyChildrenChangedForFramework(var1, var2);
         }

      }

      public void onCreate() {
         this.mServiceObj = MediaBrowserServiceCompatApi26.createService(this.this$0, this);
         MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
      }

      public void onLoadChildren(String var1, MediaBrowserServiceCompatApi26.ResultWrapper var2, Bundle var3) {
         Result var4 = new Result(this, var1, var2) {
            final MediaBrowserServiceImplApi26 this$1;
            final MediaBrowserServiceCompatApi26.ResultWrapper val$resultWrapper;

            {
               this.this$1 = var1;
               this.val$resultWrapper = var3;
            }

            public void detach() {
               this.val$resultWrapper.detach();
            }

            void onResultSent(List var1) {
               ArrayList var5;
               if (var1 != null) {
                  ArrayList var2 = new ArrayList();
                  Iterator var3 = var1.iterator();

                  while(true) {
                     var5 = var2;
                     if (!var3.hasNext()) {
                        break;
                     }

                     MediaBrowserCompat.MediaItem var6 = (MediaBrowserCompat.MediaItem)var3.next();
                     Parcel var4 = Parcel.obtain();
                     var6.writeToParcel(var4, 0);
                     var2.add(var4);
                  }
               } else {
                  var5 = null;
               }

               this.val$resultWrapper.sendResult(var5, this.getFlags());
            }
         };
         this.this$0.onLoadChildren(var1, var4, var3);
      }
   }

   class MediaBrowserServiceImplApi28 extends MediaBrowserServiceImplApi26 {
      final MediaBrowserServiceCompat this$0;

      MediaBrowserServiceImplApi28(MediaBrowserServiceCompat var1) {
         super(var1);
         this.this$0 = var1;
      }

      public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
         return this.this$0.mCurConnection != null ? this.this$0.mCurConnection.browserInfo : new MediaSessionManager.RemoteUserInfo(((MediaBrowserService)this.mServiceObj).getCurrentBrowserInfo());
      }
   }

   class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl {
      private Messenger mMessenger;
      final MediaBrowserServiceCompat this$0;

      MediaBrowserServiceImplBase(MediaBrowserServiceCompat var1) {
         this.this$0 = var1;
      }

      public Bundle getBrowserRootHints() {
         if (this.this$0.mCurConnection != null) {
            Bundle var1;
            if (this.this$0.mCurConnection.rootHints == null) {
               var1 = null;
            } else {
               var1 = new Bundle(this.this$0.mCurConnection.rootHints);
            }

            return var1;
         } else {
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
         }
      }

      public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
         if (this.this$0.mCurConnection != null) {
            return this.this$0.mCurConnection.browserInfo;
         } else {
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
         }
      }

      public void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo var1, String var2, Bundle var3) {
         this.this$0.mHandler.post(new Runnable(this, var1, var2, var3) {
            final MediaBrowserServiceImplBase this$1;
            final Bundle val$options;
            final String val$parentId;
            final MediaSessionManager.RemoteUserInfo val$remoteUserInfo;

            {
               this.this$1 = var1;
               this.val$remoteUserInfo = var2;
               this.val$parentId = var3;
               this.val$options = var4;
            }

            public void run() {
               for(int var1 = 0; var1 < this.this$1.this$0.mConnections.size(); ++var1) {
                  ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.valueAt(var1);
                  if (var2.browserInfo.equals(this.val$remoteUserInfo)) {
                     this.this$1.notifyChildrenChangedOnHandler(var2, this.val$parentId, this.val$options);
                     break;
                  }
               }

            }
         });
      }

      public void notifyChildrenChanged(String var1, Bundle var2) {
         this.this$0.mHandler.post(new Runnable(this, var1, var2) {
            final MediaBrowserServiceImplBase this$1;
            final Bundle val$options;
            final String val$parentId;

            {
               this.this$1 = var1;
               this.val$parentId = var2;
               this.val$options = var3;
            }

            public void run() {
               Iterator var1 = this.this$1.this$0.mConnections.keySet().iterator();

               while(var1.hasNext()) {
                  IBinder var2 = (IBinder)var1.next();
                  ConnectionRecord var3 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var2);
                  this.this$1.notifyChildrenChangedOnHandler(var3, this.val$parentId, this.val$options);
               }

            }
         });
      }

      void notifyChildrenChangedOnHandler(ConnectionRecord var1, String var2, Bundle var3) {
         List var4 = (List)var1.subscriptions.get(var2);
         if (var4 != null) {
            Iterator var5 = var4.iterator();

            while(var5.hasNext()) {
               Pair var6 = (Pair)var5.next();
               if (MediaBrowserCompatUtils.hasDuplicatedItems(var3, (Bundle)var6.second)) {
                  this.this$0.performLoadChildren(var2, var1, (Bundle)var6.second, var3);
               }
            }
         }

      }

      public IBinder onBind(Intent var1) {
         return "android.media.browse.MediaBrowserService".equals(var1.getAction()) ? this.mMessenger.getBinder() : null;
      }

      public void onCreate() {
         this.mMessenger = new Messenger(this.this$0.mHandler);
      }

      public void setSessionToken(MediaSessionCompat.Token var1) {
         this.this$0.mHandler.post(new Runnable(this, var1) {
            final MediaBrowserServiceImplBase this$1;
            final MediaSessionCompat.Token val$token;

            {
               this.this$1 = var1;
               this.val$token = var2;
            }

            public void run() {
               Iterator var3 = this.this$1.this$0.mConnections.values().iterator();

               while(var3.hasNext()) {
                  ConnectionRecord var1 = (ConnectionRecord)var3.next();

                  try {
                     var1.callbacks.onConnect(var1.root.getRootId(), this.val$token, var1.root.getExtras());
                  } catch (RemoteException var4) {
                     Log.w("MBServiceCompat", "Connection for " + var1.pkg + " is no longer valid.");
                     var3.remove();
                  }
               }

            }
         });
      }
   }

   public static class Result {
      private final Object mDebug;
      private boolean mDetachCalled;
      private int mFlags;
      private boolean mSendErrorCalled;
      private boolean mSendProgressUpdateCalled;
      private boolean mSendResultCalled;

      Result(Object var1) {
         this.mDebug = var1;
      }

      private void checkExtraFields(Bundle var1) {
         if (var1 != null) {
            if (var1.containsKey("android.media.browse.extra.DOWNLOAD_PROGRESS")) {
               float var2 = var1.getFloat("android.media.browse.extra.DOWNLOAD_PROGRESS");
               if (var2 < -1.0E-5F || var2 > 1.00001F) {
                  throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
               }
            }

         }
      }

      public void detach() {
         if (!this.mDetachCalled) {
            if (!this.mSendResultCalled) {
               if (!this.mSendErrorCalled) {
                  this.mDetachCalled = true;
               } else {
                  throw new IllegalStateException("detach() called when sendError() had already been called for: " + this.mDebug);
               }
            } else {
               throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
            }
         } else {
            throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
         }
      }

      int getFlags() {
         return this.mFlags;
      }

      boolean isDone() {
         boolean var1;
         if (!this.mDetachCalled && !this.mSendResultCalled && !this.mSendErrorCalled) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      void onErrorSent(Bundle var1) {
         throw new UnsupportedOperationException("It is not supported to send an error for " + this.mDebug);
      }

      void onProgressUpdateSent(Bundle var1) {
         throw new UnsupportedOperationException("It is not supported to send an interim update for " + this.mDebug);
      }

      void onResultSent(Object var1) {
      }

      public void sendError(Bundle var1) {
         if (!this.mSendResultCalled && !this.mSendErrorCalled) {
            this.mSendErrorCalled = true;
            this.onErrorSent(var1);
         } else {
            throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
         }
      }

      public void sendProgressUpdate(Bundle var1) {
         if (!this.mSendResultCalled && !this.mSendErrorCalled) {
            this.checkExtraFields(var1);
            this.mSendProgressUpdateCalled = true;
            this.onProgressUpdateSent(var1);
         } else {
            throw new IllegalStateException("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
         }
      }

      public void sendResult(Object var1) {
         if (!this.mSendResultCalled && !this.mSendErrorCalled) {
            this.mSendResultCalled = true;
            this.onResultSent(var1);
         } else {
            throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
         }
      }

      void setFlags(int var1) {
         this.mFlags = var1;
      }
   }

   private class ServiceBinderImpl {
      final MediaBrowserServiceCompat this$0;

      ServiceBinderImpl(MediaBrowserServiceCompat var1) {
         this.this$0 = var1;
      }

      public void addSubscription(String var1, IBinder var2, Bundle var3, ServiceCallbacks var4) {
         this.this$0.mHandler.postOrRun(new Runnable(this, var4, var1, var2, var3) {
            final ServiceBinderImpl this$1;
            final ServiceCallbacks val$callbacks;
            final String val$id;
            final Bundle val$options;
            final IBinder val$token;

            {
               this.this$1 = var1;
               this.val$callbacks = var2;
               this.val$id = var3;
               this.val$token = var4;
               this.val$options = var5;
            }

            public void run() {
               IBinder var1 = this.val$callbacks.asBinder();
               ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var1);
               if (var2 == null) {
                  Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.val$id);
               } else {
                  this.this$1.this$0.addSubscription(this.val$id, var2, this.val$token, this.val$options);
               }
            }
         });
      }

      public void connect(String var1, int var2, int var3, Bundle var4, ServiceCallbacks var5) {
         if (this.this$0.isValidPackage(var1, var3)) {
            this.this$0.mHandler.postOrRun(new Runnable(this, var5, var1, var2, var3, var4) {
               final ServiceBinderImpl this$1;
               final ServiceCallbacks val$callbacks;
               final int val$pid;
               final String val$pkg;
               final Bundle val$rootHints;
               final int val$uid;

               {
                  this.this$1 = var1;
                  this.val$callbacks = var2;
                  this.val$pkg = var3;
                  this.val$pid = var4;
                  this.val$uid = var5;
                  this.val$rootHints = var6;
               }

               public void run() {
                  IBinder var1 = this.val$callbacks.asBinder();
                  this.this$1.this$0.mConnections.remove(var1);
                  ConnectionRecord var2 = this.this$1.this$0.new ConnectionRecord(this.this$1.this$0, this.val$pkg, this.val$pid, this.val$uid, this.val$rootHints, this.val$callbacks);
                  this.this$1.this$0.mCurConnection = var2;
                  var2.root = this.this$1.this$0.onGetRoot(this.val$pkg, this.val$uid, this.val$rootHints);
                  this.this$1.this$0.mCurConnection = null;
                  if (var2.root == null) {
                     Log.i("MBServiceCompat", "No root for client " + this.val$pkg + " from service " + this.getClass().getName());

                     try {
                        this.val$callbacks.onConnectFailed();
                     } catch (RemoteException var4) {
                        Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.val$pkg);
                     }
                  } else {
                     try {
                        this.this$1.this$0.mConnections.put(var1, var2);
                        var1.linkToDeath(var2, 0);
                        if (this.this$1.this$0.mSession != null) {
                           this.val$callbacks.onConnect(var2.root.getRootId(), this.this$1.this$0.mSession, var2.root.getExtras());
                        }
                     } catch (RemoteException var3) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.val$pkg);
                        this.this$1.this$0.mConnections.remove(var1);
                     }
                  }

               }
            });
         } else {
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + var3 + " package=" + var1);
         }
      }

      public void disconnect(ServiceCallbacks var1) {
         this.this$0.mHandler.postOrRun(new Runnable(this, var1) {
            final ServiceBinderImpl this$1;
            final ServiceCallbacks val$callbacks;

            {
               this.this$1 = var1;
               this.val$callbacks = var2;
            }

            public void run() {
               IBinder var1 = this.val$callbacks.asBinder();
               ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.remove(var1);
               if (var2 != null) {
                  var2.callbacks.asBinder().unlinkToDeath(var2, 0);
               }

            }
         });
      }

      public void getMediaItem(String var1, ResultReceiver var2, ServiceCallbacks var3) {
         if (!TextUtils.isEmpty(var1) && var2 != null) {
            this.this$0.mHandler.postOrRun(new Runnable(this, var3, var1, var2) {
               final ServiceBinderImpl this$1;
               final ServiceCallbacks val$callbacks;
               final String val$mediaId;
               final ResultReceiver val$receiver;

               {
                  this.this$1 = var1;
                  this.val$callbacks = var2;
                  this.val$mediaId = var3;
                  this.val$receiver = var4;
               }

               public void run() {
                  IBinder var1 = this.val$callbacks.asBinder();
                  ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var1);
                  if (var2 == null) {
                     Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.val$mediaId);
                  } else {
                     this.this$1.this$0.performLoadItem(this.val$mediaId, var2, this.val$receiver);
                  }
               }
            });
         }

      }

      public void registerCallbacks(ServiceCallbacks var1, String var2, int var3, int var4, Bundle var5) {
         this.this$0.mHandler.postOrRun(new Runnable(this, var1, var2, var3, var4, var5) {
            final ServiceBinderImpl this$1;
            final ServiceCallbacks val$callbacks;
            final int val$pid;
            final String val$pkg;
            final Bundle val$rootHints;
            final int val$uid;

            {
               this.this$1 = var1;
               this.val$callbacks = var2;
               this.val$pkg = var3;
               this.val$pid = var4;
               this.val$uid = var5;
               this.val$rootHints = var6;
            }

            public void run() {
               IBinder var1 = this.val$callbacks.asBinder();
               this.this$1.this$0.mConnections.remove(var1);
               ConnectionRecord var2 = this.this$1.this$0.new ConnectionRecord(this.this$1.this$0, this.val$pkg, this.val$pid, this.val$uid, this.val$rootHints, this.val$callbacks);
               this.this$1.this$0.mConnections.put(var1, var2);

               try {
                  var1.linkToDeath(var2, 0);
               } catch (RemoteException var3) {
                  Log.w("MBServiceCompat", "IBinder is already dead.");
               }

            }
         });
      }

      public void removeSubscription(String var1, IBinder var2, ServiceCallbacks var3) {
         this.this$0.mHandler.postOrRun(new Runnable(this, var3, var1, var2) {
            final ServiceBinderImpl this$1;
            final ServiceCallbacks val$callbacks;
            final String val$id;
            final IBinder val$token;

            {
               this.this$1 = var1;
               this.val$callbacks = var2;
               this.val$id = var3;
               this.val$token = var4;
            }

            public void run() {
               IBinder var1 = this.val$callbacks.asBinder();
               ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var1);
               if (var2 == null) {
                  Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.val$id);
               } else {
                  if (!this.this$1.this$0.removeSubscription(this.val$id, var2, this.val$token)) {
                     Log.w("MBServiceCompat", "removeSubscription called for " + this.val$id + " which is not subscribed");
                  }

               }
            }
         });
      }

      public void search(String var1, Bundle var2, ResultReceiver var3, ServiceCallbacks var4) {
         if (!TextUtils.isEmpty(var1) && var3 != null) {
            this.this$0.mHandler.postOrRun(new Runnable(this, var4, var1, var2, var3) {
               final ServiceBinderImpl this$1;
               final ServiceCallbacks val$callbacks;
               final Bundle val$extras;
               final String val$query;
               final ResultReceiver val$receiver;

               {
                  this.this$1 = var1;
                  this.val$callbacks = var2;
                  this.val$query = var3;
                  this.val$extras = var4;
                  this.val$receiver = var5;
               }

               public void run() {
                  IBinder var1 = this.val$callbacks.asBinder();
                  ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var1);
                  if (var2 == null) {
                     Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.val$query);
                  } else {
                     this.this$1.this$0.performSearch(this.val$query, this.val$extras, var2, this.val$receiver);
                  }
               }
            });
         }

      }

      public void sendCustomAction(String var1, Bundle var2, ResultReceiver var3, ServiceCallbacks var4) {
         if (!TextUtils.isEmpty(var1) && var3 != null) {
            this.this$0.mHandler.postOrRun(new Runnable(this, var4, var1, var2, var3) {
               final ServiceBinderImpl this$1;
               final String val$action;
               final ServiceCallbacks val$callbacks;
               final Bundle val$extras;
               final ResultReceiver val$receiver;

               {
                  this.this$1 = var1;
                  this.val$callbacks = var2;
                  this.val$action = var3;
                  this.val$extras = var4;
                  this.val$receiver = var5;
               }

               public void run() {
                  IBinder var1 = this.val$callbacks.asBinder();
                  ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.get(var1);
                  if (var2 == null) {
                     Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.val$action + ", extras=" + this.val$extras);
                  } else {
                     this.this$1.this$0.performCustomAction(this.val$action, this.val$extras, var2, this.val$receiver);
                  }
               }
            });
         }

      }

      public void unregisterCallbacks(ServiceCallbacks var1) {
         this.this$0.mHandler.postOrRun(new Runnable(this, var1) {
            final ServiceBinderImpl this$1;
            final ServiceCallbacks val$callbacks;

            {
               this.this$1 = var1;
               this.val$callbacks = var2;
            }

            public void run() {
               IBinder var1 = this.val$callbacks.asBinder();
               ConnectionRecord var2 = (ConnectionRecord)this.this$1.this$0.mConnections.remove(var1);
               if (var2 != null) {
                  var1.unlinkToDeath(var2, 0);
               }

            }
         });
      }
   }

   private interface ServiceCallbacks {
      IBinder asBinder();

      void onConnect(String var1, MediaSessionCompat.Token var2, Bundle var3) throws RemoteException;

      void onConnectFailed() throws RemoteException;

      void onLoadChildren(String var1, List var2, Bundle var3, Bundle var4) throws RemoteException;
   }

   private static class ServiceCallbacksCompat implements ServiceCallbacks {
      final Messenger mCallbacks;

      ServiceCallbacksCompat(Messenger var1) {
         this.mCallbacks = var1;
      }

      private void sendRequest(int var1, Bundle var2) throws RemoteException {
         Message var3 = Message.obtain();
         var3.what = var1;
         var3.arg1 = 2;
         var3.setData(var2);
         this.mCallbacks.send(var3);
      }

      public IBinder asBinder() {
         return this.mCallbacks.getBinder();
      }

      public void onConnect(String var1, MediaSessionCompat.Token var2, Bundle var3) throws RemoteException {
         Bundle var4 = var3;
         if (var3 == null) {
            var4 = new Bundle();
         }

         var4.putInt("extra_service_version", 2);
         var3 = new Bundle();
         var3.putString("data_media_item_id", var1);
         var3.putParcelable("data_media_session_token", var2);
         var3.putBundle("data_root_hints", var4);
         this.sendRequest(1, var3);
      }

      public void onConnectFailed() throws RemoteException {
         this.sendRequest(2, (Bundle)null);
      }

      public void onLoadChildren(String var1, List var2, Bundle var3, Bundle var4) throws RemoteException {
         Bundle var5 = new Bundle();
         var5.putString("data_media_item_id", var1);
         var5.putBundle("data_options", var3);
         var5.putBundle("data_notify_children_changed_options", var4);
         if (var2 != null) {
            ArrayList var6;
            if (var2 instanceof ArrayList) {
               var6 = (ArrayList)var2;
            } else {
               var6 = new ArrayList(var2);
            }

            var5.putParcelableArrayList("data_media_item_list", var6);
         }

         this.sendRequest(3, var5);
      }
   }

   private final class ServiceHandler extends Handler {
      private final ServiceBinderImpl mServiceBinderImpl;
      final MediaBrowserServiceCompat this$0;

      ServiceHandler(MediaBrowserServiceCompat var1) {
         this.this$0 = var1;
         this.mServiceBinderImpl = var1.new ServiceBinderImpl(var1);
      }

      public void handleMessage(Message var1) {
         Bundle var2 = var1.getData();
         Bundle var3;
         switch (var1.what) {
            case 1:
               var3 = var2.getBundle("data_root_hints");
               MediaSessionCompat.ensureClassLoader(var3);
               this.mServiceBinderImpl.connect(var2.getString("data_package_name"), var2.getInt("data_calling_pid"), var2.getInt("data_calling_uid"), var3, new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 2:
               this.mServiceBinderImpl.disconnect(new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 3:
               var3 = var2.getBundle("data_options");
               MediaSessionCompat.ensureClassLoader(var3);
               this.mServiceBinderImpl.addSubscription(var2.getString("data_media_item_id"), BundleCompat.getBinder(var2, "data_callback_token"), var3, new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 4:
               this.mServiceBinderImpl.removeSubscription(var2.getString("data_media_item_id"), BundleCompat.getBinder(var2, "data_callback_token"), new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 5:
               this.mServiceBinderImpl.getMediaItem(var2.getString("data_media_item_id"), (ResultReceiver)var2.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 6:
               var3 = var2.getBundle("data_root_hints");
               MediaSessionCompat.ensureClassLoader(var3);
               this.mServiceBinderImpl.registerCallbacks(new ServiceCallbacksCompat(var1.replyTo), var2.getString("data_package_name"), var2.getInt("data_calling_pid"), var2.getInt("data_calling_uid"), var3);
               break;
            case 7:
               this.mServiceBinderImpl.unregisterCallbacks(new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 8:
               var3 = var2.getBundle("data_search_extras");
               MediaSessionCompat.ensureClassLoader(var3);
               this.mServiceBinderImpl.search(var2.getString("data_search_query"), var3, (ResultReceiver)var2.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(var1.replyTo));
               break;
            case 9:
               var3 = var2.getBundle("data_custom_action_extras");
               MediaSessionCompat.ensureClassLoader(var3);
               this.mServiceBinderImpl.sendCustomAction(var2.getString("data_custom_action"), var3, (ResultReceiver)var2.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(var1.replyTo));
               break;
            default:
               Log.w("MBServiceCompat", "Unhandled message: " + var1 + "\n  Service version: " + 2 + "\n  Client version: " + var1.arg1);
         }

      }

      public void postOrRun(Runnable var1) {
         if (Thread.currentThread() == this.getLooper().getThread()) {
            var1.run();
         } else {
            this.post(var1);
         }

      }

      public boolean sendMessageAtTime(Message var1, long var2) {
         Bundle var4 = var1.getData();
         var4.setClassLoader(MediaBrowserCompat.class.getClassLoader());
         var4.putInt("data_calling_uid", Binder.getCallingUid());
         var4.putInt("data_calling_pid", Binder.getCallingPid());
         return super.sendMessageAtTime(var1, var2);
      }
   }
}
