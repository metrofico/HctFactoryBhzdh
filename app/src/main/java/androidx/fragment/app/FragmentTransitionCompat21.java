package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import java.util.ArrayList;
import java.util.List;

class FragmentTransitionCompat21 extends FragmentTransitionImpl {
   private static boolean hasSimpleTarget(Transition var0) {
      boolean var1;
      if (isNullOrEmpty(var0.getTargetIds()) && isNullOrEmpty(var0.getTargetNames()) && isNullOrEmpty(var0.getTargetTypes())) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public void addTarget(Object var1, View var2) {
      if (var1 != null) {
         ((Transition)var1).addTarget(var2);
      }

   }

   public void addTargets(Object var1, ArrayList var2) {
      Transition var7 = (Transition)var1;
      if (var7 != null) {
         boolean var6 = var7 instanceof TransitionSet;
         int var4 = 0;
         int var3 = 0;
         if (var6) {
            TransitionSet var8 = (TransitionSet)var7;

            for(var4 = var8.getTransitionCount(); var3 < var4; ++var3) {
               this.addTargets(var8.getTransitionAt(var3), var2);
            }
         } else if (!hasSimpleTarget(var7) && isNullOrEmpty(var7.getTargets())) {
            int var5 = var2.size();

            for(var3 = var4; var3 < var5; ++var3) {
               var7.addTarget((View)var2.get(var3));
            }
         }

      }
   }

   public void beginDelayedTransition(ViewGroup var1, Object var2) {
      TransitionManager.beginDelayedTransition(var1, (Transition)var2);
   }

   public boolean canHandle(Object var1) {
      return var1 instanceof Transition;
   }

   public Object cloneTransition(Object var1) {
      Transition var2;
      if (var1 != null) {
         var2 = ((Transition)var1).clone();
      } else {
         var2 = null;
      }

      return var2;
   }

   public Object mergeTransitionsInSequence(Object var1, Object var2, Object var3) {
      var1 = (Transition)var1;
      Transition var4 = (Transition)var2;
      Transition var6 = (Transition)var3;
      if (var1 != null && var4 != null) {
         var1 = (new TransitionSet()).addTransition((Transition)var1).addTransition(var4).setOrdering(1);
      } else if (var1 == null) {
         if (var4 != null) {
            var1 = var4;
         } else {
            var1 = null;
         }
      }

      if (var6 != null) {
         TransitionSet var5 = new TransitionSet();
         if (var1 != null) {
            var5.addTransition((Transition)var1);
         }

         var5.addTransition(var6);
         return var5;
      } else {
         return var1;
      }
   }

   public Object mergeTransitionsTogether(Object var1, Object var2, Object var3) {
      TransitionSet var4 = new TransitionSet();
      if (var1 != null) {
         var4.addTransition((Transition)var1);
      }

      if (var2 != null) {
         var4.addTransition((Transition)var2);
      }

      if (var3 != null) {
         var4.addTransition((Transition)var3);
      }

      return var4;
   }

   public void removeTarget(Object var1, View var2) {
      if (var1 != null) {
         ((Transition)var1).removeTarget(var2);
      }

   }

   public void replaceTargets(Object var1, ArrayList var2, ArrayList var3) {
      Transition var8 = (Transition)var1;
      boolean var6 = var8 instanceof TransitionSet;
      int var5 = 0;
      int var4 = 0;
      if (var6) {
         TransitionSet var9 = (TransitionSet)var8;

         for(var5 = var9.getTransitionCount(); var4 < var5; ++var4) {
            this.replaceTargets(var9.getTransitionAt(var4), var2, var3);
         }
      } else if (!hasSimpleTarget(var8)) {
         List var7 = var8.getTargets();
         if (var7 != null && var7.size() == var2.size() && var7.containsAll(var2)) {
            if (var3 == null) {
               var4 = 0;
            } else {
               var4 = var3.size();
            }

            while(var5 < var4) {
               var8.addTarget((View)var3.get(var5));
               ++var5;
            }

            for(var4 = var2.size() - 1; var4 >= 0; --var4) {
               var8.removeTarget((View)var2.get(var4));
            }
         }
      }

   }

   public void scheduleHideFragmentView(Object var1, View var2, ArrayList var3) {
      ((Transition)var1).addListener(new Transition.TransitionListener(this, var2, var3) {
         final FragmentTransitionCompat21 this$0;
         final ArrayList val$exitingViews;
         final View val$fragmentView;

         {
            this.this$0 = var1;
            this.val$fragmentView = var2;
            this.val$exitingViews = var3;
         }

         public void onTransitionCancel(Transition var1) {
         }

         public void onTransitionEnd(Transition var1) {
            var1.removeListener(this);
            this.val$fragmentView.setVisibility(8);
            int var3 = this.val$exitingViews.size();

            for(int var2 = 0; var2 < var3; ++var2) {
               ((View)this.val$exitingViews.get(var2)).setVisibility(0);
            }

         }

         public void onTransitionPause(Transition var1) {
         }

         public void onTransitionResume(Transition var1) {
         }

         public void onTransitionStart(Transition var1) {
            var1.removeListener(this);
            var1.addListener(this);
         }
      });
   }

   public void scheduleRemoveTargets(Object var1, Object var2, ArrayList var3, Object var4, ArrayList var5, Object var6, ArrayList var7) {
      ((Transition)var1).addListener(new Transition.TransitionListener(this, var2, var3, var4, var5, var6, var7) {
         final FragmentTransitionCompat21 this$0;
         final Object val$enterTransition;
         final ArrayList val$enteringViews;
         final Object val$exitTransition;
         final ArrayList val$exitingViews;
         final Object val$sharedElementTransition;
         final ArrayList val$sharedElementsIn;

         {
            this.this$0 = var1;
            this.val$enterTransition = var2;
            this.val$enteringViews = var3;
            this.val$exitTransition = var4;
            this.val$exitingViews = var5;
            this.val$sharedElementTransition = var6;
            this.val$sharedElementsIn = var7;
         }

         public void onTransitionCancel(Transition var1) {
         }

         public void onTransitionEnd(Transition var1) {
            var1.removeListener(this);
         }

         public void onTransitionPause(Transition var1) {
         }

         public void onTransitionResume(Transition var1) {
         }

         public void onTransitionStart(Transition var1) {
            Object var2 = this.val$enterTransition;
            if (var2 != null) {
               this.this$0.replaceTargets(var2, this.val$enteringViews, (ArrayList)null);
            }

            var2 = this.val$exitTransition;
            if (var2 != null) {
               this.this$0.replaceTargets(var2, this.val$exitingViews, (ArrayList)null);
            }

            var2 = this.val$sharedElementTransition;
            if (var2 != null) {
               this.this$0.replaceTargets(var2, this.val$sharedElementsIn, (ArrayList)null);
            }

         }
      });
   }

   public void setEpicenter(Object var1, Rect var2) {
      if (var1 != null) {
         ((Transition)var1).setEpicenterCallback(new Transition.EpicenterCallback(this, var2) {
            final FragmentTransitionCompat21 this$0;
            final Rect val$epicenter;

            {
               this.this$0 = var1;
               this.val$epicenter = var2;
            }

            public Rect onGetEpicenter(Transition var1) {
               Rect var2 = this.val$epicenter;
               return var2 != null && !var2.isEmpty() ? this.val$epicenter : null;
            }
         });
      }

   }

   public void setEpicenter(Object var1, View var2) {
      if (var2 != null) {
         Transition var4 = (Transition)var1;
         Rect var3 = new Rect();
         this.getBoundsOnScreen(var2, var3);
         var4.setEpicenterCallback(new Transition.EpicenterCallback(this, var3) {
            final FragmentTransitionCompat21 this$0;
            final Rect val$epicenter;

            {
               this.this$0 = var1;
               this.val$epicenter = var2;
            }

            public Rect onGetEpicenter(Transition var1) {
               return this.val$epicenter;
            }
         });
      }

   }

   public void setListenerForTransitionEnd(Fragment var1, Object var2, CancellationSignal var3, Runnable var4) {
      ((Transition)var2).addListener(new Transition.TransitionListener(this, var4) {
         final FragmentTransitionCompat21 this$0;
         final Runnable val$transitionCompleteRunnable;

         {
            this.this$0 = var1;
            this.val$transitionCompleteRunnable = var2;
         }

         public void onTransitionCancel(Transition var1) {
         }

         public void onTransitionEnd(Transition var1) {
            this.val$transitionCompleteRunnable.run();
         }

         public void onTransitionPause(Transition var1) {
         }

         public void onTransitionResume(Transition var1) {
         }

         public void onTransitionStart(Transition var1) {
         }
      });
   }

   public void setSharedElementTargets(Object var1, View var2, ArrayList var3) {
      TransitionSet var6 = (TransitionSet)var1;
      List var7 = var6.getTargets();
      var7.clear();
      int var5 = var3.size();

      for(int var4 = 0; var4 < var5; ++var4) {
         bfsAddViewChildren(var7, (View)var3.get(var4));
      }

      var7.add(var2);
      var3.add(var2);
      this.addTargets(var6, var3);
   }

   public void swapSharedElementTargets(Object var1, ArrayList var2, ArrayList var3) {
      TransitionSet var4 = (TransitionSet)var1;
      if (var4 != null) {
         var4.getTargets().clear();
         var4.getTargets().addAll(var3);
         this.replaceTargets(var4, var2, var3);
      }

   }

   public Object wrapTransitionInSet(Object var1) {
      if (var1 == null) {
         return null;
      } else {
         TransitionSet var2 = new TransitionSet();
         var2.addTransition((Transition)var1);
         return var2;
      }
   }
}
