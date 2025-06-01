package androidx.core.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class MenuHostHelper$$ExternalSyntheticLambda1 implements LifecycleEventObserver {
   public final MenuHostHelper f$0;
   public final Lifecycle.State f$1;
   public final MenuProvider f$2;

   // $FF: synthetic method
   public MenuHostHelper$$ExternalSyntheticLambda1(MenuHostHelper var1, Lifecycle.State var2, MenuProvider var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
      this.f$0.lambda$addMenuProvider$1$androidx_core_view_MenuHostHelper(this.f$1, this.f$2, var1, var2);
   }
}
