package androidx.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.contextaware.ContextAware;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.tracing.Trace;
import java.util.concurrent.atomic.AtomicInteger;

public class ComponentActivity extends androidx.core.app.ComponentActivity implements ContextAware, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, ActivityResultCaller {
   private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
   private final ActivityResultRegistry mActivityResultRegistry;
   private int mContentLayoutId;
   final ContextAwareHelper mContextAwareHelper;
   private ViewModelProvider.Factory mDefaultFactory;
   private final LifecycleRegistry mLifecycleRegistry;
   private final AtomicInteger mNextLocalRequestCode;
   private final OnBackPressedDispatcher mOnBackPressedDispatcher;
   final SavedStateRegistryController mSavedStateRegistryController;
   private ViewModelStore mViewModelStore;

   public ComponentActivity() {
      this.mContextAwareHelper = new ContextAwareHelper();
      this.mLifecycleRegistry = new LifecycleRegistry(this);
      this.mSavedStateRegistryController = SavedStateRegistryController.create(this);
      this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new Runnable(this) {
         final ComponentActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            try {
               this.this$0.onBackPressed();
            } catch (IllegalStateException var2) {
               if (!TextUtils.equals(var2.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                  throw var2;
               }
            }

         }
      });
      this.mNextLocalRequestCode = new AtomicInteger();
      this.mActivityResultRegistry = new ActivityResultRegistry(this) {
         final ComponentActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onLaunch(int var1, ActivityResultContract var2, Object var3, ActivityOptionsCompat var4) {
            ComponentActivity var5 = this.this$0;
            ActivityResultContract.SynchronousResult var6 = var2.getSynchronousResult(var5, var3);
            if (var6 != null) {
               (new Handler(Looper.getMainLooper())).post(new Runnable(this, var1, var6) {
                  final <undefinedtype> this$1;
                  final int val$requestCode;
                  final ActivityResultContract.SynchronousResult val$synchronousResult;

                  {
                     this.this$1 = var1;
                     this.val$requestCode = var2;
                     this.val$synchronousResult = var3;
                  }

                  public void run() {
                     this.this$1.dispatchResult(this.val$requestCode, this.val$synchronousResult.getValue());
                  }
               });
            } else {
               Intent var9 = var2.createIntent(var5, var3);
               Bundle var8 = null;
               if (var9.getExtras() != null && var9.getExtras().getClassLoader() == null) {
                  var9.setExtrasClassLoader(var5.getClassLoader());
               }

               if (var9.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                  var8 = var9.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                  var9.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
               } else if (var4 != null) {
                  var8 = var4.toBundle();
               }

               if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(var9.getAction())) {
                  String[] var11 = var9.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                  String[] var10 = var11;
                  if (var11 == null) {
                     var10 = new String[0];
                  }

                  ActivityCompat.requestPermissions(var5, var10, var1);
               } else if ("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(var9.getAction())) {
                  IntentSenderRequest var12 = (IntentSenderRequest)var9.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");

                  try {
                     ActivityCompat.startIntentSenderForResult(var5, var12.getIntentSender(), var1, var12.getFillInIntent(), var12.getFlagsMask(), var12.getFlagsValues(), 0, var8);
                  } catch (IntentSender.SendIntentException var7) {
                     (new Handler(Looper.getMainLooper())).post(new Runnable(this, var1, var7) {
                        final <undefinedtype> this$1;
                        final IntentSender.SendIntentException val$e;
                        final int val$requestCode;

                        {
                           this.this$1 = var1;
                           this.val$requestCode = var2;
                           this.val$e = var3;
                        }

                        public void run() {
                           this.this$1.dispatchResult(this.val$requestCode, 0, (new Intent()).setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", this.val$e));
                        }
                     });
                  }
               } else {
                  ActivityCompat.startActivityForResult(var5, var9, var1, var8);
               }

            }
         }
      };
      if (this.getLifecycle() != null) {
         if (VERSION.SDK_INT >= 19) {
            this.getLifecycle().addObserver(new LifecycleEventObserver(this) {
               final ComponentActivity this$0;

               {
                  this.this$0 = var1;
               }

               public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
                  if (var2 == Lifecycle.Event.ON_STOP) {
                     Window var3 = this.this$0.getWindow();
                     View var4;
                     if (var3 != null) {
                        var4 = var3.peekDecorView();
                     } else {
                        var4 = null;
                     }

                     if (var4 != null) {
                        var4.cancelPendingInputEvents();
                     }
                  }

               }
            });
         }

         this.getLifecycle().addObserver(new LifecycleEventObserver(this) {
            final ComponentActivity this$0;

            {
               this.this$0 = var1;
            }

            public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
               if (var2 == Lifecycle.Event.ON_DESTROY) {
                  this.this$0.mContextAwareHelper.clearAvailableContext();
                  if (!this.this$0.isChangingConfigurations()) {
                     this.this$0.getViewModelStore().clear();
                  }
               }

            }
         });
         this.getLifecycle().addObserver(new LifecycleEventObserver(this) {
            final ComponentActivity this$0;

            {
               this.this$0 = var1;
            }

            public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
               this.this$0.ensureViewModelStore();
               this.this$0.getLifecycle().removeObserver(this);
            }
         });
         if (19 <= VERSION.SDK_INT && VERSION.SDK_INT <= 23) {
            this.getLifecycle().addObserver(new ImmLeaksCleaner(this));
         }

         this.getSavedStateRegistry().registerSavedStateProvider("android:support:activity-result", new SavedStateRegistry.SavedStateProvider(this) {
            final ComponentActivity this$0;

            {
               this.this$0 = var1;
            }

            public Bundle saveState() {
               Bundle var1 = new Bundle();
               this.this$0.mActivityResultRegistry.onSaveInstanceState(var1);
               return var1;
            }
         });
         this.addOnContextAvailableListener(new OnContextAvailableListener(this) {
            final ComponentActivity this$0;

            {
               this.this$0 = var1;
            }

            public void onContextAvailable(Context var1) {
               Bundle var2 = this.this$0.getSavedStateRegistry().consumeRestoredStateForKey("android:support:activity-result");
               if (var2 != null) {
                  this.this$0.mActivityResultRegistry.onRestoreInstanceState(var2);
               }

            }
         });
      } else {
         throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
      }
   }

   public ComponentActivity(int var1) {
      this();
      this.mContentLayoutId = var1;
   }

   private void initViewTreeOwners() {
      ViewTreeLifecycleOwner.set(this.getWindow().getDecorView(), this);
      ViewTreeViewModelStoreOwner.set(this.getWindow().getDecorView(), this);
      ViewTreeSavedStateRegistryOwner.set(this.getWindow().getDecorView(), this);
   }

   public void addContentView(View var1, ViewGroup.LayoutParams var2) {
      this.initViewTreeOwners();
      super.addContentView(var1, var2);
   }

   public final void addOnContextAvailableListener(OnContextAvailableListener var1) {
      this.mContextAwareHelper.addOnContextAvailableListener(var1);
   }

   void ensureViewModelStore() {
      if (this.mViewModelStore == null) {
         NonConfigurationInstances var1 = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
         if (var1 != null) {
            this.mViewModelStore = var1.viewModelStore;
         }

         if (this.mViewModelStore == null) {
            this.mViewModelStore = new ViewModelStore();
         }
      }

   }

   public final ActivityResultRegistry getActivityResultRegistry() {
      return this.mActivityResultRegistry;
   }

   public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
      if (this.getApplication() != null) {
         if (this.mDefaultFactory == null) {
            Application var2 = this.getApplication();
            Bundle var1;
            if (this.getIntent() != null) {
               var1 = this.getIntent().getExtras();
            } else {
               var1 = null;
            }

            this.mDefaultFactory = new SavedStateViewModelFactory(var2, this, var1);
         }

         return this.mDefaultFactory;
      } else {
         throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
      }
   }

   @Deprecated
   public Object getLastCustomNonConfigurationInstance() {
      NonConfigurationInstances var1 = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
      Object var2;
      if (var1 != null) {
         var2 = var1.custom;
      } else {
         var2 = null;
      }

      return var2;
   }

   public Lifecycle getLifecycle() {
      return this.mLifecycleRegistry;
   }

   public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
      return this.mOnBackPressedDispatcher;
   }

   public final SavedStateRegistry getSavedStateRegistry() {
      return this.mSavedStateRegistryController.getSavedStateRegistry();
   }

   public ViewModelStore getViewModelStore() {
      if (this.getApplication() != null) {
         this.ensureViewModelStore();
         return this.mViewModelStore;
      } else {
         throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
      }
   }

   @Deprecated
   protected void onActivityResult(int var1, int var2, Intent var3) {
      if (!this.mActivityResultRegistry.dispatchResult(var1, var2, var3)) {
         super.onActivityResult(var1, var2, var3);
      }

   }

   public void onBackPressed() {
      this.mOnBackPressedDispatcher.onBackPressed();
   }

   protected void onCreate(Bundle var1) {
      this.mSavedStateRegistryController.performRestore(var1);
      this.mContextAwareHelper.dispatchOnContextAvailable(this);
      super.onCreate(var1);
      ReportFragment.injectIfNeededIn(this);
      int var2 = this.mContentLayoutId;
      if (var2 != 0) {
         this.setContentView(var2);
      }

   }

   @Deprecated
   public void onRequestPermissionsResult(int var1, String[] var2, int[] var3) {
      if (!this.mActivityResultRegistry.dispatchResult(var1, -1, (new Intent()).putExtra("androidx.activity.result.contract.extra.PERMISSIONS", var2).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", var3)) && VERSION.SDK_INT >= 23) {
         super.onRequestPermissionsResult(var1, var2, var3);
      }

   }

   @Deprecated
   public Object onRetainCustomNonConfigurationInstance() {
      return null;
   }

   public final Object onRetainNonConfigurationInstance() {
      Object var3 = this.onRetainCustomNonConfigurationInstance();
      ViewModelStore var2 = this.mViewModelStore;
      ViewModelStore var1 = var2;
      if (var2 == null) {
         NonConfigurationInstances var4 = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
         var1 = var2;
         if (var4 != null) {
            var1 = var4.viewModelStore;
         }
      }

      if (var1 == null && var3 == null) {
         return null;
      } else {
         NonConfigurationInstances var5 = new NonConfigurationInstances();
         var5.custom = var3;
         var5.viewModelStore = var1;
         return var5;
      }
   }

   protected void onSaveInstanceState(Bundle var1) {
      Lifecycle var2 = this.getLifecycle();
      if (var2 instanceof LifecycleRegistry) {
         ((LifecycleRegistry)var2).setCurrentState(Lifecycle.State.CREATED);
      }

      super.onSaveInstanceState(var1);
      this.mSavedStateRegistryController.performSave(var1);
   }

   public Context peekAvailableContext() {
      return this.mContextAwareHelper.peekAvailableContext();
   }

   public final ActivityResultLauncher registerForActivityResult(ActivityResultContract var1, ActivityResultCallback var2) {
      return this.registerForActivityResult(var1, this.mActivityResultRegistry, var2);
   }

   public final ActivityResultLauncher registerForActivityResult(ActivityResultContract var1, ActivityResultRegistry var2, ActivityResultCallback var3) {
      return var2.register("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, var1, var3);
   }

   public final void removeOnContextAvailableListener(OnContextAvailableListener var1) {
      this.mContextAwareHelper.removeOnContextAvailableListener(var1);
   }

   public void reportFullyDrawn() {
      try {
         if (Trace.isEnabled()) {
            StringBuilder var1 = new StringBuilder();
            Trace.beginSection(var1.append("reportFullyDrawn() for ").append(this.getComponentName()).toString());
         }

         if (VERSION.SDK_INT > 19) {
            super.reportFullyDrawn();
         } else if (VERSION.SDK_INT == 19 && ContextCompat.checkSelfPermission(this, "android.permission.UPDATE_DEVICE_STATS") == 0) {
            super.reportFullyDrawn();
         }
      } finally {
         Trace.endSection();
      }

   }

   public void setContentView(int var1) {
      this.initViewTreeOwners();
      super.setContentView(var1);
   }

   public void setContentView(View var1) {
      this.initViewTreeOwners();
      super.setContentView(var1);
   }

   public void setContentView(View var1, ViewGroup.LayoutParams var2) {
      this.initViewTreeOwners();
      super.setContentView(var1, var2);
   }

   @Deprecated
   public void startActivityForResult(Intent var1, int var2) {
      super.startActivityForResult(var1, var2);
   }

   @Deprecated
   public void startActivityForResult(Intent var1, int var2, Bundle var3) {
      super.startActivityForResult(var1, var2, var3);
   }

   @Deprecated
   public void startIntentSenderForResult(IntentSender var1, int var2, Intent var3, int var4, int var5, int var6) throws IntentSender.SendIntentException {
      super.startIntentSenderForResult(var1, var2, var3, var4, var5, var6);
   }

   @Deprecated
   public void startIntentSenderForResult(IntentSender var1, int var2, Intent var3, int var4, int var5, int var6, Bundle var7) throws IntentSender.SendIntentException {
      super.startIntentSenderForResult(var1, var2, var3, var4, var5, var6, var7);
   }

   static final class NonConfigurationInstances {
      Object custom;
      ViewModelStore viewModelStore;
   }
}
