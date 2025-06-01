package androidx.core.os;

import android.os.Process;
import android.os.Build.VERSION;
import java.lang.reflect.Method;

public final class ProcessCompat {
   private ProcessCompat() {
   }

   public static boolean isApplicationUid(int var0) {
      if (VERSION.SDK_INT >= 24) {
         return Process.isApplicationUid(var0);
      } else if (VERSION.SDK_INT >= 17) {
         return ProcessCompat.Api17Impl.isApplicationUid(var0);
      } else {
         return VERSION.SDK_INT == 16 ? ProcessCompat.Api16Impl.isApplicationUid(var0) : true;
      }
   }

   static class Api16Impl {
      private static Method sMethodUserIdIsAppMethod;
      private static boolean sResolved;
      private static final Object sResolvedLock = new Object();

      private Api16Impl() {
      }

      static boolean isApplicationUid(int param0) {
         // $FF: Couldn't be decompiled
      }
   }

   static class Api17Impl {
      private static Method sMethodUserHandleIsAppMethod;
      private static boolean sResolved;
      private static final Object sResolvedLock = new Object();

      private Api17Impl() {
      }

      static boolean isApplicationUid(int param0) {
         // $FF: Couldn't be decompiled
      }
   }

   static class Api24Impl {
      private Api24Impl() {
      }

      static boolean isApplicationUid(int var0) {
         return Process.isApplicationUid(var0);
      }
   }
}
