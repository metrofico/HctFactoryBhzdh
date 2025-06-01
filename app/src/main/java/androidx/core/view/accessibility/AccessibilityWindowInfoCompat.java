package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityWindowInfo;

public class AccessibilityWindowInfoCompat {
   public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
   public static final int TYPE_APPLICATION = 1;
   public static final int TYPE_INPUT_METHOD = 2;
   public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
   public static final int TYPE_SYSTEM = 3;
   private static final int UNDEFINED = -1;
   private Object mInfo;

   private AccessibilityWindowInfoCompat(Object var1) {
      this.mInfo = var1;
   }

   public static AccessibilityWindowInfoCompat obtain() {
      return VERSION.SDK_INT >= 21 ? wrapNonNullInstance(AccessibilityWindowInfo.obtain()) : null;
   }

   public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat var0) {
      int var1 = VERSION.SDK_INT;
      Object var3 = null;
      AccessibilityWindowInfoCompat var2 = (AccessibilityWindowInfoCompat)var3;
      if (var1 >= 21) {
         if (var0 == null) {
            var2 = (AccessibilityWindowInfoCompat)var3;
         } else {
            var2 = wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)var0.mInfo));
         }
      }

      return var2;
   }

   private static String typeToString(int var0) {
      if (var0 != 1) {
         if (var0 != 2) {
            if (var0 != 3) {
               return var0 != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY";
            } else {
               return "TYPE_SYSTEM";
            }
         } else {
            return "TYPE_INPUT_METHOD";
         }
      } else {
         return "TYPE_APPLICATION";
      }
   }

   static AccessibilityWindowInfoCompat wrapNonNullInstance(Object var0) {
      return var0 != null ? new AccessibilityWindowInfoCompat(var0) : null;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!(var1 instanceof AccessibilityWindowInfoCompat)) {
         return false;
      } else {
         AccessibilityWindowInfoCompat var2 = (AccessibilityWindowInfoCompat)var1;
         var1 = this.mInfo;
         if (var1 == null) {
            if (var2.mInfo != null) {
               return false;
            }
         } else if (!var1.equals(var2.mInfo)) {
            return false;
         }

         return true;
      }
   }

   public AccessibilityNodeInfoCompat getAnchor() {
      return VERSION.SDK_INT >= 24 ? AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getAnchor()) : null;
   }

   public void getBoundsInScreen(Rect var1) {
      if (VERSION.SDK_INT >= 21) {
         ((AccessibilityWindowInfo)this.mInfo).getBoundsInScreen(var1);
      }

   }

   public AccessibilityWindowInfoCompat getChild(int var1) {
      return VERSION.SDK_INT >= 21 ? wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getChild(var1)) : null;
   }

   public int getChildCount() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getChildCount() : 0;
   }

   public int getId() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getId() : -1;
   }

   public int getLayer() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getLayer() : -1;
   }

   public AccessibilityWindowInfoCompat getParent() {
      return VERSION.SDK_INT >= 21 ? wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getParent()) : null;
   }

   public AccessibilityNodeInfoCompat getRoot() {
      return VERSION.SDK_INT >= 21 ? AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getRoot()) : null;
   }

   public CharSequence getTitle() {
      return VERSION.SDK_INT >= 24 ? ((AccessibilityWindowInfo)this.mInfo).getTitle() : null;
   }

   public int getType() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getType() : -1;
   }

   public int hashCode() {
      Object var2 = this.mInfo;
      int var1;
      if (var2 == null) {
         var1 = 0;
      } else {
         var1 = var2.hashCode();
      }

      return var1;
   }

   public boolean isAccessibilityFocused() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).isAccessibilityFocused() : true;
   }

   public boolean isActive() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).isActive() : true;
   }

   public boolean isFocused() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).isFocused() : true;
   }

   public void recycle() {
      if (VERSION.SDK_INT >= 21) {
         ((AccessibilityWindowInfo)this.mInfo).recycle();
      }

   }

   public String toString() {
      StringBuilder var3 = new StringBuilder();
      Rect var4 = new Rect();
      this.getBoundsInScreen(var4);
      var3.append("AccessibilityWindowInfo[");
      var3.append("id=").append(this.getId());
      var3.append(", type=").append(typeToString(this.getType()));
      var3.append(", layer=").append(this.getLayer());
      var3.append(", bounds=").append(var4);
      var3.append(", focused=").append(this.isFocused());
      var3.append(", active=").append(this.isActive());
      StringBuilder var5 = var3.append(", hasParent=");
      AccessibilityWindowInfoCompat var6 = this.getParent();
      boolean var2 = true;
      boolean var1;
      if (var6 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      var5.append(var1);
      StringBuilder var7 = var3.append(", hasChildren=");
      if (this.getChildCount() > 0) {
         var1 = var2;
      } else {
         var1 = false;
      }

      var7.append(var1);
      var3.append(']');
      return var3.toString();
   }
}
