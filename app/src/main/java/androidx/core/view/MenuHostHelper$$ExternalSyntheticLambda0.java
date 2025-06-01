package androidx.core.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class MenuHostHelper$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
   public final MenuHostHelper f$0;
   public final MenuProvider f$1;

   // $FF: synthetic method
   public MenuHostHelper$$ExternalSyntheticLambda0(MenuHostHelper var1, MenuProvider var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
      this.f$0.lambda$addMenuProvider$0$androidx_core_view_MenuHostHelper(this.f$1, var1, var2);
   }
}
