package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.collection.ArraySet;
import androidx.core.os.CancellationSignal;
import androidx.fragment.R;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class FragmentManager implements FragmentResultOwner {
   private static boolean DEBUG;
   private static final String EXTRA_CREATED_FILLIN_INTENT = "androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE";
   public static final int POP_BACK_STACK_INCLUSIVE = 1;
   static final String TAG = "FragmentManager";
   static boolean USE_STATE_MANAGER;
   ArrayList mBackStack;
   private ArrayList mBackStackChangeListeners;
   private final AtomicInteger mBackStackIndex = new AtomicInteger();
   private FragmentContainer mContainer;
   private ArrayList mCreatedMenus;
   int mCurState = -1;
   private SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory = new SpecialEffectsControllerFactory(this) {
      final FragmentManager this$0;

      {
         this.this$0 = var1;
      }

      public SpecialEffectsController createController(ViewGroup var1) {
         return new DefaultSpecialEffectsController(var1);
      }
   };
   private boolean mDestroyed;
   private Runnable mExecCommit = new Runnable(this) {
      final FragmentManager this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.execPendingActions(true);
      }
   };
   private boolean mExecutingActions;
   private Map mExitAnimationCancellationSignals = Collections.synchronizedMap(new HashMap());
   private FragmentFactory mFragmentFactory = null;
   private final FragmentStore mFragmentStore = new FragmentStore();
   private final FragmentTransition.Callback mFragmentTransitionCallback = new FragmentTransition.Callback(this) {
      final FragmentManager this$0;

      {
         this.this$0 = var1;
      }

      public void onComplete(Fragment var1, CancellationSignal var2) {
         if (!var2.isCanceled()) {
            this.this$0.removeCancellationSignal(var1, var2);
         }

      }

      public void onStart(Fragment var1, CancellationSignal var2) {
         this.this$0.addCancellationSignal(var1, var2);
      }
   };
   private boolean mHavePendingDeferredStart;
   private FragmentHostCallback mHost;
   private FragmentFactory mHostFragmentFactory = new FragmentFactory(this) {
      final FragmentManager this$0;

      {
         this.this$0 = var1;
      }

      public Fragment instantiate(ClassLoader var1, String var2) {
         return this.this$0.getHost().instantiate(this.this$0.getHost().getContext(), var2, (Bundle)null);
      }
   };
   ArrayDeque mLaunchedFragments = new ArrayDeque();
   private final FragmentLayoutInflaterFactory mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
   private final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
   private boolean mNeedMenuInvalidate;
   private FragmentManagerViewModel mNonConfig;
   private final CopyOnWriteArrayList mOnAttachListeners = new CopyOnWriteArrayList();
   private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(this, false) {
      final FragmentManager this$0;

      {
         this.this$0 = var1;
      }

      public void handleOnBackPressed() {
         this.this$0.handleOnBackPressed();
      }
   };
   private OnBackPressedDispatcher mOnBackPressedDispatcher;
   private Fragment mParent;
   private final ArrayList mPendingActions = new ArrayList();
   private ArrayList mPostponedTransactions;
   Fragment mPrimaryNav;
   private ActivityResultLauncher mRequestPermissions;
   private final Map mResultListeners = Collections.synchronizedMap(new HashMap());
   private final Map mResults = Collections.synchronizedMap(new HashMap());
   private SpecialEffectsControllerFactory mSpecialEffectsControllerFactory = null;
   private ActivityResultLauncher mStartActivityForResult;
   private ActivityResultLauncher mStartIntentSenderForResult;
   private boolean mStateSaved;
   private boolean mStopped;
   private ArrayList mTmpAddedFragments;
   private ArrayList mTmpIsPop;
   private ArrayList mTmpRecords;

   private void addAddedFragments(ArraySet var1) {
      int var2 = this.mCurState;
      if (var2 >= 1) {
         var2 = Math.min(var2, 5);
         Iterator var3 = this.mFragmentStore.getFragments().iterator();

         while(var3.hasNext()) {
            Fragment var4 = (Fragment)var3.next();
            if (var4.mState < var2) {
               this.moveToState(var4, var2);
               if (var4.mView != null && !var4.mHidden && var4.mIsNewlyAdded) {
                  var1.add(var4);
               }
            }
         }

      }
   }

   private void cancelExitAnimation(Fragment var1) {
      HashSet var2 = (HashSet)this.mExitAnimationCancellationSignals.get(var1);
      if (var2 != null) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            ((CancellationSignal)var3.next()).cancel();
         }

         var2.clear();
         this.destroyFragmentView(var1);
         this.mExitAnimationCancellationSignals.remove(var1);
      }

   }

   private void checkStateLoss() {
      if (this.isStateSaved()) {
         throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
      }
   }

   private void cleanupExec() {
      this.mExecutingActions = false;
      this.mTmpIsPop.clear();
      this.mTmpRecords.clear();
   }

   private Set collectAllSpecialEffectsController() {
      HashSet var2 = new HashSet();
      Iterator var1 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();

      while(var1.hasNext()) {
         ViewGroup var3 = ((FragmentStateManager)var1.next()).getFragment().mContainer;
         if (var3 != null) {
            var2.add(SpecialEffectsController.getOrCreateController(var3, this.getSpecialEffectsControllerFactory()));
         }
      }

      return var2;
   }

   private Set collectChangedControllers(ArrayList var1, int var2, int var3) {
      HashSet var4;
      for(var4 = new HashSet(); var2 < var3; ++var2) {
         Iterator var5 = ((BackStackRecord)var1.get(var2)).mOps.iterator();

         while(var5.hasNext()) {
            Fragment var6 = ((FragmentTransaction.Op)var5.next()).mFragment;
            if (var6 != null) {
               ViewGroup var7 = var6.mContainer;
               if (var7 != null) {
                  var4.add(SpecialEffectsController.getOrCreateController(var7, this));
               }
            }
         }
      }

      return var4;
   }

   private void completeShowHideFragment(Fragment var1) {
      if (var1.mView != null) {
         FragmentAnim.AnimationOrAnimator var4 = FragmentAnim.loadAnimation(this.mHost.getContext(), var1, var1.mHidden ^ true, var1.getPopDirection());
         if (var4 != null && var4.animator != null) {
            var4.animator.setTarget(var1.mView);
            if (var1.mHidden) {
               if (var1.isHideReplaced()) {
                  var1.setHideReplaced(false);
               } else {
                  ViewGroup var5 = var1.mContainer;
                  View var3 = var1.mView;
                  var5.startViewTransition(var3);
                  var4.animator.addListener(new AnimatorListenerAdapter(this, var5, var3, var1) {
                     final FragmentManager this$0;
                     final View val$animatingView;
                     final ViewGroup val$container;
                     final Fragment val$fragment;

                     {
                        this.this$0 = var1;
                        this.val$container = var2;
                        this.val$animatingView = var3;
                        this.val$fragment = var4;
                     }

                     public void onAnimationEnd(Animator var1) {
                        this.val$container.endViewTransition(this.val$animatingView);
                        var1.removeListener(this);
                        if (this.val$fragment.mView != null && this.val$fragment.mHidden) {
                           this.val$fragment.mView.setVisibility(8);
                        }

                     }
                  });
               }
            } else {
               var1.mView.setVisibility(0);
            }

            var4.animator.start();
         } else {
            if (var4 != null) {
               var1.mView.startAnimation(var4.animation);
               var4.animation.start();
            }

            byte var2;
            if (var1.mHidden && !var1.isHideReplaced()) {
               var2 = 8;
            } else {
               var2 = 0;
            }

            var1.mView.setVisibility(var2);
            if (var1.isHideReplaced()) {
               var1.setHideReplaced(false);
            }
         }
      }

      this.invalidateMenuForFragment(var1);
      var1.mHiddenChanged = false;
      var1.onHiddenChanged(var1.mHidden);
   }

   private void destroyFragmentView(Fragment var1) {
      var1.performDestroyView();
      this.mLifecycleCallbacksDispatcher.dispatchOnFragmentViewDestroyed(var1, false);
      var1.mContainer = null;
      var1.mView = null;
      var1.mViewLifecycleOwner = null;
      var1.mViewLifecycleOwnerLiveData.setValue((Object)null);
      var1.mInLayout = false;
   }

   private void dispatchParentPrimaryNavigationFragmentChanged(Fragment var1) {
      if (var1 != null && var1.equals(this.findActiveFragment(var1.mWho))) {
         var1.performPrimaryNavigationFragmentChanged();
      }

   }

   private void dispatchStateChange(int param1) {
      // $FF: Couldn't be decompiled
   }

   private void doPendingDeferredStart() {
      if (this.mHavePendingDeferredStart) {
         this.mHavePendingDeferredStart = false;
         this.startPendingDeferredFragments();
      }

   }

   @Deprecated
   public static void enableDebugLogging(boolean var0) {
      DEBUG = var0;
   }

   public static void enableNewStateManager(boolean var0) {
      USE_STATE_MANAGER = var0;
   }

   private void endAnimatingAwayFragments() {
      if (USE_STATE_MANAGER) {
         Iterator var1 = this.collectAllSpecialEffectsController().iterator();

         while(var1.hasNext()) {
            ((SpecialEffectsController)var1.next()).forceCompleteAllOperations();
         }
      } else if (!this.mExitAnimationCancellationSignals.isEmpty()) {
         Iterator var2 = this.mExitAnimationCancellationSignals.keySet().iterator();

         while(var2.hasNext()) {
            Fragment var3 = (Fragment)var2.next();
            this.cancelExitAnimation(var3);
            this.moveToState(var3);
         }
      }

   }

   private void ensureExecReady(boolean var1) {
      if (!this.mExecutingActions) {
         if (this.mHost == null) {
            if (this.mDestroyed) {
               throw new IllegalStateException("FragmentManager has been destroyed");
            } else {
               throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
         } else if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
            if (!var1) {
               this.checkStateLoss();
            }

            if (this.mTmpRecords == null) {
               this.mTmpRecords = new ArrayList();
               this.mTmpIsPop = new ArrayList();
            }

            this.mExecutingActions = true;

            try {
               this.executePostponedTransaction((ArrayList)null, (ArrayList)null);
            } finally {
               this.mExecutingActions = false;
            }

         } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
         }
      } else {
         throw new IllegalStateException("FragmentManager is already executing transactions");
      }
   }

   private static void executeOps(ArrayList var0, ArrayList var1, int var2, int var3) {
      for(; var2 < var3; ++var2) {
         BackStackRecord var6 = (BackStackRecord)var0.get(var2);
         boolean var5 = (Boolean)var1.get(var2);
         boolean var4 = true;
         if (var5) {
            var6.bumpBackStackNesting(-1);
            if (var2 != var3 - 1) {
               var4 = false;
            }

            var6.executePopOps(var4);
         } else {
            var6.bumpBackStackNesting(1);
            var6.executeOps();
         }
      }

   }

   private void executeOpsTogether(ArrayList var1, ArrayList var2, int var3, int var4) {
      boolean var8 = ((BackStackRecord)var1.get(var3)).mReorderingAllowed;
      ArrayList var9 = this.mTmpAddedFragments;
      if (var9 == null) {
         this.mTmpAddedFragments = new ArrayList();
      } else {
         var9.clear();
      }

      this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
      Fragment var11 = this.getPrimaryNavigationFragment();
      boolean var5 = false;

      int var6;
      BackStackRecord var10;
      for(var6 = var3; var6 < var4; ++var6) {
         var10 = (BackStackRecord)var1.get(var6);
         if (!(Boolean)var2.get(var6)) {
            var11 = var10.expandOps(this.mTmpAddedFragments, var11);
         } else {
            var11 = var10.trackAddedFragmentsInPop(this.mTmpAddedFragments, var11);
         }

         if (!var5 && !var10.mAddToBackStack) {
            var5 = false;
         } else {
            var5 = true;
         }
      }

      this.mTmpAddedFragments.clear();
      Iterator var12;
      Fragment var13;
      if (!var8 && this.mCurState >= 1) {
         if (USE_STATE_MANAGER) {
            for(var6 = var3; var6 < var4; ++var6) {
               var12 = ((BackStackRecord)var1.get(var6)).mOps.iterator();

               while(var12.hasNext()) {
                  var13 = ((FragmentTransaction.Op)var12.next()).mFragment;
                  if (var13 != null && var13.mFragmentManager != null) {
                     FragmentStateManager var14 = this.createOrGetFragmentStateManager(var13);
                     this.mFragmentStore.makeActive(var14);
                  }
               }
            }
         } else {
            FragmentTransition.startTransitions(this.mHost.getContext(), this.mContainer, var1, var2, var3, var4, false, this.mFragmentTransitionCallback);
         }
      }

      executeOps(var1, var2, var3, var4);
      if (USE_STATE_MANAGER) {
         var8 = (Boolean)var2.get(var4 - 1);

         for(var6 = var3; var6 < var4; ++var6) {
            var10 = (BackStackRecord)var1.get(var6);
            if (var8) {
               for(int var7 = var10.mOps.size() - 1; var7 >= 0; --var7) {
                  var11 = ((FragmentTransaction.Op)var10.mOps.get(var7)).mFragment;
                  if (var11 != null) {
                     this.createOrGetFragmentStateManager(var11).moveToExpectedState();
                  }
               }
            } else {
               var12 = var10.mOps.iterator();

               while(var12.hasNext()) {
                  var13 = ((FragmentTransaction.Op)var12.next()).mFragment;
                  if (var13 != null) {
                     this.createOrGetFragmentStateManager(var13).moveToExpectedState();
                  }
               }
            }
         }

         this.moveToState(this.mCurState, true);
         var12 = this.collectChangedControllers(var1, var3, var4).iterator();

         while(var12.hasNext()) {
            SpecialEffectsController var16 = (SpecialEffectsController)var12.next();
            var16.updateOperationDirection(var8);
            var16.markPostponedState();
            var16.executePendingOperations();
         }
      } else {
         if (var8) {
            ArraySet var15 = new ArraySet();
            this.addAddedFragments(var15);
            var6 = this.postponePostponableTransactions(var1, var2, var3, var4, var15);
            this.makeRemovedFragmentsInvisible(var15);
         } else {
            var6 = var4;
         }

         if (var6 != var3 && var8) {
            if (this.mCurState >= 1) {
               FragmentTransition.startTransitions(this.mHost.getContext(), this.mContainer, var1, var2, var3, var6, true, this.mFragmentTransitionCallback);
            }

            this.moveToState(this.mCurState, true);
         }
      }

      while(var3 < var4) {
         BackStackRecord var17 = (BackStackRecord)var1.get(var3);
         if ((Boolean)var2.get(var3) && var17.mIndex >= 0) {
            var17.mIndex = -1;
         }

         var17.runOnCommitRunnables();
         ++var3;
      }

      if (var5) {
         this.reportBackStackChanged();
      }

   }

   private void executePostponedTransaction(ArrayList var1, ArrayList var2) {
      ArrayList var7 = this.mPostponedTransactions;
      int var3;
      if (var7 == null) {
         var3 = 0;
      } else {
         var3 = var7.size();
      }

      int var4 = 0;

      for(int var6 = var3; var4 < var6; var6 = var3) {
         int var5;
         label54: {
            StartEnterTransitionListener var8 = (StartEnterTransitionListener)this.mPostponedTransactions.get(var4);
            if (var1 != null && !var8.mIsBack) {
               var3 = var1.indexOf(var8.mRecord);
               if (var3 != -1 && var2 != null && (Boolean)var2.get(var3)) {
                  this.mPostponedTransactions.remove(var4);
                  var5 = var4 - 1;
                  var3 = var6 - 1;
                  var8.cancelTransaction();
                  break label54;
               }
            }

            if (!var8.isReady()) {
               var3 = var6;
               var5 = var4;
               if (var1 == null) {
                  break label54;
               }

               var3 = var6;
               var5 = var4;
               if (!var8.mRecord.interactsWith(var1, 0, var1.size())) {
                  break label54;
               }
            }

            this.mPostponedTransactions.remove(var4);
            var5 = var4 - 1;
            var3 = var6 - 1;
            if (var1 != null && !var8.mIsBack) {
               var4 = var1.indexOf(var8.mRecord);
               if (var4 != -1 && var2 != null && (Boolean)var2.get(var4)) {
                  var8.cancelTransaction();
                  break label54;
               }
            }

            var8.completeTransaction();
         }

         var4 = var5 + 1;
      }

   }

   public static Fragment findFragment(View var0) {
      Fragment var1 = findViewFragment(var0);
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("View " + var0 + " does not have a Fragment set");
      }
   }

   static FragmentManager findFragmentManager(View var0) {
      Fragment var1 = findViewFragment(var0);
      FragmentManager var4;
      if (var1 != null) {
         if (!var1.isAdded()) {
            throw new IllegalStateException("The Fragment " + var1 + " that owns View " + var0 + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
         }

         var4 = var1.getChildFragmentManager();
      } else {
         Context var2 = var0.getContext();
         Object var3 = null;

         FragmentActivity var5;
         while(true) {
            var5 = (FragmentActivity)var3;
            if (!(var2 instanceof ContextWrapper)) {
               break;
            }

            if (var2 instanceof FragmentActivity) {
               var5 = (FragmentActivity)var2;
               break;
            }

            var2 = ((ContextWrapper)var2).getBaseContext();
         }

         if (var5 == null) {
            throw new IllegalStateException("View " + var0 + " is not within a subclass of FragmentActivity.");
         }

         var4 = var5.getSupportFragmentManager();
      }

      return var4;
   }

   private static Fragment findViewFragment(View var0) {
      while(var0 != null) {
         Fragment var1 = getViewFragment(var0);
         if (var1 != null) {
            return var1;
         }

         ViewParent var2 = var0.getParent();
         if (var2 instanceof View) {
            var0 = (View)var2;
         } else {
            var0 = null;
         }
      }

      return null;
   }

   private void forcePostponedTransactions() {
      if (USE_STATE_MANAGER) {
         Iterator var1 = this.collectAllSpecialEffectsController().iterator();

         while(var1.hasNext()) {
            ((SpecialEffectsController)var1.next()).forcePostponedExecutePendingOperations();
         }
      } else if (this.mPostponedTransactions != null) {
         while(!this.mPostponedTransactions.isEmpty()) {
            ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();
         }
      }

   }

   private boolean generateOpsForPendingActions(ArrayList var1, ArrayList var2) {
      ArrayList var6 = this.mPendingActions;
      synchronized(var6){}

      Throwable var10000;
      boolean var10001;
      label375: {
         boolean var5;
         try {
            var5 = this.mPendingActions.isEmpty();
         } catch (Throwable var48) {
            var10000 = var48;
            var10001 = false;
            break label375;
         }

         int var3 = 0;
         if (var5) {
            label357:
            try {
               return false;
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label357;
            }
         } else {
            label379: {
               int var4;
               try {
                  var4 = this.mPendingActions.size();
               } catch (Throwable var47) {
                  var10000 = var47;
                  var10001 = false;
                  break label379;
               }

               for(var5 = false; var3 < var4; ++var3) {
                  try {
                     var5 |= ((OpGenerator)this.mPendingActions.get(var3)).generateOps(var1, var2);
                  } catch (Throwable var46) {
                     var10000 = var46;
                     var10001 = false;
                     break label379;
                  }
               }

               label359:
               try {
                  this.mPendingActions.clear();
                  this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                  return var5;
               } catch (Throwable var45) {
                  var10000 = var45;
                  var10001 = false;
                  break label359;
               }
            }
         }
      }

      while(true) {
         Throwable var49 = var10000;

         try {
            throw var49;
         } catch (Throwable var43) {
            var10000 = var43;
            var10001 = false;
            continue;
         }
      }
   }

   private FragmentManagerViewModel getChildNonConfig(Fragment var1) {
      return this.mNonConfig.getChildNonConfig(var1);
   }

   private ViewGroup getFragmentContainer(Fragment var1) {
      if (var1.mContainer != null) {
         return var1.mContainer;
      } else if (var1.mContainerId <= 0) {
         return null;
      } else {
         if (this.mContainer.onHasView()) {
            View var2 = this.mContainer.onFindViewById(var1.mContainerId);
            if (var2 instanceof ViewGroup) {
               return (ViewGroup)var2;
            }
         }

         return null;
      }
   }

   static Fragment getViewFragment(View var0) {
      Object var1 = var0.getTag(R.id.fragment_container_view_tag);
      return var1 instanceof Fragment ? (Fragment)var1 : null;
   }

   static boolean isLoggingEnabled(int var0) {
      boolean var1;
      if (!DEBUG && !Log.isLoggable("FragmentManager", var0)) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private boolean isMenuAvailable(Fragment var1) {
      boolean var2;
      if ((!var1.mHasMenu || !var1.mMenuVisible) && !var1.mChildFragmentManager.checkForMenus()) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private void makeRemovedFragmentsInvisible(ArraySet var1) {
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         Fragment var5 = (Fragment)var1.valueAt(var2);
         if (!var5.mAdded) {
            View var4 = var5.requireView();
            var5.mPostponedAlpha = var4.getAlpha();
            var4.setAlpha(0.0F);
         }
      }

   }

   private boolean popBackStackImmediate(String var1, int var2, int var3) {
      this.execPendingActions(false);
      this.ensureExecReady(true);
      Fragment var5 = this.mPrimaryNav;
      if (var5 != null && var2 < 0 && var1 == null && var5.getChildFragmentManager().popBackStackImmediate()) {
         return true;
      } else {
         boolean var4 = this.popBackStackState(this.mTmpRecords, this.mTmpIsPop, var1, var2, var3);
         if (var4) {
            this.mExecutingActions = true;

            try {
               this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
               this.cleanupExec();
            }
         }

         this.updateOnBackPressedCallbackEnabled();
         this.doPendingDeferredStart();
         this.mFragmentStore.burpActive();
         return var4;
      }
   }

   private int postponePostponableTransactions(ArrayList var1, ArrayList var2, int var3, int var4, ArraySet var5) {
      int var7 = var4 - 1;

      int var6;
      int var8;
      for(var6 = var4; var7 >= var3; var6 = var8) {
         BackStackRecord var11 = (BackStackRecord)var1.get(var7);
         boolean var10 = (Boolean)var2.get(var7);
         boolean var9;
         if (var11.isPostponed() && !var11.interactsWith(var1, var7 + 1, var4)) {
            var9 = true;
         } else {
            var9 = false;
         }

         var8 = var6;
         if (var9) {
            if (this.mPostponedTransactions == null) {
               this.mPostponedTransactions = new ArrayList();
            }

            StartEnterTransitionListener var12 = new StartEnterTransitionListener(var11, var10);
            this.mPostponedTransactions.add(var12);
            var11.setOnStartPostponedListener(var12);
            if (var10) {
               var11.executeOps();
            } else {
               var11.executePopOps(false);
            }

            var8 = var6 - 1;
            if (var7 != var8) {
               var1.remove(var7);
               var1.add(var8, var11);
            }

            this.addAddedFragments(var5);
         }

         --var7;
      }

      return var6;
   }

   private void removeRedundantOperationsAndExecute(ArrayList var1, ArrayList var2) {
      if (!var1.isEmpty()) {
         if (var1.size() != var2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
         } else {
            this.executePostponedTransaction(var1, var2);
            int var7 = var1.size();
            int var3 = 0;

            int var4;
            int var5;
            for(var5 = 0; var3 < var7; var5 = var4) {
               int var6 = var3;
               var4 = var5;
               if (!((BackStackRecord)var1.get(var3)).mReorderingAllowed) {
                  if (var5 != var3) {
                     this.executeOpsTogether(var1, var2, var5, var3);
                  }

                  var5 = var3 + 1;
                  var4 = var5;
                  if ((Boolean)var2.get(var3)) {
                     while(true) {
                        var4 = var5;
                        if (var5 >= var7) {
                           break;
                        }

                        var4 = var5;
                        if (!(Boolean)var2.get(var5)) {
                           break;
                        }

                        var4 = var5;
                        if (((BackStackRecord)var1.get(var5)).mReorderingAllowed) {
                           break;
                        }

                        ++var5;
                     }
                  }

                  this.executeOpsTogether(var1, var2, var3, var4);
                  var6 = var4 - 1;
               }

               var3 = var6 + 1;
            }

            if (var5 != var7) {
               this.executeOpsTogether(var1, var2, var5, var7);
            }

         }
      }
   }

   private void reportBackStackChanged() {
      if (this.mBackStackChangeListeners != null) {
         for(int var1 = 0; var1 < this.mBackStackChangeListeners.size(); ++var1) {
            ((OnBackStackChangedListener)this.mBackStackChangeListeners.get(var1)).onBackStackChanged();
         }
      }

   }

   static int reverseTransit(int var0) {
      short var1 = 8194;
      if (var0 != 4097) {
         if (var0 != 4099) {
            if (var0 != 8194) {
               var1 = 0;
            } else {
               var1 = 4097;
            }
         } else {
            var1 = 4099;
         }
      }

      return var1;
   }

   private void setVisibleRemovingFragment(Fragment var1) {
      ViewGroup var2 = this.getFragmentContainer(var1);
      if (var2 != null && var1.getEnterAnim() + var1.getExitAnim() + var1.getPopEnterAnim() + var1.getPopExitAnim() > 0) {
         if (var2.getTag(R.id.visible_removing_fragment_view_tag) == null) {
            var2.setTag(R.id.visible_removing_fragment_view_tag, var1);
         }

         ((Fragment)var2.getTag(R.id.visible_removing_fragment_view_tag)).setPopDirection(var1.getPopDirection());
      }

   }

   private void startPendingDeferredFragments() {
      Iterator var1 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();

      while(var1.hasNext()) {
         this.performPendingDeferredStart((FragmentStateManager)var1.next());
      }

   }

   private void throwException(RuntimeException var1) {
      Log.e("FragmentManager", var1.getMessage());
      Log.e("FragmentManager", "Activity state:");
      PrintWriter var3 = new PrintWriter(new LogWriter("FragmentManager"));
      FragmentHostCallback var2 = this.mHost;
      if (var2 != null) {
         try {
            var2.onDump("  ", (FileDescriptor)null, var3, new String[0]);
         } catch (Exception var5) {
            Log.e("FragmentManager", "Failed dumping state", var5);
         }
      } else {
         try {
            this.dump("  ", (FileDescriptor)null, var3, new String[0]);
         } catch (Exception var4) {
            Log.e("FragmentManager", "Failed dumping state", var4);
         }
      }

      throw var1;
   }

   private void updateOnBackPressedCallbackEnabled() {
      ArrayList var4 = this.mPendingActions;
      synchronized(var4){}

      Throwable var10000;
      boolean var10001;
      label218: {
         boolean var2;
         try {
            var2 = this.mPendingActions.isEmpty();
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label218;
         }

         boolean var1 = true;
         if (!var2) {
            label211:
            try {
               this.mOnBackPressedCallback.setEnabled(true);
               return;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label211;
            }
         } else {
            label223: {
               try {
                  ;
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label223;
               }

               OnBackPressedCallback var25 = this.mOnBackPressedCallback;
               if (this.getBackStackEntryCount() <= 0 || !this.isPrimaryNavigation(this.mParent)) {
                  var1 = false;
               }

               var25.setEnabled(var1);
               return;
            }
         }
      }

      while(true) {
         Throwable var3 = var10000;

         try {
            throw var3;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            continue;
         }
      }
   }

   void addBackStackState(BackStackRecord var1) {
      if (this.mBackStack == null) {
         this.mBackStack = new ArrayList();
      }

      this.mBackStack.add(var1);
   }

   void addCancellationSignal(Fragment var1, CancellationSignal var2) {
      if (this.mExitAnimationCancellationSignals.get(var1) == null) {
         this.mExitAnimationCancellationSignals.put(var1, new HashSet());
      }

      ((HashSet)this.mExitAnimationCancellationSignals.get(var1)).add(var2);
   }

   FragmentStateManager addFragment(Fragment var1) {
      if (isLoggingEnabled(2)) {
         Log.v("FragmentManager", "add: " + var1);
      }

      FragmentStateManager var2 = this.createOrGetFragmentStateManager(var1);
      var1.mFragmentManager = this;
      this.mFragmentStore.makeActive(var2);
      if (!var1.mDetached) {
         this.mFragmentStore.addFragment(var1);
         var1.mRemoving = false;
         if (var1.mView == null) {
            var1.mHiddenChanged = false;
         }

         if (this.isMenuAvailable(var1)) {
            this.mNeedMenuInvalidate = true;
         }
      }

      return var2;
   }

   public void addFragmentOnAttachListener(FragmentOnAttachListener var1) {
      this.mOnAttachListeners.add(var1);
   }

   public void addOnBackStackChangedListener(OnBackStackChangedListener var1) {
      if (this.mBackStackChangeListeners == null) {
         this.mBackStackChangeListeners = new ArrayList();
      }

      this.mBackStackChangeListeners.add(var1);
   }

   void addRetainedFragment(Fragment var1) {
      this.mNonConfig.addRetainedFragment(var1);
   }

   int allocBackStackIndex() {
      return this.mBackStackIndex.getAndIncrement();
   }

   void attachController(FragmentHostCallback var1, FragmentContainer var2, Fragment var3) {
      if (this.mHost == null) {
         this.mHost = var1;
         this.mContainer = var2;
         this.mParent = var3;
         if (var3 != null) {
            this.addFragmentOnAttachListener(new FragmentOnAttachListener(this, var3) {
               final FragmentManager this$0;
               final Fragment val$parent;

               {
                  this.this$0 = var1;
                  this.val$parent = var2;
               }

               public void onAttachFragment(FragmentManager var1, Fragment var2) {
                  this.val$parent.onAttachFragment(var2);
               }
            });
         } else if (var1 instanceof FragmentOnAttachListener) {
            this.addFragmentOnAttachListener((FragmentOnAttachListener)var1);
         }

         if (this.mParent != null) {
            this.updateOnBackPressedCallbackEnabled();
         }

         if (var1 instanceof OnBackPressedDispatcherOwner) {
            Object var6 = (OnBackPressedDispatcherOwner)var1;
            OnBackPressedDispatcher var4 = ((OnBackPressedDispatcherOwner)var6).getOnBackPressedDispatcher();
            this.mOnBackPressedDispatcher = var4;
            if (var3 != null) {
               var6 = var3;
            }

            var4.addCallback((LifecycleOwner)var6, this.mOnBackPressedCallback);
         }

         if (var3 != null) {
            this.mNonConfig = var3.mFragmentManager.getChildNonConfig(var3);
         } else if (var1 instanceof ViewModelStoreOwner) {
            this.mNonConfig = FragmentManagerViewModel.getInstance(((ViewModelStoreOwner)var1).getViewModelStore());
         } else {
            this.mNonConfig = new FragmentManagerViewModel(false);
         }

         this.mNonConfig.setIsStateSaved(this.isStateSaved());
         this.mFragmentStore.setNonConfig(this.mNonConfig);
         var1 = this.mHost;
         if (var1 instanceof ActivityResultRegistryOwner) {
            ActivityResultRegistry var7 = ((ActivityResultRegistryOwner)var1).getActivityResultRegistry();
            String var5;
            if (var3 != null) {
               var5 = var3.mWho + ":";
            } else {
               var5 = "";
            }

            var5 = "FragmentManager:" + var5;
            this.mStartActivityForResult = var7.register(var5 + "StartActivityForResult", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback(this) {
               final FragmentManager this$0;

               {
                  this.this$0 = var1;
               }

               public void onActivityResult(ActivityResult var1) {
                  LaunchedFragmentInfo var4 = (LaunchedFragmentInfo)this.this$0.mLaunchedFragments.pollFirst();
                  if (var4 == null) {
                     Log.w("FragmentManager", "No Activities were started for result for " + this);
                  } else {
                     String var3 = var4.mWho;
                     int var2 = var4.mRequestCode;
                     Fragment var5 = this.this$0.mFragmentStore.findFragmentByWho(var3);
                     if (var5 == null) {
                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + var3);
                     } else {
                        var5.onActivityResult(var2, var1.getResultCode(), var1.getData());
                     }
                  }
               }
            });
            this.mStartIntentSenderForResult = var7.register(var5 + "StartIntentSenderForResult", new FragmentIntentSenderContract(), new ActivityResultCallback(this) {
               final FragmentManager this$0;

               {
                  this.this$0 = var1;
               }

               public void onActivityResult(ActivityResult var1) {
                  LaunchedFragmentInfo var4 = (LaunchedFragmentInfo)this.this$0.mLaunchedFragments.pollFirst();
                  if (var4 == null) {
                     Log.w("FragmentManager", "No IntentSenders were started for " + this);
                  } else {
                     String var3 = var4.mWho;
                     int var2 = var4.mRequestCode;
                     Fragment var5 = this.this$0.mFragmentStore.findFragmentByWho(var3);
                     if (var5 == null) {
                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + var3);
                     } else {
                        var5.onActivityResult(var2, var1.getResultCode(), var1.getData());
                     }
                  }
               }
            });
            this.mRequestPermissions = var7.register(var5 + "RequestPermissions", new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback(this) {
               final FragmentManager this$0;

               {
                  this.this$0 = var1;
               }

               public void onActivityResult(Map var1) {
                  String[] var4 = (String[])var1.keySet().toArray(new String[0]);
                  ArrayList var5 = new ArrayList(var1.values());
                  int[] var7 = new int[var5.size()];

                  int var2;
                  for(var2 = 0; var2 < var5.size(); ++var2) {
                     byte var3;
                     if ((Boolean)var5.get(var2)) {
                        var3 = 0;
                     } else {
                        var3 = -1;
                     }

                     var7[var2] = var3;
                  }

                  LaunchedFragmentInfo var6 = (LaunchedFragmentInfo)this.this$0.mLaunchedFragments.pollFirst();
                  if (var6 == null) {
                     Log.w("FragmentManager", "No permissions were requested for " + this);
                  } else {
                     String var8 = var6.mWho;
                     var2 = var6.mRequestCode;
                     Fragment var9 = this.this$0.mFragmentStore.findFragmentByWho(var8);
                     if (var9 == null) {
                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + var8);
                     } else {
                        var9.onRequestPermissionsResult(var2, var4, var7);
                     }
                  }
               }
            });
         }

      } else {
         throw new IllegalStateException("Already attached");
      }
   }

   void attachFragment(Fragment var1) {
      if (isLoggingEnabled(2)) {
         Log.v("FragmentManager", "attach: " + var1);
      }

      if (var1.mDetached) {
         var1.mDetached = false;
         if (!var1.mAdded) {
            this.mFragmentStore.addFragment(var1);
            if (isLoggingEnabled(2)) {
               Log.v("FragmentManager", "add from attach: " + var1);
            }

            if (this.isMenuAvailable(var1)) {
               this.mNeedMenuInvalidate = true;
            }
         }
      }

   }

   public FragmentTransaction beginTransaction() {
      return new BackStackRecord(this);
   }

   boolean checkForMenus() {
      Iterator var4 = this.mFragmentStore.getActiveFragments().iterator();
      boolean var2 = false;

      boolean var1;
      do {
         if (!var4.hasNext()) {
            return false;
         }

         Fragment var3 = (Fragment)var4.next();
         var1 = var2;
         if (var3 != null) {
            var1 = this.isMenuAvailable(var3);
         }

         var2 = var1;
      } while(!var1);

      return true;
   }

   public final void clearFragmentResult(String var1) {
      this.mResults.remove(var1);
   }

   public final void clearFragmentResultListener(String var1) {
      LifecycleAwareResultListener var2 = (LifecycleAwareResultListener)this.mResultListeners.remove(var1);
      if (var2 != null) {
         var2.removeObserver();
      }

   }

   void completeExecute(BackStackRecord var1, boolean var2, boolean var3, boolean var4) {
      if (var2) {
         var1.executePopOps(var4);
      } else {
         var1.executeOps();
      }

      ArrayList var6 = new ArrayList(1);
      ArrayList var5 = new ArrayList(1);
      var6.add(var1);
      var5.add(var2);
      if (var3 && this.mCurState >= 1) {
         FragmentTransition.startTransitions(this.mHost.getContext(), this.mContainer, var6, var5, 0, 1, true, this.mFragmentTransitionCallback);
      }

      if (var4) {
         this.moveToState(this.mCurState, true);
      }

      Iterator var8 = this.mFragmentStore.getActiveFragments().iterator();

      while(var8.hasNext()) {
         Fragment var7 = (Fragment)var8.next();
         if (var7 != null && var7.mView != null && var7.mIsNewlyAdded && var1.interactsWith(var7.mContainerId)) {
            if (var7.mPostponedAlpha > 0.0F) {
               var7.mView.setAlpha(var7.mPostponedAlpha);
            }

            if (var4) {
               var7.mPostponedAlpha = 0.0F;
            } else {
               var7.mPostponedAlpha = -1.0F;
               var7.mIsNewlyAdded = false;
            }
         }
      }

   }

   FragmentStateManager createOrGetFragmentStateManager(Fragment var1) {
      FragmentStateManager var2 = this.mFragmentStore.getFragmentStateManager(var1.mWho);
      if (var2 != null) {
         return var2;
      } else {
         FragmentStateManager var3 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, var1);
         var3.restoreState(this.mHost.getContext().getClassLoader());
         var3.setFragmentManagerState(this.mCurState);
         return var3;
      }
   }

   void detachFragment(Fragment var1) {
      if (isLoggingEnabled(2)) {
         Log.v("FragmentManager", "detach: " + var1);
      }

      if (!var1.mDetached) {
         var1.mDetached = true;
         if (var1.mAdded) {
            if (isLoggingEnabled(2)) {
               Log.v("FragmentManager", "remove from detach: " + var1);
            }

            this.mFragmentStore.removeFragment(var1);
            if (this.isMenuAvailable(var1)) {
               this.mNeedMenuInvalidate = true;
            }

            this.setVisibleRemovingFragment(var1);
         }
      }

   }

   void dispatchActivityCreated() {
      this.mStateSaved = false;
      this.mStopped = false;
      this.mNonConfig.setIsStateSaved(false);
      this.dispatchStateChange(4);
   }

   void dispatchAttach() {
      this.mStateSaved = false;
      this.mStopped = false;
      this.mNonConfig.setIsStateSaved(false);
      this.dispatchStateChange(0);
   }

   void dispatchConfigurationChanged(Configuration var1) {
      Iterator var2 = this.mFragmentStore.getFragments().iterator();

      while(var2.hasNext()) {
         Fragment var3 = (Fragment)var2.next();
         if (var3 != null) {
            var3.performConfigurationChanged(var1);
         }
      }

   }

   boolean dispatchContextItemSelected(MenuItem var1) {
      if (this.mCurState < 1) {
         return false;
      } else {
         Iterator var2 = this.mFragmentStore.getFragments().iterator();

         Fragment var3;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            var3 = (Fragment)var2.next();
         } while(var3 == null || !var3.performContextItemSelected(var1));

         return true;
      }
   }

   void dispatchCreate() {
      this.mStateSaved = false;
      this.mStopped = false;
      this.mNonConfig.setIsStateSaved(false);
      this.dispatchStateChange(1);
   }

   boolean dispatchCreateOptionsMenu(Menu var1, MenuInflater var2) {
      int var4 = this.mCurState;
      int var3 = 0;
      if (var4 < 1) {
         return false;
      } else {
         ArrayList var6 = null;
         Iterator var8 = this.mFragmentStore.getFragments().iterator();
         boolean var5 = false;

         while(var8.hasNext()) {
            Fragment var9 = (Fragment)var8.next();
            if (var9 != null && this.isParentMenuVisible(var9) && var9.performCreateOptionsMenu(var1, var2)) {
               ArrayList var7 = var6;
               if (var6 == null) {
                  var7 = new ArrayList();
               }

               var7.add(var9);
               var5 = true;
               var6 = var7;
            }
         }

         if (this.mCreatedMenus != null) {
            for(; var3 < this.mCreatedMenus.size(); ++var3) {
               Fragment var10 = (Fragment)this.mCreatedMenus.get(var3);
               if (var6 == null || !var6.contains(var10)) {
                  var10.onDestroyOptionsMenu();
               }
            }
         }

         this.mCreatedMenus = var6;
         return var5;
      }
   }

   void dispatchDestroy() {
      this.mDestroyed = true;
      this.execPendingActions(true);
      this.endAnimatingAwayFragments();
      this.dispatchStateChange(-1);
      this.mHost = null;
      this.mContainer = null;
      this.mParent = null;
      if (this.mOnBackPressedDispatcher != null) {
         this.mOnBackPressedCallback.remove();
         this.mOnBackPressedDispatcher = null;
      }

      ActivityResultLauncher var1 = this.mStartActivityForResult;
      if (var1 != null) {
         var1.unregister();
         this.mStartIntentSenderForResult.unregister();
         this.mRequestPermissions.unregister();
      }

   }

   void dispatchDestroyView() {
      this.dispatchStateChange(1);
   }

   void dispatchLowMemory() {
      Iterator var2 = this.mFragmentStore.getFragments().iterator();

      while(var2.hasNext()) {
         Fragment var1 = (Fragment)var2.next();
         if (var1 != null) {
            var1.performLowMemory();
         }
      }

   }

   void dispatchMultiWindowModeChanged(boolean var1) {
      Iterator var2 = this.mFragmentStore.getFragments().iterator();

      while(var2.hasNext()) {
         Fragment var3 = (Fragment)var2.next();
         if (var3 != null) {
            var3.performMultiWindowModeChanged(var1);
         }
      }

   }

   void dispatchOnAttachFragment(Fragment var1) {
      Iterator var2 = this.mOnAttachListeners.iterator();

      while(var2.hasNext()) {
         ((FragmentOnAttachListener)var2.next()).onAttachFragment(this, var1);
      }

   }

   boolean dispatchOptionsItemSelected(MenuItem var1) {
      if (this.mCurState < 1) {
         return false;
      } else {
         Iterator var2 = this.mFragmentStore.getFragments().iterator();

         Fragment var3;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            var3 = (Fragment)var2.next();
         } while(var3 == null || !var3.performOptionsItemSelected(var1));

         return true;
      }
   }

   void dispatchOptionsMenuClosed(Menu var1) {
      if (this.mCurState >= 1) {
         Iterator var3 = this.mFragmentStore.getFragments().iterator();

         while(var3.hasNext()) {
            Fragment var2 = (Fragment)var3.next();
            if (var2 != null) {
               var2.performOptionsMenuClosed(var1);
            }
         }

      }
   }

   void dispatchPause() {
      this.dispatchStateChange(5);
   }

   void dispatchPictureInPictureModeChanged(boolean var1) {
      Iterator var2 = this.mFragmentStore.getFragments().iterator();

      while(var2.hasNext()) {
         Fragment var3 = (Fragment)var2.next();
         if (var3 != null) {
            var3.performPictureInPictureModeChanged(var1);
         }
      }

   }

   boolean dispatchPrepareOptionsMenu(Menu var1) {
      int var2 = this.mCurState;
      boolean var3 = false;
      if (var2 < 1) {
         return false;
      } else {
         Iterator var4 = this.mFragmentStore.getFragments().iterator();

         while(var4.hasNext()) {
            Fragment var5 = (Fragment)var4.next();
            if (var5 != null && this.isParentMenuVisible(var5) && var5.performPrepareOptionsMenu(var1)) {
               var3 = true;
            }
         }

         return var3;
      }
   }

   void dispatchPrimaryNavigationFragmentChanged() {
      this.updateOnBackPressedCallbackEnabled();
      this.dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
   }

   void dispatchResume() {
      this.mStateSaved = false;
      this.mStopped = false;
      this.mNonConfig.setIsStateSaved(false);
      this.dispatchStateChange(7);
   }

   void dispatchStart() {
      this.mStateSaved = false;
      this.mStopped = false;
      this.mNonConfig.setIsStateSaved(false);
      this.dispatchStateChange(5);
   }

   void dispatchStop() {
      this.mStopped = true;
      this.mNonConfig.setIsStateSaved(true);
      this.dispatchStateChange(4);
   }

   void dispatchViewCreated() {
      this.dispatchStateChange(2);
   }

   public void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      String var8 = var1 + "    ";
      this.mFragmentStore.dump(var1, var2, var3, var4);
      ArrayList var40 = this.mCreatedMenus;
      byte var6 = 0;
      int var5;
      int var7;
      if (var40 != null) {
         var7 = var40.size();
         if (var7 > 0) {
            var3.print(var1);
            var3.println("Fragments Created Menus:");

            for(var5 = 0; var5 < var7; ++var5) {
               Fragment var41 = (Fragment)this.mCreatedMenus.get(var5);
               var3.print(var1);
               var3.print("  #");
               var3.print(var5);
               var3.print(": ");
               var3.println(var41.toString());
            }
         }
      }

      var40 = this.mBackStack;
      if (var40 != null) {
         var7 = var40.size();
         if (var7 > 0) {
            var3.print(var1);
            var3.println("Back Stack:");

            for(var5 = 0; var5 < var7; ++var5) {
               BackStackRecord var42 = (BackStackRecord)this.mBackStack.get(var5);
               var3.print(var1);
               var3.print("  #");
               var3.print(var5);
               var3.print(": ");
               var3.println(var42.toString());
               var42.dump(var8, var3);
            }
         }
      }

      var3.print(var1);
      var3.println("Back Stack Index: " + this.mBackStackIndex.get());
      var40 = this.mPendingActions;
      synchronized(var40){}

      label519: {
         Throwable var10000;
         boolean var10001;
         label520: {
            try {
               var7 = this.mPendingActions.size();
            } catch (Throwable var38) {
               var10000 = var38;
               var10001 = false;
               break label520;
            }

            if (var7 > 0) {
               try {
                  var3.print(var1);
                  var3.println("Pending Actions:");
               } catch (Throwable var37) {
                  var10000 = var37;
                  var10001 = false;
                  break label520;
               }

               for(var5 = var6; var5 < var7; ++var5) {
                  try {
                     OpGenerator var43 = (OpGenerator)this.mPendingActions.get(var5);
                     var3.print(var1);
                     var3.print("  #");
                     var3.print(var5);
                     var3.print(": ");
                     var3.println(var43);
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label520;
                  }
               }
            }

            label483:
            try {
               break label519;
            } catch (Throwable var35) {
               var10000 = var35;
               var10001 = false;
               break label483;
            }
         }

         while(true) {
            Throwable var39 = var10000;

            try {
               throw var39;
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               continue;
            }
         }
      }

      var3.print(var1);
      var3.println("FragmentManager misc state:");
      var3.print(var1);
      var3.print("  mHost=");
      var3.println(this.mHost);
      var3.print(var1);
      var3.print("  mContainer=");
      var3.println(this.mContainer);
      if (this.mParent != null) {
         var3.print(var1);
         var3.print("  mParent=");
         var3.println(this.mParent);
      }

      var3.print(var1);
      var3.print("  mCurState=");
      var3.print(this.mCurState);
      var3.print(" mStateSaved=");
      var3.print(this.mStateSaved);
      var3.print(" mStopped=");
      var3.print(this.mStopped);
      var3.print(" mDestroyed=");
      var3.println(this.mDestroyed);
      if (this.mNeedMenuInvalidate) {
         var3.print(var1);
         var3.print("  mNeedMenuInvalidate=");
         var3.println(this.mNeedMenuInvalidate);
      }

   }

   void enqueueAction(OpGenerator var1, boolean var2) {
      if (!var2) {
         if (this.mHost == null) {
            if (this.mDestroyed) {
               throw new IllegalStateException("FragmentManager has been destroyed");
            }

            throw new IllegalStateException("FragmentManager has not been attached to a host.");
         }

         this.checkStateLoss();
      }

      ArrayList var3 = this.mPendingActions;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label309: {
         label308: {
            try {
               if (this.mHost == null) {
                  break label308;
               }
            } catch (Throwable var33) {
               var10000 = var33;
               var10001 = false;
               break label309;
            }

            try {
               this.mPendingActions.add(var1);
               this.scheduleCommit();
               return;
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label309;
            }
         }

         if (var2) {
            label298:
            try {
               return;
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break label298;
            }
         } else {
            label300:
            try {
               IllegalStateException var35 = new IllegalStateException("Activity has been destroyed");
               throw var35;
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label300;
            }
         }
      }

      while(true) {
         Throwable var34 = var10000;

         try {
            throw var34;
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            continue;
         }
      }
   }

   boolean execPendingActions(boolean var1) {
      this.ensureExecReady(var1);

      for(var1 = false; this.generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop); var1 = true) {
         this.mExecutingActions = true;

         try {
            this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
         } finally {
            this.cleanupExec();
         }
      }

      this.updateOnBackPressedCallbackEnabled();
      this.doPendingDeferredStart();
      this.mFragmentStore.burpActive();
      return var1;
   }

   void execSingleAction(OpGenerator var1, boolean var2) {
      if (!var2 || this.mHost != null && !this.mDestroyed) {
         this.ensureExecReady(var2);
         if (var1.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;

            try {
               this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
               this.cleanupExec();
            }
         }

         this.updateOnBackPressedCallbackEnabled();
         this.doPendingDeferredStart();
         this.mFragmentStore.burpActive();
      }
   }

   public boolean executePendingTransactions() {
      boolean var1 = this.execPendingActions(true);
      this.forcePostponedTransactions();
      return var1;
   }

   Fragment findActiveFragment(String var1) {
      return this.mFragmentStore.findActiveFragment(var1);
   }

   public Fragment findFragmentById(int var1) {
      return this.mFragmentStore.findFragmentById(var1);
   }

   public Fragment findFragmentByTag(String var1) {
      return this.mFragmentStore.findFragmentByTag(var1);
   }

   Fragment findFragmentByWho(String var1) {
      return this.mFragmentStore.findFragmentByWho(var1);
   }

   int getActiveFragmentCount() {
      return this.mFragmentStore.getActiveFragmentCount();
   }

   List getActiveFragments() {
      return this.mFragmentStore.getActiveFragments();
   }

   public BackStackEntry getBackStackEntryAt(int var1) {
      return (BackStackEntry)this.mBackStack.get(var1);
   }

   public int getBackStackEntryCount() {
      ArrayList var2 = this.mBackStack;
      int var1;
      if (var2 != null) {
         var1 = var2.size();
      } else {
         var1 = 0;
      }

      return var1;
   }

   FragmentContainer getContainer() {
      return this.mContainer;
   }

   public Fragment getFragment(Bundle var1, String var2) {
      String var3 = var1.getString(var2);
      if (var3 == null) {
         return null;
      } else {
         Fragment var4 = this.findActiveFragment(var3);
         if (var4 == null) {
            this.throwException(new IllegalStateException("Fragment no longer exists for key " + var2 + ": unique id " + var3));
         }

         return var4;
      }
   }

   public FragmentFactory getFragmentFactory() {
      FragmentFactory var1 = this.mFragmentFactory;
      if (var1 != null) {
         return var1;
      } else {
         Fragment var2 = this.mParent;
         return var2 != null ? var2.mFragmentManager.getFragmentFactory() : this.mHostFragmentFactory;
      }
   }

   FragmentStore getFragmentStore() {
      return this.mFragmentStore;
   }

   public List getFragments() {
      return this.mFragmentStore.getFragments();
   }

   FragmentHostCallback getHost() {
      return this.mHost;
   }

   LayoutInflater.Factory2 getLayoutInflaterFactory() {
      return this.mLayoutInflaterFactory;
   }

   FragmentLifecycleCallbacksDispatcher getLifecycleCallbacksDispatcher() {
      return this.mLifecycleCallbacksDispatcher;
   }

   Fragment getParent() {
      return this.mParent;
   }

   public Fragment getPrimaryNavigationFragment() {
      return this.mPrimaryNav;
   }

   SpecialEffectsControllerFactory getSpecialEffectsControllerFactory() {
      SpecialEffectsControllerFactory var1 = this.mSpecialEffectsControllerFactory;
      if (var1 != null) {
         return var1;
      } else {
         Fragment var2 = this.mParent;
         return var2 != null ? var2.mFragmentManager.getSpecialEffectsControllerFactory() : this.mDefaultSpecialEffectsControllerFactory;
      }
   }

   ViewModelStore getViewModelStore(Fragment var1) {
      return this.mNonConfig.getViewModelStore(var1);
   }

   void handleOnBackPressed() {
      this.execPendingActions(true);
      if (this.mOnBackPressedCallback.isEnabled()) {
         this.popBackStackImmediate();
      } else {
         this.mOnBackPressedDispatcher.onBackPressed();
      }

   }

   void hideFragment(Fragment var1) {
      if (isLoggingEnabled(2)) {
         Log.v("FragmentManager", "hide: " + var1);
      }

      if (!var1.mHidden) {
         var1.mHidden = true;
         var1.mHiddenChanged ^= true;
         this.setVisibleRemovingFragment(var1);
      }

   }

   void invalidateMenuForFragment(Fragment var1) {
      if (var1.mAdded && this.isMenuAvailable(var1)) {
         this.mNeedMenuInvalidate = true;
      }

   }

   public boolean isDestroyed() {
      return this.mDestroyed;
   }

   boolean isParentMenuVisible(Fragment var1) {
      return var1 == null ? true : var1.isMenuVisible();
   }

   boolean isPrimaryNavigation(Fragment var1) {
      boolean var2 = true;
      if (var1 == null) {
         return true;
      } else {
         FragmentManager var3 = var1.mFragmentManager;
         if (!var1.equals(var3.getPrimaryNavigationFragment()) || !this.isPrimaryNavigation(var3.mParent)) {
            var2 = false;
         }

         return var2;
      }
   }

   boolean isStateAtLeast(int var1) {
      boolean var2;
      if (this.mCurState >= var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean isStateSaved() {
      boolean var1;
      if (!this.mStateSaved && !this.mStopped) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   void launchRequestPermissions(Fragment var1, String[] var2, int var3) {
      if (this.mRequestPermissions != null) {
         LaunchedFragmentInfo var4 = new LaunchedFragmentInfo(var1.mWho, var3);
         this.mLaunchedFragments.addLast(var4);
         this.mRequestPermissions.launch(var2);
      } else {
         this.mHost.onRequestPermissionsFromFragment(var1, var2, var3);
      }

   }

   void launchStartActivityForResult(Fragment var1, Intent var2, int var3, Bundle var4) {
      if (this.mStartActivityForResult != null) {
         LaunchedFragmentInfo var5 = new LaunchedFragmentInfo(var1.mWho, var3);
         this.mLaunchedFragments.addLast(var5);
         if (var2 != null && var4 != null) {
            var2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", var4);
         }

         this.mStartActivityForResult.launch(var2);
      } else {
         this.mHost.onStartActivityFromFragment(var1, var2, var3, var4);
      }

   }

   void launchStartIntentSenderForResult(Fragment var1, IntentSender var2, int var3, Intent var4, int var5, int var6, int var7, Bundle var8) throws IntentSender.SendIntentException {
      if (this.mStartIntentSenderForResult != null) {
         if (var8 != null) {
            if (var4 == null) {
               var4 = new Intent();
               var4.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            }

            if (isLoggingEnabled(2)) {
               Log.v("FragmentManager", "ActivityOptions " + var8 + " were added to fillInIntent " + var4 + " for fragment " + var1);
            }

            var4.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", var8);
         }

         IntentSenderRequest var10 = (new IntentSenderRequest.Builder(var2)).setFillInIntent(var4).setFlags(var6, var5).build();
         LaunchedFragmentInfo var9 = new LaunchedFragmentInfo(var1.mWho, var3);
         this.mLaunchedFragments.addLast(var9);
         if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Fragment " + var1 + "is launching an IntentSender for result ");
         }

         this.mStartIntentSenderForResult.launch(var10);
      } else {
         this.mHost.onStartIntentSenderFromFragment(var1, var2, var3, var4, var5, var6, var7, var8);
      }

   }

   void moveFragmentToExpectedState(Fragment var1) {
      if (!this.mFragmentStore.containsActiveFragment(var1.mWho)) {
         if (isLoggingEnabled(3)) {
            Log.d("FragmentManager", "Ignoring moving " + var1 + " to state " + this.mCurState + "since it is not added to " + this);
         }

      } else {
         this.moveToState(var1);
         if (var1.mView != null && var1.mIsNewlyAdded && var1.mContainer != null) {
            if (var1.mPostponedAlpha > 0.0F) {
               var1.mView.setAlpha(var1.mPostponedAlpha);
            }

            var1.mPostponedAlpha = 0.0F;
            var1.mIsNewlyAdded = false;
            FragmentAnim.AnimationOrAnimator var2 = FragmentAnim.loadAnimation(this.mHost.getContext(), var1, true, var1.getPopDirection());
            if (var2 != null) {
               if (var2.animation != null) {
                  var1.mView.startAnimation(var2.animation);
               } else {
                  var2.animator.setTarget(var1.mView);
                  var2.animator.start();
               }
            }
         }

         if (var1.mHiddenChanged) {
            this.completeShowHideFragment(var1);
         }

      }
   }

   void moveToState(int var1, boolean var2) {
      if (this.mHost == null && var1 != -1) {
         throw new IllegalStateException("No activity");
      } else if (var2 || var1 != this.mCurState) {
         this.mCurState = var1;
         if (USE_STATE_MANAGER) {
            this.mFragmentStore.moveToExpectedState();
         } else {
            Iterator var3 = this.mFragmentStore.getFragments().iterator();

            while(var3.hasNext()) {
               this.moveFragmentToExpectedState((Fragment)var3.next());
            }

            Iterator var5 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();

            while(var5.hasNext()) {
               FragmentStateManager var4 = (FragmentStateManager)var5.next();
               Fragment var7 = var4.getFragment();
               if (!var7.mIsNewlyAdded) {
                  this.moveFragmentToExpectedState(var7);
               }

               boolean var6;
               if (var7.mRemoving && !var7.isInBackStack()) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               if (var6) {
                  this.mFragmentStore.makeInactive(var4);
               }
            }
         }

         this.startPendingDeferredFragments();
         if (this.mNeedMenuInvalidate) {
            FragmentHostCallback var8 = this.mHost;
            if (var8 != null && this.mCurState == 7) {
               var8.onSupportInvalidateOptionsMenu();
               this.mNeedMenuInvalidate = false;
            }
         }

      }
   }

   void moveToState(Fragment var1) {
      this.moveToState(var1, this.mCurState);
   }

   void moveToState(Fragment var1, int var2) {
      FragmentStateManager var6 = this.mFragmentStore.getFragmentStateManager(var1.mWho);
      byte var4 = 1;
      FragmentStateManager var5 = var6;
      if (var6 == null) {
         var5 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, var1);
         var5.setFragmentManagerState(1);
      }

      int var3 = var2;
      if (var1.mFromLayout) {
         var3 = var2;
         if (var1.mInLayout) {
            var3 = var2;
            if (var1.mState == 2) {
               var3 = Math.max(var2, 2);
            }
         }
      }

      var2 = Math.min(var3, var5.computeExpectedState());
      if (var1.mState <= var2) {
         label165: {
            if (var1.mState < var2 && !this.mExitAnimationCancellationSignals.isEmpty()) {
               this.cancelExitAnimation(var1);
            }

            label156: {
               label155: {
                  label166: {
                     label153: {
                        var3 = var1.mState;
                        if (var3 != -1) {
                           if (var3 != 0) {
                              if (var3 != 1) {
                                 if (var3 != 2) {
                                    if (var3 != 4) {
                                       if (var3 != 5) {
                                          var3 = var2;
                                          break label165;
                                       }
                                       break label156;
                                    }
                                    break label155;
                                 }
                                 break label166;
                              }
                              break label153;
                           }
                        } else if (var2 > -1) {
                           var5.attach();
                        }

                        if (var2 > 0) {
                           var5.create();
                        }
                     }

                     if (var2 > -1) {
                        var5.ensureInflatedView();
                     }

                     if (var2 > 1) {
                        var5.createView();
                     }
                  }

                  if (var2 > 2) {
                     var5.activityCreated();
                  }
               }

               if (var2 > 4) {
                  var5.start();
               }
            }

            var3 = var2;
            if (var2 > 5) {
               var5.resume();
               var3 = var2;
            }
         }
      } else {
         var3 = var2;
         if (var1.mState > var2) {
            label167: {
               var3 = var1.mState;
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 != 4) {
                           if (var3 != 5) {
                              if (var3 != 7) {
                                 var3 = var2;
                                 break label167;
                              }

                              if (var2 < 7) {
                                 var5.pause();
                              }
                           }

                           if (var2 < 5) {
                              var5.stop();
                           }
                        }

                        if (var2 < 4) {
                           if (isLoggingEnabled(3)) {
                              Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + var1);
                           }

                           if (var1.mView != null && this.mHost.onShouldSaveFragmentState(var1) && var1.mSavedViewState == null) {
                              var5.saveViewState();
                           }
                        }
                     }

                     if (var2 < 2) {
                        View var7 = null;
                        if (var1.mView != null && var1.mContainer != null) {
                           var1.mContainer.endViewTransition(var1.mView);
                           var1.mView.clearAnimation();
                           if (!var1.isRemovingParent()) {
                              FragmentAnim.AnimationOrAnimator var9 = var7;
                              if (this.mCurState > -1) {
                                 var9 = var7;
                                 if (!this.mDestroyed) {
                                    var9 = var7;
                                    if (var1.mView.getVisibility() == 0) {
                                       var9 = var7;
                                       if (var1.mPostponedAlpha >= 0.0F) {
                                          var9 = FragmentAnim.loadAnimation(this.mHost.getContext(), var1, false, var1.getPopDirection());
                                       }
                                    }
                                 }
                              }

                              var1.mPostponedAlpha = 0.0F;
                              ViewGroup var8 = var1.mContainer;
                              var7 = var1.mView;
                              if (var9 != null) {
                                 FragmentAnim.animateRemoveFragment(var1, var9, this.mFragmentTransitionCallback);
                              }

                              var8.removeView(var7);
                              if (isLoggingEnabled(2)) {
                                 Log.v("FragmentManager", "Removing view " + var7 + " for fragment " + var1 + " from container " + var8);
                              }

                              if (var8 != var1.mContainer) {
                                 return;
                              }
                           }
                        }

                        if (this.mExitAnimationCancellationSignals.get(var1) == null) {
                           var5.destroyFragmentView();
                        }
                     }
                  }

                  if (var2 < 1) {
                     if (this.mExitAnimationCancellationSignals.get(var1) != null) {
                        var2 = var4;
                     } else {
                        var5.destroy();
                     }
                  }
               }

               if (var2 < 0) {
                  var5.detach();
               }

               var3 = var2;
            }
         }
      }

      if (var1.mState != var3) {
         if (isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveToState: Fragment state for " + var1 + " not updated inline; expected state " + var3 + " found " + var1.mState);
         }

         var1.mState = var3;
      }

   }

   void noteStateNotSaved() {
      if (this.mHost != null) {
         this.mStateSaved = false;
         this.mStopped = false;
         this.mNonConfig.setIsStateSaved(false);
         Iterator var2 = this.mFragmentStore.getFragments().iterator();

         while(var2.hasNext()) {
            Fragment var1 = (Fragment)var2.next();
            if (var1 != null) {
               var1.noteStateNotSaved();
            }
         }

      }
   }

   void onContainerAvailable(FragmentContainerView var1) {
      Iterator var2 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();

      while(var2.hasNext()) {
         FragmentStateManager var4 = (FragmentStateManager)var2.next();
         Fragment var3 = var4.getFragment();
         if (var3.mContainerId == var1.getId() && var3.mView != null && var3.mView.getParent() == null) {
            var3.mContainer = var1;
            var4.addViewToContainer();
         }
      }

   }

   @Deprecated
   public FragmentTransaction openTransaction() {
      return this.beginTransaction();
   }

   void performPendingDeferredStart(FragmentStateManager var1) {
      Fragment var2 = var1.getFragment();
      if (var2.mDeferStart) {
         if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
         }

         var2.mDeferStart = false;
         if (USE_STATE_MANAGER) {
            var1.moveToExpectedState();
         } else {
            this.moveToState(var2);
         }
      }

   }

   public void popBackStack() {
      this.enqueueAction(new PopBackStackState(this, (String)null, -1, 0), false);
   }

   public void popBackStack(int var1, int var2) {
      if (var1 >= 0) {
         this.enqueueAction(new PopBackStackState(this, (String)null, var1, var2), false);
      } else {
         throw new IllegalArgumentException("Bad id: " + var1);
      }
   }

   public void popBackStack(String var1, int var2) {
      this.enqueueAction(new PopBackStackState(this, var1, -1, var2), false);
   }

   public boolean popBackStackImmediate() {
      return this.popBackStackImmediate((String)null, -1, 0);
   }

   public boolean popBackStackImmediate(int var1, int var2) {
      if (var1 >= 0) {
         return this.popBackStackImmediate((String)null, var1, var2);
      } else {
         throw new IllegalArgumentException("Bad id: " + var1);
      }
   }

   public boolean popBackStackImmediate(String var1, int var2) {
      return this.popBackStackImmediate(var1, -1, var2);
   }

   boolean popBackStackState(ArrayList var1, ArrayList var2, String var3, int var4, int var5) {
      ArrayList var8 = this.mBackStack;
      if (var8 == null) {
         return false;
      } else {
         if (var3 == null && var4 < 0 && (var5 & 1) == 0) {
            var4 = var8.size() - 1;
            if (var4 < 0) {
               return false;
            }

            var1.add(this.mBackStack.remove(var4));
            var2.add(true);
         } else {
            if (var3 == null && var4 < 0) {
               var4 = -1;
            } else {
               int var6;
               BackStackRecord var9;
               for(var6 = var8.size() - 1; var6 >= 0; --var6) {
                  var9 = (BackStackRecord)this.mBackStack.get(var6);
                  if (var3 != null && var3.equals(var9.getName()) || var4 >= 0 && var4 == var9.mIndex) {
                     break;
                  }
               }

               if (var6 < 0) {
                  return false;
               }

               int var7 = var6;
               if ((var5 & 1) != 0) {
                  label70:
                  while(true) {
                     do {
                        var5 = var6 - 1;
                        var7 = var5;
                        if (var5 < 0) {
                           break label70;
                        }

                        var9 = (BackStackRecord)this.mBackStack.get(var5);
                        if (var3 == null) {
                           break;
                        }

                        var6 = var5;
                     } while(var3.equals(var9.getName()));

                     var7 = var5;
                     if (var4 < 0) {
                        break;
                     }

                     var7 = var5;
                     if (var4 != var9.mIndex) {
                        break;
                     }

                     var6 = var5;
                  }
               }

               var4 = var7;
            }

            if (var4 == this.mBackStack.size() - 1) {
               return false;
            }

            for(var5 = this.mBackStack.size() - 1; var5 > var4; --var5) {
               var1.add(this.mBackStack.remove(var5));
               var2.add(true);
            }
         }

         return true;
      }
   }

   public void putFragment(Bundle var1, String var2, Fragment var3) {
      if (var3.mFragmentManager != this) {
         this.throwException(new IllegalStateException("Fragment " + var3 + " is not currently in the FragmentManager"));
      }

      var1.putString(var2, var3.mWho);
   }

   public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks var1, boolean var2) {
      this.mLifecycleCallbacksDispatcher.registerFragmentLifecycleCallbacks(var1, var2);
   }

   void removeCancellationSignal(Fragment var1, CancellationSignal var2) {
      HashSet var3 = (HashSet)this.mExitAnimationCancellationSignals.get(var1);
      if (var3 != null && var3.remove(var2) && var3.isEmpty()) {
         this.mExitAnimationCancellationSignals.remove(var1);
         if (var1.mState < 5) {
            this.destroyFragmentView(var1);
            this.moveToState(var1);
         }
      }

   }

   void removeFragment(Fragment var1) {
      if (isLoggingEnabled(2)) {
         Log.v("FragmentManager", "remove: " + var1 + " nesting=" + var1.mBackStackNesting);
      }

      boolean var2 = var1.isInBackStack();
      if (!var1.mDetached || var2 ^ true) {
         this.mFragmentStore.removeFragment(var1);
         if (this.isMenuAvailable(var1)) {
            this.mNeedMenuInvalidate = true;
         }

         var1.mRemoving = true;
         this.setVisibleRemovingFragment(var1);
      }

   }

   public void removeFragmentOnAttachListener(FragmentOnAttachListener var1) {
      this.mOnAttachListeners.remove(var1);
   }

   public void removeOnBackStackChangedListener(OnBackStackChangedListener var1) {
      ArrayList var2 = this.mBackStackChangeListeners;
      if (var2 != null) {
         var2.remove(var1);
      }

   }

   void removeRetainedFragment(Fragment var1) {
      this.mNonConfig.removeRetainedFragment(var1);
   }

   void restoreAllState(Parcelable var1, FragmentManagerNonConfig var2) {
      if (this.mHost instanceof ViewModelStoreOwner) {
         this.throwException(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
      }

      this.mNonConfig.restoreFromSnapshot(var2);
      this.restoreSaveState(var1);
   }

   void restoreSaveState(Parcelable var1) {
      if (var1 != null) {
         FragmentManagerState var4 = (FragmentManagerState)var1;
         if (var4.mActive != null) {
            this.mFragmentStore.resetActiveFragments();
            Iterator var5 = var4.mActive.iterator();

            Fragment var7;
            while(var5.hasNext()) {
               FragmentState var6 = (FragmentState)var5.next();
               if (var6 != null) {
                  var7 = this.mNonConfig.findRetainedFragmentByWho(var6.mWho);
                  FragmentStateManager var8;
                  if (var7 != null) {
                     if (isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + var7);
                     }

                     var8 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, var7, var6);
                  } else {
                     var8 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, this.mHost.getContext().getClassLoader(), this.getFragmentFactory(), var6);
                  }

                  Fragment var16 = var8.getFragment();
                  var16.mFragmentManager = this;
                  if (isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "restoreSaveState: active (" + var16.mWho + "): " + var16);
                  }

                  var8.restoreState(this.mHost.getContext().getClassLoader());
                  this.mFragmentStore.makeActive(var8);
                  var8.setFragmentManagerState(this.mCurState);
               }
            }

            Iterator var9 = this.mNonConfig.getRetainedFragments().iterator();

            while(var9.hasNext()) {
               Fragment var13 = (Fragment)var9.next();
               if (!this.mFragmentStore.containsActiveFragment(var13.mWho)) {
                  if (isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "Discarding retained Fragment " + var13 + " that was not found in the set of active Fragments " + var4.mActive);
                  }

                  this.mNonConfig.removeRetainedFragment(var13);
                  var13.mFragmentManager = this;
                  FragmentStateManager var17 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, var13);
                  var17.setFragmentManagerState(1);
                  var17.moveToExpectedState();
                  var13.mRemoving = true;
                  var17.moveToExpectedState();
               }
            }

            this.mFragmentStore.restoreAddedFragments(var4.mAdded);
            BackStackState[] var10 = var4.mBackStack;
            byte var3 = 0;
            int var2;
            if (var10 != null) {
               this.mBackStack = new ArrayList(var4.mBackStack.length);

               for(var2 = 0; var2 < var4.mBackStack.length; ++var2) {
                  BackStackRecord var14 = var4.mBackStack[var2].instantiate(this);
                  if (isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "restoreAllState: back stack #" + var2 + " (index " + var14.mIndex + "): " + var14);
                     PrintWriter var11 = new PrintWriter(new LogWriter("FragmentManager"));
                     var14.dump("  ", var11, false);
                     var11.close();
                  }

                  this.mBackStack.add(var14);
               }
            } else {
               this.mBackStack = null;
            }

            this.mBackStackIndex.set(var4.mBackStackIndex);
            if (var4.mPrimaryNavActiveWho != null) {
               var7 = this.findActiveFragment(var4.mPrimaryNavActiveWho);
               this.mPrimaryNav = var7;
               this.dispatchParentPrimaryNavigationFragmentChanged(var7);
            }

            ArrayList var15 = var4.mResultKeys;
            if (var15 != null) {
               for(var2 = var3; var2 < var15.size(); ++var2) {
                  Bundle var12 = (Bundle)var4.mResults.get(var2);
                  var12.setClassLoader(this.mHost.getContext().getClassLoader());
                  this.mResults.put(var15.get(var2), var12);
               }
            }

            this.mLaunchedFragments = new ArrayDeque(var4.mLaunchedFragments);
         }
      }
   }

   @Deprecated
   FragmentManagerNonConfig retainNonConfig() {
      if (this.mHost instanceof ViewModelStoreOwner) {
         this.throwException(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
      }

      return this.mNonConfig.getSnapshot();
   }

   Parcelable saveAllState() {
      this.forcePostponedTransactions();
      this.endAnimatingAwayFragments();
      this.execPendingActions(true);
      this.mStateSaved = true;
      this.mNonConfig.setIsStateSaved(true);
      ArrayList var7 = this.mFragmentStore.saveActiveFragments();
      boolean var3 = var7.isEmpty();
      BackStackState[] var5 = null;
      if (var3) {
         if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
         }

         return null;
      } else {
         ArrayList var6 = this.mFragmentStore.saveAddedFragments();
         ArrayList var8 = this.mBackStack;
         BackStackState[] var4 = var5;
         if (var8 != null) {
            int var2 = var8.size();
            var4 = var5;
            if (var2 > 0) {
               var5 = new BackStackState[var2];
               int var1 = 0;

               while(true) {
                  var4 = var5;
                  if (var1 >= var2) {
                     break;
                  }

                  var5[var1] = new BackStackState((BackStackRecord)this.mBackStack.get(var1));
                  if (isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "saveAllState: adding back stack #" + var1 + ": " + this.mBackStack.get(var1));
                  }

                  ++var1;
               }
            }
         }

         FragmentManagerState var10 = new FragmentManagerState();
         var10.mActive = var7;
         var10.mAdded = var6;
         var10.mBackStack = var4;
         var10.mBackStackIndex = this.mBackStackIndex.get();
         Fragment var9 = this.mPrimaryNav;
         if (var9 != null) {
            var10.mPrimaryNavActiveWho = var9.mWho;
         }

         var10.mResultKeys.addAll(this.mResults.keySet());
         var10.mResults.addAll(this.mResults.values());
         var10.mLaunchedFragments = new ArrayList(this.mLaunchedFragments);
         return var10;
      }
   }

   public Fragment.SavedState saveFragmentInstanceState(Fragment var1) {
      FragmentStateManager var2 = this.mFragmentStore.getFragmentStateManager(var1.mWho);
      if (var2 == null || !var2.getFragment().equals(var1)) {
         this.throwException(new IllegalStateException("Fragment " + var1 + " is not currently in the FragmentManager"));
      }

      return var2.saveInstanceState();
   }

   void scheduleCommit() {
      ArrayList var3 = this.mPendingActions;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label436: {
         ArrayList var4;
         try {
            var4 = this.mPostponedTransactions;
         } catch (Throwable var46) {
            var10000 = var46;
            var10001 = false;
            break label436;
         }

         boolean var1;
         boolean var2;
         label427: {
            label426: {
               var2 = false;
               if (var4 != null) {
                  try {
                     if (!var4.isEmpty()) {
                        break label426;
                     }
                  } catch (Throwable var45) {
                     var10000 = var45;
                     var10001 = false;
                     break label436;
                  }
               }

               var1 = false;
               break label427;
            }

            var1 = true;
         }

         label418: {
            try {
               if (this.mPendingActions.size() != 1) {
                  break label418;
               }
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label436;
            }

            var2 = true;
         }

         if (var1 || var2) {
            try {
               this.mHost.getHandler().removeCallbacks(this.mExecCommit);
               this.mHost.getHandler().post(this.mExecCommit);
               this.updateOnBackPressedCallbackEnabled();
            } catch (Throwable var43) {
               var10000 = var43;
               var10001 = false;
               break label436;
            }
         }

         label406:
         try {
            return;
         } catch (Throwable var42) {
            var10000 = var42;
            var10001 = false;
            break label406;
         }
      }

      while(true) {
         Throwable var47 = var10000;

         try {
            throw var47;
         } catch (Throwable var41) {
            var10000 = var41;
            var10001 = false;
            continue;
         }
      }
   }

   void setExitAnimationOrder(Fragment var1, boolean var2) {
      ViewGroup var3 = this.getFragmentContainer(var1);
      if (var3 != null && var3 instanceof FragmentContainerView) {
         ((FragmentContainerView)var3).setDrawDisappearingViewsLast(var2 ^ true);
      }

   }

   public void setFragmentFactory(FragmentFactory var1) {
      this.mFragmentFactory = var1;
   }

   public final void setFragmentResult(String var1, Bundle var2) {
      LifecycleAwareResultListener var3 = (LifecycleAwareResultListener)this.mResultListeners.get(var1);
      if (var3 != null && var3.isAtLeast(Lifecycle.State.STARTED)) {
         var3.onFragmentResult(var1, var2);
      } else {
         this.mResults.put(var1, var2);
      }

   }

   public final void setFragmentResultListener(String var1, LifecycleOwner var2, FragmentResultListener var3) {
      Lifecycle var4 = var2.getLifecycle();
      if (var4.getCurrentState() != Lifecycle.State.DESTROYED) {
         LifecycleEventObserver var6 = new LifecycleEventObserver(this, var1, var3, var4) {
            final FragmentManager this$0;
            final Lifecycle val$lifecycle;
            final FragmentResultListener val$listener;
            final String val$requestKey;

            {
               this.this$0 = var1;
               this.val$requestKey = var2;
               this.val$listener = var3;
               this.val$lifecycle = var4;
            }

            public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
               if (var2 == Lifecycle.Event.ON_START) {
                  Bundle var3 = (Bundle)this.this$0.mResults.get(this.val$requestKey);
                  if (var3 != null) {
                     this.val$listener.onFragmentResult(this.val$requestKey, var3);
                     this.this$0.clearFragmentResult(this.val$requestKey);
                  }
               }

               if (var2 == Lifecycle.Event.ON_DESTROY) {
                  this.val$lifecycle.removeObserver(this);
                  this.this$0.mResultListeners.remove(this.val$requestKey);
               }

            }
         };
         var4.addObserver(var6);
         LifecycleAwareResultListener var5 = (LifecycleAwareResultListener)this.mResultListeners.put(var1, new LifecycleAwareResultListener(var4, var3, var6));
         if (var5 != null) {
            var5.removeObserver();
         }

      }
   }

   void setMaxLifecycle(Fragment var1, Lifecycle.State var2) {
      if (!var1.equals(this.findActiveFragment(var1.mWho)) || var1.mHost != null && var1.mFragmentManager != this) {
         throw new IllegalArgumentException("Fragment " + var1 + " is not an active fragment of FragmentManager " + this);
      } else {
         var1.mMaxState = var2;
      }
   }

   void setPrimaryNavigationFragment(Fragment var1) {
      if (var1 == null || var1.equals(this.findActiveFragment(var1.mWho)) && (var1.mHost == null || var1.mFragmentManager == this)) {
         Fragment var2 = this.mPrimaryNav;
         this.mPrimaryNav = var1;
         this.dispatchParentPrimaryNavigationFragmentChanged(var2);
         this.dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
      } else {
         throw new IllegalArgumentException("Fragment " + var1 + " is not an active fragment of FragmentManager " + this);
      }
   }

   void setSpecialEffectsControllerFactory(SpecialEffectsControllerFactory var1) {
      this.mSpecialEffectsControllerFactory = var1;
   }

   void showFragment(Fragment var1) {
      if (isLoggingEnabled(2)) {
         Log.v("FragmentManager", "show: " + var1);
      }

      if (var1.mHidden) {
         var1.mHidden = false;
         var1.mHiddenChanged ^= true;
      }

   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(128);
      var1.append("FragmentManager{");
      var1.append(Integer.toHexString(System.identityHashCode(this)));
      var1.append(" in ");
      Fragment var2 = this.mParent;
      if (var2 != null) {
         var1.append(var2.getClass().getSimpleName());
         var1.append("{");
         var1.append(Integer.toHexString(System.identityHashCode(this.mParent)));
         var1.append("}");
      } else {
         FragmentHostCallback var3 = this.mHost;
         if (var3 != null) {
            var1.append(var3.getClass().getSimpleName());
            var1.append("{");
            var1.append(Integer.toHexString(System.identityHashCode(this.mHost)));
            var1.append("}");
         } else {
            var1.append("null");
         }
      }

      var1.append("}}");
      return var1.toString();
   }

   public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks var1) {
      this.mLifecycleCallbacksDispatcher.unregisterFragmentLifecycleCallbacks(var1);
   }

   public interface BackStackEntry {
      @Deprecated
      CharSequence getBreadCrumbShortTitle();

      @Deprecated
      int getBreadCrumbShortTitleRes();

      @Deprecated
      CharSequence getBreadCrumbTitle();

      @Deprecated
      int getBreadCrumbTitleRes();

      int getId();

      String getName();
   }

   static class FragmentIntentSenderContract extends ActivityResultContract {
      public Intent createIntent(Context var1, IntentSenderRequest var2) {
         Intent var3 = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
         Intent var5 = var2.getFillInIntent();
         IntentSenderRequest var6 = var2;
         if (var5 != null) {
            Bundle var4 = var5.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            var6 = var2;
            if (var4 != null) {
               var3.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", var4);
               var5.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
               var6 = var2;
               if (var5.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                  var6 = (new IntentSenderRequest.Builder(var2.getIntentSender())).setFillInIntent((Intent)null).setFlags(var2.getFlagsValues(), var2.getFlagsMask()).build();
               }
            }
         }

         var3.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", var6);
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "CreateIntent created the following intent: " + var3);
         }

         return var3;
      }

      public ActivityResult parseResult(int var1, Intent var2) {
         return new ActivityResult(var1, var2);
      }
   }

   public abstract static class FragmentLifecycleCallbacks {
      @Deprecated
      public void onFragmentActivityCreated(FragmentManager var1, Fragment var2, Bundle var3) {
      }

      public void onFragmentAttached(FragmentManager var1, Fragment var2, Context var3) {
      }

      public void onFragmentCreated(FragmentManager var1, Fragment var2, Bundle var3) {
      }

      public void onFragmentDestroyed(FragmentManager var1, Fragment var2) {
      }

      public void onFragmentDetached(FragmentManager var1, Fragment var2) {
      }

      public void onFragmentPaused(FragmentManager var1, Fragment var2) {
      }

      public void onFragmentPreAttached(FragmentManager var1, Fragment var2, Context var3) {
      }

      public void onFragmentPreCreated(FragmentManager var1, Fragment var2, Bundle var3) {
      }

      public void onFragmentResumed(FragmentManager var1, Fragment var2) {
      }

      public void onFragmentSaveInstanceState(FragmentManager var1, Fragment var2, Bundle var3) {
      }

      public void onFragmentStarted(FragmentManager var1, Fragment var2) {
      }

      public void onFragmentStopped(FragmentManager var1, Fragment var2) {
      }

      public void onFragmentViewCreated(FragmentManager var1, Fragment var2, View var3, Bundle var4) {
      }

      public void onFragmentViewDestroyed(FragmentManager var1, Fragment var2) {
      }
   }

   static class LaunchedFragmentInfo implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public LaunchedFragmentInfo createFromParcel(Parcel var1) {
            return new LaunchedFragmentInfo(var1);
         }

         public LaunchedFragmentInfo[] newArray(int var1) {
            return new LaunchedFragmentInfo[var1];
         }
      };
      int mRequestCode;
      String mWho;

      LaunchedFragmentInfo(Parcel var1) {
         this.mWho = var1.readString();
         this.mRequestCode = var1.readInt();
      }

      LaunchedFragmentInfo(String var1, int var2) {
         this.mWho = var1;
         this.mRequestCode = var2;
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeString(this.mWho);
         var1.writeInt(this.mRequestCode);
      }
   }

   private static class LifecycleAwareResultListener implements FragmentResultListener {
      private final Lifecycle mLifecycle;
      private final FragmentResultListener mListener;
      private final LifecycleEventObserver mObserver;

      LifecycleAwareResultListener(Lifecycle var1, FragmentResultListener var2, LifecycleEventObserver var3) {
         this.mLifecycle = var1;
         this.mListener = var2;
         this.mObserver = var3;
      }

      public boolean isAtLeast(Lifecycle.State var1) {
         return this.mLifecycle.getCurrentState().isAtLeast(var1);
      }

      public void onFragmentResult(String var1, Bundle var2) {
         this.mListener.onFragmentResult(var1, var2);
      }

      public void removeObserver() {
         this.mLifecycle.removeObserver(this.mObserver);
      }
   }

   public interface OnBackStackChangedListener {
      void onBackStackChanged();
   }

   interface OpGenerator {
      boolean generateOps(ArrayList var1, ArrayList var2);
   }

   private class PopBackStackState implements OpGenerator {
      final int mFlags;
      final int mId;
      final String mName;
      final FragmentManager this$0;

      PopBackStackState(FragmentManager var1, String var2, int var3, int var4) {
         this.this$0 = var1;
         this.mName = var2;
         this.mId = var3;
         this.mFlags = var4;
      }

      public boolean generateOps(ArrayList var1, ArrayList var2) {
         return this.this$0.mPrimaryNav != null && this.mId < 0 && this.mName == null && this.this$0.mPrimaryNav.getChildFragmentManager().popBackStackImmediate() ? false : this.this$0.popBackStackState(var1, var2, this.mName, this.mId, this.mFlags);
      }
   }

   static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
      final boolean mIsBack;
      private int mNumPostponed;
      final BackStackRecord mRecord;

      StartEnterTransitionListener(BackStackRecord var1, boolean var2) {
         this.mIsBack = var2;
         this.mRecord = var1;
      }

      void cancelTransaction() {
         this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
      }

      void completeTransaction() {
         boolean var1;
         if (this.mNumPostponed > 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         Iterator var2 = this.mRecord.mManager.getFragments().iterator();

         while(var2.hasNext()) {
            Fragment var3 = (Fragment)var2.next();
            var3.setOnStartEnterTransitionListener((Fragment.OnStartEnterTransitionListener)null);
            if (var1 && var3.isPostponed()) {
               var3.startPostponedEnterTransition();
            }
         }

         this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, var1 ^ true, true);
      }

      public boolean isReady() {
         boolean var1;
         if (this.mNumPostponed == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void onStartEnterTransition() {
         int var1 = this.mNumPostponed - 1;
         this.mNumPostponed = var1;
         if (var1 == 0) {
            this.mRecord.mManager.scheduleCommit();
         }
      }

      public void startListening() {
         ++this.mNumPostponed;
      }
   }
}
