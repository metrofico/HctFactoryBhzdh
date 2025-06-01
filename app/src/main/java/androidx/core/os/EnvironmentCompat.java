package androidx.core.os;

import android.os.Environment;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class EnvironmentCompat {
   public static final String MEDIA_UNKNOWN = "unknown";
   private static final String TAG = "EnvironmentCompat";

   private EnvironmentCompat() {
   }

   public static String getStorageState(File var0) {
      if (VERSION.SDK_INT >= 21) {
         return Environment.getExternalStorageState(var0);
      } else if (VERSION.SDK_INT >= 19) {
         return Environment.getStorageState(var0);
      } else {
         try {
            if (var0.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
               String var2 = Environment.getExternalStorageState();
               return var2;
            }
         } catch (IOException var1) {
            Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + var1);
         }

         return "unknown";
      }
   }
}
