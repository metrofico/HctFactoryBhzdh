package androidx.core.provider;

import android.os.Handler;
import android.os.Looper;

class CalleeHandler {
   private CalleeHandler() {
   }

   static Handler create() {
      Handler var0;
      if (Looper.myLooper() == null) {
         var0 = new Handler(Looper.getMainLooper());
      } else {
         var0 = new Handler();
      }

      return var0;
   }
}
