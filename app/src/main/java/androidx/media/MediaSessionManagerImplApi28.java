package androidx.media;

import android.content.Context;
import androidx.core.util.ObjectsCompat;

class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
   android.media.session.MediaSessionManager mObject;

   MediaSessionManagerImplApi28(Context var1) {
      super(var1);
      this.mObject = (android.media.session.MediaSessionManager)var1.getSystemService("media_session");
   }

   public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl var1) {
      return var1 instanceof RemoteUserInfoImplApi28 ? this.mObject.isTrustedForMediaControl(((RemoteUserInfoImplApi28)var1).mObject) : false;
   }

   static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {
      final android.media.session.MediaSessionManager.RemoteUserInfo mObject;

      RemoteUserInfoImplApi28(android.media.session.MediaSessionManager.RemoteUserInfo var1) {
         this.mObject = var1;
      }

      RemoteUserInfoImplApi28(String var1, int var2, int var3) {
         this.mObject = new android.media.session.MediaSessionManager.RemoteUserInfo(var1, var2, var3);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof RemoteUserInfoImplApi28)) {
            return false;
         } else {
            RemoteUserInfoImplApi28 var2 = (RemoteUserInfoImplApi28)var1;
            return this.mObject.equals(var2.mObject);
         }
      }

      public String getPackageName() {
         return this.mObject.getPackageName();
      }

      public int getPid() {
         return this.mObject.getPid();
      }

      public int getUid() {
         return this.mObject.getUid();
      }

      public int hashCode() {
         return ObjectsCompat.hash(this.mObject);
      }
   }
}
