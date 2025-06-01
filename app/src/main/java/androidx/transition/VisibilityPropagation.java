package androidx.transition;

import android.view.View;

public abstract class VisibilityPropagation extends TransitionPropagation {
   private static final String PROPNAME_VIEW_CENTER = "android:visibilityPropagation:center";
   private static final String PROPNAME_VISIBILITY = "android:visibilityPropagation:visibility";
   private static final String[] VISIBILITY_PROPAGATION_VALUES = new String[]{"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

   private static int getViewCoordinate(TransitionValues var0, int var1) {
      if (var0 == null) {
         return -1;
      } else {
         int[] var2 = (int[])var0.values.get("android:visibilityPropagation:center");
         return var2 == null ? -1 : var2[var1];
      }
   }

   public void captureValues(TransitionValues var1) {
      View var5 = var1.view;
      Integer var4 = (Integer)var1.values.get("android:visibility:visibility");
      Integer var3 = var4;
      if (var4 == null) {
         var3 = var5.getVisibility();
      }

      var1.values.put("android:visibilityPropagation:visibility", var3);
      int[] var6 = new int[2];
      var5.getLocationOnScreen(var6);
      int var2 = var6[0] + Math.round(var5.getTranslationX());
      var6[0] = var2;
      var6[0] = var2 + var5.getWidth() / 2;
      var2 = var6[1] + Math.round(var5.getTranslationY());
      var6[1] = var2;
      var6[1] = var2 + var5.getHeight() / 2;
      var1.values.put("android:visibilityPropagation:center", var6);
   }

   public String[] getPropagationProperties() {
      return VISIBILITY_PROPAGATION_VALUES;
   }

   public int getViewVisibility(TransitionValues var1) {
      if (var1 == null) {
         return 8;
      } else {
         Integer var2 = (Integer)var1.values.get("android:visibilityPropagation:visibility");
         return var2 == null ? 8 : var2;
      }
   }

   public int getViewX(TransitionValues var1) {
      return getViewCoordinate(var1, 0);
   }

   public int getViewY(TransitionValues var1) {
      return getViewCoordinate(var1, 1);
   }
}
