package com.google.android.material.transformation;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.Positioning;
import java.util.HashMap;
import java.util.Map;

public class FabTransformationSheetBehavior extends FabTransformationBehavior {
   private Map importantForAccessibilityMap;

   public FabTransformationSheetBehavior() {
   }

   public FabTransformationSheetBehavior(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void updateImportantForAccessibility(View var1, boolean var2) {
      ViewParent var6 = var1.getParent();
      if (var6 instanceof CoordinatorLayout) {
         CoordinatorLayout var8 = (CoordinatorLayout)var6;
         int var5 = var8.getChildCount();
         if (VERSION.SDK_INT >= 16 && var2) {
            this.importantForAccessibilityMap = new HashMap(var5);
         }

         for(int var3 = 0; var3 < var5; ++var3) {
            View var9 = var8.getChildAt(var3);
            boolean var4;
            if (var9.getLayoutParams() instanceof CoordinatorLayout.LayoutParams && ((CoordinatorLayout.LayoutParams)var9.getLayoutParams()).getBehavior() instanceof FabTransformationScrimBehavior) {
               var4 = true;
            } else {
               var4 = false;
            }

            if (var9 != var1 && !var4) {
               if (!var2) {
                  Map var7 = this.importantForAccessibilityMap;
                  if (var7 != null && var7.containsKey(var9)) {
                     ViewCompat.setImportantForAccessibility(var9, (Integer)this.importantForAccessibilityMap.get(var9));
                  }
               } else {
                  if (VERSION.SDK_INT >= 16) {
                     this.importantForAccessibilityMap.put(var9, var9.getImportantForAccessibility());
                  }

                  ViewCompat.setImportantForAccessibility(var9, 4);
               }
            }
         }

         if (!var2) {
            this.importantForAccessibilityMap = null;
         }

      }
   }

   protected FabTransformationBehavior.FabTransformationSpec onCreateMotionSpec(Context var1, boolean var2) {
      int var3;
      if (var2) {
         var3 = R.animator.mtrl_fab_transformation_sheet_expand_spec;
      } else {
         var3 = R.animator.mtrl_fab_transformation_sheet_collapse_spec;
      }

      FabTransformationBehavior.FabTransformationSpec var4 = new FabTransformationBehavior.FabTransformationSpec();
      var4.timings = MotionSpec.createFromResource(var1, var3);
      var4.positioning = new Positioning(17, 0.0F, 0.0F);
      return var4;
   }

   protected boolean onExpandedStateChange(View var1, View var2, boolean var3, boolean var4) {
      this.updateImportantForAccessibility(var2, var3);
      return super.onExpandedStateChange(var1, var2, var3, var4);
   }
}
