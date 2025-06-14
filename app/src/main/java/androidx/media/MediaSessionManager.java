package androidx.media;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;

public final class MediaSessionManager {
   static final boolean DEBUG = Log.isLoggable("MediaSessionManager", 3);
   static final String TAG = "MediaSessionManager";
   private static final Object sLock = new Object();
   private static volatile MediaSessionManager sSessionManager;
   MediaSessionManagerImpl mImpl;

   private MediaSessionManager(Context var1) {
      if (VERSION.SDK_INT >= 28) {
         this.mImpl = new MediaSessionManagerImplApi28(var1);
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new MediaSessionManagerImplApi21(var1);
      } else {
         this.mImpl = new MediaSessionManagerImplBase(var1);
      }

   }

   public static MediaSessionManager getSessionManager(Context var0) {
      MediaSessionManager var2 = sSessionManager;
      MediaSessionManager var1 = var2;
      if (var2 == null) {
         Object var3 = sLock;
         synchronized(var3){}

         Throwable var10000;
         boolean var10001;
         label206: {
            try {
               var2 = sSessionManager;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label206;
            }

            var1 = var2;
            if (var2 == null) {
               try {
                  var1 = new MediaSessionManager(var0.getApplicationContext());
                  sSessionManager = var1;
                  var1 = sSessionManager;
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label206;
               }
            }

            label193:
            try {
               return var1;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label193;
            }
         }

         while(true) {
            Throwable var24 = var10000;

            try {
               throw var24;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               continue;
            }
         }
      } else {
         return var1;
      }
   }

   Context getContext() {
      return this.mImpl.getContext();
   }

   public boolean isTrustedForMediaControl(RemoteUserInfo var1) {
      if (var1 != null) {
         return this.mImpl.isTrustedForMediaControl(var1.mImpl);
      } else {
         throw new IllegalArgumentException("userInfo should not be null");
      }
   }

   interface MediaSessionManagerImpl {
      Context getContext();

      boolean isTrustedForMediaControl(RemoteUserInfoImpl var1);
   }

   public static final class RemoteUserInfo {
      public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";
      RemoteUserInfoImpl mImpl;

      public RemoteUserInfo(android.media.session.MediaSessionManager.RemoteUserInfo var1) {
         this.mImpl = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(var1);
      }

      public RemoteUserInfo(String var1, int var2, int var3) {
         if (VERSION.SDK_INT >= 28) {
            this.mImpl = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(var1, var2, var3);
         } else {
            this.mImpl = new MediaSessionManagerImplBase.RemoteUserInfoImplBase(var1, var2, var3);
         }

      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else {
            return !(var1 instanceof RemoteUserInfo) ? false : this.mImpl.equals(((RemoteUserInfo)var1).mImpl);
         }
      }

      public String getPackageName() {
         return this.mImpl.getPackageName();
      }

      public int getPid() {
         return this.mImpl.getPid();
      }

      public int getUid() {
         return this.mImpl.getUid();
      }

      public int hashCode() {
         return this.mImpl.hashCode();
      }
   }

   interface RemoteUserInfoImpl {
      String getPackageName();

      int getPid();

      int getUid();
   }
}
