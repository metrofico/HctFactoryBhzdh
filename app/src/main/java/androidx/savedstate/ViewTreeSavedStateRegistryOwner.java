package androidx.savedstate;

import android.view.View;
import android.view.ViewParent;

public final class ViewTreeSavedStateRegistryOwner {
   private ViewTreeSavedStateRegistryOwner() {
   }

   public static SavedStateRegistryOwner get(View var0) {
      SavedStateRegistryOwner var2 = (SavedStateRegistryOwner)var0.getTag(R.id.view_tree_saved_state_registry_owner);
      if (var2 != null) {
         return var2;
      } else {
         ViewParent var1 = var0.getParent();

         SavedStateRegistryOwner var3;
         View var4;
         for(var3 = var2; var3 == null && var1 instanceof View; var1 = var4.getParent()) {
            var4 = (View)var1;
            var3 = (SavedStateRegistryOwner)var4.getTag(R.id.view_tree_saved_state_registry_owner);
         }

         return var3;
      }
   }

   public static void set(View var0, SavedStateRegistryOwner var1) {
      var0.setTag(R.id.view_tree_saved_state_registry_owner, var1);
   }
}
