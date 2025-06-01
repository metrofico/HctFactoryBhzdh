package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.Map;

public final class SavedStateRegistry {
   private static final String SAVED_COMPONENTS_KEY = "androidx.lifecycle.BundlableSavedStateRegistry.key";
   boolean mAllowingSavingState = true;
   private SafeIterableMap mComponents = new SafeIterableMap();
   private Recreator.SavedStateProvider mRecreatorProvider;
   private boolean mRestored;
   private Bundle mRestoredState;

   SavedStateRegistry() {
   }

   public Bundle consumeRestoredStateForKey(String var1) {
      if (this.mRestored) {
         Bundle var2 = this.mRestoredState;
         if (var2 != null) {
            var2 = var2.getBundle(var1);
            this.mRestoredState.remove(var1);
            if (this.mRestoredState.isEmpty()) {
               this.mRestoredState = null;
            }

            return var2;
         } else {
            return null;
         }
      } else {
         throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
      }
   }

   public boolean isRestored() {
      return this.mRestored;
   }

   void performRestore(Lifecycle var1, Bundle var2) {
      if (!this.mRestored) {
         if (var2 != null) {
            this.mRestoredState = var2.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
         }

         var1.addObserver(new GenericLifecycleObserver(this) {
            final SavedStateRegistry this$0;

            {
               this.this$0 = var1;
            }

            public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
               if (var2 == Lifecycle.Event.ON_START) {
                  this.this$0.mAllowingSavingState = true;
               } else if (var2 == Lifecycle.Event.ON_STOP) {
                  this.this$0.mAllowingSavingState = false;
               }

            }
         });
         this.mRestored = true;
      } else {
         throw new IllegalStateException("SavedStateRegistry was already restored.");
      }
   }

   void performSave(Bundle var1) {
      Bundle var2 = new Bundle();
      Bundle var3 = this.mRestoredState;
      if (var3 != null) {
         var2.putAll(var3);
      }

      SafeIterableMap.IteratorWithAdditions var5 = this.mComponents.iteratorWithAdditions();

      while(var5.hasNext()) {
         Map.Entry var4 = (Map.Entry)var5.next();
         var2.putBundle((String)var4.getKey(), ((SavedStateProvider)var4.getValue()).saveState());
      }

      var1.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", var2);
   }

   public void registerSavedStateProvider(String var1, SavedStateProvider var2) {
      if ((SavedStateProvider)this.mComponents.putIfAbsent(var1, var2) != null) {
         throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
      }
   }

   public void runOnNextRecreation(Class var1) {
      if (this.mAllowingSavingState) {
         if (this.mRecreatorProvider == null) {
            this.mRecreatorProvider = new Recreator.SavedStateProvider(this);
         }

         try {
            var1.getDeclaredConstructor();
         } catch (NoSuchMethodException var3) {
            throw new IllegalArgumentException("Class" + var1.getSimpleName() + " must have default constructor in order to be automatically recreated", var3);
         }

         this.mRecreatorProvider.add(var1.getName());
      } else {
         throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
      }
   }

   public void unregisterSavedStateProvider(String var1) {
      this.mComponents.remove(var1);
   }

   public interface AutoRecreated {
      void onRecreated(SavedStateRegistryOwner var1);
   }

   public interface SavedStateProvider {
      Bundle saveState();
   }
}
