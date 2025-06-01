package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

abstract class SpecialEffectsController {
   private final ViewGroup mContainer;
   boolean mIsContainerPostponed = false;
   boolean mOperationDirectionIsPop = false;
   final ArrayList mPendingOperations = new ArrayList();
   final ArrayList mRunningOperations = new ArrayList();

   SpecialEffectsController(ViewGroup var1) {
      this.mContainer = var1;
   }

   private void enqueue(Operation.State var1, Operation.LifecycleImpact var2, FragmentStateManager var3) {
      ArrayList var4 = this.mPendingOperations;
      synchronized(var4){}

      Throwable var10000;
      boolean var10001;
      label164: {
         Operation var5;
         CancellationSignal var6;
         try {
            var6 = new CancellationSignal();
            var5 = this.findPendingOperation(var3.getFragment());
         } catch (Throwable var26) {
            var10000 = var26;
            var10001 = false;
            break label164;
         }

         if (var5 != null) {
            label158:
            try {
               var5.mergeWith(var1, var2);
               return;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label158;
            }
         } else {
            label160:
            try {
               FragmentStateManagerOperation var29 = new FragmentStateManagerOperation(var1, var2, var3, var6);
               this.mPendingOperations.add(var29);
               Runnable var28 = new Runnable(this, var29) {
                  final SpecialEffectsController this$0;
                  final FragmentStateManagerOperation val$operation;

                  {
                     this.this$0 = var1;
                     this.val$operation = var2;
                  }

                  public void run() {
                     if (this.this$0.mPendingOperations.contains(this.val$operation)) {
                        this.val$operation.getFinalState().applyState(this.val$operation.getFragment().mView);
                     }

                  }
               };
               var29.addCompletionListener(var28);
               var28 = new Runnable(this, var29) {
                  final SpecialEffectsController this$0;
                  final FragmentStateManagerOperation val$operation;

                  {
                     this.this$0 = var1;
                     this.val$operation = var2;
                  }

                  public void run() {
                     this.this$0.mPendingOperations.remove(this.val$operation);
                     this.this$0.mRunningOperations.remove(this.val$operation);
                  }
               };
               var29.addCompletionListener(var28);
               return;
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break label160;
            }
         }
      }

      while(true) {
         Throwable var27 = var10000;

         try {
            throw var27;
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            continue;
         }
      }
   }

   private Operation findPendingOperation(Fragment var1) {
      Iterator var2 = this.mPendingOperations.iterator();

      Operation var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (Operation)var2.next();
      } while(!var3.getFragment().equals(var1) || var3.isCanceled());

      return var3;
   }

   private Operation findRunningOperation(Fragment var1) {
      Iterator var2 = this.mRunningOperations.iterator();

      Operation var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (Operation)var2.next();
      } while(!var3.getFragment().equals(var1) || var3.isCanceled());

      return var3;
   }

   static SpecialEffectsController getOrCreateController(ViewGroup var0, FragmentManager var1) {
      return getOrCreateController(var0, var1.getSpecialEffectsControllerFactory());
   }

   static SpecialEffectsController getOrCreateController(ViewGroup var0, SpecialEffectsControllerFactory var1) {
      Object var2 = var0.getTag(R.id.special_effects_controller_view_tag);
      if (var2 instanceof SpecialEffectsController) {
         return (SpecialEffectsController)var2;
      } else {
         SpecialEffectsController var3 = var1.createController(var0);
         var0.setTag(R.id.special_effects_controller_view_tag, var3);
         return var3;
      }
   }

   private void updateFinalState() {
      Iterator var2 = this.mPendingOperations.iterator();

      while(var2.hasNext()) {
         Operation var1 = (Operation)var2.next();
         if (var1.getLifecycleImpact() == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            var1.mergeWith(SpecialEffectsController.Operation.State.from(var1.getFragment().requireView().getVisibility()), SpecialEffectsController.Operation.LifecycleImpact.NONE);
         }
      }

   }

   void enqueueAdd(Operation.State var1, FragmentStateManager var2) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + var2.getFragment());
      }

      this.enqueue(var1, SpecialEffectsController.Operation.LifecycleImpact.ADDING, var2);
   }

   void enqueueHide(FragmentStateManager var1) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + var1.getFragment());
      }

      this.enqueue(SpecialEffectsController.Operation.State.GONE, SpecialEffectsController.Operation.LifecycleImpact.NONE, var1);
   }

   void enqueueRemove(FragmentStateManager var1) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + var1.getFragment());
      }

      this.enqueue(SpecialEffectsController.Operation.State.REMOVED, SpecialEffectsController.Operation.LifecycleImpact.REMOVING, var1);
   }

   void enqueueShow(FragmentStateManager var1) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + var1.getFragment());
      }

      this.enqueue(SpecialEffectsController.Operation.State.VISIBLE, SpecialEffectsController.Operation.LifecycleImpact.NONE, var1);
   }

   abstract void executeOperations(List var1, boolean var2);

   void executePendingOperations() {
      if (!this.mIsContainerPostponed) {
         if (!ViewCompat.isAttachedToWindow(this.mContainer)) {
            this.forceCompleteAllOperations();
            this.mOperationDirectionIsPop = false;
         } else {
            ArrayList var1 = this.mPendingOperations;
            synchronized(var1){}

            Throwable var10000;
            boolean var10001;
            label731: {
               label738: {
                  ArrayList var2;
                  Iterator var77;
                  try {
                     if (this.mPendingOperations.isEmpty()) {
                        break label738;
                     }

                     var2 = new ArrayList(this.mRunningOperations);
                     this.mRunningOperations.clear();
                     var77 = var2.iterator();
                  } catch (Throwable var73) {
                     var10000 = var73;
                     var10001 = false;
                     break label731;
                  }

                  while(true) {
                     Operation var4;
                     try {
                        if (!var77.hasNext()) {
                           break;
                        }

                        var4 = (Operation)var77.next();
                        if (FragmentManager.isLoggingEnabled(2)) {
                           StringBuilder var3 = new StringBuilder();
                           Log.v("FragmentManager", var3.append("SpecialEffectsController: Cancelling operation ").append(var4).toString());
                        }
                     } catch (Throwable var76) {
                        var10000 = var76;
                        var10001 = false;
                        break label731;
                     }

                     try {
                        var4.cancel();
                        if (!var4.isComplete()) {
                           this.mRunningOperations.add(var4);
                        }
                     } catch (Throwable var75) {
                        var10000 = var75;
                        var10001 = false;
                        break label731;
                     }
                  }

                  Iterator var79;
                  try {
                     this.updateFinalState();
                     var2 = new ArrayList(this.mPendingOperations);
                     this.mPendingOperations.clear();
                     this.mRunningOperations.addAll(var2);
                     var79 = var2.iterator();
                  } catch (Throwable var72) {
                     var10000 = var72;
                     var10001 = false;
                     break label731;
                  }

                  while(true) {
                     try {
                        if (var79.hasNext()) {
                           ((Operation)var79.next()).onStart();
                           continue;
                        }
                     } catch (Throwable var74) {
                        var10000 = var74;
                        var10001 = false;
                        break label731;
                     }

                     try {
                        this.executeOperations(var2, this.mOperationDirectionIsPop);
                        this.mOperationDirectionIsPop = false;
                        break;
                     } catch (Throwable var71) {
                        var10000 = var71;
                        var10001 = false;
                        break label731;
                     }
                  }
               }

               label701:
               try {
                  return;
               } catch (Throwable var70) {
                  var10000 = var70;
                  var10001 = false;
                  break label701;
               }
            }

            while(true) {
               Throwable var78 = var10000;

               try {
                  throw var78;
               } catch (Throwable var69) {
                  var10000 = var69;
                  var10001 = false;
                  continue;
               }
            }
         }
      }
   }

   void forceCompleteAllOperations() {
      boolean var1 = ViewCompat.isAttachedToWindow(this.mContainer);
      ArrayList var3 = this.mPendingOperations;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label1745: {
         Iterator var2;
         try {
            this.updateFinalState();
            var2 = this.mPendingOperations.iterator();
         } catch (Throwable var215) {
            var10000 = var215;
            var10001 = false;
            break label1745;
         }

         while(true) {
            try {
               if (!var2.hasNext()) {
                  break;
               }

               ((Operation)var2.next()).onStart();
            } catch (Throwable var216) {
               var10000 = var216;
               var10001 = false;
               break label1745;
            }
         }

         Iterator var5;
         ArrayList var217;
         try {
            var217 = new ArrayList(this.mRunningOperations);
            var5 = var217.iterator();
         } catch (Throwable var213) {
            var10000 = var213;
            var10001 = false;
            break label1745;
         }

         Operation var4;
         StringBuilder var6;
         StringBuilder var218;
         String var219;
         while(true) {
            label1747: {
               try {
                  if (!var5.hasNext()) {
                     break;
                  }

                  var4 = (Operation)var5.next();
                  if (!FragmentManager.isLoggingEnabled(2)) {
                     break label1747;
                  }

                  var218 = new StringBuilder();
                  var6 = var218.append("SpecialEffectsController: ");
               } catch (Throwable var214) {
                  var10000 = var214;
                  var10001 = false;
                  break label1745;
               }

               if (var1) {
                  var219 = "";
               } else {
                  try {
                     var218 = new StringBuilder();
                     var219 = var218.append("Container ").append(this.mContainer).append(" is not attached to window. ").toString();
                  } catch (Throwable var212) {
                     var10000 = var212;
                     var10001 = false;
                     break label1745;
                  }
               }

               try {
                  Log.v("FragmentManager", var6.append(var219).append("Cancelling running operation ").append(var4).toString());
               } catch (Throwable var211) {
                  var10000 = var211;
                  var10001 = false;
                  break label1745;
               }
            }

            try {
               var4.cancel();
            } catch (Throwable var210) {
               var10000 = var210;
               var10001 = false;
               break label1745;
            }
         }

         try {
            var217 = new ArrayList(this.mPendingOperations);
            var5 = var217.iterator();
         } catch (Throwable var208) {
            var10000 = var208;
            var10001 = false;
            break label1745;
         }

         while(true) {
            label1750: {
               try {
                  if (!var5.hasNext()) {
                     break;
                  }

                  var4 = (Operation)var5.next();
                  if (!FragmentManager.isLoggingEnabled(2)) {
                     break label1750;
                  }

                  var218 = new StringBuilder();
                  var6 = var218.append("SpecialEffectsController: ");
               } catch (Throwable var209) {
                  var10000 = var209;
                  var10001 = false;
                  break label1745;
               }

               if (var1) {
                  var219 = "";
               } else {
                  try {
                     var218 = new StringBuilder();
                     var219 = var218.append("Container ").append(this.mContainer).append(" is not attached to window. ").toString();
                  } catch (Throwable var207) {
                     var10000 = var207;
                     var10001 = false;
                     break label1745;
                  }
               }

               try {
                  Log.v("FragmentManager", var6.append(var219).append("Cancelling pending operation ").append(var4).toString());
               } catch (Throwable var206) {
                  var10000 = var206;
                  var10001 = false;
                  break label1745;
               }
            }

            try {
               var4.cancel();
            } catch (Throwable var205) {
               var10000 = var205;
               var10001 = false;
               break label1745;
            }
         }

         label1690:
         try {
            return;
         } catch (Throwable var204) {
            var10000 = var204;
            var10001 = false;
            break label1690;
         }
      }

      while(true) {
         Throwable var220 = var10000;

         try {
            throw var220;
         } catch (Throwable var203) {
            var10000 = var203;
            var10001 = false;
            continue;
         }
      }
   }

   void forcePostponedExecutePendingOperations() {
      if (this.mIsContainerPostponed) {
         this.mIsContainerPostponed = false;
         this.executePendingOperations();
      }

   }

   Operation.LifecycleImpact getAwaitingCompletionLifecycleImpact(FragmentStateManager var1) {
      Operation var2 = this.findPendingOperation(var1.getFragment());
      Operation.LifecycleImpact var4;
      if (var2 != null) {
         var4 = var2.getLifecycleImpact();
      } else {
         var4 = null;
      }

      Operation var3 = this.findRunningOperation(var1.getFragment());
      return var3 == null || var4 != null && var4 != SpecialEffectsController.Operation.LifecycleImpact.NONE ? var4 : var3.getLifecycleImpact();
   }

   public ViewGroup getContainer() {
      return this.mContainer;
   }

   void markPostponedState() {
      ArrayList var2 = this.mPendingOperations;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label248: {
         int var1;
         try {
            this.updateFinalState();
            this.mIsContainerPostponed = false;
            var1 = this.mPendingOperations.size() - 1;
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label248;
         }

         while(true) {
            label245: {
               if (var1 >= 0) {
                  try {
                     Operation var3 = (Operation)this.mPendingOperations.get(var1);
                     Operation.State var4 = SpecialEffectsController.Operation.State.from(var3.getFragment().mView);
                     if (var3.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE || var4 == SpecialEffectsController.Operation.State.VISIBLE) {
                        break label245;
                     }

                     this.mIsContainerPostponed = var3.getFragment().isPostponed();
                  } catch (Throwable var24) {
                     var10000 = var24;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  return;
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break;
               }
            }

            --var1;
         }
      }

      while(true) {
         Throwable var25 = var10000;

         try {
            throw var25;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            continue;
         }
      }
   }

   void updateOperationDirection(boolean var1) {
      this.mOperationDirectionIsPop = var1;
   }

   private static class FragmentStateManagerOperation extends Operation {
      private final FragmentStateManager mFragmentStateManager;

      FragmentStateManagerOperation(Operation.State var1, Operation.LifecycleImpact var2, FragmentStateManager var3, CancellationSignal var4) {
         super(var1, var2, var3.getFragment(), var4);
         this.mFragmentStateManager = var3;
      }

      public void complete() {
         super.complete();
         this.mFragmentStateManager.moveToExpectedState();
      }

      void onStart() {
         if (this.getLifecycleImpact() == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            Fragment var1 = this.mFragmentStateManager.getFragment();
            View var2 = var1.mView.findFocus();
            if (var2 != null) {
               var1.setFocusedView(var2);
               if (FragmentManager.isLoggingEnabled(2)) {
                  Log.v("FragmentManager", "requestFocus: Saved focused view " + var2 + " for Fragment " + var1);
               }
            }

            var2 = this.getFragment().requireView();
            if (var2.getParent() == null) {
               this.mFragmentStateManager.addViewToContainer();
               var2.setAlpha(0.0F);
            }

            if (var2.getAlpha() == 0.0F && var2.getVisibility() == 0) {
               var2.setVisibility(4);
            }

            var2.setAlpha(var1.getPostOnViewCreatedAlpha());
         }

      }
   }

   static class Operation {
      private final List mCompletionListeners = new ArrayList();
      private State mFinalState;
      private final Fragment mFragment;
      private boolean mIsCanceled = false;
      private boolean mIsComplete = false;
      private LifecycleImpact mLifecycleImpact;
      private final HashSet mSpecialEffectsSignals = new HashSet();

      Operation(State var1, LifecycleImpact var2, Fragment var3, CancellationSignal var4) {
         this.mFinalState = var1;
         this.mLifecycleImpact = var2;
         this.mFragment = var3;
         var4.setOnCancelListener(new CancellationSignal.OnCancelListener(this) {
            final Operation this$0;

            {
               this.this$0 = var1;
            }

            public void onCancel() {
               this.this$0.cancel();
            }
         });
      }

      final void addCompletionListener(Runnable var1) {
         this.mCompletionListeners.add(var1);
      }

      final void cancel() {
         if (!this.isCanceled()) {
            this.mIsCanceled = true;
            if (this.mSpecialEffectsSignals.isEmpty()) {
               this.complete();
            } else {
               Iterator var1 = (new ArrayList(this.mSpecialEffectsSignals)).iterator();

               while(var1.hasNext()) {
                  ((CancellationSignal)var1.next()).cancel();
               }
            }

         }
      }

      public void complete() {
         if (!this.mIsComplete) {
            if (FragmentManager.isLoggingEnabled(2)) {
               Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }

            this.mIsComplete = true;
            Iterator var1 = this.mCompletionListeners.iterator();

            while(var1.hasNext()) {
               ((Runnable)var1.next()).run();
            }

         }
      }

      public final void completeSpecialEffect(CancellationSignal var1) {
         if (this.mSpecialEffectsSignals.remove(var1) && this.mSpecialEffectsSignals.isEmpty()) {
            this.complete();
         }

      }

      public State getFinalState() {
         return this.mFinalState;
      }

      public final Fragment getFragment() {
         return this.mFragment;
      }

      LifecycleImpact getLifecycleImpact() {
         return this.mLifecycleImpact;
      }

      final boolean isCanceled() {
         return this.mIsCanceled;
      }

      final boolean isComplete() {
         return this.mIsComplete;
      }

      public final void markStartedSpecialEffect(CancellationSignal var1) {
         this.onStart();
         this.mSpecialEffectsSignals.add(var1);
      }

      final void mergeWith(State var1, LifecycleImpact var2) {
         int var3 = null.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[var2.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 == 3 && this.mFinalState != SpecialEffectsController.Operation.State.REMOVED) {
                  if (FragmentManager.isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> " + var1 + ". ");
                  }

                  this.mFinalState = var1;
               }
            } else {
               if (FragmentManager.isLoggingEnabled(2)) {
                  Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> REMOVED. mLifecycleImpact  = " + this.mLifecycleImpact + " to REMOVING.");
               }

               this.mFinalState = SpecialEffectsController.Operation.State.REMOVED;
               this.mLifecycleImpact = SpecialEffectsController.Operation.LifecycleImpact.REMOVING;
            }
         } else if (this.mFinalState == SpecialEffectsController.Operation.State.REMOVED) {
            if (FragmentManager.isLoggingEnabled(2)) {
               Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.mLifecycleImpact + " to ADDING.");
            }

            this.mFinalState = SpecialEffectsController.Operation.State.VISIBLE;
            this.mLifecycleImpact = SpecialEffectsController.Operation.LifecycleImpact.ADDING;
         }

      }

      void onStart() {
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("Operation ");
         var1.append("{");
         var1.append(Integer.toHexString(System.identityHashCode(this)));
         var1.append("} ");
         var1.append("{");
         var1.append("mFinalState = ");
         var1.append(this.mFinalState);
         var1.append("} ");
         var1.append("{");
         var1.append("mLifecycleImpact = ");
         var1.append(this.mLifecycleImpact);
         var1.append("} ");
         var1.append("{");
         var1.append("mFragment = ");
         var1.append(this.mFragment);
         var1.append("}");
         return var1.toString();
      }

      static enum LifecycleImpact {
         private static final LifecycleImpact[] $VALUES;
         ADDING,
         NONE,
         REMOVING;

         static {
            LifecycleImpact var1 = new LifecycleImpact("NONE", 0);
            NONE = var1;
            LifecycleImpact var2 = new LifecycleImpact("ADDING", 1);
            ADDING = var2;
            LifecycleImpact var0 = new LifecycleImpact("REMOVING", 2);
            REMOVING = var0;
            $VALUES = new LifecycleImpact[]{var1, var2, var0};
         }
      }

      static enum State {
         private static final State[] $VALUES;
         GONE,
         INVISIBLE,
         REMOVED,
         VISIBLE;

         static {
            State var3 = new State("REMOVED", 0);
            REMOVED = var3;
            State var2 = new State("VISIBLE", 1);
            VISIBLE = var2;
            State var0 = new State("GONE", 2);
            GONE = var0;
            State var1 = new State("INVISIBLE", 3);
            INVISIBLE = var1;
            $VALUES = new State[]{var3, var2, var0, var1};
         }

         static State from(int var0) {
            if (var0 != 0) {
               if (var0 != 4) {
                  if (var0 == 8) {
                     return GONE;
                  } else {
                     throw new IllegalArgumentException("Unknown visibility " + var0);
                  }
               } else {
                  return INVISIBLE;
               }
            } else {
               return VISIBLE;
            }
         }

         static State from(View var0) {
            return var0.getAlpha() == 0.0F && var0.getVisibility() == 0 ? INVISIBLE : from(var0.getVisibility());
         }

         void applyState(View var1) {
            int var2 = null.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[this.ordinal()];
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                           Log.v("FragmentManager", "SpecialEffectsController: Setting view " + var1 + " to INVISIBLE");
                        }

                        var1.setVisibility(4);
                     }
                  } else {
                     if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + var1 + " to GONE");
                     }

                     var1.setVisibility(8);
                  }
               } else {
                  if (FragmentManager.isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "SpecialEffectsController: Setting view " + var1 + " to VISIBLE");
                  }

                  var1.setVisibility(0);
               }
            } else {
               ViewGroup var3 = (ViewGroup)var1.getParent();
               if (var3 != null) {
                  if (FragmentManager.isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "SpecialEffectsController: Removing view " + var1 + " from container " + var3);
                  }

                  var3.removeView(var1);
               }
            }

         }
      }
   }
}
