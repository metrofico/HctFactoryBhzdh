package androidx.core.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.ListPopupWindow;

public final class ListPopupWindowCompat {
   private ListPopupWindowCompat() {
   }

   public static View.OnTouchListener createDragToOpenListener(ListPopupWindow var0, View var1) {
      return VERSION.SDK_INT >= 19 ? var0.createDragToOpenListener(var1) : null;
   }

   @Deprecated
   public static View.OnTouchListener createDragToOpenListener(Object var0, View var1) {
      return createDragToOpenListener((ListPopupWindow)var0, var1);
   }
}
