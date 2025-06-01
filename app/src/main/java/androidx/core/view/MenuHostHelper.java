package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuHostHelper {
   private final CopyOnWriteArrayList mMenuProviders = new CopyOnWriteArrayList();
   private final Runnable mOnInvalidateMenuCallback;
   private final Map mProviderToLifecycleContainers = new HashMap();

   public MenuHostHelper(Runnable var1) {
      this.mOnInvalidateMenuCallback = var1;
   }

   public void addMenuProvider(MenuProvider var1) {
      this.mMenuProviders.add(var1);
      this.mOnInvalidateMenuCallback.run();
   }

   public void addMenuProvider(MenuProvider var1, LifecycleOwner var2) {
      this.addMenuProvider(var1);
      Lifecycle var4 = var2.getLifecycle();
      LifecycleContainer var3 = (LifecycleContainer)this.mProviderToLifecycleContainers.remove(var1);
      if (var3 != null) {
         var3.clearObservers();
      }

      MenuHostHelper$$ExternalSyntheticLambda0 var5 = new MenuHostHelper$$ExternalSyntheticLambda0(this, var1);
      this.mProviderToLifecycleContainers.put(var1, new LifecycleContainer(var4, var5));
   }

   public void addMenuProvider(MenuProvider var1, LifecycleOwner var2, Lifecycle.State var3) {
      Lifecycle var5 = var2.getLifecycle();
      LifecycleContainer var4 = (LifecycleContainer)this.mProviderToLifecycleContainers.remove(var1);
      if (var4 != null) {
         var4.clearObservers();
      }

      MenuHostHelper$$ExternalSyntheticLambda1 var6 = new MenuHostHelper$$ExternalSyntheticLambda1(this, var3, var1);
      this.mProviderToLifecycleContainers.put(var1, new LifecycleContainer(var5, var6));
   }

   // $FF: synthetic method
   void lambda$addMenuProvider$0$androidx_core_view_MenuHostHelper(MenuProvider var1, LifecycleOwner var2, Lifecycle.Event var3) {
      if (var3 == Lifecycle.Event.ON_DESTROY) {
         this.removeMenuProvider(var1);
      }

   }

   // $FF: synthetic method
   void lambda$addMenuProvider$1$androidx_core_view_MenuHostHelper(Lifecycle.State var1, MenuProvider var2, LifecycleOwner var3, Lifecycle.Event var4) {
      if (var4 == Lifecycle.Event.upTo(var1)) {
         this.addMenuProvider(var2);
      } else if (var4 == Lifecycle.Event.ON_DESTROY) {
         this.removeMenuProvider(var2);
      } else if (var4 == Lifecycle.Event.downFrom(var1)) {
         this.mMenuProviders.remove(var2);
         this.mOnInvalidateMenuCallback.run();
      }

   }

   public void onCreateMenu(Menu var1, MenuInflater var2) {
      Iterator var3 = this.mMenuProviders.iterator();

      while(var3.hasNext()) {
         ((MenuProvider)var3.next()).onCreateMenu(var1, var2);
      }

   }

   public boolean onMenuItemSelected(MenuItem var1) {
      Iterator var2 = this.mMenuProviders.iterator();

      do {
         if (!var2.hasNext()) {
            return false;
         }
      } while(!((MenuProvider)var2.next()).onMenuItemSelected(var1));

      return true;
   }

   public void removeMenuProvider(MenuProvider var1) {
      this.mMenuProviders.remove(var1);
      LifecycleContainer var2 = (LifecycleContainer)this.mProviderToLifecycleContainers.remove(var1);
      if (var2 != null) {
         var2.clearObservers();
      }

      this.mOnInvalidateMenuCallback.run();
   }

   private static class LifecycleContainer {
      final Lifecycle mLifecycle;
      private LifecycleEventObserver mObserver;

      LifecycleContainer(Lifecycle var1, LifecycleEventObserver var2) {
         this.mLifecycle = var1;
         this.mObserver = var2;
         var1.addObserver(var2);
      }

      void clearObservers() {
         this.mLifecycle.removeObserver(this.mObserver);
         this.mObserver = null;
      }
   }
}
