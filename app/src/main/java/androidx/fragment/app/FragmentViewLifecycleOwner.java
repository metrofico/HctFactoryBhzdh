package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;

class FragmentViewLifecycleOwner implements HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ViewModelStoreOwner {
   private ViewModelProvider.Factory mDefaultFactory;
   private final Fragment mFragment;
   private LifecycleRegistry mLifecycleRegistry = null;
   private SavedStateRegistryController mSavedStateRegistryController = null;
   private final ViewModelStore mViewModelStore;

   FragmentViewLifecycleOwner(Fragment var1, ViewModelStore var2) {
      this.mFragment = var1;
      this.mViewModelStore = var2;
   }

   public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
      ViewModelProvider.Factory var1 = this.mFragment.getDefaultViewModelProviderFactory();
      if (!var1.equals(this.mFragment.mDefaultFactory)) {
         this.mDefaultFactory = var1;
         return var1;
      } else {
         if (this.mDefaultFactory == null) {
            Object var3 = null;
            Context var2 = this.mFragment.requireContext().getApplicationContext();

            Application var4;
            while(true) {
               var4 = (Application)var3;
               if (!(var2 instanceof ContextWrapper)) {
                  break;
               }

               if (var2 instanceof Application) {
                  var4 = (Application)var2;
                  break;
               }

               var2 = ((ContextWrapper)var2).getBaseContext();
            }

            this.mDefaultFactory = new SavedStateViewModelFactory(var4, this, this.mFragment.getArguments());
         }

         return this.mDefaultFactory;
      }
   }

   public Lifecycle getLifecycle() {
      this.initialize();
      return this.mLifecycleRegistry;
   }

   public SavedStateRegistry getSavedStateRegistry() {
      this.initialize();
      return this.mSavedStateRegistryController.getSavedStateRegistry();
   }

   public ViewModelStore getViewModelStore() {
      this.initialize();
      return this.mViewModelStore;
   }

   void handleLifecycleEvent(Lifecycle.Event var1) {
      this.mLifecycleRegistry.handleLifecycleEvent(var1);
   }

   void initialize() {
      if (this.mLifecycleRegistry == null) {
         this.mLifecycleRegistry = new LifecycleRegistry(this);
         this.mSavedStateRegistryController = SavedStateRegistryController.create(this);
      }

   }

   boolean isInitialized() {
      boolean var1;
      if (this.mLifecycleRegistry != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   void performRestore(Bundle var1) {
      this.mSavedStateRegistryController.performRestore(var1);
   }

   void performSave(Bundle var1) {
      this.mSavedStateRegistryController.performSave(var1);
   }

   void setCurrentState(Lifecycle.State var1) {
      this.mLifecycleRegistry.setCurrentState(var1);
   }
}
