package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;
import java.util.Iterator;

class FragmentStateManager {
   private static final String TAG = "FragmentManager";
   private static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
   private static final String TARGET_STATE_TAG = "android:target_state";
   private static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
   private static final String VIEW_REGISTRY_STATE_TAG = "android:view_registry_state";
   private static final String VIEW_STATE_TAG = "android:view_state";
   private final FragmentLifecycleCallbacksDispatcher mDispatcher;
   private final Fragment mFragment;
   private int mFragmentManagerState = -1;
   private final FragmentStore mFragmentStore;
   private boolean mMovingToState = false;

   FragmentStateManager(FragmentLifecycleCallbacksDispatcher var1, FragmentStore var2, Fragment var3) {
      this.mDispatcher = var1;
      this.mFragmentStore = var2;
      this.mFragment = var3;
   }

   FragmentStateManager(FragmentLifecycleCallbacksDispatcher var1, FragmentStore var2, Fragment var3, FragmentState var4) {
      this.mDispatcher = var1;
      this.mFragmentStore = var2;
      this.mFragment = var3;
      var3.mSavedViewState = null;
      var3.mSavedViewRegistryState = null;
      var3.mBackStackNesting = 0;
      var3.mInLayout = false;
      var3.mAdded = false;
      String var5;
      if (var3.mTarget != null) {
         var5 = var3.mTarget.mWho;
      } else {
         var5 = null;
      }

      var3.mTargetWho = var5;
      var3.mTarget = null;
      if (var4.mSavedFragmentState != null) {
         var3.mSavedFragmentState = var4.mSavedFragmentState;
      } else {
         var3.mSavedFragmentState = new Bundle();
      }

   }

   FragmentStateManager(FragmentLifecycleCallbacksDispatcher var1, FragmentStore var2, ClassLoader var3, FragmentFactory var4, FragmentState var5) {
      this.mDispatcher = var1;
      this.mFragmentStore = var2;
      Fragment var6 = var4.instantiate(var3, var5.mClassName);
      this.mFragment = var6;
      if (var5.mArguments != null) {
         var5.mArguments.setClassLoader(var3);
      }

      var6.setArguments(var5.mArguments);
      var6.mWho = var5.mWho;
      var6.mFromLayout = var5.mFromLayout;
      var6.mRestored = true;
      var6.mFragmentId = var5.mFragmentId;
      var6.mContainerId = var5.mContainerId;
      var6.mTag = var5.mTag;
      var6.mRetainInstance = var5.mRetainInstance;
      var6.mRemoving = var5.mRemoving;
      var6.mDetached = var5.mDetached;
      var6.mHidden = var5.mHidden;
      var6.mMaxState = Lifecycle.State.values()[var5.mMaxLifecycleState];
      if (var5.mSavedFragmentState != null) {
         var6.mSavedFragmentState = var5.mSavedFragmentState;
      } else {
         var6.mSavedFragmentState = new Bundle();
      }

      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "Instantiated fragment " + var6);
      }

   }

   private boolean isFragmentViewChild(View var1) {
      if (var1 == this.mFragment.mView) {
         return true;
      } else {
         for(ViewParent var2 = var1.getParent(); var2 != null; var2 = var2.getParent()) {
            if (var2 == this.mFragment.mView) {
               return true;
            }
         }

         return false;
      }
   }

   private Bundle saveBasicState() {
      Bundle var1 = new Bundle();
      this.mFragment.performSaveInstanceState(var1);
      this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, var1, false);
      Bundle var2 = var1;
      if (var1.isEmpty()) {
         var2 = null;
      }

      if (this.mFragment.mView != null) {
         this.saveViewState();
      }

      var1 = var2;
      if (this.mFragment.mSavedViewState != null) {
         var1 = var2;
         if (var2 == null) {
            var1 = new Bundle();
         }

         var1.putSparseParcelableArray("android:view_state", this.mFragment.mSavedViewState);
      }

      var2 = var1;
      if (this.mFragment.mSavedViewRegistryState != null) {
         var2 = var1;
         if (var1 == null) {
            var2 = new Bundle();
         }

         var2.putBundle("android:view_registry_state", this.mFragment.mSavedViewRegistryState);
      }

      var1 = var2;
      if (!this.mFragment.mUserVisibleHint) {
         var1 = var2;
         if (var2 == null) {
            var1 = new Bundle();
         }

         var1.putBoolean("android:user_visible_hint", this.mFragment.mUserVisibleHint);
      }

      return var1;
   }

   void activityCreated() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.mFragment);
      }

      Fragment var1 = this.mFragment;
      var1.performActivityCreated(var1.mSavedFragmentState);
      FragmentLifecycleCallbacksDispatcher var3 = this.mDispatcher;
      Fragment var2 = this.mFragment;
      var3.dispatchOnFragmentActivityCreated(var2, var2.mSavedFragmentState, false);
   }

   void addViewToContainer() {
      int var1 = this.mFragmentStore.findFragmentIndexInContainer(this.mFragment);
      this.mFragment.mContainer.addView(this.mFragment.mView, var1);
   }

   void attach() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "moveto ATTACHED: " + this.mFragment);
      }

      Fragment var2 = this.mFragment.mTarget;
      FragmentStateManager var1 = null;
      if (var2 != null) {
         var1 = this.mFragmentStore.getFragmentStateManager(this.mFragment.mTarget.mWho);
         if (var1 == null) {
            throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTarget + " that does not belong to this FragmentManager!");
         }

         var2 = this.mFragment;
         var2.mTargetWho = var2.mTarget.mWho;
         this.mFragment.mTarget = null;
      } else if (this.mFragment.mTargetWho != null) {
         var1 = this.mFragmentStore.getFragmentStateManager(this.mFragment.mTargetWho);
         if (var1 == null) {
            throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTargetWho + " that does not belong to this FragmentManager!");
         }
      }

      if (var1 != null && (FragmentManager.USE_STATE_MANAGER || var1.getFragment().mState < 1)) {
         var1.moveToExpectedState();
      }

      Fragment var3 = this.mFragment;
      var3.mHost = var3.mFragmentManager.getHost();
      var3 = this.mFragment;
      var3.mParentFragment = var3.mFragmentManager.getParent();
      this.mDispatcher.dispatchOnFragmentPreAttached(this.mFragment, false);
      this.mFragment.performAttach();
      this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, false);
   }

   int computeExpectedState() {
      if (this.mFragment.mFragmentManager == null) {
         return this.mFragment.mState;
      } else {
         int var1 = this.mFragmentManagerState;
         int var3 = null.$SwitchMap$androidx$lifecycle$Lifecycle$State[this.mFragment.mMaxState.ordinal()];
         int var2 = var1;
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 != 4) {
                     var2 = Math.min(var1, -1);
                  } else {
                     var2 = Math.min(var1, 0);
                  }
               } else {
                  var2 = Math.min(var1, 1);
               }
            } else {
               var2 = Math.min(var1, 5);
            }
         }

         var1 = var2;
         if (this.mFragment.mFromLayout) {
            if (this.mFragment.mInLayout) {
               var2 = Math.max(this.mFragmentManagerState, 2);
               var1 = var2;
               if (this.mFragment.mView != null) {
                  var1 = var2;
                  if (this.mFragment.mView.getParent() == null) {
                     var1 = Math.min(var2, 2);
                  }
               }
            } else if (this.mFragmentManagerState < 4) {
               var1 = Math.min(var2, this.mFragment.mState);
            } else {
               var1 = Math.min(var2, 1);
            }
         }

         var2 = var1;
         if (!this.mFragment.mAdded) {
            var2 = Math.min(var1, 1);
         }

         Object var5 = null;
         SpecialEffectsController.Operation.LifecycleImpact var4 = (SpecialEffectsController.Operation.LifecycleImpact)var5;
         if (FragmentManager.USE_STATE_MANAGER) {
            var4 = (SpecialEffectsController.Operation.LifecycleImpact)var5;
            if (this.mFragment.mContainer != null) {
               var4 = SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).getAwaitingCompletionLifecycleImpact(this);
            }
         }

         if (var4 == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            var1 = Math.min(var2, 6);
         } else if (var4 == SpecialEffectsController.Operation.LifecycleImpact.REMOVING) {
            var1 = Math.max(var2, 3);
         } else {
            var1 = var2;
            if (this.mFragment.mRemoving) {
               if (this.mFragment.isInBackStack()) {
                  var1 = Math.min(var2, 1);
               } else {
                  var1 = Math.min(var2, -1);
               }
            }
         }

         var2 = var1;
         if (this.mFragment.mDeferStart) {
            var2 = var1;
            if (this.mFragment.mState < 5) {
               var2 = Math.min(var1, 4);
            }
         }

         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + var2 + " for " + this.mFragment);
         }

         return var2;
      }
   }

   void create() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "moveto CREATED: " + this.mFragment);
      }

      Fragment var1;
      if (!this.mFragment.mIsCreated) {
         FragmentLifecycleCallbacksDispatcher var2 = this.mDispatcher;
         var1 = this.mFragment;
         var2.dispatchOnFragmentPreCreated(var1, var1.mSavedFragmentState, false);
         var1 = this.mFragment;
         var1.performCreate(var1.mSavedFragmentState);
         var2 = this.mDispatcher;
         var1 = this.mFragment;
         var2.dispatchOnFragmentCreated(var1, var1.mSavedFragmentState, false);
      } else {
         var1 = this.mFragment;
         var1.restoreChildFragmentState(var1.mSavedFragmentState);
         this.mFragment.mState = 1;
      }

   }

   void createView() {
      if (!this.mFragment.mFromLayout) {
         if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
         }

         Fragment var5 = this.mFragment;
         LayoutInflater var7 = var5.performGetLayoutInflater(var5.mSavedFragmentState);
         ViewGroup var9 = null;
         if (this.mFragment.mContainer != null) {
            var9 = this.mFragment.mContainer;
         } else if (this.mFragment.mContainerId != 0) {
            if (this.mFragment.mContainerId == -1) {
               throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
            }

            ViewGroup var6 = (ViewGroup)this.mFragment.mFragmentManager.getContainer().onFindViewById(this.mFragment.mContainerId);
            var9 = var6;
            if (var6 == null) {
               if (!this.mFragment.mRestored) {
                  String var14;
                  try {
                     var14 = this.mFragment.getResources().getResourceName(this.mFragment.mContainerId);
                  } catch (Resources.NotFoundException var8) {
                     var14 = "unknown";
                  }

                  throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + var14 + ") for fragment " + this.mFragment);
               }

               var9 = var6;
            }
         }

         this.mFragment.mContainer = var9;
         Fragment var10 = this.mFragment;
         var10.performCreateView(var7, var9, var10.mSavedFragmentState);
         if (this.mFragment.mView != null) {
            View var11 = this.mFragment.mView;
            boolean var4 = false;
            var11.setSaveFromParentEnabled(false);
            this.mFragment.mView.setTag(R.id.fragment_container_view_tag, this.mFragment);
            if (var9 != null) {
               this.addViewToContainer();
            }

            if (this.mFragment.mHidden) {
               this.mFragment.mView.setVisibility(8);
            }

            View var12;
            if (ViewCompat.isAttachedToWindow(this.mFragment.mView)) {
               ViewCompat.requestApplyInsets(this.mFragment.mView);
            } else {
               var12 = this.mFragment.mView;
               var12.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(this, var12) {
                  final FragmentStateManager this$0;
                  final View val$fragmentView;

                  {
                     this.this$0 = var1;
                     this.val$fragmentView = var2;
                  }

                  public void onViewAttachedToWindow(View var1) {
                     this.val$fragmentView.removeOnAttachStateChangeListener(this);
                     ViewCompat.requestApplyInsets(this.val$fragmentView);
                  }

                  public void onViewDetachedFromWindow(View var1) {
                  }
               });
            }

            this.mFragment.performViewCreated();
            FragmentLifecycleCallbacksDispatcher var13 = this.mDispatcher;
            var5 = this.mFragment;
            var13.dispatchOnFragmentViewCreated(var5, var5.mView, this.mFragment.mSavedFragmentState, false);
            int var2 = this.mFragment.mView.getVisibility();
            float var1 = this.mFragment.mView.getAlpha();
            if (FragmentManager.USE_STATE_MANAGER) {
               this.mFragment.setPostOnViewCreatedAlpha(var1);
               if (this.mFragment.mContainer != null && var2 == 0) {
                  var12 = this.mFragment.mView.findFocus();
                  if (var12 != null) {
                     this.mFragment.setFocusedView(var12);
                     if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + var12 + " for Fragment " + this.mFragment);
                     }
                  }

                  this.mFragment.mView.setAlpha(0.0F);
               }
            } else {
               var5 = this.mFragment;
               boolean var3 = var4;
               if (var2 == 0) {
                  var3 = var4;
                  if (var5.mContainer != null) {
                     var3 = true;
                  }
               }

               var5.mIsNewlyAdded = var3;
            }
         }

         this.mFragment.mState = 2;
      }
   }

   void destroy() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "movefrom CREATED: " + this.mFragment);
      }

      boolean var4 = this.mFragment.mRemoving;
      boolean var3 = true;
      boolean var1;
      if (var4 && !this.mFragment.isInBackStack()) {
         var1 = true;
      } else {
         var1 = false;
      }

      boolean var2;
      if (!var1 && !this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
         var2 = false;
      } else {
         var2 = true;
      }

      Fragment var8;
      if (var2) {
         FragmentHostCallback var5 = this.mFragment.mHost;
         if (var5 instanceof ViewModelStoreOwner) {
            var3 = this.mFragmentStore.getNonConfig().isCleared();
         } else if (var5.getContext() instanceof Activity) {
            var3 = true ^ ((Activity)var5.getContext()).isChangingConfigurations();
         }

         if (var1 || var3) {
            this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment);
         }

         this.mFragment.performDestroy();
         this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
         Iterator var7 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();

         while(var7.hasNext()) {
            FragmentStateManager var6 = (FragmentStateManager)var7.next();
            if (var6 != null) {
               Fragment var9 = var6.getFragment();
               if (this.mFragment.mWho.equals(var9.mTargetWho)) {
                  var9.mTarget = this.mFragment;
                  var9.mTargetWho = null;
               }
            }
         }

         if (this.mFragment.mTargetWho != null) {
            var8 = this.mFragment;
            var8.mTarget = this.mFragmentStore.findActiveFragment(var8.mTargetWho);
         }

         this.mFragmentStore.makeInactive(this);
      } else {
         if (this.mFragment.mTargetWho != null) {
            var8 = this.mFragmentStore.findActiveFragment(this.mFragment.mTargetWho);
            if (var8 != null && var8.mRetainInstance) {
               this.mFragment.mTarget = var8;
            }
         }

         this.mFragment.mState = 0;
      }

   }

   void destroyFragmentView() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.mFragment);
      }

      if (this.mFragment.mContainer != null && this.mFragment.mView != null) {
         this.mFragment.mContainer.removeView(this.mFragment.mView);
      }

      this.mFragment.performDestroyView();
      this.mDispatcher.dispatchOnFragmentViewDestroyed(this.mFragment, false);
      this.mFragment.mContainer = null;
      this.mFragment.mView = null;
      this.mFragment.mViewLifecycleOwner = null;
      this.mFragment.mViewLifecycleOwnerLiveData.setValue((Object)null);
      this.mFragment.mInLayout = false;
   }

   void detach() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "movefrom ATTACHED: " + this.mFragment);
      }

      this.mFragment.performDetach();
      FragmentLifecycleCallbacksDispatcher var4 = this.mDispatcher;
      Fragment var3 = this.mFragment;
      boolean var2 = false;
      var4.dispatchOnFragmentDetached(var3, false);
      this.mFragment.mState = -1;
      this.mFragment.mHost = null;
      this.mFragment.mParentFragment = null;
      this.mFragment.mFragmentManager = null;
      boolean var1 = var2;
      if (this.mFragment.mRemoving) {
         var1 = var2;
         if (!this.mFragment.isInBackStack()) {
            var1 = true;
         }
      }

      if (var1 || this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
         if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
         }

         this.mFragment.initState();
      }

   }

   void ensureInflatedView() {
      if (this.mFragment.mFromLayout && this.mFragment.mInLayout && !this.mFragment.mPerformedCreateView) {
         if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
         }

         Fragment var1 = this.mFragment;
         var1.performCreateView(var1.performGetLayoutInflater(var1.mSavedFragmentState), (ViewGroup)null, this.mFragment.mSavedFragmentState);
         if (this.mFragment.mView != null) {
            this.mFragment.mView.setSaveFromParentEnabled(false);
            this.mFragment.mView.setTag(R.id.fragment_container_view_tag, this.mFragment);
            if (this.mFragment.mHidden) {
               this.mFragment.mView.setVisibility(8);
            }

            this.mFragment.performViewCreated();
            FragmentLifecycleCallbacksDispatcher var2 = this.mDispatcher;
            var1 = this.mFragment;
            var2.dispatchOnFragmentViewCreated(var1, var1.mView, this.mFragment.mSavedFragmentState, false);
            this.mFragment.mState = 2;
         }
      }

   }

   Fragment getFragment() {
      return this.mFragment;
   }

   void moveToExpectedState() {
      if (this.mMovingToState) {
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + this.getFragment());
         }

      } else {
         label8467: {
            Throwable var10000;
            label8466: {
               boolean var10001;
               try {
                  this.mMovingToState = true;
               } catch (Throwable var753) {
                  var10000 = var753;
                  var10001 = false;
                  break label8466;
               }

               label8465:
               while(true) {
                  label8463:
                  while(true) {
                     label8461:
                     while(true) {
                        while(true) {
                           label8457:
                           while(true) {
                              label8455:
                              while(true) {
                                 label8453:
                                 while(true) {
                                    label8451:
                                    while(true) {
                                       label8449:
                                       while(true) {
                                          label8480: {
                                             while(true) {
                                                try {
                                                   int var1 = this.computeExpectedState();
                                                   if (var1 == this.mFragment.mState) {
                                                      break;
                                                   }

                                                   if (var1 <= this.mFragment.mState) {
                                                      break label8449;
                                                   }

                                                   switch (this.mFragment.mState + 1) {
                                                      case 0:
                                                         break label8457;
                                                      case 1:
                                                         break label8455;
                                                      case 2:
                                                         break label8453;
                                                      case 3:
                                                         break label8451;
                                                      case 4:
                                                         break label8461;
                                                      case 5:
                                                         break label8480;
                                                      case 6:
                                                         break;
                                                      case 7:
                                                         break label8463;
                                                      default:
                                                         continue;
                                                   }
                                                } catch (Throwable var758) {
                                                   var10000 = var758;
                                                   var10001 = false;
                                                   break label8465;
                                                }

                                                try {
                                                   this.mFragment.mState = 6;
                                                } catch (Throwable var751) {
                                                   var10000 = var751;
                                                   var10001 = false;
                                                   break label8465;
                                                }
                                             }

                                             label8494: {
                                                SpecialEffectsController var759;
                                                try {
                                                   label8483: {
                                                      if (!FragmentManager.USE_STATE_MANAGER || !this.mFragment.mHiddenChanged) {
                                                         break label8467;
                                                      }

                                                      if (this.mFragment.mView != null && this.mFragment.mContainer != null) {
                                                         var759 = SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager());
                                                         if (!this.mFragment.mHidden) {
                                                            break label8483;
                                                         }

                                                         var759.enqueueHide(this);
                                                      }
                                                      break label8494;
                                                   }
                                                } catch (Throwable var735) {
                                                   var10000 = var735;
                                                   var10001 = false;
                                                   break label8465;
                                                }

                                                try {
                                                   var759.enqueueShow(this);
                                                } catch (Throwable var734) {
                                                   var10000 = var734;
                                                   var10001 = false;
                                                   break label8465;
                                                }
                                             }

                                             try {
                                                if (this.mFragment.mFragmentManager != null) {
                                                   this.mFragment.mFragmentManager.invalidateMenuForFragment(this.mFragment);
                                                }
                                             } catch (Throwable var733) {
                                                var10000 = var733;
                                                var10001 = false;
                                                break label8465;
                                             }

                                             try {
                                                this.mFragment.mHiddenChanged = false;
                                                Fragment var761 = this.mFragment;
                                                var761.onHiddenChanged(var761.mHidden);
                                                break label8467;
                                             } catch (Throwable var732) {
                                                var10000 = var732;
                                                var10001 = false;
                                                break label8465;
                                             }
                                          }

                                          try {
                                             this.start();
                                          } catch (Throwable var750) {
                                             var10000 = var750;
                                             var10001 = false;
                                             break label8465;
                                          }
                                       }

                                       label8484: {
                                          label8485: {
                                             label8486: {
                                                label8487: {
                                                   label8488: {
                                                      label8489: {
                                                         label8495: {
                                                            try {
                                                               switch (this.mFragment.mState - 1) {
                                                                  case -1:
                                                                     break label8485;
                                                                  case 0:
                                                                     break label8487;
                                                                  case 1:
                                                                     break label8488;
                                                                  case 2:
                                                                     break label8489;
                                                                  case 3:
                                                                     break label8486;
                                                                  case 4:
                                                                     break label8495;
                                                                  case 5:
                                                                     break;
                                                                  case 6:
                                                                     break label8484;
                                                                  default:
                                                                     continue;
                                                               }
                                                            } catch (Throwable var757) {
                                                               var10000 = var757;
                                                               var10001 = false;
                                                               break label8465;
                                                            }

                                                            try {
                                                               this.mFragment.mState = 5;
                                                               continue;
                                                            } catch (Throwable var743) {
                                                               var10000 = var743;
                                                               var10001 = false;
                                                               break label8465;
                                                            }
                                                         }

                                                         try {
                                                            this.stop();
                                                            continue;
                                                         } catch (Throwable var742) {
                                                            var10000 = var742;
                                                            var10001 = false;
                                                            break label8465;
                                                         }
                                                      }

                                                      try {
                                                         this.mFragment.mInLayout = false;
                                                         this.mFragment.mState = 2;
                                                         continue;
                                                      } catch (Throwable var739) {
                                                         var10000 = var739;
                                                         var10001 = false;
                                                         break label8465;
                                                      }
                                                   }

                                                   try {
                                                      this.destroyFragmentView();
                                                      this.mFragment.mState = 1;
                                                      continue;
                                                   } catch (Throwable var738) {
                                                      var10000 = var738;
                                                      var10001 = false;
                                                      break label8465;
                                                   }
                                                }

                                                try {
                                                   this.destroy();
                                                   continue;
                                                } catch (Throwable var737) {
                                                   var10000 = var737;
                                                   var10001 = false;
                                                   break label8465;
                                                }
                                             }

                                             try {
                                                if (FragmentManager.isLoggingEnabled(3)) {
                                                   StringBuilder var2 = new StringBuilder();
                                                   Log.d("FragmentManager", var2.append("movefrom ACTIVITY_CREATED: ").append(this.mFragment).toString());
                                                }
                                             } catch (Throwable var756) {
                                                var10000 = var756;
                                                var10001 = false;
                                                break label8465;
                                             }

                                             try {
                                                if (this.mFragment.mView != null && this.mFragment.mSavedViewState == null) {
                                                   this.saveViewState();
                                                }
                                             } catch (Throwable var741) {
                                                var10000 = var741;
                                                var10001 = false;
                                                break label8465;
                                             }

                                             try {
                                                if (this.mFragment.mView != null && this.mFragment.mContainer != null) {
                                                   SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).enqueueRemove(this);
                                                }
                                             } catch (Throwable var755) {
                                                var10000 = var755;
                                                var10001 = false;
                                                break label8465;
                                             }

                                             try {
                                                this.mFragment.mState = 3;
                                                continue;
                                             } catch (Throwable var740) {
                                                var10000 = var740;
                                                var10001 = false;
                                                break label8465;
                                             }
                                          }

                                          try {
                                             this.detach();
                                             continue;
                                          } catch (Throwable var736) {
                                             var10000 = var736;
                                             var10001 = false;
                                             break label8465;
                                          }
                                       }

                                       try {
                                          this.pause();
                                       } catch (Throwable var744) {
                                          var10000 = var744;
                                          var10001 = false;
                                          break label8465;
                                       }
                                    }

                                    try {
                                       this.activityCreated();
                                    } catch (Throwable var748) {
                                       var10000 = var748;
                                       var10001 = false;
                                       break label8465;
                                    }
                                 }

                                 try {
                                    this.ensureInflatedView();
                                    this.createView();
                                 } catch (Throwable var747) {
                                    var10000 = var747;
                                    var10001 = false;
                                    break label8465;
                                 }
                              }

                              try {
                                 this.create();
                              } catch (Throwable var746) {
                                 var10000 = var746;
                                 var10001 = false;
                                 break label8465;
                              }
                           }

                           try {
                              this.attach();
                           } catch (Throwable var745) {
                              var10000 = var745;
                              var10001 = false;
                              break label8465;
                           }
                        }
                     }

                     try {
                        if (this.mFragment.mView != null && this.mFragment.mContainer != null) {
                           SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).enqueueAdd(SpecialEffectsController.Operation.State.from(this.mFragment.mView.getVisibility()), this);
                        }
                     } catch (Throwable var754) {
                        var10000 = var754;
                        var10001 = false;
                        break label8465;
                     }

                     try {
                        this.mFragment.mState = 4;
                     } catch (Throwable var749) {
                        var10000 = var749;
                        var10001 = false;
                        break label8465;
                     }
                  }

                  try {
                     this.resume();
                  } catch (Throwable var752) {
                     var10000 = var752;
                     var10001 = false;
                     break;
                  }
               }
            }

            Throwable var760 = var10000;
            this.mMovingToState = false;
            throw var760;
         }

         this.mMovingToState = false;
      }
   }

   void pause() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "movefrom RESUMED: " + this.mFragment);
      }

      this.mFragment.performPause();
      this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
   }

   void restoreState(ClassLoader var1) {
      if (this.mFragment.mSavedFragmentState != null) {
         this.mFragment.mSavedFragmentState.setClassLoader(var1);
         Fragment var2 = this.mFragment;
         var2.mSavedViewState = var2.mSavedFragmentState.getSparseParcelableArray("android:view_state");
         var2 = this.mFragment;
         var2.mSavedViewRegistryState = var2.mSavedFragmentState.getBundle("android:view_registry_state");
         var2 = this.mFragment;
         var2.mTargetWho = var2.mSavedFragmentState.getString("android:target_state");
         if (this.mFragment.mTargetWho != null) {
            var2 = this.mFragment;
            var2.mTargetRequestCode = var2.mSavedFragmentState.getInt("android:target_req_state", 0);
         }

         if (this.mFragment.mSavedUserVisibleHint != null) {
            var2 = this.mFragment;
            var2.mUserVisibleHint = var2.mSavedUserVisibleHint;
            this.mFragment.mSavedUserVisibleHint = null;
         } else {
            var2 = this.mFragment;
            var2.mUserVisibleHint = var2.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
         }

         if (!this.mFragment.mUserVisibleHint) {
            this.mFragment.mDeferStart = true;
         }

      }
   }

   void resume() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "moveto RESUMED: " + this.mFragment);
      }

      View var2 = this.mFragment.getFocusedView();
      if (var2 != null && this.isFragmentViewChild(var2)) {
         boolean var1 = var2.requestFocus();
         if (FragmentManager.isLoggingEnabled(2)) {
            StringBuilder var3 = (new StringBuilder()).append("requestFocus: Restoring focused view ").append(var2).append(" ");
            String var4;
            if (var1) {
               var4 = "succeeded";
            } else {
               var4 = "failed";
            }

            Log.v("FragmentManager", var3.append(var4).append(" on Fragment ").append(this.mFragment).append(" resulting in focused view ").append(this.mFragment.mView.findFocus()).toString());
         }
      }

      this.mFragment.setFocusedView((View)null);
      this.mFragment.performResume();
      this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
      this.mFragment.mSavedFragmentState = null;
      this.mFragment.mSavedViewState = null;
      this.mFragment.mSavedViewRegistryState = null;
   }

   Fragment.SavedState saveInstanceState() {
      int var1 = this.mFragment.mState;
      Object var3 = null;
      Fragment.SavedState var2 = (Fragment.SavedState)var3;
      if (var1 > -1) {
         Bundle var4 = this.saveBasicState();
         var2 = (Fragment.SavedState)var3;
         if (var4 != null) {
            var2 = new Fragment.SavedState(var4);
         }
      }

      return var2;
   }

   FragmentState saveState() {
      FragmentState var1 = new FragmentState(this.mFragment);
      if (this.mFragment.mState > -1 && var1.mSavedFragmentState == null) {
         var1.mSavedFragmentState = this.saveBasicState();
         if (this.mFragment.mTargetWho != null) {
            if (var1.mSavedFragmentState == null) {
               var1.mSavedFragmentState = new Bundle();
            }

            var1.mSavedFragmentState.putString("android:target_state", this.mFragment.mTargetWho);
            if (this.mFragment.mTargetRequestCode != 0) {
               var1.mSavedFragmentState.putInt("android:target_req_state", this.mFragment.mTargetRequestCode);
            }
         }
      } else {
         var1.mSavedFragmentState = this.mFragment.mSavedFragmentState;
      }

      return var1;
   }

   void saveViewState() {
      if (this.mFragment.mView != null) {
         SparseArray var1 = new SparseArray();
         this.mFragment.mView.saveHierarchyState(var1);
         if (var1.size() > 0) {
            this.mFragment.mSavedViewState = var1;
         }

         Bundle var2 = new Bundle();
         this.mFragment.mViewLifecycleOwner.performSave(var2);
         if (!var2.isEmpty()) {
            this.mFragment.mSavedViewRegistryState = var2;
         }

      }
   }

   void setFragmentManagerState(int var1) {
      this.mFragmentManagerState = var1;
   }

   void start() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "moveto STARTED: " + this.mFragment);
      }

      this.mFragment.performStart();
      this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
   }

   void stop() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "movefrom STARTED: " + this.mFragment);
      }

      this.mFragment.performStop();
      this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
   }
}
