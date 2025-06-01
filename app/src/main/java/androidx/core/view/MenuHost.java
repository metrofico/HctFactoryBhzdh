package androidx.core.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

public interface MenuHost {
   void addMenuProvider(MenuProvider var1);

   void addMenuProvider(MenuProvider var1, LifecycleOwner var2);

   void addMenuProvider(MenuProvider var1, LifecycleOwner var2, Lifecycle.State var3);

   void invalidateMenu();

   void removeMenuProvider(MenuProvider var1);
}
