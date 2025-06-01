package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.arch.core.util.Function;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ActivityResultCaller {
   static final int ACTIVITY_CREATED = 4;
   static final int ATTACHED = 0;
   static final int AWAITING_ENTER_EFFECTS = 6;
   static final int AWAITING_EXIT_EFFECTS = 3;
   static final int CREATED = 1;
   static final int INITIALIZING = -1;
   static final int RESUMED = 7;
   static final int STARTED = 5;
   static final Object USE_DEFAULT_TRANSITION = new Object();
   static final int VIEW_CREATED = 2;
   boolean mAdded;
   AnimationInfo mAnimationInfo;
   Bundle mArguments;
   int mBackStackNesting;
   private boolean mCalled;
   FragmentManager mChildFragmentManager;
   ViewGroup mContainer;
   int mContainerId;
   private int mContentLayoutId;
   ViewModelProvider.Factory mDefaultFactory;
   boolean mDeferStart;
   boolean mDetached;
   int mFragmentId;
   FragmentManager mFragmentManager;
   boolean mFromLayout;
   boolean mHasMenu;
   boolean mHidden;
   boolean mHiddenChanged;
   FragmentHostCallback mHost;
   boolean mInLayout;
   boolean mIsCreated;
   boolean mIsNewlyAdded;
   private Boolean mIsPrimaryNavigationFragment;
   LayoutInflater mLayoutInflater;
   LifecycleRegistry mLifecycleRegistry;
   Lifecycle.State mMaxState;
   boolean mMenuVisible;
   private final AtomicInteger mNextLocalRequestCode;
   private final ArrayList mOnPreAttachedListeners;
   Fragment mParentFragment;
   boolean mPerformedCreateView;
   float mPostponedAlpha;
   Runnable mPostponedDurationRunnable;
   boolean mRemoving;
   boolean mRestored;
   boolean mRetainInstance;
   boolean mRetainInstanceChangedWhileDetached;
   Bundle mSavedFragmentState;
   SavedStateRegistryController mSavedStateRegistryController;
   Boolean mSavedUserVisibleHint;
   Bundle mSavedViewRegistryState;
   SparseArray mSavedViewState;
   int mState;
   String mTag;
   Fragment mTarget;
   int mTargetRequestCode;
   String mTargetWho;
   boolean mUserVisibleHint;
   View mView;
   FragmentViewLifecycleOwner mViewLifecycleOwner;
   MutableLiveData mViewLifecycleOwnerLiveData;
   String mWho;

   public Fragment() {
      this.mState = -1;
      this.mWho = UUID.randomUUID().toString();
      this.mTargetWho = null;
      this.mIsPrimaryNavigationFragment = null;
      this.mChildFragmentManager = new FragmentManagerImpl();
      this.mMenuVisible = true;
      this.mUserVisibleHint = true;
      this.mPostponedDurationRunnable = new Runnable(this) {
         final Fragment this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.startPostponedEnterTransition();
         }
      };
      this.mMaxState = Lifecycle.State.RESUMED;
      this.mViewLifecycleOwnerLiveData = new MutableLiveData();
      this.mNextLocalRequestCode = new AtomicInteger();
      this.mOnPreAttachedListeners = new ArrayList();
      this.initLifecycle();
   }

   public Fragment(int var1) {
      this();
      this.mContentLayoutId = var1;
   }

   private AnimationInfo ensureAnimationInfo() {
      if (this.mAnimationInfo == null) {
         this.mAnimationInfo = new AnimationInfo();
      }

      return this.mAnimationInfo;
   }

   private int getMinimumMaxLifecycleState() {
      return this.mMaxState != Lifecycle.State.INITIALIZED && this.mParentFragment != null ? Math.min(this.mMaxState.ordinal(), this.mParentFragment.getMinimumMaxLifecycleState()) : this.mMaxState.ordinal();
   }

   private void initLifecycle() {
      this.mLifecycleRegistry = new LifecycleRegistry(this);
      this.mSavedStateRegistryController = SavedStateRegistryController.create(this);
      this.mDefaultFactory = null;
   }

   @Deprecated
   public static Fragment instantiate(Context var0, String var1) {
      return instantiate(var0, var1, (Bundle)null);
   }

   @Deprecated
   public static Fragment instantiate(Context param0, String param1, Bundle param2) {
      // $FF: Couldn't be decompiled
   }

   private ActivityResultLauncher prepareCallInternal(ActivityResultContract var1, Function var2, ActivityResultCallback var3) {
      if (this.mState <= 1) {
         AtomicReference var4 = new AtomicReference();
         this.registerOnPreAttachListener(new OnPreAttachedListener(this, var2, var4, var1, var3) {
            final Fragment this$0;
            final ActivityResultCallback val$callback;
            final ActivityResultContract val$contract;
            final AtomicReference val$ref;
            final Function val$registryProvider;

            {
               this.this$0 = var1;
               this.val$registryProvider = var2;
               this.val$ref = var3;
               this.val$contract = var4;
               this.val$callback = var5;
            }

            void onPreAttached() {
               String var1 = this.this$0.generateActivityResultKey();
               ActivityResultRegistry var2 = (ActivityResultRegistry)this.val$registryProvider.apply((Object)null);
               this.val$ref.set(var2.register(var1, this.this$0, this.val$contract, this.val$callback));
            }
         });
         return new ActivityResultLauncher(this, var4, var1) {
            final Fragment this$0;
            final ActivityResultContract val$contract;
            final AtomicReference val$ref;

            {
               this.this$0 = var1;
               this.val$ref = var2;
               this.val$contract = var3;
            }

            public ActivityResultContract getContract() {
               return this.val$contract;
            }

            public void launch(Object var1, ActivityOptionsCompat var2) {
               ActivityResultLauncher var3 = (ActivityResultLauncher)this.val$ref.get();
               if (var3 != null) {
                  var3.launch(var1, var2);
               } else {
                  throw new IllegalStateException("Operation cannot be started before fragment is in created state");
               }
            }

            public void unregister() {
               ActivityResultLauncher var1 = (ActivityResultLauncher)this.val$ref.getAndSet((Object)null);
               if (var1 != null) {
                  var1.unregister();
               }

            }
         };
      } else {
         throw new IllegalStateException("Fragment " + this + " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).");
      }
   }

   private void registerOnPreAttachListener(OnPreAttachedListener var1) {
      if (this.mState >= 0) {
         var1.onPreAttached();
      } else {
         this.mOnPreAttachedListeners.add(var1);
      }

   }

   private void restoreViewState() {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
      }

      if (this.mView != null) {
         this.restoreViewState(this.mSavedFragmentState);
      }

      this.mSavedFragmentState = null;
   }

   void callStartTransitionListener(boolean var1) {
      AnimationInfo var3 = this.mAnimationInfo;
      OnStartEnterTransitionListener var2 = null;
      if (var3 != null) {
         var3.mEnterTransitionPostponed = false;
         var2 = this.mAnimationInfo.mStartEnterTransitionListener;
         this.mAnimationInfo.mStartEnterTransitionListener = null;
      }

      if (var2 != null) {
         var2.onStartEnterTransition();
      } else if (FragmentManager.USE_STATE_MANAGER && this.mView != null) {
         ViewGroup var4 = this.mContainer;
         if (var4 != null) {
            FragmentManager var5 = this.mFragmentManager;
            if (var5 != null) {
               SpecialEffectsController var6 = SpecialEffectsController.getOrCreateController(var4, var5);
               var6.markPostponedState();
               if (var1) {
                  this.mHost.getHandler().post(new Runnable(this, var6) {
                     final Fragment this$0;
                     final SpecialEffectsController val$controller;

                     {
                        this.this$0 = var1;
                        this.val$controller = var2;
                     }

                     public void run() {
                        this.val$controller.executePendingOperations();
                     }
                  });
               } else {
                  var6.executePendingOperations();
               }
            }
         }
      }

   }

   FragmentContainer createFragmentContainer() {
      return new FragmentContainer(this) {
         final Fragment this$0;

         {
            this.this$0 = var1;
         }

         public View onFindViewById(int var1) {
            if (this.this$0.mView != null) {
               return this.this$0.mView.findViewById(var1);
            } else {
               throw new IllegalStateException("Fragment " + this.this$0 + " does not have a view");
            }
         }

         public boolean onHasView() {
            boolean var1;
            if (this.this$0.mView != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }
      };
   }

   public void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      var3.print(var1);
      var3.print("mFragmentId=#");
      var3.print(Integer.toHexString(this.mFragmentId));
      var3.print(" mContainerId=#");
      var3.print(Integer.toHexString(this.mContainerId));
      var3.print(" mTag=");
      var3.println(this.mTag);
      var3.print(var1);
      var3.print("mState=");
      var3.print(this.mState);
      var3.print(" mWho=");
      var3.print(this.mWho);
      var3.print(" mBackStackNesting=");
      var3.println(this.mBackStackNesting);
      var3.print(var1);
      var3.print("mAdded=");
      var3.print(this.mAdded);
      var3.print(" mRemoving=");
      var3.print(this.mRemoving);
      var3.print(" mFromLayout=");
      var3.print(this.mFromLayout);
      var3.print(" mInLayout=");
      var3.println(this.mInLayout);
      var3.print(var1);
      var3.print("mHidden=");
      var3.print(this.mHidden);
      var3.print(" mDetached=");
      var3.print(this.mDetached);
      var3.print(" mMenuVisible=");
      var3.print(this.mMenuVisible);
      var3.print(" mHasMenu=");
      var3.println(this.mHasMenu);
      var3.print(var1);
      var3.print("mRetainInstance=");
      var3.print(this.mRetainInstance);
      var3.print(" mUserVisibleHint=");
      var3.println(this.mUserVisibleHint);
      if (this.mFragmentManager != null) {
         var3.print(var1);
         var3.print("mFragmentManager=");
         var3.println(this.mFragmentManager);
      }

      if (this.mHost != null) {
         var3.print(var1);
         var3.print("mHost=");
         var3.println(this.mHost);
      }

      if (this.mParentFragment != null) {
         var3.print(var1);
         var3.print("mParentFragment=");
         var3.println(this.mParentFragment);
      }

      if (this.mArguments != null) {
         var3.print(var1);
         var3.print("mArguments=");
         var3.println(this.mArguments);
      }

      if (this.mSavedFragmentState != null) {
         var3.print(var1);
         var3.print("mSavedFragmentState=");
         var3.println(this.mSavedFragmentState);
      }

      if (this.mSavedViewState != null) {
         var3.print(var1);
         var3.print("mSavedViewState=");
         var3.println(this.mSavedViewState);
      }

      if (this.mSavedViewRegistryState != null) {
         var3.print(var1);
         var3.print("mSavedViewRegistryState=");
         var3.println(this.mSavedViewRegistryState);
      }

      Fragment var5 = this.getTargetFragment();
      if (var5 != null) {
         var3.print(var1);
         var3.print("mTarget=");
         var3.print(var5);
         var3.print(" mTargetRequestCode=");
         var3.println(this.mTargetRequestCode);
      }

      var3.print(var1);
      var3.print("mPopDirection=");
      var3.println(this.getPopDirection());
      if (this.getEnterAnim() != 0) {
         var3.print(var1);
         var3.print("getEnterAnim=");
         var3.println(this.getEnterAnim());
      }

      if (this.getExitAnim() != 0) {
         var3.print(var1);
         var3.print("getExitAnim=");
         var3.println(this.getExitAnim());
      }

      if (this.getPopEnterAnim() != 0) {
         var3.print(var1);
         var3.print("getPopEnterAnim=");
         var3.println(this.getPopEnterAnim());
      }

      if (this.getPopExitAnim() != 0) {
         var3.print(var1);
         var3.print("getPopExitAnim=");
         var3.println(this.getPopExitAnim());
      }

      if (this.mContainer != null) {
         var3.print(var1);
         var3.print("mContainer=");
         var3.println(this.mContainer);
      }

      if (this.mView != null) {
         var3.print(var1);
         var3.print("mView=");
         var3.println(this.mView);
      }

      if (this.getAnimatingAway() != null) {
         var3.print(var1);
         var3.print("mAnimatingAway=");
         var3.println(this.getAnimatingAway());
      }

      if (this.getContext() != null) {
         LoaderManager.getInstance(this).dump(var1, var2, var3, var4);
      }

      var3.print(var1);
      var3.println("Child " + this.mChildFragmentManager + ":");
      this.mChildFragmentManager.dump(var1 + "  ", var2, var3, var4);
   }

   public final boolean equals(Object var1) {
      return super.equals(var1);
   }

   Fragment findFragmentByWho(String var1) {
      return var1.equals(this.mWho) ? this : this.mChildFragmentManager.findFragmentByWho(var1);
   }

   String generateActivityResultKey() {
      return "fragment_" + this.mWho + "_rq#" + this.mNextLocalRequestCode.getAndIncrement();
   }

   public final FragmentActivity getActivity() {
      FragmentHostCallback var1 = this.mHost;
      FragmentActivity var2;
      if (var1 == null) {
         var2 = null;
      } else {
         var2 = (FragmentActivity)var1.getActivity();
      }

      return var2;
   }

   public boolean getAllowEnterTransitionOverlap() {
      AnimationInfo var2 = this.mAnimationInfo;
      boolean var1;
      if (var2 != null && var2.mAllowEnterTransitionOverlap != null) {
         var1 = this.mAnimationInfo.mAllowEnterTransitionOverlap;
      } else {
         var1 = true;
      }

      return var1;
   }

   public boolean getAllowReturnTransitionOverlap() {
      AnimationInfo var2 = this.mAnimationInfo;
      boolean var1;
      if (var2 != null && var2.mAllowReturnTransitionOverlap != null) {
         var1 = this.mAnimationInfo.mAllowReturnTransitionOverlap;
      } else {
         var1 = true;
      }

      return var1;
   }

   View getAnimatingAway() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mAnimatingAway;
   }

   Animator getAnimator() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mAnimator;
   }

   public final Bundle getArguments() {
      return this.mArguments;
   }

   public final FragmentManager getChildFragmentManager() {
      if (this.mHost != null) {
         return this.mChildFragmentManager;
      } else {
         throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
      }
   }

   public Context getContext() {
      FragmentHostCallback var1 = this.mHost;
      Context var2;
      if (var1 == null) {
         var2 = null;
      } else {
         var2 = var1.getContext();
      }

      return var2;
   }

   public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
      if (this.mFragmentManager == null) {
         throw new IllegalStateException("Can't access ViewModels from detached fragment");
      } else {
         if (this.mDefaultFactory == null) {
            Object var3 = null;
            Context var1 = this.requireContext().getApplicationContext();

            Application var2;
            while(true) {
               var2 = (Application)var3;
               if (!(var1 instanceof ContextWrapper)) {
                  break;
               }

               if (var1 instanceof Application) {
                  var2 = (Application)var1;
                  break;
               }

               var1 = ((ContextWrapper)var1).getBaseContext();
            }

            if (var2 == null && FragmentManager.isLoggingEnabled(3)) {
               Log.d("FragmentManager", "Could not find Application instance from Context " + this.requireContext().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
            }

            this.mDefaultFactory = new SavedStateViewModelFactory(var2, this, this.getArguments());
         }

         return this.mDefaultFactory;
      }
   }

   int getEnterAnim() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? 0 : var1.mEnterAnim;
   }

   public Object getEnterTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mEnterTransition;
   }

   SharedElementCallback getEnterTransitionCallback() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mEnterTransitionCallback;
   }

   int getExitAnim() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? 0 : var1.mExitAnim;
   }

   public Object getExitTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mExitTransition;
   }

   SharedElementCallback getExitTransitionCallback() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mExitTransitionCallback;
   }

   View getFocusedView() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mFocusedView;
   }

   @Deprecated
   public final FragmentManager getFragmentManager() {
      return this.mFragmentManager;
   }

   public final Object getHost() {
      FragmentHostCallback var1 = this.mHost;
      Object var2;
      if (var1 == null) {
         var2 = null;
      } else {
         var2 = var1.onGetHost();
      }

      return var2;
   }

   public final int getId() {
      return this.mFragmentId;
   }

   public final LayoutInflater getLayoutInflater() {
      LayoutInflater var2 = this.mLayoutInflater;
      LayoutInflater var1 = var2;
      if (var2 == null) {
         var1 = this.performGetLayoutInflater((Bundle)null);
      }

      return var1;
   }

   @Deprecated
   public LayoutInflater getLayoutInflater(Bundle var1) {
      FragmentHostCallback var2 = this.mHost;
      if (var2 != null) {
         LayoutInflater var3 = var2.onGetLayoutInflater();
         LayoutInflaterCompat.setFactory2(var3, this.mChildFragmentManager.getLayoutInflaterFactory());
         return var3;
      } else {
         throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
      }
   }

   public Lifecycle getLifecycle() {
      return this.mLifecycleRegistry;
   }

   @Deprecated
   public LoaderManager getLoaderManager() {
      return LoaderManager.getInstance(this);
   }

   int getNextTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? 0 : var1.mNextTransition;
   }

   public final Fragment getParentFragment() {
      return this.mParentFragment;
   }

   public final FragmentManager getParentFragmentManager() {
      FragmentManager var1 = this.mFragmentManager;
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
      }
   }

   boolean getPopDirection() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? false : var1.mIsPop;
   }

   int getPopEnterAnim() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? 0 : var1.mPopEnterAnim;
   }

   int getPopExitAnim() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? 0 : var1.mPopExitAnim;
   }

   float getPostOnViewCreatedAlpha() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? 1.0F : var1.mPostOnViewCreatedAlpha;
   }

   public Object getReenterTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      if (var1 == null) {
         return null;
      } else {
         Object var2;
         if (var1.mReenterTransition == USE_DEFAULT_TRANSITION) {
            var2 = this.getExitTransition();
         } else {
            var2 = this.mAnimationInfo.mReenterTransition;
         }

         return var2;
      }
   }

   public final Resources getResources() {
      return this.requireContext().getResources();
   }

   @Deprecated
   public final boolean getRetainInstance() {
      return this.mRetainInstance;
   }

   public Object getReturnTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      if (var1 == null) {
         return null;
      } else {
         Object var2;
         if (var1.mReturnTransition == USE_DEFAULT_TRANSITION) {
            var2 = this.getEnterTransition();
         } else {
            var2 = this.mAnimationInfo.mReturnTransition;
         }

         return var2;
      }
   }

   public final SavedStateRegistry getSavedStateRegistry() {
      return this.mSavedStateRegistryController.getSavedStateRegistry();
   }

   public Object getSharedElementEnterTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? null : var1.mSharedElementEnterTransition;
   }

   public Object getSharedElementReturnTransition() {
      AnimationInfo var1 = this.mAnimationInfo;
      if (var1 == null) {
         return null;
      } else {
         Object var2;
         if (var1.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION) {
            var2 = this.getSharedElementEnterTransition();
         } else {
            var2 = this.mAnimationInfo.mSharedElementReturnTransition;
         }

         return var2;
      }
   }

   ArrayList getSharedElementSourceNames() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 != null && var1.mSharedElementSourceNames != null ? this.mAnimationInfo.mSharedElementSourceNames : new ArrayList();
   }

   ArrayList getSharedElementTargetNames() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 != null && var1.mSharedElementTargetNames != null ? this.mAnimationInfo.mSharedElementTargetNames : new ArrayList();
   }

   public final String getString(int var1) {
      return this.getResources().getString(var1);
   }

   public final String getString(int var1, Object... var2) {
      return this.getResources().getString(var1, var2);
   }

   public final String getTag() {
      return this.mTag;
   }

   @Deprecated
   public final Fragment getTargetFragment() {
      Fragment var1 = this.mTarget;
      if (var1 != null) {
         return var1;
      } else {
         FragmentManager var2 = this.mFragmentManager;
         if (var2 != null) {
            String var3 = this.mTargetWho;
            if (var3 != null) {
               return var2.findActiveFragment(var3);
            }
         }

         return null;
      }
   }

   @Deprecated
   public final int getTargetRequestCode() {
      return this.mTargetRequestCode;
   }

   public final CharSequence getText(int var1) {
      return this.getResources().getText(var1);
   }

   @Deprecated
   public boolean getUserVisibleHint() {
      return this.mUserVisibleHint;
   }

   public View getView() {
      return this.mView;
   }

   public LifecycleOwner getViewLifecycleOwner() {
      FragmentViewLifecycleOwner var1 = this.mViewLifecycleOwner;
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
      }
   }

   public LiveData getViewLifecycleOwnerLiveData() {
      return this.mViewLifecycleOwnerLiveData;
   }

   public ViewModelStore getViewModelStore() {
      if (this.mFragmentManager != null) {
         if (this.getMinimumMaxLifecycleState() != Lifecycle.State.INITIALIZED.ordinal()) {
            return this.mFragmentManager.getViewModelStore(this);
         } else {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
         }
      } else {
         throw new IllegalStateException("Can't access ViewModels from detached fragment");
      }
   }

   public final boolean hasOptionsMenu() {
      return this.mHasMenu;
   }

   public final int hashCode() {
      return super.hashCode();
   }

   void initState() {
      this.initLifecycle();
      this.mWho = UUID.randomUUID().toString();
      this.mAdded = false;
      this.mRemoving = false;
      this.mFromLayout = false;
      this.mInLayout = false;
      this.mRestored = false;
      this.mBackStackNesting = 0;
      this.mFragmentManager = null;
      this.mChildFragmentManager = new FragmentManagerImpl();
      this.mHost = null;
      this.mFragmentId = 0;
      this.mContainerId = 0;
      this.mTag = null;
      this.mHidden = false;
      this.mDetached = false;
   }

   public final boolean isAdded() {
      boolean var1;
      if (this.mHost != null && this.mAdded) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isDetached() {
      return this.mDetached;
   }

   public final boolean isHidden() {
      return this.mHidden;
   }

   boolean isHideReplaced() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? false : var1.mIsHideReplaced;
   }

   final boolean isInBackStack() {
      boolean var1;
      if (this.mBackStackNesting > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isInLayout() {
      return this.mInLayout;
   }

   public final boolean isMenuVisible() {
      boolean var1;
      if (this.mMenuVisible) {
         FragmentManager var2 = this.mFragmentManager;
         if (var2 == null || var2.isParentMenuVisible(this.mParentFragment)) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   boolean isPostponed() {
      AnimationInfo var1 = this.mAnimationInfo;
      return var1 == null ? false : var1.mEnterTransitionPostponed;
   }

   public final boolean isRemoving() {
      return this.mRemoving;
   }

   final boolean isRemovingParent() {
      Fragment var2 = this.getParentFragment();
      boolean var1;
      if (var2 == null || !var2.isRemoving() && !var2.isRemovingParent()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public final boolean isResumed() {
      boolean var1;
      if (this.mState >= 7) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isStateSaved() {
      FragmentManager var1 = this.mFragmentManager;
      return var1 == null ? false : var1.isStateSaved();
   }

   public final boolean isVisible() {
      boolean var1;
      if (this.isAdded() && !this.isHidden()) {
         View var2 = this.mView;
         if (var2 != null && var2.getWindowToken() != null && this.mView.getVisibility() == 0) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   void noteStateNotSaved() {
      this.mChildFragmentManager.noteStateNotSaved();
   }

   @Deprecated
   public void onActivityCreated(Bundle var1) {
      this.mCalled = true;
   }

   @Deprecated
   public void onActivityResult(int var1, int var2, Intent var3) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + var1 + " resultCode: " + var2 + " data: " + var3);
      }

   }

   @Deprecated
   public void onAttach(Activity var1) {
      this.mCalled = true;
   }

   public void onAttach(Context var1) {
      this.mCalled = true;
      FragmentHostCallback var2 = this.mHost;
      Activity var3;
      if (var2 == null) {
         var3 = null;
      } else {
         var3 = var2.getActivity();
      }

      if (var3 != null) {
         this.mCalled = false;
         this.onAttach(var3);
      }

   }

   @Deprecated
   public void onAttachFragment(Fragment var1) {
   }

   public void onConfigurationChanged(Configuration var1) {
      this.mCalled = true;
   }

   public boolean onContextItemSelected(MenuItem var1) {
      return false;
   }

   public void onCreate(Bundle var1) {
      this.mCalled = true;
      this.restoreChildFragmentState(var1);
      if (!this.mChildFragmentManager.isStateAtLeast(1)) {
         this.mChildFragmentManager.dispatchCreate();
      }

   }

   public Animation onCreateAnimation(int var1, boolean var2, int var3) {
      return null;
   }

   public Animator onCreateAnimator(int var1, boolean var2, int var3) {
      return null;
   }

   public void onCreateContextMenu(ContextMenu var1, View var2, ContextMenu.ContextMenuInfo var3) {
      this.requireActivity().onCreateContextMenu(var1, var2, var3);
   }

   public void onCreateOptionsMenu(Menu var1, MenuInflater var2) {
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      int var4 = this.mContentLayoutId;
      return var4 != 0 ? var1.inflate(var4, var2, false) : null;
   }

   public void onDestroy() {
      this.mCalled = true;
   }

   public void onDestroyOptionsMenu() {
   }

   public void onDestroyView() {
      this.mCalled = true;
   }

   public void onDetach() {
      this.mCalled = true;
   }

   public LayoutInflater onGetLayoutInflater(Bundle var1) {
      return this.getLayoutInflater(var1);
   }

   public void onHiddenChanged(boolean var1) {
   }

   @Deprecated
   public void onInflate(Activity var1, AttributeSet var2, Bundle var3) {
      this.mCalled = true;
   }

   public void onInflate(Context var1, AttributeSet var2, Bundle var3) {
      this.mCalled = true;
      FragmentHostCallback var4 = this.mHost;
      Activity var5;
      if (var4 == null) {
         var5 = null;
      } else {
         var5 = var4.getActivity();
      }

      if (var5 != null) {
         this.mCalled = false;
         this.onInflate(var5, var2, var3);
      }

   }

   public void onLowMemory() {
      this.mCalled = true;
   }

   public void onMultiWindowModeChanged(boolean var1) {
   }

   public boolean onOptionsItemSelected(MenuItem var1) {
      return false;
   }

   public void onOptionsMenuClosed(Menu var1) {
   }

   public void onPause() {
      this.mCalled = true;
   }

   public void onPictureInPictureModeChanged(boolean var1) {
   }

   public void onPrepareOptionsMenu(Menu var1) {
   }

   public void onPrimaryNavigationFragmentChanged(boolean var1) {
   }

   @Deprecated
   public void onRequestPermissionsResult(int var1, String[] var2, int[] var3) {
   }

   public void onResume() {
      this.mCalled = true;
   }

   public void onSaveInstanceState(Bundle var1) {
   }

   public void onStart() {
      this.mCalled = true;
   }

   public void onStop() {
      this.mCalled = true;
   }

   public void onViewCreated(View var1, Bundle var2) {
   }

   public void onViewStateRestored(Bundle var1) {
      this.mCalled = true;
   }

   void performActivityCreated(Bundle var1) {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mState = 3;
      this.mCalled = false;
      this.onActivityCreated(var1);
      if (this.mCalled) {
         this.restoreViewState();
         this.mChildFragmentManager.dispatchActivityCreated();
      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
      }
   }

   void performAttach() {
      Iterator var1 = this.mOnPreAttachedListeners.iterator();

      while(var1.hasNext()) {
         ((OnPreAttachedListener)var1.next()).onPreAttached();
      }

      this.mOnPreAttachedListeners.clear();
      this.mChildFragmentManager.attachController(this.mHost, this.createFragmentContainer(), this);
      this.mState = 0;
      this.mCalled = false;
      this.onAttach(this.mHost.getContext());
      if (this.mCalled) {
         this.mFragmentManager.dispatchOnAttachFragment(this);
         this.mChildFragmentManager.dispatchAttach();
      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
      }
   }

   void performConfigurationChanged(Configuration var1) {
      this.onConfigurationChanged(var1);
      this.mChildFragmentManager.dispatchConfigurationChanged(var1);
   }

   boolean performContextItemSelected(MenuItem var1) {
      if (!this.mHidden) {
         return this.onContextItemSelected(var1) ? true : this.mChildFragmentManager.dispatchContextItemSelected(var1);
      } else {
         return false;
      }
   }

   void performCreate(Bundle var1) {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mState = 1;
      this.mCalled = false;
      if (VERSION.SDK_INT >= 19) {
         this.mLifecycleRegistry.addObserver(new LifecycleEventObserver(this) {
            final Fragment this$0;

            {
               this.this$0 = var1;
            }

            public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
               if (var2 == Lifecycle.Event.ON_STOP && this.this$0.mView != null) {
                  this.this$0.mView.cancelPendingInputEvents();
               }

            }
         });
      }

      this.mSavedStateRegistryController.performRestore(var1);
      this.onCreate(var1);
      this.mIsCreated = true;
      if (this.mCalled) {
         this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
      }
   }

   boolean performCreateOptionsMenu(Menu var1, MenuInflater var2) {
      boolean var6 = this.mHidden;
      boolean var5 = false;
      boolean var4 = false;
      if (!var6) {
         boolean var3 = var4;
         if (this.mHasMenu) {
            var3 = var4;
            if (this.mMenuVisible) {
               var3 = true;
               this.onCreateOptionsMenu(var1, var2);
            }
         }

         var5 = var3 | this.mChildFragmentManager.dispatchCreateOptionsMenu(var1, var2);
      }

      return var5;
   }

   void performCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mPerformedCreateView = true;
      this.mViewLifecycleOwner = new FragmentViewLifecycleOwner(this, this.getViewModelStore());
      View var4 = this.onCreateView(var1, var2, var3);
      this.mView = var4;
      if (var4 != null) {
         this.mViewLifecycleOwner.initialize();
         ViewTreeLifecycleOwner.set(this.mView, this.mViewLifecycleOwner);
         ViewTreeViewModelStoreOwner.set(this.mView, this.mViewLifecycleOwner);
         ViewTreeSavedStateRegistryOwner.set(this.mView, this.mViewLifecycleOwner);
         this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
      } else {
         if (this.mViewLifecycleOwner.isInitialized()) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
         }

         this.mViewLifecycleOwner = null;
      }

   }

   void performDestroy() {
      this.mChildFragmentManager.dispatchDestroy();
      this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
      this.mState = 0;
      this.mCalled = false;
      this.mIsCreated = false;
      this.onDestroy();
      if (!this.mCalled) {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
      }
   }

   void performDestroyView() {
      this.mChildFragmentManager.dispatchDestroyView();
      if (this.mView != null && this.mViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
         this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
      }

      this.mState = 1;
      this.mCalled = false;
      this.onDestroyView();
      if (this.mCalled) {
         LoaderManager.getInstance(this).markForRedelivery();
         this.mPerformedCreateView = false;
      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
      }
   }

   void performDetach() {
      this.mState = -1;
      this.mCalled = false;
      this.onDetach();
      this.mLayoutInflater = null;
      if (this.mCalled) {
         if (!this.mChildFragmentManager.isDestroyed()) {
            this.mChildFragmentManager.dispatchDestroy();
            this.mChildFragmentManager = new FragmentManagerImpl();
         }

      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
      }
   }

   LayoutInflater performGetLayoutInflater(Bundle var1) {
      LayoutInflater var2 = this.onGetLayoutInflater(var1);
      this.mLayoutInflater = var2;
      return var2;
   }

   void performLowMemory() {
      this.onLowMemory();
      this.mChildFragmentManager.dispatchLowMemory();
   }

   void performMultiWindowModeChanged(boolean var1) {
      this.onMultiWindowModeChanged(var1);
      this.mChildFragmentManager.dispatchMultiWindowModeChanged(var1);
   }

   boolean performOptionsItemSelected(MenuItem var1) {
      if (!this.mHidden) {
         return this.mHasMenu && this.mMenuVisible && this.onOptionsItemSelected(var1) ? true : this.mChildFragmentManager.dispatchOptionsItemSelected(var1);
      } else {
         return false;
      }
   }

   void performOptionsMenuClosed(Menu var1) {
      if (!this.mHidden) {
         if (this.mHasMenu && this.mMenuVisible) {
            this.onOptionsMenuClosed(var1);
         }

         this.mChildFragmentManager.dispatchOptionsMenuClosed(var1);
      }

   }

   void performPause() {
      this.mChildFragmentManager.dispatchPause();
      if (this.mView != null) {
         this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
      }

      this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
      this.mState = 6;
      this.mCalled = false;
      this.onPause();
      if (!this.mCalled) {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
      }
   }

   void performPictureInPictureModeChanged(boolean var1) {
      this.onPictureInPictureModeChanged(var1);
      this.mChildFragmentManager.dispatchPictureInPictureModeChanged(var1);
   }

   boolean performPrepareOptionsMenu(Menu var1) {
      boolean var5 = this.mHidden;
      boolean var4 = false;
      boolean var3 = false;
      if (!var5) {
         boolean var2 = var3;
         if (this.mHasMenu) {
            var2 = var3;
            if (this.mMenuVisible) {
               var2 = true;
               this.onPrepareOptionsMenu(var1);
            }
         }

         var4 = var2 | this.mChildFragmentManager.dispatchPrepareOptionsMenu(var1);
      }

      return var4;
   }

   void performPrimaryNavigationFragmentChanged() {
      boolean var1 = this.mFragmentManager.isPrimaryNavigation(this);
      Boolean var2 = this.mIsPrimaryNavigationFragment;
      if (var2 == null || var2 != var1) {
         this.mIsPrimaryNavigationFragment = var1;
         this.onPrimaryNavigationFragmentChanged(var1);
         this.mChildFragmentManager.dispatchPrimaryNavigationFragmentChanged();
      }

   }

   void performResume() {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions(true);
      this.mState = 7;
      this.mCalled = false;
      this.onResume();
      if (this.mCalled) {
         this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
         if (this.mView != null) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
         }

         this.mChildFragmentManager.dispatchResume();
      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
      }
   }

   void performSaveInstanceState(Bundle var1) {
      this.onSaveInstanceState(var1);
      this.mSavedStateRegistryController.performSave(var1);
      Parcelable var2 = this.mChildFragmentManager.saveAllState();
      if (var2 != null) {
         var1.putParcelable("android:support:fragments", var2);
      }

   }

   void performStart() {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions(true);
      this.mState = 5;
      this.mCalled = false;
      this.onStart();
      if (this.mCalled) {
         this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
         if (this.mView != null) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_START);
         }

         this.mChildFragmentManager.dispatchStart();
      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
      }
   }

   void performStop() {
      this.mChildFragmentManager.dispatchStop();
      if (this.mView != null) {
         this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
      }

      this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
      this.mState = 4;
      this.mCalled = false;
      this.onStop();
      if (!this.mCalled) {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
      }
   }

   void performViewCreated() {
      this.onViewCreated(this.mView, this.mSavedFragmentState);
      this.mChildFragmentManager.dispatchViewCreated();
   }

   public void postponeEnterTransition() {
      this.ensureAnimationInfo().mEnterTransitionPostponed = true;
   }

   public final void postponeEnterTransition(long var1, TimeUnit var3) {
      this.ensureAnimationInfo().mEnterTransitionPostponed = true;
      FragmentManager var4 = this.mFragmentManager;
      Handler var5;
      if (var4 != null) {
         var5 = var4.getHost().getHandler();
      } else {
         var5 = new Handler(Looper.getMainLooper());
      }

      var5.removeCallbacks(this.mPostponedDurationRunnable);
      var5.postDelayed(this.mPostponedDurationRunnable, var3.toMillis(var1));
   }

   public final ActivityResultLauncher registerForActivityResult(ActivityResultContract var1, ActivityResultCallback var2) {
      return this.prepareCallInternal(var1, new Function(this) {
         final Fragment this$0;

         {
            this.this$0 = var1;
         }

         public ActivityResultRegistry apply(Void var1) {
            return this.this$0.mHost instanceof ActivityResultRegistryOwner ? ((ActivityResultRegistryOwner)this.this$0.mHost).getActivityResultRegistry() : this.this$0.requireActivity().getActivityResultRegistry();
         }
      }, var2);
   }

   public final ActivityResultLauncher registerForActivityResult(ActivityResultContract var1, ActivityResultRegistry var2, ActivityResultCallback var3) {
      return this.prepareCallInternal(var1, new Function(this, var2) {
         final Fragment this$0;
         final ActivityResultRegistry val$registry;

         {
            this.this$0 = var1;
            this.val$registry = var2;
         }

         public ActivityResultRegistry apply(Void var1) {
            return this.val$registry;
         }
      }, var3);
   }

   public void registerForContextMenu(View var1) {
      var1.setOnCreateContextMenuListener(this);
   }

   @Deprecated
   public final void requestPermissions(String[] var1, int var2) {
      if (this.mHost != null) {
         this.getParentFragmentManager().launchRequestPermissions(this, var1, var2);
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to Activity");
      }
   }

   public final FragmentActivity requireActivity() {
      FragmentActivity var1 = this.getActivity();
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
      }
   }

   public final Bundle requireArguments() {
      Bundle var1 = this.getArguments();
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Fragment " + this + " does not have any arguments.");
      }
   }

   public final Context requireContext() {
      Context var1 = this.getContext();
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to a context.");
      }
   }

   @Deprecated
   public final FragmentManager requireFragmentManager() {
      return this.getParentFragmentManager();
   }

   public final Object requireHost() {
      Object var1 = this.getHost();
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to a host.");
      }
   }

   public final Fragment requireParentFragment() {
      Fragment var1 = this.getParentFragment();
      if (var1 == null) {
         if (this.getContext() == null) {
            throw new IllegalStateException("Fragment " + this + " is not attached to any Fragment or host");
         } else {
            throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + this.getContext());
         }
      } else {
         return var1;
      }
   }

   public final View requireView() {
      View var1 = this.getView();
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
      }
   }

   void restoreChildFragmentState(Bundle var1) {
      if (var1 != null) {
         Parcelable var2 = var1.getParcelable("android:support:fragments");
         if (var2 != null) {
            this.mChildFragmentManager.restoreSaveState(var2);
            this.mChildFragmentManager.dispatchCreate();
         }
      }

   }

   final void restoreViewState(Bundle var1) {
      SparseArray var2 = this.mSavedViewState;
      if (var2 != null) {
         this.mView.restoreHierarchyState(var2);
         this.mSavedViewState = null;
      }

      if (this.mView != null) {
         this.mViewLifecycleOwner.performRestore(this.mSavedViewRegistryState);
         this.mSavedViewRegistryState = null;
      }

      this.mCalled = false;
      this.onViewStateRestored(var1);
      if (this.mCalled) {
         if (this.mView != null) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
         }

      } else {
         throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
      }
   }

   public void setAllowEnterTransitionOverlap(boolean var1) {
      this.ensureAnimationInfo().mAllowEnterTransitionOverlap = var1;
   }

   public void setAllowReturnTransitionOverlap(boolean var1) {
      this.ensureAnimationInfo().mAllowReturnTransitionOverlap = var1;
   }

   void setAnimatingAway(View var1) {
      this.ensureAnimationInfo().mAnimatingAway = var1;
   }

   void setAnimations(int var1, int var2, int var3, int var4) {
      if (this.mAnimationInfo != null || var1 != 0 || var2 != 0 || var3 != 0 || var4 != 0) {
         this.ensureAnimationInfo().mEnterAnim = var1;
         this.ensureAnimationInfo().mExitAnim = var2;
         this.ensureAnimationInfo().mPopEnterAnim = var3;
         this.ensureAnimationInfo().mPopExitAnim = var4;
      }
   }

   void setAnimator(Animator var1) {
      this.ensureAnimationInfo().mAnimator = var1;
   }

   public void setArguments(Bundle var1) {
      if (this.mFragmentManager != null && this.isStateSaved()) {
         throw new IllegalStateException("Fragment already added and state has been saved");
      } else {
         this.mArguments = var1;
      }
   }

   public void setEnterSharedElementCallback(SharedElementCallback var1) {
      this.ensureAnimationInfo().mEnterTransitionCallback = var1;
   }

   public void setEnterTransition(Object var1) {
      this.ensureAnimationInfo().mEnterTransition = var1;
   }

   public void setExitSharedElementCallback(SharedElementCallback var1) {
      this.ensureAnimationInfo().mExitTransitionCallback = var1;
   }

   public void setExitTransition(Object var1) {
      this.ensureAnimationInfo().mExitTransition = var1;
   }

   void setFocusedView(View var1) {
      this.ensureAnimationInfo().mFocusedView = var1;
   }

   public void setHasOptionsMenu(boolean var1) {
      if (this.mHasMenu != var1) {
         this.mHasMenu = var1;
         if (this.isAdded() && !this.isHidden()) {
            this.mHost.onSupportInvalidateOptionsMenu();
         }
      }

   }

   void setHideReplaced(boolean var1) {
      this.ensureAnimationInfo().mIsHideReplaced = var1;
   }

   public void setInitialSavedState(SavedState var1) {
      if (this.mFragmentManager != null) {
         throw new IllegalStateException("Fragment already added");
      } else {
         Bundle var2;
         if (var1 != null && var1.mState != null) {
            var2 = var1.mState;
         } else {
            var2 = null;
         }

         this.mSavedFragmentState = var2;
      }
   }

   public void setMenuVisibility(boolean var1) {
      if (this.mMenuVisible != var1) {
         this.mMenuVisible = var1;
         if (this.mHasMenu && this.isAdded() && !this.isHidden()) {
            this.mHost.onSupportInvalidateOptionsMenu();
         }
      }

   }

   void setNextTransition(int var1) {
      if (this.mAnimationInfo != null || var1 != 0) {
         this.ensureAnimationInfo();
         this.mAnimationInfo.mNextTransition = var1;
      }
   }

   void setOnStartEnterTransitionListener(OnStartEnterTransitionListener var1) {
      this.ensureAnimationInfo();
      if (var1 != this.mAnimationInfo.mStartEnterTransitionListener) {
         if (var1 != null && this.mAnimationInfo.mStartEnterTransitionListener != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
         } else {
            if (this.mAnimationInfo.mEnterTransitionPostponed) {
               this.mAnimationInfo.mStartEnterTransitionListener = var1;
            }

            if (var1 != null) {
               var1.startListening();
            }

         }
      }
   }

   void setPopDirection(boolean var1) {
      if (this.mAnimationInfo != null) {
         this.ensureAnimationInfo().mIsPop = var1;
      }
   }

   void setPostOnViewCreatedAlpha(float var1) {
      this.ensureAnimationInfo().mPostOnViewCreatedAlpha = var1;
   }

   public void setReenterTransition(Object var1) {
      this.ensureAnimationInfo().mReenterTransition = var1;
   }

   @Deprecated
   public void setRetainInstance(boolean var1) {
      this.mRetainInstance = var1;
      FragmentManager var2 = this.mFragmentManager;
      if (var2 != null) {
         if (var1) {
            var2.addRetainedFragment(this);
         } else {
            var2.removeRetainedFragment(this);
         }
      } else {
         this.mRetainInstanceChangedWhileDetached = true;
      }

   }

   public void setReturnTransition(Object var1) {
      this.ensureAnimationInfo().mReturnTransition = var1;
   }

   public void setSharedElementEnterTransition(Object var1) {
      this.ensureAnimationInfo().mSharedElementEnterTransition = var1;
   }

   void setSharedElementNames(ArrayList var1, ArrayList var2) {
      this.ensureAnimationInfo();
      this.mAnimationInfo.mSharedElementSourceNames = var1;
      this.mAnimationInfo.mSharedElementTargetNames = var2;
   }

   public void setSharedElementReturnTransition(Object var1) {
      this.ensureAnimationInfo().mSharedElementReturnTransition = var1;
   }

   @Deprecated
   public void setTargetFragment(Fragment var1, int var2) {
      FragmentManager var4 = this.mFragmentManager;
      FragmentManager var3;
      if (var1 != null) {
         var3 = var1.mFragmentManager;
      } else {
         var3 = null;
      }

      if (var4 != null && var3 != null && var4 != var3) {
         throw new IllegalArgumentException("Fragment " + var1 + " must share the same FragmentManager to be set as a target fragment");
      } else {
         for(Fragment var5 = var1; var5 != null; var5 = var5.getTargetFragment()) {
            if (var5.equals(this)) {
               throw new IllegalArgumentException("Setting " + var1 + " as the target of " + this + " would create a target cycle");
            }
         }

         if (var1 == null) {
            this.mTargetWho = null;
            this.mTarget = null;
         } else if (this.mFragmentManager != null && var1.mFragmentManager != null) {
            this.mTargetWho = var1.mWho;
            this.mTarget = null;
         } else {
            this.mTargetWho = null;
            this.mTarget = var1;
         }

         this.mTargetRequestCode = var2;
      }
   }

   @Deprecated
   public void setUserVisibleHint(boolean var1) {
      if (!this.mUserVisibleHint && var1 && this.mState < 5 && this.mFragmentManager != null && this.isAdded() && this.mIsCreated) {
         FragmentManager var3 = this.mFragmentManager;
         var3.performPendingDeferredStart(var3.createOrGetFragmentStateManager(this));
      }

      this.mUserVisibleHint = var1;
      boolean var2;
      if (this.mState < 5 && !var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.mDeferStart = var2;
      if (this.mSavedFragmentState != null) {
         this.mSavedUserVisibleHint = var1;
      }

   }

   public boolean shouldShowRequestPermissionRationale(String var1) {
      FragmentHostCallback var2 = this.mHost;
      return var2 != null ? var2.onShouldShowRequestPermissionRationale(var1) : false;
   }

   public void startActivity(Intent var1) {
      this.startActivity(var1, (Bundle)null);
   }

   public void startActivity(Intent var1, Bundle var2) {
      FragmentHostCallback var3 = this.mHost;
      if (var3 != null) {
         var3.onStartActivityFromFragment(this, var1, -1, var2);
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to Activity");
      }
   }

   @Deprecated
   public void startActivityForResult(Intent var1, int var2) {
      this.startActivityForResult(var1, var2, (Bundle)null);
   }

   @Deprecated
   public void startActivityForResult(Intent var1, int var2, Bundle var3) {
      if (this.mHost != null) {
         this.getParentFragmentManager().launchStartActivityForResult(this, var1, var2, var3);
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to Activity");
      }
   }

   @Deprecated
   public void startIntentSenderForResult(IntentSender var1, int var2, Intent var3, int var4, int var5, int var6, Bundle var7) throws IntentSender.SendIntentException {
      if (this.mHost != null) {
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in startIntentSenderForResult() requestCode: " + var2 + " IntentSender: " + var1 + " fillInIntent: " + var3 + " options: " + var7);
         }

         this.getParentFragmentManager().launchStartIntentSenderForResult(this, var1, var2, var3, var4, var5, var6, var7);
      } else {
         throw new IllegalStateException("Fragment " + this + " not attached to Activity");
      }
   }

   public void startPostponedEnterTransition() {
      if (this.mAnimationInfo != null && this.ensureAnimationInfo().mEnterTransitionPostponed) {
         if (this.mHost == null) {
            this.ensureAnimationInfo().mEnterTransitionPostponed = false;
         } else if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            this.mHost.getHandler().postAtFrontOfQueue(new Runnable(this) {
               final Fragment this$0;

               {
                  this.this$0 = var1;
               }

               public void run() {
                  this.this$0.callStartTransitionListener(false);
               }
            });
         } else {
            this.callStartTransitionListener(true);
         }
      }

   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(128);
      var1.append(this.getClass().getSimpleName());
      var1.append("{");
      var1.append(Integer.toHexString(System.identityHashCode(this)));
      var1.append("}");
      var1.append(" (");
      var1.append(this.mWho);
      if (this.mFragmentId != 0) {
         var1.append(" id=0x");
         var1.append(Integer.toHexString(this.mFragmentId));
      }

      if (this.mTag != null) {
         var1.append(" tag=");
         var1.append(this.mTag);
      }

      var1.append(")");
      return var1.toString();
   }

   public void unregisterForContextMenu(View var1) {
      var1.setOnCreateContextMenuListener((View.OnCreateContextMenuListener)null);
   }

   static class AnimationInfo {
      Boolean mAllowEnterTransitionOverlap;
      Boolean mAllowReturnTransitionOverlap;
      View mAnimatingAway;
      Animator mAnimator;
      int mEnterAnim;
      Object mEnterTransition = null;
      SharedElementCallback mEnterTransitionCallback;
      boolean mEnterTransitionPostponed;
      int mExitAnim;
      Object mExitTransition;
      SharedElementCallback mExitTransitionCallback;
      View mFocusedView;
      boolean mIsHideReplaced;
      boolean mIsPop;
      int mNextTransition;
      int mPopEnterAnim;
      int mPopExitAnim;
      float mPostOnViewCreatedAlpha;
      Object mReenterTransition;
      Object mReturnTransition;
      Object mSharedElementEnterTransition;
      Object mSharedElementReturnTransition;
      ArrayList mSharedElementSourceNames;
      ArrayList mSharedElementTargetNames;
      OnStartEnterTransitionListener mStartEnterTransitionListener;

      AnimationInfo() {
         this.mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
         this.mExitTransition = null;
         this.mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
         this.mSharedElementEnterTransition = null;
         this.mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
         this.mEnterTransitionCallback = null;
         this.mExitTransitionCallback = null;
         this.mPostOnViewCreatedAlpha = 1.0F;
         this.mFocusedView = null;
      }
   }

   public static class InstantiationException extends RuntimeException {
      public InstantiationException(String var1, Exception var2) {
         super(var1, var2);
      }
   }

   private abstract static class OnPreAttachedListener {
      private OnPreAttachedListener() {
      }

      // $FF: synthetic method
      OnPreAttachedListener(Object var1) {
         this();
      }

      abstract void onPreAttached();
   }

   interface OnStartEnterTransitionListener {
      void onStartEnterTransition();

      void startListening();
   }

   public static class SavedState implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1, (ClassLoader)null);
         }

         public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
            return new SavedState(var1, var2);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      final Bundle mState;

      SavedState(Bundle var1) {
         this.mState = var1;
      }

      SavedState(Parcel var1, ClassLoader var2) {
         Bundle var3 = var1.readBundle();
         this.mState = var3;
         if (var2 != null && var3 != null) {
            var3.setClassLoader(var2);
         }

      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeBundle(this.mState);
      }
   }
}
