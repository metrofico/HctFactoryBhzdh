package androidx.lifecycle;

import android.view.View;
import android.view.ViewParent;
import androidx.lifecycle.viewmodel.R;

public class ViewTreeViewModelStoreOwner {
   private ViewTreeViewModelStoreOwner() {
   }

   public static ViewModelStoreOwner get(View var0) {
      ViewModelStoreOwner var2 = (ViewModelStoreOwner)var0.getTag(R.id.view_tree_view_model_store_owner);
      if (var2 != null) {
         return var2;
      } else {
         ViewParent var1 = var0.getParent();

         ViewModelStoreOwner var3;
         View var4;
         for(var3 = var2; var3 == null && var1 instanceof View; var1 = var4.getParent()) {
            var4 = (View)var1;
            var3 = (ViewModelStoreOwner)var4.getTag(R.id.view_tree_view_model_store_owner);
         }

         return var3;
      }
   }

   public static void set(View var0, ViewModelStoreOwner var1) {
      var0.setTag(R.id.view_tree_view_model_store_owner, var1);
   }
}
