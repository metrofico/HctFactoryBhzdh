package androidx.transition;

import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;

class GhostViewUtils {
   private GhostViewUtils() {
   }

   static GhostViewImpl addGhost(View var0, ViewGroup var1, Matrix var2) {
      return VERSION.SDK_INT >= 21 ? GhostViewApi21.addGhost(var0, var1, var2) : GhostViewApi14.addGhost(var0, var1);
   }

   static void removeGhost(View var0) {
      if (VERSION.SDK_INT >= 21) {
         GhostViewApi21.removeGhost(var0);
      } else {
         GhostViewApi14.removeGhost(var0);
      }

   }
}
