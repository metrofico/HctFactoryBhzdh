package androidx.startup;

import android.util.Log;

public final class StartupLogger {
   static final boolean DEBUG = false;
   private static final String TAG = "StartupLogger";

   private StartupLogger() {
   }

   public static void e(String var0, Throwable var1) {
      Log.e("StartupLogger", var0, var1);
   }

   public static void i(String var0) {
      Log.i("StartupLogger", var0);
   }
}
