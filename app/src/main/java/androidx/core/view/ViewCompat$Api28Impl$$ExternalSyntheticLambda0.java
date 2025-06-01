package androidx.core.view;

import android.view.KeyEvent;
import android.view.View;

public final class ViewCompat$Api28Impl$$ExternalSyntheticLambda0 implements View.OnUnhandledKeyEventListener {
   public final ViewCompat.OnUnhandledKeyEventListenerCompat f$0;

   // $FF: synthetic method
   public ViewCompat$Api28Impl$$ExternalSyntheticLambda0(ViewCompat.OnUnhandledKeyEventListenerCompat var1) {
      this.f$0 = var1;
   }

   public final boolean onUnhandledKeyEvent(View var1, KeyEvent var2) {
      return this.f$0.onUnhandledKeyEvent(var1, var2);
   }
}
