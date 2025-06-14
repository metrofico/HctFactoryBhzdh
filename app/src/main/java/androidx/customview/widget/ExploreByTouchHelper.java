package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewParentCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
   private static final String DEFAULT_CLASS_NAME = "android.view.View";
   public static final int HOST_ID = -1;
   public static final int INVALID_ID = Integer.MIN_VALUE;
   private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
   private static final FocusStrategy.BoundsAdapter NODE_ADAPTER = new FocusStrategy.BoundsAdapter() {
      public void obtainBounds(AccessibilityNodeInfoCompat var1, Rect var2) {
         var1.getBoundsInParent(var2);
      }
   };
   private static final FocusStrategy.CollectionAdapter SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter() {
      public AccessibilityNodeInfoCompat get(SparseArrayCompat var1, int var2) {
         return (AccessibilityNodeInfoCompat)var1.valueAt(var2);
      }

      public int size(SparseArrayCompat var1) {
         return var1.size();
      }
   };
   int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
   private final View mHost;
   private int mHoveredVirtualViewId = Integer.MIN_VALUE;
   int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
   private final AccessibilityManager mManager;
   private MyNodeProvider mNodeProvider;
   private final int[] mTempGlobalRect = new int[2];
   private final Rect mTempParentRect = new Rect();
   private final Rect mTempScreenRect = new Rect();
   private final Rect mTempVisibleRect = new Rect();

   public ExploreByTouchHelper(View var1) {
      if (var1 != null) {
         this.mHost = var1;
         this.mManager = (AccessibilityManager)var1.getContext().getSystemService("accessibility");
         var1.setFocusable(true);
         if (ViewCompat.getImportantForAccessibility(var1) == 0) {
            ViewCompat.setImportantForAccessibility(var1, 1);
         }

      } else {
         throw new IllegalArgumentException("View may not be null");
      }
   }

   private boolean clearAccessibilityFocus(int var1) {
      if (this.mAccessibilityFocusedVirtualViewId == var1) {
         this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
         this.mHost.invalidate();
         this.sendEventForVirtualView(var1, 65536);
         return true;
      } else {
         return false;
      }
   }

   private boolean clickKeyboardFocusedVirtualView() {
      int var1 = this.mKeyboardFocusedVirtualViewId;
      boolean var2;
      if (var1 != Integer.MIN_VALUE && this.onPerformActionForVirtualView(var1, 16, (Bundle)null)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private AccessibilityEvent createEvent(int var1, int var2) {
      return var1 != -1 ? this.createEventForChild(var1, var2) : this.createEventForHost(var2);
   }

   private AccessibilityEvent createEventForChild(int var1, int var2) {
      AccessibilityEvent var4 = AccessibilityEvent.obtain(var2);
      AccessibilityNodeInfoCompat var3 = this.obtainAccessibilityNodeInfo(var1);
      var4.getText().add(var3.getText());
      var4.setContentDescription(var3.getContentDescription());
      var4.setScrollable(var3.isScrollable());
      var4.setPassword(var3.isPassword());
      var4.setEnabled(var3.isEnabled());
      var4.setChecked(var3.isChecked());
      this.onPopulateEventForVirtualView(var1, var4);
      if (var4.getText().isEmpty() && var4.getContentDescription() == null) {
         throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
      } else {
         var4.setClassName(var3.getClassName());
         AccessibilityRecordCompat.setSource(var4, this.mHost, var1);
         var4.setPackageName(this.mHost.getContext().getPackageName());
         return var4;
      }
   }

   private AccessibilityEvent createEventForHost(int var1) {
      AccessibilityEvent var2 = AccessibilityEvent.obtain(var1);
      this.mHost.onInitializeAccessibilityEvent(var2);
      return var2;
   }

   private AccessibilityNodeInfoCompat createNodeForChild(int var1) {
      AccessibilityNodeInfoCompat var4 = AccessibilityNodeInfoCompat.obtain();
      var4.setEnabled(true);
      var4.setFocusable(true);
      var4.setClassName("android.view.View");
      Rect var5 = INVALID_PARENT_BOUNDS;
      var4.setBoundsInParent(var5);
      var4.setBoundsInScreen(var5);
      var4.setParent(this.mHost);
      this.onPopulateNodeForVirtualView(var1, var4);
      if (var4.getText() == null && var4.getContentDescription() == null) {
         throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
      } else {
         var4.getBoundsInParent(this.mTempParentRect);
         if (!this.mTempParentRect.equals(var5)) {
            int var2 = var4.getActions();
            if ((var2 & 64) != 0) {
               throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            } else if ((var2 & 128) == 0) {
               var4.setPackageName(this.mHost.getContext().getPackageName());
               var4.setSource(this.mHost, var1);
               if (this.mAccessibilityFocusedVirtualViewId == var1) {
                  var4.setAccessibilityFocused(true);
                  var4.addAction(128);
               } else {
                  var4.setAccessibilityFocused(false);
                  var4.addAction(64);
               }

               boolean var3;
               if (this.mKeyboardFocusedVirtualViewId == var1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  var4.addAction(2);
               } else if (var4.isFocusable()) {
                  var4.addAction(1);
               }

               var4.setFocused(var3);
               this.mHost.getLocationOnScreen(this.mTempGlobalRect);
               var4.getBoundsInScreen(this.mTempScreenRect);
               if (this.mTempScreenRect.equals(var5)) {
                  var4.getBoundsInParent(this.mTempScreenRect);
                  if (var4.mParentVirtualDescendantId != -1) {
                     AccessibilityNodeInfoCompat var6 = AccessibilityNodeInfoCompat.obtain();

                     for(var1 = var4.mParentVirtualDescendantId; var1 != -1; var1 = var6.mParentVirtualDescendantId) {
                        var6.setParent(this.mHost, -1);
                        var6.setBoundsInParent(INVALID_PARENT_BOUNDS);
                        this.onPopulateNodeForVirtualView(var1, var6);
                        var6.getBoundsInParent(this.mTempParentRect);
                        this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
                     }

                     var6.recycle();
                  }

                  this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
               }

               if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                  this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                  if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                     var4.setBoundsInScreen(this.mTempScreenRect);
                     if (this.isVisibleToUser(this.mTempScreenRect)) {
                        var4.setVisibleToUser(true);
                     }
                  }
               }

               return var4;
            } else {
               throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
         } else {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
         }
      }
   }

   private AccessibilityNodeInfoCompat createNodeForHost() {
      AccessibilityNodeInfoCompat var3 = AccessibilityNodeInfoCompat.obtain(this.mHost);
      ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, var3);
      ArrayList var4 = new ArrayList();
      this.getVisibleVirtualViews(var4);
      if (var3.getChildCount() > 0 && var4.size() > 0) {
         throw new RuntimeException("Views cannot have both real and virtual children");
      } else {
         int var1 = 0;

         for(int var2 = var4.size(); var1 < var2; ++var1) {
            var3.addChild(this.mHost, (Integer)var4.get(var1));
         }

         return var3;
      }
   }

   private SparseArrayCompat getAllNodes() {
      ArrayList var2 = new ArrayList();
      this.getVisibleVirtualViews(var2);
      SparseArrayCompat var3 = new SparseArrayCompat();

      for(int var1 = 0; var1 < var2.size(); ++var1) {
         var3.put(var1, this.createNodeForChild(var1));
      }

      return var3;
   }

   private void getBoundsInParent(int var1, Rect var2) {
      this.obtainAccessibilityNodeInfo(var1).getBoundsInParent(var2);
   }

   private static Rect guessPreviouslyFocusedRect(View var0, int var1, Rect var2) {
      int var3 = var0.getWidth();
      int var4 = var0.getHeight();
      if (var1 != 17) {
         if (var1 != 33) {
            if (var1 != 66) {
               if (var1 != 130) {
                  throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
               }

               var2.set(0, -1, var3, -1);
            } else {
               var2.set(-1, 0, -1, var4);
            }
         } else {
            var2.set(0, var4, var3, var4);
         }
      } else {
         var2.set(var3, 0, var3, var4);
      }

      return var2;
   }

   private boolean isVisibleToUser(Rect var1) {
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 != null) {
         if (var1.isEmpty()) {
            var2 = var3;
         } else {
            if (this.mHost.getWindowVisibility() != 0) {
               return false;
            }

            ViewParent var4 = this.mHost.getParent();

            while(true) {
               if (!(var4 instanceof View)) {
                  var2 = var3;
                  if (var4 != null) {
                     var2 = true;
                  }
                  break;
               }

               View var5 = (View)var4;
               if (var5.getAlpha() <= 0.0F || var5.getVisibility() != 0) {
                  return false;
               }

               var4 = var5.getParent();
            }
         }
      }

      return var2;
   }

   private static int keyToDirection(int var0) {
      if (var0 != 19) {
         if (var0 != 21) {
            return var0 != 22 ? 130 : 66;
         } else {
            return 17;
         }
      } else {
         return 33;
      }
   }

   private boolean moveFocus(int var1, Rect var2) {
      SparseArrayCompat var7 = this.getAllNodes();
      int var4 = this.mKeyboardFocusedVirtualViewId;
      int var3 = Integer.MIN_VALUE;
      AccessibilityNodeInfoCompat var6;
      if (var4 == Integer.MIN_VALUE) {
         var6 = null;
      } else {
         var6 = (AccessibilityNodeInfoCompat)var7.get(var4);
      }

      AccessibilityNodeInfoCompat var9;
      if (var1 != 1 && var1 != 2) {
         if (var1 != 17 && var1 != 33 && var1 != 66 && var1 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
         }

         Rect var8 = new Rect();
         var4 = this.mKeyboardFocusedVirtualViewId;
         if (var4 != Integer.MIN_VALUE) {
            this.getBoundsInParent(var4, var8);
         } else if (var2 != null) {
            var8.set(var2);
         } else {
            guessPreviouslyFocusedRect(this.mHost, var1, var8);
         }

         var9 = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInAbsoluteDirection(var7, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, var6, var8, var1);
      } else {
         boolean var5;
         if (ViewCompat.getLayoutDirection(this.mHost) == 1) {
            var5 = true;
         } else {
            var5 = false;
         }

         var9 = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInRelativeDirection(var7, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, var6, var1, var5, false);
      }

      if (var9 == null) {
         var1 = var3;
      } else {
         var1 = var7.keyAt(var7.indexOfValue(var9));
      }

      return this.requestKeyboardFocusForVirtualView(var1);
   }

   private boolean performActionForChild(int var1, int var2, Bundle var3) {
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 64) {
               return var2 != 128 ? this.onPerformActionForVirtualView(var1, var2, var3) : this.clearAccessibilityFocus(var1);
            } else {
               return this.requestAccessibilityFocus(var1);
            }
         } else {
            return this.clearKeyboardFocusForVirtualView(var1);
         }
      } else {
         return this.requestKeyboardFocusForVirtualView(var1);
      }
   }

   private boolean performActionForHost(int var1, Bundle var2) {
      return ViewCompat.performAccessibilityAction(this.mHost, var1, var2);
   }

   private boolean requestAccessibilityFocus(int var1) {
      if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
         int var2 = this.mAccessibilityFocusedVirtualViewId;
         if (var2 != var1) {
            if (var2 != Integer.MIN_VALUE) {
               this.clearAccessibilityFocus(var2);
            }

            this.mAccessibilityFocusedVirtualViewId = var1;
            this.mHost.invalidate();
            this.sendEventForVirtualView(var1, 32768);
            return true;
         }
      }

      return false;
   }

   private void updateHoveredVirtualView(int var1) {
      int var2 = this.mHoveredVirtualViewId;
      if (var2 != var1) {
         this.mHoveredVirtualViewId = var1;
         this.sendEventForVirtualView(var1, 128);
         this.sendEventForVirtualView(var2, 256);
      }
   }

   public final boolean clearKeyboardFocusForVirtualView(int var1) {
      if (this.mKeyboardFocusedVirtualViewId != var1) {
         return false;
      } else {
         this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
         this.onVirtualViewKeyboardFocusChanged(var1, false);
         this.sendEventForVirtualView(var1, 8);
         return true;
      }
   }

   public final boolean dispatchHoverEvent(MotionEvent var1) {
      boolean var5 = this.mManager.isEnabled();
      boolean var4 = false;
      boolean var3 = var4;
      if (var5) {
         if (!this.mManager.isTouchExplorationEnabled()) {
            var3 = var4;
         } else {
            int var2 = var1.getAction();
            if (var2 != 7 && var2 != 9) {
               if (var2 != 10) {
                  return false;
               }

               if (this.mHoveredVirtualViewId != Integer.MIN_VALUE) {
                  this.updateHoveredVirtualView(Integer.MIN_VALUE);
                  return true;
               }

               return false;
            }

            var2 = this.getVirtualViewAt(var1.getX(), var1.getY());
            this.updateHoveredVirtualView(var2);
            var3 = var4;
            if (var2 != Integer.MIN_VALUE) {
               var3 = true;
            }
         }
      }

      return var3;
   }

   public final boolean dispatchKeyEvent(KeyEvent var1) {
      int var3 = var1.getAction();
      boolean var6 = false;
      int var2 = 0;
      boolean var5 = var6;
      if (var3 != 1) {
         var3 = var1.getKeyCode();
         if (var3 != 61) {
            if (var3 != 66) {
               switch (var3) {
                  case 19:
                  case 20:
                  case 21:
                  case 22:
                     var5 = var6;
                     if (var1.hasNoModifiers()) {
                        int var4 = keyToDirection(var3);
                        var3 = var1.getRepeatCount();

                        for(var5 = false; var2 < var3 + 1 && this.moveFocus(var4, (Rect)null); var5 = true) {
                           ++var2;
                        }
                     }

                     return var5;
                  case 23:
                     break;
                  default:
                     var5 = var6;
                     return var5;
               }
            }

            var5 = var6;
            if (var1.hasNoModifiers()) {
               var5 = var6;
               if (var1.getRepeatCount() == 0) {
                  this.clickKeyboardFocusedVirtualView();
                  var5 = true;
               }
            }
         } else if (var1.hasNoModifiers()) {
            var5 = this.moveFocus(2, (Rect)null);
         } else {
            var5 = var6;
            if (var1.hasModifiers(1)) {
               var5 = this.moveFocus(1, (Rect)null);
            }
         }
      }

      return var5;
   }

   public final int getAccessibilityFocusedVirtualViewId() {
      return this.mAccessibilityFocusedVirtualViewId;
   }

   public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View var1) {
      if (this.mNodeProvider == null) {
         this.mNodeProvider = new MyNodeProvider(this);
      }

      return this.mNodeProvider;
   }

   @Deprecated
   public int getFocusedVirtualView() {
      return this.getAccessibilityFocusedVirtualViewId();
   }

   public final int getKeyboardFocusedVirtualViewId() {
      return this.mKeyboardFocusedVirtualViewId;
   }

   protected abstract int getVirtualViewAt(float var1, float var2);

   protected abstract void getVisibleVirtualViews(List var1);

   public final void invalidateRoot() {
      this.invalidateVirtualView(-1, 1);
   }

   public final void invalidateVirtualView(int var1) {
      this.invalidateVirtualView(var1, 0);
   }

   public final void invalidateVirtualView(int var1, int var2) {
      if (var1 != Integer.MIN_VALUE && this.mManager.isEnabled()) {
         ViewParent var3 = this.mHost.getParent();
         if (var3 != null) {
            AccessibilityEvent var4 = this.createEvent(var1, 2048);
            AccessibilityEventCompat.setContentChangeTypes(var4, var2);
            ViewParentCompat.requestSendAccessibilityEvent(var3, this.mHost, var4);
         }
      }

   }

   AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int var1) {
      return var1 == -1 ? this.createNodeForHost() : this.createNodeForChild(var1);
   }

   public final void onFocusChanged(boolean var1, int var2, Rect var3) {
      int var4 = this.mKeyboardFocusedVirtualViewId;
      if (var4 != Integer.MIN_VALUE) {
         this.clearKeyboardFocusForVirtualView(var4);
      }

      if (var1) {
         this.moveFocus(var2, var3);
      }

   }

   public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2) {
      super.onInitializeAccessibilityEvent(var1, var2);
      this.onPopulateEventForHost(var2);
   }

   public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
      super.onInitializeAccessibilityNodeInfo(var1, var2);
      this.onPopulateNodeForHost(var2);
   }

   protected abstract boolean onPerformActionForVirtualView(int var1, int var2, Bundle var3);

   protected void onPopulateEventForHost(AccessibilityEvent var1) {
   }

   protected void onPopulateEventForVirtualView(int var1, AccessibilityEvent var2) {
   }

   protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat var1) {
   }

   protected abstract void onPopulateNodeForVirtualView(int var1, AccessibilityNodeInfoCompat var2);

   protected void onVirtualViewKeyboardFocusChanged(int var1, boolean var2) {
   }

   boolean performAction(int var1, int var2, Bundle var3) {
      return var1 != -1 ? this.performActionForChild(var1, var2, var3) : this.performActionForHost(var2, var3);
   }

   public final boolean requestKeyboardFocusForVirtualView(int var1) {
      if (!this.mHost.isFocused() && !this.mHost.requestFocus()) {
         return false;
      } else {
         int var2 = this.mKeyboardFocusedVirtualViewId;
         if (var2 == var1) {
            return false;
         } else {
            if (var2 != Integer.MIN_VALUE) {
               this.clearKeyboardFocusForVirtualView(var2);
            }

            this.mKeyboardFocusedVirtualViewId = var1;
            this.onVirtualViewKeyboardFocusChanged(var1, true);
            this.sendEventForVirtualView(var1, 8);
            return true;
         }
      }
   }

   public final boolean sendEventForVirtualView(int var1, int var2) {
      if (var1 != Integer.MIN_VALUE && this.mManager.isEnabled()) {
         ViewParent var3 = this.mHost.getParent();
         if (var3 == null) {
            return false;
         } else {
            AccessibilityEvent var4 = this.createEvent(var1, var2);
            return ViewParentCompat.requestSendAccessibilityEvent(var3, this.mHost, var4);
         }
      } else {
         return false;
      }
   }

   private class MyNodeProvider extends AccessibilityNodeProviderCompat {
      final ExploreByTouchHelper this$0;

      MyNodeProvider(ExploreByTouchHelper var1) {
         this.this$0 = var1;
      }

      public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int var1) {
         return AccessibilityNodeInfoCompat.obtain(this.this$0.obtainAccessibilityNodeInfo(var1));
      }

      public AccessibilityNodeInfoCompat findFocus(int var1) {
         if (var1 == 2) {
            var1 = this.this$0.mAccessibilityFocusedVirtualViewId;
         } else {
            var1 = this.this$0.mKeyboardFocusedVirtualViewId;
         }

         return var1 == Integer.MIN_VALUE ? null : this.createAccessibilityNodeInfo(var1);
      }

      public boolean performAction(int var1, int var2, Bundle var3) {
         return this.this$0.performAction(var1, var2, var3);
      }
   }
}
