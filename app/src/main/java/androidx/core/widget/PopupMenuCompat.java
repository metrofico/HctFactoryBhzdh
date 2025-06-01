package androidx.core.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.PopupMenu;

public final class PopupMenuCompat {
   private PopupMenuCompat() {
   }

   public static View.OnTouchListener getDragToOpenListener(Object var0) {
      return VERSION.SDK_INT >= 19 ? ((PopupMenu)var0).getDragToOpenListener() : null;
   }
}
