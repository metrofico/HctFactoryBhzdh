package androidx.lifecycle;

import android.view.View;
import android.view.ViewParent;
import androidx.lifecycle.runtime.R;

public class ViewTreeLifecycleOwner {
   private ViewTreeLifecycleOwner() {
   }

   public static LifecycleOwner get(View var0) {
      LifecycleOwner var2 = (LifecycleOwner)var0.getTag(R.id.view_tree_lifecycle_owner);
      if (var2 != null) {
         return var2;
      } else {
         ViewParent var1 = var0.getParent();

         LifecycleOwner var3;
         View var4;
         for(var3 = var2; var3 == null && var1 instanceof View; var1 = var4.getParent()) {
            var4 = (View)var1;
            var3 = (LifecycleOwner)var4.getTag(R.id.view_tree_lifecycle_owner);
         }

         return var3;
      }
   }

   public static void set(View var0, LifecycleOwner var1) {
      var0.setTag(R.id.view_tree_lifecycle_owner, var1);
   }
}
