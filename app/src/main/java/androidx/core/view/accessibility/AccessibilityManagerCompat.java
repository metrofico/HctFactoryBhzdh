package androidx.core.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

public final class AccessibilityManagerCompat {
   private AccessibilityManagerCompat() {
   }

   @Deprecated
   public static boolean addAccessibilityStateChangeListener(AccessibilityManager var0, AccessibilityStateChangeListener var1) {
      return var1 == null ? false : var0.addAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(var1));
   }

   public static boolean addTouchExplorationStateChangeListener(AccessibilityManager var0, TouchExplorationStateChangeListener var1) {
      if (VERSION.SDK_INT >= 19) {
         return var1 == null ? false : var0.addTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(var1));
      } else {
         return false;
      }
   }

   @Deprecated
   public static List getEnabledAccessibilityServiceList(AccessibilityManager var0, int var1) {
      return var0.getEnabledAccessibilityServiceList(var1);
   }

   @Deprecated
   public static List getInstalledAccessibilityServiceList(AccessibilityManager var0) {
      return var0.getInstalledAccessibilityServiceList();
   }

   @Deprecated
   public static boolean isTouchExplorationEnabled(AccessibilityManager var0) {
      return var0.isTouchExplorationEnabled();
   }

   @Deprecated
   public static boolean removeAccessibilityStateChangeListener(AccessibilityManager var0, AccessibilityStateChangeListener var1) {
      return var1 == null ? false : var0.removeAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(var1));
   }

   public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager var0, TouchExplorationStateChangeListener var1) {
      if (VERSION.SDK_INT >= 19) {
         return var1 == null ? false : var0.removeTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(var1));
      } else {
         return false;
      }
   }

   @Deprecated
   public interface AccessibilityStateChangeListener {
      @Deprecated
      void onAccessibilityStateChanged(boolean var1);
   }

   @Deprecated
   public abstract static class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener {
   }

   private static class AccessibilityStateChangeListenerWrapper implements AccessibilityManager.AccessibilityStateChangeListener {
      AccessibilityStateChangeListener mListener;

      AccessibilityStateChangeListenerWrapper(AccessibilityStateChangeListener var1) {
         this.mListener = var1;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof AccessibilityStateChangeListenerWrapper)) {
            return false;
         } else {
            AccessibilityStateChangeListenerWrapper var2 = (AccessibilityStateChangeListenerWrapper)var1;
            return this.mListener.equals(var2.mListener);
         }
      }

      public int hashCode() {
         return this.mListener.hashCode();
      }

      public void onAccessibilityStateChanged(boolean var1) {
         this.mListener.onAccessibilityStateChanged(var1);
      }
   }

   public interface TouchExplorationStateChangeListener {
      void onTouchExplorationStateChanged(boolean var1);
   }

   private static final class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager.TouchExplorationStateChangeListener {
      final TouchExplorationStateChangeListener mListener;

      TouchExplorationStateChangeListenerWrapper(TouchExplorationStateChangeListener var1) {
         this.mListener = var1;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof TouchExplorationStateChangeListenerWrapper)) {
            return false;
         } else {
            TouchExplorationStateChangeListenerWrapper var2 = (TouchExplorationStateChangeListenerWrapper)var1;
            return this.mListener.equals(var2.mListener);
         }
      }

      public int hashCode() {
         return this.mListener.hashCode();
      }

      public void onTouchExplorationStateChanged(boolean var1) {
         this.mListener.onTouchExplorationStateChanged(var1);
      }
   }
}
