package androidx.fragment.app;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class FragmentTransitionImpl {
   protected static void bfsAddViewChildren(List var0, View var1) {
      int var4 = var0.size();
      if (!containedBeforeIndex(var0, var1, var4)) {
         if (ViewCompat.getTransitionName(var1) != null) {
            var0.add(var1);
         }

         for(int var2 = var4; var2 < var0.size(); ++var2) {
            var1 = (View)var0.get(var2);
            if (var1 instanceof ViewGroup) {
               ViewGroup var6 = (ViewGroup)var1;
               int var5 = var6.getChildCount();

               for(int var3 = 0; var3 < var5; ++var3) {
                  var1 = var6.getChildAt(var3);
                  if (!containedBeforeIndex(var0, var1, var4) && ViewCompat.getTransitionName(var1) != null) {
                     var0.add(var1);
                  }
               }
            }
         }

      }
   }

   private static boolean containedBeforeIndex(List var0, View var1, int var2) {
      for(int var3 = 0; var3 < var2; ++var3) {
         if (var0.get(var3) == var1) {
            return true;
         }
      }

      return false;
   }

   static String findKeyForValue(Map var0, String var1) {
      Iterator var2 = var0.entrySet().iterator();

      Map.Entry var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (Map.Entry)var2.next();
      } while(!var1.equals(var3.getValue()));

      return (String)var3.getKey();
   }

   protected static boolean isNullOrEmpty(List var0) {
      boolean var1;
      if (var0 != null && !var0.isEmpty()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public abstract void addTarget(Object var1, View var2);

   public abstract void addTargets(Object var1, ArrayList var2);

   public abstract void beginDelayedTransition(ViewGroup var1, Object var2);

   public abstract boolean canHandle(Object var1);

   void captureTransitioningViews(ArrayList var1, View var2) {
      if (var2.getVisibility() == 0) {
         if (var2 instanceof ViewGroup) {
            ViewGroup var5 = (ViewGroup)var2;
            if (ViewGroupCompat.isTransitionGroup(var5)) {
               var1.add(var5);
            } else {
               int var4 = var5.getChildCount();

               for(int var3 = 0; var3 < var4; ++var3) {
                  this.captureTransitioningViews(var1, var5.getChildAt(var3));
               }
            }
         } else {
            var1.add(var2);
         }
      }

   }

   public abstract Object cloneTransition(Object var1);

   void findNamedViews(Map var1, View var2) {
      if (var2.getVisibility() == 0) {
         String var5 = ViewCompat.getTransitionName(var2);
         if (var5 != null) {
            var1.put(var5, var2);
         }

         if (var2 instanceof ViewGroup) {
            ViewGroup var6 = (ViewGroup)var2;
            int var4 = var6.getChildCount();

            for(int var3 = 0; var3 < var4; ++var3) {
               this.findNamedViews(var1, var6.getChildAt(var3));
            }
         }
      }

   }

   protected void getBoundsOnScreen(View var1, Rect var2) {
      if (ViewCompat.isAttachedToWindow(var1)) {
         RectF var4 = new RectF();
         var4.set(0.0F, 0.0F, (float)var1.getWidth(), (float)var1.getHeight());
         var1.getMatrix().mapRect(var4);
         var4.offset((float)var1.getLeft(), (float)var1.getTop());

         View var5;
         for(ViewParent var3 = var1.getParent(); var3 instanceof View; var3 = var5.getParent()) {
            var5 = (View)var3;
            var4.offset((float)(-var5.getScrollX()), (float)(-var5.getScrollY()));
            var5.getMatrix().mapRect(var4);
            var4.offset((float)var5.getLeft(), (float)var5.getTop());
         }

         int[] var6 = new int[2];
         var1.getRootView().getLocationOnScreen(var6);
         var4.offset((float)var6[0], (float)var6[1]);
         var2.set(Math.round(var4.left), Math.round(var4.top), Math.round(var4.right), Math.round(var4.bottom));
      }
   }

   public abstract Object mergeTransitionsInSequence(Object var1, Object var2, Object var3);

   public abstract Object mergeTransitionsTogether(Object var1, Object var2, Object var3);

   ArrayList prepareSetNameOverridesReordered(ArrayList var1) {
      ArrayList var5 = new ArrayList();
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var4 = (View)var1.get(var2);
         var5.add(ViewCompat.getTransitionName(var4));
         ViewCompat.setTransitionName(var4, (String)null);
      }

      return var5;
   }

   public abstract void removeTarget(Object var1, View var2);

   public abstract void replaceTargets(Object var1, ArrayList var2, ArrayList var3);

   public abstract void scheduleHideFragmentView(Object var1, View var2, ArrayList var3);

   void scheduleNameReset(ViewGroup var1, ArrayList var2, Map var3) {
      OneShotPreDrawListener.add(var1, new Runnable(this, var2, var3) {
         final FragmentTransitionImpl this$0;
         final Map val$nameOverrides;
         final ArrayList val$sharedElementsIn;

         {
            this.this$0 = var1;
            this.val$sharedElementsIn = var2;
            this.val$nameOverrides = var3;
         }

         public void run() {
            int var2 = this.val$sharedElementsIn.size();

            for(int var1 = 0; var1 < var2; ++var1) {
               View var4 = (View)this.val$sharedElementsIn.get(var1);
               String var3 = ViewCompat.getTransitionName(var4);
               ViewCompat.setTransitionName(var4, (String)this.val$nameOverrides.get(var3));
            }

         }
      });
   }

   public abstract void scheduleRemoveTargets(Object var1, Object var2, ArrayList var3, Object var4, ArrayList var5, Object var6, ArrayList var7);

   public abstract void setEpicenter(Object var1, Rect var2);

   public abstract void setEpicenter(Object var1, View var2);

   public void setListenerForTransitionEnd(Fragment var1, Object var2, CancellationSignal var3, Runnable var4) {
      var4.run();
   }

   void setNameOverridesOrdered(View var1, ArrayList var2, Map var3) {
      OneShotPreDrawListener.add(var1, new Runnable(this, var2, var3) {
         final FragmentTransitionImpl this$0;
         final Map val$nameOverrides;
         final ArrayList val$sharedElementsIn;

         {
            this.this$0 = var1;
            this.val$sharedElementsIn = var2;
            this.val$nameOverrides = var3;
         }

         public void run() {
            int var2 = this.val$sharedElementsIn.size();

            for(int var1 = 0; var1 < var2; ++var1) {
               View var4 = (View)this.val$sharedElementsIn.get(var1);
               String var3 = ViewCompat.getTransitionName(var4);
               if (var3 != null) {
                  ViewCompat.setTransitionName(var4, FragmentTransitionImpl.findKeyForValue(this.val$nameOverrides, var3));
               }
            }

         }
      });
   }

   void setNameOverridesReordered(View var1, ArrayList var2, ArrayList var3, ArrayList var4, Map var5) {
      int var8 = var3.size();
      ArrayList var10 = new ArrayList();

      for(int var6 = 0; var6 < var8; ++var6) {
         View var11 = (View)var2.get(var6);
         String var9 = ViewCompat.getTransitionName(var11);
         var10.add(var9);
         if (var9 != null) {
            ViewCompat.setTransitionName(var11, (String)null);
            String var12 = (String)var5.get(var9);

            for(int var7 = 0; var7 < var8; ++var7) {
               if (var12.equals(var4.get(var7))) {
                  ViewCompat.setTransitionName((View)var3.get(var7), var9);
                  break;
               }
            }
         }
      }

      OneShotPreDrawListener.add(var1, new Runnable(this, var8, var3, var4, var2, var10) {
         final FragmentTransitionImpl this$0;
         final ArrayList val$inNames;
         final int val$numSharedElements;
         final ArrayList val$outNames;
         final ArrayList val$sharedElementsIn;
         final ArrayList val$sharedElementsOut;

         {
            this.this$0 = var1;
            this.val$numSharedElements = var2;
            this.val$sharedElementsIn = var3;
            this.val$inNames = var4;
            this.val$sharedElementsOut = var5;
            this.val$outNames = var6;
         }

         public void run() {
            for(int var1 = 0; var1 < this.val$numSharedElements; ++var1) {
               ViewCompat.setTransitionName((View)this.val$sharedElementsIn.get(var1), (String)this.val$inNames.get(var1));
               ViewCompat.setTransitionName((View)this.val$sharedElementsOut.get(var1), (String)this.val$outNames.get(var1));
            }

         }
      });
   }

   public abstract void setSharedElementTargets(Object var1, View var2, ArrayList var3);

   public abstract void swapSharedElementTargets(Object var1, ArrayList var2, ArrayList var3);

   public abstract Object wrapTransitionInSet(Object var1);
}
