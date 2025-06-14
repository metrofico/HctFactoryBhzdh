package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.Property;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;

class ViewUtils {
   static final Property CLIP_BOUNDS;
   private static final ViewUtilsBase IMPL;
   private static final String TAG = "ViewUtils";
   static final Property TRANSITION_ALPHA;
   private static final int VISIBILITY_MASK = 12;
   private static Field sViewFlagsField;
   private static boolean sViewFlagsFieldFetched;

   static {
      if (VERSION.SDK_INT >= 22) {
         IMPL = new ViewUtilsApi22();
      } else if (VERSION.SDK_INT >= 21) {
         IMPL = new ViewUtilsApi21();
      } else if (VERSION.SDK_INT >= 19) {
         IMPL = new ViewUtilsApi19();
      } else {
         IMPL = new ViewUtilsBase();
      }

      TRANSITION_ALPHA = new Property(Float.class, "translationAlpha") {
         public Float get(View var1) {
            return ViewUtils.getTransitionAlpha(var1);
         }

         public void set(View var1, Float var2) {
            ViewUtils.setTransitionAlpha(var1, var2);
         }
      };
      CLIP_BOUNDS = new Property(Rect.class, "clipBounds") {
         public Rect get(View var1) {
            return ViewCompat.getClipBounds(var1);
         }

         public void set(View var1, Rect var2) {
            ViewCompat.setClipBounds(var1, var2);
         }
      };
   }

   private ViewUtils() {
   }

   static void clearNonTransitionAlpha(View var0) {
      IMPL.clearNonTransitionAlpha(var0);
   }

   private static void fetchViewFlagsField() {
      if (!sViewFlagsFieldFetched) {
         try {
            Field var0 = View.class.getDeclaredField("mViewFlags");
            sViewFlagsField = var0;
            var0.setAccessible(true);
         } catch (NoSuchFieldException var1) {
            Log.i("ViewUtils", "fetchViewFlagsField: ");
         }

         sViewFlagsFieldFetched = true;
      }

   }

   static ViewOverlayImpl getOverlay(View var0) {
      return (ViewOverlayImpl)(VERSION.SDK_INT >= 18 ? new ViewOverlayApi18(var0) : ViewOverlayApi14.createFrom(var0));
   }

   static float getTransitionAlpha(View var0) {
      return IMPL.getTransitionAlpha(var0);
   }

   static WindowIdImpl getWindowId(View var0) {
      return (WindowIdImpl)(VERSION.SDK_INT >= 18 ? new WindowIdApi18(var0) : new WindowIdApi14(var0.getWindowToken()));
   }

   static void saveNonTransitionAlpha(View var0) {
      IMPL.saveNonTransitionAlpha(var0);
   }

   static void setAnimationMatrix(View var0, Matrix var1) {
      IMPL.setAnimationMatrix(var0, var1);
   }

   static void setLeftTopRightBottom(View var0, int var1, int var2, int var3, int var4) {
      IMPL.setLeftTopRightBottom(var0, var1, var2, var3, var4);
   }

   static void setTransitionAlpha(View var0, float var1) {
      IMPL.setTransitionAlpha(var0, var1);
   }

   static void setTransitionVisibility(View var0, int var1) {
      fetchViewFlagsField();
      Field var3 = sViewFlagsField;
      if (var3 != null) {
         try {
            int var2 = var3.getInt(var0);
            sViewFlagsField.setInt(var0, var1 | var2 & -13);
         } catch (IllegalAccessException var4) {
         }
      }

   }

   static void transformMatrixToGlobal(View var0, Matrix var1) {
      IMPL.transformMatrixToGlobal(var0, var1);
   }

   static void transformMatrixToLocal(View var0, Matrix var1) {
      IMPL.transformMatrixToLocal(var0, var1);
   }
}
